package com.tinet.oskit.adapter.holder;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.LinkMovementClickMethod;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatQueueMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionQueueViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-02 17:01
 * @Description: 排队
 */
public class SessionQueueViewHolder extends SessionViewHolder {
    /**
     * 显示文本消息
     * */
    protected TextView tvNotification;

    public SessionQueueViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        tvNotification = itemView.findViewById(R.id.tvNotification);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);
    }

    public void update(final OnlineMessage info,Boolean isInQueue){
        OnlineServiceMessage message = info.getOnlineContent();

        if(message instanceof ChatQueueMessage){
            //排队消息，放弃排队
            final ChatQueueMessage queueMessage = (ChatQueueMessage)message;
            if(isInQueue && !queueMessage.isLeaveQueue()) {
                if(queueMessage.getAbandonEnabled()){
                    String str = queueMessage.getLocation() + itemView.getContext().getString(R.string.ti_abandon_queue);
                    SpannableString ss = new SpannableString(str);
                    ss.setSpan(new ClickableSpan() {
                                   @Override
                                   public void onClick(@NonNull View widget) {
                                       if (null != listener) {
                                           listener.cancelQueue();
                                       }

                                       queueMessage.setLeaveQueue(true);

                                       update(info,false);
                                   }

                                   @Override
                                   public void updateDrawState(@NonNull TextPaint ds) {
                                       ds.linkColor = ContextCompat.getColor(itemView.getContext(), R.color.ti_line_up_text_color);
                                       super.updateDrawState(ds);
                                   }
                               },
                        str.length() - 4, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvNotification.setText(ss);
                    tvNotification.setMovementMethod(LinkMovementClickMethod.getInstance());
                }else{
                    tvNotification.setText(queueMessage.getLocation());
                }

            }else{
                tvNotification.setText(queueMessage.getLocation());
            }
        }
    }
}
