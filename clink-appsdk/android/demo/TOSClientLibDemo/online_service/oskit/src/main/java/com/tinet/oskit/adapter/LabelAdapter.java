package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.LabelViewHolder;
import com.tinet.oskit.listener.LabelListener;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.threepart.tools.DoubleClickUtil;

import androidx.annotation.NonNull;

public class LabelAdapter extends TinetAdapter<LabeInfo, LabelViewHolder> {

    private LabelListener listener;

    public LabelAdapter(LabelListener listener) {
        this.listener = listener;
    }

    @Override
    protected LabelViewHolder viewHolder(View itemView, int viewType) {
        return new LabelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LabelViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DoubleClickUtil.isFastClick(holder.itemView)) {
                    if (null != listener) {
                        listener.onClick(getItem(position));
                    }
                }
            }
        };

        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.frg_session_label_tv;
    }
}
