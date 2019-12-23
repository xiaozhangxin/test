package com.akan.wms;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/8
 */

public final class Constants {


//    public static final String BASE_URL = "http://192.168.0.34:8091/";//耿清
    //public static final String BASE_URL = "http://192.168.0.105:8081/";//李志伟
    //public static final String BASE_URL = "http://192.168.0.163:8081/";//陈艳涛
    public static final String BASE_URL = "http://wms.akan.com.cn:9999/";
    //public static final String BASE_URL = "http://180.167.240.180:8091/";

    public static final String KEY_FRAGMENT = "key_fragment";

    public static final String KEY_TITLE = "key_title";

    public static final String KEY_IS_TAB_LIVE = "key_is_tab_live";


    public static final String KEY_SLUG = "key_type";

    public static final String KEY_URL = "key_url";

    public static final String KEY_COVER = "key_cover";

    public static final String USER_BEAN = "userbean";
    public static final String DETAIL_ID = "detail_id";
    public static final String BEAN = "bean";
    public static final String LIST_DATA = "list_data";
    public static final String LIST_DATA_TWO = "list_data_two";
    public static final String DETAIL_TYPE = "detail_type";
    public static final String STATE = "state";


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
    public static final int MESSAGE = 0X08;
    public static final int TRANSFER_ADD = 0X09;
    public static final int TRANSFER_LIST = 0X10;
    public static final int CHOOSE_DEPOT = 0X11;
    public static final int CHOOSE_GOODS = 0X12;
    public static final int DEPORT_IN = 0X13;
    public static final int DEPORT_OUT = 0X14;
    public static final int PURCHASE_RETURN_LIST = 0X15;
    public static final int PUSHASE_RETURN_DETAIL = 0X16;
    public static final int PUSHASE_RETURN_EDIT = 0X17;
    public static final int SEND_ADD = 0X18;
    public static final int STOCK_LIST = 0X19;
    public static final int STOCK_CHILD = 0X20;
    public static final int PERSONAL_INFO = 0X21;
    public static final int SZ = 0X22;
    public static final int CHANGE_PWD = 0X23;
    public static final int ALL_FUNCTION = 0X24;
    public static final int OUT_BUG_RETURN = 0X25;
    public static final int OUT_SALE = 0X26;
    public static final int IN_BUY = 0X27;
    public static final int IN_SALE_RETURN = 0X28;
    public static final int IN_SALE_RETURN_ADD = 0X29;
    public static final int CHOOSE_SALE_RETURN_LIST = 0X30;
    public static final int CHOOSE_SALE_RETURN_CHILD = 0X31;
    public static final int IN_SALE_RETURN_DETAIL = 0X32;
    public static final int OUT_BUY_RETURN_DETAIL = 0X33;
    public static final int OUT_BUY_RETURN_ADD = 0X34;
    public static final int CHOOSE_BUY_RETURN_LIST = 0X35;
    public static final int CHOOSE_BUY_RETURN_CHILD = 0X36;
    public static final int OUT_SALE_ADD = 0X37;
    public static final int SCAN_RESULT = 0X38;
    public static final int CHOOSE_OUT_PLAN_LIST = 0X39;
    public static final int CHOOSE_OUT_PLAN_CHILD = 0X40;
    public static final int OUT_SALE_DETAIL = 0X41;
    public static final int IN_BUY_DETAIL = 0X42;
    public static final int COMPLETE_LIST = 0X43;
    public static final int COMPLETE_ADD = 0X44;
    public static final int CHOOSE_PRODUCE_LIST = 0X45;
    public static final int CHOOSE_PRODUCE_CHILD = 0X46;
    public static final int COMPLETE_DETAIL = 0X47;
    public static final int PRODUCE_RETURN_LIST = 0X48;
    public static final int PRODUCE_RETURN_DETAIL = 0X49;
    public static final int PRODUCE_RETURN_ADD = 0X50;
    public static final int FINISH_LIST = 0X51;
    public static final int FINISH_ADD = 0X52;
    public static final int CHOOSE_GOODS_MORE = 0X53;
    public static final int CHOOSE_ORGANIZATION = 0X54;
    public static final int SUGGEST = 0X55;
    public static final int SAFTWARE = 0X56;
    public static final int ACCOUNT = 0X57;
    public static final int FORGOT_PWD = 0X58;
    public static final int PRODUCE_RECEIVE_LIST = 0X59;
    public static final int PURCHASE_RECEIVE_ADD = 0X60;
    public static final int CHOOSE_DEPORT = 0X61;
    public static final int PRODUCE_RECEIVE_DETAIL = 0X62;
    public static final int CHOOSE_SUPPLIER = 0X63;
    public static final int SCAN = 0X64;
    public static final int CHOOSE_RECEIPT_REPORT = 0X65;
    public static final int PRODUCE_CHOOSE = 0X66;
    public static final int CHOOSE_SALE_TYPE = 0X67;
    public static final int CHECK_LIST = 0X68;
    public static final int CHECK_DETAIL = 0X69;
    public static final int CHECK_ADD = 0X70;
    public static final int CHOOSE_ITEM = 0X71;
    public static final int SUPPLIER = 0X72;
    public static final int DEPORT_BASE = 0X73;
    public static final int TRANSFER_APPLY_LIST = 0X74;
    public static final int CHOOSE_TRANFER_APPLY_CHILD = 0X75;
    public static final int TRANSFER_DETAIL = 0X76;
    public static final int OUT_TRANSFER_LIST = 0X77;
    public static final int OUT_TRANSFER_ADD = 0X78;
    public static final int OUT_APPLY_TYPE = 0X79;
    public static final int OUT_TRANSFER_DETAIL = 0X80;
    public static final int IN_TRANSFER_LIST = 0X81;
    public static final int IN_TRANSFER_ADD = 0X82;
    public static final int IN_APPLY_TYPE = 0X83;
    public static final int CHOOSE_TRANSFER_OUT = 0X84;
    public static final int CHOOSE_TRANFER_OUT_CHILD = 0X85;
    public static final int IN_TRANSFER_DETAIL = 0X86;
    public static final int STOCK_FIND = 0X87;
    public static final int COMPLETE_CHOOSE = 0X88;
    public static final int CHOOSE_COMPLETE_PARAM = 0X89;
    public static final int FINISH_DETAIL = 0X90;
    public static final int FINISH_CHOOSE = 0X91;
    public static final int SEND_MIX_LIST = 0X92;
    public static final int RECEIVE_MIX_LIST = 0X93;
    public static final int SEND_MIX_DETAIL = 0X94;
    public static final int RECEIVE_MIX_DETAIL = 0X95;
    public static final int FLOW_LIST = 0X96;
    public static final int STOCK_FIND_DETAIL = 0X97;
    public static final int CUSTOMER = 0X98;
    public static final int SCAN_BEAN = 0X99;
    public static final int SCAN_BEAN_RESULT = 0X100;
    public static final int SCAN_IN_BUY = 0X101;
    public static final int SCAN_IN_BUY_RESULT = 0X102;
    public static final int CHOOSE_MFC = 0X103;
    public static final int OUT_APPLY_TYPE_ALL = 0X104;
    public static final int INVENTORY_WARNING = 0X105;
    public static final int CHOOSE_OPERATOR = 0X106;
    public static final int OUT_SALE_ADD_NEW = 0X107;
    public static final int OUT_TRANSFER_ADD_NEW = 0X108;
    public static final int COMPLETE_ADD_NEW = 0X109;
    public static final int HOME_SCAN = 0X110;
}
