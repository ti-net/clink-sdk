package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.tool.TimeUtils;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.listener.SessionInfoResult;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.bean.SessionInfo;
import com.tinet.threepart.tools.TUIUtils;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.app.App;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;
import com.tinet.tosclientkitdemo.ui.fragment.ChatFragment;

import java.util.HashMap;

public class SessionActivity extends BaseActivity implements View.OnClickListener {

    private TextView toolbarTitle;

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        setTheme(switchTheme());
        return R.layout.activity_session;
    }

    /**
     * 切换不同主题
     *
     * @return
     */
    private int switchTheme() {
        switch (App.CHOOSE_THEME_INDEX) {
            case 0:
                return R.style.Theme_TIMSDK_LEAVE_default;
            case 1:
                return R.style.Theme_TIMSDK_LEAVE_blue;
            case 2:
                return R.style.Theme_TIMSDK_LEAVE_yellow;
            case 3:
                return R.style.Theme_TIMSDK_LEAVE_red;
            case 4:
                return R.style.Theme_TIMSDK_LEAVE_green;
        }
        return R.style.Theme_TIMSDK_LEAVE_default;
    }

    @Override
    protected void initView() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        findViewById(R.id.toolbar_back).setOnClickListener(this);

        ChatFragment chatFragment = new ChatFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, chatFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

        updateStatus(TOSClientKit.getCurrentOnlineStatus());

        //在线状态变化事件
        TOSClientKit
                .setOnlineStatusListener(new OnlineMessageManager.OnlineStatusListener() {
                    @Override
                    public void onStatusChanged(int status) {
                        updateStatus(status);
                    }
                });
    }


    private void updateStatus(int status) {
        TUIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                toolbarTitle.setText(
                        "在线客服(" + (status == OnlineMessageManager.STATUS_ROBOT ? "机器人"
                                : (status == OnlineMessageManager.STATUS_ONLINE ? "人工座席"
                                : "--"))
                                + ")");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;
        }
    }
}