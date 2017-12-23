package com.giiisp.giiisp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseApp;
import com.giiisp.giiisp.model.GlideApp;


/**
 * Created by root on 16-7-7.
 */
public class FloatingManager implements View.OnTouchListener {

    private static FloatingManager mInstance;

    private LayoutInflater mLayoutInflater;
    private WindowManager mWindowManager;
    private Activity mContext;
    private View mFloatWidgetView;
    private MyHandler mHandler;

    private FloatingManager(Activity context) {
        mHandler = new MyHandler();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static FloatingManager getInstance(Activity context) {
        if (mInstance == null) {
            mInstance = new FloatingManager(context);
        }
        return mInstance;
    }

    /**
     * 是否已经显示符窗
     *
     * @return
     */
    public boolean isWidgetShowing() {
        return mFloatWidgetView != null;
    }

    /**
     * 添加下载动画
     *
     * @param v
     * @param url
     */
    public void showDownloadAnimator(final View v, final String url,final boolean detail) {
        if (isWidgetShowing())
            return;

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (isWidgetShowing())
                    return;
                if (v == null || url == null || mContext == null)
                    return;

                int[] location = new int[]{0, 0};
                View parent = (View) v.getParent();
                if (parent == null)
                    return;
                ImageView icon = (ImageView) parent.findViewById(R.id.iv_icon);
                Log.i("--->>", "run: " + icon);
                if (icon == null)
                    return;
                icon.getLocationOnScreen(location);

                mFloatWidgetView = mLayoutInflater.inflate(R.layout.item_floating_icon, (ViewGroup) mContext.findViewById(android.R.id.content), false);
                mFloatWidgetView.setOnTouchListener(FloatingManager.this);
                final ImageView floatingIcon = (ImageView) mFloatWidgetView.findViewById(R.id.floating_icon);

//                ImageLoader.getInstance().displayCricleImage(BaseApp.app.getApplicationContext(), url, floatingIcon);
                GlideApp
                        .with(BaseApp.app.getApplicationContext())
                        .load(url)
                        //.centerCrop()//网友反馈，设置此属性可能不起作用,在有些设备上可能会不能显示为圆形。
                        .transform(new GlideCircleTransform(BaseApp.app.getApplicationContext()))
                        .into(floatingIcon);
                floatingIcon.setVisibility(View.GONE);

                AnimationUtil.IAnimationHelper helper = new AnimationUtil.IAnimationHelper() {
                    @Override
                    public void setLayout(int x, int y) {
                        floatingIcon.setTranslationX(x);
                        floatingIcon.setTranslationY(y);
                        RelativeLayout.LayoutParams params =
                                (RelativeLayout.LayoutParams) floatingIcon.getLayoutParams();
                        params.width = params.height = DensityUtils.dp2px(mContext, 40f);
                        floatingIcon.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimatorEnd(Object animation) {
                        closeFloatWidget();
                    }
                };

                WindowManager.LayoutParams sLayoutParams = new WindowManager.LayoutParams();
              /*  if (Util.isMiui()) {
                    sLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                    sLayoutParams.format = PixelFormat.RGBA_8888;
                    sLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                    sLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    sLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                    sLayoutParams.gravity = 83;
                } else {*/
                sLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                sLayoutParams.format = PixelFormat.RGBA_8888;
                sLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_FULLSCREEN;
                sLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                sLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                sLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                sLayoutParams.x = 0;
                sLayoutParams.y = 0;
                //                }
                mWindowManager.addView(mFloatWidgetView, sLayoutParams);
                AnimationUtil.showDetailAnimotion(mContext, floatingIcon,
                        location[0], location[1], helper, detail);
            }
        });
    }


    /**
     * 关闭符窗
     */
    public void closeFloatWidget() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (isWidgetShowing()) {
                    mWindowManager.removeView(mFloatWidgetView);
                    mFloatWidgetView = null;
                }
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    private static class MyHandler extends Handler {

        private static final int MSG_CLOSE_SELF = 0;

        public MyHandler() {
            super(BaseApp.app.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case MSG_CLOSE_SELF:
                    if (mInstance != null) {
                        mInstance.closeFloatWidget();
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        public void closeSelf() {
            removeMessages(MSG_CLOSE_SELF);
            sendEmptyMessageDelayed(MSG_CLOSE_SELF, 3000);
        }
    }
}
