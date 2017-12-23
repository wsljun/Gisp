package com.giiisp.giiisp.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.DataCleanManager;
import com.giiisp.giiisp.utils.SharedPreferencesHelper;
import com.giiisp.giiisp.view.impl.BaseImpl;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 系统设置
 * Created by Thinkpad on 2017/5/4.
 */

public class SystemSettingFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.switch_image_watermarking)
    Switch switchImageWatermarking;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.spinner_voice_selection)
    Spinner spinnerVoiceSelection;
    private String s;

    @Override
    public void onSuccess(BaseEntity entity) {

    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_systemsetting;
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.system_setting));
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(getContext());
            tvCache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        SharedPreferencesHelper.getInstance(getContext()).putStringValue("VoiceSelection", spinnerVoiceSelection.getSelectedItem() + "");
        s = SharedPreferencesHelper.getInstance(getContext()).getStringValue("VoiceSelection");
        if (Objects.equals(s, "English")) {
            spinnerVoiceSelection.setSelection(1);
        } else if (s == null) {
            s = getActivity().getResources().getStringArray(R.array.languages)[0];
            spinnerVoiceSelection.setSelection(0);
        }
        spinnerVoiceSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String cardNumber = getActivity().getResources().getStringArray(R.array.languages)[position];
                SharedPreferencesHelper.getInstance(getContext()).putStringValue("VoiceSelection", cardNumber);
                Log.i("--->>", "onItemSelected: " + s + position + "  " + cardNumber);
                if (!Objects.equals(cardNumber, s)) {
                    s = cardNumber;
                    mActivity.changeAppLanguage();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.fl_notice_praise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getSettingActivity().getVpLogin().setCurrentItem(0, false);
                break;
            case R.id.fl_notice_praise:
                showNormalDialog();
                break;
        }
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setIcon(null);
        normalDialog.setTitle(R.string.clear_the_cache);
        normalDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataCleanManager.clearAllCache(getContext());
                        String totalCacheSize = null;
                        try {
                            totalCacheSize = DataCleanManager.getTotalCacheSize(getContext());
                            tvCache.setText(totalCacheSize);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        normalDialog.setNegativeButton(R.string.cancel, null);
        // 显示
        normalDialog.show();
    }

}


