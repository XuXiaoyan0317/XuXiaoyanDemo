package com.lanou3g.liwushuodemo.home;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.R;

/**
 * Created by dllo on 16/5/27.
 */
public class ImageWebActivity extends BaseActivity{
    private WebView webView;
    @Override
    public int setLayout() {
        return R.layout.activity_image_web;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.image_webview);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("webPath"));

    }
}
