package com.tinet.tosclientkitdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhangping
 *
 * Toast工具类
 *
 * showStaticToast(final Activity act, final String msg) 弹静态吐司，无论子线程还是主线程都能执行（里面做了一个线程判断）
 * showToast(Context context, final String msg) 这个也是静态吐司，但是只能在主线程中执行
 * showShortToast(Context context, String message) 短时间显示Toast
 * showLongToast(Context context, String message) 长时间显示Toast
 *
 * @author zhangping
 */
public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /* 是否弹toast总开关 */
    public static boolean isShow = true;

    public static void showStaticToast(final Activity act, final String msg) {
        //获取当前线程
        String nowThreadName = Thread.currentThread().getName();
        //如果为主线程
        if ("main".equals(nowThreadName)) {
            if (isShow)
                showToast(act, msg);

            //如果为子线程
        } else {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (isShow)
                        showToast(act, msg);
                }
            });
        }
    }


    private static Toast toast;

    //如果在主线程弹静态吐司可以使用这个，传入上下文即可
    public static void showToast(Context context, final String msg) {

        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        if (isShow)
            toast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShortToast(Context context, String message) {
        if(null == context){
            return;
        }

        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLongToast(Context context, String message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
