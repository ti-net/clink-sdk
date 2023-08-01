package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.RobotQuestionExpandableAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.TRobotUtils;
import com.tinet.oskit.widget.TiCustomExpandableListView;
import com.tinet.oslib.model.bean.OnlineKnowledgeItem;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.bean.OnlineQuestionData;
import com.tinet.oslib.model.message.OnlineMessage;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-09 16:18
 * @Description: 机器人常见问题（竖版）
 */
public class RobotGroupQuestionVerticalViewHolder extends RobotGroupBaseViewHolder {

    private final TiCustomExpandableListView expandableLV;
    private final RobotQuestionExpandableAdapter robotQuestionExpandableAdapter;
    private TextView tvGuess;

    public RobotGroupQuestionVerticalViewHolder(@NonNull View itemView, OnlineMessage message,
                                                SessionClickListener listener) {
        super(itemView, message, listener);
        tvGuess = itemView.findViewById(R.id.tvGuess);
        expandableLV = itemView.findViewById(R.id.expandableLV);
        robotQuestionExpandableAdapter = new RobotQuestionExpandableAdapter(itemView.getContext());
    }

    @Override
    public void update(OnlineQuestion info) {
        super.update(info);

        tvGuess.setText((!TextUtils.isEmpty(info.getText()) && info.getText() != "null") ? info.getText() : "猜你想问");

        if (null == info.getData() || info.getData().size() == 0) {
            expandableLV.setVisibility(View.GONE);
        } else {
            expandableLV.setVisibility(View.VISIBLE);

            List<OnlineQuestionData> onlineQuestionDataList = info.getData();
            robotQuestionExpandableAdapter.setExpandableModeList(onlineQuestionDataList);
            expandableLV.setAdapter(robotQuestionExpandableAdapter);

            expandableLV.expandGroup(0);

            //保证每次只展开一组
            expandableLV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {

                    int count = expandableLV.getExpandableListAdapter().getGroupCount();
                    for (int i = 0; i < count; i++) {
                        if (groupPosition != i) {
                            expandableLV.collapseGroup(i);
                        }
                    }
                }
            });

            expandableLV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    OnlineQuestionData onlineQuestionData = onlineQuestionDataList.get(groupPosition);
                    if (onlineQuestionData != null && onlineQuestionData.getTopics() != null && onlineQuestionData.getTopics().size() > 0) {
                        List<String> topics = onlineQuestionData.getShowTopics();
                        // : 2022/12/26 处理点击的是最后一个换一换item不继续触发发送消息
                        if (onlineQuestionData.getTopics().size() > RobotQuestionExpandableAdapter.CHANGE_LIMIT && childPosition == onlineQuestionData.getShowTopics().size() - 1) {
                            return false;
                        }
                        String childName = topics.get(childPosition);
                        List<OnlineKnowledgeItem> knowledgeList = onlineQuestionData.getKnowledgeList();
                        TRobotUtils.detailTransferToKnowledge(knowledgeList, listener, childName, onlineQuestionData.getCurrentPage(), childPosition);
                    }
                    return false;
                }
            });
        }
    }
}
