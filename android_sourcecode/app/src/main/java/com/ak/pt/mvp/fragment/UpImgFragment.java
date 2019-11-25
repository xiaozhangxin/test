package com.ak.pt.mvp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ak.pt.R;
import com.ak.pt.bean.ImgTypeBean;
import com.ak.pt.bean.ImgUpBean;
import com.ak.pt.bean.PressureImgBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.photoBeanListBean;
import com.ak.pt.mvp.adapter.ImgTypeAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.UpImgPresenter;
import com.ak.pt.mvp.view.IUpImgView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/1/22.
 */

public class UpImgFragment extends BaseFragment<IUpImgView, UpImgPresenter> implements IUpImgView {
    private static final int IMAGE = 0X02;
    private static final String SD_PATH = "/sdcard/pt/pic/";
    private static final String IN_PATH = "/pt/pic/";
    Unbinder unbinder;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ok)
    Button ok;
    private ImgTypeAdapter numAdapter;
    private List<ImgTypeBean> imgList;
    private int mPositon = 0;
    private String doc_no;//单据编号
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<String> chooseType = new ArrayList<>();//图片类型
    private ArrayList<String> allList = new ArrayList<>();//所有图片类型
    private ArrayList<ImgUpBean> upList = new ArrayList<>();//上传的图片列表
    //获取当前位置
    private String address;
    //初始化进度条
    private ProgressDialog pd;
    //选择图片类型弹窗
    private AlertDialog alertDialog;

    public static UpImgFragment newInstance(String doc_no, String address) {
        Bundle args = new Bundle();
        UpImgFragment fragment = new UpImgFragment();
        fragment.doc_no = doc_no;
        fragment.address = address;
        fragment.setArguments(args);
        return fragment;
    }

    public static File saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir().getAbsolutePath() + IN_PATH;
        }
        try {
            filePic = new File(savePath + UUID.randomUUID().toString() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return filePic;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_up_img;
    }

    @Override
    public void initUI() {

        tvTitle.setText(R.string.upload);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.choose_type);
        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        numAdapter = new ImgTypeAdapter(context, imgList);
        recycleView.setAdapter(numAdapter);
        numAdapter.setOnImageClickListener(new ImgTypeAdapter.OnImageClickListener() {
            @Override
            public void onDeleteClick(final int position) {
                deleteClass(position);
            }

            @Override
            public void onAdd(int data) {
                mPositon = data;
                show_img(IMAGE);
            }

            @Override
            public void onChildDelete(int position, int childPosition) {
                numAdapter.getItem(position).getUrlList().remove(childPosition);
                numAdapter.notifyItemChanged(position);
            }
        });
        getData();
        initList();
        initProgress();

    }

    //删除图片分类
    private void deleteClass(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.sure_delete_class);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numAdapter.remove(position);
                numAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //上传图片
   /* private void upImage(final List<ImgUpBean> allData) {
        final ArrayList<String> markList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            ImgUpBean imgUpBean = allData.get(i);
            Bitmap bitmap = adjustImage(imgUpBean.getUtl());
            Bitmap markBitmap = createBitmap(bitmap, imgUpBean.getType(), getDate());
            //保存bitmap 返回路劲
            String s = saveBitmap(context, markBitmap);
            markList.add(s);
            pd.setProgress(markList.size());
        }


        //压缩并上传
        final List<File> files = new ArrayList<>();
        Luban.with(getActivity())
                .load(markList)
                .ignoreBy(200).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(File file) {
                pd.setMessage("图片压缩中...");
                files.add(file);
                pd.setProgress(files.size());
                if (markList.size() == files.size()) {
                    pd.setMessage("图片上传中...");
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/order");
                    for (int i = 0; i < files.size(); i++) {
                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), files.get(i)));
                        pd.setProgress(i);
                    }
                    MultipartBody build = builder.build();
                    getPresenter().uploadFiles(userBean.getStaff_token(), build);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }*/

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    //打开选择图片界面
    protected void show_img(int requestCode) {
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtil.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.CHINA, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG)
                .withCropOption(new BoxingCropOption(destUri))
                .withMaxCount(100)
                .needCamera(R.drawable.ic_boxing_camera_white)
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(config).withIntent(context, BoxingActivity.class).start(this, requestCode);
    }

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    List<String> urlList = numAdapter.getItem(mPositon).getUrlList();
                    urlList.remove(urlList.size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            urlList.add(((ImageMedia) media).getThumbnailPath());
                        } else {
                            urlList.add(media.getPath());
                        }
                    }
                    urlList.add("");
                    numAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                List<ImgTypeBean> allData = numAdapter.getAllData();
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < allData.size(); i++) {
                    strings.add(allData.get(i).getTittle());
                }
                showSingleAlertDialog(strings);
                break;
            case R.id.ok:
                upList.clear();
                List<ImgTypeBean> allImg = numAdapter.getAllData();
                for (int i = 0; i < allImg.size(); i++) {
                    List<String> urlList = allImg.get(i).getUrlList();
                    for (int j = 0; j < urlList.size(); j++) {
                        String s = urlList.get(j);
                        if (!TextUtils.isEmpty(s)) {
                            upList.add(new ImgUpBean(numAdapter.getItem(i).getTittle(), s));
                        }
                    }
                }

                if (upList.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请先选择照片");
                    return;
                }
                ok.setEnabled(false);
                if (pd != null) {
                    pd.setMessage("上传进度...");
                    pd.setMax(upList.size());
                    pd.show();
                }
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        upImage();
                    }
                }.start();

                break;
        }
    }


    //保存bitmap到本地
/*    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir().getAbsolutePath() + IN_PATH;
        }
        try {
            filePic = new File(savePath + UUID.randomUUID().toString() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
             mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
             fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }*/

    private void upImage() {
        final ArrayList<String> markList = new ArrayList<>();
        //添加水印
        for (int i=0;i<upList.size();i++){
            pd.setProgress(i);
            ImgUpBean imgUpBean = upList.get(i);
            Bitmap bitmap = BitmapFactory.decodeFile(imgUpBean.getUtl());
            if (bitmap != null) {
                Bitmap alreadyBitmap = createBitmap(bitmap, imgUpBean.getType(), getDate());
                File target = saveBitmap(context, alreadyBitmap);
                markList.add(target.getAbsolutePath());
            }else {
                upList.remove(i);
            }
        }
        //压缩并上传
        final List<File> files = new ArrayList<>();
        Luban.with(getActivity())
                .load(markList)
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (markList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/order");
                    for (int i = 0; i < files.size(); i++) {
                        File mFile = files.get(i);
//                        Bitmap bitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath());
//                        Bitmap alreadyBitmap = createBitmap(bitmap, upList.get(i).getType(), getDate());
//                        File mAlreadFile = saveBitmap(context, alreadyBitmap);
                        pd.setProgress(upList.size() + i);
                        //自己创建想要保存的文件的文件对象
                        builder.addFormDataPart("file", mFile.getName(), RequestBody.create(MediaType.parse("image/jpeg"), mFile));
                    }

                    MultipartBody build = builder.build();
                    pd.setProgress(upList.size() * 2 + 1);
                    getPresenter().uploadFiles(userBean.getStaff_token(), build);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }

    private Bitmap adjustImage(String absolutePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        // 这个isjustdecodebounds很重要
        //opt.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeFile(absolutePath, opt);
/*
        // 获取到这个图片的原始宽度和高度
        int picWidth = opt.outWidth;
        int picHeight = opt.outHeight;

        // 获取屏的宽度和高度
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        // isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
        opt.inSampleSize = 1;
        // 根据屏的大小和图片大小计算出缩放比例
        if (picWidth > picHeight) {
            if (picWidth > screenWidth)
                opt.inSampleSize = picWidth / screenWidth;
        } else {
            if (picHeight > screenHeight)
                opt.inSampleSize = picHeight / screenHeight;
        }
        // 这次再真正地生成一个有像素的，经过缩放了的bitmap
        opt.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(absolutePath, opt);*/
        return bm;
    }

    //获取水印上的日期
    private String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        String day = format.format(date);
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String week = dateFm.format(date);
        SimpleDateFormat dateMinute = new SimpleDateFormat("HH:mm");
        String minute = dateMinute.format(date);
        return day + " " + week + " " + minute;
    }

    //给图片添加水印
    private Bitmap createBitmap(Bitmap bitmap, String imgType, String imgDate) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmpTemp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpTemp);
        //text
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        //背景
        TextPaint bgPaint = new TextPaint();
        bgPaint.setColor(getResources().getColor(R.color.bg_img));
        bgPaint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, 0, 0, textPaint);


        int roundradius = 5;//阴影背景角度
        int margin = 8;//透明背景距离左下的边距
        int textSize;//字体大小
        if (w < 500) {
            textSize = 6;
        } else if (w > 500 && w < 1000) {
            textSize = 16;
        } else {
            textSize = 32;

        }
        textPaint.setTextSize(textSize);//设置字体大小
        //计算最大长度
        int bgWidth = 0;
        Rect addressRect = new Rect();
        Rect dateRect = new Rect();
        textPaint.getTextBounds(address, 0, address.length(), addressRect);
        textPaint.getTextBounds(imgDate, 0, imgDate.length(), dateRect);
        int addressWidth = addressRect.width();
        int dateWidth = dateRect.width();
        if (addressWidth > dateWidth) {
            bgWidth = addressWidth;
        } else {
            bgWidth = dateWidth;
        }
        RectF rectF = new RectF(margin, h - textSize * 7 - margin * 3, bgWidth + margin * 5, h - margin);
        canvas.drawRoundRect(rectF, roundradius, roundradius, bgPaint);//画带圆角的透明背景
        String str = imgType + "\n\n" + imgDate + "\n\n" + address;
        StaticLayout staticLayout2 = new StaticLayout(str, textPaint, w, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        canvas.save();
        canvas.translate(margin * 3, h - textSize * 7 - 2 * margin);
        staticLayout2.draw(canvas);
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.save();
        canvas.restore();
        return bmpTemp;
    }

    @Override
    public void onInsertPiptbFixPhoto(String data) {
        if (pd != null) {
            pd.dismiss();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        numAdapter.removeAll();
        finish();
    }

    @Override
    public void onUploadFiles(String[] data) {
        ArrayList<photoBeanListBean> photoList = new ArrayList<>();
        for (int i = 0; i < upList.size(); i++) {
            photoBeanListBean photoBean = new photoBeanListBean();
            photoBean.setDoc_no(doc_no);
            photoBean.setType_name(upList.get(i).getType());
            photoBean.setImage_url(data[i]);
            photoList.add(photoBean);
        }
        String s = new Gson().toJson(photoList);
        map.clear();
        map.put("photoBeanList", s);
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().insertPiptbFixPhoto(userBean.getStaff_token(), map);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public UpImgPresenter createPresenter() {
        return new UpImgPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveData();
        unbinder.unbind();

    }

    @Override
    public void showProgress() {


    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }

    //保存选择的图片
    private void saveData() {
        PressureImgBean mBean = new PressureImgBean();
        mBean.setDoc_no(doc_no);
        mBean.setImgTypeBeanList(numAdapter.getAllData());
        String s = new Gson().toJson(mBean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString(doc_no, s);
        editor.commit();
    }

    //获取保存本地选择的图片
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString(doc_no, "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                if (doc_no.equals(object.getString("doc_no"))) {
                    String imgString = object.getString("imgTypeBeanList");
                    if (!TextUtils.isEmpty(imgString) && !"[]".equals(imgString)) {
                        List<ImgTypeBean> oldList = new ArrayList();
                        Gson gson = new Gson();
                        JsonArray array = new JsonParser().parse(imgString).getAsJsonArray();
                        for (JsonElement elem : array) {
                            ImgTypeBean imgBean = gson.fromJson(elem, ImgTypeBean.class);
                            oldList.add(imgBean);
                        }
                        numAdapter.addAll(oldList);

                    } else {
                        initAdapter();
                        numAdapter.addAll(imgList);
                    }
                } else {
                    initAdapter();
                    numAdapter.addAll(imgList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            initAdapter();
            numAdapter.addAll(imgList);
        }
    }

    //初始化图片列表
    private void initAdapter() {
        //用户门牌号","水表"，“过道”,"厨房","卫生间主","客厅","阳台南","阳台北","主卧","次卧","压力表","安装回执单"
        ArrayList<String> strings = new ArrayList<>();
        strings.add("");
        imgList.add(new ImgTypeBean("用户门牌号", strings));

        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("");
        imgList.add(new ImgTypeBean("水表", strings1));

        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("");
        imgList.add(new ImgTypeBean("过道", strings2));

        ArrayList<String> strings9 = new ArrayList<>();
        strings9.add("");
        imgList.add(new ImgTypeBean("厨房", strings9));

        ArrayList<String> strings6 = new ArrayList<>();
        strings6.add("");
        imgList.add(new ImgTypeBean("卫生间主", strings6));

        ArrayList<String> strings5 = new ArrayList<>();
        strings5.add("");
        imgList.add(new ImgTypeBean("客厅", strings5));

        ArrayList<String> strings8 = new ArrayList<>();
        strings8.add("");
        imgList.add(new ImgTypeBean("阳台南", strings8));
        ArrayList<String> strings7 = new ArrayList<>();
        strings7.add("");
        imgList.add(new ImgTypeBean("阳台北", strings7));

        ArrayList<String> strings3 = new ArrayList<>();
        strings3.add("");
        imgList.add(new ImgTypeBean("主卧", strings3));
        ArrayList<String> strings4 = new ArrayList<>();
        strings4.add("");
        imgList.add(new ImgTypeBean("次卧", strings4));


        ArrayList<String> string12 = new ArrayList<>();
        string12.add("");
        imgList.add(new ImgTypeBean("细节照片", string12));

        ArrayList<String> strings13 = new ArrayList<>();
        strings13.add("");
        imgList.add(new ImgTypeBean("线管", strings13));

        ArrayList<String> strings10 = new ArrayList<>();
        strings10.add("");
        imgList.add(new ImgTypeBean("压力表", strings10));
        ArrayList<String> strings11 = new ArrayList<>();
        strings11.add("");
        imgList.add(new ImgTypeBean("安装回执单", strings11));
    }

    private void initProgress() {
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
    }


    //-----------------------------------------------上传图片------------------------------------------------------//

    //初始化选择类别列表
    private void initList() {
        allList.add("用户门牌号");
        allList.add("小区图片");
        allList.add("安装回执单");
        allList.add("水表");
        allList.add("管道整改");
        allList.add("衣帽间");
        allList.add("储藏间");
        allList.add("照片");
        allList.add("产品全景");
        allList.add("细节照片");
        allList.add("管子固定整理");
        allList.add("电源固定");
        allList.add("门牌号");
        allList.add("过道");
        allList.add("厨房");
        allList.add("卫生间主");
        allList.add("卫生间客");
        allList.add("客厅");
        allList.add("阳台南");
        allList.add("阳台北");
        allList.add("压力表");
        allList.add("卫生间副");
        allList.add("前置过滤器");
        allList.add("回执单");
        allList.add("视屏");
        allList.add("管线");
        allList.add("线管");
        allList.add("车库");
        allList.add("主卧");
        allList.add("次卧");
        allList.add("客卧");
        allList.add("书房");
        allList.add("餐厅");
    }

    public void showSingleAlertDialog(ArrayList<String> strings) {
        chooseType.clear();
        ArrayList<String> mList = new ArrayList<>();
        mList.addAll(allList);
        mList.removeAll(strings);
        final String[] items = new String[mList.size()];
        mList.toArray(items);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(R.string.choose_img_type);
        alertBuilder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    chooseType.add(items[which]);
                } else {
                    chooseType.remove(items[which]);
                }
            }
        });
        alertBuilder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int m = 0; m < chooseType.size(); m++) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("");
                    numAdapter.add(new ImgTypeBean(chooseType.get(m), strings));
                }
                numAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });

        alertBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

}
