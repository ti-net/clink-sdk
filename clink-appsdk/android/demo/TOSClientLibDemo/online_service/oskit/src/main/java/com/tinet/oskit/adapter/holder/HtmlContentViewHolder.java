package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.spanhtml.bean.Html;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlContentViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-10-08 16:36
 * @Description:
 */
public class HtmlContentViewHolder extends TinetViewHolder<Html> {

    protected SessionClickListener listener;

    public HtmlContentViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView);

        this.listener = listener;
    }
}
