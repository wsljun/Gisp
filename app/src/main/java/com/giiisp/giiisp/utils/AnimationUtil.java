
package com.giiisp.giiisp.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseApp;

import java.util.ArrayList;
import java.util.List;

import static android.animation.ObjectAnimator.ofPropertyValuesHolder;

public class AnimationUtil {

    private static final int defaultImgLength = 66;
    private static final int imgLength = 88;
    private static final int MARGIN_TOP = 150;
    //    private static ConcurrentHashMap<View, AnimatorSet> animationMap = new ConcurrentHashMap();
    private static Handler mHandler = new Handler(BaseApp.app.getMainLooper());

    //    public static void cancleAnimation(View v) {
    //        if (animationMap == null) animationMap = new ConcurrentHashMap();
    //        if (animationMap.containsKey(v)) {
    //            AnimatorSet animatorSet = animationMap.get(v);
    //            Log.e("dyr9", "cancleAnimation animator = " + animatorSet.toString());
    //            if (animatorSet != null) animatorSet.cancel();
    //            animationMap.remove(v);
    //        }
    //    }
    //
    //    public static void destroyAnimationMap() {
    //        if (animationMap == null) return;
    //        Iterator<View> it = animationMap.keySet().iterator();
    //        while (it.hasNext()) {
    //            View key = it.next();
    //            AnimatorSet animatorSet = animationMap.get(key);
    //            if (animatorSet != null) animatorSet.cancel();
    //        }
    //        animationMap.clear();
    //        animationMap = null;
    //        if (mHandler != null) mHandler.removeCallbacksAndMessages(null);
    //        mHandler = null;
    //    }
    //
    //    private static void addAnimation(View v, AnimatorSet animatorSet) {
    //        if (animationMap == null) animationMap = new ConcurrentHashMap();
    //        animationMap.put(v, animatorSet);
    //    }

    private static void postAnimation(Runnable runnable) {
        if (mHandler == null)
            mHandler = new Handler(BaseApp.app.getMainLooper());
        //        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(runnable);
    }

    private static void postAnimationDelay(Runnable runnable, long delay) {
        if (mHandler == null)
            mHandler = new Handler(BaseApp.app.getMainLooper());
        //        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(runnable, delay);
    }

    public static void showDetailAnimotion(final Context context,
                                           final ImageView mImageView,
                                           final int x, final int y,
                                           final IAnimationHelper helper,
                                           final boolean detail) {
        if (helper == null || mImageView == null)
            return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                helper.setLayout(x, y);
                int size = mImageView.getLayoutParams().height;
                size = size == 0 ? dip2px(context, defaultImgLength) : size;
                int targetX = detail ? getWidth(context) - dip2px(context, 70) : getWidth(context) - dip2px(context, 40);
                int targetY = dip2px(context, 25);
                int targetSize = detail ? dip2px(context, imgLength) : 0;
                float targetScale = (float) targetSize / (float) size;
                PropertyValuesHolder iconX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, x, targetX);
                PropertyValuesHolder iconY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, y, targetY);
                PropertyValuesHolder iconAlpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0.7f);
                ObjectAnimator animationLocation = ofPropertyValuesHolder(mImageView, iconX, iconY, iconAlpha);
                Log.i("--->>", "run: " + targetScale + View.SCALE_X + View.SCALE_Y);
                PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0.4f);
                PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0.4f);
                ObjectAnimator animationSize = ObjectAnimator.ofPropertyValuesHolder(mImageView, pvhScaleX, pvhScaleY);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setStartDelay(200);
                animatorSet.setDuration(1200);
                animatorSet.playTogether(animationLocation, animationSize);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        helper.onAnimatorEnd(animation);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                animatorSet.start();
            }
        });
    }

    public static void tabLayoutAnimator(final View view, final int visibleState) {
        if (view == null)
            return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                view.animate().cancel();
                boolean show = visibleState == View.VISIBLE;

                ViewPropertyAnimator animator = view.animate()
                        .translationY(show ? 0 : view.getLayoutParams().height)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .setStartDelay(400)
                        .setDuration(400);
                animator.setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        view.setVisibility(visibleState);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                animator.start();
            }
        });
    }

    /*public static void toolbarAnimator(final View view, final int startHeight, final int endHeight) {
        if (view == null) return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator height = ObjectAnimator
                        .ofInt(new MarginProxyAnimator(view), "height", startHeight, endHeight);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setStartDelay(400);
                animatorSet.setDuration(400);
                animatorSet.play(height);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.requestLayout();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        view.getLayoutParams().height = startHeight;
                        view.requestLayout();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                animatorSet.start();
            }
        });
    }*/

    public static void showHorizontalAnimotion(final Context context, final View view,
                                               final long duration, final long delay,
                                               final boolean in, final IAnimationHelper helper) {
        if (helper == null)
            return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                int height = getHeight(context) - getStatusBarHeight(context);
                helper.setLayout(0, height);

                final int screenWidth = getWidth(context);
                final int size = dip2px(context, 60);
                final float oldAlpha = in ? 0.0f : 1.0f;
                final float targetAlpha = in ? 1.0f : 0.0f;
                final int oldX = in ? screenWidth : screenWidth - size;
                final int targetX = in ? screenWidth - size : screenWidth;

                PropertyValuesHolder iconAlpha = PropertyValuesHolder.ofFloat(View.ALPHA,
                        oldAlpha, targetAlpha);
                PropertyValuesHolder iconX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X,
                        oldX, targetX);
                ObjectAnimator animator =
                        ofPropertyValuesHolder(view, iconAlpha, iconX);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setStartDelay(delay);
                animatorSet.setDuration(duration);
                animatorSet.play(animator);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (helper != null)
                            helper.onAnimatorEnd(in);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            }
        });
    }

    public static void showAppsInAnimotion(final Context context, final List<View> views,
                                           final IAnimationHelper helper) {
        if (helper == null)
            return;
        if (views == null || views.size() == 0)
            return;

        postAnimation(new Runnable() {
            @Override
            public void run() {

                List<Animator> animators = new ArrayList<>();
                for (int i = 0; i < views.size(); i++) {
                    final View v = views.get(i);
                    if (v == null)
                        continue;

                    final float x = v.getX();
                    final float y = v.getY();
                    final int defaultSize = dip2px(context, 16);

                    final int[] iconLocation = (int[]) v.getTag();
                    final int targetX = iconLocation[0] + defaultSize;
                    final int targetY = iconLocation[1] + defaultSize;
                    final int targetSize = (int) v.getTag(R.id.tag_app_size);
                    final float tagetScale = (float) targetSize / (float) defaultSize;
                    //                    Log.e("dyr11", " scale = " + tagetScale);

                    PropertyValuesHolder iconX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X,
                            x, targetX);
                    PropertyValuesHolder iconY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y,
                            y, targetY);
                    PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,
                            1f, tagetScale);
                    PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,
                            1f, tagetScale);
                    ObjectAnimator animationLocation =
                            ofPropertyValuesHolder(v, iconX, iconY, pvhScaleX, pvhScaleY);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.addListener(new AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            ViewGroup.LayoutParams params = v.getLayoutParams();
                            params.height = params.width = defaultSize;
                            v.setLayoutParams(params);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    });
                    animatorSet.playTogether(animationLocation);
                    animators.add(animatorSet);
                }

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animators);
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setStartDelay(200);
                animatorSet.setDuration(400);
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (helper != null)
                            helper.onAnimatorEnd(animation);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                animatorSet.start();
            }
        });
    }

    public static void showRightInAnimation(final Activity activity, final View view, final long duration,
                                            final IAnimationHelper helper) {
        if (view == null)
            return;
        postAnimation(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX",
                        getWidth(activity), 0f);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1f);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(duration);
                animatorSet.playTogether(objectAnimator, objectAnimator1);
                animatorSet.addListener(new AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        // TODO Auto-generated method stub
                        if (helper != null)
                            helper.onAnimatorEnd(arg0);
                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                animatorSet.start();
            }
        });
    }

    public static void showShutterAnimation(View view, long delayMillis, IAnimationHelper helper) {
        if (view == null)
            return;
        postAnimationDelay(new MyRunnable(view, helper), delayMillis);
    }

    public static void showBreathAnimation(final View view, final long delay, final boolean end,
                                           final float fromX, final float toX, final float fromY, final float toY) {
        if (view == null)
            return;
        if (!end) {
            Animation animation = view.getAnimation();
            if (animation != null)
                animation.cancel();
            view.clearAnimation();
        }

        postAnimation(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                final ScaleAnimation animation = new ScaleAnimation(fromX, toX, fromY, toY,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(300);// 设置动画持续时间
                animation.setStartOffset(delay);// 执行前的等待时间
                animation.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        // TODO Auto-generated method stub
                        if (end) {
                            view.clearAnimation();
                            animation.setAnimationListener(null);
                            animation.cancel();
                        } else {
                            showBreathAnimation(view, 0, true, toX, fromX, toY, fromY);
                        }
                    }
                });
                view.startAnimation(animation);
            }
        });
    }

    public static void showBottomOutAnimator(final View view, final long duration, final IAnimationHelper helper) {
        if (view == null)
            return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f,
                        -view.getHeight());
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.1f);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(duration);
                animatorSet.playTogether(objectAnimator, objectAnimator1);
                animatorSet.addListener(new AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        // TODO Auto-generated method stub
                        if (helper != null)
                            helper.onAnimatorEnd(arg0);
                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                animatorSet.start();
            }
        });
    }

    public static void showTopInAnimator(final View view, final long duration, final IAnimationHelper helper) {
        if (view == null)
            return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY",
                        -view.getHeight(), 0f);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1f);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(duration);
                animatorSet.playTogether(objectAnimator, objectAnimator1);
                animatorSet.addListener(new AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        // TODO Auto-generated method stub
                        if (helper != null)
                            helper.onAnimatorEnd(arg0);
                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                animatorSet.start();
            }
        });
    }

    public static void showTada(final View view, final float shakeFactor, final IAnimationHelper helper) {
        if (view == null)
            return;
        postAnimation(new Runnable() {
            @Override
            public void run() {
                PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                        Keyframe.ofFloat(0f, 1f),
                        Keyframe.ofFloat(.1f, .9f),
                        Keyframe.ofFloat(.2f, .9f),
                        Keyframe.ofFloat(.3f, 1.1f),
                        Keyframe.ofFloat(.4f, 1.1f),
                        Keyframe.ofFloat(.5f, 1.1f),
                        Keyframe.ofFloat(.6f, 1.1f),
                        Keyframe.ofFloat(.7f, 1.1f),
                        Keyframe.ofFloat(.8f, 1.1f),
                        Keyframe.ofFloat(.9f, 1.1f),
                        Keyframe.ofFloat(1f, 1f)
                );
                PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                        Keyframe.ofFloat(0f, 1f),
                        Keyframe.ofFloat(.1f, .9f),
                        Keyframe.ofFloat(.2f, .9f),
                        Keyframe.ofFloat(.3f, 1.1f),
                        Keyframe.ofFloat(.4f, 1.1f),
                        Keyframe.ofFloat(.5f, 1.1f),
                        Keyframe.ofFloat(.6f, 1.1f),
                        Keyframe.ofFloat(.7f, 1.1f),
                        Keyframe.ofFloat(.8f, 1.1f),
                        Keyframe.ofFloat(.9f, 1.1f),
                        Keyframe.ofFloat(1f, 1f)
                );
                PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                        Keyframe.ofFloat(0f, 0f),
                        Keyframe.ofFloat(.1f, -3f * shakeFactor),
                        Keyframe.ofFloat(.2f, -3f * shakeFactor),
                        Keyframe.ofFloat(.3f, 3f * shakeFactor),
                        Keyframe.ofFloat(.4f, -3f * shakeFactor),
                        Keyframe.ofFloat(.5f, 3f * shakeFactor),
                        Keyframe.ofFloat(.6f, -3f * shakeFactor),
                        Keyframe.ofFloat(.7f, 3f * shakeFactor),
                        Keyframe.ofFloat(.8f, -3f * shakeFactor),
                        Keyframe.ofFloat(.9f, 3f * shakeFactor),
                        Keyframe.ofFloat(1f, 0)
                );
                ObjectAnimator animator = ofPropertyValuesHolder(
                        view, pvhScaleX, pvhScaleY, pvhRotate);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(1000);
                animatorSet.play(animator);
                animatorSet.addListener(new AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        // TODO Auto-generated method stub
                        if (helper != null)
                            helper.onAnimatorEnd(arg0);
                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                animatorSet.start();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showCircularReveal(final View view, final int width, final int height, final int duration) {
        if (view == null)
            return;
        view.clearAnimation();
        view.setVisibility(View.INVISIBLE);

        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                view.removeOnLayoutChangeListener(this);
                postAnimation(new Runnable() {
                    @Override
                    public void run() {
                        Animator animator = ViewAnimationUtils.createCircularReveal(
                                view, 0, 0, 0, (float) Math.hypot(width, height));
                        animator.setInterpolator(new AccelerateInterpolator());
                        animator.setDuration(duration);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);
                                view.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.start();
                    }
                });
            }
        });
    }

    private static int getWidth(Context context) {
        if (context instanceof Activity) {
            DisplayMetrics displaysMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displaysMetrics);

            // 得到屏幕宽
            return displaysMetrics.widthPixels;
        }
        return 0;
    }

    private static int getHeight(Context context) {
        if (context instanceof Activity) {
            DisplayMetrics displaysMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displaysMetrics);
            // 得到屏幕宽
            return displaysMetrics.heightPixels;
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        // dip->px
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                context.getResources().getDisplayMetrics());
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*static class MarginProxyAnimator {
        private View mView;

        public MarginProxyAnimator(View view) {
            this.mView = view;
        }

        public int getHeight() {
            return mView == null ? 0 : mView.getLayoutParams().height;
        }

        public int getWidth() {
            return mView == null ? 0 : mView.getLayoutParams().width;
        }

        public void setWidth(int width) {
            if (mView == null) return;
            ViewGroup.LayoutParams lp = mView.getLayoutParams();
            lp.width = width;
            mView.setLayoutParams(lp);
        }

        public void setHeight(int height) {
            if (mView == null) return;
            ViewGroup.LayoutParams lp = mView.getLayoutParams();
            lp.height = height;
            mView.setLayoutParams(lp);
        }
    }*/

    static class MyRunnable implements Runnable {
        View onetapResultView;
        IAnimationHelper helper;

        public MyRunnable(View onetapResultView, IAnimationHelper helper) {
            this.onetapResultView = onetapResultView;
            this.helper = helper;
        }

        public void run() {
            MyAnimation animation = new MyAnimation(90f, 0f,
                    ((float) (onetapResultView.getWidth() / 2)),
                    ((float) (onetapResultView.getHeight() / 2)), 0f, false, false);
            animation.setFillAfter(true);
            animation.setDuration(400);
            animation.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation arg0) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    // TODO Auto-generated method stub
                    //                    if (helper != null)
                    //                        helper.onAnimationEnd(AnimationUtil.this, arg0);
                }
            });
            onetapResultView.startAnimation(animation);
        }
    }

    static class MyAnimation extends Animation {
        private final float a;
        private final float b;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private final boolean g;
        private Camera h;

        public MyAnimation(float arg1, float arg2, float arg3, float arg4, float arg5,
                           boolean arg6, boolean arg7) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
            this.f = arg6;
            this.g = arg7;
        }

        protected void applyTransformation(float arg10, Transformation arg11) {
            float v8 = 1f;
            float v0 = this.a + (this.b - this.a) * arg10;
            float v1 = this.c;
            float v2 = this.d;
            Camera v3 = this.h;
            Matrix v4 = arg11.getMatrix();
            v3.save();
            if (this.f) {
                v3.translate(0f, 0f, this.e * arg10);
            } else {
                v3.translate(0f, 0f, this.e * (v8 - arg10));
            }

            if (this.g) {
                v3.rotateY(v0);
            } else {
                v3.rotateX(v0);
            }

            v3.getMatrix(v4);
            v3.restore();
            v0 = 0.2f;
            if (!this.f) {
                arg11.setAlpha(v0 + (v8 - v0) * arg10);
            } else {
                arg11.setAlpha(v8 - v0 * arg10);
            }

            v4.preTranslate(-v1, -v2);
            v4.postTranslate(v1, v2);
        }

        public void initialize(int arg2, int arg3, int arg4, int arg5) {
            super.initialize(arg2, arg3, arg4, arg5);
            this.h = new Camera();
        }
    }

    public interface IAnimationHelper {

        void setLayout(int x, int y);

        void onAnimatorEnd(Object animation);
    }

}
