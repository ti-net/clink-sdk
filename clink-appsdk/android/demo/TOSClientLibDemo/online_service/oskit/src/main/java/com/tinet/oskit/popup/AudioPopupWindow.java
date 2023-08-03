package com.tinet.oskit.popup;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tinet.oskit.manager.AudioPopupManager;
import com.tinet.oskit.manager.AudioPopupManager.AudioListener;
import com.tinet.threepart.audio.AudioRecordManager;
import com.tinet.threepart.audio.IAudioRecordListener;
import com.tinet.threepart.tools.TFileUtils;
import com.tinet.oskit.R;

import java.io.File;

/**
 * @ProjectName: TIMSDK
 * @ClassName: AudioPopupWindow
 * @Author: liuzr
 * @CreateDate: 2021-08-24 14:17
 * @Description:
 */
public class AudioPopupWindow extends PopupWindow implements IAudioRecordListener {

    //语音存放位置
    public static String AUDIO_SAVE_DIR;

    public static final int DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND = 60;

    private TextView tvTimer;
    private TextView tvAudioState;
    private ImageView ivAudioState;

    private View anchor;

    private ISendVoiceListener listener;

    public void setListener(ISendVoiceListener listener) {
        this.listener = listener;
    }

    /**
     * 发送语音监听
     */
    public interface ISendVoiceListener {

        /**
         * 发送语音
         *
         * @param voicePath 语音路径
         */
        void onSend(String voicePath);

    }

    private Context mContext;

    public AudioPopupWindow(Context mContext, View anchor) {
        this.anchor = anchor;
        this.mContext = mContext;

        setContentView(LayoutInflater.from(mContext).inflate(R.layout.tinet_voice_popup_layout, null, false));

        AudioListener mAudioListener = AudioPopupManager.getListener();
        if(mAudioListener != null){
            mAudioListener.initView(getContentView());
        }else {
            ivAudioState = getContentView().findViewById(R.id.tinetVoiceStatusIcon);
            tvAudioState = getContentView().findViewById(R.id.tinetVoiceStatusText);
            tvTimer = getContentView().findViewById(R.id.tinetVoiceTimer);
        }

        setWidth(-1);
        setHeight(-1);

        setFocusable(false);
        setOutsideTouchable(false);

        setTouchable(false);

        initAudio(mContext);
    }

    private void initAudio(Context mContext) {
        AUDIO_SAVE_DIR = TFileUtils.getDir(mContext, "audio");

        AudioRecordManager.getInstance(mContext).setMaxVoiceDuration(DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND);
        final File audioDir = new File(AUDIO_SAVE_DIR);
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }

        AudioRecordManager.getInstance(mContext).setAudioSavePath(audioDir.getAbsolutePath());
        AudioRecordManager.getInstance(mContext).setAudioRecordListener(this);
    }

    public void show() {
        showAtLocation(anchor, 17, 0, 0);
    }

    @Override
    public void initTipView() {
        show();
    }

    @Override
    public void setTimeoutTipView(int counter) {
        AudioListener mAudioListener = AudioPopupManager.getListener();
        if(mAudioListener != null){
            mAudioListener.timeoutTip();
            return;
        }

        if(this.ivAudioState != null) {
            this.ivAudioState.setVisibility(View.GONE);
        }

        if(this.tvAudioState != null){
            this.tvAudioState.setVisibility(View.VISIBLE);
            this.tvAudioState.setText(R.string.ti_voice_rec);
        }

//        this.tvAudioState.setBackgroundResource(R.drawable.ti_voice_record_view_bg);
        if(this.tvTimer != null) {
            this.tvTimer.setText(String.format("%s", new Object[]{Integer.valueOf(counter)}));
            this.tvTimer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();

        if (AudioRecordManager.getInstance(mContext).isRecording()) {
            AudioRecordManager.getInstance(mContext).stopRecord();
        }
    }

    @Override
    public void setRecordingTipView() {
        AudioListener mAudioListener = AudioPopupManager.getListener();
        if(mAudioListener != null){
            mAudioListener.startRecording();
            return;
        }

        if(this.ivAudioState != null) {
            this.ivAudioState.setVisibility(View.VISIBLE);
            this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_1);
        }

        if(this.tvAudioState != null){
            this.tvAudioState.setVisibility(View.VISIBLE);
            this.tvAudioState.setText(R.string.ti_voice_rec);
        }
//        this.tvAudioState.setBackgroundResource(R.drawable.ti_voice_record_view_bg);

        if(this.tvTimer != null) {
            this.tvTimer.setVisibility(View.GONE);
        }
    }

    @Override
    public void setAudioShortTipView() {
        AudioListener mAudioListener = AudioPopupManager.getListener();
        if(mAudioListener != null){
            mAudioListener.shortRecording();
            return;
        }

        if(ivAudioState != null) {
            ivAudioState.setImageResource(R.mipmap.ti_ic_volume_wraning);
        }

        if(tvAudioState != null) {
            tvAudioState.setText(R.string.ti_voice_short);
        }
    }

    @Override
    public void setCancelTipView() {
        AudioListener mAudioListener = AudioPopupManager.getListener();
        if(mAudioListener != null){
            mAudioListener.cancelRecording();
            return;
        }

        if(this.tvTimer != null) {
            this.tvTimer.setVisibility(View.GONE);
        }

        if(this.ivAudioState!=null) {
            this.ivAudioState.setVisibility(View.VISIBLE);
            this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_cancel);
        }

        if(this.tvAudioState != null) {
            this.tvAudioState.setVisibility(View.VISIBLE);
            this.tvAudioState.setText(R.string.ti_voice_cancel);
        }
//        this.tvAudioState.setBackgroundResource(R.drawable.ti_corner_voice_style);
    }

    @Override
    public void destroyTipView() {
        dismiss();
    }

    @Override
    public void onStartRecord() {

    }

    @Override
    public void onFinish(Uri audioPath, int duration) {
        //发送文件
        if (audioPath != null) {
            File file = new File(audioPath.getPath());
            if (file.exists()) {
                // : 2020/5/14 发送音频文件
                if (null != listener) {
                    listener.onSend(audioPath.getPath());
                }
            }
        }
    }

    @Override
    public void onAudioDBChanged(int db) {
        AudioListener mAudioListener = AudioPopupManager.getListener();
        if(mAudioListener != null){
            mAudioListener.onAudioChanged(db);
            return;
        }

        if(this.ivAudioState == null){
            return;
        }

        switch (db / 5) {
            case 0:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_1);
                break;
            case 1:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_2);
                break;
            case 2:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_3);
                break;
            case 3:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_4);
                break;
            case 4:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_5);
                break;
            case 5:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_6);
                break;
            case 6:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_7);
                break;
            default:
                this.ivAudioState.setImageResource(R.mipmap.ti_ic_volume_8);
        }
    }

    public void release() {
        // : 2022/8/9 释放资源，避免内存泄露
        AudioRecordManager.getInstance(mContext).setAudioRecordListener(null);
    }

}
