package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.message.OnlineMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ChatLeadingWordsViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-12-02 14:11
 * @Description:
 */
public class SessionChatLeadingWordsViewHolder extends SessionViewHolder {

    /**
     * 显示欢迎语
     */
    private TextView tvNotification;

    public SessionChatLeadingWordsViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        tvNotification = itemView.findViewById(R.id.tvNotification);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);
        tvNotification.setText(info.getOnlineContent().getContent());
    }
}
