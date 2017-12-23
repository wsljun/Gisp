package com.giiisp.giiisp.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.adapter.ClickEntity;
import com.giiisp.giiisp.view.adapter.ItemClickAdapter;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Thinkpad on 2017/6/1.
 */

public class PaperPalyPopupWindow extends BasePopupWindow {

    public PaperPalyPopupWindow(BaseActivity context) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public PaperPalyPopupWindow(BaseActivity context, int w, int h) {
        super(context, w, h);
        initView(context);
    }

    private void initView(BaseActivity context) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        List<ClickEntity> list = new ArrayList<>();
        list.add(new ClickEntity());
        list.add(new ClickEntity());
        ItemClickAdapter itemClickAdapter = new ItemClickAdapter(context, R.layout.item_duration_size, list,"");
        recyclerView.setAdapter(itemClickAdapter);

        itemClickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Utils.showToast("点击了第"+position + "个item");
                dismiss();
            }
        });
    }


    @Override
    public void showPopupWindow(View v) {
        setOffsetX(-getWidth() - v.getWidth() / 2);
                setOffsetY(-v.getHeight());
        super.showPopupWindow(v);
    }

    @Override
    protected Animation initShowAnimation() {
        return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public Animation initExitAnimation() {
        return getScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
    }

    @Override
    public View onCreatePopupView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_duration_size, null);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.linear_duration_size);
    }

}
