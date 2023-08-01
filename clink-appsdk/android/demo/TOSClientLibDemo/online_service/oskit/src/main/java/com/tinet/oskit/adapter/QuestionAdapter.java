package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.QuestionViewHolder;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.bean.OnlineKnowledgeItem;
import com.tinet.oslib.model.message.OnlineMessage;

import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: QuestionAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:09
 * @Description: 问题列表
 */
public class QuestionAdapter extends TinetAdapter<String, QuestionViewHolder> {

    private SessionClickListener listener;
    private QuestionViewHolder questionViewHolder;
    private List<OnlineKnowledgeItem> knowledgeList;

    public interface KnowledgeListListener {

        List<OnlineKnowledgeItem> getKnowledgeList();

    }

    public QuestionAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected QuestionViewHolder viewHolder(View itemView, int viewType) {
        questionViewHolder = new QuestionViewHolder(itemView, listener, new KnowledgeListListener() {
            @Override
            public List<OnlineKnowledgeItem> getKnowledgeList() {
                return knowledgeList;
            }
        });
        return questionViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.frg_session_robot_group_question;
    }

    public void setKnowledgeList(List<OnlineKnowledgeItem> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

}
