package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.TCommonUtils;
import com.tinet.oslib.model.bean.LogisticsCardInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.timclientlib.utils.TStringUtils;
import com.tinet.timclientlib.utils.TToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class SessionLogisticsCardViewHolder extends SessionViewHolder {

    private ImageView ivCopy;
    private TextView tvCreateTime, tvGoodsName, tvSenderName, tvRecipientName, tvGoodsAmount, tvGoodsQuantity, tvOrderNumber;


    public SessionLogisticsCardViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        tvCreateTime = itemView.findViewById(R.id.tvCreateTime);
        tvGoodsName = itemView.findViewById(R.id.tvGoodsName);
        tvSenderName = itemView.findViewById(R.id.tvSenderName);
        tvRecipientName = itemView.findViewById(R.id.tvRecipientName);
        tvGoodsAmount = itemView.findViewById(R.id.tvGoodsAmount);
        tvGoodsQuantity = itemView.findViewById(R.id.tvGoodsQuantity);
        tvOrderNumber = itemView.findViewById(R.id.tvOrderNumber);
        ivCopy = itemView.findViewById(R.id.ivCopy);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);
        try {
            final OnlineServiceMessage serviceMessage = info.getOnlineContent();
            LogisticsCardInfo logisticsCardInfo = LogisticsCardInfo.fromJson(new JSONObject(serviceMessage.getContent()));
            setContent(tvCreateTime, logisticsCardInfo.getCreateTime());
            setContent(tvGoodsName, logisticsCardInfo.getGoodsName());
            setContent(tvSenderName, logisticsCardInfo.getSenderName());
            setContent(tvRecipientName, logisticsCardInfo.getRecipientName());
            setContent(tvGoodsAmount, logisticsCardInfo.getGoodsAmount());
            setContent(tvGoodsQuantity, logisticsCardInfo.getGoodsQuantity());
            if (TStringUtils.isNotEmpty(logisticsCardInfo.getOrderNumber())) {
                setContent(tvOrderNumber, "订单编号：" + logisticsCardInfo.getOrderNumber());
            }

            ivCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TCommonUtils.copyTextToClipboard(itemView.getContext(), logisticsCardInfo.getOrderNumber())) {
                        TToastUtils.showShortToast(itemView.getContext(), "复制成功");
                    } else {
                        TToastUtils.showShortToast(itemView.getContext(), "复制失败");
                    }
                }
            });

            tvGoodsQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onLogisticsCardButtonClick(logisticsCardInfo);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setContent(TextView textView, String content) {
        if (TStringUtils.isNotEmpty(content)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(content);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    private List<String> firstThreeArticles(List<String> messages) {
        List<String> message = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            message.add(messages.get(i));
        }
        return message;
    }

}
