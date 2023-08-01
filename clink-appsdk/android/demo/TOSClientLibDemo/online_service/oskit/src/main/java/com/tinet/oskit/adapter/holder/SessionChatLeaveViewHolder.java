package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ChatLeaveViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-28 17:22
 * @Description:
 */
public class SessionChatLeaveViewHolder extends SessionTextViewHolder {
    public SessionChatLeaveViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), tvText);

        if (message instanceof ChatLeaveMessage) {
            ChatLeaveMessage textMessage = (ChatLeaveMessage) message;
            String leaveTip = textMessage.getLeaveTip();
            if (TextUtils.isEmpty(leaveTip)) {
                tvText.setText(R.string.ti_leave_tip);
            } else {
                tvText.setText(leaveTip);
            }
        }
    }
}
