package com.tinet.oskit.adapter.holder;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinet.oskit.R;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.adapter.QuestionAdapter;
import com.tinet.oskit.adapter.SessionHtmlAdapter;
import com.tinet.oskit.adapter.decoration.LinearLayoutManagerDecoration;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.HtmlHelper;
import com.tinet.oskit.tool.LinkMovementClickMethod;
import com.tinet.oskit.tool.THyperLinkUtils;
import com.tinet.oskit.widget.web.TinetWebView;
import com.tinet.oskit.widget.web.WebViewListener;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.spanhtml.JsoupUtil;
import com.tinet.threepart.emoji.MoonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:11
 * @Description:
 */
public class RobotGroupViewHolder extends RobotGroupBaseViewHolder {

    /**
     * 换一换限制
     */
    private static final int LIMIT = RobotGroupQuestionClassicViewHolder.LIMIT;
    /**
     * 当前偏移量
     */
    private int currentOffset = 0;

    private static final String NULL_STR = "null";

    private SessionHtmlAdapter adapter;

    private LinearLayoutManagerDecoration itemDecoration;

    public RobotGroupViewHolder(@NonNull View itemView, OnlineMessage message, SessionClickListener listener) {
        super(itemView, message, listener);
        itemDecoration = new LinearLayoutManagerDecoration(itemView.getContext(),  itemView.getResources().getDimensionPixelSize(R.dimen.ti_question_span), Color.TRANSPARENT);
    }

    private WebViewListener webViewListener = new WebViewListener() {
        @Override
        public void onLinkClick(String url) {
            if (null != listener) {
                listener.onLinkClick(url);
            }
        }

        @Override
        public void viewImage(String url) {
            if (null != listener) {
                ArrayList<String> list = new ArrayList<>();
                list.add(url);
                listener.onImageMessageClick(list, 0);
            }
        }

        @Override
        public void onVideoPlay(String url) {
            if (null != listener) {
                listener.videoPlay(url);
            }
        }
    };

    @Override
    public void update(final OnlineQuestion question) {
        super.update(question);

        switch (question.getType()) {
            case OnlineMessageType.ROBOT_HTML: {
                String str = question.getText();
                if (HtmlHelper.hasHTMLTags(str)) {
                    adapter = new SessionHtmlAdapter(listener);
                    RecyclerView recyclerView = itemView.findViewById(R.id.recyclerView);
                    if (null != recyclerView) {
                        recyclerView.setAdapter(adapter);
                        adapter.setData(JsoupUtil.parseHtml(str));
                    }
                } else {
                    TextView tvText = itemView.findViewById(R.id.tvText);
                    if (null != tvText) {
                        // : 2022/10/19 机器人文本消息带高亮显示规则配置
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
                        THyperLinkUtils.setTextHyperlink(tvText.getContext(), spannableStringBuilder, listener, TOSClientKit.getTOSClientKitConfig() != null ? TOSClientKit.getTOSClientKitConfig().getTextHighLightRuleList() : new ArrayList<>(), false);
                        tvText.setText(spannableStringBuilder);
                        tvText.setMovementMethod(LinkMovementClickMethod.getInstance());
                    }
                }

                break;
            }
            case OnlineMessageType.ROBOT_CHOOSE:
            case OnlineMessageType.ROBOT_ABOUT_QUESTION:
            case OnlineMessageType.ROBOT_GUESS_QUESTION:
            case OnlineMessageType.ROBOT_COMMON_QUESTION:
            case OnlineMessageType.ROBOT_APPROX_QUESTION:
            case OnlineMessageType.ROBOT_COMMENT_QUESTION: {
                RecyclerView recyclerView = itemView.findViewById(R.id.recyclerView);
                TextView tvGuess = itemView.findViewById(R.id.tvGuess);
                //换一换
                TextView tvChanged = itemView.findViewById(R.id.tvChanged);
                tvChanged.setVisibility(View.GONE);
                if (TextUtils.isEmpty(question.getText()) || NULL_STR.equalsIgnoreCase(question.getText())) {
                    tvGuess.setVisibility(View.GONE);
                } else {
                    tvGuess.setVisibility(View.VISIBLE);
                    tvGuess.setText((!TextUtils.isEmpty(question.getText()) && question.getText() != "null") ? question.getText() : "猜你想问");
                }
                recyclerView.removeItemDecoration(itemDecoration);
                recyclerView.addItemDecoration(itemDecoration);
                if (recyclerView != null) {
                    List<String> cards = change(question.getCards(), false);
                    if (question.getCards() != null && question.getCards().size() > LIMIT) {
                        tvChanged.setVisibility(View.VISIBLE);
                    }

                    final QuestionAdapter adapter = new QuestionAdapter(listener);
                    recyclerView.setAdapter(adapter);
                    adapter.setData(cards);
                    adapter.setKnowledgeList(question.getKnowledgeList());

                    tvChanged.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.setData(change(question.getCards(), true));
                            adapter.setKnowledgeList(question.getKnowledgeList());
                        }
                    });
                }
                break;
            }
            default: {
                String str = question.getText();
                if (HtmlHelper.hasHTMLTags(str)) {
                    TinetWebView wvName = itemView.findViewById(R.id.tvText);
                    wvName.setListener(webViewListener);
                    if (null != wvName) {
                        wvName.loadRobotData(question.getRichText(message.getOnlineContent()));
                    }
                } else {
                    TextView tvName = itemView.findViewById(R.id.tvText);
                    if (null != tvName) {
                        MoonUtils.identifyFaceExpression(itemView.getContext(),
                                tvName, str, ImageSpan.ALIGN_BOTTOM);
                    }
                }

                break;
            }
        }
    }

    /**
     * 换一批
     *
     * @param change 是否换下一页
     */
    private List<String> change(List<String> cards, boolean change) {
        List<String> data = new ArrayList<>();
        if (cards != null && cards.size() > 0) {
            int start = currentOffset;
            int end = currentOffset + LIMIT;

            if (change) {
                start = end;
                end = end + LIMIT;
            }

            if (start >= cards.size()) {
                //没有更多了，再次从第一页开始展示
                start = 0;
                end = start + LIMIT;
            }

            if (end > cards.size()) {
                end = cards.size();
            }

            for (int i = start; i < end; i++) {
                data.add(cards.get(i));
            }

            currentOffset = start;
        }
        return data;
    }
}
