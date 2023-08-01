package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.MediaHelper;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;

import java.io.File;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-09 16:18
 * @Description: 机器人组合消息 -- 视频
 */
public class RobotGroupVideoViewHolder extends RobotGroupBaseViewHolder {

    private ImageView ivBivPic;

    public RobotGroupVideoViewHolder(@NonNull View itemView, OnlineMessage message, SessionClickListener listener) {
        super(itemView, message, listener);
        ivBivPic = itemView.findViewById(R.id.ivBivPic);
    }

    @Override
    public void update(final OnlineQuestion info) {
        super.update(info);
        final String videoUri = info.getUri(message.getOnlineContent());

        String thumnail = itemView.getContext().getCacheDir().getPath() + MediaHelper.COVER_PATH + videoUri.hashCode() + MediaHelper.SUFFIX;
        File thumnailFile = new File(thumnail);
        if (thumnailFile.exists()) {
            TOSClientKit.getImageLoader().loadImage(ivBivPic, thumnailFile, R.drawable.ti_ic_load_default_image,
                    R.drawable.ti_ic_load_default_image);
        } else {
            MediaHelper.getHelper(itemView.getContext()).handler(videoUri, new MediaHelper.MediaListener() {

                @Override
                public void callback(String uri, String thumnailUri) {
                    if (!TextUtils.isEmpty(uri) && uri.equals(videoUri)) {
                        TOSClientKit.getImageLoader().loadImage(ivBivPic, thumnailUri, R.drawable.ti_ic_load_default_image,
                                R.drawable.ti_ic_load_default_image);
                    }
                }
            });
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.videoPlay(info.getUri(message.getOnlineContent()));
                }
            }
        });
    }
}
