package com.tinet.spanhtml.bean;

import com.tinet.spanhtml.JsoupUtil;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlOl
 * @Author: liuzr
 * @CreateDate: 2021-12-06 13:46
 * @Description: <ol style = 'list-style-type: lower-alpha;'>
 * <li>我还是一个中国人</li>
 * <li>我确定我还是一个中国人</li>
 * <li>我非常确定</li>
 * </ol>
 */
public class HtmlOl implements Html {

    private String style;

    private ArrayList<HtmlLi> lis;

    public ArrayList<HtmlLi> getLis() {
        return lis;
    }

    public void setLis(ArrayList<HtmlLi> lis) {
        this.lis = lis;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean hasLi() {
        return null != lis && lis.size() > 0;
    }

    @Override
    public void parse(Node node) {
        setStyle(JsoupUtil.getAttribute(node, JsoupUtil.STYLE));

        //解析节点
        List<Node> nodes = node.childNodes();
        ArrayList<Html> list = new ArrayList<>();
        if (null != nodes && nodes.size() > 0) {
            for (Node li : nodes) {
                JsoupUtil.parseElement(li, null, list);
            }
        }

        //只认可li标签
        setLis(new ArrayList<HtmlLi>());
        if (list.size() > 0) {
            for (Html html : list) {
                if (html instanceof HtmlLi) {
                    getLis().add((HtmlLi) html);
                }
            }
        }
    }
}
