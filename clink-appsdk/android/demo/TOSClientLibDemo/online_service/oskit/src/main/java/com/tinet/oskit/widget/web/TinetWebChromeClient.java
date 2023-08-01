package com.tinet.oskit.widget.web;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import java.lang.ref.SoftReference;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetWebChromeClient
 * @Author: liuzr
 * @CreateDate: 2021-09-02 09:07
 * @Description:
 */
public class TinetWebChromeClient extends WebChromeClient {

    private SoftReference<TinetWebView> refWebView;

    @Nullable
    @Override
    public View getVideoLoadingProgressView() {
        if (null != refWebView.get()) {
            FrameLayout frameLayout = new FrameLayout(refWebView.get().getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return frameLayout;
        } else {
            return super.getVideoLoadingProgressView();
        }
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
    }

//    /** 视频播放全屏 **/
//    private void showCustomView(View view, CustomViewCallback callback) {
//        // if a view already exists then immediately terminate the new one
//        if (customView != null) {
//            callback.onCustomViewHidden();
//            return;
//        }
//
//        WebVideoActivity.this.getWindow().getDecorView();
//
//        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
//        fullscreenContainer = new FullscreenHolder(WebVideoActivity.this);
//        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
//        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
//        customView = view;
//        setStatusBarVisibility(false);
//        customViewCallback = callback;
//    }
//
//    /** 隐藏视频全屏 */
//    private void hideCustomView() {
//        if (customView == null) {
//            return;
//        }
//
//        setStatusBarVisibility(true);
//        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
//        decor.removeView(fullscreenContainer);
//        fullscreenContainer = null;
//        customView = null;
//        customViewCallback.onCustomViewHidden();
//        webView.setVisibility(View.VISIBLE);
//    }
}
