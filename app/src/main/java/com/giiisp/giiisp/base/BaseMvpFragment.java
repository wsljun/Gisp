package com.giiisp.giiisp.base;

import android.os.Bundle;
import android.util.Log;

import com.giiisp.giiisp.utils.Utils;


/**
 * ----------BigGod be here!----------/
 * <p>
 * 类描述： 解决fragment重叠问题
 */

/**
 * Fragment 重叠问题
 * 1>程序崩溃
 * 2>程序切换后台
 * Fragment  show   是否隐藏的状态
 */

public abstract class BaseMvpFragment<V, P extends BasePresenter> extends BaseFragment {
    public P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        if (presenter != null && presenter.isViewAttached()) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract P initPresenter();

    public void onFailure(String msg, Exception ex) {
        timeout = true;
        Log.i("--->>", "onFailure: " +"P: "+presenter.getClass().getSimpleName()+" : "+ msg + "" + ex.toString());
        if (isShow)
            Utils.showToast(msg);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i("--->>", "onHiddenChanged: " + hidden );
        if (timeout)
            initNetwork();
        if (!hidden) {
            isShow = true;
        }
        super.onHiddenChanged(hidden);

    }

}
