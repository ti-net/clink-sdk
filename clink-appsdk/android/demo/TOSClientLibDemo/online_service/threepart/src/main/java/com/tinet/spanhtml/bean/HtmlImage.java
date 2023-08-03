package com.tinet.spanhtml.bean;

import android.text.TextUtils;

import com.tinet.spanhtml.JsoupUtil;

import org.jsoup.nodes.Node;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlImage
 * @Author: liuzr
 * @CreateDate: 2021-12-02 18:39
 * @Description: <img src=""/> html图片
 */
public class HtmlImage implements Html {

    /**
     * 图片链接
     */
    private String src;

    /**
     * 图片的横宽比
     */
    private float ratio = 4 / 3;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @Override
    public void parse(Node node) {
        //图片，需要关注的是图片的src，图片的宽高比暂时不管
        String src = JsoupUtil.getAttribute(node, JsoupUtil.SRC);

        if (!TextUtils.isEmpty(src)) {
            setSrc(JsoupUtil.handleImageSrc(src));
        }

        // : 2023/1/5 处理图片宽高比 展示更完整
        String widthStr = JsoupUtil.getAttribute(node, "width");
        String heightStr = JsoupUtil.getAttribute(node, "height");
        if (!TextUtils.isEmpty(widthStr) && !TextUtils.isEmpty(heightStr)) {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);
            if (width > 0 && height > 0){
                setRatio((float) width / height);
            }
        }
    }
}
