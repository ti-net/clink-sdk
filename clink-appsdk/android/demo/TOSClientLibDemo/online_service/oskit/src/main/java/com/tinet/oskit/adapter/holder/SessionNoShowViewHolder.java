package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.message.OnlineMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionNoShowViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-31 16:55
 * @Description:
 */
public class SessionNoShowViewHolder extends SessionViewHolder {
    private TextView tvText;

    public SessionNoShowViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        tvText = itemView.findViewById(R.id.tvText);
    }

    @Override
    public void update(OnlineMessage info, OnlineMessage preInfo) {
    }
}
