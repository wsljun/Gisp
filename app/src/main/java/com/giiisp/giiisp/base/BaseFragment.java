package com.giiisp.giiisp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.giiisp.giiisp.net.NetChangeObserver;
import com.giiisp.giiisp.net.NetWorkUtil;
import com.giiisp.giiisp.net.NetworkStateReceiver;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.activity.GiiispActivity;
import com.giiisp.giiisp.view.activity.LogInActivity;
import com.giiisp.giiisp.view.activity.SettingActivity;
import com.giiisp.giiisp.view.activity.VerifiedActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称：ProjectFunction
 * 类描述： 解决fragment重叠问题
 * 修改备注：
 */

/**
 * Fragment 重叠问题
 * 1>程序崩溃
 * 2>程序切换后台
 * Fragment  show   是否隐藏的状态
 */

public abstract class BaseFragment extends Fragment implements NetChangeObserver {
    public static final String STATE_IS_HIDDEN = "state_is_hidden";
    public static final String STATE_IS_ADDED = "state_is_added";
    public LayoutInflater inflater;
    public BaseActivity mActivity;
    public Context context;
    public boolean isShow;
    public Unbinder unbinder;
    private boolean isNetworkConnect;
    public boolean timeout;
    protected boolean mLastNetworkConnect; // 上次网络连接状态
    protected boolean isResume = false;
    protected String type="";
    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mLastNetworkConnect = NetWorkUtil.isNetworkAvailable(getContext());
        if (savedInstanceState != null) {
            //            boolean isAdded = savedInstanceState.getBoolean(STATE_IS_ADDED);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (ft != null && isAdded()) {
                ft.remove(this);
                ft.commit();
            }
            if (getParentFragment() != null) {
                FragmentTransaction pft = getParentFragment().getChildFragmentManager().beginTransaction();
                if (pft != null && isAdded()) {
                    pft.remove(this);
                    pft.commit();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //手动去保存隐藏的状态
        //        outState.putBoolean(STATE_IS_ADDED, isAdded());
        //        outState.putBoolean(STATE_IS_HIDDEN, isHidden());
        super.onSaveInstanceState(outState);
    }

    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private final void init() {
        initData();
        initView();
    }

    public abstract void initView();

    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        isResume = true;
        Log.i("--->>", "onResume: "+this + mLastNetworkConnect);
        NetworkStateReceiver.registerObserver(this);
        isNetworkConnect = NetWorkUtil.isNetworkAvailable(getContext());
        isResume = true;
        if (timeout) {
            initNetwork();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isResume = false;
        NetworkStateReceiver.removeRegisterObserver(this);
    }

    @Override
    public void onConnect(NetWorkUtil.NetType type) {
        Log.i("--->>", "onConnect: " + type + mLastNetworkConnect);
        if (!isResume)
            return;
        Utils.showToast(type + "连接");
        if (!mLastNetworkConnect) {
            initNetwork();
            mLastNetworkConnect = !mLastNetworkConnect;
        }
    }

    public void initNetwork() {
        Log.i("--->>", "initNetwork: ");
    }

    /**
     * 当前没有网络连接
     */
    @Override
    public void onDisConnect() {
        //        mLastNetworkConnect = false;
        Utils.showToast("断开连接");
        Log.i("--->>", "onDisConnect: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void hindInputMethod() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public GiiispActivity getGiiispActivity() {
        if (mActivity != null && !mActivity.isFinishing() && mActivity instanceof GiiispActivity) {
            return (GiiispActivity) mActivity;
        }
        return null;
    }

    public LogInActivity getLogInActivity() {
        if (mActivity != null && !mActivity.isFinishing() && mActivity instanceof LogInActivity) {
            return (LogInActivity) mActivity;
        }
        return null;

    }

    public SettingActivity getSettingActivity() {
        if (mActivity != null && !mActivity.isFinishing() && mActivity instanceof SettingActivity) {
            return (SettingActivity) mActivity;
        }
        return null;
    }

    public VerifiedActivity getVerifiedActivity() {
        if (mActivity != null && !mActivity.isFinishing() && mActivity instanceof VerifiedActivity) {
            return (VerifiedActivity) mActivity;
        }
        return null;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isShow = !hidden;
    }

    public void addDownload() {

    }

    public void downloadCompleted() {

    }

    public void attention() {

    }

    public void collection(int id, int integer, String type, String isFollowed,int parentPosition,int position) {

    }

}
