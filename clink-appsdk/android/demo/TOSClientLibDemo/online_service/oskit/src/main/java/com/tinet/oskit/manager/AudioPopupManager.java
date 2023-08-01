package com.tinet.oskit.manager;

import android.view.View;
import java.lang.ref.SoftReference;

/**
 * @author: liuzeren
 * @date: 2023/6/13
 */
public class AudioPopupManager {

  public interface AudioListener{

    /**
     * 视图初始化
     * @param view
     */
    void initView(View view);

    /**
     * 开始录音，即手指按下
     * */
    void startRecording();

    /**
     * 录音超时
     */
    void timeoutTip();

    /**
     * 取消录音视图初始化（非真实取消录音）
     */
    void cancelRecording();

    /**
     * 录音时间太短
     */
    void shortRecording();

    /**
     * 声音大小变化
     * @param db 声音的大小
     */
    void onAudioChanged(int db);
  }

  private static SoftReference<AudioListener> refListener;

  /**
   * 注册监听
   */
  public static void registerAudioListener(AudioListener listener){
    unRegisterAudioListener();

    refListener = new SoftReference<>(listener);
  }

  /**
   * 取消注册监听
   */
  public static void unRegisterAudioListener(){
    if(refListener != null){
      refListener.clear();
    }
  }

  public static AudioListener getListener(){
    if(refListener != null){
      return refListener.get();
    }

    return null;
  }

}
