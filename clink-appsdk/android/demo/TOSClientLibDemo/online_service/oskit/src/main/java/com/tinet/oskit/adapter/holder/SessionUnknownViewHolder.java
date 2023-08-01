package com.tinet.oskit.adapter.holder;

import android.view.View;
import androidx.annotation.NonNull;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.message.OnlineMessage;

/**
 * 未知消息
 * @ProjectName: TIMSDK
 * @ClassName: SessionNoShowViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-31 16:55
 * @Description:
 */
public class SessionUnknownViewHolder extends SessionViewHolder {

    public SessionUnknownViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
    }

    @Override
    public void update(OnlineMessage info, OnlineMessage preInfo) {
    }
}
