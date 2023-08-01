package com.tinet.threepart.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;


/**
 * @创建者
 * @描述 和ui相关的工具类
 */
public class TUIUtils {

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Toast mToast;
    public static int screenWidth;
    public static int screenHeight;
    public static int screenMin;// 宽高中，小的一边
    public static int screenMax;// 宽高中，较大的值
    public static float density;
    public static float scaleDensity;
    public static float xdpi;
    public static float ydpi;
    public static int densityDpi;
    public static int statusbarheight;
    public static int navbarheight;

    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", duration);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 用于在线程中执行弹土司操作
     */
    public static void showToastSafely(final Context context, final String msg) {
        TUIUtils.getMainThreadHandler().post(new Runnable() {

            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

    /**
     * 得到resources对象
     *
     * @return
     */
    public static Resources getResource(Context context) {
        return context.getResources();
    }

    /**
     * 得到string.xml中的字符串
     *
     * @param resId
     * @return
     */
    public static String getString(int resId, Context context) {
        return getResource(context).getString(resId);
    }

    /**
     * 得到string.xml中的字符串，带点位符
     *
     * @return
     */
    public static String getString(Context context, int id, Object... formatArgs) {
        return getResource(context).getString(id, formatArgs);
    }

    /**
     * 得到string.xml中和字符串数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArr(Context context, int resId) {
        return getResource(context).getStringArray(resId);
    }

    /**
     * 得到colors.xml中的颜色
     *
     * @param colorId
     * @return
     */
    public static int getColor(Context context, int colorId) {
        return getResource(context).getColor(colorId);
    }

    /**
     * 得到应用程序的包名
     *
     * @return
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 得到主线程Handler
     *
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mHandler;
    }

    /**
     * 得到主线程id
     *
     * @return
     */
    public static long getMainThreadId() {
        return mHandler.getLooper().getThread().getId();
    }

    /**
     * 安全的执行一个任务
     *
     * @param task
     */
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();
        // 如果当前线程是主线程
        if (curThreadId == getMainThreadId()) {
            task.run();
        } else {
            // 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }

    /**
     * 安全的执行一个任务
     *
     * @param task
     */
    public static void postTaskSafely(Runnable task,int delayMillis) {
        int curThreadId = android.os.Process.myTid();
        // 如果当前线程是主线程
        if (curThreadId == getMainThreadId()) {
            task.run();
        } else {
            // 如果当前线程不是主线程
            getMainThreadHandler().postDelayed(task,delayMillis);
        }
    }

    /**
     * 延迟执行任务
     *
     * @param task
     * @param delayMillis
     */
    public static void postTaskDelay(Runnable task, int delayMillis) {
        getMainThreadHandler().postDelayed(task, delayMillis);
    }

    /**
     * 移除任务
     */
    public static void removeTask(Runnable task) {
        getMainThreadHandler().removeCallbacks(task);
    }

    /**
     * dip-->px
     */
    public static int dip2Px(Context context, int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density = getResource(context).getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2dip(Context context, int px) {

        float density = getResource(context).getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    /**
     * sp-->px
     */
    public static int sp2px(Context context, int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResource(context).getDisplayMetrics()) + 0.5f);
    }


    public static int getDisplayWidth(Context context) {
        if (screenWidth == 0) {
            GetInfo(context);
        }
        return screenWidth;
    }

    public static int getDisplayHeight(Context context) {
        if (screenHeight == 0) {
            GetInfo(context);
        }
        return screenHeight;
    }

    public static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
//        statusbarheight = getStatusBarHeight(context);
        navbarheight = getNavBarHeight(context);
//        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
    }


    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static void setFullScreen(Activity activity) {
        if (activity == null) return;
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }
}