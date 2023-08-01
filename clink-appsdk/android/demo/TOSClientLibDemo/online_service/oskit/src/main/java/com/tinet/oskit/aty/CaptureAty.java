package com.tinet.oskit.aty;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tinet.oskit.R;

/**
 * @ProjectName: TIMSDK
 * @ClassName: CaptureAty
 * @Author: liuzr
 * @CreateDate: 2021-08-26 14:10
 * @Description: 拍摄界面，该界面只对CaptureFragment进行简单的包装，用户集成时，可以自行实现该界面。
 */
public class CaptureAty extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_capture);

        //因为这是API23之后才能改变的，所以你的判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取窗口区域
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
