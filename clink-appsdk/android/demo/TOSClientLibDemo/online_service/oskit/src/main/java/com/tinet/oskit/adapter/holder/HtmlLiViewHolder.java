package com.tinet.oskit.adapter.holder;

import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.LinkMovementClickMethod;
import com.tinet.oskit.tool.THyperLinkUtils;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlLi;
import com.tinet.spanhtml.bean.HtmlTextList;
import com.tinet.spanhtml.bean.HtmlUl;
import com.tinet.spanhtml.listener.HtmlListener;

import java.util.ArrayList;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlOlViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-12-06 14:37
 * @Description:
 */
public class HtmlLiViewHolder extends TinetViewHolder<HtmlLi> {

    private SessionClickListener listener;
    private TextView tvText, tvLogo;
    private ImageView ivLogo;

    public HtmlLiViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView);

        this.listener = listener;
        tvText = itemView.findViewById(R.id.tvText);
        tvLogo = itemView.findViewById(R.id.tvLogo);
        ivLogo = itemView.findViewById(R.id.ivLogo);
    }

    @Override
    public void update(HtmlLi info) {
        super.update(info);
    }

    public void update(HtmlLi info, Html html, int position) {
        final HtmlTextList spanContent = info.getTextList();
        if (null != spanContent) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
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
                }
            });

            tvText.setMovementMethod(LinkMovementClickMethod.getInstance());
        }

        ivLogo.setVisibility(View.GONE);
        tvLogo.setVisibility(View.GONE);

        if (html instanceof HtmlUl) {
            ivLogo.setVisibility(View.VISIBLE);
            HtmlUl ul = (HtmlUl) html;
            if (TextUtils.isEmpty(ul.getStyle())) {
                ivLogo.setImageResource(R.drawable.ti_point);
            } else if (ul.getStyle().contains(HtmlUl.CIRCLE_STYLE)) {
                ivLogo.setImageResource(R.drawable.ti_circle);
            } else {
                ivLogo.setImageResource(R.drawable.ti_square);
            }
        } else {
            tvLogo.setVisibility(View.VISIBLE);
            tvLogo.setText(itemView.getContext().getString(R.string.ti_index_position, position));
        }
    }
}
