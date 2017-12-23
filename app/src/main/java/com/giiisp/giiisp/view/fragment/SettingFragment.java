package com.giiisp.giiisp.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.ImageLoader;
import com.giiisp.giiisp.utils.SharedPreferencesHelper;
import com.giiisp.giiisp.view.activity.LogInActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置页面
 * Created by Thinkpad on 2017/5/4.
 */

public class SettingFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.im_user_icon)
    ImageView imUserIcon;
    private String imagUrl = "";


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_setting;
    }

    public TextView getTvUserName() {
        return tvUserName;
    }

    public void setTvUserName(TextView tvUserName) {
        this.tvUserName = tvUserName;
    }

    public ImageView getImUserIcon() {
        return imUserIcon;
    }

    public void setImUserIcon(ImageView imUserIcon) {
        this.imUserIcon = imUserIcon;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.setting);
    }

    @Override
    public void initData() {
       /* ArrayMap<String, Object> userMap = new ArrayMap<>();
        //        userMap.put("token", "A760880003E7DDEDFEF56ACB3B09697F");
        userMap.put("token", token);
        //        userMap.put("oid", 1);
        userMap.put("uid", uid);
        presenter.getUserInfoData(userMap);*/
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_back, R.id.tv_signout, R.id.fl_privacy_setting, R.id.fl_user_info, R.id.fl_account_security, R.id.fl_notification_settings, R.id.fl_system_setting, R.id.fl_my_points, R.id.fl_about_giiisp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                getSettingActivity().finish();
                break;
            case R.id.fl_user_info:
                getSettingActivity().getVpLogin().setCurrentItem(1);
                break;
            case R.id.fl_privacy_setting:
                //                getSettingActivity().getVpLogin().setCurrentItem(9);

                break;
            case R.id.fl_account_security:
                getSettingActivity().getVpLogin().setCurrentItem(2, false);
                break;
            case R.id.fl_notification_settings:
                getSettingActivity().getVpLogin().setCurrentItem(5, false);
                break;
            case R.id.fl_system_setting:
                getSettingActivity().getVpLogin().setCurrentItem(6, false);
                break;
            case R.id.fl_my_points:
                break;
            case R.id.fl_about_giiisp:
                getSettingActivity().getVpLogin().setCurrentItem(7, false);
                break;
            case R.id.tv_signout:
                showNormalDialog();
                break;
        }
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题 Attempt to invoke virtual method 'android.content.res.Resources$Theme' on a null object reference
	at android.app.AlertDialog.resolveDialogTheme(AlertDialog.java:225)
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        if (getContext() == null)
            return;
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setIcon(null);
        normalDialog.setTitle(R.string.determine_cancellation);
        normalDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferencesHelper.getInstance(getContext()).putStringValue("token", "");
                        SharedPreferencesHelper.getInstance(getContext()).putIntValue("Uid", 0);
                        LogInActivity.actionActivity(getContext());
                        presenter.getUserLogOutData();
                        getActivity().finish();
                    }
                });
        normalDialog.setNegativeButton(R.string.cancel, null);
        // 显示
        normalDialog.show();
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        if (tvUserName == null)
            return;

        if (entity instanceof UserInfoEntity) {
            UserInfoEntity userInfoEntity = (UserInfoEntity) entity;
            if (userInfoEntity.getUserInfo() != null) {
                EventBus.getDefault().post(userInfoEntity);
                String nickName = userInfoEntity.getUserInfo().getRealName();
                String avatar = userInfoEntity.getUserInfo().getAvatar();
                ImageLoader.getInstance().displayCricleImage((BaseActivity) getActivity(), avatar, imUserIcon);
                if (!TextUtils.isEmpty(nickName))
                    tvUserName.setText(nickName);
            }
        }
    }


    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }
}
