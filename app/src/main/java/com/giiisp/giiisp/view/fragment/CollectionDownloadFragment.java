package com.giiisp.giiisp.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * 下载和收藏页面的fragment
 * Created by Thinkpad on 2017/5/27.
 */

public class CollectionDownloadFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {

    @BindView(R.id.tv_home_news)
    ImageView tvHomeNews;
    @BindView(R.id.fl_news)
    FrameLayout flNews;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_rt)
    TextView tvTitleRt;
    @BindView(R.id.tv_download_number)
    TextView tvDownloadNumber;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_news_spot)
    ImageView tvNewsSpot;
    @BindView(R.id.rl_banner)
    RelativeLayout rlBanner;
    @BindView(R.id.line_banner)
    FrameLayout lineBanner;
    private int downloadNunber;
    List<BaseFragment> fragments = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDownloadNunber();
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

    /*    map.put("uid",uid);
            map.put("pape",1);
            map.put("upTime","desc");
            map.put("isOneOrTwo",1);
            presenter.getListFollowedPaperData(map);*/
    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_download;
    }

    public static CollectionDownloadFragment newInstance(String param1, String param2) {
        CollectionDownloadFragment fragment = new CollectionDownloadFragment();
        Bundle args = new Bundle();
        args.putString("1005", param1);
        args.putString("1006", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void loadDownloadNunber() {
        RxDownload.getInstance(getContext()).getTotalDownloadRecords()
                .map(new Function<List<DownloadRecord>, List<String>>() {
                    @Override
                    public List<String> apply(List<DownloadRecord> downloadRecords) throws Exception {
                        List<String> missionIds = new ArrayList<>();
                        for (DownloadRecord each : downloadRecords) {
                            if (each.getFlag() != DownloadFlag.COMPLETED && each.getExtra1() != null && !missionIds.contains(each.getExtra1())) {
                                missionIds.add(each.getExtra1());
                            }
                        }
                        return missionIds;
                    }
                })
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> downloadBeen) throws Exception {
                        downloadNunber = downloadBeen.size();
                        if (downloadNunber != 0) {
                            tvDownloadNumber.setText("" + downloadNunber);
                        } else {
                            tvDownloadNumber.setText("");
                        }
                        if (type.equals("download")) {
                            if (getParentFragment() != null && getParentFragment() instanceof CollectionDownloadFragment) {
                                if (downloadNunber != 0) {
                                    ((CollectionDownloadFragment) getParentFragment()).setUpTabBadge("" + downloadNunber);
                                } else {
                                    ((CollectionDownloadFragment) getParentFragment()).setUpTabBadge("");
                                }
                            }
                        }
                        Log.i("--->>11", "loadDownloadNunber: " + downloadNunber);
                    }
                });
        Log.i("--->>22", "loadDownloadNunber: " + downloadNunber);

    }

    @Override
    public void initData() {
        super.initData();
        if (getArguments() == null) {
            throw new NullPointerException("Arguments is null!!!");
        }
        type = getArguments().getString("1005");
        String string = getArguments().getString("1006");

    }

    @Override
    public void initView() {
        List<String> mTitles = new ArrayList<>();

        switch (type) {
            case "download_activity":
                tabLayout.setupWithViewPager(viewPager);
                tvTitle.setText(R.string.download);
                ivMenu.setVisibility(View.VISIBLE);
                ivMenu.setImageResource(R.mipmap.download_collection_icon);
                fragments.add(BannerRecyclerViewFragment.newInstance("paper_download", "0"));
                //                fragments.add(BannerRecyclerViewFragment.newInstance("summary_download", "1"));
                fragments.add(BannerRecyclerViewFragment.newInstance("download", "2"));
                viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments, mTitles));

                initTabBadge();
                break;
            case "collection_fragment":
                lineBanner.setVisibility(View.GONE);
                rlBanner.setVisibility(View.VISIBLE);

                //                map.put("isOneOrTwo", 2);
                //                presenter.getListFollowedPaperData(map);
                tvTitleRt.setText(R.string.collection);
                tvBack.setVisibility(View.GONE);
                ivMenu.setImageResource(R.mipmap.collection_download);
                ivMenu.setVisibility(View.VISIBLE);
                tabLayout.setupWithViewPager(viewPager);
                fragments.add(BannerRecyclerViewFragment.newInstance("collection_paper", "0"));
                fragments.add(BannerRecyclerViewFragment.newInstance("collection_summary", "1"));
                mTitles.add(getString(R.string.paper));
                mTitles.add(getString(R.string.summary));
                viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments, mTitles));

                break;
        }


    }

    public void setUpTabBadge(String nunber) {
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        View customView = null;
        if (tab != null) {
            customView = tab.getCustomView();
        }
        TextView spot = null;
        if (customView != null) {
            spot = (TextView) customView.findViewById(R.id.tv_download_spot);
            if (nunber.equals("-1")) {
                Integer integer = Integer.valueOf(spot.getText().toString());
                spot.setText(String.valueOf(integer - 1));
            }
            spot.setText(nunber);
        }
        Log.i("--->", "setUpTabBadge: " + nunber);
    }

    /**
     * 设置Tablayout上的标题的角标
     */
    private void initTabBadge() {

        // 1. 最简单
        //        for (int i = 0; i < mFragmentList.size(); i++) {
        //            mBadgeViews.get(i).setTargetView(((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(i));
        //            mBadgeViews.get(i).setText(formatBadgeNumber(mBadgeCountList.get(i)));
        //        }

        // 2. 最实用
        for (int i = 0; i < 2; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);

            // 更新Badge前,先remove原来的customView,否则Badge无法更新
            View customView = null;
            if (tab == null) {
                return;
            }

            customView = tab.getCustomView();
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(customView);
                }
            }

            // 更新CustomView
            tab.setCustomView(getTabItemView(i));
        }

        // 需加上以下代码,不然会出现更新Tab角标后,选中的Tab字体颜色不是选中状态的颜色
        //        mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition()).getCustomView().setSelected(true);
    }


    public View getTabItemView(int position) {
        String[] tabTitle = new String[2];
        tabTitle[0] = getString(R.string.download_completes);
        tabTitle[1] = getString(R.string.downloading);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_tab_download, tabLayout, false);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        TextView spot = (TextView) view.findViewById(R.id.tv_download_spot);
        textView.setText(tabTitle[position]);
        if (position == 1) {
            spot.setVisibility(View.VISIBLE);
        }
        return view;
    }


    @OnClick({R.id.tv_home_news, R.id.tv_back, R.id.fl_menu, R.id.iv_play, R.id.iv_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_news:
                FragmentActivity.actionActivity(getContext(), "news");
                break;
            case R.id.fl_menu:
                if (Objects.equals(type, "collection_fragment")) {
                    FragmentActivity.actionActivity(getContext(), "download_activity");
                } else if (Objects.equals(type, "download_activity")) {
                    EventBus.getDefault().post("collection");
                    getActivity().finish();
                }
                break;
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

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(int nunber) {
        Log.i("--->>", "onMessage:100 ");
        setUpTabBadge(String.valueOf(-1));
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void addDownload() {
        Log.i("--->>", "addDownload: ");
        String text = tvDownloadNumber.getText().toString();
        Integer integer;
        if (TextUtils.isEmpty(text)) {
            integer = 1;
        } else {
            integer = Integer.valueOf(text) + 1;
        }
        tvDownloadNumber.setText(String.valueOf(integer));
        super.addDownload();
    }

    @Override
    public void collection(int id, int integer, String type, String isFollowed, int parentPosition, int position) {
        for (BaseFragment fragment : fragments) {
            if (fragment.getType().equals(type))
                fragment.collection(id, integer, type, isFollowed, parentPosition, position);


        }
    }

    @Override
    public void downloadCompleted() {
        super.downloadCompleted();
        for (BaseFragment fragment : fragments) {
            fragment.downloadCompleted();
        }
    }
}
