package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.bean.ThemeItemBean;
import com.tinet.tosclientkitdemo.bean.ThemePropertyBean;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;

public class ThemeDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView chatBackground;//聊天背景
    private TextView portraitCornerRadius;//头像圆角弧度
    private TextView senderBubbleBackground;//发送者气泡颜色
    private TextView SenderBubbleRadius;//发送者气泡圆角弧度
    private TextView receiveBubbleBackground;//接收者气泡颜色
    private TextView receiveBubbleRadius;//接收者气泡圆角弧度
    private TextView title;

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_theme_detail;
    }

    @Override
    protected void initView() {
        title = findViewById(R.id.toolbar_title);
        findViewById(R.id.toolbar_back).setOnClickListener(this);

        chatBackground = findViewById(R.id.chatBackground);
        portraitCornerRadius = findViewById(R.id.portraitCornerRadius);
        senderBubbleBackground = findViewById(R.id.senderBubbleBackground);
        SenderBubbleRadius = findViewById(R.id.SenderBubbleRadius);
        receiveBubbleBackground = findViewById(R.id.receiveBubbleBackground);
        receiveBubbleRadius = findViewById(R.id.receiveBubbleRadius);
    }

    @Override
    protected void initData() {
        ThemeItemBean themeItemBean = (ThemeItemBean) getIntent().getSerializableExtra("ThemeItemBean");
        if (themeItemBean != null) {
            title.setText(getIntent().getStringExtra("title"));
            ThemePropertyBean themePropertyBean = themeItemBean.getThemePropertyBean();
            chatBackground.setText(String.valueOf(themePropertyBean.getChatBackground()));
            portraitCornerRadius.setText(String.valueOf(themePropertyBean.getPortraitCornerRadius()));
            senderBubbleBackground.setText(String.valueOf(themePropertyBean.getSenderBubbleBackground()));
            SenderBubbleRadius.setText(String.valueOf(themePropertyBean.getSenderBubbleRadius()));
            receiveBubbleBackground.setText(String.valueOf(themePropertyBean.getReceiveBubbleBackground()));
            receiveBubbleRadius.setText(String.valueOf(themePropertyBean.getReceiveBubbleRadius()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;
        }
    }
}