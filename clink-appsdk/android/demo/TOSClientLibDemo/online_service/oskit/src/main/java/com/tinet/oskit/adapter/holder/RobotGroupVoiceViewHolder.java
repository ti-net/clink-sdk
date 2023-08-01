package com.tinet.oskit.adapter.holder;

import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.popup.AudioPopupWindow;
import com.tinet.oskit.tool.AudioPlayHelper;
import com.tinet.oskit.tool.VoiceHelper;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.threepart.tools.TUIUtils;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-09 16:18
 * @Description: 机器人组合消息 -- 音频
 */
public class RobotGroupVoiceViewHolder extends RobotGroupBaseViewHolder implements AudioPlayHelper.AudioPlayListener {

    private TextView tvDuration;
    private ImageView ivAudio;

    private LinearLayout viewAudio;

    private String audioUrl = "";

    public RobotGroupVoiceViewHolder(@NonNull View itemView, OnlineMessage message, SessionClickListener listener) {
        super(itemView, message, listener);
        tvDuration = itemView.findViewById(R.id.tvDuration);
        ivAudio = itemView.findViewById(R.id.ivAudio);

        viewAudio = itemView.findViewById(R.id.viewAudio);
    }

    @Override
    public void update(OnlineQuestion info) {
        super.update(info);

        audioUrl = info.getUri(message.getOnlineContent());

        VoiceHelper.getHelper(itemView.getContext()).handler(audioUrl, new VoiceHelper.VoiceListener() {
            @Override
            public void callback(String uri, long duration) {
                if (!TextUtils.isEmpty(uri) && uri.equals(audioUrl)) {
                    duration = duration / 1000;
                    if (duration <= 0) {
                        duration = 1;
                    }

                    int screenWidth = TUIUtils.getDisplayWidth(itemView.getContext());

                    int length = 0;
                    int minWidth = screenWidth / 6;
                    int maxWidth = screenWidth / 4;
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

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlayHelper.getHelper().startPlay(itemView.getContext(), audioUrl, RobotGroupVoiceViewHolder.this);
            }
        });
    }

    @Override
    public void audio(String url, Boolean isPlay) {
        if (TextUtils.isEmpty(url) || !url.equals(audioUrl)) {
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
