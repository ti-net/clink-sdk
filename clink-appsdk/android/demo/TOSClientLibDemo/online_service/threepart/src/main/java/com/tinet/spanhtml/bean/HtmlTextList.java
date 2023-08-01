package com.tinet.spanhtml.bean;

import android.content.Context;
import android.text.SpannableStringBuilder;

import com.tinet.spanhtml.listener.HtmlListener;

import org.jsoup.nodes.Node;

import java.util.ArrayList;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlTextList
 * @Author: liuzr
 * @CreateDate: 2021-12-02 20:45
 * @Description: 一条完整的富文本(里面包含普通的富文本以及超链接)
 */
public class HtmlTextList extends ArrayList<Html> implements Html {

    @Override
    public void parse(Node node) {

    }

    public boolean hasContent() {
        return size() > 0;
    }

    /**
     * @param context
     * @param listener html默认点击事件
     * @param isSender 是否为发送消息
     * @return
     */
    public SpannableStringBuilder convert(Context context, HtmlListener listener, boolean isSender) {
        if (!hasContent()) {
            return null;
        }

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        for (Html content : this) {
            if (content instanceof HtmlText) {
                HtmlText htmlText = (HtmlText) content;
                htmlText.convert(context, ssb);
            } else if (content instanceof HtmlLink) {
                //超链接富文本
                //1、最外层需要添加下划线
                //2、内部可能有其他的富文本
                HtmlLink htmlLink = (HtmlLink) content;
                htmlLink.convert(context, ssb, listener, isSender);
            }else if (content instanceof HtmlKnowledge) {
                HtmlKnowledge htmlKnowledge = (HtmlKnowledge) content;
                htmlKnowledge.convert(context, ssb, listener, isSender);
            }
        }

        return ssb;
    }
}
