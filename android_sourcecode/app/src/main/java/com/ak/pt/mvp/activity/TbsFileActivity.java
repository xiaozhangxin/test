package com.ak.pt.mvp.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.githang.statusbar.StatusBarCompat;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

import java.io.File;

import static com.ak.pt.mvp.fragment.home.NoticeFileFragment.getMIMEType;


public class TbsFileActivity extends AppCompatActivity implements ReaderCallback {

    public static final int REQUEST_WRITE = 0x01;
    private TbsReaderView mTbsReaderView;
    private DownloadManager mDownloadManager;
    private long mRequestId;
    private DownloadObserver mDownloadObserver;
    private String mName;
    private String mUrl;
    private Button mDownloadBtn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs_file);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);

        String file_name = getIntent().getStringExtra("file_name");
        if (TextUtils.isEmpty(file_name)) {
            tvTitle.setText("未命名");
        } else {
            tvTitle.setText(file_name);
        }
        mUrl = Constants.BASE_URL + getIntent().getStringExtra("file_url");
        mDownloadBtn = (Button) findViewById(R.id.tvOpen);
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocalExist(mName)) {
                    displayFile(mName);
                } else {
                    startDownload(mUrl, mName);
                }
            }
        });

        mName = parseName(mUrl);
        mTbsReaderView = new TbsReaderView(this, this);
        LinearLayout rlRoot = (LinearLayout) findViewById(R.id.rl_root);
        rlRoot.addView(mTbsReaderView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        if (isLocalExist(mName)) {
            displayFile(mName);
        } else {
            startDownload(mUrl, mName);
        }
        ImageView ivLeft = (ImageView) findViewById(R.id.ivLeft);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_WRITE){
            if (isLocalExist(mName)) {
                displayFile(mName);
            } else {
                startDownload(mUrl, mName);
            }
        }

    }

    private void displayFile(String fileNAme) {
        if (fileNAme.endsWith(".doc") | fileNAme.endsWith(".docx") | fileNAme.endsWith(".xls") | fileNAme.endsWith(".xlsx") | fileNAme.endsWith(".pdf") | fileNAme.endsWith(".txt")) {
            mDownloadBtn.setVisibility(View.GONE);
            Bundle bundle = new Bundle();
            bundle.putString("filePath", getLocalFile(fileNAme).getPath());
            bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
            boolean result = mTbsReaderView.preOpen(parseFormat(fileNAme), false);
            if (result) {
                mTbsReaderView.openFile(bundle);
            }
        } else {
            Intent intent = new Intent();
            File file = new File(getLocalFile(fileNAme).getPath());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            String type = getMIMEType(file);
            //设置intent的data和Type属性。
            intent.setDataAndType(Uri.fromFile(file), type);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "您没有安装Office文件", Toast.LENGTH_SHORT).show();
            }


        }

    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        return fileName;
    }

    private boolean isLocalExist(String fileName) {
        return getLocalFile(fileName).exists();
    }

    private File getLocalFile(String fileName) {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
    }

    private void startDownload(String url, String fileNAme) {
        mDownloadObserver = new DownloadObserver(new Handler());
        getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true, mDownloadObserver);

        mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Request request = new Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileNAme);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(Request.VISIBILITY_HIDDEN);
        mRequestId = mDownloadManager.enqueue(request);
    }

    private void queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(mRequestId);
        Cursor cursor = null;
        try {
            cursor = mDownloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载的字节数
                int currentBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //总需下载的字节数
                int totalBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //状态所在的列索引
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                Log.i("downloadUpdate: ", currentBytes + " " + totalBytes + " " + status);
                mDownloadBtn.setText("正在下载：" + currentBytes + "/" + totalBytes);
                if (DownloadManager.STATUS_SUCCESSFUL == status && mDownloadBtn.getVisibility() == View.VISIBLE) {
                    mDownloadBtn.setText("打开文件");
                    mDownloadBtn.performClick();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
        if (mDownloadObserver != null) {
            getContentResolver().unregisterContentObserver(mDownloadObserver);
        }
    }

    private class DownloadObserver extends ContentObserver {

        private DownloadObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            Log.i("downloadUpdate: ", "onChange(boolean selfChange, Uri uri)");
            queryDownloadStatus();
        }
    }
}
