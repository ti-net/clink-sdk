package com.tinet.oskit.adapter.holder;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oskit.tool.ImageCacheHelper;
import com.tinet.oskit.tool.MediaHelper;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.VideoMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.threepart.tools.TDensityUtil;

import java.io.File;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionVideoViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 14:52
 * @Description:
 */
public class SessionVideoViewHolder extends SessionViewHolder {

    ImageView ivBivPic;

    public SessionVideoViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        ivBivPic = itemView.findViewById(R.id.ivBivPic);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage onlineServiceMessage = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), onlineServiceMessage.getSenderType(), ivBivPic);

        if (onlineServiceMessage instanceof VideoMessage) {
            final VideoMessage chatVideoMessage = (VideoMessage) onlineServiceMessage;
            final String videoUri = chatVideoMessage.getUri();

            String thumnail = itemView.getContext().getCacheDir().getPath() + MediaHelper.COVER_PATH + videoUri.hashCode() + MediaHelper.SUFFIX;
            File thumnailFile = new File(thumnail);
            if (thumnailFile.exists()) {
                TOSClientKit.getImageLoader().loadImage(ivBivPic, thumnailFile, R.drawable.ti_ic_load_default_image,
                        R.drawable.ti_ic_load_default_image);
            } else {
                if (TextUtils.isEmpty(ImageCacheHelper.getHelper(itemView.getContext().getApplicationContext()).getCacheUrl(itemView.getContext().getApplicationContext(), chatVideoMessage.getUriKey()))) {
                    TOSClientKit.getImageLoader().loadImage(ivBivPic,
                            videoUri, TDensityUtil.dip2px(itemView.getContext(), 250), Integer.MIN_VALUE, new TImageLoaderListener() {
                                @Override
                                public void onResourceReady(Drawable drawable) {
                                    ImageCacheHelper.getHelper(itemView.getContext().getApplicationContext()).putCacheUrl(itemView.getContext().getApplicationContext(), chatVideoMessage.getUriKey(), videoUri);
                                }

                                @Override
                                public void onLoadFailed() {

                                }
                            });
                } else {
                    TOSClientKit.getImageLoader().loadImage(ivBivPic,
                            ImageCacheHelper.getHelper(itemView.getContext().getApplicationContext()).getCacheUrl(itemView.getContext().getApplicationContext(), chatVideoMessage.getUriKey()), TDensityUtil.dip2px(itemView.getContext(), 250), Integer.MIN_VALUE, new TImageLoaderListener() {
                                @Override
                                public void onResourceReady(Drawable drawable) {
                                }

                                @Override
                                public void onLoadFailed() {
                                    TOSClientKit.getImageLoader().loadImage(ivBivPic,
                                            videoUri, TDensityUtil.dip2px(itemView.getContext(), 250), Integer.MIN_VALUE, new TImageLoaderListener() {
                                                @Override
                                                public void onResourceReady(Drawable drawable) {
                                                    ImageCacheHelper.getHelper(itemView.getContext().getApplicationContext()).putCacheUrl(itemView.getContext().getApplicationContext(), chatVideoMessage.getUriKey(), videoUri);
                                                }

                                                @Override
                                                public void onLoadFailed() {

                                                }
                                            });
                                }
                            });
                }
            }

            ivBivPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (info.getOnlineContent() instanceof VideoMessage) {
                        VideoMessage videoMessage = (VideoMessage) info.getOnlineContent();
                        listener.videoPlay(videoMessage.getUri());
                    }

                }
            });
        }
    }
}
