package com.tinet.oskit.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.FuncViewHolder;
import com.tinet.oskit.listener.FuncListener;
import com.tinet.oskit.model.Function;
import com.tinet.threepart.tools.DoubleClickUtil;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FuncAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-24 15:13
 * @Description:
 */
public class FuncAdapter extends TinetAdapter<Function, FuncViewHolder> {

    private FuncListener listener;

    public FuncAdapter(FuncListener listener) {
        this.listener = listener;
    }

    @Override
    protected FuncViewHolder viewHolder(View itemView, int viewType) {
        return new FuncViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FuncViewHolder holder, final int position) {
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
        return R.layout.frg_session_func;
    }
}
