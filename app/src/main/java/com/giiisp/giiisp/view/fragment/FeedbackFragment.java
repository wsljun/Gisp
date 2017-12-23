package com.giiisp.giiisp.view.fragment;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.KeyBoardUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.impl.BaseImpl;

import butterknife.BindView;
import butterknife.OnClick;

import static com.giiisp.giiisp.base.BaseActivity.uid;

//import static com.giiisp.giiisp.base.BaseActivity.token;

/**
 * 反馈页面
 * Created by Thinkpad on 2017/5/4.
 */

public class FeedbackFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rb_suggest_feedback)
    RadioButton rbSuggestFeedback;
    @BindView(R.id.rb_comment_failure_feedback)
    RadioButton rbCommentFailureFeedback;
    @BindView(R.id.rb_share_failure_feedback)
    RadioButton rbShareFailureFeedback;
    @BindView(R.id.rb_crash_feedback)
    RadioButton rbCrashFeedback;
    @BindView(R.id.rg_type_feedback)
    RadioGroup rgTypeFeedback;
    @BindView(R.id.et_content_feedback)
    EditText etContentFeedback;
    @BindView(R.id.et_contact_feedback)
    EditText etContactFeedback;
    private int type;

    @Override
    public void onSuccess(BaseEntity entity) {
        if (tvTitle==null)return;
        Utils.showToast(entity.getInfo());
        if (entity.getResult() == 1) {
            KeyBoardUtils.closeKeybord(etContentFeedback, getContext());
            getSettingActivity().getVpLogin().setCurrentItem(7, false);
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_feedback;
    }

    @Override
    public void initView() {
        rgTypeFeedback.setOnCheckedChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                KeyBoardUtils.closeKeybord(etContentFeedback, getContext());
                getSettingActivity().getVpLogin().setCurrentItem(7, false);
                break;
            case R.id.tv_submit:
                String content = etContentFeedback.getText() + "";
                if (type == 0) {
                    Utils.showToast(R.string.select_type_feedback);
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    Utils.showToast(R.string.input_feedback_content);
                    return;
                }
                String contact = etContactFeedback.getText() + "";
                commentData(type, content, contact);
                break;
        }
    }

    private void commentData(int type, String content, String contactInfo) {
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("type", type);
        map.put("content", content);
        map.put("contactInfo", contactInfo);
        map.put("uid", uid);
        presenter.getFeedbackData(map);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_suggest_feedback:
                type = 1;
                break;
            case R.id.rb_comment_failure_feedback:
                type = 2;
                break;
            case R.id.rb_share_failure_feedback:
                type = 3;
                break;
            case R.id.rb_crash_feedback:
                type = 4;
                break;
        }
    }
}
