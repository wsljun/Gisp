package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.Log;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.AttentionFragment;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.MViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择订阅的关键字 领域 学者
 */
public class AttentionActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseImpl, TabLayout.OnTabSelectedListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tl_my_attention)
    TabLayout tlMySubscription;
    @BindView(R.id.iv_contacts_attention)
    ImageView ivContacts;
    @BindView(R.id.vp_my_attention)
    MViewPager vpMySubscription;
    @BindView(R.id.linear_layout_guide)
    LinearLayout linearLayoutGuide;
    String type;
    private ArrayList<BaseFragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_attention;
    }

    public MViewPager getVpMySubscription() {
        return vpMySubscription;
    }

    public void setVpMySubscription(MViewPager vpMySubscription) {
        this.vpMySubscription = vpMySubscription;
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, AttentionActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Context context, String type) {
        Intent sIntent = new Intent(context, AttentionActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        sIntent.putExtra("type", type);
        context.startActivity(sIntent);
    }

    @Override
    public void initData() {
        super.initData();
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "first":
                linearLayoutGuide.setVisibility(View.VISIBLE);
                break;
            case "mine":
                break;
        }
        Log.i("--->>", "initData");

        //        presenter.getPortraitData("152A293601289D8B18D9155DF1C6EB44");
    }

    @Override
    public void initView() {
        List<String> titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add(getString(R.string.field));
        titleList.add(getString(R.string.keyword));
        titleList.add(getString(R.string.scholars));
        fragmentList.add(AttentionFragment.newInstance(type, "1"));
        fragmentList.add(AttentionFragment.newInstance(type, "2"));
        fragmentList.add(AttentionFragment.newInstance(type, "3"));
        vpMySubscription.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList));
        tlMySubscription.setupWithViewPager(vpMySubscription);
        vpMySubscription.setCurrentItem(0);
        vpMySubscription.setOffscreenPageLimit(2);
        tlMySubscription.addOnTabSelectedListener(this);

    }


    @OnClick({R.id.iv_close, R.id.tv_attention, R.id.tv_home, R.id.iv_back, R.id.iv_contacts_attention})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_attention:
                linearLayoutGuide.setVisibility(View.GONE);
                getVpMySubscription().setCurrentItem(0);
                break;
            case R.id.iv_close:
            case R.id.tv_home:
                GiiispActivity.actionActivity(this, "1");
                finish();
                break;
            case R.id.iv_back:
                switch (type) {
                    case "first":
                        GiiispActivity.actionActivity(this, "1");
                        finish();
                        break;
                    case "mine":
                        finish();
                        break;
                }

                break;
            case R.id.iv_contacts_attention:
                break;
        }
    }


    @Override
    public void onSuccess(BaseEntity entity) {

    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                tlMySubscription.setBackgroundResource(R.mipmap.guide_tab_left);
                break;
            case 1:
                tlMySubscription.setBackgroundResource(R.mipmap.guide_tab_center);
                break;
            case 2:
                tlMySubscription.setBackgroundResource(R.mipmap.guide_tab_right);
                break;
        }
        for (BaseFragment baseFragment : fragmentList) {
            baseFragment.attention();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

