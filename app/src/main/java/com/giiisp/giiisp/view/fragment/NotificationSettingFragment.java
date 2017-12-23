package com.giiisp.giiisp.view.fragment;

import android.widget.Switch;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.view.impl.BaseImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  通知设置页面
 * Created by Thinkpad on 2017/5/4.
 */

public class NotificationSettingFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.switch_at_my)
    Switch switchAtMy;
    @BindView(R.id.switch_notice_praise)
    Switch switchNoticePraise;
    @BindView(R.id.switch_notice_comment)
    Switch switchNoticeComment;
    @BindView(R.id.switch_notice_problem)
    Switch switchNoticeProblem;
    @BindView(R.id.switch_notice_answer)
    Switch switchNoticeAnswer;
    @BindView(R.id.switch_friend_request)
    Switch switchFriendRequest;
    @BindView(R.id.switch_same_question)
    Switch switchSameQuestion;
    @BindView(R.id.switch_notice_news)
    Switch switchNoticeNews;

    @Override
    public void onSuccess(BaseEntity entity) {

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_noticesetting;
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
        getSettingActivity().getVpLogin().setCurrentItem(0,false);
    }
}
