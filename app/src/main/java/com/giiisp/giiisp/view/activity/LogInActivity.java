package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.utils.AppManager;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.AgreementFragment;
import com.giiisp.giiisp.view.fragment.LoginFragment;
import com.giiisp.giiisp.view.fragment.LoginHintFragment;
import com.giiisp.giiisp.view.fragment.RegisterFragment;
import com.giiisp.giiisp.view.fragment.RetrievePwdFragment;
import com.giiisp.giiisp.widget.MViewPager;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 * 登录界面
 * Created by Thinkpad on 2017/4/28.
 */

public class LogInActivity extends BaseActivity {


    @BindView(R.id.vp_login)
    MViewPager vpLogin;
    private int from;
    private int agreement;

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
        Intent sIntent = new Intent(context, LogInActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public MViewPager getVpLogin() {
        return vpLogin;
    }


    @Override
    public void initView() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new LoginHintFragment());
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        fragments.add(new RetrievePwdFragment());
        fragments.add(AgreementFragment.newInstance("login_webView",""));
        vpLogin.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments,null));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (vpLogin.getCurrentItem()) {
                case 0:
                    AppManager.getAppManager().AppExit(this);
                    return true;
                case 1:
                    vpLogin.setCurrentItem(0);
                    return true;
                case 2:
                    vpLogin.setCurrentItem(from);
                    return true;
                case 3:
                    vpLogin.setCurrentItem(1);
                    return true;
                case 4:
                    vpLogin.setCurrentItem(agreement);
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


}
