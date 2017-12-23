package com.giiisp.giiisp.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.MViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 * 带有tabLayout的页面
 * Created by Thinkpad on 2017/5/19.
 */

public class TabLayoutFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, TabLayout.OnTabSelectedListener {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    MViewPager viewPager;
    @BindView(R.id.tv_download_number)
    TextView tvDownloadNumber;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    private String type;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_qanewsmine;
    }

    public static TabLayoutFragment newInstance(String param1, String param2) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putString("1003", param1);
        args.putString("1004", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        super.initData();
        if (getArguments() == null) {
            throw new NullPointerException("Arguments is null!!!");
        }
        type = getArguments().getString("1003");
        String string = getArguments().getString("1004");

    }

    @Override
    public void initView() {
        List<BaseFragment> list = new ArrayList<>();
        List<String> mTitles = new ArrayList<>();

        switch (type) {
            case "subscribe":

                mTitles.add(getString(R.string.subscribe));
                mTitles.add(getString(R.string.newest));
                list.add(BannerRecyclerViewFragment.newInstance("subscribe", ""));
                list.add(BannerRecyclerViewFragment.newInstance("newest", ""));
                break;
            case "news":
                tvBack.setVisibility(View.VISIBLE);
                tvDownloadNumber.setVisibility(View.GONE);
                ivDownload.setVisibility(View.GONE);
                ivPlay.setVisibility(View.GONE);
                mTitles.add(getString(R.string.notice));
                mTitles.add(getString(R.string.interactive));


                list.add(BannerRecyclerViewFragment.newInstance("notice", ""));
                list.add(BannerRecyclerViewFragment.newInstance("interactive", ""));
                break;
            case "qa":

                tvBack.setVisibility(View.VISIBLE);
                tvDownloadNumber.setVisibility(View.GONE);
                ivDownload.setVisibility(View.GONE);
                ivPlay.setVisibility(View.GONE);
                mTitles.add(getString(R.string.answer));
                mTitles.add(getString(R.string.put_question));
                list.add(BannerRecyclerViewFragment.newInstance("answer", ""));
                list.add(BannerRecyclerViewFragment.newInstance("questions", ""));
                break;


        }
        tabLayout.addOnTabSelectedListener(this);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), list, mTitles));
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                tabLayout.setBackgroundResource(R.mipmap.tab_left);
                break;
            case 1:
                tabLayout.setBackgroundResource(R.mipmap.tab_right);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @OnClick({R.id.tv_back, R.id.iv_play, R.id.iv_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getActivity().finish();
                break;
            case R.id.iv_play:
                FragmentActivity.actionActivity(getActivity(), "play");
                break;
            case R.id.iv_download:
                FragmentActivity.actionActivity(getContext(), "download_activity");
                break;
        }
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        switch (type) {
            case "subscribe":

            case "news":

                break;
            case "qa":

                break;


        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }
}
