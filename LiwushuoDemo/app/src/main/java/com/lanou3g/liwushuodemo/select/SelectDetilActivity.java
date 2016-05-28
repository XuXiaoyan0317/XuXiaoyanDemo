package com.lanou3g.liwushuodemo.select;

import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.R;

/**
 * Created by dllo on 16/5/21.
 */
public class SelectDetilActivity extends BaseActivity {
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.activity_select_detil;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.select_webview);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(intent.getStringExtra("selectPath"));
        webView.loadUrl(intent.getStringExtra("getPurchasePath"));


    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
}
