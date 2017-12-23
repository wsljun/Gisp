package com.giiisp.giiisp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {

    protected Reference<T> mViewRef;//View接口类型的弱引用

    public void attachView(T view){

        mViewRef=new WeakReference<T>(view);//建立关系

    }

    protected T getView(){

        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef!=null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if (mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }

    }

}