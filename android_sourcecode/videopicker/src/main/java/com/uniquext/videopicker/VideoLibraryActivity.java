package com.uniquext.videopicker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.PermissionCallback;
import com.uniquext.android.rxlifecycle.base.RxAppCompatActivity;
import com.uniquext.android.rxlifecycle.event.ActivityEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 　 　　   へ　　　 　／|
 * 　　    /＼7　　　 ∠＿/
 * 　     /　│　　 ／　／
 * 　    │　Z ＿,＜　／　　   /`ヽ
 * 　    │　　　 　　ヽ　    /　　〉
 * 　     Y　　　　　   `　  /　　/
 * 　    ｲ●　､　●　　⊂⊃〈　　/
 * 　    ()　 へ　　　　|　＼〈
 * 　　    >ｰ ､_　 ィ　 │ ／／      去吧！
 * 　     / へ　　 /　ﾉ＜| ＼＼        比卡丘~
 * 　     ヽ_ﾉ　　(_／　 │／／           消灭代码BUG
 * 　　    7　　　　　　　|／
 * 　　    ＞―r￣￣`ｰ―＿
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author UniqueXT
 * @version 1.0
 * @date 2019/5/29  10:49
 */
public class VideoLibraryActivity extends RxAppCompatActivity {

    private AppCompatImageView mVideoBack;
    private AppCompatTextView mPickFinished;

    private int mPickTotal = 1;
    private int mMaxSize = Integer.MAX_VALUE;
    private VideoAdapter mVideoAdapter;
    private List<VideoBean> mVideoList = new ArrayList<>();
    private Set<VideoBean> mPickedVideo = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_video_library);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(Environment.getExternalStorageDirectory())));

        mVideoBack = findViewById(R.id.title_bar_back);
        mPickFinished = findViewById(R.id.pick_finished);

        RecyclerView recyclerView = findViewById(R.id.video_grid);
        mVideoAdapter = new VideoAdapter(mVideoList);
        recyclerView.setAdapter(mVideoAdapter);

        mVideoBack.setOnClickListener(v -> onBackPressed());
        mPickFinished.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra(VideoHelper.PICK_VIDEO, new ArrayList<>(mPickedVideo));
            setResult(Activity.RESULT_OK, intent);
            onBackPressed();
        });
        mVideoAdapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showVideo(mVideoList.get(position).getFilePath());
            }
        });

        mPickTotal = getIntent().getIntExtra(VideoHelper.PICK_TOTAL, 1);
        mMaxSize = getIntent().getIntExtra(VideoHelper.MAX_VIDEO_SIZE, Integer.MAX_VALUE);
        LightPermission.with(this)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .result(new PermissionCallback() {
                    @Override
                    public void onGranted() {
                        setImageList();
                    }

                    @Override
                    public void onDenied(String[] strings) {
                        showWarning();
                    }

                    @Override
                    public void onNoRequest(String[] strings) {
                        this.onDenied(strings);
                    }
                });
    }

    private void showWarning() {
        new AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("访问视频库需要获取手机存储权限！")
                .setPositiveButton("确定", (dialog, which) -> finish())
                .show();
    }

    private void showVideo(String filePath){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filePath)), "video/mp4");
        startActivity(intent);
    }

    private void setImageList() {
        Observable.create((ObservableOnSubscribe<VideoBean>) emitter -> {
            Cursor cursor = getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    new String[]{
                            MediaStore.Video.Media._ID,
                            MediaStore.Video.Media.DATA,
                            MediaStore.Video.Media.DISPLAY_NAME,
                            MediaStore.Video.Media.SIZE,
                            MediaStore.Video.Media.DURATION
                    },
                    MediaStore.Video.Media.SIZE + "> ?",
                    new String[]{String.valueOf(0)},
                    MediaStore.Video.Media.DATE_ADDED + " DESC"
            );
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    VideoBean videoBean = new VideoBean(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID)));
                    videoBean.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)));
                    videoBean.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME)));
                    videoBean.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE)));
                    videoBean.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION)));
                    emitter.onNext(videoBean);
                }
                cursor.close();
            }
            emitter.onComplete();
        }).doOnNext(videoBean -> videoBean.setThumbPath(VideoHelper.getThumbPathByCursor(this, String.valueOf(videoBean.getId()))))
                .doOnNext(videoBean -> {
                    if (TextUtils.isEmpty(videoBean.getThumbPath()))
                        videoBean.setThumbPath(videoBean.getFilePath());
                })
                .compose(SchedulersTransformer.observableIO2Main())
                .doOnNext(videoBean -> mVideoList.add(videoBean))
                .map(videoBean -> mVideoList.indexOf(videoBean))
                .compose(bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer i) {
                        mVideoAdapter.notifyItemInserted(i);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
//                        mVideoAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void refreshPage(int size) {
        if (size == 0) {
            mPickFinished.setEnabled(false);
            mPickFinished.setAlpha(0.6f);
        } else {
            mPickFinished.setEnabled(true);
            mPickFinished.setAlpha(1f);
            mPickFinished.setText(String.format(Locale.CHINA, "完成%d/%d", size, mPickTotal));
        }
    }

    private String formatDuration(long time) {
        int second = (int) (time % 60);
        int minute = (int) (time / 60 % 60);
        int hour = (int) (time / 60 / 60);
        if (hour > 0) {
            return String.format(Locale.CHINA, "%d:%02d:%02d", hour, minute, second);
        } else {
            return String.format(Locale.CHINA, "%02d:%02d", minute, second);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private class VideoAdapter extends CommonRecyclerAdapter<VideoBean> {

        VideoAdapter(@NonNull List<VideoBean> data) {
            super(data, R.layout.item_video_library);
        }

        @Override
        protected void convert(CommonRecyclerHolder holder, int position, VideoBean videoBean) {
            ImageView imageView = holder.getView(R.id.item_video_thumb);
            CheckBox checkBox = holder.getView(R.id.item_pick_status);
            TextView textView = holder.getView(R.id.item_video_duration);
            textView.setText(formatDuration(videoBean.getDuration() / 1000));
            Glide.with(imageView.getContext()).load(videoBean.getThumbPath()).into(imageView);
            checkBox.setTag(videoBean);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (mPickedVideo.size() >= mPickTotal) {
                        buttonView.setChecked(false);
                        showToast(String.format(Locale.CHINA, "最多选取%d个!", mPickTotal));
                    } else if (((VideoBean) buttonView.getTag()).getSize() > mMaxSize) {
                        buttonView.setChecked(false);
                        showToast("视频过大!");
                    } else {
                        mPickedVideo.add((VideoBean) buttonView.getTag());
                    }
                } else {
                    mPickedVideo.remove((VideoBean) buttonView.getTag());
                }
                refreshPage(mPickedVideo.size());
            });
        }
    }
}
