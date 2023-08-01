package com.tinet.spanhtml.listener;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlListener
 * @Author: liuzr
 * @CreateDate: 2021-12-02 18:28
 * @Description: html本身自有事件
 */
public interface HtmlListener {

    /**
     * 超链接点击事件
     *
     * @param href 超链接
     */
    void onHref(String href);

    /**
     * knowledge标签点击事件
     *
     * @param info 点击需要发送的文本
     */
    void onKnowledgeClick(String info,String dataBack);

}
