package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.SessioncardAdapter;
import com.tinet.oskit.adapter.SessionCarddarkAdapter;
import com.tinet.oskit.adapter.TinetAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.common.OnlineMessageSenderType;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.timclientlib.utils.TStringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class SessioncardViewHolder extends SessionViewHolder {

    public interface SessionCardListener{

        void onClick(OnlineMessage info);

    }

    private RecyclerView recyclerView;
    private TinetAdapter adapter;
    private ImageView img, open;
    private TextView order_number, time, subTitle, description, price, status, more;
    private View layout;
    private View lineCardTop, lineCardBottom;

    private SessionCardListener mSessionCardListener;

    public SessioncardViewHolder(@NonNull View itemView, SessionClickListener listener,SessionCardListener mSessionCardListener) {
        super(itemView, listener);
        img = itemView.findViewById(R.id.img);
        open = itemView.findViewById(R.id.open);
        order_number = itemView.findViewById(R.id.order_number);
        time = itemView.findViewById(R.id.time);
        subTitle = itemView.findViewById(R.id.subTitle);
        description = itemView.findViewById(R.id.description);
        price = itemView.findViewById(R.id.price);
        status = itemView.findViewById(R.id.status);
        more = itemView.findViewById(R.id.more);
        recyclerView = itemView.findViewById(R.id.recyclerView);
        layout = itemView.findViewById(R.id.layout);
        lineCardTop = itemView.findViewById(R.id.view_line_card_top);
        lineCardBottom = itemView.findViewById(R.id.view_line_card_bottom);
        this.mSessionCardListener = mSessionCardListener;
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        boolean showMore = false;
        Object obj = itemView.getTag();
        if(obj == null){
            showMore = false;
        }else{
            try {
                showMore = (Boolean) obj;
            }catch (Exception e){}
        }

        try {
            final OnlineServiceMessage serviceMessage = info.getOnlineContent();
            ModifyUiUtils.modifyBubble(itemView.getContext(), serviceMessage.getSenderType(), layout);

            if (serviceMessage.getSenderType() == OnlineMessageSenderType.VISITOR)
                adapter = new SessioncardAdapter(listener);
            else adapter = new SessionCarddarkAdapter(listener);
            recyclerView.setAdapter(adapter);
            CardInfo cardInfo = CardInfo.fromJson(new JSONObject(serviceMessage.getContent()));
            if (!TextUtils.isEmpty(cardInfo.getImg())) {
                img.setVisibility(View.VISIBLE);
                TOSClientKit.getImageLoader().loadImage(img, cardInfo.getImg(), R.drawable.ti_ic_load_default_image, R.drawable.ti_ic_load_default_image);
            } else img.setVisibility(View.GONE);

            time.setText(cardInfo.getTime());
            subTitle.setText(cardInfo.getSubTitle());
            description.setText(cardInfo.getDescription());
            price.setText(cardInfo.getPrice());
            status.setText("到货状态：" + cardInfo.getStatus());

            final List<String> message = new ArrayList<>();
            if (TStringUtils.isNotEmpty(cardInfo.getExtraInfo())) {
                JSONArray jsonArray = new JSONArray(cardInfo.getExtraInfo());
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        if ("订单号".equals(jsonObject.getString("name")))
                            order_number.setText("订单号：" + jsonObject.optString("value"));
                        else
                            message.add(jsonObject.optString("name") + "：" + jsonObject.optString("value"));
                    }
                    if (message.size() != 0) {
                        if (message.size() > 3) {
                            if(showMore){
                                adapter.setData(message);
                                more.setText("收起");
                            }else {
                                adapter.setData(firstThreeArticles(message));
                                more.setText("展开");
                            }
//                        if (serviceMessage.getSenderType() == OnlineMessageSenderType.VISITOR)
//                            open.setImageResource(R.mipmap.down);
//                        else
                            open.setImageResource(R.mipmap.darkdown);
                            open.setVisibility(View.VISIBLE);
                        } else {
                            more.setText("");
                            adapter.setData(message);
                            open.setVisibility(View.GONE);
                        }
                    }
                } else {
                    more.setText("");
                    order_number.setText("");
                    open.setVisibility(View.GONE);
                }
            } else {
                more.setText("");
                order_number.setText("");
                open.setVisibility(View.GONE);
            }

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("展开".equals(more.getText())) {
                        adapter.setData(message);
                        itemView.setTag(true);

                        more.setText("收起");
//                        if (serviceMessage.getSenderType() == OnlineMessageSenderType.VISITOR)
//                            open.setImageResource(R.mipmap.up);
//                        else
                        open.setImageResource(R.mipmap.darkup);
                    } else if ("收起".equals(more.getText())) {
                        itemView.setTag(false);
                        adapter.setData(firstThreeArticles(message));
                        more.setText("展开");
//                        if (serviceMessage.getSenderType() == OnlineMessageSenderType.VISITOR)
//                            open.setImageResource(R.mipmap.down);
//                        else
                        open.setImageResource(R.mipmap.darkdown);
                    }

                    if(null != mSessionCardListener){
                        mSessionCardListener.onClick(info);
                    }
                }
            });
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        listener.onCardMessageClick(itemView, info);
                    }
                }
            });

            checkEnableView(cardInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 未填参数显示隐藏处理
     *
     * @param cardInfo
     */
    private void checkEnableView(CardInfo cardInfo) {
        order_number.setVisibility(order_number.getText().length() == 0 ? View.GONE : View.VISIBLE);
        time.setVisibility(TStringUtils.isEmpty(cardInfo.getTime()) ? View.GONE : View.VISIBLE);
        description.setVisibility(TStringUtils.isEmpty(cardInfo.getDescription()) ? View.GONE : View.VISIBLE);
        price.setVisibility(TStringUtils.isEmpty(cardInfo.getPrice()) ? View.GONE : View.VISIBLE);
        status.setVisibility(TStringUtils.isEmpty(cardInfo.getStatus()) ? View.GONE : View.VISIBLE);
        more.setVisibility(more.getText().length() == 0 ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(TStringUtils.isEmpty(cardInfo.getExtraInfo()) ? View.GONE : View.VISIBLE);
        lineCardTop.setVisibility(order_number.getVisibility() == View.GONE && time.getVisibility() == View.GONE ? View.GONE : View.VISIBLE);
        lineCardBottom.setVisibility(status.getVisibility() == View.GONE && more.getVisibility() == View.GONE ? View.GONE : View.VISIBLE);
    }

    private List<String> firstThreeArticles(List<String> messages) {
        List<String> message = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            message.add(messages.get(i));
        }
        return message;
    }

}
