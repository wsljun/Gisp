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
import com.giiisp.giiisp.utils.SharedPreferencesHelper;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.LogInActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;

import butterknife.BindView;
import butterknife.OnClick;

import static com.giiisp.giiisp.base.BaseActivity.uid;

/**
 * 修改手机号码页面
 * Created by Thinkpad on 2017/5/4.
 */

public class ModifyPhoneFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_area_code)
    EditText etAreaCode;
    @BindView(R.id.et_phone_input_frame)
    EditText etPhoneInputFrame;
    @BindView(R.id.et_input_verification_code)
    EditText etInputVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_close)
    TextView tvClose;
    private String phone;
    int number;

    @Override
    public void onSuccess(BaseEntity entity) {
        if (context == null)
            return;
        if (entity instanceof PhoneEntity) {
            if (((PhoneEntity) entity).getIsMobileExist() == 1) {
                Utils.showToast(R.string.phone_been_binding);
            } else {
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvGetVerificationCode, 60000, 1000);
                mCountDownTimerUtils.start();
                number = 3;
                presenter.getSendCodeData(phone, "3");
            }
        } else {
            if (entity.getResult() == 1 && number == 100) {
                SharedPreferencesHelper.getInstance(getContext()).putStringValue("token", "");
                SharedPreferencesHelper.getInstance(getContext()).putIntValue("Uid", 0);
                LogInActivity.actionActivity(getContext());
                number = 2000;
                Utils.showToast(entity.getInfo() + getString(R.string.log_in_again));
                presenter.getUserLogOutData();
                getActivity().finish();
                //AppManager.getAppManager().finishActivity(BambooActivity.class);
            }
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_bindingphone;
    }

    @Override
    public void initView() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.tv_get_verification_code, R.id.tv_confirm, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
            case R.id.tv_close:
                getSettingActivity().getVpLogin().setCurrentItem(2, false);
                break;
            case R.id.tv_get_verification_code:
                phone = etPhoneInputFrame.getText() + "";
                if (!TextUtils.isEmpty(phone)) {
                    if (Utils.checkMobileNumber(phone)) {
                        presenter.getPhoneData(phone);
                    } else {
                        Utils.showToast(R.string.phone_format_correct);
                    }
                }
                break;
            case R.id.tv_confirm:
                String code = etInputVerificationCode.getText() + "";
                if (TextUtils.isEmpty(code)) {
                    Utils.showToast(R.string.verification_code_empty);
                } else {
                    ArrayMap<String, Object> map = new ArrayMap<>();
                    map.put("uid", uid + "");
                    map.put("mobile", phone);
                    map.put("code", code);
                    number = 100;
                    presenter.getUpdateMobileData(map);
                }
                break;
        }
    }
}
