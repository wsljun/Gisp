package com.giiisp.giiisp.utils;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 */
public class OkhttpHelper {
    private static volatile OkhttpHelper instance = null;
    OkHttpClient okHttpClient;
    private Looper looper = Looper.getMainLooper();
    private CallBack callBack;
    private Handler handler = new Handler(looper) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (callBack != null) {
                        callBack.onSuccess((String) msg.obj);
                    }
                    break;
                case -1:
                    if (callBack != null) {
                        callBack.onFailure();
                    }
                    break;
            }
        }
    };

    private OkhttpHelper() {
        okHttpClient = new OkHttpClient();
    }

    public static OkhttpHelper getInstance() {
        if (instance == null) {
            synchronized (OkhttpHelper.class) {
                if (instance == null) {
                    instance = new OkhttpHelper();
                }
            }
        }
        return instance;
    }


    public void get(String url, CallBack callBack) {
        this.callBack = callBack;
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = -1;
                msg.obj = e;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //并不是主线 不能跟新ui
                String json = response.body().string();
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        });

    }


    public void post(String url, HashMap<String, String> params) {



    }


    public interface CallBack {

        void onSuccess(String json);

        void onFailure();

    }


}
