package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oslib.config.TOSConnectOption;
import com.tinet.oslib.listener.OnlineConnectResultCallback;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;
import com.tinet.tosclientkitdemo.ui.fragment.ChatFragment;
import com.tinet.tosclientkitdemo.utils.TLogUtils;
import com.tinet.tosclientkitdemo.utils.ToastUtils;
import com.zp.customdialoglib.loading.ProgressDialogHandler;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();

        findViewById(R.id.tv_android_doc).setOnClickListener(this);
        findViewById(R.id.tv_enter_session).setOnClickListener(this);
        findViewById(R.id.tv_enter_login).setOnClickListener(this);
        findViewById(R.id.tv_theme_list).setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_android_doc:
                startActivity(new Intent(SplashActivity.this, DevelopDocActivity.class));
                break;
            case R.id.tv_enter_session:
                enterSession();
                break;
            case R.id.tv_enter_login:
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                break;
            case R.id.tv_theme_list:
                startActivity(new Intent(SplashActivity.this, CustomStyleActivity.class));
                break;
        }
    }

    private void enterSession() {
        Map<String, Object> extraInfo = new HashMap<>();
        extraInfo.put("tinetAddress", "江苏南京");
        extraInfo.put("tinetSex", "男");
        extraInfo.put("tinetType", "用户端（安卓）");
        extraInfo.put("tinetAge", 36);

        // : 2022/6/23 改版后直接连接，无需点击登录按钮再连接
        TOSConnectOption tOSConnectOption = new TOSConnectOption();
        tOSConnectOption.setNickname("快速接入测试名称");
        tOSConnectOption.setHeadUrl("https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500");
        tOSConnectOption.setMobile("135xxxx9206");
        tOSConnectOption.setAdvanceParams(extraInfo);

        ProgressDialogHandler progressDialogHandler = new ProgressDialogHandler(SplashActivity.this, true);
        progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        TOSClientKit.connect(tOSConnectOption, new OnlineConnectResultCallback() {
            @Override
            public void onSuccess() {
                progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
                startActivity(new Intent(SplashActivity.this, SessionActivity.class));
            }

            @Override
            public void onError(int errorCode, String errorDesc) {
                TLogUtils.e(errorDesc);
                ToastUtils.showShortToast(SplashActivity.this,errorDesc);
                progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            }
        });
    }
}