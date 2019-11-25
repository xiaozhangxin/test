package com.ak.pt.mvp.fragment.water;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ak.pt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2019/5/27.
 */

public class VidePalyActivity extends AppCompatActivity {

    @BindView(R.id.video_local)
    VideoView mVideoLocal;
    @BindView(R.id.video_net)
    VideoView mVideoNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ButterKnife.bind(this);
        String uri = getIntent().getStringExtra("uri");
        initLocalVideo();
        initNetVideo();
    }

    //播放本地视频
    private void initLocalVideo() {
        //设置有进度条可以拖动快进
        MediaController localMediaController = new MediaController(this);
        mVideoLocal.setMediaController(localMediaController);
        String uri = ("android.resource://" + getPackageName() + "/" );
        mVideoLocal.setVideoURI(Uri.parse(uri));
        mVideoLocal.start();
    }

    //播放网络视频
    private void initNetVideo() {
        //设置有进度条可以拖动快进
        MediaController localMediaController = new MediaController(this);
        mVideoNet.setMediaController(localMediaController);
        String url = "https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4";
        mVideoNet.setVideoPath(url);
        mVideoNet.start();
    }
}
