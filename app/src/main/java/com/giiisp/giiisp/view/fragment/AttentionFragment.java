package com.giiisp.giiisp.view.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.AttentionEntity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.MyScholarBean;
import com.giiisp.giiisp.entity.MyScholarEntity;
import com.giiisp.giiisp.entity.PbTypeBean;
import com.giiisp.giiisp.entity.RecommendScholarEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.DensityUtils;
import com.giiisp.giiisp.view.activity.AttentionActivity;
import com.giiisp.giiisp.view.activity.GiiispActivity;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;
import com.giiisp.giiisp.view.impl.BaseImpl;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 订阅的关键字领域学者的fragment
 * Created by Thinkpad on 2017/4/24.
 */

public class AttentionFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, BaseQuickAdapter.OnItemChildClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_my_attention)
    RecyclerView rvMyTerritory;
    @BindView(R.id.tv_my_attention)
    TextView tvMyTerritory;

    @BindView(R.id.tv_recommended_attention)
    TextView tvRecommendedTerritory;
    @BindView(R.id.rv_recommended_attention)
    RecyclerView rvRecommendedTerritory;
    ArrayList<ClickEntity> list = new ArrayList<>();
    @BindView(R.id.tv_next)
    TextView tvNext;
    private String sub;
    private String type;
    private ItemClickAdapter itemClickAdapter;
    private ItemClickAdapter itemClickAdapter2;
    private String superType;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static AttentionFragment newInstance(String param1, String param2) {
        AttentionFragment fragment = new AttentionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_attention;
    }

    @Override
    public void initData() {
        super.initData();
        superType = getArguments().getString(ARG_PARAM1);
        type = getArguments().getString(ARG_PARAM2);
        ArrayMap<String, Object> mapt1 = new ArrayMap<>();
        ArrayMap<String, Object> mapt2 = new ArrayMap<>();
//        mapt1.put("token", token);
        mapt1.put("uid", uid);
//        mapt2.put("token", token);
        mapt2.put("uid", uid);
        switch (superType) {
            case "first":
                tvNext.setVisibility(View.VISIBLE);
                break;
            case "mine":
                tvNext.setVisibility(View.GONE);
                break;
        }
        switch (type) {
            case "1":
                sub = getString(R.string.field);
                mapt1.put("t", 1);
                mapt2.put("t", 2);
                mapt1.put("flag", 1);
                presenter.getSubscribeFieldData(mapt1);
                mapt2.put("flag", 1);
                presenter.getSubscribeFieldData(mapt2);
                break;
            case "2":
                sub = getString(R.string.keyword);
                mapt1.put("t", 1);
                mapt2.put("t", 2);
                mapt1.put("flag", 2);
                presenter.getSubscribeFieldData(mapt1);
                mapt2.put("flag", 2);
                presenter.getSubscribeFieldData(mapt2);
                break;
            case "3":
                sub = getString(R.string.scholars);
                presenter.getScholarsRecommendData(mapt1);
                presenter.getScholarsFollowedData(mapt2);
                break;
        }
    }

    @Override
    public void initView() {

        tvRecommendedTerritory.setText(getString(R.string.recommended) + sub);
        list.clear();

        StaggeredGridLayoutManager  flowLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager  flowLayoutManager2 = new StaggeredGridLayoutManager (2,StaggeredGridLayoutManager.HORIZONTAL);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 3);
//        flowLayoutManager.setScrollEnabled(true);
//        flowLayoutManager2.setScrollEnabled(true);
        switch (type) {
            case "1":
                tvMyTerritory.setText(getString(R.string.mine) + sub);
                tvNext.setText(R.string.next_choose_keywords);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_attention_text, list, type);
                itemClickAdapter2 = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_attention_text, list, type);
                rvMyTerritory.setLayoutManager(flowLayoutManager);
                rvRecommendedTerritory.setLayoutManager(flowLayoutManager2);
                break;
            case "2":
                tvMyTerritory.setText(getString(R.string.mine) + sub);
                tvNext.setText(R.string.next_attention_scholars);
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_attention_text, list, type);
                itemClickAdapter2 = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_attention_text, list, type);
                rvMyTerritory.setLayoutManager(flowLayoutManager);
                rvRecommendedTerritory.setLayoutManager(flowLayoutManager2);

                break;
            case "3":
                tvMyTerritory.setText(getString(R.string.my_sub) + sub);
                tvNext.setText(getString(R.string.you_are_welcome));
                itemClickAdapter = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_attention_img, list, type);
                itemClickAdapter2 = new ItemClickAdapter((BaseActivity) getActivity(), R.layout.item_attention_img, list, type);

                rvMyTerritory.setLayoutManager(layoutManager);
                rvRecommendedTerritory.setLayoutManager(layoutManager2);
                ViewGroup.LayoutParams layoutParams = rvMyTerritory.getLayoutParams();
                layoutParams.height = DensityUtils.dp2px(getContext(), 190f);
                ViewGroup.LayoutParams layoutParams2 = rvRecommendedTerritory.getLayoutParams();
                layoutParams2.height = DensityUtils.dp2px(getContext(), 190f);

                rvMyTerritory.setLayoutParams(layoutParams);
                rvRecommendedTerritory.setLayoutParams(layoutParams2);
                break;
        }
//        rvMyTerritory.setNestedScrollingEnabled(false);
//        rvRecommendedTerritory.setNestedScrollingEnabled(false);
        rvMyTerritory.setAdapter(itemClickAdapter);
        rvRecommendedTerritory.setAdapter(itemClickAdapter2);
        itemClickAdapter2.setOnItemChildClickListener(this);
        itemClickAdapter.setOnItemChildClickListener(this);
    }

    public AttentionActivity getAttentionActivity() {
        if (mActivity != null && !mActivity.isFinishing() && mActivity instanceof AttentionActivity) {
            return (AttentionActivity) mActivity;
        }
        return null;
    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        if (getAttentionActivity() != null) {
            switch (type) {
                case "1":
                    getAttentionActivity().getVpMySubscription().setCurrentItem(1);
                    break;
                case "2":
                    getAttentionActivity().getVpMySubscription().setCurrentItem(2);
                    break;
                case "3":
                    GiiispActivity.actionActivity(getAttentionActivity());
                    getActivity().finish();
                    break;
            }

            //            presenter.getCancelFollowTypeInfo(map);


        }

    }

    @Override
    public void attention() {
        super.attention();
        //        initNetworks();
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (context == null)
            return;
        if (entity instanceof RecommendScholarEntity) {
            List<MyScholarBean> rScholar = ((RecommendScholarEntity) entity).getRScholar();
            itemClickAdapter2.setNewData(null);
            if (rScholar != null && rScholar.size() > 0) {
                for (MyScholarBean myScholarBean : rScholar) {
                    ClickEntity clickEntity = new ClickEntity(myScholarBean);
                    clickEntity.setSelected(false);
                    itemClickAdapter2.addData(clickEntity);
                }
            }

        } else if (entity instanceof MyScholarEntity) {
            MyScholarEntity.MyScholarBeanX myScholar = ((MyScholarEntity) entity).getMyScholar();
            itemClickAdapter.setNewData(null);
            if (myScholar != null) {
                List<MyScholarBean> rows = myScholar.getRows();
                if (rows != null && rows.size() > 0) {
                    for (MyScholarBean myScholarBean : rows) {
                        ClickEntity clickEntity = new ClickEntity(myScholarBean);
                        clickEntity.setSelected(true);
                        itemClickAdapter.addData(clickEntity);
                    }
                }
            }
        } else if (entity instanceof AttentionEntity) {
            AttentionEntity attentionEntity = (AttentionEntity) entity;
            if (attentionEntity.getT() == null)
                return;
            switch (attentionEntity.getT()) {
                case "1":
                    itemClickAdapter.setNewData(null);

                    List<PbTypeBean> pbType = attentionEntity.getPbType();
                    if (pbType != null && pbType.size() > 0) {

                        for (int i = 0; i < pbType.size(); i++) {
                            ClickEntity clickEntity = new ClickEntity(pbType.get(i));
                            clickEntity.setSelected(true);
                            itemClickAdapter.addData(clickEntity);
                        }
                    }

                    break;
                case "2":
                    itemClickAdapter2.setNewData(null);

                    List<PbTypeBean> pbTypeBeans = attentionEntity.getPbType();
                    if (pbTypeBeans != null && pbTypeBeans.size() > 0) {

                        for (int i = 0; i < pbTypeBeans.size(); i++) {
                            itemClickAdapter2.addData(new ClickEntity(pbTypeBeans.get(i)));
                        }
                    }
            }
        } else {
            initData();
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    public void submitFollow(boolean isFollowed, String id) {
        ArrayMap<String, Object> map = new ArrayMap<>();
//        map.put("token", token);
        map.put("oid", id);
        map.put("uid", uid);
        if (isFollowed) {
            presenter.getSaveFollowUserData(map);
        } else {
            presenter.getCancelFollowUserData(map);
        }


    }

    private void SaveFollow(int id) {
        ArrayMap<String, Object> map = new ArrayMap<>();

        switch (type) {
            case "1":
                map.put("flag", 1);
                break;
            case "2":
                map.put("flag", 2);
                break;

        }
        map.put("uid", uid);
//        map.put("token", token);
        map.put("anids", id);
        presenter.getSaveFollowTypeData(map);
    }

    private void CancelFollow(int id) {
        ArrayMap<String, Object> map = new ArrayMap<>();
        switch (type) {
            case "1":
                map.put("flag", 1);
                break;
            case "2":
                map.put("flag", 2);
                break;

        }
        map.put("uid", uid);
        map.put("id", id);
        presenter.getCancelFollowTypeInfo(map);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_attention:
                ClickEntity item = (ClickEntity) adapter.getItem(position);
                if (item == null)
                    return;
                PbTypeBean typeBean = item.getTypeBean();
                if (typeBean != null && typeBean.getId() != 0)
                    if (!view.isSelected()) {
                        SaveFollow(typeBean.getId());
                    } else {
                        CancelFollow(typeBean.getId());
                    }
                break;
            case R.id.iv_scholar_icon:
                ClickEntity itemScholar = (ClickEntity) adapter.getItem(position);
                if (itemScholar == null)
                    return;
                MyScholarBean myScholarBean = itemScholar.getMyScholarBean();
                if (myScholarBean != null) {
                    String oid = myScholarBean.getOid();
                    if (!TextUtils.isEmpty(oid))
                        submitFollow(view.isSelected(), oid);
                }

                break;
        }
    }
}
