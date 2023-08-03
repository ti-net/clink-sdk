package com.tinet.online;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.manager.AudioPopupManager;
import com.tinet.oskit.manager.AudioPopupManager.AudioListener;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.listener.SessionInfoListener;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.tinet.oslib.manager.OnlineQueueManager;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.bean.SessionInfo;
import com.tinet.oslib.model.message.content.ChatBridgeMessage;
import com.tinet.oslib.model.message.content.ChatLocationMessage;
import com.tinet.oslib.model.message.content.ChatQueueMessage;
import com.tinet.threepart.tools.TUIUtils;
import com.tinet.threepart.tools.TimeUtils;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView tvQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


//        AudioPopupManager.registerAudioListener(new AudioListener() {
//
//            ImageView iv;
//            TextView tv;
//
//            View v;
//
//            @Override
//            public void initView(View view) {
//                iv = view.findViewById(R.id.iv);
//                tv = view.findViewById(R.id.tv);
//                v= view.findViewById(R.id.view);
//            }
//
//            @Override
//            public void startRecording() {
//                tv.setText("可以松开手指");
//                v.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.ti_guess_text_color));
//            }
//
//            @Override
//            public void timeoutTip() {
//                tv.setText("超时");
//            }
//
//            @Override
//            public void cancelRecording() {
//                tv.setText("松开手指，取消发送");
//                v.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.ti_line_up_text_color));
//            }
//
//            @Override
//            public void shortRecording() {
//                Toast.makeText(MainActivity.this,"录制时间太短了",Toast.LENGTH_SHORT).show();;
//            }
//
//            @Override
//            public void onAudioChanged(int db) {
//                tv.setText("录音中，音量："+db);
//            }
//        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvQueue = findViewById(R.id.tvQueue);

        Bundle bundle = getIntent().getExtras();

        bundle.putParcelable(ChatFragment.ARGS_CARD, cardInfo());


        ChatFragment chatFragment = new ChatFragment();
//        chatFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, chatFragment);
        transaction.commitAllowingStateLoss();

        updateStatus(OnlineMessageManager.getCurrentOnlineStatus());

        OnlineQueueManager.registerQueueListener(new OnlineQueueManager.OnlineQueueListener() {
            @Override
            public void chatBridge(ChatBridgeMessage message) {
                tvQueue.setText("排队结束，进入人工座席");
            }

            @Override
            public void chatQueue(ChatQueueMessage message) {
                tvQueue.setText("排队开始："+message.getLocation());
            }

            @Override
            public void chatQueueLocation(ChatLocationMessage message) {
                tvQueue.setText("排队变化："+message.getLocation());
            }

            @Override
            public void exitChatQueue() {
                tvQueue.setText("排队结束");
            }
        });

        //在线状态变化事件
        TOSClientKit
                .setOnlineStatusListener(new OnlineMessageManager.OnlineStatusListener() {
                    @Override
                    public void onStatusChanged(int status) {
                        updateStatus(status);
                    }
                });

        OnlineServiceClient.getCurrentSessionInfo(new SessionInfoListener() {
            @Override
            public void sessionInfo(SessionInfo sessionInfo) {
                TUIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        toolbar.setSubtitle(sessionInfo.getMainUniqueId());
                    }
                });
            }
        });
    }

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

    private void updateStatus(int status) {
        TUIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(
                        "在线客服(" + (status == OnlineMessageManager.STATUS_ROBOT ? "机器人"
                                : (status == OnlineMessageManager.STATUS_ONLINE ? "人工座席"
                                : "--"))
                                + ")");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // : 2022/8/9 资源释放，避免内存泄露
        TOSClientKit.getCurrentSessionInfo(null);
        TOSClientKit.setOnlineStatusListener(null);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}