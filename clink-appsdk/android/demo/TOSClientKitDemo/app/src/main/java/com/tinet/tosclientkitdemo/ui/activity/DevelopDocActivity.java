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
        mWebView.loadUrl("file:///android_asset/在线客服SDK_Android_集成文档.html");
    }

    private void initWebView() {
        //设置为ChromeClinet 才能执⾏js代码
        WebChromeClient webChromeClient = new WebChromeClient();
        mWebView.setWebChromeClient(webChromeClient);
        //设置开启js⽀持
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 是否⽀持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }
}