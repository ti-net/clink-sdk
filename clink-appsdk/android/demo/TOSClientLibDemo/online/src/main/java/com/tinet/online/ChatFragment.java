package com.tinet.online;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tinet.oskit.fragment.SessionFragment;
import com.tinet.oskit.listener.FuncListener;
import com.tinet.oskit.listener.LabelListener;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.listener.impl.FuncListenerImpl;
import com.tinet.oskit.listener.impl.LabelListenerImpl;
import com.tinet.oskit.listener.impl.SessionClickListenerImpl;
import com.tinet.oskit.model.Function;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.oslib.model.bean.LogisticsCardInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;
import com.tinet.oslib.model.message.content.ChatMiniProgramCardMessage;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.timclientlib.utils.TToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ChatFragment
 * @Author: liuzr
 * @CreateDate: 2021-10-11 19:11
 * @Description:
 */
public class ChatFragment extends SessionFragment {

    SmartRefreshLayout smartRefresh;

    @Override
    public void onResume() {
        super.onResume();
        int currentOnlineStatus = OnlineMessageManager.getCurrentOnlineStatus();

//        viewChat.btnSend.setEnabled(false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        smartRefresh = view.findViewById(R.id.smartRefresh);
        if(null != smartRefresh) {
            smartRefresh.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    onMessageRefresh();
                }
            });
        }
    }

    @Override
    protected void finishMessageRefresh(Long lastMessageTime,boolean hasMore,boolean isRefresh) {
        super.finishMessageRefresh(lastMessageTime,hasMore,isRefresh);

        if(smartRefresh != null) {
            smartRefresh.finishRefresh();
        }
    }

    @Override
    protected SessionClickListener getListener() {
        return new SessionClickListenerImpl(this) {

            @Override
            public void onClick(View itemView, OnlineMessage message) {
                super.onClick(itemView, message);
                TLogUtils.i("消息点击事件" + message.getMessageUUID());
            }

            @Override
            public void onLongClick(View itemView, OnlineMessage info) {
                super.onLongClick(itemView, info);
                TLogUtils.i("消息长按事件" + info.getMessageUUID());
            }

            @Override
            public void onLinkClick(String url) {
                super.onLinkClick(url);
                // 自定义超链接事件
                Toast.makeText(requireContext(), "超链接地址：" + url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLinkClick(String content, String messageEventType) {
                super.onLinkClick(content, messageEventType);
                TLogUtils.i("onLinkClick :content=" + content + "  messageEventType=" + messageEventType);
                Toast.makeText(requireContext(), "内容：" + content + "  类型：" + messageEventType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMiniProgramCardClick(ChatMiniProgramCardMessage miniProgramCardMessage) {
                super.onMiniProgramCardClick(miniProgramCardMessage);
                try {
                    TToastUtils.showShortToast(getContext(), "小程序卡片");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void videoPlay(String url) {
                super.videoPlay(url);
                // 自定义视频播放，如果需要自己实现视频播放，则需要屏蔽super.videoPlay(url);父类的实现方式
                Toast.makeText(requireContext(), "视频播放地址：" + url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void downloadFile(String url, String name) {
                super.downloadFile(url, name);
                TLogUtils.d("下载链接为：" + url);
            }

            @Override
            public void onCardMessageClick(View itemView, OnlineMessage message) {
                super.onCardMessageClick(itemView, message);
                if (message.getOnlineContent().getMessageType() == OnlineMessageType.CARD)
                    Toast.makeText(requireContext(), "卡片消息", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLogisticsCardButtonClick(LogisticsCardInfo logisticsCardInfo) {
                super.onLogisticsCardButtonClick(logisticsCardInfo);
            }

            /**
             *
             * @param permissions
             * @param requestCode
             *   申请语音权限                1661
             *   申请相机权限                1662
             *   申请相机、语音权限 -- 拍摄   1663
             *   申请文件权限 -- 文件        1664
             *
             */
            @Override
            public void onStartRequestPermissionsCallback(@NonNull String[] permissions, int requestCode) {
                super.onStartRequestPermissionsCallback(permissions, requestCode);
                // : 2022/9/19 权限申请回调
                TLogUtils.i("onStartRequestPermissionsCallback:" + requestCode);
            }
        };
    }

    @Override
    public void onSessionClosed() {
//        super.onSessionClosed();
        Toast.makeText(requireContext(),"会话结束了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void funcList(List<Function> funcs) {
        if (null == funcs) {
            funcs = new ArrayList<>();
        }
        funcs.clear();

        Function f = new Function(Function.TYPE_SYSTEM,Function.SEND_IMAGE);
        f.setLogo(ContextCompat.getDrawable(requireContext(),R.mipmap.session_chat_over));
        f.setTitle("图片");
        funcs.add(f);
        funcs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_VIDEO));
        funcs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_FILE));
        funcs.add(new Function(Function.TO_ONLINE, ContextCompat.getDrawable(getActivity(), R.mipmap.session_to_online), "转人工"));
        funcs.add(new Function(Function.CHAT_OVER, ContextCompat.getDrawable(getActivity(), R.mipmap.session_chat_over), "结束会话"));
        super.funcList(funcs);
    }

    @Override
    public boolean canShowSend() {
        return true;
    }

    @Override
    protected void onMessageListLayoutChanged(int currentHeight, int messageHeight) {
        super.onMessageListLayoutChanged(currentHeight, messageHeight);

        android.util.Log.d("ti-net","currentHeight="+currentHeight+",messageHeight="+messageHeight);
    }

    @Override
    protected FuncListener getFuncListener() {
        return new FuncListenerImpl(this) {
            @Override
            public void onFuncClick(Function func) {
                super.onFuncClick(func);

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
