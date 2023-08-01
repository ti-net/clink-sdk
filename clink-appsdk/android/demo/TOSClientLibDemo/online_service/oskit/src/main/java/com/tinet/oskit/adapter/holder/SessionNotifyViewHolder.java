package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.common.OnlineMessageStatus;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatCloseMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.TextMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.threepart.emoji.MoonUtils;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SesseionTextViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-20 14:52
 * @Description: 系统通知消息
 */
public class SessionNotifyViewHolder extends SessionViewHolder {

    /**
     * 显示文本消息
     */
    protected TextView tvNotification;

    public SessionNotifyViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        tvNotification = itemView.findViewById(R.id.tvNotification);
    }

    @Override
    public void update(OnlineMessage info, OnlineMessage preInfo) {
        update(info);
    }

    @Override
    public void update(OnlineMessage info) {
        OnlineServiceMessage message = info.getOnlineContent();

        if (null != message.getSendStatus() && message.getSendStatus() == OnlineMessageStatus.REVOKE_MESSAGE) {
            tvNotification.setText(R.string.ti_cancel_send_message_by_online);
            return;
        }
        if (message instanceof TextMessage) {
            //普通文本消息
            TextMessage textMessage = (TextMessage) message;
            if (!TextUtils.isEmpty(textMessage.getContent())) {
                MoonUtils.identifyFaceExpression(itemView.getContext(), tvNotification, textMessage.getContent(), ImageSpan.ALIGN_BOTTOM);
                tvNotification.setVisibility(View.VISIBLE);
            } else
                tvNotification.setVisibility(View.GONE);
        } else if (message instanceof ChatInvestigationMessage) {
            ChatInvestigationMessage chatInvestigationMessage = (ChatInvestigationMessage) message;
            tvNotification.setText(chatInvestigationMessage.getEndMessage());
        } else if (message instanceof ChatCloseMessage) {
            ChatCloseMessage chatCloseMessage = (ChatCloseMessage) message;
            if (chatCloseMessage.hasEndMessage()) {
                tvNotification.setText(chatCloseMessage.getEndMessage());
                tvNotification.setVisibility(View.VISIBLE);
            } else
                tvNotification.setVisibility(View.GONE);

        }

    }
}
