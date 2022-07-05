package com.tinet.tosclientkitdemo.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tinet.oskit.fragment.SessionFragment;
import com.tinet.oskit.listener.FuncListener;
import com.tinet.oskit.listener.LabelListener;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.listener.impl.FuncListenerImpl;
import com.tinet.oskit.listener.impl.LabelListenerImpl;
import com.tinet.oskit.listener.impl.SessionClickListenerImpl;
import com.tinet.oskit.model.Function;
import com.tinet.oskit.tool.TimeUtils;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.tinet.oslib.manager.OnlineQuickManager;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;
import com.tinet.timclientlib.utils.TLogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ChatFragment
 * @Author: liuzr
 * @CreateDate: 2021-10-11 19:11
 * @Description:
 */
public class ChatFragment extends SessionFragment {

    @Override
    public void onResume() {
        super.onResume();

        int currentOnlineStatus = OnlineMessageManager.getCurrentOnlineStatus();

        Log.d("tstatus", "获取当前状态：" + currentOnlineStatus);
    }

    @Override
    protected SessionClickListener getListener() {
        return new SessionClickListenerImpl(this) {
            @Override
            public void onLinkClick(String url) {
//                super.onLinkClick(url);
                //TODO 自定义超链接事件
                Toast.makeText(requireContext(), "超链接地址：" + url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void videoPlay(String url) {
                super.videoPlay(url);
                //TODO 自定义视频播放，如果需要自己实现视频播放，则需要屏蔽super.videoPlay(url);父类的实现方式
//                Toast.makeText(requireContext(), "视频播放地址：" + url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void downloadFile(String url, String name) {
                super.downloadFile(url, name);

                TLogUtils.d("下载链接为：" + url);
            }

            @Override
            public void oncardMessageClick(View itemView, OnlineMessage message) {
                super.oncardMessageClick(itemView, message);
                if (message.getOnlineContent().getMessageType() == OnlineMessageType.CARD)
                    Toast.makeText(requireContext(), "卡片消息", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void funcList(List<Function> funcs) {
        if (null == funcs) {
            funcs = new ArrayList<>();
        }
        funcs.clear();

        funcs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_IMAGE));
        funcs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_VIDEO));
        funcs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_FILE));
        funcs.add(new Function(Function.TYPE_SYSTEM, Function.TO_ONLINE));
        funcs.add(new Function(Function.TYPE_SYSTEM, Function.CHAT_OVER));
        super.funcList(funcs);
    }

    @Override
    protected FuncListener getFuncListener() {
        //TODO 自定义底部功能栏

        return new FuncListenerImpl(this) {
            @Override
            public void onFuncClick(Function func) {
                super.onFuncClick(func);
                if ("机器人".equals(func.getTitle())) {
                    getPresent().sendText("发送文本：机器人");
                }
            }


        };
    }

    //快捷入口的点击
    @Override
    protected LabelListener getLabelListener() {
        return new LabelListenerImpl(this) {
            @Override
            public void onLabelClick(LabeInfo info) {
                super.onLabelClick(info);
                Toast.makeText(requireContext(), info.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public void chatLeaveMessage(ChatLeaveMessage message) {
        super.chatLeaveMessage(message);//自定义实现留言
        //TODO 自定义实现标题栏样式
//        Intent intent = new Intent(requireContext(), ChatLeaveMessageAty.class);
//        intent.putExtra(ChatLeaveMessageFragment.CHAT_LEAVE_MESSAGE_KEY,message.getBodyJson());
//        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        OnlineServiceClient.disConnect(false,null);
    }
}
