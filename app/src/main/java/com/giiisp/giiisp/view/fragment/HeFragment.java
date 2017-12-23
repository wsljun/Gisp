package com.giiisp.giiisp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学者详情页
 * Created by Thinkpad on 2017/6/5.
 */

public class HeFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_attention)
    ImageView ivAttention;
    @BindView(R.id.tv_prompt)
    TextView tvPrompt;
    @BindView(R.id.tv_user_position)
    TextView tvUserPosition;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.tv_paper)
    TextView tvPaper;
    @BindView(R.id.tv_paper_number)
    TextView tvPaperNumber;
    @BindView(R.id.tv_review)
    TextView tvReview;
    @BindView(R.id.tv_review_number)
    TextView tvReviewNumber;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_fans_number)
    TextView tvFansNumber;
    @BindView(R.id.tv_authentication)
    TextView tvAuthentication;
    @BindView(R.id.tv_institutions)
    TextView tvInstitutions;
    @BindView(R.id.tv_institutions_info)
    TextView tvInstitutionsInfo;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_department_info)
    TextView tvDepartmentInfo;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_position_info)
    TextView tvPositionInfo;
    @BindView(R.id.tv_research_field)
    TextView tvResearchField;
    @BindView(R.id.tv_research_info)
    TextView tvResearchInfo;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.recyclerView_introduction)
    RecyclerView recyclerViewIntroduction;
    @BindView(R.id.recyclerView_paper_list)
    RecyclerView recyclerViewPaperList;
    @BindView(R.id.recyclerView_review_list)
    RecyclerView recyclerViewReviewList;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_he;
    }

    @Override
    public void initView() {
        tvTitle.setText("学者详情页");
//        scrollView.fullScroll(ScrollView.FOCUS_UP);
        recyclerViewIntroduction.setNestedScrollingEnabled(false);
        recyclerViewReviewList.setNestedScrollingEnabled(false);
        recyclerViewPaperList.setNestedScrollingEnabled(false);
        recyclerViewIntroduction.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClickEntity> list = new ArrayList<>();
        list.add(new ClickEntity());
        recyclerViewPaperList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewReviewList.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewPaperList.setAdapter(new ItemClickAdapter((BaseActivity)getActivity(), R.layout.item_paper_indexes, list,"PaperList"));
        recyclerViewReviewList.setAdapter(new ItemClickAdapter((BaseActivity)getActivity(), R.layout.item_paper_indexes, list,"ReviewList"));
        recyclerViewIntroduction.setAdapter(new ItemClickAdapter((BaseActivity)getActivity(), R.layout.item_editinfo_education, list,"Introduction"));



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.tv_paper_number, R.id.tv_review_number, R.id.tv_fans_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                break;
            case R.id.tv_paper_number:
                break;
            case R.id.tv_review_number:
                break;
            case R.id.tv_fans_number:
                break;
        }
    }
}
