package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.QuickReplyTagAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.widget.tagview.FlowTagLayout;
import com.tinet.oskit.widget.tagview.OnTagClickListener;
import com.tinet.oslib.model.bean.QuickReplyTagInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.RobotQuickReplyMessage;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:11
 * @Description:
 */
public class RobotQuickReplyViewHolder extends SessionViewHolder {

    private TextView tvText;
    private final FlowTagLayout tiFlowLayout;

    public RobotQuickReplyViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        tvText = itemView.findViewById(R.id.tvText);
        tiFlowLayout = itemView.findViewById(R.id.ti_flow_layout);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);
        RobotQuickReplyMessage quickReplyMessage = (RobotQuickReplyMessage) info.getOnlineContent();
        tvText.setText(quickReplyMessage.getContentText());

        QuickReplyTagAdapter quickReplyTagAdapter = new QuickReplyTagAdapter(tiFlowLayout.getContext());
        tiFlowLayout.setAdapter(quickReplyTagAdapter);
        List<QuickReplyTagInfo> quickReplyTagList = quickReplyMessage.getQuickReplyTagList();
        if (quickReplyTagList.size() > 8) {
            quickReplyTagList = quickReplyTagList.subList(0, 8);
        }
        quickReplyTagAdapter.setList(quickReplyTagList);

        final List<QuickReplyTagInfo> finalQuickReplyTagList = quickReplyTagList;
        tiFlowLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                if (listener != null) {
                    listener.onQuestionRequest(finalQuickReplyTagList.get(position).getText());
                }
            }
        });
    }
}
