package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.SatisfactionLeveViewHolder;
import com.tinet.oskit.listener.SessionClickListener;

import androidx.annotation.NonNull;

public class SatisfactionLevelAdapter extends TinetAdapter<String, SatisfactionLeveViewHolder> {

    private SessionClickListener listener;

    private ItemClickListener clickListener;

    public void setListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ItemClickListener{
        void onItemClick(String tabBar);
    }

    public SatisfactionLevelAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected SatisfactionLeveViewHolder viewHolder(View itemView, int viewType) {
        return new SatisfactionLeveViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SatisfactionLeveViewHolder holder, final int position) {
        holder.update(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.frg_session_satisfaction_level;
    }
}