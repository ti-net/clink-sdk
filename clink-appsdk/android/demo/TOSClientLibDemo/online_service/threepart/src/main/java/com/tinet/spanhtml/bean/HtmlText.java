package com.tinet.spanhtml.bean;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.UnderlineSpan;

import com.tinet.spanhtml.JsoupUtil;
import com.tinet.threepart.emoji.EmojiManager;
import com.tinet.threepart.emoji.MoonUtils;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.regex.Matcher;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlText
 * @Author: liuzr
 * @CreateDate: 2021-12-02 18:06
 * @Description: html的文本
 */
public class HtmlText implements Html {

    /**
     * 文本内容
     */
    private String text;

    /**
     * 文本样式
     */
    private HtmlStyle style;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HtmlStyle getStyle() {
        return style;
    }

    public void setStyle(HtmlStyle style) {
        this.style = style;
    }

    @Override
    public void parse(Node node) {
        if (node instanceof TextNode) {
            setText(((TextNode) node).text());
        }

        HtmlStyle style = getStyle();
        if (style == null) {
            style = new HtmlStyle();
        }
        style.parse(node);
        setStyle(style);
    }

    /**
     * 内容为不规范的换行符
     * 换行符只认可<br>
     *
     * @return
     */
    public boolean isNN() {
        if (JsoupUtil.NN.equals(text)) {
            return true;
        }

        return false;
    }

    /**
     * 解析普通文本
     *
     * @param ssb 富文本
     */
    public void convert(Context context, SpannableStringBuilder ssb) {
        if (TextUtils.isEmpty(getText())) {
            return;
        }

        int where = ssb.length();
        ssb.append(getText());
        int len = ssb.length();
        replaceEmoticons(context, ssb, text, where);

        if (null != getStyle()) {
            appendStyle(ssb, getStyle(), where, len);
        }
    }

    /**
     * 处理表情
     *
     * @param content 内容
     */
    private void replaceEmoticons(Context context, SpannableStringBuilder ssb, String content, int start) {
        if (TextUtils.isEmpty(content)) {
            return;
        }

        //处理表情
        Matcher matcher = EmojiManager.getPattern().matcher(content);

        while (matcher.find()) {
            int from = start + matcher.start();
            int to = start + matcher.end();
            String emot = ssb.toString().subSequence(from, to).toString();
            Drawable d = MoonUtils.getEmotDrawable(context, emot, MoonUtils.DEF_SCALE);
            if (d != null) {
                ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
                ssb.setSpan(span, from, to, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    /**
     * 样式应用
     *
     * @param ssb
     * @param style 样式
     * @param where
     * @param len
     */
    private void appendStyle(SpannableStringBuilder ssb, HtmlStyle style, int where, int len) {
        if (!TextUtils.isEmpty(style.getColor())) {
            appendForegroundColorSpan(ssb, style.getColor(), where, len);
        }

        if (style.isBold() || style.isEm()) {
            appendStyleSpan(ssb, style.isBold(), style.isEm(), where, len);
        }

        if (style.isUnderline()) {
            appendUnderline(ssb, where, len);
        }

        if (style.isSubscript()) {
            appendSubscriptSpan(ssb, where, len);
        }

        if (style.getSize() != style.DEFAULT_SIZE) {
            appendRelativeSizeSpan(ssb, style.getSize(), where, len);
        }
    }

    /**
     * 添加前景色
     *
     * @param ssb
     * @param color 颜色
     * @param where 起始位置
     * @param len   结束位置
     */
    private void appendForegroundColorSpan(SpannableStringBuilder ssb, String color, int where, int len) {
        try {
            ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor(color));
            ssb.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
        }
    }

    /**
     * 粗体、斜体
     *
     * @param ssb
     * @param bold  加粗
     * @param em    斜体
     * @param where 起始位置
     * @param len   结束位置
     */
    private void appendStyleSpan(SpannableStringBuilder ssb, boolean bold, boolean em, int where, int len) {
        StyleSpan span = null;
        if (bold && em) {
            span = new StyleSpan(Typeface.BOLD_ITALIC);
        } else if (bold) {
            span = new StyleSpan(Typeface.BOLD);
        } else {
            span = new StyleSpan(Typeface.ITALIC);
        }

        ssb.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
     * 字体相对大小
     *
     * @param ssb
     * @param size  字体大小
     * @param where
     * @param len
     */
    private void appendRelativeSizeSpan(SpannableStringBuilder ssb, int size, int where, int len) {
        RelativeSizeSpan span = new RelativeSizeSpan(size * 1.0f / HtmlStyle.DEFAULT_SIZE);
        ssb.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 字体相对大小
     *
     * @param ssb
     * @param where
     * @param len
     */
    private void appendSubscriptSpan(SpannableStringBuilder ssb, int where, int len) {
        SubscriptSpan span = new SubscriptSpan();
        ssb.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
