package com.lanou3g.liwushuodemo.home;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.R;

/**
 * Created by dllo on 16/5/21.
 */
public class ListDetilActivity extends BaseActivity {
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.activity_list_detil;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.list_detil_web);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(intent.getStringExtra("url"));//精选页recyclerview的攻略详情
//        Log.d("@@@@@@@@", intent.getStringExtra("url"));
        webView.loadUrl(intent.getStringExtra("imagePath"));//轮播图的攻略详情
        webView.loadUrl(intent.getStringExtra("listPath"));//其他频道的攻略详情

    }
}
