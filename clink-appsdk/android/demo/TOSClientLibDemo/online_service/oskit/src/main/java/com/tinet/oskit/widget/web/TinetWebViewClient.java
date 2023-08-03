package com.tinet.oskit.widget.web;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetWebViewClient
 * @Author: liuzr
 * @CreateDate: 2021-09-01 17:46
 * @Description: 自定义拦截
 */
public class TinetWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (view instanceof TinetWebView) {
            TinetWebView webView = (TinetWebView) view;
            if (webView.getListener() != null) {
                webView.getListener().onLinkClick(url);

                return true;
            }
        }

        return super.shouldOverrideUrlLoading(view, url);
    }

//    @Override
//    public void onLoadResource(WebView view, String url) {
//        if(!TextUtils.isEmpty(url)){
//            if(url.startsWith(HtmlHelper.TINET_PREF_DEFINE)){
//                url = url.substring(HtmlHelper.TINET_PREF_DEFINE.length());
//                if(url.startsWith(HtmlHelper.HTTP)){
//                    super.onLoadResource(view,url);
//                }else{
//                    //服务端返回的是一个相对地址
//                    if(url.startsWith(HtmlHelper.ARTICLE_IMAGE)){
//                        //是知识库图片
//                        super.onLoadResource(view,HtmlHelper.ARTICLE_IMAGE_PREF + url);
//                    } else {
//                        //不是知识库图片
//                        super.onLoadResource(view, Api.BASE_URL + url);
//                    }
//                }
//            }else{
//                super.onLoadResource(view, url);
//            }
//        }else {
//            super.onLoadResource(view, url);
//        }
//    }
//
//
//    @Nullable
//    @Override
//    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//        if(!TextUtils.isEmpty(url)){
//            if(url.startsWith(HtmlHelper.TINET_PREF_DEFINE)){
//                url = url.substring(HtmlHelper.TINET_PREF_DEFINE.length());
//                if(!url.startsWith(HtmlHelper.HTTP)){
//                    //服务端返回的是一个相对地址
//                    if(url.startsWith(HtmlHelper.ARTICLE_IMAGE)){
//                        //是知识库图片
//                        url = HtmlHelper.ARTICLE_IMAGE_PREF + url;
//                    } else {
//                        //不是知识库图片
//                        url = Api.BASE_URL + url;
//                    }
//                }
//            }
//        }
//
//        android.util.Log.d("------","old="+url);
//
//        return super.shouldInterceptRequest(view, url);
//    }
//
//    @Nullable
//    @Override
//    public WebResourceResponse shouldInterceptRequest(WebView view, final WebResourceRequest request) {
//
//        return super.shouldInterceptRequest(view, new WebResourceRequest() {
//            @Override
//            public Uri getUrl() {
//                String url = request.getUrl().toString();
//                if(!TextUtils.isEmpty(url)){
//                    if(url.startsWith(HtmlHelper.TINET_PREF_DEFINE)){
//                        url = url.substring(HtmlHelper.TINET_PREF_DEFINE.length());
//                        if(!url.startsWith(HtmlHelper.HTTP)){
//                            //服务端返回的是一个相对地址
//                            if(url.startsWith(HtmlHelper.ARTICLE_IMAGE)){
//                                //是知识库图片
//                                url = HtmlHelper.ARTICLE_IMAGE_PREF + url;
//                            } else {
//                                //不是知识库图片
//                                url = Api.BASE_URL + url;
//                            }
//                        }
//                    }
//                }
//
//                android.util.Log.d("------",url);
//                return Uri.parse(url);
//            }
//
//            @Override
//            public boolean isForMainFrame() {
//                return request.isForMainFrame();
//            }
//
//            @Override
//            public boolean isRedirect() {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    return request.isRedirect();
//                }else{
//                    return true;
//                }
//            }
//
//            @Override
//            public boolean hasGesture() {
//                return request.hasGesture();
//            }
//
//            @Override
//            public String getMethod() {
//                return request.getMethod();
//            }
//
//            @Override
//            public Map<String, String> getRequestHeaders() {
//                return request.getRequestHeaders();
//            }
//        });
//    }
}
