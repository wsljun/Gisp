package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;

/**
 *  全屏Activity
 * Created by Thinkpad on 2017/6/5.
 */

public class FullscreenActivity extends BaseActivity{
    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_fullscreen;
    }
    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, FullscreenActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(params);
//
    }

    @Override
    public void initView() {
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                //                            linearLayoutMain.scrollTo(100,100);
            }
        });
       /* WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(params);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);*/
    }

}
