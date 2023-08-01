package com.tinet.spanhtml.bean;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.tinet.spanhtml.JsoupUtil;
import com.tinet.spanhtml.listener.HtmlListener;
import com.tinet.threepart.R;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlLink
 * @Author: liuzr
 * @CreateDate: 2021-12-02 18:04
 * @Description: 超链接（即<a href="http://www.baidu.com">百度一下</a>）
 */
public class HtmlLink implements Html {

    private static final String HREF = "href";

    /**
     * 超链接地址
     */
    private String href;

    /**
     * 链接标题
     * 标题可能是一个富文本（只支持文字富文本，不支持图片）
     */
    private ArrayList<Html> title;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<Html> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<Html> title) {
        this.title = title;
    }

    @Override
    public void parse(Node node) {
        //超文本
        String href = JsoupUtil.getAttribute(node, HREF);
        setHref(href);
        //超文本的链接为空，舍弃掉
        if (!TextUtils.isEmpty(href)) {
            title = new ArrayList<>();
            //超链接的标题在其子节点中，即超链接的标题也是一个富文本
            List<Node> nodes = node.childNodes();
            if (null != nodes && nodes.size() > 0) {
                for (Node subNode : nodes) {
                    JsoupUtil.parseElement(subNode, null, title);
                }
            } else {
                //超链接的标题只是文字
                String title = JsoupUtil.getAttribute(node, "title");
                if (TextUtils.isEmpty(title)) {
                    title = href;
                }

                HtmlText text = new HtmlText();
                text.setText(title);
                this.title.add(text);
            }
        }
    }

    public void convert(Context context, SpannableStringBuilder ssb) {

        ArrayList<Html> htmlTexts = getTitle();
        if (null != htmlTexts && htmlTexts.size() > 0) {
            for (Html htmlText : htmlTexts) {
                if (htmlText instanceof HtmlText) {
                    ((HtmlText) htmlText).convert(context, ssb);
                }
            }
        }

    }

    public void convert(Context context, SpannableStringBuilder ssb, HtmlListener listener, boolean isSender) {
        int where = ssb.length();

        ArrayList<Html> htmlTexts = getTitle();
        if (null != htmlTexts && htmlTexts.size() > 0) {
            for (Html htmlText : htmlTexts) {
                if (htmlText instanceof HtmlText) {
                    ((HtmlText) htmlText).convert(context, ssb);
                }
            }
        }

        //超链接需要有下划线
        appendUnderline(ssb, where, ssb.length());
        //超链接需要有点击事件
        appendClickableSpan(context, ssb, isSender, getHref(), where, ssb.length(), listener);
    }

    /**
     * 下划线
     *
     * @param ssb
     * @param where 起始位置
     * @param len   结束位置
     */
    private void appendUnderline(SpannableStringBuilder ssb, int where, int len) {
        UnderlineSpan span = new UnderlineSpan();
        ssb.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 单击事件
     *
     * @param href  跳转链接
     * @param ssb
     * @param where
     * @param len
     */
    private void appendClickableSpan(final Context context, SpannableStringBuilder ssb, final boolean isSender, final String href, int where, int len, final HtmlListener listener) {
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (null != listener) {
                    listener.onHref(href);
                }
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                int[] attrs = null;

                if (isSender) {
                    attrs = new int[]{R.attr.super_link_sender_color};
                } else {
                    attrs = new int[]{R.attr.super_link_receiver_color};
                }

                TypedArray typedArray = context.obtainStyledAttributes(attrs);
                ds.linkColor = typedArray.getColor(0, ContextCompat.getColor(context, R.color.tinet_send_bg));

                super.updateDrawState(ds);
            }
        };
        ssb.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
