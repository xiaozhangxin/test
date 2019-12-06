package com.akan.qf.mvp.fragment.mine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.SimpleFragment;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/2.
 */

public class CoordinationLetterFragment extends SimpleFragment {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.viewBg)
    View viewBg;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.textView23)
    TextView textView23;
    private UserBean userBean;
    Bitmap image;

    public static CoordinationLetterFragment newInstance() {
        Bundle args = new Bundle();
        CoordinationLetterFragment fragment = new CoordinationLetterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_coordination;
    }

    @Override
    public void initUI() {
        tvTitle.setText("协调函");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        Bitmap bmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_logo, null);
        image = CodeUtils.createImage("https://www.wjx.top/m/33266402.aspx", 400, 400, bmap);
        ivCode.setImageBitmap(image);
    }

    @OnClick({R.id.ivLeft, R.id.textView4, R.id.textView3, R.id.link})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.link:
                startWeb("协调函","https://www.wjx.top/m/33266402.aspx");
                break;
            case R.id.textView3:
                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), image, "title", "description");
                ToastUtil.showToast(context.getApplicationContext(), "保存成功");
                break;
            case R.id.textView4:
                UMImage image1 = new UMImage(getActivity(), image);
                new ShareAction(getActivity())
                        .withText("扫一扫填写哦")
                        .withMedia(image1)
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                        .setCallback(umShareListener)
                        .open();
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtil.showToast(context.getApplicationContext(), "分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showToast(context.getApplicationContext(), "分享失败了");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showToast(context.getApplicationContext(), "分享取消了");

        }
    };


    /**
     * 保存生成的二维码图片
     */
    private Uri imageFileUri;

    private void saveBitmap(Bitmap bitmap, String bitName) {
        //获取与应用相关联的路径
        String imageFilePath = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        File imageFile = new File(imageFilePath, "/" + bitName);// 通过路径创建保存文件
        imageFileUri = Uri.fromFile(imageFile);
        if (imageFile.exists()) {
            imageFile.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(imageFile);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
                ToastUtil.showToast(context.getApplicationContext(), "保存成功");
            }
        } catch (Exception e) {
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