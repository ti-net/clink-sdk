package com.tinet.oskit.tool;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

/**
 * @author: liuzr
 * @date: 2021-12-09
 */
public class ImageCacheHelper {

    private static final int APP_VERSION = 1;
    private static final int VALUE_COUNT = 1;

    /**
     * 1M
     */
    private static final int CACHE_SIZE = 1 * 1024 * 1024;

    private static ImageCacheHelper helper;

    private LruCache<String, String> cache = null;
    private DiskLruCache diskLruCache;

    private ImageCacheHelper(Context context) {
        cache = new LruCache<>(CACHE_SIZE);
        try {
            diskLruCache = DiskLruCache.open(context.getCacheDir(), APP_VERSION, VALUE_COUNT, CACHE_SIZE);
        } catch (Exception e) {
        }

    }

    public static ImageCacheHelper getHelper(Context context) {
        if (helper == null) {
            synchronized (ImageCacheHelper.class) {
                if (helper == null) {
                    helper = new ImageCacheHelper(context);
                }
            }
        }

        return helper;
    }

    public String getCacheUrl(Context context, String id) {
        if (TextUtils.isEmpty(id)) {
            return null;
        }

        String url = getHelper(context).cache.get(id);

        try {
            if (TextUtils.isEmpty(url)) {
                url = diskLruCache.get(id).getString(0);
            }
        } catch (Exception e) {
        }

        return url;
    }

    public void putCacheUrl(Context context, String id, String url) {
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(url)) {
            return;
        }

        getHelper(context).cache.put(id, url);

        try {
            DiskLruCache.Editor editor = diskLruCache.edit(id);
            editor.newOutputStream(0).write(url.getBytes());
            editor.commit();
        } catch (Exception e) {
        }
    }
}
