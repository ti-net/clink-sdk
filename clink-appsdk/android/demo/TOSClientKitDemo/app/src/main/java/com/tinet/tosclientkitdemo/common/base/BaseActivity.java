package com.tinet.tosclientkitdemo.common.base;

import android.graphics.Color;
import android.os.Bundle;

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
        initView();
        initData();
    }

    protected abstract int getLayoutId(@Nullable Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();
}
