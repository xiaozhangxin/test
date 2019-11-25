package com.ak.pt.mvp.fragment.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FeedBackBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.FeedbackPresenter;
import com.ak.pt.mvp.view.water.IFeedbackView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/23.
 */

public class FeedBackDetailFragment extends BaseFragment<IFeedbackView, FeedbackPresenter> implements IFeedbackView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvQOne)
    TextView tvQOne;
    @BindView(R.id.tvQTwo)
    TextView tvQTwo;
    @BindView(R.id.tvWOne)
    TextView tvWOne;
    @BindView(R.id.tvWTwo)
    TextView tvWTwo;
    @BindView(R.id.tvWThree)
    TextView tvWThree;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.takeVideo)
    ImageView takeVideo;
    @BindView(R.id.tlttleLine)
    View tlttleLine;
    @BindView(R.id.imgTittle)
    TextView imgTittle;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.llDepartment)
    LinearLayout llDepartment;
    @BindView(R.id.tvDepartmentAdd)
    TextView tvDepartmentAdd;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private FeedBackBean bean;

    private DialogLoading dialogLoading;

    private AppPermissionsBean permissionsBean;

    public static FeedBackDetailFragment newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FeedBackDetailFragment fragment = new FeedBackDetailFragment();
        fragment.detail_id = detail_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_feed_back_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("产品质量反馈详情");
        dialogLoading = new DialogLoading(context);
        dialogLoading.showDialog();
        llDepartment.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("quality_id", detail_id);
        getPresenter().getQualityFeedbackDetail(userBean.getStaff_token(), map);
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.takeVideo})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.takeVideo:
                Uri parse = Uri.parse(Constants.BASE_URL + bean.getFileList().get(0).getFile_url());
                showVideo(parse);
                break;
            case R.id.tvRight:
                String s = tvRight.getText().toString();
                switch (s) {
                    case "删除":
                        onDelete();
                        break;
                    case "编辑":
                        startFeedBackAddFragment(bean, "1", permissionsBean);
                        break;
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                }

                break;
        }

    }


    private AlertDialog alertDialog1;

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startFeedBackAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        onDelete();
                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要删除此单据吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("quality_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteQualityFeedback(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 播放视频
    private void showVideo(Uri fileUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "video/mp4");
        startActivity(intent);
    }

    @Override
    public void onInsertQualityFeedback(String data) {

    }

    @Override
    public void onDeleteQualityFeedback(String data) {
        EventBus.getDefault().post(new FirstEventFilter("filter_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteQualityFile(String data) {

    }

    @Override
    public void onUpdateQualityFeedback(String data) {

    }

    @Override
    public void OnGetQualityFeedbackList(List<FeedBackBean> data) {

    }

    @Override
    public void onGetQualityFeedbackDetail(final FeedBackBean data) {
        if (TextUtils.isEmpty(data.getQuality_id())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.dismiss();
                }
            });
            builder1.onCreate().show();
            return;
        }
        bean = data;

        //权限控制
        String appOperation = permissionsBean.getApp_operation();

        if (appOperation.contains("1") && appOperation.contains("2")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("更多");
        } else if (appOperation.contains("1") && !appOperation.contains("2")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("编辑");

        } else if (!appOperation.contains("1") && appOperation.contains("2")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("删除");
        } else if (!appOperation.contains("1") && !appOperation.contains("2")) {
            tvRight.setVisibility(View.GONE);
        }
        tvDepartmentAdd.setText(data.getDepartment_name());
        tvNo.setText(data.getQuality_no());
        tvName.setText(data.getStaff_name());
        tvJobName.setText(data.getJob_name());
        tvTime.setText(data.getCreate_time());

        tvOne.setText(data.getCustomer_name());
        tvTwo.setText(data.getCustomer_sex());
        tvThree.setText(data.getCustomer_tel());
        tvFour.setText(data.getCustomer_address() + "-" + data.getAddress_info());
        //  tvFive.setText(data.getAddress_code());

        tvQOne.setText(data.getQuality_name());
        tvQTwo.setText(data.getQuality_tel());

        tvWOne.setText(data.getFault_model() + data.getFault_no());
        tvWTwo.setText(data.getProduct_no());
        tvWThree.setText(data.getQuality_info());

        if (data.getFileList().size() > 0) {
            takeVideo.setVisibility(View.VISIBLE);
            tlttleLine.setVisibility(View.VISIBLE);
            imgTittle.setVisibility(View.VISIBLE);
            //Bitmap videoThumbnail = getNetVideoBitmap(Constants.BASE_URL + data.getFileList().get(0).getFile_url());
            //takeVideo.setImageBitmap(videoThumbnail);
            // takeVideo.setImageBitmap(createVideoThumbnail(Constants.BASE_URL + data.getFileList().get(0).getFile_url(), MediaStore.Images.Thumbnails.MINI_KIND));

        } else {
            takeVideo.setVisibility(View.GONE);
            tlttleLine.setVisibility(View.GONE);
            imgTittle.setVisibility(View.GONE);


        }
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
    }

    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://")) {
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            } else {
                retriever.setDataSource(filePath);
            }
            bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC); //retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null) {
            return null;
        }

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {//压缩图片 开始处
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512) {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }//压缩图片 结束处
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                    96,
                    96,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }


    @Override
    public void onUploadFiles(String[] data) {

    }


    public Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Override
    public FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

