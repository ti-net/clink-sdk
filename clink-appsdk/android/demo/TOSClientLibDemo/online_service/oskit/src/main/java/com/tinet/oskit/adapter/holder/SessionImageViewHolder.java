package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.tinet.oskit.adapter.SessionAdapter;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ImageMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 13:28
 * @Description:
 */
public class SessionImageViewHolder extends SessionViewHolder {

    private ImageView ivBivPic;
    private FrameLayout layout;
    private SessionAdapter.OnImageClickListener onImageClickListener;

    public SessionImageViewHolder(@NonNull View itemView, SessionClickListener listener, SessionAdapter.OnImageClickListener onImageClickListener) {
        super(itemView, listener);

        layout = itemView.findViewById(R.id.layout);
        ivBivPic = itemView.findViewById(R.id.ivBivPic);
        this.onImageClickListener = onImageClickListener;
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        final OnlineServiceMessage serviceMessage = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), serviceMessage.getSenderType(), ivBivPic);

        if (serviceMessage instanceof ImageMessage) {
            final ImageMessage chatImageMessage = (ImageMessage) serviceMessage;
            TOSClientKit.getImageLoader().loadImage(ivBivPic,
                    chatImageMessage.getThumbnail(),
                    R.drawable.ti_ic_load_default_image,
                    R.drawable.ti_ic_load_default_image);
            ivBivPic.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onImageClickListener.onImageClick(info);
                }
            });
        }
    }
}
