package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.AdditionalViewHolder;
import com.tinet.oskit.listener.SessionClickListener;

import androidx.annotation.NonNull;

public class SessionCarddarkAdapter extends TinetAdapter<String, AdditionalViewHolder> {

    private SessionClickListener listener;

    private String additional;

    public void setUlOrOl(String additional) {
        this.additional = additional;
    }

    public SessionCarddarkAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected AdditionalViewHolder viewHolder(View itemView, int viewType) {
        return new AdditionalViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdditionalViewHolder holder, int position) {
        holder.update(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.frg_session_card_tv_dark;
    }
}