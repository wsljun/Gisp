package com.giiisp.giiisp.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.entity.Constant;
import com.giiisp.giiisp.utils.AppManager;
import com.giiisp.giiisp.utils.Utils;
import com.giiisp.giiisp.view.adapter.ViewPagerAdapter;
import com.giiisp.giiisp.view.fragment.BannerRecyclerViewFragment;
import com.giiisp.giiisp.view.fragment.CollectionDownloadFragment;
import com.giiisp.giiisp.view.fragment.HomeFragment;
import com.giiisp.giiisp.view.fragment.MineFragment;
import com.giiisp.giiisp.widget.MViewPager;
import com.giiisp.giiisp.widget.recording.AppCache;
import com.giiisp.giiisp.widget.recording.Extras;
import com.jpeng.jptabbar.DensityUtils;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.JPTabItem;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.giiisp.giiisp.view.activity.PaperDetailsActivity.downloadId;
import static com.giiisp.giiisp.view.activity.PaperDetailsActivity.paperId;

/**
 * Giiisp主界面
 */
public class GiiispActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    @BindView(R.id.viewPager_giiisp)
    MViewPager viewPagerGiiisp;
    @BindView(R.id.tabLayout_giiisp)
    JPTabBar tabLayoutGiiisp;
    private String type;
    private long mBackTime;
    private JPTabItem childAt;
    List<BaseFragment> fragments = new ArrayList<>();
    private Dialog dialog;

    public MViewPager getViewPagerGiiisp() {
        return viewPagerGiiisp;
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_giiisp;
    }

    public static void actionActivity(Context context) {
        Intent sIntent = new Intent(context, GiiispActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Context context, Intent intent) {
        Intent sIntent = new Intent(context, GiiispActivity.class);
        sIntent.putExtras(intent);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Context context, String type) {
        Intent sIntent = new Intent(context, GiiispActivity.class);
        sIntent.putExtra("type", type);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(Extras.EXTRA_NOTIFICATION)) {
            initplay();
            setIntent(new Intent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        parseIntent();
    }

    @Override
    public void initData() {
        super.initData();
        type = getIntent().getStringExtra("type");
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Permission permission) throws Exception {
                        switch (permission.name) {
                            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                                if (permission.granted) {
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast("取消存储授权,不能存储录音文件");
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    Utils.showToast("您已经禁止弹出存储的授权操作,请在设置中手动开启");
                                }
                                break;
                            case Manifest.permission.RECORD_AUDIO:
                                if (permission.granted) {
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast("取消录音授权,不能录音");
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    Utils.showToast("您已经禁止弹出录音的授权操作,请在设置中手动开启");
                                }
                                break;
                           /* case Manifest.permission.SYSTEM_ALERT_WINDOW:
                                if (permission.granted) {
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast("取消悬浮窗授权");
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    Utils.showToast("您已经禁止弹出悬浮窗的授权操作,请在设置中手动开启");
                                }break;*/
                            case Manifest.permission.CAMERA:
                                if (permission.granted) {
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // Denied permission without ask never again
                                    Utils.showToast("取消照相机授权");
                                } else {
                                    // Denied permission with ask never again
                                    // Need to go to the
                                    Utils.showToast("您已经禁止弹出照相机的授权操作,请在设置中手动开启");
                                }
                                break;
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        Log.i("--->>", "onError", throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }

    @Subscribe
    public void download(String userInfoEntity) {
        Log.i("--->>", "download: " + userInfoEntity);
        switch (userInfoEntity) {
            case "collection":
                viewPagerGiiisp.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        startService(new Intent(this, PlayerService.class));
        EventBus.getDefault().register(this);
        parseIntent();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        //        stopService(new Intent(this, PlayerService.class));
        Log.i("--->>", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void initView() {

        fragments.add(HomeFragment.newInstance("home", type));
        fragments.add(BannerRecyclerViewFragment.newInstance("subscribe", "1"));
        fragments.add(BannerRecyclerViewFragment.newInstance("play", "giiisp"));
        fragments.add(CollectionDownloadFragment.newInstance("collection_fragment", "4"));
        fragments.add(new MineFragment());
        viewPagerGiiisp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, null));
        tabLayoutGiiisp.setTitles(getString(R.string.home), getString(R.string.subscribe), "", getString(R.string.collection), getString(R.string.mine)).setNormalIcons(R.mipmap.home_icon, R.mipmap.subscribe_icon, R.mipmap.main_stop, R.mipmap.collection_icon, R.mipmap.mine_icon)
                .setSelectedIcons(R.mipmap.home_select, R.mipmap.subscribe_select, R.mipmap.demonstration_play, R.mipmap.collection_select, R.mipmap.mine_select).generate();
        //        tabLayoutGiiisp.showCircleBadge(4);
        tabLayoutGiiisp.setIconSize(19);
        tabLayoutGiiisp.setUid(BaseActivity.uid);
        tabLayoutGiiisp.setContainer(viewPagerGiiisp);
        viewPagerGiiisp.setOffscreenPageLimit(4);
        tabLayoutGiiisp.setOnPageChangeListener(this);
        tabLayoutGiiisp.setListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LogInActivity.actionActivity(GiiispActivity.this);
            }
        });
        childAt = tabLayoutGiiisp.getTabItem(2);
        childAt.getIconView().getLayoutParams().width = DensityUtils.dp2px(this, 40);
        childAt.getIconView().getLayoutParams().height = DensityUtils.dp2px(this, 40);
        ((RelativeLayout.LayoutParams) childAt.getIconView().getLayoutParams()).topMargin = DensityUtils.dp2px(this, 2);
        childAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initplay();
            }
        });
        //        showDialog();
        //        tabLayoutGiiisp.setOnPageChangeListener();
    }

    private void initplay() {
        if (TextUtils.isEmpty(paperId) && TextUtils.isEmpty(downloadId)) {
            viewPagerGiiisp.setCurrentItem(2);
        } else {
            if (!TextUtils.isEmpty(paperId)) {
                ArrayList<String> map = new ArrayList<>();
                map.add(PaperDetailsActivity.paperId);
                PaperDetailsActivity.actionActivity(GiiispActivity.this, PaperDetailsActivity.id, map, "online_paper");
            } else if (!TextUtils.isEmpty(downloadId)) {
                ArrayList<String> map = new ArrayList<>();
                map.add(PaperDetailsActivity.downloadId);
                PaperDetailsActivity.actionActivity(GiiispActivity.this, PaperDetailsActivity.id, map, "download_paper");
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (AppCache.getPlayService() != null && childAt != null) {
            if (AppCache.getPlayService().isPlaying()) {
                childAt.getIconView().setImageResource(R.mipmap.demonstration_play);

            } else {
                childAt.getIconView().setImageResource(R.mipmap.main_stop);
            }

        }
    }

    @Override
    public void addDownload() {
        super.addDownload();
        for (BaseFragment fragment : fragments) {
            fragment.addDownload();
        }
    }

    @Override
    public void collection(int id, int integer, String type, String isFollowed,
                           int parentPosition, int position) {
        for (BaseFragment fragment : fragments) {

            switch (type) {
                case "collection_paper":
                case "collection_summary":
                    if (fragment.getType().equals("collection_fragment")) {
                        fragment.collection(id, integer, type, isFollowed, parentPosition, position);
                    }
                    break;
                default:
                    if (fragment.getType().equals(type)) {
                        fragment.collection(id, integer, type, isFollowed, parentPosition, position);
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long sTime = System.currentTimeMillis();
            if (sTime - mBackTime < 3000) {
                AppManager.getAppManager().AppExit(this);
                return true;
            } else {
                mBackTime = sTime;
                Utils.showToast(R.string.prompt_exit_app);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (fragments.size() > position) {
            BaseFragment baseFragment = fragments.get(position);
            if (baseFragment instanceof MineFragment) {
//                ((MineFragment) baseFragment).initData();
            } else if (baseFragment instanceof BannerRecyclerViewFragment) {
                if (Objects.equals("play", baseFragment.getType()))
                    ((BannerRecyclerViewFragment) baseFragment).onRefresh();
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_update, null);
        TextView tvLater = view.findViewById(R.id.tv_later);
        TextView tvUpgrade = view.findViewById(R.id.tv_upgrade);
        tvLater.setOnClickListener(this);
        tvUpgrade.setOnClickListener(this);
        dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
      /*  Window window = dialog.getWindow();
        // 设置显示动画
        if (window != null) {
            window.setWindowAnimations(R.style.main_menu_animstyle);
            WindowManager.LayoutParams wl = window.getAttributes();
        *//*    wl.x = 0;
            wl.y = getWindowManager().getDefaultDisplay().getHeight();*//*
            // 以下这两句是为了保证按钮可以水平满屏
            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
            wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
            // 设置显示位置
            dialog.onWindowAttributesChanged(wl);
        }*/
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View view) {

    }
}
