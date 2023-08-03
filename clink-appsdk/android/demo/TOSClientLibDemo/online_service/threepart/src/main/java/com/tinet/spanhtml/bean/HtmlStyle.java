package com.tinet.spanhtml.bean;

import android.text.TextUtils;

import com.tinet.spanhtml.JsoupUtil;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlStyle
 * @Author: liuzr
 * @CreateDate: 2021-12-02 17:48
 * @Description: html样式
 */
public class HtmlStyle implements Html {

    /**
     * 标准字体大小，其他字体的大小参照该尺寸进行缩放处理
     */
    public static final int DEFAULT_SIZE = 14;

    /**
     * style 标签  color
     */
    public static final String COLOR = "color";

    /**
     * style 标签  color
     */
    public static final String FONT_SIZE = "font-size";

    /**
     * 大小
     * 以16为基数，16为H5标准字体大小
     */
    private int size = DEFAULT_SIZE;

    /**
     * 颜色
     */
    private String color;

    /**
     * 是否加粗
     */
    private boolean bold;

    /**
     * 是否有下划线
     */
    private boolean underline;

    /**
     * 是否为下标
     */
    private boolean subscript;

    /**
     * 是否斜体
     */
    private boolean em;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean isSubscript() {
        return subscript;
    }

    public void setSubscript(boolean subscript) {
        this.subscript = subscript;
    }

    public boolean isEm() {
        return em;
    }

    public void setEm(boolean em) {
        this.em = em;
    }

    @Override
    public void parse(Node node) {
        if (node instanceof Element) {
            Element element = (Element) node;
            if (JsoupUtil.STRONG.equals(element.tagName())) {
                //粗体
                setBold(true);
            } else if (JsoupUtil.EM.equals(element.tagName())) {
                //斜体
                setEm(true);
            } else if (JsoupUtil.SPAN.equals(element.tagName()) || JsoupUtil.DIV.equals(element.tagName())) {
                //字体颜色
                String color = getColor(element);
                if (!TextUtils.isEmpty(color)) {
                    setColor(color);
                }

                //字体大小
                setSize(getTextSize(element));
                //下划线
                setUnderline(isUnderline(element));
            }
        }
    }

    public HtmlStyle copy() {
        HtmlStyle style = new HtmlStyle();
        style.setBold(isBold());
        style.setColor(getColor());
        style.setEm(isEm());
        style.setSize(getSize());
        style.setSubscript(isSubscript());
        style.setUnderline(isUnderline());

        return style;
    }

    /**
     * 获取颜色属性
     *
     * @param element
     * @return
     */
    private String getColor(Element element) {
        String color = "";
        if (null == element) {
            return color;
        }

        Attributes attributes = element.attributes();
        if (null != attributes) {
            for (Attribute attribute : element.attributes()) {
                if (!TextUtils.isEmpty(attribute.getValue())) {
                    String styleValueByKey = JsoupUtil.getStyleValueByKey(COLOR, attribute.getValue());
                    if (!TextUtils.isEmpty(styleValueByKey)) {
                        color = styleValueByKey;
                        break;
                    }
                }
            }
        }

        return color;
    }

    private int getTextSize(Element element) {
        int textSize = DEFAULT_SIZE;
        if (null == element) {
            return textSize;
        }

        Attributes attributes = element.attributes();
        if (null != attributes) {
            for (Attribute attribute : element.attributes()) {
                if (!TextUtils.isEmpty(attribute.getValue())) {
                    String styleValueByKey = JsoupUtil.getStyleValueByKey(FONT_SIZE, attribute.getValue());
                    if (!TextUtils.isEmpty(styleValueByKey)) {
                        styleValueByKey = styleValueByKey.replace("px","");
                        try {
                            textSize = Integer.parseInt(styleValueByKey);
                        }catch (Exception e){
                            textSize = DEFAULT_SIZE;
                        }
                        break;
                    }
                }
            }
        }

        return textSize;
    }

    /**
     * 是否有下划线
     *
     * @param element
     * @return
     */
    private boolean isUnderline(Element element) {
        boolean underline = false;

        if (null == element) {
            return false;
        }

        Attributes attributes = element.attributes();
        if (null != attributes) {
            for (Attribute attribute : element.attributes()) {
                if (!TextUtils.isEmpty(attribute.getKey())) {
                    if (attribute.getKey().contains(JsoupUtil.UNDERLINE)) {
                        underline = true;
                        break;
                    }
                }

                if (!TextUtils.isEmpty(attribute.getValue())) {
                    if (attribute.getValue().contains(JsoupUtil.UNDERLINE)) {
                        underline = true;
                        break;
                    }
                }
            }
        }

        return underline;
    }
}
