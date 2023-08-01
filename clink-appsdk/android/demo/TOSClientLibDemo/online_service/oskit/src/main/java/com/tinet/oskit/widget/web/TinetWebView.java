package com.tinet.oskit.widget.web;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tinet.oskit.tool.HtmlFileHelper;
import com.tinet.oskit.tool.HtmlHelper;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetWebView
 * @Author: liuzr
 * @CreateDate: 2021-09-01 15:50
 * @Description:
 */
public class TinetWebView extends WebView {

    public WebViewListener getListener() {
        return listener;
    }

    public void setListener(WebViewListener listener) {
        this.listener = listener;
    }

    private WebViewListener listener;

    public TinetWebView(@NonNull Context context) {
        super(context);

        init();
    }

    public TinetWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TinetWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WebSettings s = getSettings();
        s.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            s.setSavePassword(false);
        }
        s.setDefaultTextEncodingName("UTF-8");
        s.setSaveFormData(false);
        s.setDomStorageEnabled(true);
        s.setUseWideViewPort(false);
        s.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        s.setDatabaseEnabled(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        s.setLoadWithOverviewMode(false);
        s.setAllowFileAccess(true);
        s.setGeolocationEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            s.setTextZoom(100);
        } else {
            s.setTextSize(WebSettings.TextSize.NORMAL);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            s.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        s.setSupportZoom(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);

        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);

        s.setBlockNetworkImage(false);
        s.setPluginState(WebSettings.PluginState.ON);

        setWebViewClient(new TinetWebViewClient());
        setWebChromeClient(new TinetWebChromeClient());
        addJavascriptInterface(new TinetJsInterface(this), "tinet");

        setScrollBarStyle(View.SCROLLBAR_POSITION_DEFAULT);
        s.setLoadsImagesAutomatically(true);
        s.setRenderPriority(WebSettings.RenderPriority.HIGH);
    }

    /**
     * 加载数据
     */
    public void loadData(String data) {
        String newData = HtmlHelper.h5Container + HtmlFileHelper.handleImageStr(data) + HtmlHelper.h5ContainerEnd;
        loadDataWithBaseURL("", newData, HtmlHelper.MIME_TYPE, HtmlHelper.ENCODING, "");
    }

    /**
     * 加载数据
     */
    public void loadRobotData(String data) {
        loadDataWithBaseURL("", data, HtmlHelper.MIME_TYPE, HtmlHelper.ENCODING, "");
    }

}
