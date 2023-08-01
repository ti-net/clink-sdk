package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.RobotGroupAdapter;
import com.tinet.oskit.adapter.SessionHtmlAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.RobotGroupMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionRobotGroupViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:08
 * @Description: 机器人组合消息
 */
public class SessionRobotGroupViewHolder extends SessionViewHolder {

    private RobotGroupAdapter adapter;
    private RecyclerView recyclerView;

    public SessionRobotGroupViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        recyclerView = itemView.findViewById(R.id.recyclerView);
//        recyclerView.addItemDecoration(new LinearLayoutManagerDecoration(itemView.getContext(), itemView.getContext().getResources().getDimensionPixelSize(R.dimen.ti_msg_msg_span), Color.TRANSPARENT));
        adapter = new RobotGroupAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage serviceMessage = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), serviceMessage.getSenderType(), recyclerView);

        if (serviceMessage instanceof RobotGroupMessage) {
            adapter.setMessage(info);
            RobotGroupMessage message = (RobotGroupMessage) serviceMessage;
            adapter.setData(message.getQuestions());
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
        }
    }
}
