package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oslib.model.bean.LabeInfo;

import androidx.annotation.NonNull;

public class LabelViewHolder extends TinetViewHolder<LabeInfo> {

    private TextView tvText;

    public LabelViewHolder(@NonNull View itemView) {
        super(itemView);

        tvText = itemView.findViewById(R.id.tvText);
    }

    @Override
    public void update(LabeInfo info) {
        super.update(info);
        tvText.setText(info.getName());
    }
}
