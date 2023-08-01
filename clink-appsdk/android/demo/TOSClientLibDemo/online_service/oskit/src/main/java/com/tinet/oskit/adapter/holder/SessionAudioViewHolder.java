package com.tinet.oskit.adapter.holder;

import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oskit.tool.VoiceHelper;

import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.VoiceMessage;
import com.tinet.threepart.tools.TUIUtils;
import com.tinet.oskit.popup.AudioPopupWindow;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.AudioPlayHelper;
import com.tinet.timclientlib.common.constans.TMessageStatus;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionAudioViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 14:51
 * @Description: 音频
 */
public class SessionAudioViewHolder extends SessionViewHolder implements AudioPlayHelper.AudioPlayListener {

    private TextView tvDuration;
    private View viewAudio;
    private ImageView ivAudio;

    private String audioUri;

    public SessionAudioViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        tvDuration = itemView.findViewById(R.id.tvDuration);
        viewAudio = itemView.findViewById(R.id.viewAudio);
        ivAudio = itemView.findViewById(R.id.ivAudio);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        final VoiceMessage voiceMessage = (VoiceMessage) info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), voiceMessage.getSenderType(), viewAudio);

        audioUri = voiceMessage.getUri();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getStatus() == TMessageStatus.MSG_STATUS_SEND_SUCCESS) {
                    AudioPlayHelper.getHelper().startPlay(itemView.getContext(), audioUri, SessionAudioViewHolder.this);
                }
            }
        });

        VoiceHelper.getHelper(itemView.getContext()).handler(audioUri, new VoiceHelper.VoiceListener() {
            @Override
            public void callback(String uri, long duration) {
                if (!TextUtils.isEmpty(uri) && uri.equals(audioUri)) {
                    duration = duration / 1000;
                    if (duration <= 0) {
                        duration = 1;
                    }

                    int screenWidth = TUIUtils.getDisplayWidth(itemView.getContext());

                    int length = 0;
                    int minWidth = screenWidth / 6;
                    int width = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.ti_voice_min_width);
                    if(minWidth < width){
                        minWidth = width;
                    }
                    int maxWidth = screenWidth / 2;
                    if(maxWidth < minWidth){
                        maxWidth = minWidth;
                    }
                    int len = maxWidth - minWidth;
                    if (duration > AudioPopupWindow.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND) {
                        length = maxWidth;
                    } else {
                        length = minWidth + new Float(duration * 1.0f / AudioPopupWindow.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND * len).intValue();
                    }

                    tvDuration.setText(itemView.getContext().getString(R.string.ti_voice_time, duration));

                    ViewGroup.LayoutParams params = viewAudio.getLayoutParams();
                    params.width = length;
                    viewAudio.setLayoutParams(params);
                }
            }
        });
    }

    @Override
    public void audio(String url, Boolean isPlay) {
        if (TextUtils.isEmpty(url) || !url.equals(audioUri)) {
            return;
        }

        if (isPlay) {
            if (ivAudio != null && ivAudio.getBackground() instanceof AnimationDrawable) {
                AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                animation.start();
            }
        } else {
            if (ivAudio != null && ivAudio.getBackground() instanceof AnimationDrawable) {
                AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                animation.stop();
                animation.selectDrawable(0);
            }
        }
    }
}
