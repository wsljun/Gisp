package com.giiisp.giiisp.widget;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.utils.Utils;

import razerdp.basepopup.BasePopupWindow;

/**
 * 公司全面显示Window
 * Created by Thinkpad on 2017/6/1.
 */

public class CompanyNamePopupWindow extends BasePopupWindow implements View.OnClickListener {

    public CompanyNamePopupWindow(Activity context, String companyName) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, companyName);
    }

    public CompanyNamePopupWindow(Activity context, int w, int h, String companyName) {
        super(context, w, h);
        initView(context, companyName);
    }

    private void initView(Activity context, String companyName) {
        TextView textView = (TextView) findViewById(R.id.tv_company_name);
        textView.setText(companyName);
        textView.setOnClickListener(this);
        getPopupWindowView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dismiss();
                return false;
            }
        });
    }


    @Override
    public void showPopupWindow(View v) {
        setOffsetX(v.getWidth());
        setOffsetY(-v.getHeight());
        super.showPopupWindow(v);
    }

    @Override
    protected Animation initShowAnimation() {
        return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public Animation initExitAnimation() {
        return getScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popupwindow_company_name);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.linear_company_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_company_name:

                Utils.showToast(((TextView) v).getText().toString());
                dismiss();
                break;
        }
    }
}
