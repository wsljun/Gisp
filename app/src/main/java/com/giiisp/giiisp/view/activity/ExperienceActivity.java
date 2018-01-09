package com.giiisp.giiisp.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpActivity;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.giiisp.giiisp.widget.ProgressPopupWindow;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的教育经历页面
 * Created by Thinkpad on 2017/6/1.
 */

public class ExperienceActivity extends BaseMvpActivity<BaseImpl, WholePresenter> implements BaseImpl {
    public static int EDU_SUCCESS = 203;
    public static int EDU_BACK = 202;

    String type;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_user_school)
    TextView tvUserSchool;
    @BindView(R.id.tv_user_major)
    TextView tvUserMajor;
    @BindView(R.id.tv_user_edubackground)
    TextView tvUserEdubackground;
    @BindView(R.id.tv_user_degree)
    TextView tvUserDagree;
    @BindView(R.id.tv_user_starttime)
    TextView tvUserStarttime;
    @BindView(R.id.tv_user_endtime)
    TextView tvUserEndtime;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    int pType;
    ProgressPopupWindow progressPopupWindow;
    UserInfoEntity.IntroductionBean introductionBean;

    String rid = "";

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_experience;
    }

    public static void actionActivity(Activity context, String type, UserInfoEntity.IntroductionBean introductionBean) {
        Intent sIntent = new Intent(context, ExperienceActivity.class);
        sIntent.putExtra("type", type);
        sIntent.putExtra("introductionBean", introductionBean);
//        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivityForResult(sIntent, 1002);
    }

    @Override
    public void initData() {
        super.initData();
        type = getIntent().getStringExtra("type");
        introductionBean = (UserInfoEntity.IntroductionBean) getIntent().getSerializableExtra("introductionBean");
        progressPopupWindow = new ProgressPopupWindow(this);
    }

    @Override
    public void initView() {

        tvTitle.setText(R.string.my_education_experience);
        tvRight.setText(R.string.save);
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case "add":
                    tvDelete.setVisibility(View.GONE);
                    break;
                case "edit":
                    tvUserSchool.setText(TextUtils.isEmpty(introductionBean.getSchool()) ? "" : introductionBean.getSchool());
                    tvUserMajor.setText(TextUtils.isEmpty(introductionBean.getMajor()) ? "" : introductionBean.getMajor());
                    tvUserEdubackground.setText(introductionBean.getEduBackground());
                    tvUserDagree.setText(introductionBean.getDegree());
                    tvUserStarttime.setText(introductionBean.getTimeStart());
                    tvUserEndtime.setText(introductionBean.getTimeEnd());
                    rid = introductionBean.getId();
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(ExperienceActivity.EDU_BACK, getIntent());
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tv_back, R.id.fl_user_school, R.id.fl_user_major, R.id.fl_user_edubackground, R.id.fl_user_degree, R.id.fl_user_starttime, R.id.fl_user_endtime, R.id.tv_delete, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                setResult(ExperienceActivity.EDU_BACK);
                finish();
                break;
            case R.id.fl_user_school:
                inputTitleDialog(tvUserSchool, getString(R.string.school));
                break;
            case R.id.fl_user_major:
                inputTitleDialog(tvUserMajor, getString(R.string.major));
                break;
            case R.id.fl_user_edubackground:
                inputTitleDialog(tvUserEdubackground, getString(R.string.edubackground));
                break;
            case R.id.fl_user_degree:
                inputTitleDialog(tvUserDagree, getString(R.string.degree));
                break;
            case R.id.fl_user_starttime:
                showDate(tvUserStarttime, getString(R.string.starttime));
                break;
            case R.id.fl_user_endtime:
                showDate(tvUserEndtime, getString(R.string.endtime));
                break;
            case R.id.tv_delete:
                ArrayMap<String, Object> dmap = new ArrayMap<>();
                dmap.put("uid", uid);
                if (introductionBean.getId() != null) {
                    dmap.put("rid", introductionBean.getId());
                    if (presenter != null) {
                        pType = 2;
                        progressPopupWindow.showPopupWindow();
                        presenter.deleteIntroduction(dmap);
                    }
                }

                break;
            case R.id.tv_right:
                String school = tvUserSchool.getText().toString();
                String major = tvUserMajor.getText().toString();
                String edubackground = tvUserEdubackground.getText().toString();
                String degree = tvUserDagree.getText().toString();
                String timeStart = tvUserStarttime.getText().toString();
                String timeEnd = tvUserEndtime.getText().toString();

                ArrayMap<String, Object> map = new ArrayMap<>();
                map.put("uid", uid);
                if (!TextUtils.isEmpty(school)) {
                    map.put("school", school);
                }
                if (!TextUtils.isEmpty(major)) {
                    map.put("major", major);
                }
                if (TextUtils.isEmpty(edubackground)) {
                    Utils.showToast(R.string.edubackground_verified);
                } else {
                    map.put("edubackground", edubackground);
                }
                if (TextUtils.isEmpty(degree)) {
                    Utils.showToast(R.string.degree_verified);
                    return;
                } else {
                    map.put("degree", degree);
                }
                if (TextUtils.isEmpty(timeStart)) {
                    Utils.showToast(R.string.time_start_verified);
                    return;
                } else {
                    map.put("timestart", timeStart);
                }
                if (TextUtils.isEmpty(timeEnd)) {
                    Utils.showToast(R.string.time_end_verified);
                    return;
                } else {
                    map.put("timeend", timeEnd);
                }
                map.put("rid", rid);
                if (presenter != null) {
                    progressPopupWindow.showPopupWindow();
                    if (type.equals("add")) {
                        pType = 1;
                        presenter.addIntroduction(map);
                    } else if (type.equals("edit")) {
                        pType = 3;
                        presenter.updateIntroduction(map);
                    }
                }

                break;
        }
    }

    private void inputTitleDialog(final TextView view, final String name) {

        final EditText inputServer = new EditText(this);
        inputServer.setPadding(50, 50, 50, 50);
        inputServer.setFocusable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.input) + name).setIcon(
                R.mipmap.ic_launcher).setView(inputServer).setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString();
                        switch (name) {
                            case "学校":
                                view.setText(inputName);
                                break;
                            case "专业":
                                view.setText(inputName);
                                break;
                            case "学历":
                                view.setText(inputName);
                                break;
                            case "学位":
                                view.setText(inputName);
                                break;
                        }


                    }
                });
        builder.show();
    }


    private void showDate(final TextView view, final String name) {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText(getString(R.string.cancel))//取消按钮文字
                .setSubmitText(getString(R.string.confirm))//确认按钮文字
                .setTitleText(name)//标题文字
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (progressPopupWindow != null) {
            progressPopupWindow.dismiss();
        }
        Utils.showToast(entity.getInfo());
        if (pType == 1) {
            if (entity.getResult() == 1) {
                setResult(ExperienceActivity.EDU_SUCCESS);
                finish();
            }
        }
        if (pType == 2) {
            if (entity.getResult() == 1) {
                setResult(ExperienceActivity.EDU_SUCCESS);
                finish();
            }
        }
        if (pType == 3) {
            if (entity.getResult() == 1) {
                setResult(ExperienceActivity.EDU_SUCCESS);
                finish();
            }
        }
    }

    @Override
    public void onFailure(String msg, Exception ex) {

    }
}
