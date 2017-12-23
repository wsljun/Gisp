package com.giiisp.giiisp.widget;

import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.view.activity.DubbingPermissionActivity;
import com.pnikosis.materialishprogress.ProgressWheel;

import razerdp.basepopup.BasePopupWindow;

/**
 * 公司全面显示Window
 * Created by Thinkpad on 2017/6/1.
 */

public class ProgressPopupWindow extends BasePopupWindow  {


    public ProgressPopupWindow(BaseActivity context) {
        super(context);
        /**全屏popup*/
        setPopupWindowFullScreen(true);
        ProgressWheel progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        if (context instanceof DubbingPermissionActivity) {
            ((DubbingPermissionActivity) context).setProgressWheel(progressWheel);
        }
    }


    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }


    @Override
    public View onCreatePopupView() {
        View popupById = createPopupById(R.layout.popupwindow_progress);
        popupById.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                return false;
            }
        });
        return popupById;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.linear_progress_wheel);
    }


}
