package com.akan.qf.mvp.fragment.mine;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.NoticePresenter;
import com.akan.qf.mvp.view.mine.INoticeView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by admin on 2018/6/28.
 */

public class MineFragment extends BaseFragment<INoticeView, NoticePresenter> implements INoticeView {

    Unbinder unbinder;
    @BindView(R.id.topView)
    View topView;
    @BindView(R.id.imageView)
    CircleImageView imageView;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.ivMessage)
    ImageView ivMessage;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJob)
    TextView tvJob;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvPersonInfo)
    TextView tvPersonInfo;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.tvNotice)
    TextView tvNotice;
    @BindView(R.id.numTwo)
    TextView numTwo;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;
    @BindView(R.id.tvSecurity)
    TextView tvSecurity;
    @BindView(R.id.tvShare)
    TextView tvShare;
    @BindView(R.id.tvCard)
    TextView tvCard;
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    @BindView(R.id.viewEight)
    TextView viewEight;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initUI() {
    }

    @Override
    public void initData() {


    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvPhone.setText(userBean.getStaff_account());
        tvName.setText(userBean.getModule_role_names());
        tvJob.setText(userBean.getJob_name());
        tvDepartment.setText(userBean.getDepartment_name());
        Glide.with(getContext())
                .load(Constants.BASE_URL + userBean.getHead_img())
                .error(R.drawable.error_img)
                .into(imageView);
        map.clear();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getNotReadNoticeCount(userBean.getStaff_token(), map);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public NoticePresenter createPresenter() {
        return new NoticePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        if (msg.equals("refreshNotice")) {
            userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
            map.clear();
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getNotReadNoticeCount(userBean.getStaff_token(), map);
        }

    }



    @OnClick({R.id.imageView, R.id.ivCode, R.id.ivMessage, R.id.tvName, R.id.tvJob, R.id.tvDepartment,
            R.id.tvPersonInfo, R.id.tvQuestion, R.id.tvNotice, R.id.tvSecurity,
            R.id.tvShare, R.id.tvCard, R.id.tvSetting, R.id.tvPhone, R.id.viewEight, R.id.tvAddOne})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ivCode:
                startCardFragment();
                break;
            case R.id.ivMessage:
                //startMessageFragment("1");
                startNoticeFragment();
                break;
            case R.id.tvDepartment:
            case R.id.tvPersonInfo:
            case R.id.imageView:
            case R.id.tvName:
            case R.id.tvPhone:
            case R.id.tvJob:
                startPersonalnfoFragment();
                break;
            case R.id.tvQuestion:
                ToastUtil.showToast(context.getApplicationContext(), "功能开发中...");
                break;
            case R.id.tvNotice:
                startNoticeFragment();
                break;
        /*    case R.id.tvContributions:
                startContributionsFragment(new PermissionsBean());
                break;*/
            case R.id.tvAddOne:
                startCoordinationLetterFragment();
                break;
            case R.id.tvSecurity:
                startSecurityCheckFragment();
                break;
            case R.id.tvShare:
			    /*
                new ShareAction(getActivity())
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                        .addButton(getString(R.string.copy_link), getString(R.string.app_name), "share_copy", "share_copy")
                        .setShareboardclickCallback(shareBoardlistener)
                        .open();
						*/
				android.util.Log.v("alex","startShare");
				startShare();		
                break;
            case R.id.tvCard:
                startCardFragment();
                break;
            case R.id.tvSetting:
                startSettingFragment();
                break;
            case R.id.viewEight:
                final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
                builder1.setMessage("是否确认拨打客服电话?");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq("4008208717");
                        dialog.dismiss();
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.onCreate().show();
                break;
        }
    }

    private void startShare() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.download_link));
        startActivity(Intent.createChooser(textIntent, "分享"));
    }
    private void sq(String phone) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.authorized));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        } else {
            CallPhone(phone);
        }
    }

    private void CallPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + phone); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
    }

    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {
        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            if (share_media == null) {
                //根据key来区分自定义按钮的类型，并进行对应的操作
                if (snsPlatform.mKeyword.equals(getString(R.string.app_name))) {
                    ClipboardManager manager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
                    manager.setText(getResources().getString(R.string.down_url));
                    Toast.makeText(getContext().getApplicationContext(), "链接复制成功", Toast.LENGTH_SHORT).show();
                }
            } else {//社交平台的分享行为
                UMImage image = new UMImage(getActivity(), R.mipmap.ic_launcher);
                UMWeb web = new UMWeb(getString(R.string.down_url));
                web.setTitle(getString(R.string.app_name));
                web.setThumb(image);
                web.setDescription(getString(R.string.share_description));
                new ShareAction(getActivity())
                        .withMedia(web)
                        .setPlatform(share_media)
                        .setCallback(umShareListener)
                        .share();
            }
        }
    };


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
    public void OnGetNoticeList(List<NoticeBean> data, String total) {

    }

    @Override
    public void OnGetNoticeDetail(NoticeBean data) {

    }

    @Override
    public void OnGetNotReadNoticeCount(String data) {

        if ("0".equals(data)) {
            num.setVisibility(View.GONE);
            numTwo.setVisibility(View.GONE);
        } else {
            num.setVisibility(View.VISIBLE);
            numTwo.setVisibility(View.VISIBLE);
            num.setText(data);
            numTwo.setText(data);
        }
    }
}
