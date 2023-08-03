package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;

public class SessionPreSendCardViewHolder extends SessionViewHolder {

    private TextView subTitle, price,tvSend;
    private ImageView img;

    public SessionPreSendCardViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        img = itemView.findViewById(R.id.img);
        subTitle = itemView.findViewById(R.id.subTitle);
        tvSend = itemView.findViewById(R.id.tvSend);
        price = itemView.findViewById(R.id.price);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);
        try {
            OnlineServiceMessage serviceMessage = info.getOnlineContent();
            CardInfo cardInfo = CardInfo.fromJson(new JSONObject(serviceMessage.getContent()));
            if (!TextUtils.isEmpty(cardInfo.getImg())) {
                img.setVisibility(View.VISIBLE);
                TOSClientKit.getImageLoader().loadImage(img, cardInfo.getImg(), R.drawable.ti_ic_load_default_image,
                        R.drawable.ti_ic_load_default_image);
            } else
                img.setVisibility(View.GONE);
            subTitle.setText(cardInfo.getSubTitle());
            price.setText(cardInfo.getPrice());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        listener.onCardMessageClickSendOut(info);
                    }
                }
            });
            tvSend.setText(TextUtils.isEmpty(cardInfo.getButtonText())?itemView.getResources().getText(R.string.ti_send_product):cardInfo.getButtonText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}