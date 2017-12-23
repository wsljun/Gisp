package com.giiisp.giiisp.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * 主界面 底部切换tab工具类
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    //    public static final int DEFAULT_POSTION = 0;
    private List<Fragment> fragments; // 一个tab页面对应一个Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentManager fragmentManager; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab; // 当前Tab页面索引
    private int lastTab; // 上级Tab页面索引
    private Activity context; // 当前Tab页面索引
    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能

    /**
     * @param fragmentManager
     * @param fragments
     * @param fragmentContentId
     * @param rgs
     */
    public FragmentTabUtils(FragmentManager fragmentManager, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentManager = fragmentManager;
        this.fragmentContentId = fragmentContentId;
        rgs.setOnCheckedChangeListener(this);
    }

    public FragmentTabUtils(Activity context, FragmentManager fragmentManager, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.context = context;
        this.rgs = rgs;
        this.fragmentManager = fragmentManager;
        this.fragmentContentId = fragmentContentId;
        rgs.setOnCheckedChangeListener(this);
    }


    public FragmentTabUtils setDefaultPostion(int postion) {
        if (postion < rgs.getChildCount()) {
            ((RadioButton) rgs.getChildAt(1)).setChecked(true);
            rgs.setOnCheckedChangeListener(this);
            ((RadioButton) rgs.getChildAt(postion)).setChecked(true);
        }
        return this;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for (int i = 0; i < rgs.getChildCount(); i++) {
            if (rgs.getChildAt(i).getId() == checkedId) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);
                //                getCurrentFragment().onPause(); // 暂停当前tab
                getCurrentFragment().onStop(); // 暂停当前tab
                Log.i("--->>", "onCreate: 22 " + fragment.getId() + "  " + fragment.isAdded() + "  " + fragment);
                if (fragment.isAdded()) {
                    fragment.onStart(); // 启动目标tab的fragment onStart()

                    //                    Fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    int id = fragment.getId();
                    if (id != fragmentContentId)
                        ft.add(fragmentContentId, fragment);
                    ft.commitAllowingStateLoss();
                }
                showTab(i); // 显示目标tab
                // 如果设置了切换tab额外功能功能接口
                if (null != onRgsExtraCheckedChangedListener) {
                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
                }
            }
        }

    }

    public int getLastTab() {
        return lastTab;
    }

    public void setLastTab(int lastTab) {
        this.lastTab = lastTab;
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
        lastTab = currentTab;
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // 设置切换动画
        //        if (index > currentTab) {
        //            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        //        } else {
        //            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        //        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
        return onRgsExtraCheckedChangedListener;
    }

    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
    }

    /**
     * 切换tab额外功能功能接口
     */
    public static interface OnRgsExtraCheckedChangedListener {
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index);
    }
}