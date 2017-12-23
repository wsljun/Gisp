package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑名片信息页面
 * Created by Thinkpad on 2017/6/1.
 */

public class EditUserInfoActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_sex)
    TextView tvUserSex;
    @BindView(R.id.tv_user_professional)
    TextView tvUserProfessional;
    @BindView(R.id.tv_user_mechanism)
    TextView tvUserMechanism;
    @BindView(R.id.tv_user_position)
    TextView tvUserPosition;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_edituserinfo;
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, EditUserInfoActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.edit_information);
        tvRight.setText(R.string.save);
    }


    @OnClick({R.id.tv_back, R.id.fl_user_icon, R.id.fl_user_name, R.id.fl_user_sex, R.id.fl_user_professional, R.id.fl_user_mechanism, R.id.fl_user_position, R.id.fl_user_email, R.id.fl_user_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                break;
            case R.id.fl_user_icon:
                break;
            case R.id.fl_user_name:
                break;
            case R.id.fl_user_sex:
                break;
            case R.id.fl_user_professional:
                break;
            case R.id.fl_user_mechanism:
                break;
            case R.id.fl_user_position:
                break;
            case R.id.fl_user_email:
                break;
            case R.id.fl_user_phone:
                break;
        }
    }
}
