package com.tinet.threepart.tools;

import android.view.View;

/**
 * @author: liuzr
 * @date: 2021-12-09
 */
public class DoubleClickUtil {

    /**
     * 连击时间间隔判断
     */
    private static final int TIME_SPAN = 1500;

    /**
     * 是否为连击
     *
     * @param view
     * @return true 连击
     */
    public static boolean isFastClick(View view) {
        long lastClickTime = (view.getTag() != null && view.getTag() instanceof Long) ? (Long) view.getTag() : 0;

        long time = System.currentTimeMillis();
        long timeSpan = time - lastClickTime;
        if (timeSpan >= 0 && timeSpan <= TIME_SPAN) {
            return true;
        } else {
            lastClickTime = time;
            view.setTag(lastClickTime);
            return false;
        }
    }

}
