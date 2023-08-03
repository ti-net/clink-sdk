package com.tinet.oskit.tool;

import org.tinet.http.okhttp3.internal.Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ExecutorHelper
 * @Author: liuzr
 * @CreateDate: 2021-09-10 14:49
 * @Description: 线程池辅助类
 */
public final class ExecutorHelper {

    private ExecutorService executorService;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("tinet executor", false));
        }
        return executorService;
    }

    private ExecutorHelper() {
    }

    private volatile static ExecutorHelper helper;

    public static ExecutorHelper getHelper() {
        if (null == helper) {
            synchronized (ExecutorHelper.class) {
                if (null == helper) {
                    helper = new ExecutorHelper();
                }
            }
        }

        return helper;
    }

    public void execute(Runnable command) {
        executorService().execute(command);
    }
}
