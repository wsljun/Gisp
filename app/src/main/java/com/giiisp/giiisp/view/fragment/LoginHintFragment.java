package com.giiisp.giiisp.view.fragment;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseMvpFragment;
import com.giiisp.giiisp.entity.BaseEntity;
import com.giiisp.giiisp.presenter.WholePresenter;
import com.giiisp.giiisp.utils.SharedPreferencesHelper;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.GiiispActivity;
import com.giiisp.giiisp.view.impl.BaseImpl;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录引导页
 * Created by Android on 2017/5/3.
 **/
public class LoginHintFragment extends BaseMvpFragment<BaseImpl, WholePresenter> implements BaseImpl {

    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_phone_login)
    TextView tvPhoneLogin;
    @BindView(R.id.tv_weixin_login)
    TextView tvWeixinLogin;
    @BindView(R.id.tv_tourist)
    TextView tvTourist;


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_loginhint;
    }

    public static LoginHintFragment newInstance() {
        return new LoginHintFragment();
    }

    @Override
    public void initView() {
    }


    @Override
    public void onSuccess(BaseEntity entity) {
        if (context == null)
            return;
        Log.i("--->>", "onSuccess: " + entity.toString());
        Utils.showToast(entity.getInfo());
        if (entity.getUid() != null) {
//            BaseActivity.token = entity.getToken();
            BaseActivity.uid = entity.getUid();
//            SharedPreferencesHelper.getInstance(context).putStringValue("token", entity.getToken());
            SharedPreferencesHelper.getInstance(context).putStringValue("Uid", entity.getUid());
            GiiispActivity.actionActivity(getContext());
        }
    }

    @Override
    protected WholePresenter initPresenter() {
        return new WholePresenter(this);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
            progressWheel.setVisibility(View.VISIBLE);
        }

        //usId") String usId, @Field("usName") String usName, @Field("type") String type, @Field("iconUrl") String iconUrl);
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            progressWheel.setVisibility(View.INVISIBLE);
            Utils.showToast(platform + getString(R.string.authorization_success));
            String wxname = data.get("name");
            String headimgurl = data.get("iconurl");
            String uid = data.get("uid");
            //            String sex = data.get("sex");
            if (!TextUtils.isEmpty(wxname) && !TextUtils.isEmpty(headimgurl) && !TextUtils.isEmpty(uid)) {
                ArrayMap<String, Object> map = new ArrayMap<>();
                map.put("usId", uid);
                map.put("usName", wxname);
                map.put("type", 2);
                map.put("iconUrl", headimgurl);
                presenter.getWith3Data(map);
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            progressWheel.setVisibility(View.INVISIBLE);
            Utils.showToast(R.string.authorization_error);
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            progressWheel.setVisibility(View.INVISIBLE);
            Utils.showToast(R.string.authorization_cancel);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_register, R.id.tv_phone_login, R.id.tv_weixin_login, R.id.tv_tourist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                getLogInActivity().setFrom(0);
                getLogInActivity().getVpLogin().setCurrentItem(2);
                break;
            case R.id.tv_phone_login:
                getLogInActivity().getVpLogin().setCurrentItem(1);
                break;
            case R.id.tv_weixin_login:
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.tv_tourist:
//                BaseActivity.token = "8CE7C86C6B32F90074CBDCEEAF0D42DA";
                BaseActivity.uid = "15";
                GiiispActivity.actionActivity(getContext());
                getActivity().finish();
                break;
        }
    }
}
