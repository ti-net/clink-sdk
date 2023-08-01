package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-20 11:27
 * @Description: ViewHolder基类
 */
public class TinetViewHolder<T> extends RecyclerView.ViewHolder {

    public TinetViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void update(T info) {

    }

    public void update(T info,int position) {

    }

}
