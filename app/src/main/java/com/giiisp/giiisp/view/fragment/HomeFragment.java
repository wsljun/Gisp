package com.giiisp.giiisp.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.HomeEntity;
import com.giiisp.giiisp.entity.UpDateAppEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.FragmentActivity;
import com.giiisp.giiisp.view.activity.GiiispActivity;
import com.giiisp.giiisp.view.activity.LogInActivity;
import com.giiisp.giiisp.view.activity.SearchActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.MultipleItemQuickAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.UpdatePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.giiisp.giiisp.R.id.recycler_view;
import static com.giiisp.giiisp.base.BaseActivity.uid;
//import static com.giiisp.giiisp.base.BaseActivity.token;

/**
 * home页面
 * Created by Thinkpad on 2017/5/19.
 */

public class HomeFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, SwipeRefreshLayout.OnRefreshListener {
    @BindView(recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_news_spot)
    ImageView tvNewsSpot;
    @BindView(R.id.tv_home_news)
    TextView tvHomeNews;
    @BindView(R.id.tv_home_search)
    TextView tvHomeSearch;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private MultipleItemQuickAdapter multipleItemQuickAdapter;
    private String string;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_home;
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("1005", param1);
        args.putString("1006", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAuxiliary);
        swipeRefreshLayout.setOnRefreshListener(this);
        List<ClickEntity> list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        multipleItemQuickAdapter = new MultipleItemQuickAdapter((BaseActivity) getActivity(), list);
        recyclerView.setAdapter(multipleItemQuickAdapter);
        View notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        multipleItemQuickAdapter.setEmptyView(notDataView);
    }

    @Override
    public void initData() {
        super.initData();
        String type = getArguments().getString("1005");
        string = getArguments().getString("1006");
        initNetwork();

    }

    @Override
    public void initNetwork() {
        super.initNetwork();
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("uid", uid);
        map.put("type", 1);
        if (string != null) {
            map.put("firstUser", string);
        } else {
            map.put("firstUser", 0);
        }
        swipeRefreshLayout.setRefreshing(true);
        presenter.gethomePageData(map);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fl_news, R.id.tv_home_search, R.id.tv_home_history, R.id.tv_home_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_news:
                FragmentActivity.actionActivity(getContext(), "news");
                break;
            case R.id.tv_home_search:
                if (uid.equals("15")) {
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(getActivity());
                    normalDialog.setIcon(null);
                    normalDialog.setTitle(R.string.need_login);
                    normalDialog.setPositiveButton(R.string.register,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LogInActivity.actionActivity(getActivity());
                                }
                            });
                    normalDialog.setNegativeButton(R.string.cancel, null);
                    // 显示
                    normalDialog.show();
                } else
                    SearchActivity.actionActivity(getActivity());
                break;
            case R.id.tv_home_history:
                FragmentActivity.actionActivity(getActivity(), "plays");
                break;
            case R.id.tv_home_download:
                FragmentActivity.actionActivity(getContext(), "download_activity");
                break;
        }
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (swipeRefreshLayout == null)
            return;
  /*      if(entity!=null&&entity.){

        }*/
        swipeRefreshLayout.setRefreshing(false);
        if (entity instanceof HomeEntity) {
            List<HomeEntity.PageInfoBean> pageInfo = ((HomeEntity) entity).getPageInfo();
            if (pageInfo != null && pageInfo.size() > 0) {
                multipleItemQuickAdapter.setNewData(null);

                ClickEntity clickEntityHead = new ClickEntity(R.layout.item_home_head, "head");
                clickEntityHead.setHomeEntity((HomeEntity) entity);
                multipleItemQuickAdapter.addData(clickEntityHead);

                ClickEntity clickEntityReview = new ClickEntity(R.layout.item_home_recycler, getString(R.string.in_a_column));
                clickEntityReview.setHomeEntity((HomeEntity) entity);
                multipleItemQuickAdapter.addData(clickEntityReview);

                ClickEntity clickEntityHot = new ClickEntity(R.layout.item_home_recycler, getString(R.string.hot_recommended));
                clickEntityHot.setHomeEntity((HomeEntity) entity);
                multipleItemQuickAdapter.addData(clickEntityHot);
            }
            ArrayMap<String, Object> map = new ArrayMap<>();
            map.put("version", Utils.getAppVersionCode());
            map.put("pbid", 1);
            presenter.getAppInfoData(map);

        } else if (entity instanceof UpDateAppEntity) {
            if (getActivity() instanceof GiiispActivity) {
                UpDateAppEntity.AppInfoBean appInfo = ((UpDateAppEntity) entity).getAppInfo();
                if (appInfo != null) {
                    if (appInfo.getVersionCode() > Utils.getAppVersionCode()) {
                        UpdatePopupWindow updatePopupWindow = new UpdatePopupWindow(getActivity(), appInfo);
                        updatePopupWindow.showPopupWindow();
                    }
                }
            }
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
        super.onFailure(msg, ex);
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }


    @Override
    public void onRefresh() {
        initNetwork();
    }
}
