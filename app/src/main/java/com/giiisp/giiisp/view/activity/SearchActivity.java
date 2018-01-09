package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.KeyBoardUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.BannerRecyclerViewFragment;
import com.giiisp.giiisp.view.impl.BaseImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索的页面
 * Created by Thinkpad on 2017/5/18.
 */

public class SearchActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseImpl {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<BaseFragment> fragments = new ArrayList<>();
    private String searchContent;

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_search;
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, SearchActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void initSearch(String data) {
        EventBus.getDefault().post(data);
        searchContent = data;
        viewPager.setCurrentItem(1);

    }

    @Override
    public void initView() {

        fragments.add(BannerRecyclerViewFragment.newInstance("search_hint", "0"));
        fragments.add(BannerRecyclerViewFragment.newInstance("search_result", "1"));
        fragments.add(BannerRecyclerViewFragment.newInstance("search_scholar", "2"));
        fragments.add(BannerRecyclerViewFragment.newInstance("search_paper", "3"));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, null));
        viewPager.setOffscreenPageLimit(3);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    KeyBoardUtils.closeKeybord(etSearch, SearchActivity.this);
                    searchContent = etSearch.getText().toString();
                    if (!TextUtils.isEmpty(searchContent)) {
                        EventBus.getDefault().post(searchContent);
                        viewPager.setCurrentItem(1);

                    } else {
                        Utils.showToast(R.string.please_input_content);
                    }

                    return true;
                }
                return false;

            }

        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    return super.onKeyDown(keyCode, event);
                case 1:
                    viewPager.setCurrentItem(0, false);
                    return true;
                case 2:
                    viewPager.setCurrentItem(1, false);
                    return true;
                case 3:
                    viewPager.setCurrentItem(1, false);
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (entity.getResult() == 1) {
            Fragment fragment = fragments.get(1);
            if (fragment instanceof BannerRecyclerViewFragment) {
                ((BannerRecyclerViewFragment) fragment).onMessageSearch(searchContent);
                //                fragment.
            }
        }
    }

    public void submitFollow(String isFollowed, String id) {
        ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("token", token);
        map.put("oid", id);
        map.put("uid", uid);
        switch (isFollowed) {
            case "1": //getIsFollowed 1 未关注， 0 已关注
                presenter.getSaveFollowUserData(map);
                break;
            case "0":
            case "2":
                presenter.getCancelFollowUserData(map);
                break;
        }


    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }
}
