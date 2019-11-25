package com.ak.pt;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/8
 */

public final class Constants {


    // public static final String BASE_URL = "http://pressuretest.akan.com.cn:81/";
//    public static final String BASE_URL = "http://192.168.0.27:8080/";
    public static final String BASE_URL = "http://pressure.akan.com.cn/";
//    public static final String BASE_URL = "http://pressure-pre.akan.com.cn:81";


    public static final String USER_BEAN = "userbean";
    public static final String IMG_LIST = "img_list";
    public static final String IMG_NAME = "img_name";
    public static final String DETAIL_ID = "detail_id";
    public static final String ADDRESS = "address";
    public static final String DETAIL_IDS = "detail_ids";
    public static final String DETAIL_BEAN = "detail_bean";
    public static final String PERMISSION_BEAN = "permission_bean";
    public static final String KEY_FRAGMENT = "key_fragment";
    public static final String BLEDEVICE = "bledevice";
    public static final String HEAD_IMG = "head_img";
    public static final String KEY_TITLE = "key_title";
    public static final String TYPE = "type";
    public static final String KEY_DATA = "key_data";
    public static final String PT_BEAN = "ptBean";
    public static final String PT_MODE = "pt_mode";

    public static final String KEY_IS_TAB_LIVE = "key_is_tab_live";

    public static final String KEY_UID = "key_uid";
    public static final String USER_ID = "member_id";
    public static final String USER_TOKEN = "member_token";
    public static final String KEY_SLUG = "key_slug";

    public static final String KEY_URL = "key_url";
    public static final String IS_RED = "is_red";
    public static final String DEPARTMENT_TYPE = "department_type";
    //网络类型
    public static final int NETTYPE_WIFI = 0;
    public static final int NETTYPE_NONE = 1;
    public static final int NETTYPE_2G = 2;
    public static final int NETTYPE_3G = 3;
    public static final int NETTYPE_4G = 4;

    public static final int TOKEN_EXPRIED = 0X98;
    //-----------------------------------------

    public static final int ROOM_FRAGMENT = 0X01;
    public static final int LIVE_FRAGMENT = 0X02;
    public static final int WEB_FRAGMENT = 0X03;
    public static final int LOGIN_FRAGMENT = 0X04;
    public static final int ABOUT_FRAGMENT = 0X05;
    public static final int FULL_ROOM_FRAGMENT = 0X06;
    public static final int SEARCH_FRAGMENT = 0X07;
    public static final int PERSONAL_INFO = 0X08;
    public static final int CHANGE_PWD = 0X09;
    public static final int SAFTWARE = 0X10;
    public static final int CHECK = 0X11;
    public static final int TEST_PRESSURE = 0X12;
    public static final int PRESSURE_DETAIL = 0X13;
    public static final int BLE_LIST = 0X14;
    public static final int PRESSURE_BEGIN = 0X15;
    public static final int MESSAGE = 0X16;
    public static final int MESSAGE_DETAIL = 0X17;
    public static final int Sign_DETAIL = 0X18;
    public static final int AREA_PRESSURE = 0X19;
    public static final int BIG_PRESSURE = 0X20;
    public static final int ORDER_ADD = 0X22;
    public static final int CHOOSE_WORKER = 0X23;
    public static final int NOTICE = 0X24;
    public static final int NOTICE_DETAIL = 0X25;
    public static final int NOTICE_FILE = 0X26;
    public static final int NOTICE_SEARCH = 0X27;
    public static final int BOOK = 0X28;
    public static final int CHOOSE_DEPARTMENT = 0X29;
    public static final int ORDER_ACCEPT = 0X30;
    public static final int ORDER_MANAGER = 0X31;
    public static final int MANAGER_DETAIL = 0X32;
    public static final int PT_SETTING = 0X33;
    public static final int PT_SETTING_CHILD = 0X34;
    public static final int UP_IMG = 0X35;
    public static final int IMG_TWO = 0X36;
    public static final int PT_RESULT = 0X37;
    public static final int Sign_DETAIL_NEW = 0X38;
    public static final int DAILY_DETAIL = 0X39;
    public static final int DAILY_ADD = 0X40;
    public static final int MONTH_DETAIL = 0X41;
    public static final int MONTH_ADD = 0X42;
    public static final int WEEKLY_DETAIL = 0X43;
    public static final int WEEKLY_ADD = 0X44;
    public static final int FORGOT_PWD = 0X45;
    public static final int INTEGRAL = 0X46;
    public static final int INTEGRAL_SEARCH = 0X47;
    public static final int OFFICE_DOCUMENT = 0X48;
    public static final int OFFICE_SEARCH = 0X49;
    public static final int SECURITY_LIST = 0X50;
    public static final int DOCUMENT = 0X51;
    public static final int DOCUMENT_SEARCH = 0X52;
    public static final int ACCOUNT = 0X53;
    public static final int MALL = 0X54;
    public static final int GOODS_DETAIL = 0X55;
    public static final int ORDER_ADD_WORKER = 0X56;
    public static final int CONFIRM_ORDER = 0X57;
    public static final int MALL_ADDRESS = 0X58;
    public static final int MALL_ADDRESS_ADD = 0X59;
    public static final int ORDER_COMPLETE = 0X60;
    public static final int ALL_ORDER = 0X61;
    public static final int ORDER_DETAIL = 0X62;
    public static final int FILTER_REPLACE_LIST = 0X63;
    public static final int FILTER_REPLACE_DETAIL = 0X64;
    public static final int FILTER_REPLACE_ADD_ADD = 0X65;
    public static final int FIX_RECORD_DETAIL = 0X66;
    public static final int FIX_RECORD_ADD = 0X67;
    public static final int FEED_BACK_DETAIL = 0X68;
    public static final int FEED_BACK_ADD = 0X69;
    public static final int WARRANTY_DETAIL = 0X70;
    public static final int WARRANTY_ADD = 0X71;
    public static final int REGION_DETAIL = 0X72;
    public static final int REGION__ADD = 0X73;
    public static final int INTERVIEW_DETAIL = 0X74;
    public static final int INTERVIEW_ADD = 0X75;
    public static final int MONTH_SUMMARY_DETAIL = 0X76;
    public static final int SUMMARY_ADD = 0X77;
    public static final int CHOOSE_CHECK_PERSON = 0X78;
    public static final int PEOPLE_DETAIL = 0X79;
    public static final int PEOPLE_ADD = 0X80;
    public static final int REWORK_DETAIL = 0X81;
    public static final int REWORK_ADD = 0X82;
    public static final int TABLE = 0X83;
    public static final int ALL_TABLE = 0X84;
    public static final int TABLE_TWO = 0X85;
    public static final int TABLE_THREE = 0X86;
    public static final int TABLE_FOUR = 0X87;
    public static final int TABLE_FIVE = 0X88;
    public static final int TABLE_SIX = 0X89;
    public static final int TABLE_SEVEN = 0X90;
    public static final int TABLE_EIGHT = 0X91;
    public static final int TABLE_NINE = 0X92;
    public static final int TABLE_TEN = 0X93;
    public static final int TABLE_ELEVEN = 0X94;
    public static final int TABLE_TWELVE = 0X95;
    public static final int TABLE_THIRTEEN = 0X96;
    public static final int TABLE_FORTEEN = 0X97;
    public static final int TABLE_FIFTEEN = 0X98;
    public static final int TABLE_SIXTEEN = 0X99;
    public static final int CHOOSE_CHECK = 0X100;
    public static final int CHOOSE_DEPARTMENT_PERMISSION = 0X101;
    public static final int COMPLETE_DETAIL = 0X102;
    public static final int ENTRY_DETAIL = 0X103;
    public static final int ENTRY_ADD = 0X104;
    public static final int ChHOOSE_PEOPLE_LEAVE_LIST = 0X105;
    public static final int LEAVE_DETAIL = 0X106;
    public static final int LEAVE_ADD = 0X107;
    public static final int CLOSE_DETAIL = 0X108;
    public static final int CLOSE_ADD = 0X109;

    public static final int PRESSURE_QF_DETAIL = 0X110;
    public static final int ORDER_IMG_TWO = 0X111;

    public static final String STATISTICS_TYPE = "STATISTICS_TYPE";
    public static final int PRESSURE_RECORD_DEFAULT = 0;
    public static final int PRESSURE_RECORD_QIFEI = 1;
}
