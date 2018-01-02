package com.giiisp.giiisp.view.fragment;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.SelectUser;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;
import com.giiisp.giiisp.view.adapter.MultipleItemQuickAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.ProgressPopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 选择证明人页面
 * Created by Thinkpad on 2017/5/8.
 */

public class WitnessFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_submission)
    TextView tvSubmission;
    ItemClickAdapter itemClickAdapter;
    ArrayMap<String, Object> map;
    ProgressPopupWindow progressPopupWindow;
    private int netType;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_witness;
    }

    @Override
    public void initData() {
        super.initData();
        progressPopupWindow = new ProgressPopupWindow((BaseActivity) getActivity());
        map = new ArrayMap<>();
        map.put("uid", uid);
        initNetwork();
    }

    public void updateData(ArrayMap<String, Object> m) {
        map = m;
        initNetwork();
    }

    @Override
    public void initView() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.tv_submission)
    public void onViewClicked() {
        List<String> list = new ArrayList<>();
        for (ClickEntity clickEntity : listClick) {
            if (clickEntity.getUserInfo().isCheck()) {
                list.add(clickEntity.getUserInfo().getId());
            }
        }
        if (list.size() >= 2) {
            String ids = "";
            for (String str : list) {
                if (TextUtils.isEmpty(ids)) {
                    ids = str;
                } else {
                    ids += ("," + str);
                }
            }
            netType = 2;
            MultipartBody.Part part1 = MultipartBody.Part.createFormData("ids",ids);
            MultipartBody.Part part2 = MultipartBody.Part.createFormData("uid",uid);
            ((VerifiedActivity) getActivity()).parts.add(part1);
            ((VerifiedActivity) getActivity()).parts.add(part2);

            presenter.userAuthen(((VerifiedActivity) getActivity()).parts);

            progressPopupWindow.showPopupWindow();
        } else {
            Utils.showToast(getString(R.string.tishi_zsyg));
        }
    }

    List<ClickEntity> listClick;

    @Override
    public void onSuccess(BaseEntity entity) {
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
        switch (netType) {
            case 1:
                if (entity.getResult() == 1) {
                    if (entity instanceof SelectUser) {
                        SelectUser userInfo = (SelectUser) entity;
                        if (userInfo.getUserInfo() != null) {
                            List<UserInfoEntity.UserInfoBean> list = userInfo.getUserInfo();
                            UserInfoEntity.UserInfoBean admin =  new UserInfoEntity.UserInfoBean();
                            admin.setId("admin");
                            admin.setRealName("集思谱官方认证");
                            list.add(0,admin);
                            if (null != list && list.size() > 0) {
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                listClick = new ArrayList<>();
                                for (UserInfoEntity.UserInfoBean userInfoBean : list) {
                                    listClick.add(new ClickEntity(userInfoBean));
                                }
                                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_witness_people, listClick, "selectUser");
                                recyclerView.setAdapter(itemClickAdapter);
                            }
                        }
                    }
                } else {
                    Utils.showToast(entity.getInfo());
                }
                break;
            case 2:
                if (entity.getResult() == 1) {
                    getVerifiedActivity().getViewPagerVerified().setCurrentItem(3);
                } else {
                    Utils.showToast(entity.getInfo());
                }
                break;
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {
        super.onFailure(msg, ex);
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
        Utils.showToast(msg);
    }

    @Override
    public void initNetwork() {
        netType = 1;
        progressPopupWindow.showPopupWindow();
        presenter.selectUser(map);
        super.initNetwork();
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }
}
