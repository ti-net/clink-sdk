package com.tinet.online;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.tinet.online.test.PlantformInfo;
import com.tinet.online.test.PlantformUtil;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oslib.config.TOSConnectOption;
import com.tinet.oslib.listener.OnLastMessageListener;
import com.tinet.oslib.listener.OnlineConnectResultCallback;
import com.tinet.oslib.listener.OnlineConnectStatusListener;
import com.tinet.oslib.listener.OnlineDisconnectListener;


import com.tinet.oslib.manager.OnlineQuickManager;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.timclientlib.manager.TIMMqttManager;
import com.tinet.timclientlib.utils.TNtpUtils;
import com.tinet.timclientlib.utils.TLogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName:
 * @ClassName: LoginActivity
 * @Author: liuzr
 * @CreateDate: 2021-08-26 14:35
 * @Description:
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etId, etNickname, etHeader, etPhone;
    private TextView messagettype;
    private CheckBox checkbox;
    private TextView tvPlantform, connectionStatus;
    private String id, nickname, headerUrl, phone;
    private OnlineConnectStatusListener onlineConnectStatusListener;

    ImageView ivImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_login);

        /*ivImage = findViewById(R.id.ivImage);*/
        etId = findViewById(R.id.etId);
        etNickname = findViewById(R.id.etNickname);
        etHeader = findViewById(R.id.etHeader);
        etPhone = findViewById(R.id.etPhone);
        messagettype = findViewById(R.id.messagettype);
        checkbox = findViewById(R.id.checkbox);
        tvPlantform = findViewById(R.id.tvPlantform);
        connectionStatus = findViewById(R.id.connectionStatus);

        tvPlantform.setOnClickListener(v -> showPopup());

        PlantformInfo info = PlantformUtil.getPlantform(this.getApplicationContext());
        if (null != info) {
            tvPlantform.setText(info.getName());
        } else {
            tvPlantform.setText(String.valueOf(Constant.enterpriseId));
        }

        quickEnter(false);

        id = etId.getText().toString();
        nickname = TextUtils.isEmpty(etNickname.getText().toString()) ? "帅哥" : etNickname.getText().toString();
        headerUrl = "";//"https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500";

        /*Glide.with(this).load(headerUrl).into(ivImage);*/

        Map<String, Object> extraInfo = new HashMap<>();
        extraInfo.put("地址", "江苏1111南京");
        extraInfo.put("性别", "女11");
        extraInfo.put("tinetType", "用户端（安卓）");
        extraInfo.put("tinetAge", 36);
        extraInfo.put("enableMqtt", false);

        phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            phone = "13851415555";
        }

        try {
            TIMMqttManager.getInstance().updateQueueSize(2);
        } catch (Exception e) {
        }

        findViewById(R.id.btnLogin).setOnClickListener(v -> {

            id = etId.getText().toString();
            nickname = TextUtils.isEmpty(etNickname.getText().toString()) ? "帅哥" : etNickname.getText().toString();

            TLogUtils.d("进入客服," + TNtpUtils.getRealTimeMillis());

//            phone = etPhone.getText().toString();

            // : 2022/6/23 改版后直接连接，无需点击登录按钮再连接
            TOSConnectOption tOSConnectOption = new TOSConnectOption();
            tOSConnectOption.setVisitorId(id);
            tOSConnectOption.setNickname(nickname);
            tOSConnectOption.setHeadUrl(headerUrl);
            tOSConnectOption.setMobile(phone);
            tOSConnectOption.setAdvanceParams(extraInfo);

            TOSClientKit.connect(tOSConnectOption, new OnlineConnectResultCallback() {
                @Override
                public void onSuccess() {
                    TLogUtils.d("进入聊天," + TNtpUtils.getRealTimeMillis());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if (checkbox.isChecked()) {
                        intent.putExtra(ChatFragment.ARGS_CARD, cardInfo());
                    }
                    startActivity(intent);
                }

                @Override
                public void onError(int errorCode, String errorDesc) {
                }
            });

        });

        TOSClientKit.disConnect(false, null);

        findViewById(R.id.btnLoginOut).setOnClickListener(v -> TOSClientKit.disConnect(false, new OnlineDisconnectListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(Exception e) {

            }
        }));

        findViewById(R.id.btnLoginOutPush)
                .setOnClickListener(v ->
                        TOSClientKit.disConnect(true, null));

        TOSClientKit.getLastMessageInfo(etId.getText().toString(), new OnLastMessageListener() {

            @Override
            public void onLastMessage(int unreadCount, String lastMessage) {
                TLogUtils.i("chatRecord", "未读数=" + unreadCount + "   " + lastMessage);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messagettype.setText("未读数=" + unreadCount + "   " + lastMessage);
                    }
                });
            }
        });

        onlineConnectStatusListener = new OnlineConnectStatusListener() {
            @Override
            public void onConnecting() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connectionStatus.setText("当前连接状态: " + "连接中");
                    }
                });
            }

            @Override
            public void onConnected() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connectionStatus.setText("当前连接状态: " + "连接成功");
                    }
                });
            }

            @Override
            public void onDisconnected() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connectionStatus.setText("当前连接状态: " + "连接断开");
                    }
                });
            }

            @Override
            public void onReConnecting() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connectionStatus.setText("当前连接状态: " + "断线重连中");
                    }
                });
            }

            @Override
            public void onReconnected() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connectionStatus.setText("当前连接状态: " + "断线重连成功");
                    }
                });
            }

            @Override
            public void onKickOut() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        connectionStatus.setText("当前连接状态: " + "被踢下线");
                    }
                });
            }
        };

        TOSClientKit.addOnlineConnectStatusListener(onlineConnectStatusListener);

        TLogUtils.i("getSDKVersion:" + TOSClientKit.getSDKVersion());

    }

    /**
     * 快捷入口测试数据
     *
     * @param enable
     */
    private void quickEnter(boolean enable) {
        if (enable) {
            ArrayList message = new ArrayList<>();
            message.add(new LabeInfo("订单号", "1234567890"));
            message.add(new LabeInfo("服务地区", "北京市"));
            message.add(new LabeInfo("服务", "满意"));
            message.add(new LabeInfo("师傅", "金师傅"));
            message.add(new LabeInfo("产品类型", "电子产品"));
            message.add(new LabeInfo("师傅电话", "12345678900"));
            message.add(new LabeInfo("订单状态", "已完成"));
            TOSClientKit.updateSessionWindowQuickEntrys(message);
        } else {
            OnlineQuickManager.getInstance().clearQuicks();
        }
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
//        message.setTime(TimeUtils.getDate(TNtpUtils.getRealTimeMillis()));
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

    private void showPopup() {
        PopupMenu popup = new PopupMenu(this, tvPlantform);
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

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (onlineConnectStatusListener != null) {
            TOSClientKit.removeOnlineConnectStatusListener(onlineConnectStatusListener);
        }
    }
}
