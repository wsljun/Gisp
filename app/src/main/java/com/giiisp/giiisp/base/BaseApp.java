package com.giiisp.giiisp.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.giiisp.giiisp.entity.DaoMaster;
import com.giiisp.giiisp.entity.DaoSession;
import com.giiisp.giiisp.net.NetworkStateReceiver;
import com.giiisp.giiisp.utils.Log;
import com.giiisp.giiisp.widget.recording.AppCache;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.greendao.database.Database;

import zlc.season.rxdownload2.RxDownload;


/**
 * 修改备注：
 * 1>初始化全局配置,第三方的sdk
 * 2>初始化第三方的SDK
 * 3>初始化通用框架
 * 全局异常处理
 * 友盟统计有全局异常的处理
 */

public class BaseApp extends Application {
    public static BaseApp app;

    /* private NetChangeObserver observer = new NetChangeObserver() {

         @Override
         public void onConnect(NetWorkUtil.NetType type) {
             super.onConnect(type);

             //表示已连接
             switch (type) {
                 case NETWORK_WIFI:
                     //表示wifi
                     //                    Toast.makeText(BaseApp.this, "WIFI已连接", Toast.LENGTH_SHORT).show();
                     break;
                 default:
                     Toast.makeText(BaseApp.this, "2/3/4G网络已连接", Toast.LENGTH_SHORT).show();
                     break;
             }

         }

         @Override
         public void onDisConnect() {
             super.onDisConnect();
             //表示网络已断开
             //            Toast.makeText(BaseApp.this, "网络已断开", Toast.LENGTH_SHORT).show();
         }
     };*/
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AppCache.init(this);
        initCloudChannel(this);
//        UMShareAPI.get(this);
        UMShareAPI.init(this,"594a0f71a40fa31919001163");
        initNetState();

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setDebugMode(true);
        RxDownload.getInstance(this)
                .defaultSavePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Giiisp/download") //设置默认的下载路径
                .maxThread(3)                     //设置最大线程
                .maxRetryCount(3)                 //设置下载失败重试次数
                .maxDownloadNumber(3);             //Service同时下载数量

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("--->>", "init cloudchannel success" + response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d("--->>", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    private void initNetState() {
        //动态注册网络监听
        NetworkStateReceiver.registerNetworkStateReceiver(this);

    }
 /*   */

    /**
     * 程序结束的位置 注销监听
     *//*
    public void unRegisterNetworkStateReceiver() {
        NetworkStateReceiver.unRegisterNetworkStateReceiver(this);
        if (observer != null) {
            NetworkStateReceiver.removeRegisterObserver(observer);
        }
20f13353b5df231b382fefea229442e1
    }*/
    {
        Config.DEBUG = false;

        PlatformConfig.setWeixin("wxbc54fb482164e3f9", "b3c249a0f0cb298c2c520f0e4f5b233a");
        PlatformConfig.setQQZone("1106127773", "s7s8AKzC4E4i6ZpQ");
        PlatformConfig.setSinaWeibo("126663232","d39969613faa5fcc75859cf8406649eb","http://sns.whalecloud.com");
    }

}
