package com.tinet.threepart.tools;

/**
 * Time:2019/11/6
 * Author:zhaixs
 * Description: 防止快速连续点击
 */

public class TClickUtil {
    private static final int DELAY = 500;
    private static final int DELAY_1000 = 1000;
    private static final int DELAY_2000 = 2000;
    private static long lastClickTime = 0;

    public static boolean isNotFastClick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DELAY) {
            lastClickTime = currentTime;
            return true;
        }
        return false;
    }

    public static boolean isNotFastClick1000() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DELAY_1000) {
            lastClickTime = currentTime;
            return true;
        }
        return false;
    }

    public static boolean isNotFastClick2000() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DELAY_2000) {
            lastClickTime = currentTime;
            return true;
        }
        return false;
    }
}
