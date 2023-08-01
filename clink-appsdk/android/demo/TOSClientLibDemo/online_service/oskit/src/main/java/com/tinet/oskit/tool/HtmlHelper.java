package com.tinet.oskit.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oslib.Api;

import java.lang.ref.SoftReference;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * @ProjectName: TIMSDK
 * @ClassName: HtmlHelper
 * @Author: liuzr
 * @CreateDate: 2021-08-31 09:11
 * @Description: html标签辅助工具类
 */
public class HtmlHelper {

    private static final String imgSrcRegex = "<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>";

    public static final String MIME_TYPE = "text/html";

    public static final String ENCODING = "utf-8";

    /**
     * 服务端返回的是相对地址，需要作处理，有两种实现方式
     * 1、使用正则统一修改。
     * 2、加个标志位，然后在webview中统一进行拦截。该方法实现简单。TINET_PREF_DEFINE就是用来添加的标志位。
     */
    public static final String TINET_PREF_DEFINE = "http://tinet_os_sdk";

    public static final String HTTP = "http";

    /**
     * 知识库图片
     */
    public static final String ARTICLE_IMAGE = "article/images";

    /**
     * 知识库图片-前缀
     */
    public static final String ARTICLE_IMAGE_PREF = Api.BASE_URL + Api.ARTICLES_IMAGES + "?filePath=";

    /**
     * 为图片标签统一添加前缀，解决相对地址问题
     */
    private static final String jsForImage = "<script>\n" +
            "    var baseUrl = \"" + TINET_PREF_DEFINE + "\"\n" +
            "    var imgs = document.getElementsByTagName(\"img\");\n" +
            "    for(var i = 0;i<imgs.length;i++){\n" +
            "        const src = imgs.item(i).getAttribute('src')\n" +
            "        imgs.item(i).addEventListener('click', () => {\n" +
            "          tinet.viewImage(src)\n" +
            "        })" +
            "    }\n" +
            "    var videos = document.getElementsByTagName(\"video\");\n" +
            "    for(var i = 0;i<videos.length;i++){\n" +
            "        const src = videos.item(i).getAttribute('src')\n" +
            "        videos.item(i).addEventListener('click', () => {\n" +
            "          tinet.videoPlay(src)\n" +
            "        })" +
            "    }\n" +
            "</script>";

    public static final String h5Container = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no, width=device-width,viewport-fit=cover\">\n" +
            "  <meta name=\"format-detection\" content=\"telphone=no, email=no\" />\n" +
            "  <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" />\n" +
            "  <meta-data android:name=\"android.max_aspect\" android:value=\"ratio_float\" />\n" +
            "  <title>tinet</title>\n" +
            "  <style type=\"text/css\">\n" +
            "   img{\n" +
            "      width: 100% !important;\n" +
            "   }\n" +
            "   video{\n" +
            "      height: 75vw;\n" +
            "      width: 95vw;\n" +
            "      background-color: #000;\n" +
            "   }\n" +
            "  body{\n" +
            "     display: flex;\n" +
            "     flex-wrap: wrap;\n" +
            "   }" +
            "  </style>\n" +
            "</head>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<body>\n";

    public static final String h5ContainerEnd =
            "</body>\n" + jsForImage +
                    "\n" +
                    "</html>";

    private static class HtmlImageGetter implements Html.ImageGetter {

        private SoftReference<TextView> srTv = null;

        public HtmlImageGetter(TextView tv) {
            srTv = new SoftReference<>(tv);
        }

        @Override
        public Drawable getDrawable(String source) {
            if (srTv.get() != null) {
                Context context = srTv.get().getContext();
                TOSClientKit.getImageLoader().loadImage(context,
                        source, context.getResources().getDimensionPixelSize(R.dimen.ti_image_size),
                        context.getResources().getDimensionPixelSize(R.dimen.ti_image_size), new TImageLoaderListener() {
                            @Override
                            public void onResourceReady(Drawable drawable) {
                                if (srTv.get() != null) {
                                    srTv.get().setCompoundDrawablePadding(srTv.get().getResources().getDimensionPixelSize(R.dimen.ti_image_drawable_padding));
                                    srTv.get().setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, drawable);
                                }
                            }

                            @Override
                            public void onLoadFailed() {

                            }
                        });

                return context.getResources().getDrawable(R.drawable.ti_transparent_drawable);
            }

            return null;
        }
    }

    public static final void setHtml(TextView tv, String str) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(str, FROM_HTML_MODE_LEGACY, new HtmlImageGetter(tv), null));
        } else {
            Html.fromHtml(str, new HtmlImageGetter(tv), null);
        }
    }

    private static final String HTML_FLAG = "<";

    /**
     * 是否包含html标签
     */
    public static boolean hasHTMLTags(String text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }

        return text.contains(HTML_FLAG) || text.contains(HTTP);
    }

}
