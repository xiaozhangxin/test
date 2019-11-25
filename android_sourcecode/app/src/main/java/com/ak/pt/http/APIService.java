package com.ak.pt.http;

/**
 * @author zz
 * @since 2017/2/13
 */

import com.ak.pt.bean.AddressBean;
import com.ak.pt.bean.AppHomeMenuTreeBean;
import com.ak.pt.bean.AppVersionBean;
import com.ak.pt.bean.AreaPressureBean;
import com.ak.pt.bean.AreaStudyBean;
import com.ak.pt.bean.BannerBean;
import com.ak.pt.bean.BigAreaBean;
import com.ak.pt.bean.BookBean;
import com.ak.pt.bean.DailyBean;
import com.ak.pt.bean.DocumentBean;
import com.ak.pt.bean.FakeBean;
import com.ak.pt.bean.FeedBackBean;
import com.ak.pt.bean.FileBean;
import com.ak.pt.bean.FilterReplaceBean;
import com.ak.pt.bean.FixRecordBean;
import com.ak.pt.bean.GoodsBean;
import com.ak.pt.bean.InstallFormBean;
import com.ak.pt.bean.IntegralBean;
import com.ak.pt.bean.InterviewBean;
import com.ak.pt.bean.MeParentBean;
import com.ak.pt.mvp.fragment.statistics.MemoryBean;
import com.ak.pt.bean.MonthTotalBean;
import com.ak.pt.bean.NoticeBean;
import com.ak.pt.bean.OrderBean;
import com.ak.pt.bean.PeopleBean;
import com.ak.pt.bean.PressureBackBean;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.QuitJobBean;
import com.ak.pt.bean.ShopBannerBean;
import com.ak.pt.bean.ShopCloseBean;
import com.ak.pt.bean.SignBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.bean.StaffInfoLeaveBean;
import com.ak.pt.bean.StaffMessageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WarrantyBean;
import com.ak.pt.bean.WorkerBean;
import com.ak.pt.bean.staffGroupBeans;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

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
     * 上传单张图片/文件
     *
     * @return
     */
    @POST("api/othersController/uploadFile")
    Observable<HttpResult<String>> uploadFile(@Header("Authorization") String token, @Body RequestBody Body);

    /**
     * 上传多张图片/文件
     *
     * @return
     */
    @POST("api/othersController/uploadFiles")
    Observable<HttpResult<String[]>> uploadFiles(@Header("Authorization") String token, @Body RequestBody Body);

    /**
     * 区域试压
     */
    @FormUrlEncoded
    @POST("/api/pressureController/getAreaTestPressure")
    Observable<HttpResult<List<AreaPressureBean>>> getAreaTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 大区试压
     */
    @FormUrlEncoded
    @POST("/api/pressureController/getAreaTestPressure")
    Observable<HttpResult<List<BigAreaBean>>> queryBigAreaCountPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改员工信息
     */
    @FormUrlEncoded
    @POST("api/staffController/updateStaff")
    Observable<HttpResult<String>> updateStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer); /**
     * 修改员工信息(无权限)
     */
    @FormUrlEncoded
    @POST("api/staffController/updateAppStaff")
    Observable<HttpResult<String>> updateAppStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("api/staffController/updateStaffPassword")
    Observable<HttpResult<String>> updateStaffPassword(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 版本
     */
    @FormUrlEncoded
    @POST("api/othersController/getAppVersionDetail")
    Observable<HttpResult<AppVersionBean>> getAppVersionDetail( @FieldMap Map<String, String> parmer);

    /**
     * 消息
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffMessageList")
    Observable<HttpResult<List<StaffMessageBean>>> getStaffMessageList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 消息详情
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffMessageDetail")
    Observable<HttpResult<StaffMessageBean>> getStaffMessageDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * app首页菜单树
     */
    @FormUrlEncoded
    @POST("api/appController/getAppHomeMenuTree")
    Observable<HttpResult<List<AppHomeMenuTreeBean>>> getAppHomeMenuTreeByStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 未读消息数量
     */
    @FormUrlEncoded
    @POST("api/staffController/getNotReadMessageCount")
    Observable<HttpResult<String>> getNotReadMessageCount(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除消息
     */
    @FormUrlEncoded
    @POST("api/staffController/deleteStaffMessages")
    Observable<HttpResult<String>> deleteStaffMessages(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 公告列表
     */
    @FormUrlEncoded
    @POST("api/appController/getNoticeList")
    Observable<HttpResult<List<NoticeBean>>> getNoticeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 员工组织架构树
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffGroupTreeByStaff")
    Observable<HttpResult<List<staffGroupBeans>>> getStaffGroupTreeByStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 通讯簿列表
     */
    @FormUrlEncoded
    @POST("api/appController/getAddressBookList")
    Observable<HttpResult<List<BookBean>>> getAddressBookList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 公告详情
     */
    @FormUrlEncoded
    @POST("api/appController/getNoticeDetail")
    Observable<HttpResult<NoticeBean>> getNoticeDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 未读公告数量
     */
    @FormUrlEncoded
    @POST("api/appController/getNotReadNoticeCount")
    Observable<HttpResult<String>> getNotReadNoticeCount(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 签到记录
     */
    @FormUrlEncoded
    @POST("api/crmController/getSignList")
    Observable<HttpResult<List<SignBean>>> getSignList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 签到记录
     */
    @FormUrlEncoded
    @POST("api/crmController/getDaySignList")
    Observable<HttpResult<List<SignBean>>> getDaySignList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 员工签到
     */
    @FormUrlEncoded
    @POST("api/crmController/insertSign")
    Observable<HttpResult<String>> insertSign(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 获取试压单列表
     */
    @FormUrlEncoded
    @POST("api/pressureController/getTestPressureList")
    Observable<HttpResult<List<PressurePageBean>>> getTestPressureList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 试压工试压单
     */
    @FormUrlEncoded
    @POST("api/pressureController/getAppTestPressureList")
    Observable<HttpResult<List<PressurePageBean>>> getAppTestPressureList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 试压详情
     */
    @FormUrlEncoded
    @POST("api/pressureController/getTestPressureDaily")
    Observable<HttpResult<PressurePageBean>> queryDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加或修改试压单
     */
    @FormUrlEncoded
    @POST("api/pressureController/insertOrUpdateTestPressure")
    Observable<HttpResult<String>> insertOrUpdateTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);    /**
     * 添加或修改试压单(无权限)
     */
    @FormUrlEncoded
    @POST("api/pressureController/insertOrUpdateAppTestPressure")
    Observable<HttpResult<String>> insertOrUpdateAppTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 试压单流程状态
     */
    @FormUrlEncoded
    @POST("api/pressureController/auditTestPressure")
    Observable<HttpResult<String>> auditTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 试压工试压单流程状态
     */
    @FormUrlEncoded
    @POST("api/pressureController/auditAppTestPressure")
    Observable<HttpResult<String>> auditAppTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 给水工发送消息
     */
    @FormUrlEncoded
    @POST("api/pressureController/sendWaterMessage")
    Observable<HttpResult<String>> sendWaterMessage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查找试压水工信息
     */
    @FormUrlEncoded
    @POST("api/staffController/getWaterList")
    Observable<HttpResult<List<WorkerBean>>> getWaterList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 轮播图
     */
    @FormUrlEncoded
    @POST("api/appController/getBannerList")
    Observable<HttpResult<List<BannerBean>>> getBannerList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 新增图片
     */
    @FormUrlEncoded
    @POST("api/pressureController/insertPiptbFixPhoto")
    Observable<HttpResult<String>> insertPiptbFixPhoto(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加日报
     */
    @FormUrlEncoded
    @POST("api/crmController/insertDaily")
    Observable<HttpResult<String>> insertDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改日报
     */
    @FormUrlEncoded
    @POST("api/crmController/updateDaily")
    Observable<HttpResult<String>> updateDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除日报
     */
    @FormUrlEncoded
    @POST("api/crmController/deleteDaily")
    Observable<HttpResult<String>> deleteDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 日报详情
     */
    @FormUrlEncoded
    @POST("api/crmController/getDailyDetail")
    Observable<HttpResult<DailyBean>> getDailyDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核日报
     */
    @FormUrlEncoded
    @POST("api/crmController/auditDaily")
    Observable<HttpResult<String>> auditDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加防伪历史
     */
    @FormUrlEncoded
    @POST("api/pressureController/insertAntFake")
    Observable<HttpResult<String>> insertAntiFake(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 日报列表
     */
    @FormUrlEncoded
    @POST("api/crmController/getDailyList")
    Observable<HttpResult<List<DailyBean>>> getDailyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 发送验证码
     */
    @FormUrlEncoded
    @POST("api/staffController/insertCode")
    Observable<HttpResult<String>> insertCode(@FieldMap Map<String, String> parmer);

    /**
     * 更换密码
     */
    @FormUrlEncoded
    @POST("api/staffController/updatePassWordByPhone")
    Observable<HttpResult<String>> getwangjiminma(@FieldMap Map<String, String> parmer);

    /**
     * 积分列表
     */
    @FormUrlEncoded
    @POST("api/pressureController/getIntegralDailyList")
    Observable<HttpResult<List<IntegralBean>>> getIntegralDailyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 积分列表
     */
    @FormUrlEncoded
    @POST("api/pressureController/getAntFakeList")
    Observable<HttpResult<List<FakeBean>>> getAntiFakeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询公文列表
     */
    @FormUrlEncoded
    @POST("api/pressureController/getDocumentList")
    Observable<HttpResult<List<DocumentBean>>> getDocumentList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询文档列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getFilePaperList")
    Observable<HttpResult<List<FileBean>>> getFilePaperList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查找试压模块列表
     */
    @FormUrlEncoded
    @POST("api/pressureController/getPressureDropList")
    Observable<HttpResult<List<PressureDropBean>>> getPressureDropList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询文档列表
     */
    @FormUrlEncoded
    @POST("api/commonController/verificationPass")
    Observable<HttpResult<String>> verificationPass(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除试压单图片
     */
    @FormUrlEncoded
    @POST("api/pressureController/deletePiptbFixPhoto")
    Observable<HttpResult<String>> deletePiptbFixPhoto(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除试压单
     */
    @FormUrlEncoded
    @POST("api/pressureController/deleteTestPressure")
    Observable<HttpResult<String>> deleteTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取部门职位
     */
    @FormUrlEncoded
    @POST("api/staffController/queryJobNames")
    Observable<HttpResult<List<String>>> queryJobNames(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 商品列表
     */
    @FormUrlEncoded
    @POST("api/goodsController/querySaleGoodsList")
    Observable<HttpResult<List<GoodsBean>>> querySaleGoodsList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 商品详情
     */
    @FormUrlEncoded
    @POST("api/goodsController/getGoodsDetail")
    Observable<HttpResult<GoodsBean>> getGoodsDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加修改地址
     */
    @FormUrlEncoded
    @POST("api/addressController/insertUpdateAddress")
    Observable<HttpResult<String>> insertUpdateAddress(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除地址
     */
    @FormUrlEncoded
    @POST("api/addressController/deleteAddress")
    Observable<HttpResult<String>> deleteAddress(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 下单
     */
    @FormUrlEncoded
    @POST("api/orderController/insertOrder")
    Observable<HttpResult<String>> insertOrder(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 水工积分
     */
    @FormUrlEncoded
    @POST("api/pressureController/getWaterIntegral")
    Observable<HttpResult<String>> getWaterIntegral(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 设置默认地址
     */
    @FormUrlEncoded
    @POST("api/addressController/setDefaultAddress")
    Observable<HttpResult<String>> setDefaultAddress(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 设置默认地址
     */
    @FormUrlEncoded
    @POST("api/addressController/getDefaultAddress")
    Observable<HttpResult<AddressBean>> getDefaultAddress(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 商品列表
     */
    @FormUrlEncoded
    @POST("api/addressController/getMemberAddressList")
    Observable<HttpResult<List<AddressBean>>> getMemberAddressList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 订单列表
     */
    @FormUrlEncoded
    @POST("api/orderController/queryMyOrderList")
    Observable<HttpResult<List<OrderBean>>> queryMyOrderList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 商城轮播列表
     */
    @FormUrlEncoded
    @POST("api/appController/getShopBannerList")
    Observable<HttpResult<List<ShopBannerBean>>> getShopBannerList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 订单详情
     */
    @FormUrlEncoded
    @POST("api/orderController/getOrderDetail")
    Observable<HttpResult<OrderBean>> getOrderDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 确认收货
     */
    @FormUrlEncoded
    @POST("api/orderController/confirmOrder")
    Observable<HttpResult<String>> confirmOrder(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加滤芯更换表单
     */
    @FormUrlEncoded
    @POST("api/filterElementController/insertFilterElement")
    Observable<HttpResult<String>> insertFilterElement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除滤芯替换记录
     */
    @FormUrlEncoded
    @POST("api/filterElementController/deleteFilterElement")
    Observable<HttpResult<String>> deleteFilterElement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除滤芯替换附件
     */
    @FormUrlEncoded
    @POST("api/filterElementController/deleteFilterFile")
    Observable<HttpResult<String>> deleteFilterFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改滤芯更换表单
     */
    @FormUrlEncoded
    @POST("api/filterElementController/updateFilterElement")
    Observable<HttpResult<String>> updateFilterElement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取滤芯更换列表
     */
    @FormUrlEncoded
    @POST("api/filterElementController/getFilterElementList")
    Observable<HttpResult<List<FilterReplaceBean>>> getFilterElementList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取滤芯更换详情
     */
    @FormUrlEncoded
    @POST("api/filterElementController/getFilterElementDetail")
    Observable<HttpResult<FilterReplaceBean>> getFilterElementDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加维修记录
     */
    @FormUrlEncoded
    @POST("api/repairRecordController/insertRepairRecord")
    Observable<HttpResult<String>> insertRepairRecord(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除维修记录记录
     */
    @FormUrlEncoded
    @POST("api/repairRecordController/deleteRepairRecord")
    Observable<HttpResult<String>> deleteRepairRecord(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除维修记录附件
     */
    @FormUrlEncoded
    @POST("api/repairRecordController/deleteRepairFile")
    Observable<HttpResult<String>> deleteRepairFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改维修记录表单
     */
    @FormUrlEncoded
    @POST("api/repairRecordController/updateRepairRecord")
    Observable<HttpResult<String>> updateRepairRecord(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取维修记录列表
     */
    @FormUrlEncoded
    @POST("api/repairRecordController/getRepairRecordList")
    Observable<HttpResult<List<FixRecordBean>>> getRepairRecordList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取维修记录详情
     */
    @FormUrlEncoded
    @POST("api/repairRecordController/getRepairRecordDetail")
    Observable<HttpResult<FixRecordBean>> getRepairRecordDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加质量反馈
     */
    @FormUrlEncoded
    @POST("api/qualityFeedbackController/insertQualityFeedback")
    Observable<HttpResult<String>> insertQualityFeedback(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除质量反馈记录
     */
    @FormUrlEncoded
    @POST("api/qualityFeedbackController/deleteQualityFeedback")
    Observable<HttpResult<String>> deleteQualityFeedback(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 删除质量反馈附件
     */
    @FormUrlEncoded
    @POST("api/qualityFeedbackController/deleteQualityFile")
    Observable<HttpResult<String>> deleteQualityFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改质量反馈表单
     */
    @FormUrlEncoded
    @POST("api/qualityFeedbackController/updateQualityFeedback")
    Observable<HttpResult<String>> updateQualityFeedback(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取质量反馈列表
     */
    @FormUrlEncoded
    @POST("api/qualityFeedbackController/getQualityFeedbackList")
    Observable<HttpResult<List<FeedBackBean>>> getQualityFeedbackList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取质量反馈详情
     */
    @FormUrlEncoded
    @POST("api/qualityFeedbackController/getQualityFeedbackDetail")
    Observable<HttpResult<FeedBackBean>> getQualityFeedbackDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加电子保修卡
     */
    @FormUrlEncoded
    @POST("api/electronicCardController/insertElectronicCard")
    Observable<HttpResult<String>> insertElectronicCard(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除电子保修卡记录
     */
    @FormUrlEncoded
    @POST("api/electronicCardController/deleteElectronicCard")
    Observable<HttpResult<String>> deleteElectronicCard(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 删除电子保修卡附件
     */
    @FormUrlEncoded
    @POST("api/electronicCardController/deleteElectronicImg")
    Observable<HttpResult<String>> deleteElectronicImg(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改电子保修卡表单
     */
    @FormUrlEncoded
    @POST("api/electronicCardController/updateElectronicCard")
    Observable<HttpResult<String>> updateElectronicCard(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取电子保修卡列表
     */
    @FormUrlEncoded
    @POST("api/electronicCardController/getElectronicCardList")
    Observable<HttpResult<List<WarrantyBean>>> getElectronicCardList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取电子保修卡详情
     */
    @FormUrlEncoded
    @POST("api/electronicCardController/getElectronicCardDetail")
    Observable<HttpResult<WarrantyBean>> getElectronicCardDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加区域培训
     */
    @FormUrlEncoded
    @POST("api/areaController/insertAreaStudy")
    Observable<HttpResult<String>> insertAreaStudy(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除区域培训记录
     */
    @FormUrlEncoded
    @POST("api/areaController/deleteAreaStudy")
    Observable<HttpResult<String>> deleteAreaStudy(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 删除区域培训附件
     */
    @FormUrlEncoded
    @POST("api/areaController/deleteAreaStudyFile")
    Observable<HttpResult<String>> deleteAreaStudyFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改区域培训表单
     */
    @FormUrlEncoded
    @POST("api/areaController/updateAreaStudy")
    Observable<HttpResult<String>> updateAreaStudy(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核区域培训表单
     */
    @FormUrlEncoded
    @POST("api/areaController/auditAreaStudy")
    Observable<HttpResult<String>> auditAreaStudy(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取区域培训列表
     */
    @FormUrlEncoded
    @POST("api/areaController/getAreaStudyList")
    Observable<HttpResult<List<AreaStudyBean>>> getAreaStudyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取区域培训详情
     */
    @FormUrlEncoded
    @POST("api/areaController/getAreaStudyDetail")
    Observable<HttpResult<AreaStudyBean>> getAreaStudyDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加区域走访
     */
    @FormUrlEncoded
    @POST("api/areaController/insertAreaInterview")
    Observable<HttpResult<String>> insertAreaInterview(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除区域走访记录
     */
    @FormUrlEncoded
    @POST("api/areaController/deleteAreaInterview")
    Observable<HttpResult<String>> deleteAreaInterview(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 删除区域走访附件
     */
    @FormUrlEncoded
    @POST("api/areaController/deleteAreaInterviewFile")
    Observable<HttpResult<String>> deleteAreaInterviewFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改区域走访表单
     */
    @FormUrlEncoded
    @POST("api/areaController/updateAreaInterview")
    Observable<HttpResult<String>> updateAreaInterview(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核区域走访表单
     */
    @FormUrlEncoded
    @POST("api/areaController/auditAreaInterview")
    Observable<HttpResult<String>> auditAreaInterview(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取区域走访列表
     */
    @FormUrlEncoded
    @POST("api/areaController/getAreaInterviewList")
    Observable<HttpResult<List<InterviewBean>>> getAreaInterviewList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取区域走访详情
     */
    @FormUrlEncoded
    @POST("api/areaController/getAreaInterviewDetail")
    Observable<HttpResult<InterviewBean>> getAreaInterviewDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加月度总结表
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/insertMonthTotal")
    Observable<HttpResult<String>> insertMonthTotal(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除月度总结表
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/deleteMonthTotal")
    Observable<HttpResult<String>> deleteMonthTotal(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 删除月度总结表附件
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/deleteMonthTotalFile")
    Observable<HttpResult<String>> deleteMonthTotalFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改月度总结表表单
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/updateMonthTotal")
    Observable<HttpResult<String>> updateMonthTotal(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核月度总结表表单
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/auditMonthTotal")
    Observable<HttpResult<String>> auditMonthTotal(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取月度总结表列表
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/getMonthTotalList")
    Observable<HttpResult<List<MonthTotalBean>>> getMonthTotalList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取月度总结表详情
     */
    @FormUrlEncoded
    @POST("api/monthTotalController/getMonthTotalDetail")
    Observable<HttpResult<MonthTotalBean>> getMonthTotalDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加新人添加
     */
    @FormUrlEncoded
    @POST("api/staffAddController/insertStaffAdd")
    Observable<HttpResult<String>> insertStaffAdd(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除新人添加
     */
    @FormUrlEncoded
    @POST("api/staffAddController/deleteStaffAdd")
    Observable<HttpResult<String>> deleteStaffAdd(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 修改新人添加表单
     */
    @FormUrlEncoded
    @POST("api/staffAddController/updateStaffAdd")
    Observable<HttpResult<String>> updateStaffAdd(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核表单
     */
    @FormUrlEncoded
    @POST("api/staffAddController/auditStaffAdd")
    Observable<HttpResult<String>> auditStaffAdd(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取新人添加列表
     */
    @FormUrlEncoded
    @POST("api/staffAddController/getStaffAddList")
    Observable<HttpResult<List<PeopleBean>>> getStaffAddList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取新人添加详情
     */
    @FormUrlEncoded
    @POST("api/staffAddController/getStaffAddDetail")
    Observable<HttpResult<PeopleBean>> getStaffAddDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加试压仪返修
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/insertPressureBack")
    Observable<HttpResult<String>> insertPressureBack(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除试压仪返修
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/deletePressureBack")
    Observable<HttpResult<String>> deletePressureBack(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 修改试压仪返修表单
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/updatePressureBack")
    Observable<HttpResult<String>> updatePressureBack(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核试压仪返修表单
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/auditPressureBack")
    Observable<HttpResult<String>> auditPressureBack(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取试压仪返修列表
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/getPressureBackList")
    Observable<HttpResult<List<PressureBackBean>>> getPressureBackList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取试压仪返修详情
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/getPressureBackDetail")
    Observable<HttpResult<PressureBackBean>> getPressureBackDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 本部门和上级部门员工列表
     */
    @FormUrlEncoded
    @POST("api/staffController/getMyAndParentDepartmentStaffList")
    Observable<HttpResult<List<MeParentBean>>> getMeAndParentList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询人员申请审核人
     */
    @FormUrlEncoded
    @POST("api/staffController/getParentDepartmentStaffList")
    Observable<HttpResult<List<MeParentBean>>> getParentDepartmentStaffList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取审核列表
     */
    @FormUrlEncoded
    @POST("api/pressureBackController/getLargeStaffList")
    Observable<HttpResult<List<MeParentBean>>> getLargeStaffList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 单据审核及时率统计表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getTimelyForm")
    Observable<HttpResult<List<InstallFormBean>>> getTimelyForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 经销商统计报表（直营专属）
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getDistributorForm")
    Observable<HttpResult<List<InstallFormBean>>> getDistributorForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 门套安装情况统计报表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getDoorForm")
    Observable<HttpResult<List<InstallFormBean>>> getDoorForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 管路走向统计表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getPipeTrendForm")
    Observable<HttpResult<List<InstallFormBean>>> getPipeTrendForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 特殊情况
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getEventForm")
    Observable<HttpResult<List<InstallFormBean>>> getEventForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 试压人员试压量统计表报
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getPlumberForm")
    Observable<HttpResult<List<InstallFormBean>>> getPlumberForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 水工试压量统计报表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getHydraulicNameForm")
    Observable<HttpResult<List<InstallFormBean>>> getHydraulicNameForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 线管品牌统计表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getSpoolTypeForm")
    Observable<HttpResult<List<InstallFormBean>>> getSpoolTypeForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 项目经理信息登记表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getProjectManagerForm")
    Observable<HttpResult<List<InstallFormBean>>> getProjectManagerForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询小区报表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getHouseNameForm")
    Observable<HttpResult<List<InstallFormBean>>> getHouseNameForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 业主信息收集数量报表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getOwnerForm")
    Observable<HttpResult<List<InstallFormBean>>> getOwnerForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 业主装修方式报表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getInstallForm")
    Observable<HttpResult<List<InstallFormBean>>> getInstallForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 真伪查询率
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getAuthenticityForm")
    Observable<HttpResult<List<InstallFormBean>>> getAuthenticityForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 制单数量统计报
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getStaffForm")
    Observable<HttpResult<List<InstallFormBean>>> getStaffForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询装饰公司报表
     */
    @FormUrlEncoded
    @POST("api/reportFormsController/getDecorateCompanyForm")
    Observable<HttpResult<List<InstallFormBean>>> getDecorateCompanyForm(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取app有权限的模块
     */
    @FormUrlEncoded
    @POST("api/systemController/getAppSystemModuleTree")
    Observable<HttpResult<List<AppHomeMenuTreeBean>>> getAppSystemModuleTree(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 查询选中部门组织架构
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffGroupByDepartment")
    Observable<HttpResult<List<staffGroupBeans>>> getStaffGroupByDepartment(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);







    /**
     * 添加入职申请
     */
    @FormUrlEncoded
    @POST("api/staffApplyController/insertStaffApply")
    Observable<HttpResult<String>> insertStaffApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除入职申请
     */
    @FormUrlEncoded
    @POST("api/staffApplyController/deleteStaffApply")
    Observable<HttpResult<String>> deleteStaffApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 修改入职申请
     */
    @FormUrlEncoded
    @POST("api/staffApplyController/updateStaffApply")
    Observable<HttpResult<String>> updateStaffApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核入职申请
     */
    @FormUrlEncoded
    @POST("api/staffApplyController/auditStaffApply")
    Observable<HttpResult<String>> auditStaffApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 入职申请分页查询
     */
    @FormUrlEncoded
    @POST("api/staffApplyController/getStaffApplyList")
    Observable<HttpResult<List<StaffApplyBean>>> getStaffApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 入职申请详情查询
     */
    @FormUrlEncoded
    @POST("api/staffApplyController/getStaffApplyDetail")
    Observable<HttpResult<StaffApplyBean>> getStaffApplyDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);





    /**
     * 添加离职申请
     */
    @FormUrlEncoded
    @POST("api/quitJobController/insertQuitJob")
    Observable<HttpResult<String>> insertQuitJob(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除离职申请
     */
    @FormUrlEncoded
    @POST("api/quitJobController/deleteQuitJob")
    Observable<HttpResult<String>> deleteQuitJob(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 修改离职申请
     */
    @FormUrlEncoded
    @POST("api/quitJobController/updateQuitJob")
    Observable<HttpResult<String>> updateQuitJob(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核离职申请
     */
    @FormUrlEncoded
    @POST("api/quitJobController/auditQuitJob")
    Observable<HttpResult<String>> auditQuitJob(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 离职申请分页查询
     */
    @FormUrlEncoded
    @POST("api/quitJobController/getQuitJobList")
    Observable<HttpResult<List<QuitJobBean>>> getQuitJobList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 离职申请详情查询
     */
    @FormUrlEncoded
    @POST("api/quitJobController/getQuitJobDetail")
    Observable<HttpResult<QuitJobBean>> getQuitJobDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 查找人员
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffInfoList")
    Observable<HttpResult<List<StaffInfoLeaveBean>>> getStaffInfoList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);








    /**
     * 添加入职申请
     */
    @FormUrlEncoded
    @POST("api/areaController/insertShopClose")
    Observable<HttpResult<String>> insertShopClose(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除入职申请
     */
    @FormUrlEncoded
    @POST("api/areaController/deleteShopClose")
    Observable<HttpResult<String>> deleteShopClose(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 修改入职申请
     */
    @FormUrlEncoded
    @POST("api/areaController/updateShopClose")
    Observable<HttpResult<String>> updateShopClose(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核入职申请
     */
    @FormUrlEncoded
    @POST("api/areaController/auditShopClose")
    Observable<HttpResult<String>> auditShopClose(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 入职申请分页查询
     */
    @FormUrlEncoded
    @POST("api/areaController/getShopCloseList")
    Observable<HttpResult<List<ShopCloseBean>>> getShopCloseList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 入职申请详情查询
     */
    @FormUrlEncoded
    @POST("api/areaController/getShopCloseDetail")
    Observable<HttpResult<ShopCloseBean>> getShopCloseDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);



    /**
     * 获取记忆词汇
     */
    @FormUrlEncoded
    @POST("api/pressureController/getMemoryList")
    Observable<HttpResult<List<MemoryBean>>> getMemoryList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 启飞试压详情
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryDetail")
    Observable<HttpResult<com.ak.pt.mvp.fragment.qifei.PressurePageBean>> queryQFDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
    /**
     * 启飞试压记录
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryPressurePage")
    Observable<HttpResult<List<com.ak.pt.mvp.fragment.qifei.PressurePageBean>>> queryQFPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);    /**
     * 启飞区域试压
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryAreaCountPressurePage")
    Observable<HttpResult<List<com.ak.pt.mvp.fragment.qifei.AreaPressureBean>>> queryQFAreaCountPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);    /**
     * 启飞大区试压
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryBigAreaCountPressurePage")
    Observable<HttpResult<List<com.ak.pt.mvp.fragment.qifei.BigAreaBean>>> queryQFBigAreaCountPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
}
