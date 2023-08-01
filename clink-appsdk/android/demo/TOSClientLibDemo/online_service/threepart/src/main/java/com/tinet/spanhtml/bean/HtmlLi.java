package com.tinet.spanhtml.bean;

import com.tinet.spanhtml.JsoupUtil;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlLi
 * @Author: liuzr
 * @CreateDate: 2021-12-06 13:48
 * @Description:
 */
public class HtmlLi implements Html {

    /**
     * html的textlist
     */
    private HtmlTextList textList;

    public HtmlTextList getTextList() {
        return textList;
    }

    public void setTextList(HtmlTextList textList) {
        this.textList = textList;
    }

    @Override
    public void parse(Node node) {
        ArrayList<Html> list = new ArrayList<>();

        List<Node> children = node.childNodes();
        if (null != children && children.size() > 0) {
            for (Node child : children) {
                JsoupUtil.parseElement(child, null, list);
            }
        }

        list = JsoupUtil.convertList(list);

        //li中只认为是一个富文本（可以包含超链接），其他一概不解析
        if (null != list && list.size() > 0) {
            for (Html html : list) {
                if (html instanceof HtmlTextList) {
                    setTextList((HtmlTextList) html);
                    break;
                }
            }
        }
    }

}
