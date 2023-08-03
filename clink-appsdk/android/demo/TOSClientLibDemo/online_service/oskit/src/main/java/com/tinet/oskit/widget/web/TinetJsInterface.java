package com.tinet.oskit.widget.web;

import android.webkit.JavascriptInterface;

import java.lang.ref.SoftReference;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetJsInterface
 * @Author: liuzr
 * @CreateDate: 2021-09-02 10:55
 * @Description:
 */
public class TinetJsInterface {

    private SoftReference<TinetWebView> refWebView;

    public TinetJsInterface(TinetWebView webView) {
        refWebView = new SoftReference<>(webView);
    }

    @JavascriptInterface
    public void viewImage(String str) {
        if (null != refWebView.get() && null != refWebView.get().getListener()) {
            refWebView.get().getListener().viewImage(str);
        }
    }

    @JavascriptInterface
    public void videoPlay(String str) {
        if (null != refWebView.get() && null != refWebView.get().getListener()) {
            refWebView.get().getListener().onVideoPlay(str);
        }
    }

}
