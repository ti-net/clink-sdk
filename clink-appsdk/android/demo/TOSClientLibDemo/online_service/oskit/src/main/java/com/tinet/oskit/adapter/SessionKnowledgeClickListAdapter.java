
package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.KnowledgeClickViewHolder;
import com.tinet.oskit.listener.SessionClickListener;

import org.json.JSONObject;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FuncAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-24 15:13
 * @Description:
 */
public class SessionKnowledgeClickListAdapter extends TinetAdapter<JSONObject, KnowledgeClickViewHolder> {

    private SessionClickListener listener;

    public SessionKnowledgeClickListAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected KnowledgeClickViewHolder viewHolder(View itemView, int viewType) {
        return new KnowledgeClickViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final KnowledgeClickViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.frg_session_item_knowledge_click;
    }
}
