package com.akan.qf.http;

import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.mvp.fragment.qifei.AreaPressureBean;
import com.akan.qf.bean.BannerBean;
import com.akan.qf.mvp.fragment.qifei.BigAreaBean;
import com.akan.qf.bean.BookBean;
import com.akan.qf.bean.ClassList;
import com.akan.qf.bean.CompanyBean;
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.CostTypeBean;
import com.akan.qf.bean.CustomerInfoBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.DebtApplyBean;
import com.akan.qf.bean.DocumentBean;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.NetworkBean;
import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PayApplyBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.bean.PeopleLeaveBean;
import com.akan.qf.bean.PeopleNewBean;
import com.akan.qf.bean.PolicyBean;
import com.akan.qf.mvp.fragment.qifei.PressurePageBean;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.bean.ReimbursementInfoBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.bean.RetnrnBean;
import com.akan.qf.bean.SaleDataBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.bean.SaleForecastBean;
import com.akan.qf.bean.SignBean;
import com.akan.qf.mvp.fragment.qifei.StaffBean;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.bean.StoreApplyBean;
import com.akan.qf.bean.TaskBean;
import com.akan.qf.bean.TemporaryBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.VisitorBean;
import com.akan.qf.bean.WaterBean;
import com.akan.qf.bean.staffGroupBeans;

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
     * 日报列表
     */
    @FormUrlEncoded
    @POST("api/crmController/getDailyList")
    Observable<HttpResult<List<DailyBean>>> getDailyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 轮播列表
     */
    @FormUrlEncoded
    @POST("api/appController/getBannerList")
    Observable<HttpResult<List<BannerBean>>> getBannerList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

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
     * 审核日报
     */
    @FormUrlEncoded
    @POST("api/crmController/auditDaily")
    Observable<HttpResult<String>> auditDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

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
     * 员工组织架构树
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffGroupTreeByStaff")
    Observable<HttpResult<List<staffGroupBeans>>> getStaffGroupTreeByStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 审核请假条
     */
    @FormUrlEncoded
    @POST("api/crmController/auditAskLeave")
    Observable<HttpResult<String>> auditAskLeave(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 请假条列表
     */
    @FormUrlEncoded
    @POST("api/crmController/getAskLeaveList")
    Observable<HttpResult<List<LeaveBean>>> getAskLeaveList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加请假条
     */
    @FormUrlEncoded
    @POST("api/crmController/insertOrUpdateAskLeave")
    Observable<HttpResult<String>> insertAskLeave(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加请假条
     */
    @FormUrlEncoded
    @POST("api/crmController/deleteAskLeaveFile")
    Observable<HttpResult<String>> deleteAskLeaveFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除请假条
     */
    @FormUrlEncoded
    @POST("api/crmController/deleteAskLeave")
    Observable<HttpResult<String>> deleteAskLeave(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 请假条详情
     */
    @FormUrlEncoded
    @POST("api/crmController/getAskLeaveDetail")
    Observable<HttpResult<LeaveBean>> getLeaveDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * app首页菜单树
     */
    @FormUrlEncoded
    @POST("api/systemController/getAppSystemModuleTree")
    Observable<HttpResult<List<AppHomeMenuTreeBean>>> getAppHomeMenuTreeByStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 本部门和上级部门员工列表
     */
    @FormUrlEncoded
    @POST("api/staffController/getMyAndParentDepartmentStaffList")
    Observable<HttpResult<List<MeParentBean>>> getMyAndParentDepartmentStaffList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

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
     * 费用报销单详情
     */
    @FormUrlEncoded
    @POST("api/crmController/getExpenseReimbursement")
    Observable<HttpResult<ReimbursementInfoBean>> getExpenseReimbursement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 通讯簿列表
     */
    @FormUrlEncoded
    @POST("api/appController/getAddressBookList")
    Observable<HttpResult<List<BookBean>>> getAddressBookList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 费用报销单列表
     */
    @FormUrlEncoded
    @POST("api/crmController/getExpenseReimbursementInfoList")
    Observable<HttpResult<List<ReimbursementInfoBean>>> getExpenseReimbursementInfoList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加费用报销单
     */
    @FormUrlEncoded
    @POST("api/crmController/insertExpenseReimbursement")
    Observable<HttpResult<String>> insertExpenseReimbursement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除费用报销单
     */
    @FormUrlEncoded
    @POST("api/crmController/deleteExpenseReimbursement")
    Observable<HttpResult<String>> deleteExpenseReimbursement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除费用报销单文件
     */
    @FormUrlEncoded
    @POST("api/crmController/deleteExpenseReimbursementFile")
    Observable<HttpResult<String>> deleteExpenseReimbursementFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加费用报销单
     */
    @FormUrlEncoded
    @POST("api/crmController/updateExpenseReimbursement")
    Observable<HttpResult<String>> updateExpenseReimbursement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加或修改招人申请
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateRecruitApply")
    Observable<HttpResult<String>> insertOrUpdateRecruitApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询招人申请列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getRecruitApplyList")
    Observable<HttpResult<List<PeopleJionBean>>> getRecruitApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取招人申请详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getRecruitApply")
    Observable<HttpResult<PeopleJionBean>> getRecruitApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核招人申请
     */
    @FormUrlEncoded
    @POST("api/commonController/auditRecruitApply")
    Observable<HttpResult<String>> auditRecruitApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除招人申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteRecruitApply")
    Observable<HttpResult<String>> deleteRecruitApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改新人入职
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateEntryApply")
    Observable<HttpResult<String>> insertOrUpdateEntryApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取新人入职列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getEntryApplyList")
    Observable<HttpResult<List<PeopleNewBean>>> getEntryApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询新人入职详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getEntryApplyDaily")
    Observable<HttpResult<PeopleNewBean>> getEntryApplyDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核新人入职
     */
    @FormUrlEncoded
    @POST("api/commonController/auditEntryApply")
    Observable<HttpResult<String>> auditEntryApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除新人入职申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteEntryApply")
    Observable<HttpResult<String>> deleteEntryApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改离职申请
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateQuiteApply")
    Observable<HttpResult<String>> insertOrUpdateQuiteApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取离职申请列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getQuiteApplyList")
    Observable<HttpResult<List<PeopleLeaveBean>>> getQuiteApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询离职申请详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getQuiteApplyDaily")
    Observable<HttpResult<PeopleLeaveBean>> getQuiteApplyDaily(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核离职申请
     */
    @FormUrlEncoded
    @POST("api/commonController/auditQuiteApply")
    Observable<HttpResult<String>> auditQuiteApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除离职申请申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteQuiteApply")
    Observable<HttpResult<String>> deleteQuiteApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改广告
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateAdvertApply")
    Observable<HttpResult<String>> insertOrUpdateAdvertApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取广告列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getAdvertApplyList")
    Observable<HttpResult<List<AdManagementBean>>> getAdvertApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 查询广告详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getAdvertApply")
    Observable<HttpResult<AdManagementBean>> getAdvertApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核广告
     */
    @FormUrlEncoded
    @POST("api/commonController/auditAdvertApply")
    Observable<HttpResult<String>> auditAdvertApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除广告
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteAdvertApply")
    Observable<HttpResult<String>> deleteAdvertApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除广告附件
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteAdvertApplyFile")
    Observable<HttpResult<String>> deleteAdvertApplyFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 审核费用报销单
     */
    @FormUrlEncoded
    @POST("api/crmController/auditExpenseReimbursement")
    Observable<HttpResult<String>> auditExpenseReimbursement(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加付费单
     */
    @FormUrlEncoded
    @POST("api/commonController/insertPayApply")
    Observable<HttpResult<String>> insertPayApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加付费单
     */
    @FormUrlEncoded
    @POST("api/commonController/deletePayApply")
    Observable<HttpResult<String>> deletePayApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加付费单
     */
    @FormUrlEncoded
    @POST("api/commonController/deletePayApplyFile")
    Observable<HttpResult<String>> deletePayApplyFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加付费单
     */
    @FormUrlEncoded
    @POST("api/commonController/updatePayApply")
    Observable<HttpResult<String>> updatePayApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 付费单审核
     */
    @FormUrlEncoded
    @POST("api/commonController/auditPayApply")
    Observable<HttpResult<String>> auditPayApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 付费单列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getPayApplyList")
    Observable<HttpResult<List<PayApplyBean>>> getPayApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 公告列表
     */
    @FormUrlEncoded
    @POST("api/appController/getNoticeList")
    Observable<HttpResult<List<NoticeBean>>> getNoticeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 付费单详情
     */
    @FormUrlEncoded
    @POST("api/appController/getNoticeDetail")
    Observable<HttpResult<NoticeBean>> getNoticeDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 付费单详情
     */
    @FormUrlEncoded
    @POST("api/appController/getNotReadNoticeCount")
    Observable<HttpResult<String>> getNotReadNoticeCount(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 付费单详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getPayApply")
    Observable<HttpResult<PayApplyBean>> getPayApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加信息统计
     */
    @FormUrlEncoded
    @POST("api/commonController/insertCustomerInfo")
    Observable<HttpResult<String>> insertCustomerInfo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 信息统计列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getCustomerInfoList")
    Observable<HttpResult<List<CustomerInfoBean>>> getCustomerInfoList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 信息统计详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getCustomerInfo")
    Observable<HttpResult<CustomerInfoBean>> getCustomerInfo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核信息统计
     */
    @FormUrlEncoded
    @POST("api/commonController/auditCustomerInfo")
    Observable<HttpResult<String>> auditCustomerInfo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加暂支单
     */
    @FormUrlEncoded
    @POST("api/commonController/insertTemporarySupport")
    Observable<HttpResult<String>> insertTemporarySupport(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加暂支单
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteTemporarySupport")
    Observable<HttpResult<String>> deleteTemporarySupport(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加暂支单
     */
    @FormUrlEncoded
    @POST("api/commonController/updateTemporarySupport")
    Observable<HttpResult<String>> updateTemporarySupport(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加暂支单
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteTemporarySupportFile")
    Observable<HttpResult<String>> deleteTemporarySupportFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 暂支单列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getTemporarySupportList")
    Observable<HttpResult<List<TemporaryBean>>> getTemporarySupportList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 暂支单详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getTemporarySupport")
    Observable<HttpResult<TemporaryBean>> getTemporarySupport(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核暂支单
     */
    @FormUrlEncoded
    @POST("api/commonController/auditTemporarySupport")
    Observable<HttpResult<String>> auditTemporarySupport(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加政策单
     */
    @FormUrlEncoded
    @POST("api/commonController/insertPolicyApply")
    Observable<HttpResult<String>> insertPolicyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除政策单
     */
    @FormUrlEncoded
    @POST("api/commonController/deletePolicyApply")
    Observable<HttpResult<String>> deletePolicyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 修改政策单
     */
    @FormUrlEncoded
    @POST("api/commonController/updatePolicyApply")
    Observable<HttpResult<String>> updatePolicyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除政策单附件
     */
    @FormUrlEncoded
    @POST("api/commonController/deletePolicyApplyFile")
    Observable<HttpResult<String>> deletePolicyApplyFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除客户合同附件
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteContractFileApply")
    Observable<HttpResult<String>> deleteContractFileApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 政策单列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getPolicyApplyList")
    Observable<HttpResult<List<PolicyBean>>> getPolicyApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 政策单详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getPolicyApply")
    Observable<HttpResult<PolicyBean>> getPolicyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核政策单
     */
    @FormUrlEncoded
    @POST("api/commonController/auditPolicyApply")
    Observable<HttpResult<String>> auditPolicyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加问题建议/新品申请
     */
    @FormUrlEncoded
    @POST("api/commonController/insertNewApply")
    Observable<HttpResult<String>> insertNewApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加问题建议/新品申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteNewApply")
    Observable<HttpResult<String>> deleteNewApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加问题建议/新品申请
     */
    @FormUrlEncoded
    @POST("api/commonController/updateNewApply")
    Observable<HttpResult<String>> updateNewApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加问题建议/新品申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteNewApplyFile")
    Observable<HttpResult<String>> deleteNewApplyFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 问题建议/新品申请列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getNewApplyList")
    Observable<HttpResult<List<NewApplyBean>>> getNewApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 问题建议/新品申请详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getNewApply")
    Observable<HttpResult<NewApplyBean>> getNewApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核问题建议/新品申请
     */
    @FormUrlEncoded
    @POST("api/commonController/auditNewApply")
    Observable<HttpResult<String>> auditNewApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加退货申请
     */
    @FormUrlEncoded
    @POST("api/commonController/insertGoodsApply")
    Observable<HttpResult<String>> insertGoodsApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加退货申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteGoodsApply")
    Observable<HttpResult<String>> deleteGoodsApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加退货申请
     */
    @FormUrlEncoded
    @POST("api/commonController/updateGoodsApply")
    Observable<HttpResult<String>> updateGoodsApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加退货申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteGoodsApplyFile")
    Observable<HttpResult<String>> deleteGoodsApplyFile(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 退货申请列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getGoodsApplyList")
    Observable<HttpResult<List<RetnrnBean>>> getGoodsApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 退货申请详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getGoodsApply")
    Observable<HttpResult<RetnrnBean>> getGoodsApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核退货申请
     */
    @FormUrlEncoded
    @POST("api/commonController/auditGoodsApply")
    Observable<HttpResult<String>> auditGoodsApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/insertVisitorApply")
    Observable<HttpResult<String>> insertVisitorApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/updateVisitorApply")
    Observable<HttpResult<String>> updateVisitorApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteCustomerInfo")
    Observable<HttpResult<String>> deleteCustomerInfo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/updateCustomerInfo")
    Observable<HttpResult<String>> updateCustomerInfo(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteVisitorApply")
    Observable<HttpResult<String>> deleteVisitorApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteStoreApply")
    Observable<HttpResult<String>> deleteStoreApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 客户来访列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getVisitorApplyList")
    Observable<HttpResult<List<VisitorBean>>> getVisitorApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 客户来访详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getVisitorApply")
    Observable<HttpResult<VisitorBean>> getVisitorApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核客户来访
     */
    @FormUrlEncoded
    @POST("api/commonController/auditVisitorApply")
    Observable<HttpResult<String>> auditVisitorApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加工程报备
     */
    @FormUrlEncoded
    @POST("api/commonController/insertProjectApply")
    Observable<HttpResult<String>> insertProjectApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加工程报备
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteProjectApply")
    Observable<HttpResult<String>> deleteProjectApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加工程报备
     */
    @FormUrlEncoded
    @POST("api/commonController/updateProjectApply")
    Observable<HttpResult<String>> updateProjectApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 工程报备列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getProjectApplyList")
    Observable<HttpResult<List<ReprotedBean>>> getProjectApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 工程报备详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getProjectApply")
    Observable<HttpResult<ReprotedBean>> getProjectApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核工程报备
     */
    @FormUrlEncoded
    @POST("api/commonController/auditProjectApply")
    Observable<HttpResult<String>> auditProjectApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("api/commonController/insertSuggest")
    Observable<HttpResult<String>> insertSuggestList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 任务分类列表
     */
    @FormUrlEncoded
    @POST("api/crmController/getSaleTaskList")
    Observable<HttpResult<List<TaskBean>>> getSaleTaskList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 销售数据汇总
     */
    @FormUrlEncoded
    @POST("api/crmController/getSaleDataSumList")
    Observable<HttpResult<List<SaleDataBean>>> getSaleDataSumList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 销售数据分析
     */
    @FormUrlEncoded
    @POST("api/crmController/getSaleDataContrastList")
    Observable<HttpResult<List<SaleDataContrastBean>>> getSaleDataContrastList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 试压记录
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryPressurePage")
    Observable<HttpResult<List<PressurePageBean>>> queryPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 版本
     */
    @FormUrlEncoded
    @POST("api/othersController/getAppVersionDetail")
    Observable<HttpResult<AppVersionBean>> getAppVersionDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 区域试压
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryAreaCountPressurePage")
    Observable<HttpResult<List<AreaPressureBean>>> queryAreaCountPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 大区试压
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryBigAreaCountPressurePage")
    Observable<HttpResult<List<BigAreaBean>>> queryBigAreaCountPressurePage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 区域投稿列表
     */
    @FormUrlEncoded
    @POST("api/staffController/getAreaContributeList")
    Observable<HttpResult<List<ContributeBean>>> getAreaContributeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 区域投稿
     */
    @FormUrlEncoded
    @POST("api/staffController/updateStaffPassword")
    Observable<HttpResult<String>> updateStaffPassword(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 区域投稿
     */
    @FormUrlEncoded
    @POST("api/staffController/insertOrUpdateAreaContribute")
    Observable<HttpResult<String>> insertOrUpdateAreaContribute(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 评论区域投稿
     */
    @FormUrlEncoded
    @POST("api/staffController/insertContributeComment")
    Observable<HttpResult<String>> insertContributeComment(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审阅区域投稿
     */
    @FormUrlEncoded
    @POST("api/staffController/auditAreaContribute")
    Observable<HttpResult<String>> auditAreaContribute(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 区域投稿详情
     */
    @FormUrlEncoded
    @POST("api/staffController/getAreaContributeDetail")
    Observable<HttpResult<ContributeBean>> getAreaContributeDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 区域投稿分类树
     */
    @FormUrlEncoded
    @POST("api/staffController/getContributeClassTree")
    Observable<HttpResult<List<ContributeClassBean>>> getContributeClassTree(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 区域投稿分类树
     */
    @FormUrlEncoded
    @POST("api/staffController/deleteAreaContribute")
    Observable<HttpResult<String>> deleteAreaContribute(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户合同
     */
    @FormUrlEncoded
    @POST("api/commonController/insertContractApply")
    Observable<HttpResult<String>> insertContractApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户合同
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteContractApply")
    Observable<HttpResult<String>> deleteContractApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户合同
     */
    @FormUrlEncoded
    @POST("api/commonController/updateContractApply")
    Observable<HttpResult<String>> updateContractApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户合同
     */
    @FormUrlEncoded
    @POST("api/commonController/getContractApply")
    Observable<HttpResult<ContractApplyBean>> getContractApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 客户合同列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getContractApplyList")
    Observable<HttpResult<List<ContractApplyBean>>> getContractApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户档案
     */
    @FormUrlEncoded
    @POST("api/commonController/insertArchivesApply")
    Observable<HttpResult<String>> insertArchivesApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户档案
     */
    @FormUrlEncoded
    @POST("api/commonController/getArchivesApply")
    Observable<HttpResult<ArchivesApplyBean>> getArchivesApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户档案
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteArchivesApply")
    Observable<HttpResult<String>> deleteArchivesApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加客户档案
     */
    @FormUrlEncoded
    @POST("api/commonController/updateArchivesApply")
    Observable<HttpResult<String>> updateArchivesApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 修改员工信息
     */
    @FormUrlEncoded
    @POST("api/staffController/updateAppStaff")
    Observable<HttpResult<String>> updateStaff(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 客户档案列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getArchivesApplyList")
    Observable<HttpResult<List<ArchivesApplyBean>>> getArchivesApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 员工列表
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffInfoList")
    Observable<HttpResult<List<StaffBean>>> getStaffList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 员工详情
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffDetail")
    Observable<HttpResult<StaffBean>> getStaffDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

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
     * 添加或修改工程项目
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateStoreApply")
    Observable<HttpResult<String>> insertOrUpdateStoreApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取工程项目列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getStoreApplyList")
    Observable<HttpResult<List<StoreApplyBean>>> getStoreApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 工程报备详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getStoreApply")
    Observable<HttpResult<StoreApplyBean>> getStoreApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核工程项目
     */
    @FormUrlEncoded
    @POST("api/commonController/auditStoreApply")
    Observable<HttpResult<String>> auditStoreApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加或修改欠款申请
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateDebtApply")
    Observable<HttpResult<String>> insertOrUpdateDebtApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加或修改欠款申请
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteDebtApply")
    Observable<HttpResult<String>> deleteDebtApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取欠费申请列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getDebtApplyList")
    Observable<HttpResult<List<DebtApplyBean>>> getDebtApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取欠费申请列表
     */
    @FormUrlEncoded
    @POST("api/crmController/getSaleTaskClassList")
    Observable<HttpResult<List<ClassList>>> getSaleTaskClassList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 获取欠费申请详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getDebtApply")
    Observable<HttpResult<DebtApplyBean>> getDebtApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核欠费申请
     */
    @FormUrlEncoded
    @POST("api/commonController/auditDebtApply")
    Observable<HttpResult<String>> auditDebtApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核欠费申请
     */
    @FormUrlEncoded
    @POST("api/startFlayController/queryDetail")
    Observable<HttpResult<PressurePageBean>> queryDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 扫码登陆
     */
    @FormUrlEncoded
    @POST("api/qrCodeLoginController/scanQrLoginCode")
    Observable<HttpResult<String>> scanQrLoginCode(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改公司管理
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateCompanyApply")
    Observable<HttpResult<String>> insertOrUpdateCompanyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取家装公司列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getCompanyApplyList")
    Observable<HttpResult<List<CompanyBean>>> getCompanyApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取公司详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getCompanyApply")
    Observable<HttpResult<CompanyBean>> getCompanyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核公司
     */
    @FormUrlEncoded
    @POST("api/commonController/auditCompanyApply")
    Observable<HttpResult<String>> auditCompanyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除公司
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteCompanyApply")
    Observable<HttpResult<String>> deleteCompanyApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改网点
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateDistributionApply")
    Observable<HttpResult<String>> insertOrUpdateDistributionApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取网点列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getDistributionApplyList")
    Observable<HttpResult<List<NetworkBean>>> getDistributionApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取网点详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getDistributionApply")
    Observable<HttpResult<NetworkBean>> getDistributionApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核网点
     */
    @FormUrlEncoded
    @POST("api/commonController/auditDistributionApply")
    Observable<HttpResult<String>> auditDistributionApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除网点
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteDistributionApply")
    Observable<HttpResult<String>> deleteDistributionApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改工长信息
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateForemanApply")
    Observable<HttpResult<String>> insertOrUpdateForemanApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取工长信息列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getForemanApplyList")
    Observable<HttpResult<List<WaterBean>>> getForemanApplyList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取工长信息详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getForemanApply")
    Observable<HttpResult<WaterBean>> getForemanApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 审核工长信息
     */
    @FormUrlEncoded
    @POST("api/commonController/auditForemanApply")
    Observable<HttpResult<String>> auditForemanApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除工长信息
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteForemanApply")
    Observable<HttpResult<String>> deleteForemanApply(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 查询公文列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getDocumentList")
    Observable<HttpResult<List<DocumentBean>>> getDocumentList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 添加或修改财务统计
     */
    @FormUrlEncoded
    @POST("api/commonController/insertOrUpdateStatistics")
    Observable<HttpResult<String>> insertOrUpdateStatistics(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除销售预测
     */
    @FormUrlEncoded
    @POST("api/crmController/deleteSaleForecast")
    Observable<HttpResult<String>> deleteSaleForecast(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 添加或修改销售预测
     */
    @FormUrlEncoded
    @POST("api/crmController/addOrUpdateSaleForecast")
    Observable<HttpResult<String>> addOrUpdateSaleForecast(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 销售预测列表
     */
    @FormUrlEncoded
    @POST("api/crmController/querySaleForecastPage")
    Observable<HttpResult<List<SaleForecastBean>>> querySaleForecastPage(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取财务统计列表
     */
    @FormUrlEncoded
    @POST("api/commonController/getFinancialStatisticsList")
    Observable<HttpResult<List<FinancialBean>>> getFinancialStatisticsList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取部门职位
     */
    @FormUrlEncoded
    @POST("api/staffController/queryJobNames")
    Observable<HttpResult<List<String>>> queryJobNames(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取标签列表
     */
    @FormUrlEncoded
    @POST("api/staffSignController/getStaffSignList")
    Observable<HttpResult<List<LableBean>>> getStaffSignList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 成本类型列表
     */
    @FormUrlEncoded
    @POST("api/profitStatisticController/queryCostTypeList")
    Observable<HttpResult<List<CostTypeBean>>> queryCostTypeList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 其它成本利润
     */
    @FormUrlEncoded
    @POST("api/profitStatisticController/queryCostTypeProfitStatisticList")
    Observable<HttpResult<List<ProfitBean>>> queryCostTypeProfitStatisticList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 获取财务统计详情
     */
    @FormUrlEncoded
    @POST("api/commonController/getFinancialStatistics")
    Observable<HttpResult<FinancialBean>> getFinancialStatistics(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 删除财务统计
     */
    @FormUrlEncoded
    @POST("api/commonController/deleteFinancialStatistics")
    Observable<HttpResult<String>> deleteFinancialStatistics(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

    /**
     * 验证码
     */
    @FormUrlEncoded
    @POST("api/staffController/insertCode")
    Observable<HttpResult<String>> insertCode(@FieldMap Map<String, String> parmer);

    /**
     * 验证码
     */
    @FormUrlEncoded
    @POST("api/staffController/sendCodeByAccount")
    Observable<HttpResult<String>> sendCodeByAccount(@FieldMap Map<String, String> parmer);

    /**
     * 更换密码
     */
    @FormUrlEncoded
    @POST("api/staffController/updatePassWordByPhone")
    Observable<HttpResult<String>> getwangjiminma(@FieldMap Map<String, String> parmer);

    /**
     * 查询选中部门组织架构
     */
    @FormUrlEncoded
    @POST("api/staffController/getStaffGroupByDepartment")
    Observable<HttpResult<List<staffGroupBeans>>> getStaffGroupByDepartment(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);


    /**
     * 试压 区域试压/大区试压
     */
    @FormUrlEncoded
    @POST("/api/pressureController/getAreaTestPressure")
    Observable<HttpResult<List<BigAreaBean>>> getAreaTestPressure(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
    /**
     * 试压 试压详情
     */
    @FormUrlEncoded
    @POST("api/pressureController/getTestPressureDetail")
    Observable<HttpResult<com.akan.qf.mvp.fragment.statistics.PressurePageBean>> queryPressureDetail(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);
    /**
     * 试压 获取试压单列表
     */
    @FormUrlEncoded
    @POST("api/pressureController/getTestPressureList")
    Observable<HttpResult<List<com.akan.qf.mvp.fragment.statistics.PressurePageBean>>> getTestPressureList(@Header("Authorization") String token, @FieldMap Map<String, String> parmer);

}
