package com.akan.wms.http;

import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.ClassTeamBean;
import com.akan.wms.bean.CompleteDecBean;
import com.akan.wms.bean.CustomBean;
import com.akan.wms.bean.DefineValueBean;
import com.akan.wms.bean.DeliverGoodsBean;
import com.akan.wms.bean.InventoryBean;
import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.bean.MessageBean;
import com.akan.wms.bean.MfcBean;
import com.akan.wms.bean.MiscRcvBean;
import com.akan.wms.bean.MiscShipBean;
import com.akan.wms.bean.MiscShipDocTypeBean;
import com.akan.wms.bean.ModNoBean;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.OperatorStaffBean;
import com.akan.wms.bean.OutSaleRtuBean;
import com.akan.wms.bean.OutTypeLBean;
import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.RcvBean;
import com.akan.wms.bean.RtnedGoodsBean;
import com.akan.wms.bean.SaleRcvBean;
import com.akan.wms.bean.SaleReturnBean;
import com.akan.wms.bean.SaleShipBean;
import com.akan.wms.bean.SaleShipTypeBean;
import com.akan.wms.bean.ShipBean;
import com.akan.wms.bean.ShipPlanBean;
import com.akan.wms.bean.StaffGroupBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.StoragingProBean;
import com.akan.wms.bean.SupplierBean;
import com.akan.wms.bean.TransferBean;
import com.akan.wms.bean.TransferInBean;
import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.bean.WarnBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.bean.WarnTypeBean;
import com.akan.wms.bean.WhBean;
import com.akan.wms.bean.WhTransferApplyBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


/**
 * @author zz
 * @since 2017/2/13
 */

/**
 * Created by zz on 2017/1/11.
 *
 * @GET 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 */
public interface APIService {


    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("api/staffController/staffLogin")
    Observable<HttpResult<UserBean>> getLogin(@FieldMap Map<String, String> parmer);

    /**
     * 验证码
     */
    @FormUrlEncoded
    @POST("api/staffController/sendCodeByAccount")
    Observable<HttpResult<String>> sendCodeByAccount(@FieldMap Map<String, String> parmer);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("api/staffController/updatePassWordByPhone")
    Observable<HttpResult<String>> getwangjiminma(@FieldMap Map<String, String> parmer);

    /**
     * 查询员工关联的组织
     */
    @FormUrlEncoded
    @POST("api/staffController/queryStaffOrgs")
    Observable<HttpResult<List<StaffOrgsBean>>> queryStaffOrgs(@FieldMap Map<String, String> parmer);

    /**
     * 查询所有组织
     */
    @FormUrlEncoded
    @POST("api/staffController/queryAllOrg")
    Observable<HttpResult<List<StaffOrgsBean>>> queryAllOrg(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查找存储地点
     */
    @FormUrlEncoded
    @POST("api/miscShipController/getWareHouseList")
    Observable<HttpResult<List<WareHouseBean>>> getWareHouseList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);/**
     * 查找存储地点
     */
    @FormUrlEncoded
    @POST("api/miscShipController/getAllWareHouseList")
    Observable<HttpResult<List<WareHouseBean>>> getAllWareHouseList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 上传多张图片/文件
     *
     * @return
     */
    @POST("api/othersController/uploadFiles")
    Observable<HttpResult<String[]>> uploadFiles(@Header("Authorization") String token, @Body RequestBody Body);

    /**
     * 上传单张图片/文件
     *
     * @return
     */
    @POST("api/othersController/uploadFile")
    Observable<HttpResult<String>> uploadFile(@Header("Authorization") String token, @Body RequestBody Body);

    /**
     * 修改员工信息
     */
    @FormUrlEncoded
    @POST("api/staffController/updateStaff")
    Observable<HttpResult<String>> updateStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("api/staffController/updateStaffPassword")
    Observable<HttpResult<String>> updateStaffPassword(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("api/systemController/addSuggest")
    Observable<HttpResult<String>> insertSuggestList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 条码查询
     */
    @FormUrlEncoded
    @POST("api/barController/queryBar")
    Observable<HttpResult<BarBean>> queryBar(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 版本
     */
    @FormUrlEncoded
    @POST("api/othersController/getAppVersionDetail")
    Observable<HttpResult<AppVersionBean>> getAppVersionDetail(@FieldMap Map<String, String> parmer);


    /**
     * 料品列表
     */
    @FormUrlEncoded
    @POST("api/itemInfoController/getItemInfoList")
    Observable<HttpResult<List<ItemInfoBean>>> getItemInfoList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 盘点料品列表
     */
    @FormUrlEncoded
    @POST("api/whQohController/getItemWhQohByWhList")
    Observable<HttpResult<List<ItemWhQohBean>>> getItemWhQohByWhList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 随机查找仓库料品数据
     */
    @FormUrlEncoded
    @POST("api/whQohController/getItemWhQohByRandomList")
    Observable<HttpResult<List<ItemWhQohBean>>> getItemWhQohByRandomList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 查找生产领料单据类型
     */
    @FormUrlEncoded
    @POST("api/miscDocTypeController/getMiscShipDocTypeList")
    Observable<HttpResult<List<MiscShipDocTypeBean>>> getMiscShipDocTypeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查找生产退料单据类型
     */
    @FormUrlEncoded
    @POST("api/miscRcvController/getMiscRcvDocTypeList")
    Observable<HttpResult<List<MiscShipDocTypeBean>>> getMiscRcvDocTypeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 库管员
     */
    @FormUrlEncoded
    @POST("api/operatorController/getOperatorList")
    Observable<HttpResult<List<OperatorBean>>> getOperatorList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 负责部门/受益部门
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffGroupByOrgId")
    Observable<HttpResult<List<StaffGroupBean>>> getStaffGroupByOrgId(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 负责中心使用中心
     */
    @FormUrlEncoded
    @POST("api/defineValueController/getDefineValueList")
    Observable<HttpResult<List<DefineValueBean>>> getDefineValueList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 生产领料列表
     */
    @FormUrlEncoded
    @POST("api/miscShipController/getMiscShipList")
    Observable<HttpResult<List<MiscShipBean>>> getMiscShipList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 领料详情
     */
    @FormUrlEncoded
    @POST("api/miscShipController/getMiscShipDetail")
    Observable<HttpResult<MiscShipBean>> getMiscShipDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加生产领料
     */
    @FormUrlEncoded
    @POST("api/miscShipController/insertMiscShip")
    Observable<HttpResult<String>> insertMiscShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加出库单
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/addSaleShip")
    Observable<HttpResult<String>> addSaleShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 出库单列表
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleShipPage")
    Observable<HttpResult<List<SaleShipBean>>> querySaleShipPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出货计划列表
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/queryUnCompleteShipPlan")
    Observable<HttpResult<List<ShipPlanBean>>> queryShipPlanPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出货单单据类型列表
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleShipTypeList")
    Observable<HttpResult<List<SaleShipTypeBean>>> querySaleShipTypeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出货计划详情
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/queryPlanById")
    Observable<HttpResult<ShipPlanBean>> queryPlanById(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出库单详情
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleShipById")
    Observable<HttpResult<SaleShipBean>> querySaleShipByCode(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 领料流程变更
     */
    @FormUrlEncoded
    @POST("api/miscShipController/updateMiscShipCode")
    Observable<HttpResult<String>> updateMiscShipCode(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
   /**
     * 领料删除
     */
    @FormUrlEncoded
    @POST("api/miscShipController/deleteMiscShip")
    Observable<HttpResult<String>> deleteMiscShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 作废出库单
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/invalidSaleShip")
    Observable<HttpResult<String>> invalidSaleShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出库
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/outOfShip")
    Observable<HttpResult<String>> outOfShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 装车
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/sendSaleShip")
    Observable<HttpResult<String>> sendSaleShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

   /**
     * 删除出库单
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/deleteSaleShip")
    Observable<HttpResult<String>> deleteSaleShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 生产退料列表
     */
    @FormUrlEncoded
    @POST("api/miscRcvController/getMiscRcvList")
    Observable<HttpResult<List<MiscRcvBean>>> getMiscRcvList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查找生产退料详情
     */
    @FormUrlEncoded
    @POST("api/miscRcvController/getMiscRcvDetail")
    Observable<HttpResult<MiscRcvBean>> getMiscRcvDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加生产退料
     */
    @FormUrlEncoded
    @POST("api/miscRcvController/insertMiscRcv")
    Observable<HttpResult<String>> insertMiscRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 退料流程变更
     */
    @FormUrlEncoded
    @POST("api/miscRcvController/updateMiscRcvCode")
    Observable<HttpResult<String>> updateMiscRcvCode(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

   /**
     * 退料删除
     */
    @FormUrlEncoded
    @POST("api/miscRcvController/deleteMiscRcv")
    Observable<HttpResult<String>> deleteMiscRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 采购单列表
     */
    @GET("api/deliverGoodsController/queryPurchase")
    Observable<HttpResult<List<PurchaseBean>>> queryPurchaseLists(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 添加送货单
     */
    @FormUrlEncoded
    @POST("api/deliverGoodsController/addDeliverGoods")
    Observable<HttpResult<String>> addDeliverGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 点收
     */

    @FormUrlEncoded
    @POST("api/deliverGoodsController/addReceiveGoods")
    Observable<HttpResult<String>> addReceiveGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 质检
     */
    @FormUrlEncoded
    @POST("api/deliverGoodsController/qualityReceiveGoods")
    Observable<HttpResult<String>> qualityReceiveGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 同意入库
     */
    @FormUrlEncoded
    @POST("api/deliverGoodsController/pastWh")
    Observable<HttpResult<String>> pastWh(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 拒绝入库
     */
    @FormUrlEncoded
    @POST("api/deliverGoodsController/rejectWh")
    Observable<HttpResult<String>> rejectWh(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 供应商
     */
    @GET("api/baseInfoController/querySupplierLists")
    Observable<HttpResult<List<SupplierBean>>> querySupplier(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 客户分页查询
     */
    @GET("api/baseInfoController/queryCustomLists")
    Observable<HttpResult<List<CustomBean>>> queryCustomLists(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 收货仓库
     */
    @GET("api/deliverGoodsController/queryWh")
    Observable<HttpResult<List<WhBean>>> queryWh(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 选择厂牌
     */
    @GET("api/deliverGoodsController/queryMfc")
    Observable<HttpResult<List<MfcBean>>> queryMfc(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 送货单列表
     */
    @GET("api/deliverGoodsController/queryDeliverGoodsLists")
    Observable<HttpResult<List<DeliverGoodsBean>>> queryDeliverGoodsLists(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 查询送货单详情
     */
    @GET("api/deliverGoodsController/querySupplierDeliverById")
    Observable<HttpResult<DeliverGoodsBean>> querySupplierDeliverById(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 退货申请单列表
     */
    @GET("api/rtnedGoodsController/queryApplyedRtnList")
    Observable<HttpResult<List<OutSaleRtuBean>>> queryApplyedRtnList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 退货单列表
     */
    @GET("api/rtnedGoodsController/queryRtnedGoodsList")
    Observable<HttpResult<List<RtnedGoodsBean>>> queryRtnedGoodsList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 退货单详情
     */
    @GET("api/rtnedGoodsController/queryRtnedGoodsById")
    Observable<HttpResult<RtnedGoodsBean>> queryRtnedGoodsById(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 退货单详情
     */
    @GET("api/rtnedGoodsController/queryApplyedRtnById")
    Observable<HttpResult<OutSaleRtuBean>> queryApplyedRtnById(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 退货单添加
     */
    @FormUrlEncoded
    @POST("api/rtnedGoodsController/addRtnedGoods")
    Observable<HttpResult<String>> addRtnedGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 退货单作废
     */
    @FormUrlEncoded
    @POST("api/rtnedGoodsController/invalidRtnedGoods")
    Observable<HttpResult<String>> invalidRtnedGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
   /**
     * 退货单删除
     */
    @FormUrlEncoded
    @POST("api/rtnedGoodsController/delRtnedGoods")
    Observable<HttpResult<String>> delRtnedGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 同意入库
     */
    @FormUrlEncoded
    @POST("api/rtnedGoodsController/pastRtnedGoodsById")
    Observable<HttpResult<String>> pastRtnedGoods(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 退货入库单列表
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleRcvPage")
    Observable<HttpResult<List<SaleRcvBean>>> querySaleRcvPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 退货入库单详情
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleRcv")
    Observable<HttpResult<SaleRcvBean>> querySaleRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 新建销售退货入库单
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/addSaleRcv")
    Observable<HttpResult<String>> addSaleRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 销售退货单入库
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/whSaleRcv")
    Observable<HttpResult<String>> whSaleRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 作废退货入库单
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/invalidSaleRcv")
    Observable<HttpResult<String>> invalidSaleRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 退回处理列表
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleReturnPage")
    Observable<HttpResult<List<SaleReturnBean>>> querySaleReturnPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出货计划详情
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/querySaleReturnDetail")
    Observable<HttpResult<SaleReturnBean>> querySaleReturnDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 查找盘点列表
     */
    @FormUrlEncoded
    @POST("api/inventoryController/getInventoryList")
    Observable<HttpResult<List<InventoryBean>>> getInventoryList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 盘点单详情
     */
    @FormUrlEncoded
    @POST("api/inventoryController/getInventoryDetail")
    Observable<HttpResult<InventoryBean>> getInventoryDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 盘点状态变更
     */
    @FormUrlEncoded
    @POST("api/inventoryController/updateInventoryStatus")
    Observable<HttpResult<String>> updateInventoryStatus(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
   /**
     * 盘点删除
     */
    @FormUrlEncoded
    @POST("api/inventoryController/deleteInventory")
    Observable<HttpResult<String>> deleteInventory(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加盘点记录
     */
    @FormUrlEncoded
    @POST("api/inventoryController/insertInventory")
    Observable<HttpResult<String>> insertInventory(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 仓库调拨审核
     */
    @FormUrlEncoded
    @POST("api/transferController/auditOutAndIn")
    Observable<HttpResult<String>> auditOutAndIn(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 组织内调拨列表
     */
    @FormUrlEncoded
    @POST("api/transferController/queryTransferOutInPage")
    Observable<HttpResult<List<TransferBean>>> queryTransferOutInPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 消息列表
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffMessageList")
    Observable<HttpResult<List<MessageBean>>> getStaffMessageList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 组织内调拨详情
     */
    @FormUrlEncoded
    @POST("api/transferController/queryTransferOutInById")
    Observable<HttpResult<TransferBean>> queryTransferOutInById(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 组织内调拨添加
     */
    @FormUrlEncoded
    @POST("api/transferController/addOutAndIn")
    Observable<HttpResult<String>> addOutAndIn(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 调出单列表
     */
    @FormUrlEncoded
    @POST("api/transferController/queryTransferOutPage")
    Observable<HttpResult<List<TransferOutBean>>> queryTransferOutPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单列表
     */
    @FormUrlEncoded
    @POST("api/transferController/queryUnCompleteOutPage")
    Observable<HttpResult<List<TransferOutBean>>> queryUnCompleteOutPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询未完成调拨申请单列表
     */
    @FormUrlEncoded
    @POST("api/transferController/queryUnClosedApplyPage")
    Observable<HttpResult<List<TransferUnCompleteBean>>> queryUnClosedApplyPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单详情
     */
    @FormUrlEncoded
    @POST("api/transferController/queryTransferById")
    Observable<HttpResult<TransferOutBean>> queryTransferById(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单单据类型
     */
    @FormUrlEncoded
    @POST("api/transferController/queryOutTypeList")
    Observable<HttpResult<List<OutTypeLBean>>> queryOutTypeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单单据类型
     */
    @FormUrlEncoded
    @POST("api/transferController/queryInTypeList")
    Observable<HttpResult<List<OutTypeLBean>>> queryInTypeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 新建调出单
     */
    @FormUrlEncoded
    @POST("api/transferController/addTransferOut")
    Observable<HttpResult<String>> addTransferOut(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单出库
     */
    @FormUrlEncoded
    @POST("api/transferController/transferOutWh")
    Observable<HttpResult<String>> transferOutWh(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调拨申请列表(调拨出库添加时选择)
     */
    @FormUrlEncoded
    @POST("api/transferController/queryApplyPage")
    Observable<HttpResult<List<WhTransferApplyBean>>> queryWhTransferApplyPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 组织内调拨申请详情
     */
    @FormUrlEncoded
    @POST("api/transferController/queryApplyDocNo")
    Observable<HttpResult<WhTransferApplyBean>> queryApplyDocNo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 组织内调拨申请详情
     */
    @FormUrlEncoded
    @POST("api/transferController/queryApplyById")
    Observable<HttpResult<TransferUnCompleteBean>> queryUnCloseDocNo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 调入单列表
     */
    @FormUrlEncoded
    @POST("api/transferController/queryTransferInPage")
    Observable<HttpResult<List<TransferInBean>>> queryTransferInPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调入单详情
     */
    @FormUrlEncoded
    @POST("api/transferController/queryTransferInById")
    Observable<HttpResult<TransferInBean>> queryTransferInById(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 新建调入单
     */
    @FormUrlEncoded
    @POST("api/transferController/addTransferIn")
    Observable<HttpResult<String>> addTransferIn(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单出库
     */
    @FormUrlEncoded
    @POST("api/transferController/transferInWh")
    Observable<HttpResult<String>> transferInWh(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 完工申报单分页查询
     */
    @GET("api/completeDecController/queryCompleteDecList")
    Observable<HttpResult<List<CompleteDecBean>>> queryCompleteDecList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 查询完工申报单数据
     */
    @GET("api/completeDecController/queryCompleteDec")
    Observable<HttpResult<CompleteDecBean>> queryCompleteDec(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 添加完工申报单
     */
    @FormUrlEncoded
    @POST("api/completeDecController/addCompleteDec")
    Observable<HttpResult<String>> addCompleteDec(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 同意完工申报单
     */
    @FormUrlEncoded
    @POST("api/completeDecController/pastCompleteDec")
    Observable<HttpResult<String>> pastCompleteDec(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 作废完工申报单
     */
    @FormUrlEncoded
    @POST("api/completeDecController/invalidCompleteDec")
    Observable<HttpResult<String>> invalidCompleteDec(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除完工申报单
     */
    @FormUrlEncoded
    @POST("api/completeDecController/delCompleteDec")
    Observable<HttpResult<String>> delCompleteDec(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 查询操作工
     */
    @GET("api/completeDecController/queryOperatorStaffList")
    Observable<HttpResult<List<OperatorStaffBean>>> queryOperatorStaffList (@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 查询班组
     */
    @GET("api/completeDecController/queryClassTeam")
    Observable<HttpResult<List<ClassTeamBean>>> queryClassTeam(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 查询模具编号
     */
    @GET("api/completeDecController/queryModNo")
    Observable<HttpResult<List<ModNoBean>>> queryModNo(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 成品入库单列表
     */
    @GET("api/storagingProController/queryStoragingProList")
    Observable<HttpResult<List<StoragingProBean>>> queryStoragingProList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 完工生产订单
     */
    @GET("api/completeDecController/queryProductionOrderList")
    Observable<HttpResult<List<ProductionOrderBean>>> queryProductionOrderList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 成品生产订单列表
     */
    @GET("api/storagingProController/queryProductionOrderList")
    Observable<HttpResult<List<ProductionOrderBean>>> queryProductionOrderTwoList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 成品入库单详情
     */
    @GET("api/storagingProController/queryStoragingPro")
    Observable<HttpResult<StoragingProBean>> queryStoragingPro(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 添加成品入库单
     */
    @FormUrlEncoded
    @POST("api/storagingProController/addStoragingPro")
    Observable<HttpResult<String>> addStoragingPro(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 同意成品入库单
     */
    @FormUrlEncoded
    @POST("api/storagingProController/pastStoragingPro")
    Observable<HttpResult<String>> pastStoragingPro(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 作废成品入库单
     */
    @FormUrlEncoded
    @POST("api/storagingProController/invalidStoragingPro")
    Observable<HttpResult<String>> invalidStoragingPro(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
   /**
     * 删除成品入库单
     */
    @FormUrlEncoded
    @POST("api/storagingProController/delStoragingPro")
    Observable<HttpResult<String>> delStoragingPro(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 杂发单列表
     */
    @FormUrlEncoded
    @POST("api/miscController/queryShipPage")
    Observable<HttpResult<List<ShipBean>>> queryShipPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * id查询杂发单单据详情
     */
    @FormUrlEncoded
    @POST("api/miscController/queryShipById")
    Observable<HttpResult<ShipBean>> queryShipById(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核杂发单
     */
    @FormUrlEncoded
    @POST("api/miscController/auditShip")
    Observable<HttpResult<String>> auditShip(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 杂收单列表
     */
    @FormUrlEncoded
    @POST("api/miscController/queryRcvPage")
    Observable<HttpResult<List<RcvBean>>> queryRcvPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * id查询杂收单单据详情
     */
    @FormUrlEncoded
    @POST("api/miscController/queryRcvById")
    Observable<HttpResult<RcvBean>> queryRcvById(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核杂收单
     */
    @FormUrlEncoded
    @POST("api/miscController/auditRcv")
    Observable<HttpResult<String>> auditRcv(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 库存流水单分页查询
     */
    @GET("api/inventoryStatementController/queryWarningList")
    Observable<HttpResult<List<WarnBean>>> queryWarningList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 库存类型
     */
    @GET("api/inventoryStatementController/queryInventoryStatementTypeList")
    Observable<HttpResult<List<WarnTypeBean>>> queryInventoryStatementTypeList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 库存预警分页查询
     */
    @GET("api/warningController/queryWarningList")
    Observable<HttpResult<List<WarnTwoBean>>> queryWarningTwoList(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);

    /**
     * 首页预警查询
     */
    @GET("api/warningController/queryBoardWarnings")
    Observable<HttpResult<List<WarnTwoBean>>> queryBoardWarnings(@Header("Authorization") String token, @QueryMap Map<String, String> parmer);


    /**
     * 料品数据同步
     */
    @FormUrlEncoded
    @POST("api/itemInfoController/syncItemInfoList")
    Observable<HttpResult<String>> syncItemInfoList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 调出单同步
     */
    @FormUrlEncoded
    @POST("api/transferController/sync")
    Observable<HttpResult<String>> sync(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 出货计划单同步
     */
    @FormUrlEncoded
    @POST("api/shipPlanController/sync")
    Observable<HttpResult<String>> syncPlan(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 杂收杂发同步
     */
    @FormUrlEncoded
    @POST("api/miscController/sync")
    Observable<HttpResult<String>> syncMix(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 完工申报订单同步
     */
    @FormUrlEncoded
    @POST("api/taskController/importProductionData")
    Observable<HttpResult<String>> syncProduction(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 采购入库(采购单同步)
     */
    @FormUrlEncoded
    @POST("api/taskController/importPurchaseData")
    Observable<HttpResult<String>> importPurchaseData(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 采购退货(退货申请同步)
     */
    @FormUrlEncoded
    @POST("api/taskController/importRtnData")
    Observable<HttpResult<String>> importRtnData(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
}
