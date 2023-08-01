package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.RobotBridgeMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionRobotBridgeViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-30 11:59
 * @Description: 接通机器人
 */
public class SessionRobotBridgeViewHolder extends SessionTextViewHolder {

    public SessionRobotBridgeViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);
        //ti_manual_service

        OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), tvText);

        if (message instanceof RobotBridgeMessage) {
            RobotBridgeMessage robotBridgeMessage = (RobotBridgeMessage) message;
            tvText.setText(robotBridgeMessage.getRobotNickname());
        }
    }
}
