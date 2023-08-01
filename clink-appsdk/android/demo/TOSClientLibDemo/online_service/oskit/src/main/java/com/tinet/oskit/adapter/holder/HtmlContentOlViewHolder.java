package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.SessionHtmlOlAdapter;
import com.tinet.oskit.adapter.decoration.HorizontalItemDecoration;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlOl;
import com.tinet.spanhtml.bean.HtmlUl;

/**
 * 富文本
 *
 * @ProjectName: TIMSDK
 * @ClassName: HtmlContentViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-10-08 16:35
 * @Description:
 */
public class HtmlContentOlViewHolder extends HtmlContentViewHolder {

    private RecyclerView recyclerView;
    private SessionHtmlOlAdapter adapter;

    public HtmlContentOlViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        recyclerView = itemView.findViewById(R.id.recyclerView);
        adapter = new SessionHtmlOlAdapter(listener);
        recyclerView.addItemDecoration(new HorizontalItemDecoration(5));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void update(final Html info) {
        super.update(info);

        if (info instanceof HtmlOl) {
            HtmlOl ol = (HtmlOl) info;
            adapter.setUlOrOl(ol);
            adapter.setData(ol.getLis());
        } else if (info instanceof HtmlUl) {
            HtmlUl ul = (HtmlUl) info;
            adapter.setUlOrOl(ul);
            adapter.setData(ul.getLis());
        } else {
            adapter.setData(null);
        }
    }
}
