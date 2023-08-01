package com.tinet.oskit.tool;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.model.TTextPatternRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: THyperLinkUtils
 * @Author: zhangping
 * @CreateDate: 2022/10/13 10:51
 * @Description: 描述说明
 */
public class THyperLinkUtils {

    /**
     * 匹配url正则
     */
    public static final String PATTERN_REGEX_URL = "((http[s]{0,1})://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";

    /**
     * 匹配url类型type说明
     */
    public static final String TEXT_PATTERN_MESSAGE_TYPE_URL = "textUrl";

    /**
     * @param context
     * @param spannableString     需要设置的 spannableString
     * @param listener            监听回调
     * @param textPatternRuleList 匹配规则列表
     * @param isSender            是否发送端
     * @return
     */
    public static SpannableStringBuilder setTextHyperlink(Context context, SpannableStringBuilder spannableString, final SessionClickListener listener, List<TTextPatternRule> textPatternRuleList, boolean isSender) {
        if (textPatternRuleList == null) {
            textPatternRuleList = new ArrayList<>();
        }
        // : 2022/10/13  添加默认httpUrl类型匹配规则
        int color = context.getResources().getColor(R.color.ti_receive_super_line_text_color);
        if (isSender) {
            color = context.getResources().getColor(R.color.ti_send_super_line_text_color);
        }
        textPatternRuleList.add(new TTextPatternRule(Pattern.compile(PATTERN_REGEX_URL, Pattern.CASE_INSENSITIVE), color, TEXT_PATTERN_MESSAGE_TYPE_URL));

        for (int i = 0; i < textPatternRuleList.size(); i++) {
            final TTextPatternRule tTextPatternRule = textPatternRuleList.get(i);
            Pattern pPhone = tTextPatternRule.getPattern();
            Matcher mPhone = pPhone.matcher(spannableString);
            while (mPhone.find()) {
                int start = mPhone.start();
                int end = mPhone.end();
                spannableString.setSpan(new TClickSpan(tTextPatternRule.getHighLightColor(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv = (TextView) v;
                        String linkText = tv.getText().subSequence(tv.getSelectionStart(), tv.getSelectionEnd()).toString();
                        if (listener != null) {
                            if (tTextPatternRule.getMessageEventType().equals(THyperLinkUtils.TEXT_PATTERN_MESSAGE_TYPE_URL)) {
                                listener.onLinkClick(linkText);
                            }
                            listener.onLinkClick(linkText, tTextPatternRule.getMessageEventType());
                        }
                    }
                }), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return spannableString;
    }

}
