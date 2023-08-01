package com.tinet.oskit.tool;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.tinet.threepart.audio.IAudioPlayListener;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ProjectName: TIMSDK
 * @ClassName: AudioPlayHelper
 * @Author: liuzr
 * @CreateDate: 2021-08-25 13:36
 * @Description: 音频播放辅助类
 */
public class AudioPlayHelper {

    public interface AudioPlayListener {
        /**
         * 当前是否正在播放回调
         *
         * @param url    音频地址
         * @param isPlay 是否正在播放，true = 播放
         */
        void audio(String url, Boolean isPlay);
    }

    private static AudioPlayHelper helper;

    private AudioPlayHelper() {
    }

    public static AudioPlayHelper getHelper() {
        if (helper == null) {
            synchronized (AudioPlayHelper.class) {
                if (null == helper) {
                    helper = new AudioPlayHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 当前播放的音频的uri
     */
    private String currentUri;

    /**
     * 是否正在播放
     */
    private boolean isPlay = false;

    /**
     * 停止播放
     */
    public void stopPlay() {
        currentUri = null;
        AudioPlayManager.getInstance().stopPlay();
    }

    private ArrayList<SoftReference<AudioPlayListener>> refListeners = new ArrayList<>();

    private void notifyListener(String url, boolean isPlay) {
        Iterator<SoftReference<AudioPlayListener>> iterator = refListeners.iterator();
        while (iterator.hasNext()) {
            SoftReference<AudioPlayListener> refListener = iterator.next();
            if (refListener == null || refListener.get() == null) {
                refListeners.remove(refListener);
                continue;
            }

            refListener.get().audio(url, isPlay);
        }
    }

    public String getCurrentUri() {
        return currentUri;
    }

    /**
     * 播放音频
     */
    public void startPlay(Context mContext, String uri, AudioPlayListener mAudioPlayListener) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }

        if (uri.equals(currentUri) && isPlay) {
            return;
        }

        if (isPlay) {
            if (!TextUtils.isEmpty(currentUri)) {
                isPlay = false;
                notifyListener(currentUri, isPlay);
            }
        }

        refListeners.add(new SoftReference<>(mAudioPlayListener));
        this.currentUri = uri;

        isPlay = true;
        AudioPlayManager.getInstance().startPlay(mContext.getApplicationContext(), Uri.parse(uri), new IAudioPlayListener() {
            @Override
            public void onStart(Uri uri) {
                isPlay = true;
                currentUri = uri.toString();

                notifyListener(currentUri, isPlay);
            }

            @Override
            public void onStop(Uri uri) {
                isPlay = false;

                notifyListener(currentUri, false);
            }

            @Override
            public void onComplete(Uri uri) {
                isPlay = false;

                notifyListener(currentUri, false);
                currentUri = null;
            }
        });
    }

}
