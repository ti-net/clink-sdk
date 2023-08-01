package com.tinet.oskit.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.HtmlLiViewHolder;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlLi;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionHtmlOlAdapter
 * @Author: liuzr
 * @CreateDate: 2021-12-06 14:28
 * @Description:
 */
public class SessionHtmlOlAdapter extends TinetAdapter<HtmlLi, HtmlLiViewHolder> {

    private SessionClickListener listener;

    private Html ulOrOl;

    public void setUlOrOl(Html ulOrOl) {
        this.ulOrOl = ulOrOl;
    }

    public SessionHtmlOlAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected HtmlLiViewHolder viewHolder(View itemView, int viewType) {
        return new HtmlLiViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HtmlLiViewHolder holder, int position) {
        holder.update(getItem(position), ulOrOl, position + 1);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.frg_session_receive_html_li;
    }
}
