package com.giiisp.giiisp.model;


import android.support.v4.util.ArrayMap;

import com.giiisp.giiisp.api.ApiStore;
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
import retrofit2.Callback;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2017/5/3.
 */
public class BaseModel {
    public void getCodeData(String mobile, String codeType, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getCodeInfo(mobile, codeType).enqueue(callback);
    }

    public void getPhoneData(String mobile, Callback<PhoneEntity> callback) {
        ApiStore.getInstance().getApiService().getPhoneInfo(mobile).enqueue(callback);
    }

    public void getEnrollData(ArrayMap<String, Object> token, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getEnrollInfo(token).enqueue(callback);
    }

    public void saveClientTypeData(ArrayMap<String, Object> token, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().saveClientTypeInfo(token).enqueue(callback);
    }

    public void getUpdatePortraitData(String uid,  MultipartBody.Part filePart, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getUpdatePortraitInfo(uid,filePart).enqueue(callback);
    }

    public void getLoginData(String mobile, String pwd, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getLoginInfo(mobile, pwd).enqueue(callback);
    }

    public void getRetrieveData(ArrayMap<String, Object> token, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getRetrieveDateInfo(token).enqueue(callback);
    }

    public void getUpdateMobileData(ArrayMap<String, Object> token, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getUpdateMobileInfo(token).enqueue(callback);
    }

    public void getUserInfoData(ArrayMap<String, Object> token, Callback<UserInfoEntity> callback) {
        ApiStore.getInstance().getApiService().getUserInfo(token).enqueue(callback);
    }

    public void getUpdateUserInfoData(ArrayMap<String, Object> token, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getUpdateUserInfo(token).enqueue(callback);
    }

    public void getUserNumsData(String uid, String token, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getUserNumsInfo(uid, token).enqueue(callback);
    }

    public void getResetPwdData(ArrayMap<String, Object> mobile, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getResetPwdInfo(mobile).enqueue(callback);
    }

    public void getFeedbackData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getFeedbackInfo(options).enqueue(callback);
    }

    public void getSaveFollowTypeData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveFollowTypeInfo(options).enqueue(callback);
    }

    public void getCancelFollowTypeInfo(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getCancelFollowTypeInfo(options).enqueue(callback);
    }

    public void getSubscribeFieldData(ArrayMap<String, Object> options, Callback<AttentionEntity> callback) {
        ApiStore.getInstance().getApiService().getSubscribeFieldInfo(options).enqueue(callback);
    }

    public void getScholarsFollowedData(ArrayMap<String, Object> options, Callback<MyScholarEntity> callback) {
        ApiStore.getInstance().getApiService().getScholarsFollowedInfo(options).enqueue(callback);
    }

    public void getScholarsRecommendData(ArrayMap<String, Object> options, Callback<RecommendScholarEntity> callback) {
        ApiStore.getInstance().getApiService().getScholarsRecommendInfo(options).enqueue(callback);
    }

    public void getPortraitData(String options, Callback<ResponseBody> callback) {
        ApiStore.getInstance().getApiServiceString().getPortraitInfo(options).enqueue(callback);
    }

    public void getQNTokenData(String options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getQNTokenInfo(options).enqueue(callback);
    }

    public void downloadFileWithDynamicUrlSync(Callback<ResponseBody> callback) {
        ApiStore.getInstance().getApiServiceURl().downloadFileWithDynamicUrlSync().enqueue(callback);
    }


    public void getWith3Data(ArrayMap<String, Object> options, Callback<PhoneEntity> callback) {
        ApiStore.getInstance().getApiService().getWith3info(options).enqueue(callback);
    }

    public void getListAnswerData(ArrayMap<String, Object> options, Callback<AnswerEntity> callback) {
        ApiStore.getInstance().getApiService().getListAnswerInfo(options).enqueue(callback);
    }

    public void getListQuizData(ArrayMap<String, Object> options, Callback<QuizEntity> callback) {
        ApiStore.getInstance().getApiService().getListQuizInfo(options).enqueue(callback);
    }

    public void getListUserFollowedData(ArrayMap<String, Object> options, Callback<FansConcernedEntity> callback) {
        ApiStore.getInstance().getApiService().getListUserFollowedInfo(options).enqueue(callback);
    }

    public void getListUserFollowData(ArrayMap<String, Object> options, Callback<FansConcernedEntity> callback) {
        ApiStore.getInstance().getApiService().getListUserFollowInfo(options).enqueue(callback);
    }

    public void getSaveFollowUserData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveFollowUserInfo(options).enqueue(callback);
    }

    public void getCancelFollowUserData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getCancelFollowUserInfo(options).enqueue(callback);
    }

    public void getSaveFollowPaperPictureData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveFollowPaperPictureInfo(options).enqueue(callback);
    }

    public void getCancelFollowPaperPictureData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getCancelFollowPaperPictureInfo(options).enqueue(callback);
    }

    public void getSaveQuizData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveQuizInfo(options).enqueue(callback);
    }

    public void getSaveAnswerData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveAnswerInfo(options).enqueue(callback);
    }

    public void getSaveShareData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveShareInfo(options).enqueue(callback);
    }

    public void getListScholarData(ArrayMap<String, Object> options, Callback<ScholarEntity> callback) {
        ApiStore.getInstance().getApiService().getListScholarInfo(options).enqueue(callback);
    }

    public void getListSummarizeData(ArrayMap<String, Object> options, Callback<SubscribeEntity> callback) {
        ApiStore.getInstance().getApiService().getListSummarizeInfo(options).enqueue(callback);
    }

    public void getListNewPaperData(ArrayMap<String, Object> options, Callback<SubscribeEntity> callback) {
        ApiStore.getInstance().getApiService().getListNewPaperInfo(options).enqueue(callback);
    }

    public void getWaitRecordPaperListData(ArrayMap<String, Object> options, Callback<WaitRecordPaperEntity> callback) {
        ApiStore.getInstance().getApiService().getWaitRecordPaperListInfo(options).enqueue(callback);
    }

    public void getPaperBaseByIdData(ArrayMap<String, Object> options, Callback<PaperDatailEntity> callback) {
        ApiStore.getInstance().getApiService().getPaperBaseByIdInfo(options).enqueue(callback);
    }

    public void getListOfLiteratureData(ArrayMap<String, Object> options, Callback<LiteratureEntity> callback) {
        ApiStore.getInstance().getApiService().getListOfLiteratureInfo(options).enqueue(callback);
    }

    public void getListOfAntistopData(ArrayMap<String, Object> options, Callback<AntistopEntity> callback) {
        ApiStore.getInstance().getApiService().getListOfAntistopInfo(options).enqueue(callback);
    }

    public void getlistFollowPaperData(ArrayMap<String, Object> options, Callback<SubscribeEntity> callback) {
        ApiStore.getInstance().getApiService().getlistFollowPaperInfo(options).enqueue(callback);
    }

    public void getListOfQuizAndAnswerData(ArrayMap<String, Object> options, Callback<QAEntity> callback) {
        ApiStore.getInstance().getApiService().getListOfQuizAndAnswerInfo(options).enqueue(callback);
    }

    public void getListFollowedPaperData(ArrayMap<String, Object> options, Callback<SubscribeEntity> callback) {
        ApiStore.getInstance().getApiService().getListFollowedPaperInfo(options).enqueue(callback);
    }

    public void getSaveRecordData( ArrayMap<String, Object> options,MultipartBody.Part part, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getSaveRecordInfo(options,part).enqueue(callback);
    }

    public void gethomePageData(ArrayMap<String, Object> options, Callback<HomeEntity> callback) {
        ApiStore.getInstance().getApiService().gethomePageInfo(options).enqueue(callback);
    }

    public void getUserRecordData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getUserRecordInfo(options).enqueue(callback);
    }

    public void getQuizHintListData(ArrayMap<String, Object> options, Callback<QuizHintEntity> callback) {
        ApiStore.getInstance().getApiService().getQuizHintListInfo(options).enqueue(callback);
    }

    public void getSearchHistoryData(ArrayMap<String, Object> options, Callback<SearchHistoryEntity> callback) {
        ApiStore.getInstance().getApiService().getSearchHistoryInfo(options).enqueue(callback);
    }

    public void getCleanHistoryData(ArrayMap<String, Object> options, Callback<QuizHintEntity> callback) {
        ApiStore.getInstance().getApiService().getCleanHistoryInfo(options).enqueue(callback);
    }

    public void getHomeSearchData(ArrayMap<String, Object> options, Callback<HomeSearchEntity> callback) {
        ApiStore.getInstance().getApiService().getHomeSearchInfo(options).enqueue(callback);
    }

    public void getUpdatePaperData(ArrayMap<String, Object> options, Callback<HomeSearchEntity> callback) {
        ApiStore.getInstance().getApiService().getUpdatePaperInfo(options).enqueue(callback);
    }

    public void getMsgListData(ArrayMap<String, Object> options, Callback<MsgEntity> callback) {
        ApiStore.getInstance().getApiService().getMsgListInfo(options).enqueue(callback);
    }

    public void getMsgDelData(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getMsgDelInfo(options).enqueue(callback);
    }

    public void getAuthenUserlData(String email,String uid,MultipartBody.Part part, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getAuthenUserInfo(email,uid,part).enqueue(callback);
    }

    public void getUserLogOutData(Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().getUserLogOutInfo().enqueue(callback);
    }

    public void getAppInfoData(ArrayMap<String, Object> options, Callback<UpDateAppEntity> callback) {
        ApiStore.getInstance().getApiService().getAppInfo(options).enqueue(callback);
    }

    public void addIntroduction(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().addIntroduction(options).enqueue(callback);
    }
    public void updateIntroduction(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().updateIntroduction(options).enqueue(callback);
    }
    public void deleteIntroduction(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().deleteIntroduction(options).enqueue(callback);
    }
    public void userAuthen(List<MultipartBody.Part> parts, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().userAuthen(parts).enqueue(callback);
    }

    public void selectUser(ArrayMap<String, Object> options, Callback<SelectUser> callback) {
        ApiStore.getInstance().getApiService().selectUser(options).enqueue(callback);
    }

    public void checkPaperPwd(ArrayMap<String, Object> options, Callback<BaseEntity> callback) {
        ApiStore.getInstance().getApiService().checkPaperPwd(options).enqueue(callback);
    }
}
