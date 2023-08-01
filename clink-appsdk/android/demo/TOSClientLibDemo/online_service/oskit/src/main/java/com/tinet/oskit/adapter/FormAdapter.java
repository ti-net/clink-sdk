package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.FormCommonViewHolder;
import com.tinet.oskit.adapter.holder.FormViewHolder;
import com.tinet.oslib.model.form.FormBean;

/**
 * 表单
 *
 * @author: liuzr
 * @date: 2021-12-15
 */
public class FormAdapter extends TinetAdapter<FormBean, FormViewHolder> {

    @Override
    protected FormViewHolder viewHolder(View itemView, int viewType) {
        FormViewHolder vh = null;
        if (viewType == R.layout.tinet_form_common) {
            vh = new FormCommonViewHolder(itemView);
        }

        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        int layoutId = R.layout.tinet_form_common;
        FormBean bean = getItem(position);
        switch (bean.getType()) {
            case singleLine:
            case multiLine:
                layoutId = R.layout.tinet_form_common;
                break;
            case unKnown:
                layoutId = -1;
                break;
        }

        return layoutId;
    }
}
