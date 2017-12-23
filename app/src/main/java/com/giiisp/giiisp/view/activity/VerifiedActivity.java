package com.giiisp.giiisp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.EditInfoFragment;
import com.giiisp.giiisp.view.fragment.UserUploadFragment;
import com.giiisp.giiisp.view.fragment.VerifiedCompleteFragment;
import com.giiisp.giiisp.view.fragment.WitnessFragment;
import com.giiisp.giiisp.widget.MViewPager;
import com.giiisp.giiisp.widget.ScreenPopupWindow;
import com.giiisp.giiisp.widget.StepsView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 认证页面
 * Created by Thinkpad on 2017/5/8.
 */

public class VerifiedActivity extends BaseActivity implements ScreenPopupWindow.SelectListening {
    @BindView(R.id.stepsView)
    StepsView stepsView;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.line_banner)
    FrameLayout lineBanner;
    private String[] labels = new String[4];
    @BindView(R.id.viewPager_verified)
    MViewPager viewPagerVerified;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    ScreenPopupWindow creenPopupWindow;

    public List<MultipartBody.Part> parts;
    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_verified;
    }

    public MViewPager getViewPagerVerified() {
        return viewPagerVerified;
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, VerifiedActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    private void addStatusBarView() {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this));
        ViewGroup decorView = (ViewGroup) findViewById(android.R.id.content);
        decorView.addView(view, params);
    }

    public int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    @Override
    public void initView() {
        addStatusBarView();
        tvTitle.setText("编辑基本信息");
        labels[0] = getString(R.string.essential_information);
        labels[1] = getString(R.string.upload_msg);
        labels[2] = getString(R.string.select_a_witness);
        labels[3] = getString(R.string.verified_complete);
        stepsView.setCompletedPosition(0 % labels.length)
                .setLabels(labels)
                .setBarColorIndicator(getResources().getColor(R.color.colorGray))
                .setProgressColorIndicator(getResources().getColor(R.color.colorAuxiliary))
                .setLabelColorIndicator(getResources().getColor(R.color.colorGray))
                .drawView();

        viewPagerVerified.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                stepsView.setCompletedPosition(position);
                stepsView.drawView();
                switch (position) {
                    case 0:
                        tvRight.setText("保存");
                        ivMenu.setVisibility(View.GONE);
                        break;
                    case 1:
                        break;
                    case 2:
                        ivMenu.setVisibility(View.VISIBLE);
                        ivMenu.setImageResource(R.mipmap.screen);
                        tvRight.setText("");
                        break;
                    case 3:
                        ivMenu.setVisibility(View.VISIBLE);
                        ivMenu.setImageResource(R.mipmap.guide_user_icon);
                        tvRight.setText("");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        List<BaseFragment> fragments = new ArrayList<>();
        editInfoFragment = new EditInfoFragment();
        userUploadFragment = new UserUploadFragment();
        witnessFragment = new WitnessFragment();
        verifiedCompleteFragment = new VerifiedCompleteFragment();

        fragments.add(editInfoFragment);
        fragments.add(userUploadFragment);
        fragments.add(witnessFragment);
        fragments.add(verifiedCompleteFragment);
        viewPagerVerified.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, null));

        creenPopupWindow = new ScreenPopupWindow(this);
        creenPopupWindow.setSelectListening(this);

        parts = new ArrayList<>();
    }

    EditInfoFragment editInfoFragment;
    UserUploadFragment userUploadFragment;
    WitnessFragment witnessFragment;
    VerifiedCompleteFragment verifiedCompleteFragment;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == 203) {
            editInfoFragment.initData();
        } else if (requestCode == 1002 && resultCode == 203) {
            editInfoFragment.initData();
        }
    }

    @OnClick({R.id.tv_back, R.id.fl_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.fl_menu:
                switch (viewPagerVerified.getCurrentItem()) {
                    case 0:
                        break;
                    case 2:
                        creenPopupWindow.showPopupWindow(lineBanner);
                        break;
                    case 1:
                        break;
                }
                break;
        }
    }

    @Override
    public void myListening(Set<Integer> set) {
        ArrayMap<String, Object> map = new ArrayMap<>();
        for (Integer i : set) {
            if (i == 0) {
                map.put("major", 1);
            }
            if (i == 1) {
                map.put("organization", 1);
            }
            if (i == 2) {
                map.put("school", 1);
            }
            if (i == 3) {
                map.put("rmajor", 1);
            }
        }

        if (map.size() > 0) {
            map.put("uid", uid);
            witnessFragment.updateData(map);
        }
    }

}
