package com.giiisp.giiisp.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 账号与安全页面
 * Created by Thinkpad on 2017/5/4.
 */

public class AccountSecurityFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_accountsecurity;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.account_security);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageUserInfo(UserInfoEntity userInfo) {
        if (tvPhoneNumber == null)
            return;
        Log.i("--->>", "onMessageUserInfo: " + userInfo);
        if (Utils.checkMobileNumber(userInfo.getUserInfo().getMobile())) {
            tvPhoneNumber.setText(userInfo.getUserInfo().getMobile());
        } else {
            tvPhoneNumber.setText(R.string.not_binding_mobile);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.fl_change_password, R.id.fl_binding_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getSettingActivity().getVpLogin().setCurrentItem(0, false);
                break;
            case R.id.fl_change_password:
                getSettingActivity().getVpLogin().setCurrentItem(3, false);
                break;
            case R.id.fl_binding_phone:
                getSettingActivity().getVpLogin().setCurrentItem(4, false);
                break;
        }
    }

    public void onSuccess(BaseEntity entity) {

    }
}
