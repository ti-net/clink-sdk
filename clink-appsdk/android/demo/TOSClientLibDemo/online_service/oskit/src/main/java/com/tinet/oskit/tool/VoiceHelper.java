package com.tinet.oskit.tool;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;

import com.tinet.threepart.tools.TUIUtils;
import com.tinet.timclientlib.manager.TIMBaseManager;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: TIMSDK
 * @ClassName: MediaHelper
 * @Author: liuzr
 * @CreateDate: 2021-09-06 15:03
 * @Description:
 */
public class VoiceHelper {

    private volatile static VoiceHelper helper;

    private Context context;

    private VoiceHelper(Context context) {
        this.context = context;
    }

    public static VoiceHelper getHelper(Context context) {
        if (null == helper) {
            synchronized (VoiceHelper.class) {
                if (null == helper) {
                    helper = new VoiceHelper(context.getApplicationContext());
                }
            }
        }

        return helper;
    }

    /**
     * 回调监听
     */
    private List<SoftReference<VoiceListener>> listeners = new ArrayList();

    public interface VoiceListener {

        /**
         * @param uri
         * @param duration
         */
        void callback(String uri, long duration);

    }

    public void handler(final String uri, final VoiceListener listener) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }

        listeners.add(new SoftReference<>(listener));

        ExecutorHelper.getHelper().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        final MediaPlayer mediaPlayer = new MediaPlayer();
                        Map<String, Object> headers = TIMBaseManager.getInstance().getInitOption().getAdvanceParams();
                        HashMap<String, String> hashMap = new HashMap<>();
                        if (uri.contains("https") && headers.containsKey("deBugEnv")) {
                            if ("ktTest".equals(headers.get("deBugEnv")))
                                hashMap.put("X-Virtual-Env", "dev.chat");
                            mediaPlayer.setDataSource(context.getApplicationContext(), Uri.parse(uri), hashMap);
                        } else
                            mediaPlayer.setDataSource(uri);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                notifyCover(uri, mp.getDuration());

                                mediaPlayer.release();
                            }
                        });
                        mediaPlayer.prepareAsync();
                    } catch (IOException e) {
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public void notifyCover(final String uri, final long duration) {
        Iterator<SoftReference<VoiceListener>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            final SoftReference<VoiceListener> sr = iterator.next();
            if (sr.get() == null) {
                listeners.remove(sr);
            } else {
                TUIUtils.postTaskDelay(new Runnable() {
                    @Override
                    public void run() {
                        sr.get().callback(uri, duration);
                    }
                }, 0);
            }
        }
    }
}
