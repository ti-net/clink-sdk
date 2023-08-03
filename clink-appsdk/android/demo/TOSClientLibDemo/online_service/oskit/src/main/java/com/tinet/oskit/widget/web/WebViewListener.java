package com.tinet.oskit.widget.web;

/**
 * @ProjectName: TIMSDK
 * @ClassName: WebViewListener
 * @Author: liuzr
 * @CreateDate: 2021-09-01 17:47
 * @Description: webview的监听事件
 */
public interface WebViewListener {

    /**
     * 超链接点击事件
     *
     * @param url 链接地址
     */
    void onLinkClick(String url);

    /**
     * 查看大图
     *
     * @param url 图片地址
     */
    void viewImage(String url);

    /**
     * 视频播放
     *
     * @param url 视频地址
     */
    void onVideoPlay(String url);

}
