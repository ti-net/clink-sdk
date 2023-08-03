package com.tinet.oskit.adapter.holder;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.QuestionAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.TRobotUtils;
import com.tinet.oslib.model.bean.OnlineKnowledgeItem;

import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: QuestionViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:09
 * @Description: 问题
 */
public class QuestionViewHolder extends TinetViewHolder<String> {

    private SessionClickListener listener;
    private TextView tvName;
    private QuestionAdapter.KnowledgeListListener knowledgeListListener;

    public QuestionViewHolder(@NonNull View itemView, SessionClickListener listener, QuestionAdapter.KnowledgeListListener knowledgeListListener) {
        super(itemView);
        this.listener = listener;
        this.knowledgeListListener = knowledgeListListener;
        tvName = itemView.findViewById(R.id.tvName);
    }

    @Override
    public void update(String info, int position) {
        super.update(info, position);


        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TRobotUtils.detailTransferToKnowledge(knowledgeListListener.getKnowledgeList(), listener, info);
            }
        });

        SpannableString sb = new SpannableString(info);
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(itemView.getContext(), R.color.ti_dark)),
                0, info.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvName.setText((position + 1) + "." + sb);
    }

}
