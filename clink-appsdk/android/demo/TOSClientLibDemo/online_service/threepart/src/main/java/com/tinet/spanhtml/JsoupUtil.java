package com.tinet.spanhtml;

import android.text.TextUtils;

import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlBr;
import com.tinet.spanhtml.bean.HtmlImage;
import com.tinet.spanhtml.bean.HtmlKnowledge;
import com.tinet.spanhtml.bean.HtmlLi;
import com.tinet.spanhtml.bean.HtmlLink;
import com.tinet.spanhtml.bean.HtmlOl;
import com.tinet.spanhtml.bean.HtmlStyle;
import com.tinet.spanhtml.bean.HtmlText;
import com.tinet.spanhtml.bean.HtmlTextList;
import com.tinet.spanhtml.bean.HtmlUl;
import com.tinet.spanhtml.bean.HtmlVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: JsoupUtil
 * @Author: liuzr
 * @CreateDate: 2021-12-02 17:49
 * @Description:
 */
public class JsoupUtil {

    private static final String IMG = "img";
    private static final String VIDEO = "video";
    public static final String SRC = "src";
    private static final String A = "a";
    private static final String BR = "br";
    private static final String OL = "ol";
    private static final String UL = "ul";
    private static final String LI = "li";

    private static final String P = "p";

    private static final String B = "b";

    /**
     * 加粗标识
     */
    public static final String STRONG = "strong";

    /**
     * 下划线标识
     */
    public static final String UNDERLINE = "underline";

    /**
     * 斜体标识
     */
    public static final String EM = "em";

    public static final String SPAN = "span";

    public static final String DIV = "div";

    public static final String BODY = "body";

    public static final String HTTP = "http";

    public static final String STYLE = "style";


    public static final String TABLE = "table";
    public static final String TBODY = "tbody";
    public static final String TR = "tr";
    public static final String TD = "td";

    public static final String KNOWLEDGE = "knowledge";

    /**
     * 过滤\n换行符，即不承认\n为换行符标识
     */
    public static final String NN = "\\n";

    /**
     * 知识库图片
     */
    public static final String ARTICLE_IMAGE = "article/images";

    /**
     * 知识库图片（file/attachment）
     */
    public static final String FILE_ATTACHMENT = "file/attachment";

    /**
     * 服务器相对地址
     */
    public static String BASE_URL = "";

    /**
     * 知识库图片
     */
    public static final String ARTICLES_IMAGES = "/api/kb/articles/images";

    /**
     * 知识库图片-前缀
     */
    public static final String ARTICLE_IMAGE_PREF = "?filePath=";

    /**
     * html字符串解析入口
     *
     * @param str html字符串
     * @return 解析后的标签集
     */
    public static ArrayList<Html> parseHtml(String str) {
        ArrayList<Html> contents = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return contents;
        }

        Document document = Jsoup.parse(str);
        if (document != null) {
            Elements elements = document.body().getAllElements();
            if (null != elements && elements.size() > 0) {
                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    if (BODY.equals(element.tagName())) {
                        List<Node> nodes = element.childNodes();
                        if (null != nodes && nodes.size() > 0) {
                            for (Node node : nodes) {
                                parseElement(node, null, contents);
                            }
                        }
                    }
                }
            }
        }

        return convertList(contents);
    }

    /**
     * 需要对contents进行二次处理
     * 文本、超链接中间若没有回车符，则处理成一条数据，将一连串的文本消息与超链接组成SpanContent
     *
     * @param contents 需要进行处理的标签集
     * @return 处理后的标签集
     */
    public static ArrayList<Html> convertList(ArrayList<Html> contents) {
        ArrayList<Html> newContent = new ArrayList<>();

        if (contents == null || contents.size() == 0) {
            return newContent;
        }

        HtmlTextList spanContent = null;
        for (Html htmlContent : contents) {
            if (htmlContent instanceof HtmlText || htmlContent instanceof HtmlLink || htmlContent instanceof HtmlKnowledge) {
                if (htmlContent instanceof HtmlText) {
                    HtmlText textContent = (HtmlText) htmlContent;
                    if (textContent.isNN()) {
                        continue;
                    }
                }

                if (spanContent == null) {
                    spanContent = new HtmlTextList();
                }

                spanContent.add(htmlContent);
            } else {
                if (spanContent != null) {
                    newContent.add(spanContent);
                }
                spanContent = null;

                newContent.add(htmlContent);
            }
        }

        if (spanContent != null) {
            newContent.add(spanContent);
        }

        spanContent = null;

        return newContent;
    }

    /**
     * 解析标签
     *
     * @param node     待解析的标签
     * @param style    标签父级的样式，子标签需要继承父级的样式
     * @param contents 解析完成后的标签集合
     */
    public static void parseElement(Node node, HtmlStyle style, ArrayList<Html> contents) {
        if (node instanceof TextNode) {
            HtmlText htmlText = new HtmlText();
            htmlText.setStyle(style);
            htmlText.parse(node);

            contents.add(htmlText);
        } else if (node instanceof Element) {
            Element element = (Element) node;
            switch (element.tagName()) {
                case P: {
                    List<Node> nodes = element.childNodes();
                    if (null != nodes) {
                        for (Node subNode : nodes) {
                            if (subNode instanceof TextNode) {
                                HtmlText htmlText = new HtmlText();
                                htmlText.setStyle(style);
                                htmlText.setText(((TextNode) subNode).text());
                                contents.add(htmlText);
                            } else {
                                if (null == style) {
                                    style = new HtmlStyle();
                                }
                                parseElement(subNode, style.copy(), contents);
                            }
                        }
                    }
                    contents.add(new HtmlBr());
                    break;
                }
                case IMG:
                    //图片
                    HtmlImage image = new HtmlImage();
                    image.parse(element);
                    contents.add(image);
                    break;
                case VIDEO:
                    //视频
                    HtmlVideo video = new HtmlVideo();
                    video.parse(element);
                    contents.add(video);
                    break;
                case A:
                    //超文本
                    HtmlLink link = new HtmlLink();
                    link.parse(element);
                    contents.add(link);
                    break;
                case KNOWLEDGE:
                    //超文本
                    HtmlKnowledge htmlKnowledge = new HtmlKnowledge();
                    htmlKnowledge.parse(element);
                    contents.add(htmlKnowledge);
                    break;
                case BR:
                    contents.add(new HtmlBr());
                    break;
                case OL:
                    HtmlOl ol = new HtmlOl();
                    ol.parse(element);
                    contents.add(ol);
                    break;
                case UL:
                    HtmlUl ul = new HtmlUl();
                    ul.parse(element);
                    contents.add(ul);
                    break;
                case LI:
                    HtmlLi li = new HtmlLi();
                    li.parse(element);
                    contents.add(li);
                    break;
                case TR:
                case STRONG:
                case SPAN:
                case DIV:
                case EM:
                case TABLE:
                case TBODY:
                case TD:
                    //div内容可能还有其他标识
                    if (null == style) {
                        style = new HtmlStyle();
                    }
                    style.parse(element);

                    List<Node> nodes = element.childNodes();
                    if (null != nodes) {
                        for (Node subNode : nodes) {
                            parseElement(subNode, style.copy(), contents);
                        }
                    }
                    break;
                case B:
                    //div内容可能还有其他标识
                    if (null == style) {
                        style = new HtmlStyle();
                    }
                    style.parse(element);
                    style.setBold(true);

                    List<Node> nodes1 = element.childNodes();
                    if (null != nodes1) {
                        for (Node subNode : nodes1) {
                            parseElement(subNode, style.copy(), contents);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * 获取元素的指定属性
     *
     * @param node 节点
     * @param key
     * @return
     */
    public static String getAttribute(Node node, String key) {
        String value = "";

        if (TextUtils.isEmpty(key)) {
            return value;
        }

        for (Attribute attribute : node.attributes()) {
            if (key.equals(attribute.getKey())) {
                value = attribute.getValue();
                break;
            }
        }

        return value;
    }

    /**
     * 处理图片相对路径问题
     *
     * @param src 相对路径
     * @return 完整路径
     */
    public static String handleImageSrc(String src) {
        if (TextUtils.isEmpty(src)) {
            return src;
        }

        if (src.toLowerCase().startsWith(HTTP)) {
            return src;
        }

        //content 不是以http开头的
        if (src.startsWith(ARTICLE_IMAGE) || src.startsWith(FILE_ATTACHMENT)) {
            //知识库图片
            return BASE_URL + ARTICLES_IMAGES + ARTICLE_IMAGE_PREF + src;
        } else {
            //普通图片，需要添加域名
            return BASE_URL + src;
        }
    }

    public static String getStyleValueByKey(String key, String styleStr) {
        if ((!TextUtils.isEmpty(key)) && (!TextUtils.isEmpty(styleStr))) {
            if (styleStr.contains(key)) {
                // : 2023/2/22 字符串拆分前，先替换空格，避免后端返回数据空格不一致
                styleStr = styleStr.replace(" ", "");
                String[] split = styleStr.split(";");
                for (String s : split) {
                    if (s.contains(key)) {
                        return s.split(":")[1];
                    }
                }
            }
        }
        return "";
    }

}
