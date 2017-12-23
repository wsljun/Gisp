package com.giiisp.giiisp.view.fragment;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;

import butterknife.OnClick;

/**
 * 认证完成页面
 * Created by Thinkpad on 2017/5/8.
 */

public class VerifiedCompleteFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_verifiedcomplete;
    }

    @Override
    public void initView() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        getActivity().finish();
    }
}