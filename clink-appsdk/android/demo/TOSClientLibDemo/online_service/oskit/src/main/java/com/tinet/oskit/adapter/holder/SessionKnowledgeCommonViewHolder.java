package com.tinet.oskit.adapter.holder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.SessionHtmlAdapter;
import com.tinet.oskit.adapter.SessionKnowledgeClickListAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatKnowledgeCommonMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionKnowledgeCommonViewHolder
 * @Author:
 * @CreateDate:
 * @Description:
 */
public class SessionKnowledgeCommonViewHolder extends SessionViewHolder {

    private final SessionKnowledgeClickListAdapter sessionKnowledgeClickListAdapter;
    private RecyclerView recyclerView, recyclerViewClick;
    private SessionHtmlAdapter adapter;

    public SessionKnowledgeCommonViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        recyclerView = itemView.findViewById(R.id.recyclerView);
        adapter = new SessionHtmlAdapter(listener);
        recyclerView.setAdapter(adapter);

        recyclerViewClick = itemView.findViewById(R.id.recyclerViewClick);
        sessionKnowledgeClickListAdapter = new SessionKnowledgeClickListAdapter(listener);
        recyclerViewClick.setAdapter(sessionKnowledgeClickListAdapter);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), recyclerView);

        if (message instanceof ChatKnowledgeCommonMessage) {
            ChatKnowledgeCommonMessage chatKnowledgeCommonMessage = (ChatKnowledgeCommonMessage) message;

            adapter.setData(chatKnowledgeCommonMessage.getRichContent());
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

            if (chatKnowledgeCommonMessage.getClickList() != null) {
                List<JSONObject> list = new ArrayList<>();
                for (int i = 0; i < chatKnowledgeCommonMessage.getClickList().length(); i++) {
                    list.add(chatKnowledgeCommonMessage.getClickList().optJSONObject(i));
                }
                sessionKnowledgeClickListAdapter.setData(list);
                recyclerViewClick.setVisibility(View.VISIBLE);
            } else {
                recyclerViewClick.setVisibility(View.GONE);
            }
        }

    }

}
