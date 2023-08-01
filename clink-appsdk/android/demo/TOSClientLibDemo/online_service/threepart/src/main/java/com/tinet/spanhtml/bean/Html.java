package com.tinet.spanhtml.bean;

import org.jsoup.nodes.Node;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlContent
 * @Author: liuzr
 * @CreateDate: 2021-12-02 17:51
 * @Description: html富文本基类
 */
public interface Html {

    /**
     * 解析对象
     *
     * @param node 解析对象
     */
    void parse(Node node);


}
