package com.tinet.spanhtml.bean;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.tinet.spanhtml.JsoupUtil;
import com.tinet.spanhtml.listener.HtmlListener;
import com.tinet.threepart.R;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlLink
 * @Author: liuzr
 * @CreateDate: 2021-12-02 18:04
 * @Description: 超链接（即<a href="http://www.baidu.com">百度一下</a>）
 */
public class HtmlKnowledge implements Html {

    /**
     * 代发送文本内容
     */
    private String dataFrontend;

    /**
     * 回传服务器json字符串
     */
    private String dataBack;

    /**
     * 链接标题
     * 标题可能是一个富文本（只支持文字富文本，不支持图片）
     */
    private ArrayList<Html> title;

    public String getDataFrontend() {
        return dataFrontend;
    }

    public void setDataFrontend(String dataFrontend) {
        this.dataFrontend = dataFrontend;
    }

    public String getDataBack() {
        return dataBack;
    }

    public void setDataBack(String dataBack) {
        this.dataBack = dataBack;
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
        String dataFrontend = JsoupUtil.getAttribute(node, "data-frontend");
        setDataFrontend(dataFrontend);

        //超文本
        String dataBack = JsoupUtil.getAttribute(node, "data-back");
        setDataBack(dataBack);

        //超文本的链接为空，舍弃掉
        if (!TextUtils.isEmpty(dataFrontend)) {
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
                    title = dataFrontend;
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
                    HtmlText htmlText1 = (HtmlText) htmlText;
                    HtmlStyle style = htmlText1.getStyle();
                    if(style!=null){
                        style.setUnderline(false);
                    }
                    htmlText1.setStyle(style);
                    htmlText1.convert(context, ssb);
                }
            }
        }

        //超链接需要有点击事件
        appendClickableSpan(context, ssb, isSender, getDataFrontend(), where, ssb.length(), listener);
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
                    listener.onKnowledgeClick(href,getDataBack());
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
