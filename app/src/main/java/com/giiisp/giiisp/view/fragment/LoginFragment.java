package com.giiisp.giiisp.view.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.KeyBoardUtils;
import com.giiisp.giiisp.utils.Log;
import com.giiisp.giiisp.utils.SharedPreferencesHelper;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.AttentionActivity;
import com.giiisp.giiisp.view.activity.GiiispActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;

import butterknife.BindView;
import butterknife.OnClick;

import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 登录页面
 * Created by Android on 2016/11/29.
 */


public class LoginFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ed_enter_phone)
    EditText edEnterPhone;
    @BindView(R.id.ed_enter_password)
    EditText edEnterPassword;


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_loginpage;
    }

    public static LoginHintFragment newInstance() {
        return new LoginHintFragment();
    }

    @Override
    public void initView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R.string.register));
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(getString(R.string.login));
    }


    @Override
    public void onSuccess(BaseEntity entity) {
        Log.i("--->>",entity.toString());
        if (tvTitle == null)
            return;
        if ( TextUtils.isEmpty(entity.getUid()) || entity.getResult() != 1) {
            Utils.showToast(R.string.login_failed);
            return;
        } else {
            Utils.showToast(entity.getInfo());
        }

//        BaseActivity.token = entity.getToken();
        uid = entity.getUid();
//        SharedPreferencesHelper.getInstance(context).putStringValue("token", entity.getToken());
        SharedPreferencesHelper.getInstance(context).putStringValue("Uid", entity.getUid());
        if ("0".equals(entity.getNewUser() + "")) {
            GiiispActivity.actionActivity(getContext());
        } else {
            AttentionActivity.actionActivity(getActivity(), "first");
        }
        getActivity().finish();
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.tv_register, R.id.tv_forget_password, R.id.tv_user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                KeyBoardUtils.closeKeybord(edEnterPhone, getContext());
                KeyBoardUtils.closeKeybord(edEnterPassword, getContext());
                getLogInActivity().getVpLogin().setCurrentItem(0);
                break;
            case R.id.tv_register:
           /*     AttentionActivity.actionActivity(getActivity());
                getActivity().finish();
*/
                String mobile = edEnterPhone.getText().toString();
                String pwd = edEnterPassword.getText().toString();
                if (TextUtils.isEmpty(mobile)) {
                    Utils.showToast(R.string.account_cannot_empty);
                    break;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Utils.showToast(R.string.password_cannot_empty);
                    break;
                }
                presenter.getLoginData(mobile, pwd);

                break;
            case R.id.tv_right:
                getLogInActivity().setFrom(1);
                getLogInActivity().getVpLogin().setCurrentItem(2);
                break;
            case R.id.tv_forget_password:
                getLogInActivity().getVpLogin().setCurrentItem(3);
                break;
            case R.id.tv_user_agreement:
                getLogInActivity().setAgreement(1);
                getLogInActivity().getVpLogin().setCurrentItem(4);
                break;
        }
    }
}