package com.giiisp.giiisp.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.QuizHintEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.KeyBoardUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.impl.BaseImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 回答 提问 的 页面
 * Created by Thinkpad on 2017/5/9.
 */

public class ProblemActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseImpl {


    @BindView(R.id.tv_put_question)
    TextView tvPutQuestion;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.tv_examine_minutely)
    TextView tvExamineMinutely;
    @BindView(R.id.editText_answer)
    AutoCompleteTextView editTextAnswer;
    @BindView(R.id.tv_text_number)
    TextView tvTextNumber;
    @BindView(R.id.iv_answer_at)
    ImageView ivAnswerAt;
    @BindView(R.id.iv_answer_link)
    ImageView ivAnswerLink;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.fl_edit)
    FrameLayout flEdit;
    private String type;
    private String pcid;
    private String uid;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_problem;
    }

    @Override
    public void initView() {
        addStatusBarView();
        type = getIntent().getStringExtra("type");
        pcid = getIntent().getStringExtra("pcid");
        uid = getIntent().getStringExtra("uid");


        InputFilter[] filters = {new InputFilter.LengthFilter(100)};

        switch (type) {
            case "answer":
                tvPutQuestion.setText(R.string.answer);
                editTextAnswer.setHint(R.string.please_input_answered);
                tvTextNumber.setVisibility(View.GONE);
                break;
            case "Problem":
                tvPutQuestion.setText(R.string.put_question);
                editTextAnswer.setMaxLines(100);
                editTextAnswer.setFilters(filters);
                ivAnswerLink.setVisibility(View.GONE);
                ivAnswerAt.setVisibility(View.GONE);
                tvConfirm.setText(R.string.complete);
                break;
            case "examineMinutely":
                tvPutQuestion.setText(R.string.examine_minutely);
                editTextAnswer.setFilters(filters);
                tvExamineMinutely.setVisibility(View.VISIBLE);
                editTextAnswer.setMaxLines(100);
                editTextAnswer.setFilters(filters);
                ivAnswerLink.setVisibility(View.GONE);
                ivAnswerAt.setVisibility(View.GONE);
                tvConfirm.setText(R.string.complete);
                break;
        }
        editTextAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (type) {
                    case "answer":
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvConfirm.setVisibility(View.VISIBLE);
                        break;
                    case "Problem":
                        if (s.length() >= 0) {
                            tvEmpty.setVisibility(View.VISIBLE);
                            tvConfirm.setVisibility(View.VISIBLE);
                            tvTextNumber.setText(String.valueOf(s.length()));
                            if (s.length() >= 100) {
                                Utils.showToast(R.string.answer_concise);
                            }
                        }

                        if (s.length() == 0) {
                            tvEmpty.setVisibility(View.GONE);
                            tvConfirm.setVisibility(View.GONE);
                        }
                        if (s.length() >= 4) {
                            ArrayMap<String, Object> map = new ArrayMap<>();
                            map.put("content", editTextAnswer.getText().toString());
                            map.put("pcid", pcid);
                            presenter.getQuizHintListData(map);

                        }

                        break;
                    case "examineMinutely":
                        break;
                }
            }
        });

    }

    public static void actionActivity(Activity context, String type, String pcid, String uid) {
        Intent sIntent = new Intent(context, ProblemActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        sIntent.putExtra("type", type);
        sIntent.putExtra("pcid", pcid);
        sIntent.putExtra("uid", uid);
        context.startActivityForResult(sIntent,1000);
    }

    private void addStatusBarView() {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this));
        ViewGroup decorView = (ViewGroup) findViewById(android.R.id.content);
        decorView.addView(view, params);
    }

    public int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    @OnClick({R.id.im_close, R.id.tv_publish, R.id.iv_answer_at, R.id.fl_edit, R.id.iv_answer_link, R.id.tv_empty, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_close:
                setResult(RESULT_OK,getIntent());
                finish();
                break;
            case R.id.tv_publish:


                ArrayMap<String, Object> map = new ArrayMap<>();
                //                map.put("uid",uid);
                map.put("uid", uid);
                String content = editTextAnswer.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Utils.showToast(R.string.please_input_content);
                    return;
                }
                map.put("content", content);

                switch (type) {
                    case "answer":
                        map.put("qid", pcid);
                        map.put("record", "");
                        map.put("isRecord", "");
                        map.put("timing", "");
                        presenter.getSaveAnswerData(map);

                        break;
                    case "Problem":
                        map.put("pcid", pcid);
                        map.put("firstQuiz", 1);
                        presenter.getSaveQuizData(map);
                        break;
                    case "examineMinutely":
                        map.put("pcid", pcid);
                        map.put("firstQuiz", 0);
                        presenter.getSaveQuizData(map);
                        break;
                }

                KeyBoardUtils.closeKeybord(editTextAnswer, this);

                break;
            case R.id.fl_edit:
                KeyBoardUtils.openKeybord(editTextAnswer, this);
                break;
            case R.id.iv_answer_at:
                FragmentActivity.actionActivity(this, "at");
                break;
            case R.id.iv_answer_link:
                FragmentActivity.actionActivity(this, "link");
                break;
            case R.id.tv_empty:
                editTextAnswer.setText("");
                break;
            case R.id.tv_confirm:
                KeyBoardUtils.closeKeybord(editTextAnswer, this);
                switch (type) {
                    case "answer":
                        AnswerVoiceMP3Activity.actionActivity(this, editTextAnswer.getText().toString(), pcid, uid);
                        break;
                    case "Problem":

                        break;
                    case "examineMinutely":

                        break;
                }

                break;
        }
    }


    @Override
    public void onSuccess(BaseEntity entity) {
        if (entity instanceof QuizHintEntity) {
            List<QuizHintEntity.QuizHintListBean> quizHintList = ((QuizHintEntity) entity).getQuizHintList();
            ArrayList<String> suggest = new ArrayList<>();
            for (QuizHintEntity.QuizHintListBean quizHintListBean : quizHintList) {
                suggest.add(quizHintListBean.getContent());
            }
            final ArrayAdapter<String> sugAdapter = new ArrayAdapter<>(ProblemActivity.this, android.R.layout.simple_dropdown_item_1line, suggest);
            editTextAnswer.setAdapter(sugAdapter);
            sugAdapter.notifyDataSetChanged();

            editTextAnswer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            editTextAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Utils.showToast(R.string.problem_exists);
                }
            });
        }else{
            setResult(RESULT_OK,getIntent());
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK,getIntent());
        finish();
    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }
}
