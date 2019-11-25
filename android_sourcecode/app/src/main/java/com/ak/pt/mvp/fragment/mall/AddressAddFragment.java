package com.ak.pt.mvp.fragment.mall;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.core.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AddressBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.AddressPresenter;
import com.ak.pt.mvp.view.IAddressView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.githang.statusbar.StatusBarCompat;

import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_LOCATION;

/**
 * Created by admin on 2019/5/7.
 */

public class AddressAddFragment extends BaseFragment<IAddressView, AddressPresenter> implements IAddressView {
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
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.chooseBook)
    ImageView chooseBook;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.etAddressDetail)
    EditText etAddressDetail;
    @BindView(R.id.btOne)
    RadioButton btOne;
    @BindView(R.id.btTwo)
    RadioButton btTwo;
    @BindView(R.id.btThree)
    RadioButton btThree;
    @BindView(R.id.tvSwitch)
    Switch tvSwitch;
    Unbinder unbinder;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvDelete)
    TextView tvDelete;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mProvince = "";
    private String mCity = "";
    private String mDistrict = "";
    //获取当前位置
    private AMapLocationClient mLocationClient = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private AddressBean bean;
    private String type;

    public static AddressAddFragment newInstance(AddressBean bean, String type) {

        Bundle args = new Bundle();

        AddressAddFragment fragment = new AddressAddFragment();
        fragment.bean = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mall_address_add;
    }

    @Override
    public void initUI() {
        switch (type) {
            case "0":
                tvTitle.setText("新增收货地址");
                tvDelete.setVisibility(View.GONE);
                break;
            case "1":
                tvTitle.setText("编辑收货地址");
                tvDelete.setVisibility(View.VISIBLE);
                etName.setText(bean.getAddress_name());
                etPhone.setText(bean.getAddress_mobile());
                etAddressDetail.setText(bean.getAddress_detail());
                tvAddress.setText(bean.getAddress_province() + " " + bean.getAddress_city() + " " + bean.getAddress_district());
                mProvince = bean.getAddress_province();
                mCity = bean.getAddress_city();
                mDistrict = bean.getAddress_district();
                if ("1".equals(bean.getIs_default())) {
                    tvSwitch.setChecked(true);
                } else {
                    tvSwitch.setChecked(false);
                }

                String address_flag = bean.getAddress_flag();
                switch (address_flag) {
                    case "家":
                        btOne.setChecked(true);
                        break;
                    case "公司":
                        btTwo.setChecked(true);
                        break;
                    case "学校":
                        btThree.setChecked(true);
                        break;
                }
        }


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(type)) {
            if (Build.VERSION.SDK_INT >= 23) {
                showContacts();
            } else {
                initLocation();
            }
        }

    }

    @OnClick({R.id.ivLeft, R.id.chooseBook, R.id.tvAddress, R.id.btOne, R.id.btTwo, R.id.btThree,
            R.id.tvSwitch, R.id.tvDelete, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.chooseBook:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, 0);
                break;
            case R.id.btOne:
                break;
            case R.id.btTwo:
                break;
            case R.id.btThree:
                break;
            case R.id.tvAddress:
                hideInputMethod(etPhone);
                cityPicker(mProvince,mCity,mDistrict);
                break;
            case R.id.tvSwitch:
                break;
            case R.id.tvDelete:
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("是否删除当前地址？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        map.clear();
                        map.put("address_id", bean.getAddress_id());
                        getPresenter().deleteAddress(userBean.getStaff_token(), map);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();
                break;
            case R.id.ok:

                String mName = etName.getText().toString().trim();
                if (TextUtils.isEmpty(mName)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写收货人姓名");
                    return;
                }
                String mPhone = etPhone.getText().toString();
                if (TextUtils.isEmpty(mPhone)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写手机号");
                    return;
                }
                String mAddress = tvAddress.getText().toString();
                if (TextUtils.isEmpty(mAddress)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择所在地区");
                    return;
                }
                String mAddressDetail = etAddressDetail.getText().toString();
                if (TextUtils.isEmpty(mAddressDetail)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写详细地址");
                    return;
                }
                ok.setEnabled(false);
                map.put("staff_id", userBean.getStaff_id());
                map.put("address_name", mName);
                map.put("address_mobile", mPhone);
                map.put("address_province", mProvince);
                map.put("address_city", mCity);
                map.put("address_district", mDistrict);

                map.put("address_detail", mAddressDetail);
                if (tvSwitch.isChecked()) {
                    map.put("is_default", "1");
                }
                int count = radioGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        map.put("address_flag", rb.getText().toString());
                        break;
                    }
                }
                if ("1".equals(type)) {
                    map.put("address_id", bean.getAddress_id());

                }
                getPresenter().insertUpdateAddress(userBean.getStaff_token(), map);
                break;
        }
    }
    //----------------------------------定位------------------------------------------------//

    //申请定位权限
    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            initLocation();
        }
    }


    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    //异步获取定位结果
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    mProvince = amapLocation.getProvince();
                    mCity = amapLocation.getCity();
                    mDistrict = amapLocation.getDistrict();
                }
                mLocationClient.stopLocation();
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                ContentResolver reContentResolverol = getActivity().getContentResolver();
                Uri contactData = data.getData();
                @SuppressWarnings("deprecation")
                Cursor cursor = getActivity().managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();     // 获取联系人的姓名
                try {
                    String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

                    while (phone.moveToNext()) {
                        // 获取联系人号码

                        String usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        etPhone.setText(usernumber); // 显示返回的号码
                        etName.setText(username); // 显示返回的联系人姓名
                    }
                } catch (Exception e) {


                }
            }

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocation();
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "请开启定位权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 显示城市列表
     */
    private void cityPicker(String province,String  city,String district) {
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#fafafa")
                .titleTextColor("#666666")
                .backgroundPop(0xa0000000)
                .confirTextColor("#33aaff")
                .cancelTextColor("#999999")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#666666"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(8)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];

                tvAddress.setText(province + " " + city + " " + district);
                mProvince = province;
                mCity = city;
                mDistrict = district;

            }

            @Override
            public void onCancel() {
            }

        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLocationClient!=null){
        mLocationClient.onDestroy();}
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

    @Override
    public void OnGetMemberAddressList(List<AddressBean> data) {

    }

    @Override
    public void onInsertUpdateAddress(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onDeleteAddress(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onSetDefaultAddress(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
    }
}