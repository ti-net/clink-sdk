package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:11
 * @Description:
 */
public class RobotGroupBaseViewHolder extends TinetViewHolder<OnlineQuestion> {

    protected SessionClickListener listener;
    protected OnlineMessage message;

    public RobotGroupBaseViewHolder(@NonNull View itemView, OnlineMessage message, SessionClickListener listener) {
        super(itemView);
        this.listener = listener;
        this.message = message;
    }
}
