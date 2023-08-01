package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.timclientlib.utils.TStringUtils;

import org.json.JSONObject;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FuncViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 15:20
 * @Description:
 */
public class KnowledgeClickViewHolder extends TinetViewHolder<JSONObject> {

    private final TextView tvClickTitle;
    private SessionClickListener listener;

    public KnowledgeClickViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView);
        tvClickTitle = itemView.findViewById(R.id.tvClickTitle);
        this.listener = listener;
    }

    @Override
    public void update(JSONObject info) {
        super.update(info);
        if (info != null && info.has("click")) {
            try {
                JSONObject jsonObject = info.getJSONObject("click");
                if (jsonObject.has("content")) {
                    String content = jsonObject.optString("content");
                    if (TStringUtils.isNotEmpty(content)) {
                        tvClickTitle.setText("#" + content);
                        tvClickTitle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onQuestionRequest(content);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
