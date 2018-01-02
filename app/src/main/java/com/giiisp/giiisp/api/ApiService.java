package com.giiisp.giiisp.api;


import android.support.v4.util.ArrayMap;

import com.giiisp.giiisp.entity.AnswerEntity;
import com.giiisp.giiisp.entity.AntistopEntity;
import com.giiisp.giiisp.entity.AttentionEntity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.FansConcernedEntity;
import com.giiisp.giiisp.entity.HomeEntity;
import com.giiisp.giiisp.entity.HomeSearchEntity;
import com.giiisp.giiisp.entity.LiteratureEntity;
import com.giiisp.giiisp.entity.MsgEntity;
import com.giiisp.giiisp.entity.MyScholarEntity;
import com.giiisp.giiisp.entity.PaperDatailEntity;
import com.giiisp.giiisp.entity.PhoneEntity;
import com.giiisp.giiisp.entity.QAEntity;
import com.giiisp.giiisp.entity.QuizEntity;
import com.giiisp.giiisp.entity.QuizHintEntity;
import com.giiisp.giiisp.entity.RecommendScholarEntity;
import com.giiisp.giiisp.entity.ScholarEntity;
import com.giiisp.giiisp.entity.SearchHistoryEntity;
import com.giiisp.giiisp.entity.SelectUser;
import com.giiisp.giiisp.entity.SubscribeEntity;
import com.giiisp.giiisp.entity.UpDateAppEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.entity.WaitRecordPaperEntity;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //发送验证码
    @GET(UrlConstants.RequestUrl.SEND_CODE)
    Call<BaseEntity> getCodeInfo(@Query("mobile") String mobile, @Query("codeType") String codeType);

    // 获取用户头像接口
    @GET(UrlConstants.RequestUrl.USER_PORTRAIT)
    Call<ResponseBody> getPortraitInfo(@Query("token") String token);

    //获取七牛图片上传token
    @GET(UrlConstants.RequestUrl.QN_UPLOAD_TOKEN)
    Call<BaseEntity> getQNTokenInfo(@Query("uid") String uid);

    @GET("2017-06-15_1497518545856.mp3")
    Call<ResponseBody> downloadFileWithDynamicUrlSync();
/*
    // 获取用户头像接口
    @GET(UrlConstants.RequestUrl.USER_PORTRAIT)
    Call<ResponseBody> getPortraitInfo(@Query("token") String token);
*/

    //用户注册
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.USER_ENROLL)
    Call<BaseEntity> getEnrollInfo(@FieldMap ArrayMap<String, Object> options);

    //保存客户端设备类型
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_CLIENT_TYPE)
    Call<BaseEntity> saveClientTypeInfo(@FieldMap ArrayMap<String, Object> options);

    //更新用户头像接口
    @Multipart
    @POST(UrlConstants.RequestUrl.USER_UPDATE_PORTRAIT)
    Call<BaseEntity> getUpdatePortraitInfo(@Query("uid") String uid, @Part MultipartBody.Part filePrta);

    //检查指定手机号是否注册
    @GET(UrlConstants.RequestUrl.EXAMINE_PHONE)
    Call<PhoneEntity> getPhoneInfo(@Query("mobile") String mobile);


    //获取我的订阅，赞和新消息数量
    @GET(UrlConstants.RequestUrl.USER_NUMS)
    Call<BaseEntity> getUserNumsInfo(@Query("uid") String uid, @Query("token") String token);

    //修改重新绑定手机号码
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.USER_UPDATE_MOBILE)
    Call<BaseEntity> getUpdateMobileInfo(@FieldMap ArrayMap<String, Object> options);

    //用户登录
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.USER_LOGIN)
    Call<BaseEntity> getLoginInfo(@Field("mobile") String mobile, @Field("pwd") String pwd);

    //用户忘记密码
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.UPDATE_PWD)
    Call<BaseEntity> getRetrieveDateInfo(@FieldMap ArrayMap<String, Object> options);

    //重置密码
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.USER_UPDATE_PWD)
    Call<BaseEntity> getResetPwdInfo(@FieldMap ArrayMap<String, Object> options);


    //第三方登录
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LOGIN_WITH3RD)
    Call<PhoneEntity> getWith3info(@FieldMap ArrayMap<String, Object> options);

    //获取个人信息接口
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.USER_INFO)
    Call<UserInfoEntity> getUserInfo(@FieldMap ArrayMap<String, Object> options);

    //修改个人信息接口
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.UPDATE_USER_INFO)
    Call<BaseEntity> getUpdateUserInfo(@FieldMap ArrayMap<String, Object> options);

    //保存用户反馈信息
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.FEEDBACK_SAVE)
    Call<BaseEntity> getFeedbackInfo(@FieldMap ArrayMap<String, Object> options);

    //获取我订阅的领域/关键字和推荐的领域/关键字
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_SUBSCRIBE_FIELD)
    Call<AttentionEntity> getSubscribeFieldInfo(@FieldMap ArrayMap<String, Object> options);

    //订阅领域/关键字
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_FOLLOW_TYPE)
    Call<BaseEntity> getSaveFollowTypeInfo(@FieldMap ArrayMap<String, Object> options);

    //取消订阅领域/关键字
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.CANCEL_FOLLOW_TYPE)
    Call<BaseEntity> getCancelFollowTypeInfo(@FieldMap ArrayMap<String, Object> options);

    //获取我关注的学者
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_SCHOLARS_FOLLOWED)
    Call<MyScholarEntity> getScholarsFollowedInfo(@FieldMap ArrayMap<String, Object> options);

    //获取推荐的学者
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_SCHOLARS_RECOMMEND)
    Call<RecommendScholarEntity> getScholarsRecommendInfo(@FieldMap ArrayMap<String, Object> options);

    //获取我的回答列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_ANSWER)
    Call<AnswerEntity> getListAnswerInfo(@FieldMap ArrayMap<String, Object> options);

    //获取我的提问列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_QUIZ)
    Call<QuizEntity> getListQuizInfo(@FieldMap ArrayMap<String, Object> options);

    //获取用户的粉丝列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LISTOF_USER_FOLLOWED)
    Call<FansConcernedEntity> getListUserFollowedInfo(@FieldMap ArrayMap<String, Object> options);


    //获取用户关注的人的信息列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LISTOF_USER_FOLLOW)
    Call<FansConcernedEntity> getListUserFollowInfo(@FieldMap ArrayMap<String, Object> options);

    //分享
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_SHARE)
    Call<BaseEntity> getSaveShareInfo(@FieldMap ArrayMap<String, Object> options);


    //关注学者
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_FOLLOW_USER)
    Call<BaseEntity> getSaveFollowUserInfo(@FieldMap ArrayMap<String, Object> options);


    //取消关注
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.CANCEL_FOLLOW_USER)
    Call<BaseEntity> getCancelFollowUserInfo(@FieldMap ArrayMap<String, Object> options);

    //收藏点赞 论文或者图片
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_FOLLOW_PAPER_PICTURE)
    Call<BaseEntity> getSaveFollowPaperPictureInfo(@FieldMap ArrayMap<String, Object> options);

    //  取消收藏点赞 论文或者图片
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.CANCEL_FOLLOW_PAPER_PICTURE)
    Call<BaseEntity> getCancelFollowPaperPictureInfo(@FieldMap ArrayMap<String, Object> options);

    //提问
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_QUIZ)
    Call<BaseEntity> getSaveQuizInfo(@FieldMap ArrayMap<String, Object> options);

    //回答
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_ANSWER)
    Call<BaseEntity> getSaveAnswerInfo(@FieldMap ArrayMap<String, Object> options);

    //学者列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_SCHOLAR)
    Call<ScholarEntity> getListScholarInfo(@FieldMap ArrayMap<String, Object> options);

    //综述/论文列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_SUMMARIZE)
    Call<SubscribeEntity> getListSummarizeInfo(@FieldMap ArrayMap<String, Object> options);

    //论文列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_NEW_PAPER)
    Call<SubscribeEntity> getListNewPaperInfo(@FieldMap ArrayMap<String, Object> options);

    //获取待配音论文列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.WAIT_RECORD_LIST)
    Call<WaitRecordPaperEntity> getWaitRecordPaperListInfo(@FieldMap ArrayMap<String, Object> options);

    //获取论文详细信息
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_PAPER_BASE)
    Call<PaperDatailEntity> getPaperBaseByIdInfo(@FieldMap ArrayMap<String, Object> options);

    //获取论文对应的图片的问答列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_LIST_QA)
    Call<QAEntity> getListOfQuizAndAnswerInfo(@FieldMap ArrayMap<String, Object> options);

    //获取论文文献列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_LIST_LITERATURE)
    Call<LiteratureEntity> getListOfLiteratureInfo(@FieldMap ArrayMap<String, Object> options);

    //获取论文关键字列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_LIST_ANTISTOP)
    Call<AntistopEntity> getListOfAntistopInfo(@FieldMap ArrayMap<String, Object> options);

    //用户订阅的论文列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_FOLLOW_PAPER)
    Call<SubscribeEntity> getlistFollowPaperInfo(@FieldMap ArrayMap<String, Object> options);

    //我收藏的论文/综述列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.LIST_FOLLOWED_PAPER)
    Call<SubscribeEntity> getListFollowedPaperInfo(@FieldMap ArrayMap<String, Object> options);

    //保存录音
    @Multipart
    @POST(UrlConstants.RequestUrl.SAVE_RECORD)
//    @Headers("Connection:close")
    Call<BaseEntity> getSaveRecordInfo(@QueryMap ArrayMap<String, Object> options, @Part MultipartBody.Part parts);

    //首页
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.HOME_PAGE)
    Call<HomeEntity> gethomePageInfo(@FieldMap ArrayMap<String, Object> options);

    //获取用户的履历
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.GET_USER_RECORD)
    Call<BaseEntity> getUserRecordInfo(@FieldMap ArrayMap<String, Object> options);

    //提问提示列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.QUIZ_HINT_LIST)
    Call<QuizHintEntity> getQuizHintListInfo(@FieldMap ArrayMap<String, Object> options);

    //清空我的搜索历史
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.CLEAN_SEARCH_HISTORY)
    Call<QuizHintEntity> getCleanHistoryInfo(@FieldMap ArrayMap<String, Object> options);

    //我的搜索和其他人的搜索关键词
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SEARCH_HISTORY)
    Call<SearchHistoryEntity> getSearchHistoryInfo(@FieldMap ArrayMap<String, Object> options);

    //首页搜索
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.HOME_SEARCH)
    Call<HomeSearchEntity> getHomeSearchInfo(@FieldMap ArrayMap<String, Object> options);

    //发表论文
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.UPDATE_PAPER_BASE)
    Call<HomeSearchEntity> getUpdatePaperInfo(@FieldMap ArrayMap<String, Object> options);

    //获取新消息列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.MSG_LIST)
    Call<MsgEntity> getMsgListInfo(@FieldMap ArrayMap<String, Object> options);

    //获取新消息列表
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.MSG_DEL)
    Call<BaseEntity> getMsgDelInfo(@FieldMap ArrayMap<String, Object> options);

    //用户注销
    @GET(UrlConstants.RequestUrl.USER_LOGOUT)
    Call<BaseEntity> getUserLogOutInfo();

    //update app
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.APP_INFO)
    Call<UpDateAppEntity> getAppInfo(@FieldMap ArrayMap<String, Object> options);

    //用邮箱认证
    @Multipart
    @POST(UrlConstants.RequestUrl.AUTHEN_USER)
    @Headers("Connection:close")
    Call<BaseEntity> getAuthenUserInfo(@Query("email") String email, @Query("uid") String uid, @Part MultipartBody.Part part);

    //添加教育经历
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.ADD_EXP)
    Call<BaseEntity> addIntroduction(@FieldMap ArrayMap<String, Object> options);

    //update教育经历
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.UPDATE_EXP)
    Call<BaseEntity> updateIntroduction(@FieldMap ArrayMap<String, Object> options);

    //delete教育经历
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.DELETE_EXP)
    Call<BaseEntity> deleteIntroduction(@FieldMap ArrayMap<String, Object> options);

    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SELECT_USER)
    Call<SelectUser> selectUser(@FieldMap ArrayMap<String, Object> options);

    @Multipart
    @POST(UrlConstants.RequestUrl.USER_AUTHEN)
    @Headers("Connection:close")
    Call<BaseEntity> userAuthen(@Part List<MultipartBody.Part> parts);

   /* //收藏点赞 论文或者图片
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.SAVE_FOLLOW_PAPER_PICTURE)
    Call<BaseEntity> getListQuizInfo(@FieldMap ArrayMap<String, Object> options);*/

   // 检查论文密码
    @FormUrlEncoded
    @POST(UrlConstants.RequestUrl.CHECK_PAPERPWD)
    Call<BaseEntity> checkPaperPwd(@FieldMap ArrayMap<String, Object> options);

}
