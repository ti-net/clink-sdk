package com.tinet.oskit.adapter.holder;

import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.tool.LinkMovementClickMethod;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oskit.tool.THyperLinkUtils;
import com.tinet.oslib.common.OnlineChatCloseType;
import com.tinet.oslib.common.OnlineMessageSenderType;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatCloseMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.ChatLeadingWordsMessage;
import com.tinet.oslib.model.message.content.ChatLeaveReceiveMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.TextMessage;
import com.tinet.threepart.emoji.MoonUtils;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SesseionTextViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-20 14:52
 * @Description: 文本消息
 */
public class SessionTextViewHolder extends SessionViewHolder {

    /**
     * 显示文本消息
     */
    protected TextView tvText, state;

    public SessionTextViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        tvText = itemView.findViewById(R.id.tvText);
        state = itemView.findViewById(R.id.state);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        final OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), tvText);

        if (state != null) state.setVisibility(View.GONE);
        if (message instanceof TextMessage) {
            //普通文本消息
            TextMessage textMessage = (TextMessage) message;
            SpannableStringBuilder spannableString = MoonUtils.identifyFaceExpression(itemView.getContext(), new SpannableStringBuilder(textMessage.getContent()), textMessage.getContent(), ImageSpan.ALIGN_BOTTOM);
            THyperLinkUtils.setTextHyperlink(tvText.getContext(), spannableString, listener, TOSClientKit.getTOSClientKitConfig() != null ? TOSClientKit.getTOSClientKitConfig().getTextHighLightRuleList() : new ArrayList<>(), message.getSenderType() == OnlineMessageSenderType.VISITOR);
            tvText.setText(spannableString);
            tvText.setMovementMethod(LinkMovementClickMethod.getInstance());
            tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) listener.onClick(itemView, info);
                }
            });
            tvText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) listener.onLongClick(itemView, info);
                    return true;
                }
            });
        } else if (message instanceof ChatInvestigationMessage) {
            tvText.setText(R.string.ti_investigation_message);
        } else if (message instanceof ChatLeaveReceiveMessage) {
            ChatLeaveReceiveMessage mChatLeaveReceiveMessage = (ChatLeaveReceiveMessage) message;
            Map<String, String> map = mChatLeaveReceiveMessage.getLeaveMessage();
            StringBuilder sb = new StringBuilder();
            for (String key : map.keySet()) {
                if (sb.length() != 0) {
                    sb.append(itemView.getContext().getString(R.string.ti_chat_leavve_receive_message_n));
                }
                sb.append(itemView.getContext().getString(R.string.ti_chat_leavve_receive_message, key, map.get(key)));
            }
            tvText.setText(sb.toString());
        } else if (message instanceof ChatCloseMessage) {
            //关闭会话消息
            ChatCloseMessage closeSessionMessage = (ChatCloseMessage) message;
            switch (closeSessionMessage.getCloseType()) {
                case OnlineChatCloseType.ONLINE_CLSOE:
                    tvText.setText(R.string.ti_chat_close_by_online);
                    break;
                case OnlineChatCloseType.SYSTEM_CLSOE:
                    tvText.setText(R.string.ti_chat_close_by_system);
                    break;
                case OnlineChatCloseType.VISITOR_CLSOE:
                    tvText.setText(R.string.ti_chat_close_by_client);
                    break;
                case OnlineChatCloseType.ROBOT_CLOSE:
                    tvText.setText(R.string.ti_chat_close_by_robot);
                    break;
            }
        } else if (message instanceof ChatLeadingWordsMessage) {
            ChatLeadingWordsMessage chatLeadingWordsMessage = (ChatLeadingWordsMessage) message;
            MoonUtils.identifyFaceExpression(itemView.getContext(), tvText, chatLeadingWordsMessage.getContent(), ImageSpan.ALIGN_BOTTOM);
        }
    }
}
