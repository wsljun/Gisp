package com.giiisp.giiisp.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.base.BaseActivity;
import com.giiisp.giiisp.base.BaseFragment;
import com.giiisp.giiisp.entity.UserInfoEntity;
import com.giiisp.giiisp.view.fragment.AgreementFragment;
import com.giiisp.giiisp.view.fragment.AtLinkFragment;
import com.giiisp.giiisp.view.fragment.BannerRecyclerViewFragment;
import com.giiisp.giiisp.view.fragment.CollectionDownloadFragment;
import com.giiisp.giiisp.view.fragment.MailboxAuthenticationFragment;
import com.giiisp.giiisp.view.fragment.TabLayoutFragment;
import com.giiisp.giiisp.view.fragment.UserInfoFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Fragment 的Activity基类
 * Created by Thinkpad on 2017/5/31.
 */

public class FragmentActivity extends BaseActivity {
    String type;
    private String oid;
    BaseFragment fragment;

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_fragment;
    }

    public static void actionActivity(Context context, String type) {
        Intent sIntent = new Intent(context, FragmentActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sIntent.putExtra("type", type);
        context.startActivity(sIntent);
    }

    public static void actionActivity(Activity context, String type) {
        Intent sIntent = new Intent(context, FragmentActivity.class);
//                sIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sIntent.putExtra("type", type);
        context.startActivityForResult(sIntent, 1001);
    }

    public static void actionActivity(Context context, String type, String oid) {
        Intent sIntent = new Intent(context, FragmentActivity.class);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sIntent.putExtra("type", type);
        sIntent.putExtra("oid", oid);
        context.startActivity(sIntent);
    }


    @Override
    public void initData() {
        super.initData();
        type = getIntent().getStringExtra("type");
        oid = getIntent().getStringExtra("oid");
    }

    @Override
    public void initView() {
        if (type != null) {
            switch (type) {
                case "news":
                case "qa":
                    fragment = TabLayoutFragment.newInstance(type, "");
                    break;
                case "he":
                case "plays":
                case "play":
                case "scholar_list":
                case "wait_dubbing":
                case "my_paper":
                case "mine_follow":
                case "mine_scholar":
                case "paper_list":
                case "summary_list":
                case "my_review":
                    fragment = BannerRecyclerViewFragment.newInstance(type, oid);
                    break;
                case "at":
                case "link":
                    fragment = AtLinkFragment.newInstance(type, "");
                    break;
                case "download_activity":
                    fragment = CollectionDownloadFragment.newInstance(type, "");
                    break;
                case "verified_edit_info":
                    fragment = UserInfoFragment.newInstance(type, "");
                    break;
                case "webView_home":
                    fragment = AgreementFragment.newInstance(type, oid);
                    break;
                case "mailbox_authentication":
                    fragment = new MailboxAuthenticationFragment();
                    break;
            }
        }
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    @Override
    public void addDownload() {
        Log.i("--->>", "addDownload: ");
        super.addDownload();
        fragment.addDownload();
    }

    @Override
    public void downloadCompleted() {
        super.downloadCompleted();
        fragment.downloadCompleted();
    }

    public void initBus() {
        Log.i("--->>", "initBus: ");
        EventBus.getDefault().post(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (fragment != null && fragment instanceof BannerRecyclerViewFragment)
            ((BannerRecyclerViewFragment) fragment).onRefresh();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && type.equals("webView_home")) {
            if (fragment instanceof AgreementFragment) {
                WebView webView = ((AgreementFragment) fragment).getWebview();
                if (webView.canGoBack()) {
                    webView.goBack();//返回上一页面
                    return true;
                } else {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void collection(int id, int integer, String type, String isFollowed, int parentPosition, int position) {
        fragment.collection(id, integer, type, isFollowed, parentPosition, position);
    }


    public void submitFollow(String isFollowed, String id) {
        switch (type) {
            case "mine_follow":
            case "mine_scholar":
                if (fragment instanceof BannerRecyclerViewFragment) {
                    ((BannerRecyclerViewFragment) fragment).submitFollow(isFollowed, id);
                }
                break;
        }

    }


}
