package com.giiisp.giiisp.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于集思谱
 * Created by Thinkpad on 2017/5/4.
 */

public class AboutGiiispFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_versionName)
    TextView tvVersionName;


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_aboutgiiisp;
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.about_giiisp));
        tvVersionName.setText("" + "v" + Utils.getAppVersion());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_back, R.id.tv_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getSettingActivity().getVpLogin().setCurrentItem(0, false);
                break;
            case R.id.tv_feedback:
                getSettingActivity().getVpLogin().setCurrentItem(8, false);

                break;
        }
    }
}


