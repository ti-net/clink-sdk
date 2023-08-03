package com.tinet.oskit.listener;

import android.graphics.drawable.Drawable;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TImageLoaderListener
 * @Author: liuzr
 * @CreateDate: 2021-08-27 09:48
 * @Description: 图片加载监听
 */
public interface TImageLoaderListener {

    /**
     * 图片资源加载成功
     */
    void onResourceReady(Drawable drawable);

    /**
     * 图片资源加载失败
     */
    void onLoadFailed();

}
