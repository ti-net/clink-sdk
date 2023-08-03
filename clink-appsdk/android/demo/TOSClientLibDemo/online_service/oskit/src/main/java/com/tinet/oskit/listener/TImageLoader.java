package com.tinet.oskit.listener;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TImageLoader
 * @Author: liuzr
 * @CreateDate: 2021-08-24 18:52
 * @Description: 图片加载器
 */
public interface TImageLoader {

    /**
     * 本地图片或本地缩略图片加载器
     *
     * @param uri 图片uri
     */
    void loadImage(ImageView imageView, Object uri);

    /**
     * 原图片指定高度宽度加载器
     *
     * @param uri            图片uri
     * @param placeholderImg 预加载图片
     * @param errorImg       异常图片
     */
    void loadImage(ImageView imageView, Object uri, @DrawableRes int placeholderImg, @DrawableRes int errorImg);

    /**
     * 原图片非ImageView加载器
     *
     * @param uri            网络图片
     * @param listener       图片加载监听，可以为空
     * @param originalWidth  宽度
     * @param originalHeight 高度
     */
    void loadImage(ImageView imageView, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener);

    /**
     * 图片加载器
     *
     * @param uri            网络图片
     * @param listener       图片加载监听，可以为空
     * @param originalWidth  宽度
     * @param originalHeight 高度
     */
    void loadImage(Context context, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener);

}
