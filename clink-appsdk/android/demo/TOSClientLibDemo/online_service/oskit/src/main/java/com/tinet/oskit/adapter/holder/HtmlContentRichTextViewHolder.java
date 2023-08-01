package com.tinet.oskit.adapter.holder;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.LinkMovementClickMethod;
import com.tinet.oskit.tool.THyperLinkUtils;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlTextList;
import com.tinet.spanhtml.listener.HtmlListener;

import java.util.ArrayList;

/**
 * 富文本
 *
 * @ProjectName: TIMSDK
 * @ClassName: HtmlContentViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-10-08 16:35
 * @Description:
 */
public class HtmlContentRichTextViewHolder extends HtmlContentViewHolder {

    private TextView tvText;

    public HtmlContentRichTextViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        tvText = itemView.findViewById(R.id.tvText);
    }

    @Override
    public void update(final Html info) {
        super.update(info);
        if (info instanceof HtmlTextList) {
            HtmlTextList spanContent = (HtmlTextList) info;
            SpannableStringBuilder spannableStringBuilder = spanContent.convert(itemView.getContext(), new HtmlListener() {
                @Override
                public void onHref(String href) {
                    listener.onLinkClick(href);
                }

                @Override
                public void onKnowledgeClick(String info, String dataBack) {
                    listener.onQuestionRequest(info, dataBack);
                }
            }, true);
            THyperLinkUtils.setTextHyperlink(tvText.getContext(), spannableStringBuilder, listener, TOSClientKit.getTOSClientKitConfig() != null ? TOSClientKit.getTOSClientKitConfig().getTextHighLightRuleList() : new ArrayList<>(), false);
            tvText.setText(spannableStringBuilder);

            tvText.setMovementMethod(LinkMovementClickMethod.getInstance());
        } else {
            tvText.setText("");
        }
    }
}
