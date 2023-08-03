package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatMiniProgramCardMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 13:28
 * @Description:
 */
public class SessionMiniProgramCardViewHolder extends SessionViewHolder {

    private final View llMiniProgramCard;
    private ImageView ivMiniLogo, ivMiniPicUrl;
    private TextView tvMiniName, tvMiniTitle;

    public SessionMiniProgramCardViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        ivMiniLogo = itemView.findViewById(R.id.ivMiniLogo);
        ivMiniPicUrl = itemView.findViewById(R.id.ivMiniPicUrl);
        tvMiniName = itemView.findViewById(R.id.tvMiniName);
        tvMiniTitle = itemView.findViewById(R.id.tvMiniTitle);
        llMiniProgramCard = itemView.findViewById(R.id.llMiniProgramCard);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        final OnlineServiceMessage serviceMessage = info.getOnlineContent();
        if (serviceMessage instanceof ChatMiniProgramCardMessage) {
            final ChatMiniProgramCardMessage miniProgramCardMessage = (ChatMiniProgramCardMessage) serviceMessage;
            TOSClientKit.getImageLoader().loadImage(ivMiniLogo,
                    miniProgramCardMessage.getAppLogo(),
                    R.drawable.ti_ic_load_default_image,
                    R.drawable.ti_ic_load_default_image);
            TOSClientKit.getImageLoader().loadImage(ivMiniPicUrl,
                    miniProgramCardMessage.getPicUrl(),
                    R.drawable.ti_ic_load_default_image,
                    R.drawable.ti_ic_load_default_image);
            tvMiniName.setText(miniProgramCardMessage.getAppName());
            tvMiniTitle.setText(miniProgramCardMessage.getTitle());

            llMiniProgramCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onMiniProgramCardClick(miniProgramCardMessage);
                    }
                }
            });
        }
    }
}
