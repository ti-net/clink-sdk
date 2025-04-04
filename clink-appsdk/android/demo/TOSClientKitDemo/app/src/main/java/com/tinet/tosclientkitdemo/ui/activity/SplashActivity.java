package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oslib.config.TOSConnectOption;
import com.tinet.oslib.listener.OnlineConnectResultCallback;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;
import com.tinet.tosclientkitdemo.common.platform.PlantformInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformUtil;
import com.tinet.tosclientkitdemo.utils.TLogUtils;
import com.tinet.tosclientkitdemo.utils.ToastUtils;
import com.zp.customdialoglib.loading.ProgressDialogHandler;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvLogo;

    final static int COUNTS = 4;// 点击次数
    final static long DURATION = 1000;// 规定有效时间
    long[] mHits = new long[COUNTS];
    private boolean isClick;

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();

        findViewById(R.id.ll_android_doc).setOnClickListener(this);
        findViewById(R.id.tv_enter_session).setOnClickListener(this);
        findViewById(R.id.tv_enter_login).setOnClickListener(this);
        findViewById(R.id.ll_theme_list).setOnClickListener(this);
        mIvLogo = findViewById(R.id.iv_logo);
        mIvLogo.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_android_doc:
                startActivity(new Intent(SplashActivity.this, DevelopDocActivity.class));
                break;
            case R.id.tv_enter_session:
                enterSession();
                break;
            case R.id.tv_enter_login:
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                break;
            case R.id.ll_theme_list:
                startActivity(new Intent(SplashActivity.this, CustomStyleActivity.class));
                break;
            case R.id.iv_logo:
                continuousClick(COUNTS, DURATION);
                break;
        }
    }

    private void enterSession() {
        Map<String, Object> extraInfo = new HashMap<>();
        extraInfo.put("tinetAddress", "江苏南京");
        extraInfo.put("tinetSex", "男");
        extraInfo.put("tinetType", "用户端（安卓）");
        extraInfo.put("tinetAge", 36);
        extraInfo.put("enableMqtt", false);

        // : 2022/6/23 改版后直接连接，无需点击登录按钮再连接
        TOSConnectOption tOSConnectOption = new TOSConnectOption();
        tOSConnectOption.setVisitorId("android_visitor_demo_" + System.currentTimeMillis());
        tOSConnectOption.setNickname("快速接入测试名称");
        tOSConnectOption.setHeadUrl("https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500");
        tOSConnectOption.setMobile("135xxxx9206");
        tOSConnectOption.setAdvanceParams(extraInfo);
        isClick = true;

        ProgressDialogHandler progressDialogHandler = new ProgressDialogHandler(SplashActivity.this, true);
        progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        TOSClientKit.connect(tOSConnectOption, new OnlineConnectResultCallback() {
            @Override
            public void onSuccess() {
                progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
                if (isClick) {
                    startActivity(new Intent(SplashActivity.this, SessionActivity.class));
                }
                isClick = false;
            }

            @Override
            public void onError(int errorCode, String errorDesc) {
                isClick = false;
                TLogUtils.e(errorDesc);
                ToastUtils.showShortToast(SplashActivity.this, errorDesc);
                progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            }
        });
    }

    private void continuousClick(int count, long time) {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
            mHits = new long[COUNTS];//重新初始化数组
            showPopup();
        }
    }

    private void showPopup() {
        PopupMenu popup = new PopupMenu(this, mIvLogo);
        Menu menu = popup.getMenu();
        for (PlantformInfo info : PlantformUtil.getPlantforms(this.getApplicationContext())) {
            menu.add(info.getName());
        }
        popup.setOnMenuItemClickListener(item -> {
            PlantformUtil.updatePlantform(getApplicationContext(), item.getTitle().toString());
            restartApp();
            return false;
        });
        popup.show();
    }

    /**
     * 重启应用
     */
    private void restartApp() {

        Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }
}