package com.tinet.oskit.aty;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tinet.oskit.R;
import com.tinet.oskit.fragment.VideoPlayFragment;

/**
 * @ProjectName: TIMSDK
 * @ClassName: CaptureAty
 * @Author: liuzr
 * @CreateDate: 2021-08-26 14:10
 * @Description: 视频播放器
 */
public class VideoPlayAty extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_image);

        //因为这是API23之后才能改变的，所以你的判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取窗口区域
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        VideoPlayFragment videoPlayFragment = new VideoPlayFragment();
        videoPlayFragment.setArguments(getIntent().getExtras());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.viewImage, videoPlayFragment);
        transaction.commitAllowingStateLoss(); // 提交创建Fragment请求
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
