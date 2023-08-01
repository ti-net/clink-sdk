package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.widget.FormView;
import com.tinet.oslib.model.form.FormBean;
import com.tinet.oslib.model.form.FormBeanType;

/**
 * 普通表单
 *
 * @author: liuzr
 * @date: 2021-12-20
 */
public class FormCommonViewHolder extends FormViewHolder {

    private FormView formView;

    public FormCommonViewHolder(@NonNull View itemView) {
        super(itemView);

        formView = itemView.findViewById(R.id.formView);
    }

    @Override
    public void update(final FormBean info) {
        super.update(info);

        formView.setFormValue(info.getContent());
        formView.setFormTitle(info.getName());
        formView.setIsRequired(info.isRequired());

        if (info.getType() == FormBeanType.singleLine) {
            formView.setModel(FormView.MODEL_INPUT_SINGLE);
        } else if (info.getType() == FormBeanType.multiLine) {
            formView.setModel(FormView.MODEL_INPUT_MULTI);
        }

        formView.setOnTextChanged(new FormView.OnTextChanged() {
            @Override
            public void onChanged(String text) {
                info.setContent(text);
            }
        });

        formView.setType(FormView.TYPE_EDIT);
    }
}
