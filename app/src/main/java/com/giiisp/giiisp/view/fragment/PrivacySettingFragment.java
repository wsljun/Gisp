package com.giiisp.giiisp.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 隐私设置 (1.0不用)
 * Created by Thinkpad on 2017/5/31.
 */

public class PrivacySettingFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_my_data)
    TextView tvMyData;
    @BindView(R.id.tv_my_emails)
    TextView tvMyEmails;
    @BindView(R.id.tv_my_phone)
    TextView tvMyPhone;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_privacysettings;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.privacy_settings);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.fl_my_data, R.id.fl_my_emails, R.id.fl_my_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getSettingActivity().getVpLogin().setCurrentItem(0);
                break;
            case R.id.fl_my_data:
                getSettingActivity().getVpLogin().setCurrentItem(10);
                break;
            case R.id.fl_my_emails:
                getSettingActivity().getVpLogin().setCurrentItem(10);
                break;
            case R.id.fl_my_phone:
                getSettingActivity().getVpLogin().setCurrentItem(10);
                break;
        }
    }
}
