package com.tinet.oskit.tool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.tinet.timclientlib.utils.TStringUtils;

import org.json.JSONObject;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: TCommonUtils
 * @Author: zhangping
 * @CreateDate: 2022/12/30 11:33
 * @Description: 公共工具类
 */
public class TCommonUtils {

    /**
     * 根据content内容是否包含cardType=1来判断是否为物流卡片消息
     */
    public static boolean isLogisticsCardMessage(String content) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }

        try {
            JSONObject jsonObject = new JSONObject(content);
            if (jsonObject.has("cardType") && jsonObject.optString("cardType").equals("1")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 复制文本内容到系统剪贴板
     *
     * @param context
     * @param text
     * @return
     */
    public static boolean copyTextToClipboard(Context context, String text) {
        if (TStringUtils.isEmpty(text)) {
            return false;
        }
        // 获取系统剪贴板
        try {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, text);
            // 把数据集设置（复制）到剪贴板
            clipboard.setPrimaryClip(clipData);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean openUrlLink(Context context, String url) {
        if (TStringUtils.isEmpty(url) && !url.startsWith("http")) {
            return false;
        }
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }
}
