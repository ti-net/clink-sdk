package com.tinet.spanhtml.bean;

import android.text.TextUtils;

import com.tinet.spanhtml.JsoupUtil;

import org.jsoup.nodes.Node;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlImage
 * @Author: liuzr
 * @CreateDate: 2021-12-02 18:39
 * @Description: <video src=""/> html视频
 */
public class HtmlVideo implements Html {

    /**
     * 视频链接
     */
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public void parse(Node node) {
        String src = JsoupUtil.getAttribute(node, JsoupUtil.SRC);
        if (!TextUtils.isEmpty(src)) {
            setSrc(JsoupUtil.handleImageSrc(src));
        }
    }
}
