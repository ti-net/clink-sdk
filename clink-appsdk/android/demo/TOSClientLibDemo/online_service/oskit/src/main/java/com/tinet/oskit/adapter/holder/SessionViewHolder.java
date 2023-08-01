package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.listener.AnimListener;
import com.tinet.oskit.manager.TCustomizationUI;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.common.OnlineEvent;
import com.tinet.oslib.common.OnlineMessageSenderType;
import com.tinet.oslib.listener.GetOnlineClientInfoListener;
import com.tinet.oslib.model.bean.OnlineClientInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.threepart.tools.TimeUtils;
import com.tinet.timclientlib.common.constans.TMessageStatus;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-20 14:24
 * @Description: 会话适配器基类
 */
public class SessionViewHolder extends TinetViewHolder<OnlineMessage> {

    protected SessionClickListener listener;

    /**
     * 显示会话时间
     */
    private static final int TIME_DISTANCE = 3 * 60 * 1000;

    /**
     * 显示时间的控件
     */
    private TextView tvTime;
    private View viewTime;
    /**
     * 头像
     */
    private ImageView ivAvatar;
    /**
     * 发送异常
     */
    private View viewError;
    /**
     * 发送中
     */
    private View pbSending;
    /**
     * 是否已读
     */
    private TextView tvReadStatus;

    /**
     * 发送/接收人姓名
     */
    private TextView tvName;

    /**
     * 敏感词提示
     */
    private final TextView tvSensitiveWord;


    boolean showVisitorNickname = true, showVisitAvatar = true, showAgentRobotNickname = true, showAgentRobotAvatar = true;

    public SessionViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView);

        this.listener = listener;

        viewTime = itemView.findViewById(R.id.viewTime);
        tvTime = itemView.findViewById(R.id.tvTime);
        tvName = itemView.findViewById(R.id.tvName);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        viewError = itemView.findViewById(R.id.tinetSendFailureContent);
        pbSending = itemView.findViewById(R.id.tinetSending);
        tvReadStatus = itemView.findViewById(R.id.tvReadStatus);
        tvSensitiveWord = itemView.findViewById(R.id.tvSensitiveWord);

    }

    /**
     * @param info    当前消息
     * @param preInfo 上一条消息
     */
    public void update(OnlineMessage info, OnlineMessage preInfo) {
        if (OnlineEvent.ROBOT_BRIDGE.equals(info.getEvent())) {
            //不展示的消息
            return;
        }

        update(info);
        if (preInfo != null)
            if (null != tvTime && preInfo.getSendTime() != null && info.getSendTime() != null) {

                long currentTime = info.getSendTime();
                if (preInfo == null) {
                    if(null != viewTime){
                        viewTime.setVisibility(View.VISIBLE);
                    }
                } else {
                    long time = currentTime - preInfo.getSendTime();
                    if (time > TIME_DISTANCE || time == 0) {
                        if(null != viewTime){
                            viewTime.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if(null != viewTime){
                            viewTime.setVisibility(View.GONE);
                        }
                    }
                }

                tvTime.setText(TimeUtils.getNewChatTime(currentTime));


                if (ModifyUiUtils.kitConfigState() != null) {
                    TCustomizationUI customizationUI = ModifyUiUtils.kitConfigState();
                    ModifyUiUtils.modifySetTextColor(tvTime, customizationUI.msgTimeColor);
                }
            }

    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage message = info.getOnlineContent();

        //发送失败的消息
        if ((null != message && message.getSenderType() == OnlineMessageSenderType.VISITOR) && (info.getStatus() == TMessageStatus.MSG_STATUS_SEND_FAIL || info.getStatus() == TMessageStatus.MSG_STATUS_SEND_FAIL_SENSITIVE_WORDS)) {
            if(null != viewError) {
                viewError.setOnClickListener(v -> listener.resendMessage(info));
            }
        }

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(itemView, info);
                return true;
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(itemView, info);
            }
        });
        getStatus();
        setHeaderAndName(info);
        setStatus(info);
        setReadStatus(info);
        setShowSensitiveWord(info);
    }

    private void getStatus() {
        if (ModifyUiUtils.kitConfigState() != null) {
            TCustomizationUI customizationUI = ModifyUiUtils.kitConfigState();
            showVisitorNickname = customizationUI.showVisitorNickname;//访客名称
            showVisitAvatar = customizationUI.showVisitAvatar;//访客头像
            showAgentRobotNickname = customizationUI.showAgentRobotNickname;//客服、机器人昵称
            showAgentRobotAvatar = customizationUI.showAgentRobotAvatar;//客服、机器人头像
        }
    }

    /**
     * 更新头像
     */
    protected void setHeaderAndName(OnlineMessage item) {
        if (null != ivAvatar) {
            ivAvatar.setImageResource(R.drawable.ti_visitor_default_header);
        }

        updateText(tvName, itemView.getResources().getString(R.string.ti_online_default_name));

        final OnlineServiceMessage message = item.getOnlineContent();

        if (null == message.getSenderType()) {
            return;
        }

        if (message.getSenderType() == OnlineMessageSenderType.VISITOR) {
            if (null != OnlineServiceClient.getCurrentUserInfo()) {
                String name = OnlineServiceClient.getCurrentUserInfo().getVisitorName();
                if (TextUtils.isEmpty(name)) {
                    name = message.getVisitorName();
                }
                //访客昵称
//                if (null != tvName)
//                    tvName.setVisibility(showVisitorNickname ? View.VISIBLE : View.GONE);
                updateText(tvName, name);
                if (null != ivAvatar && !TextUtils.isEmpty(TOSClientKit.getCurrentUserInfo().getHeaderUrl())) {
                    //访客头像
                    if (null != ivAvatar)
                        ivAvatar.setVisibility(showVisitAvatar ? View.VISIBLE : View.GONE);
                    TOSClientKit.getImageLoader().loadImage(ivAvatar,
                            TOSClientKit.getCurrentUserInfo().getHeaderUrl(),
                            R.drawable.ti_visitor_default_header,
                            R.drawable.ti_visitor_default_header);

                }
            }
        } else {
            String sender = message.getSender();
            final Integer senderType = message.getSenderType();
            if (TextUtils.isEmpty(sender) || senderType == null) {
                return;
            }

            OnlineServiceClient.getClientInfo(sender, senderType, new GetOnlineClientInfoListener() {
                @Override
                public void onSuccess(OnlineClientInfo info) {
                    updateClientInfo(senderType, info);
                }

                @Override
                public void onError(Exception e) {
                    updateClientInfo(senderType, null);
                }
            });
        }
    }

    private void updateClientInfo(int senderType, OnlineClientInfo info) {

        String avatar = info == null ? "" : info.getAvatar();
        String name = info == null ? "" : info.getNickName();
        int defaultHeader = R.drawable.ti_visitor_default_header;
        //客服、机器人昵称
        if (null != tvName)
            tvName.setVisibility(showAgentRobotNickname ? View.VISIBLE : View.GONE);
        switch (senderType) {
            case OnlineMessageSenderType.NOTIFY:
            case OnlineMessageSenderType.SYSTEM:
            case OnlineMessageSenderType.ONLINE: {
                if (TextUtils.isEmpty(name)) {
                    name = itemView.getResources().getString(R.string.ti_online_default_name);
                }
                defaultHeader = R.drawable.ti_visitor_default_header;
                break;
            }
            case OnlineMessageSenderType.ROBOT: {
                if (TextUtils.isEmpty(name)) {
                    name = itemView.getResources().getString(R.string.ti_rebot_default_name);
                }

                defaultHeader = R.drawable.ti_robot_default_header;
                break;
            }
            case OnlineMessageSenderType.VISITOR: {
                break;
            }
        }

        ////客服、机器人头像
        if (null != ivAvatar)
            ivAvatar.setVisibility(showAgentRobotAvatar ? View.VISIBLE : View.GONE);
        if (null != ivAvatar && !TextUtils.isEmpty(avatar) && !"null".equals(avatar)) {
            TOSClientKit.getImageLoader().loadImage(ivAvatar, avatar, defaultHeader, defaultHeader);
        } else if (null != ivAvatar) {
            ivAvatar.setImageResource(defaultHeader);
        }
        //机器人 客服昵称
        updateText(tvName, name);
    }

    private void updateText(TextView tv, String value) {
        if (null == tv) {
            return;
        }

        tv.setText(value);
    }

    /**
     * 消息状态
     */
    protected void setStatus(OnlineMessage item) {
        OnlineServiceMessage message = item.getOnlineContent();
        if (message.getSenderType() != OnlineMessageSenderType.VISITOR) {
            return;
        }

        int sentStatus = item.getStatus();
        if (sentStatus == TMessageStatus.MSG_STATUS_SENDING) {
            setVisibility(pbSending, View.VISIBLE);
            setVisibility(viewError, View.GONE);
        } else if (sentStatus == TMessageStatus.MSG_STATUS_SEND_FAIL || sentStatus == TMessageStatus.MSG_STATUS_SEND_FAIL_SENSITIVE_WORDS) {
            setVisibility(pbSending, View.GONE);
            setVisibility(viewError, View.VISIBLE);
        } else {
            setVisibility(pbSending, View.GONE);
            setVisibility(viewError, View.GONE);
        }
    }

    /**
     * 设置是否显示敏感词提示
     */
    protected void setShowSensitiveWord(OnlineMessage item) {
        OnlineServiceMessage message = item.getOnlineContent();
        if (message.getSenderType() != OnlineMessageSenderType.VISITOR) {
            return;
        }
        if (tvSensitiveWord == null) {
            return;
        }
        if (item.getStatus() == TMessageStatus.MSG_STATUS_SEND_FAIL_SENSITIVE_WORDS) {
            tvSensitiveWord.setVisibility(View.VISIBLE);
        } else {
            tvSensitiveWord.setVisibility(View.GONE);
        }
    }

    /**
     * 是否已读
     */
    protected void setReadStatus(OnlineMessage msg) {
        if (tvReadStatus == null) {
            return;
        }

        tvReadStatus.setVisibility(View.GONE);

    }

    private void setVisibility(View view, int visibility) {
        if (view == null) {
            return;
        }

        if(view instanceof AnimListener){
            AnimListener animListener = (AnimListener) view;
            if(visibility == View.VISIBLE){
                animListener.startAnimation();
            }else{
                animListener.stopAnimation();
            }
        }

        view.setVisibility(visibility);
    }
}
