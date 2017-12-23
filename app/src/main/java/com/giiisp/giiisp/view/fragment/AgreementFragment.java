package com.giiisp.giiisp.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.giiisp.giiisp.R;
import com.giiisp.giiisp.api.UrlConstants;
import com.giiisp.giiisp.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户协议页面
 * Created by Android on 2016/12/2.
 */

public class AgreementFragment extends BaseFragment {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_banner)
    FrameLayout lineBanner;
    @BindView(R.id.webView_bg)
    View webViewBg;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String type;
    private String string;

    public WebView getWebview() {
        return webview;
    }

    public void setWebview(WebView webview) {
        this.webview = webview;
    }

    public static AgreementFragment newInstance(String param1, String param2) {
        AgreementFragment fragment = new AgreementFragment();
        Bundle args = new Bundle();
        args.putString("1005", param1);
        args.putString("1006", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        webview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        webview.onPause();
    }

    @Override
    public void initData() {
        super.initData();
        type = getArguments().getString("1005");
        string = getArguments().getString("1006");
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar == null)
                    return;
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    // 加载中
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }

            }
        });
        if (type != null) {
            switch (type) {
                case "login_webView":
                    tvTitle.setText(getString(R.string.user_agreement));
                    webview.loadUrl(UrlConstants.RequestUrl.AGREEMENT);
                    break;
                case "webView_home":
//                    lineBanner.setVisibility(View.GONE);
//                    webViewBg.setVisibility(View.VISIBLE);
                    WebSettings webSettings = webview.getSettings();
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }


                    });

                    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                    webSettings.setJavaScriptEnabled(true);

                    //支持插件
                    webSettings.setPluginState(WebSettings.PluginState.ON);
                    //设置自适应屏幕，两者合用
                    webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
                    webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

                    //缩放操作
                    webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                    webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
                    webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

                    //其他细节操作
                    webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
                    webSettings.setAllowFileAccess(true); //设置可以访问文件
                    webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
                    webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
                    webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

                    webview.loadUrl(string);
                    break;
            }
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_agreement;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.tv_back)
    public void onClick() {
        if (type != null) {
            switch (type) {
                case "login_webView":
                    getLogInActivity().getVpLogin().setCurrentItem(getLogInActivity().getAgreement());
                    break;
                case "webView_home":
                    getActivity().finish();
                    break;
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
