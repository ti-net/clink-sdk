package com.tinet.oskit.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;

import com.tinet.threepart.tools.TFileUtils;
import com.tinet.threepart.tools.TUIUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: MediaHelper
 * @Author: liuzr
 * @CreateDate: 2021-09-06 15:03
 * @Description:
 */
public class MediaHelper {

    /**
     * 以http开头的为服务器端的视频，否则为本地视频
     */
    private static final String HTTP = "http";

    /**
     * 视频封面目录
     */
    public static final String COVER_PATH = "cover";

    /**
     * 保存的后缀名
     */
    public static final String SUFFIX = ".jpg";

    private volatile static MediaHelper helper;

    private Context context;

    private MediaHelper(Context context) {
        this.context = context;
    }

    public static MediaHelper getHelper(Context context) {
        if (null == helper) {
            synchronized (MediaHelper.class) {
                if (null == helper) {
                    helper = new MediaHelper(context.getApplicationContext());
                }
            }
        }

        return helper;
    }

    private List<SoftReference<MediaListener>> listeners = new ArrayList();
    /**
     * 避免重复加载
     */
    private HashSet<String> map = new HashSet<>();

    public interface MediaListener {

        /**
         * 回调监听
         *
         * @param uri         加载的视频的地址
         * @param thumnailUri 保存本地的封面地址
         */
        void callback(String uri, String thumnailUri);

    }

    /**
     * 获取视频的封面地址
     *
     * @param uri      视频的uri地址
     * @param listener 回调监听
     */
    public void handler(final String uri, final MediaListener listener) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }

        listeners.add(new SoftReference<>(listener));

        if (map.contains(uri)) {
            return;
        } else {
            map.add(uri);
        }

        ExecutorHelper.getHelper().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaMetadataRetriever media = new MediaMetadataRetriever();
                    if (uri.startsWith(HTTP)) {
                        media.setDataSource(uri, new HashMap<String, String>());
                    } else {
                        media.setDataSource(uri);
                    }
                    Bitmap bitmap = media.getFrameAtTime();
                    media.release();

                    String thumnail = context.getCacheDir().getPath() + COVER_PATH + uri.hashCode() + SUFFIX;

                    TFileUtils.saveImage(bitmap, thumnail);
                    bitmap.recycle();
                    notifyCover(uri, thumnail);
                    map.remove(uri);
                } catch (Exception e) {
                }
            }
        });
    }

    public void notifyCover(final String uri, final String thumnail) {
        Iterator<SoftReference<MediaListener>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            final SoftReference<MediaListener> sr = iterator.next();
            if (sr.get() == null) {
                listeners.remove(sr);
            } else {
                TUIUtils.postTaskDelay(new Runnable() {
                    @Override
                    public void run() {
                        sr.get().callback(uri, thumnail);
                    }
                }, 0);
            }
        }
    }
}
