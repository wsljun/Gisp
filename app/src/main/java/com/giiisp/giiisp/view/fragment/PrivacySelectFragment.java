package com.giiisp.giiisp.view.fragment;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * 资料设置页面 (1.0不用)
 * Created by Thinkpad on 2017/5/31.
 */

public class PrivacySelectFragment extends BaseFragment {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rb_everyone)
    RadioButton rbEveryone;
    @BindView(R.id.rb_friend)
    RadioButton rbFriend;
    @BindView(R.id.rb_me)
    RadioButton rbMe;
    @BindView(R.id.rg_privacy_select)
    RadioGroup rgPrivacySelect;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_privacyselect;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.look_profile);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        getSettingActivity().getVpLogin().setCurrentItem(9);
    }
}
