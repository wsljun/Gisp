package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.AboutGiiispFragment;
import com.giiisp.giiisp.view.fragment.AccountSecurityFragment;
import com.giiisp.giiisp.view.fragment.FeedbackFragment;
import com.giiisp.giiisp.view.fragment.ModifyPasswordFragment;
import com.giiisp.giiisp.view.fragment.ModifyPhoneFragment;
import com.giiisp.giiisp.view.fragment.NotificationSettingFragment;
import com.giiisp.giiisp.view.fragment.SettingFragment;
import com.giiisp.giiisp.view.fragment.SystemSettingFragment;
import com.giiisp.giiisp.view.fragment.UserInfoFragment;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.MViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设置页面
 * Created by Thinkpad on 2017/5/4.
 */

public class SettingActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseImpl ,ViewPager.OnPageChangeListener {


    @BindView(R.id.vp_login)
    MViewPager vpLogin;
    private int from;
    private int agreement;
    private SettingFragment settingFragment;
    List<BaseFragment> fragments = new ArrayList<>();


    public int getAgreement() {
        return agreement;
    }

    public void setAgreement(int agreement) {
        this.agreement = agreement;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_login;
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, SettingActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public MViewPager getVpLogin() {
        return vpLogin;
    }


    @Override
    public void initView() {

        ArrayMap<String, Object> userMap = new ArrayMap<>();
        //        userMap.put("token", "A760880003E7DDEDFEF56ACB3B09697F");
//        userMap.put("token", token);
        //        userMap.put("oid", 1);
        userMap.put("uid", uid);
        presenter.getUserInfoData(userMap);
    }

    @Override
    public void initData() {
        fragments.add(new SettingFragment());
        fragments.add(UserInfoFragment.newInstance("setting_edit_info",""));
        fragments.add(new AccountSecurityFragment());
        fragments.add(new ModifyPasswordFragment());
        fragments.add(new ModifyPhoneFragment());
        fragments.add(new NotificationSettingFragment());
        fragments.add(new SystemSettingFragment());
        fragments.add(new AboutGiiispFragment());
        fragments.add(new FeedbackFragment());
        //        fragments.add(new PrivacySettingFragment());
        //        fragments.add(new PrivacySelectFragment());
        //                vpLogin.setOffscreenPageLimit(8);
        vpLogin.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, null));
        vpLogin.addOnPageChangeListener(this);
        super.initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (vpLogin.getCurrentItem()) {
                case 0:
                    return super.onKeyDown(keyCode, event);
                case 1:
                case 2:
                case 5:
                case 6:
                case 7:
                case 9:
                    vpLogin.setCurrentItem(0, false);
                    return true;
                case 3:
                    vpLogin.setCurrentItem(2, false);
                case 4:
                    vpLogin.setCurrentItem(2, false);
                    return true;
                case 8:
                    vpLogin.setCurrentItem(7, false);
                    return true;
                case 10:
                    vpLogin.setCurrentItem(9, false);
                    return true;

            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onSuccess(BaseEntity entity) {
        for (BaseFragment fragment : fragments) {
            if (fragment instanceof UserInfoFragment) {
                ((UserInfoFragment) fragment).onMessageUserInfo((UserInfoEntity) entity);
            }
            if (fragment instanceof SettingFragment) {
                ((SettingFragment) fragment).onSuccess(entity);
            }
            if (fragment instanceof AccountSecurityFragment) {
                ((AccountSecurityFragment) fragment).onMessageUserInfo((UserInfoEntity) entity);
            }
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
            case 1:
            case 2:
                initView();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
