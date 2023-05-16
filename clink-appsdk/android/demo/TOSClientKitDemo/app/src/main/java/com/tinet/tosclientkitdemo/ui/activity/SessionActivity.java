package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.tool.TimeUtils;
import com.tinet.oslib.OnlineServiceClient;
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
        return R.layout.activity_session;
    }

    @Override
    protected void initView() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        findViewById(R.id.toolbar_back).setOnClickListener(this);

        ChatFragment chatFragment = new ChatFragment();

        // : 2022/7/21 此处通过bundle添加商品卡片数据
        Bundle bundle = new Bundle();
        bundle.putParcelable(ChatFragment.ARGS_CARD, cardInfo());
        chatFragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, chatFragment);
        transaction.commitAllowingStateLoss();
    }


    /**
     * 卡片消息
     *
     * @return
     */
    private CardInfo cardInfo() {
        CardInfo message = new CardInfo();
        message.setSubTitle("华为P40麒麟990 5G SoC芯片 5000万超感知徕卡三摄 30倍数字变焦");
        message.setDescription("这是商品描述，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
        message.setImg(
                "https://img1.baidu.com/it/u=1963848283,2056721126&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500");
        message.setPrice("￥ 100.99");
        message.setTime(TimeUtils.getDate(System.currentTimeMillis()));
        message.setStatus("已到货");

        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("订单号", "1234567890");
        extraInfo.put("服务地区", "北京市");
        extraInfo.put("服务", "满意");
        extraInfo.put("师傅", "金师傅");
        extraInfo.put("产品类型", "电子产品");
        extraInfo.put("师傅电话", "12345678900");
        extraInfo.put("订单状态", "已完成");
        message.setExtraInfo(extraInfo);

        return message;
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