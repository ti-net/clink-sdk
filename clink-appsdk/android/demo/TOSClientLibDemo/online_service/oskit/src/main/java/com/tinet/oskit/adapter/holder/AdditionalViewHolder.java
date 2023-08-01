package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;

import androidx.annotation.NonNull;

public class AdditionalViewHolder extends TinetViewHolder<String> {

    private TextView tvText;

    public AdditionalViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView);
        tvText = itemView.findViewById(R.id.tvText);
    }

    @Override
    public void update(String additional) {
        super.update(additional);
        tvText.setText(additional);
    }

}
