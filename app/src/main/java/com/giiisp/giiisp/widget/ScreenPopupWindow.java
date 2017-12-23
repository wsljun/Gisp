package com.giiisp.giiisp.widget;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.utils.DensityUtils;
import com.giiisp.giiisp.view.activity.VerifiedActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import razerdp.basepopup.BasePopupWindow;

/**
 * 认证选择证明人window
 * Created by Thinkpad on 2017/6/1.
 */

public class ScreenPopupWindow extends BasePopupWindow implements View.OnClickListener {
    private TagFlowLayout tagFlowLayout;

    private Activity activity;

    public ScreenPopupWindow(final Activity context) {
        super(context);
        this.activity = context;
        setBackPressEnable(false);
        setDismissWhenTouchOuside(true);
        List<String> list = new ArrayList<>();
        list.add("同领域");
        list.add("机构");
        list.add("学校");
        list.add("专业");
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.tag_flowlayout);

        TagAdapter<String> tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(context);
                TextView tv = (TextView) mInflater.inflate(R.layout.item_screen,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        tagFlowLayout.setAdapter(tagAdapter);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_empty).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    public void showPopupWindow(View v) {
        setOffsetY(v.getHeight());
        super.showPopupWindow(v);
    }


    @Override
    protected Animation initShowAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, -DensityUtils.dp2px(getContext(), 350f), 0);
        translateAnimation.setDuration(450);
        translateAnimation.setInterpolator(new OvershootInterpolator(1));
        return translateAnimation;
    }

    @Override
    protected Animation initExitAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0, -DensityUtils.dp2px(getContext(), 350f));
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
        return createPopupById(R.layout.layout_screen_witness);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.linear_screen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_empty:
                tagFlowLayout.getSelectedList().clear();
                tagFlowLayout.onChanged();
                break;
            case R.id.tv_confirm:
                selectListening.myListening(tagFlowLayout.getSelectedList());
                dismiss();
                break;
        }
    }

    public interface SelectListening {
        void myListening(Set<Integer> set);
    }

    SelectListening selectListening = null;

    public void setSelectListening(SelectListening selectListening) {
        this.selectListening = selectListening;
    }
}
