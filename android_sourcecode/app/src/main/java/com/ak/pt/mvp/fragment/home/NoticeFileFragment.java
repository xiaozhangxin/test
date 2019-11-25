package com.ak.pt.mvp.fragment.home;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ak.pt.R;
import com.ak.pt.bean.NoticeBean;
import com.ak.pt.mvp.activity.TbsFileActivity;
import com.ak.pt.mvp.adapter.NoticeFileAdapter;
import com.ak.pt.mvp.base.SimpleFragment;
import com.ak.pt.util.img.ShowPictureActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/17.
 */

public class NoticeFileFragment extends SimpleFragment {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    private List<NoticeBean.NoticeFileBeans> imgList;
    private NoticeFileAdapter adapter;

    private String fileName;

    public static NoticeFileFragment newInstance(List<NoticeBean.NoticeFileBeans> imgList, String fileName) {

        Bundle args = new Bundle();

        NoticeFileFragment fragment = new NoticeFileFragment();
        fragment.imgList = imgList;
        fragment.fileName = fileName;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_notice_file;
    }

    @Override
    public void initUI() {
        tvTitle.setText("附件列表");
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoticeFileAdapter(getContext(), imgList, fileName);
        recycleView.setAdapter(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {



                String upUrl = adapter.getItem(position).getFile_path();
                if (upUrl.endsWith(".png") | upUrl.endsWith(".jpg")|upUrl.endsWith(".jpeg")) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(upUrl);
                    Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) list);
                    intent.putExtra("position", position);
                    getActivity().startActivity(intent);
                } else {
                    Intent intentVisit = new Intent(getActivity(), TbsFileActivity.class);
                    intentVisit.putExtra("file_url", upUrl);
                    intentVisit.putExtra("file_name", "附件"+(position+1));
                    startActivity(intentVisit);

                }
            }
        });

    }

    ProgressDialog dialog1;

    /**
     * @param urlString：文件的URL
     * @param fileName：文件名
     */
    private void downFile(String urlString, String fileName) {
        HttpURLConnection urlConnection = null;
        filePath = Environment.getExternalStorageDirectory() + "/" + fileName;
        Log.e("******文件的保存路径", filePath);
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                fileLength = file.length();
            }
            urlConnection = (HttpURLConnection) new URL(urlString).openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestProperty("RANGE", "bytes=" + fileLength + "-");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.connect();
            contentLength = urlConnection.getContentLength();
            if (fileLength < (contentLength < 0 ? 1 : contentLength)) {
                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fos = new FileOutputStream(filePath, fileLength > 0);
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) > 0) {
                    fos.write(bytes, 0, len);
                    fileLength += len;
                    Message message1 = new Message();
                    message1.what = 1;
                    handler.sendMessage(message1);
                }
                fos.flush();
                fos.getFD().sync();
                fos.close();
                inputStream.close();
                Message message2 = new Message();
                message2.what = 2;
                handler.sendMessage(message2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }


    long fileLength = 0;//下载的文件长度
    long contentLength;//文件的总长度
    private String filePath;//文件的保存路径
    int len;
    private DocAdapterHandler handler = new DocAdapterHandler();


    private class DocAdapterHandler extends Handler {
        public void handleMessage(Message msg) {
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 0:
                        dialog1.setMessage(contentLength + "%");
                        Log.i("********文件长度-:", contentLength + "");
                        break;
                    case 1:
                        long x = (fileLength / contentLength * 100);
                        dialog1.setMessage(x + "%");
                        break;
                    case 2:
                        dialog1.dismiss();
                        // Toast.makeText(context.getApplicationContext(), "下载完成", Toast.LENGTH_LONG).show();
                        // MediaPlayerUtil.startOK();
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("下载完成");
                        builder.setNegativeButton("取消", null);
                        builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                File file = new File(filePath);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction(Intent.ACTION_VIEW);
                                String type = getMIMEType(file);
                                //设置intent的data和Type属性。
                                intent.setDataAndType(Uri.fromFile(file), type);
                                try {
                                    context.startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(context, "您没有安装Office文件", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.show();
                        break;
                }
            }
        }
    }

    public static String getMIMEType(File file) {
        String type = "*/*";
        String name = file.getName();
        int index = name.lastIndexOf('.');
        if (index < 0) {
            return type;
        }
        String end = name.substring(index, name.length()).toLowerCase();
        if (TextUtils.isEmpty(end)) return type;

        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private static final String[][] MIME_MapTable = {
            {".3gp", "video_v/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video_v/x-ms-asf"},
            {".avi", "video_v/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video_v/vnd.mpegurl"},
            {".m4v", "video_v/x-m4v"},
            {".mov", "video_v/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video_v/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video_v/mpeg"},
            {".mpeg", "video_v/mpeg"},
            {".mpg", "video_v/mpeg"},
            {".mpg4", "video_v/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    @Override
    public void initData() {

    }

    @OnClick(R.id.ivLeft)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
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