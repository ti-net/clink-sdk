package com.tinet.oskit.aty;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tinet.oskit.R;
import com.tinet.oskit.fragment.ChatLeaveMessageFragment;

/**
 * @ProjectName: TIMSDK
 * @ClassName: CaptureAty
 * @Author: liuzr
 * @CreateDate: 2021-08-26 14:10
 * @Description: 留言
 */
public class ChatLeaveMessageAty extends AppCompatActivity {

    private View toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_chat_leave_message);

        //因为这是API23之后才能改变的，所以你的判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取窗口区域
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ChatLeaveMessageFragment fragment = new ChatLeaveMessageFragment();
        fragment.setArguments(getIntent().getExtras());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.tinetContent, fragment, "content");
        transaction.commit(); // 提交创建Fragment请求

        toolbar = findViewById(R.id.toolbar_back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setTitle(CharSequence title) {
    }
}
