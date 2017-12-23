package com.giiisp.giiisp.base;

import android.os.Bundle;

public abstract class BaseMvpActivity<V, T extends BasePresenter> extends BaseActivity {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        presenter = initPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null && presenter.isViewAttached()) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract T initPresenter();
}
