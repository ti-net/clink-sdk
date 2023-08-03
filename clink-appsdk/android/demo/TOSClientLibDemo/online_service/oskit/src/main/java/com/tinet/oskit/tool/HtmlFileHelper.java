package com.tinet.oskit.tool;

import android.text.TextUtils;

import com.tinet.oslib.Api;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * /api/chat_file开头的相对路径是直接加上域名就好
 * @ProjectName: TIMSDK
 * @ClassName: HtmlFileHelper
 * @Author: liuzr
 * @CreateDate: 2021-09-01 18:23
 * @Description:
 */
public class HtmlFileHelper {

    public static void handle(){
        String str = "<p><img class='w-100' src='/api/chat_file?fileKey=chat/message/23122020/chat_file/8000581/941daae6-9c03-47f4-819f-4a8f26183608.png&amp;fileName=123.png' ngxViewer ><img class='w-100' src='/api/chat_file?fileKey=chat/message/23122020/chat_file/8000581/083804a5-e083-47f8-867a-6884019e814c.jpg&amp;fileName=%E6%B5%8B%E8%AF%95123.jpg' ngxViewer ><img class='w-100' src='/api/chat_file?fileKey=chat/message/23122020/chat_file/8000581/a85bd9a6-8508-485c-9343-73541bf96143.jpeg&amp;fileName=%E6%B5%8B%E8%AF%95.jpeg' ngxViewer ><img class='w-100' src='/api/chat_file?fileKey=chat/message/23122020/chat_file/8000581/9f6f61fd-042a-472b-833c-1db429e15552.jpg&amp;fileName=%E6%B5%8B%E8%AF%95%E5%9B%BE%E7%89%87.jpg' ngxViewer ></p>\n<p><span style=\"color: #225566; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 15px; background-color: #ffffff;\">Charles is an HTTP proxy / HTTP monitor / Reverse Proxy that enables a developer to view all of the HTTP and SSL / HTTPS traffic between their machine and the Internet. This includes requests, responses and the HTTP headers (which contain the cookies and caching information).</span></p>\n<p><span style=\"color: #225566; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 15px; background-color: #ffffff;\"><a href=\"https://www.baidu.com\" target=\"_blank\" rel=\"noopener\">百度</a></span></p>\n<p><span style=\"color: #225566; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 15px; background-color: #ffffff;\">你好啊</span></p>";
        android.util.Log.d("------",str);

        String s = handleImageStr(str);

        android.util.Log.d("------",s);
    }

    /**
     * 得到网页中图片的地址
     */
    public static String handleImageStr(String htmlStr) {
        String newHtmlStr = htmlStr;
        Pattern pattern;
        Matcher matcher;
        String regEx_img = "<[img|video].*src\\s*=\\s*(.*?)[^>]*?>";
        pattern = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {
            // 得到<img />数据
            String img = matcher.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*[\"|\']?(.*?)([\"|\']>|\\s+)").matcher(img);
            while (m.find()) {
                if(m.groupCount()>=2) {
                    String str = m.group(0);
                    String content = m.group(1);

                    if(!TextUtils.isEmpty(content) && !content.startsWith(HtmlHelper.HTTP)){
                        //content 不是以http开头的
                        if(content.startsWith(HtmlHelper.ARTICLE_IMAGE)){
                            //知识库图片
                            String newStr = "src=\""+HtmlHelper.ARTICLE_IMAGE_PREF+content+"\"";
                            newHtmlStr = newHtmlStr.replace(str,newStr);
                        }else{
                            //普通图片，需要添加域名
                            String newStr = "src=\""+ Api.BASE_URL+content+"\"";
                            newHtmlStr = newHtmlStr.replace(str,newStr);
                        }
                    }
                }
            }
        }

        return newHtmlStr;
    }

    public static void getImgStr1(String htmlStr) {
        Set<String> pics = new HashSet<>();
        Pattern pattern;
        Matcher matcher;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*?>";
        pattern = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {
            android.util.Log.d("------",matcher.group());
        }


    }

    /**
     * 正则表达式过滤<img > 标签
     * @param str
     * @return
     */
    public static String cutOutImgPrefix(String str){

        String regex = "<img[^>]*>";

        return str.replaceAll(regex, "");
    }
}