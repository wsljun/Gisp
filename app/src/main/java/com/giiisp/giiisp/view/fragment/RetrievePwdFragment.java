package com.giiisp.giiisp.view.fragment;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.PhoneEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.CountDownTimerUtils;
import com.giiisp.giiisp.utils.KeyBoardUtils;
import com.giiisp.giiisp.utils.Log;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.impl.BaseImpl;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改密码界面
 * Created by Android on 2017/5/3.
 */


public class RetrievePwdFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_enter_phones)
    EditText edEnterPhones;
    @BindView(R.id.ed_enter_code)
    EditText edEnterCode;
    @BindView(R.id.ed_enter_password)
    EditText edEnterPassword;
    @BindView(R.id.ed_second_password)
    EditText edSecondPassword;
    @BindView(R.id.tv_verification_code)
    TextView tvVerificationCode;
    private String phone;
    private int successType;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_retrievepassword;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.retrieve_password);
    }


    @Override
    public void onSuccess(BaseEntity entity) {
        Log.i("--->>",entity.toString());
        if (entity == null || context == null)
            return;
        switch (successType) {
            case 1:
                if (entity instanceof PhoneEntity) {
                    if (((PhoneEntity) entity).getIsMobileExist() == 1) {
                        presenter.getSendCodeData(phone, "2");
                        successType = 2;
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvVerificationCode, 60000, 1000);
                        mCountDownTimerUtils.start();
                    } else {
                        Utils.showToast(R.string.phone_not_registered);
                    }
                }
                break;
            case 2:
                Utils.showToast(entity.getInfo());
                break;
            case 3:
                if (entity.getResult() == 1) {
                    getLogInActivity().getVpLogin().setCurrentItem(1);
                }
                Utils.showToast(entity.getInfo());
                break;
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    @OnClick({R.id.tv_register, R.id.tv_back, R.id.tv_verification_code, R.id.ed_enter_phones})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_enter_phones:
                KeyBoardUtils.openKeybord(edEnterPhones, getContext());
                break;
            case R.id.tv_back:
                getLogInActivity().getVpLogin().setCurrentItem(1);
                break;
            case R.id.tv_register:
                String pwd = edEnterPassword.getText().toString();
                String code = edEnterCode.getText().toString();
                String secondPwd = edSecondPassword.getText().toString();
                String mobile = edEnterPhones.getText().toString();

                if (TextUtils.isEmpty(code)) {
                    Utils.showToast(R.string.verification_code_empty);
                    break;
                }
                if (!Objects.equals(mobile, phone)) {
                    Utils.showToast(R.string.enter_the_phone);
                    break;
                }
                if (TextUtils.isEmpty(pwd) && TextUtils.isEmpty(secondPwd)) {
                    Utils.showToast(R.string.password_cannot_empty);
                    break;
                } else if (!pwd.equals(secondPwd)) {
                    Utils.showToast(R.string.passwords_match);
                } else if (pwd.length() > 8 | pwd.length() < 6) {
                    Utils.showToast(R.string.password_can_only);
                    break;
                }
                ArrayMap<String, Object> map = new ArrayMap<>();
                map.put("mobile", mobile);
                map.put("pwd", pwd);
                map.put("code", code);
                successType = 3;
                presenter.getRetrieveData(map);//Retrieve
                break;

            case R.id.tv_verification_code:
                phone = edEnterPhones.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    if (Utils.checkMobileNumber(phone)) {
                        successType = 1;
                        presenter.getPhoneData(phone);
                    } else {
                        Utils.showToast(R.string.format_not_correct);
                    }
                } else {
                    Utils.showToast(R.string.input_phone);
                }
                break;
        }
    }

}
