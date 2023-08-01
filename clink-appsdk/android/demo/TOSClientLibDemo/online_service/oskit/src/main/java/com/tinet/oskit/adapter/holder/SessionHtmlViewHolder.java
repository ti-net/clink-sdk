package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.tinet.oskit.R;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.adapter.SessionHtmlAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.RepliedMessage;
import com.tinet.oslib.utils.ResourceUtils;

/**
 * 解析为本地支持的富文本消息
 *
 * @ProjectName: TIMSDK
 * @ClassName: SessionHtmlViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-10-08 14:06
 * @Description:
 */
public class SessionHtmlViewHolder extends SessionViewHolder {

    private final View llContent;
    private RecyclerView recyclerView;
    private SessionHtmlAdapter adapter;

    /**
     * 引用消息布局
     */
    private LinearLayout lvRepliedLayout;

    public SessionHtmlViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        recyclerView = itemView.findViewById(R.id.recyclerView);
        adapter = new SessionHtmlAdapter(listener);
        recyclerView.setAdapter(adapter);
        lvRepliedLayout = itemView.findViewById(R.id.lv_replied_layout);
        llContent = itemView.findViewById(R.id.ll_content);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), llContent);

        adapter.setData(message.getRichContent());
        adapter.setLongClickListener(new SessionHtmlAdapter.LongClickListener() {
            @Override
            public void onLongClickListener() {
                listener.onLongClick(itemView, info);
            }
        });
        adapter.setClickListener(new SessionHtmlAdapter.ClickListener() {
            @Override
            public void onClickListener() {
                listener.onClick(itemView, info);
            }
        });

        // : 2022/10/12 引用功能
        if (lvRepliedLayout != null) {
            if (message.getRepliedMessage() != null) {
                detailRepliedMessageShow(message);
                lvRepliedLayout.setVisibility(View.VISIBLE);
            } else {
                lvRepliedLayout.setVisibility(View.GONE);
            }
        }
    }

    private void detailRepliedMessageShow(OnlineServiceMessage message) {
        RepliedMessage repliedMessage = message.getRepliedMessage();
        if (repliedMessage != null) {
            TextView tvRepliedMessageName = itemView.findViewById(R.id.tv_replied_message_name);
            tvRepliedMessageName.setText(repliedMessage.getSenderName());

            TextView tvRepliedMessageContent = itemView.findViewById(R.id.tv_replied_message_content);
            ImageView ivRepliedMessageContent = itemView.findViewById(R.id.iv_replied_message_content);
            if (repliedMessage.getMessageType() != OnlineMessageType.IMAGE) {
                tvRepliedMessageContent.setVisibility(View.VISIBLE);
                ivRepliedMessageContent.setVisibility(View.GONE);
                switch (repliedMessage.getMessageType()) {
                    case OnlineMessageType.TEXT:
                        tvRepliedMessageContent.setText(repliedMessage.getContent());
                        break;
                    case OnlineMessageType.IMAGE:
                        break;
                    case OnlineMessageType.FILE:
                        tvRepliedMessageContent.setText("[文件] " + repliedMessage.getFileName());
                        break;
                    case OnlineMessageType.VIDEO:
                        tvRepliedMessageContent.setText("[视频] " + repliedMessage.getFileName());
                        break;
                    case OnlineMessageType.VOICE:
                        tvRepliedMessageContent.setText("[音频文件]");
                        break;
                    default:
                        tvRepliedMessageContent.setText("[未知消息] " + repliedMessage.getContent());
                        break;
                }
            } else {
                tvRepliedMessageContent.setVisibility(View.GONE);
                ivRepliedMessageContent.setVisibility(View.VISIBLE);
                TOSClientKit.getImageLoader().loadImage(ivRepliedMessageContent, ResourceUtils.getUri(repliedMessage.getFileKey(), repliedMessage.getFileName(), null, false, repliedMessage.getSenderType(), true), R.drawable.ti_ic_load_default_image, R.drawable.ti_ic_load_default_image);
            }
        }
    }

}
