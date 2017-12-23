package com.giiisp.giiisp.widget;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.utils.DensityUtils;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * 语音选择window
 * Created by Thinkpad on 2017/6/1.
 */

public class LanguageSelectPopupWindow extends BasePopupWindow{

    public LanguageSelectPopupWindow(Activity context) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public LanguageSelectPopupWindow(Activity context, int w, int h) {
        super(context, w, h);
        initView(context);
    }

    private void initView(Activity context) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        final List<ClickEntity> list = new ArrayList<>();
        list.add(new ClickEntity("CN"));
        list.add(new ClickEntity("EN"));
        ItemClickAdapter itemClickAdapter = new ItemClickAdapter((BaseActivity)context, R.layout.item_popoupwindow_languageselect, list,"LanguageSelectPopupWindow");
        recyclerView.setAdapter(itemClickAdapter);

        itemClickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Utils.showToast(list.get(position).getString());
                dismiss();
            }
        });
    }


    @Override
    public void showPopupWindow(View v) {
        setOffsetY(v.getHeight());
        super.showPopupWindow(v);
    }


    @Override
    protected Animation initShowAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, -DensityUtils.dp2px(getContext(), 50f), 0);
        translateAnimation.setDuration(450);
        translateAnimation.setInterpolator(new OvershootInterpolator(1));
        return translateAnimation;
    }

    @Override
    protected Animation initExitAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0, -DensityUtils.dp2px(getContext(), 50f));
        translateAnimation.setDuration(450);
        translateAnimation.setInterpolator(new OvershootInterpolator(-4));
        return translateAnimation;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popupwindow_language_select);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.linear_language_select);
    }


}
