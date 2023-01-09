package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;

public class DevelopDocActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_develop_doc;
    }

    @Override
    protected void initView() {

        ((TextView) findViewById(R.id.toolbar_title)).setText("Android 集成文档");

        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mWebView = findViewById(R.id.wv_develop_doc);
        initWebView();
    }

    @Override
    protected void initData() {
        //加载本地html
        mWebView.loadUrl("https://develop.clink.cn/develop/mobile/chat-mobile.html#_android%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3");
    }

    private void initWebView() {
        //设置开启js⽀持
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 是否⽀持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDomStorageEnabled(true);//打开DOM存储API

        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
    }
}