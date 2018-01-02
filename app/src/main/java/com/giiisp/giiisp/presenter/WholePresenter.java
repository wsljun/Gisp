package com.giiisp.giiisp.presenter;


import android.os.Environment;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.giiisp.giiisp.base.BasePresenter;
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
import com.giiisp.giiisp.model.ModelFactory;
import com.giiisp.giiisp.view.impl.BaseImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WholePresenter extends BasePresenter<BaseImpl> {
    private BaseImpl impl = null;


    public WholePresenter(BaseImpl impl) {
        this.impl = impl;
    }

    public void getSendCodeData(String mobile, String codeType) {
        Callback<BaseEntity> callback = new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        };
        ModelFactory.getBaseModel().getCodeData(mobile, codeType, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getPhoneData(String mobile) {
        ModelFactory.getBaseModel().getPhoneData(mobile, new Callback<PhoneEntity>() {
            @Override
            public void onResponse(Call<PhoneEntity> call, Response<PhoneEntity> response) {
                if (response.isSuccessful()) {
                    PhoneEntity entity = response.body();
                    if (entity != null) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getEnrollData(ArrayMap<String, Object> token) {
        ModelFactory.getBaseModel().getEnrollData(token, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void saveClientTypeData(ArrayMap<String, Object> token) {
        ModelFactory.getBaseModel().saveClientTypeData(token, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getUpdatePortraitData(String uid, MultipartBody.Part filePart) {
        ModelFactory.getBaseModel().getUpdatePortraitData(uid, filePart, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getLoginData(String mobile, String pwd) {
        ModelFactory.getBaseModel().getLoginData(mobile, pwd, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getRetrieveData(ArrayMap<String, Object> token) {
        ModelFactory.getBaseModel().getRetrieveData(token, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getUpdateMobileData(ArrayMap<String, Object> token) {
        ModelFactory.getBaseModel().getUpdateMobileData(token, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getUserInfoData(ArrayMap<String, Object> map) {
        ModelFactory.getBaseModel().getUserInfoData(map, new Callback<UserInfoEntity>() {
            @Override
            public void onResponse(Call<UserInfoEntity> call, Response<UserInfoEntity> response) {
                if (response.isSuccessful()) {
                    UserInfoEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getUpdateUserInfoData(ArrayMap<String, Object> mobile) {
        ModelFactory.getBaseModel().getUpdateUserInfoData(mobile, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getUserNumsData(String uid, String mobile) {
        ModelFactory.getBaseModel().getUserNumsData(uid, mobile, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getResetPwdData(ArrayMap<String, Object> mobile) {
        ModelFactory.getBaseModel().getResetPwdData(mobile, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getFeedbackData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getFeedbackData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getSaveFollowTypeData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSaveFollowTypeData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getCancelFollowTypeInfo(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getCancelFollowTypeInfo(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getSubscribeFieldData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSubscribeFieldData(options, new Callback<AttentionEntity>() {
            @Override
            public void onResponse(Call<AttentionEntity> call, Response<AttentionEntity> response) {
                if (response.isSuccessful()) {
                    AttentionEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<AttentionEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getScholarsRecommendData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getScholarsRecommendData(options, new Callback<RecommendScholarEntity>() {
            @Override
            public void onResponse(Call<RecommendScholarEntity> call, Response<RecommendScholarEntity> response) {
                if (response.isSuccessful()) {
                    BaseEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecommendScholarEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getScholarsFollowedData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getScholarsFollowedData(options, new Callback<MyScholarEntity>() {
            @Override
            public void onResponse(Call<MyScholarEntity> call, Response<MyScholarEntity> response) {
                if (response.isSuccessful()) {
                    MyScholarEntity entity = response.body();
                    if (entity != null /*&& entity.getResult() == 1*/) {
                        impl.onSuccess(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyScholarEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getPortraitData(String options) {
        ModelFactory.getBaseModel().getPortraitData(options, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    try {
                        String result = response.body().string().trim();
                        BaseEntity entity = new BaseEntity();
                        entity.setInfo("result");
                        impl.onSuccess(entity);
                        Log.i("--->>", "onResponse: " + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void downloadFileWithDynamicUrlSync() {
   /*     new Thread(new Runnable() {
            @Override
            public void run() {*/
        Log.i("--->>", "downloadFileWithDynamicUrlSync: " + Thread.currentThread().getName());
        ModelFactory.getBaseModel().downloadFileWithDynamicUrlSync(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                    Log.i("--->>", "onResponse: " + writtenToDisk);
                    // response.body() 返回 ResponseBody
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
        //            }

        //        });

    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + File.separator + "Giiisp/" + System.currentTimeMillis() + ".mp3");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                int len;
                while ((len = inputStream.read(fileReader)) != -1) {
                    outputStream.write(fileReader, 0, len);
                    fileSizeDownloaded += len;

                    Log.d("--->>", "file download: " + fileSizeDownloaded + " of " + fileSize);

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    public void getListUserFollowedData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListUserFollowedData(options, new Callback<FansConcernedEntity>() {
            @Override
            public void onResponse(Call<FansConcernedEntity> call, Response<FansConcernedEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<FansConcernedEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListUserFollowData(ArrayMap<String, Object> options) {
        Log.i("--->>", "getListUserFollowData:" + options.toString());
        ModelFactory.getBaseModel().getListUserFollowData(options, new Callback<FansConcernedEntity>() {
            @Override
            public void onResponse(Call<FansConcernedEntity> call, Response<FansConcernedEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<FansConcernedEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getSaveFollowUserData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSaveFollowUserData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getCancelFollowUserData(ArrayMap<String, Object> options) { // TODO getCancelFollowUserData
        ModelFactory.getBaseModel().getCancelFollowUserData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getSaveFollowPaperPictureData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSaveFollowPaperPictureData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getCancelFollowPaperPictureData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getCancelFollowPaperPictureData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getSaveQuizData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSaveQuizData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getSaveAnswerData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSaveAnswerData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void getListScholarData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListScholarData(options, new Callback<ScholarEntity>() {
            @Override
            public void onResponse(Call<ScholarEntity> call, Response<ScholarEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<ScholarEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListSummarizeData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListSummarizeData(options, new Callback<SubscribeEntity>() {
            @Override
            public void onResponse(Call<SubscribeEntity> call, Response<SubscribeEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<SubscribeEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListNewPaperData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListNewPaperData(options, new Callback<SubscribeEntity>() {
            @Override
            public void onResponse(Call<SubscribeEntity> call, Response<SubscribeEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<SubscribeEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getWaitRecordPaperListData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getWaitRecordPaperListData(options, new Callback<WaitRecordPaperEntity>() {
            @Override
            public void onResponse(Call<WaitRecordPaperEntity> call, Response<WaitRecordPaperEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<WaitRecordPaperEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getPaperBaseByIdData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getPaperBaseByIdData(options, new Callback<PaperDatailEntity>() {
            @Override
            public void onResponse(Call<PaperDatailEntity> call, Response<PaperDatailEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<PaperDatailEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListOfLiteratureData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListOfLiteratureData(options, new Callback<LiteratureEntity>() {
            @Override
            public void onResponse(Call<LiteratureEntity> call, Response<LiteratureEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<LiteratureEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListOfAntistopData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListOfAntistopData(options, new Callback<AntistopEntity>() {
            @Override
            public void onResponse(Call<AntistopEntity> call, Response<AntistopEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<AntistopEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getlistFollowPaperData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getlistFollowPaperData(options, new Callback<SubscribeEntity>() {
            @Override
            public void onResponse(Call<SubscribeEntity> call, Response<SubscribeEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<SubscribeEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListOfQuizAndAnswerData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListOfQuizAndAnswerData(options, new Callback<QAEntity>() {
            @Override
            public void onResponse(Call<QAEntity> call, Response<QAEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<QAEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListFollowedPaperData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListFollowedPaperData(options, new Callback<SubscribeEntity>() {
            @Override
            public void onResponse(Call<SubscribeEntity> call, Response<SubscribeEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<SubscribeEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getSaveRecordData(ArrayMap<String, Object> options,MultipartBody.Part part) {
        ModelFactory.getBaseModel().getSaveRecordData(options,part, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void gethomePageData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().gethomePageData(options, new Callback<HomeEntity>() {
            @Override
            public void onResponse(Call<HomeEntity> call, Response<HomeEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<HomeEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getUserRecordData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getUserRecordData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getUserLogOutData() {
        ModelFactory.getBaseModel().getUserLogOutData(new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getAppInfoData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getAppInfoData(options, new Callback<UpDateAppEntity>() {
            @Override
            public void onResponse(Call<UpDateAppEntity> call, Response<UpDateAppEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<UpDateAppEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getAuthenUserlData(String email,String uid,MultipartBody.Part part) {
        ModelFactory.getBaseModel().getAuthenUserlData(email,uid,part, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
                Log.e("Presenter", "onFailure: "+t.toString() );
            }


        });
    }

    public void getHomeSearchData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getHomeSearchData(options, new Callback<HomeSearchEntity>() {
            @Override
            public void onResponse(Call<HomeSearchEntity> call, Response<HomeSearchEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<HomeSearchEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getUpdatePaperData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getUpdatePaperData(options, new Callback<HomeSearchEntity>() {
            @Override
            public void onResponse(Call<HomeSearchEntity> call, Response<HomeSearchEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<HomeSearchEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getMsgListData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getMsgListData(options, new Callback<MsgEntity>() {
            @Override
            public void onResponse(Call<MsgEntity> call, Response<MsgEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<MsgEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getMsgDelData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getMsgDelData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getQuizHintListData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getQuizHintListData(options, new Callback<QuizHintEntity>() {
            @Override
            public void onResponse(Call<QuizHintEntity> call, Response<QuizHintEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<QuizHintEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getSearchHistoryData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSearchHistoryData(options, new Callback<SearchHistoryEntity>() {
            @Override
            public void onResponse(Call<SearchHistoryEntity> call, Response<SearchHistoryEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<SearchHistoryEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getCleanHistoryData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getCleanHistoryData(options, new Callback<QuizHintEntity>() {
            @Override
            public void onResponse(Call<QuizHintEntity> call, Response<QuizHintEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<QuizHintEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getSaveShareData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getSaveShareData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getQNTokenData(String options) {
        ModelFactory.getBaseModel().getQNTokenData(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getWith3Data(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getWith3Data(options, new Callback<PhoneEntity>() {
            @Override
            public void onResponse(Call<PhoneEntity> call, Response<PhoneEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    PhoneEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<PhoneEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListAnswerData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListAnswerData(options, new Callback<AnswerEntity>() {
            @Override
            public void onResponse(Call<AnswerEntity> call, Response<AnswerEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    AnswerEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<AnswerEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }


        });
    }

    public void getListQuizData(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().getListQuizData(options, new Callback<QuizEntity>() {
            @Override
            public void onResponse(Call<QuizEntity> call, Response<QuizEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    QuizEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<QuizEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void addIntroduction(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().addIntroduction(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void updateIntroduction(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().updateIntroduction(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void deleteIntroduction(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().deleteIntroduction(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void userAuthen(List<MultipartBody.Part> parts) {
        ModelFactory.getBaseModel().userAuthen(parts, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public void selectUser(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().selectUser(options, new Callback<SelectUser>() {
            @Override
            public void onResponse(Call<SelectUser> call, Response<SelectUser> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    SelectUser entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<SelectUser> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }


    // 验证论文密码
    public void checkPaperPwd(ArrayMap<String, Object> options) {
        ModelFactory.getBaseModel().checkPaperPwd(options, new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                if (response.isSuccessful()) {
                    // response.body() 返回 ResponseBody
                    BaseEntity entity = response.body();
                    impl.onSuccess(entity);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                impl.onFailure(call + "", (Exception) t);
            }
        });
    }
}
