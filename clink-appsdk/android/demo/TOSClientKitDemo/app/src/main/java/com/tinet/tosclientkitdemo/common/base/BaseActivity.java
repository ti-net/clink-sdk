package com.tinet.tosclientkitdemo.common.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: BaseActivity
 * @Author: zhangping
 * @CreateDate: 2022/7/4 10:55
 * @Description: 描述说明
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutId(savedInstanceState));
        super.onCreate(savedInstanceState);
        setDefaultStatusBar();
        initView();
        initData();
    }

    private void setDefaultStatusBar() {
        //因为这是API23之后才能改变的，所以你的判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取窗口区域
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //设置状态栏颜色
            window.setStatusBarColor(Color.WHITE);

            //设置显示为白色背景，黑色字体
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected abstract int getLayoutId(@Nullable Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();
}
