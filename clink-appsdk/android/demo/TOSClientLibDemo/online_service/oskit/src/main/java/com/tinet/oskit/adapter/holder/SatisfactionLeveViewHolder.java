package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.CheckBox;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.SatisfactionLevelAdapter;

import androidx.annotation.NonNull;

public class SatisfactionLeveViewHolder extends TinetViewHolder<String> {
    private SatisfactionLevelAdapter.ItemClickListener clickListener;
    private CheckBox cbSatisfactionLevel;

    public SatisfactionLeveViewHolder(@NonNull View itemView, SatisfactionLevelAdapter.ItemClickListener clickListener) {
        super(itemView);
        this.clickListener=clickListener;
        cbSatisfactionLevel = itemView.findViewById(R.id.cb_satisfaction_level);
    }

    @Override
    public void update(final String tabBar) {
        super.update(tabBar);
        cbSatisfactionLevel.setText(tabBar);
        cbSatisfactionLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(tabBar);
            }
        });
    }

}