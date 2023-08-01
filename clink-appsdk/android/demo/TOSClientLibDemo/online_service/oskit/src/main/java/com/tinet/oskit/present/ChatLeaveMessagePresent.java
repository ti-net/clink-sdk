package com.tinet.oskit.present;

import android.text.TextUtils;

import com.tinet.oskit.R;
import com.tinet.oskit.view.ChatLeaveMessageView;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.listener.OnlineSendMessageCallback;
import com.tinet.oslib.model.form.FormBean;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;

import java.util.ArrayList;

/**
 * @author: liuzr
 * @date: 2021-12-15
 */
public class ChatLeaveMessagePresent extends TinetPresent<ChatLeaveMessageView> {

    private ChatLeaveMessage message;

    public ChatLeaveMessagePresent(ChatLeaveMessageView view) {
        super(view);
    }

    public void setMessage(ChatLeaveMessage message) {
        this.message = message;
    }

    /**
     * 提交留言
     */
    public void commit() {
        ArrayList<FormBean> list = message.getFormBean();
        if (null != list && list.size() > 0) {
            for (FormBean bean : list) {
                if (bean.isRequired() && TextUtils.isEmpty(bean.getContent())) {
                    view.showToast(R.string.ti_form_bean_is_not_empty, true, bean.getName());
                    return;
                }
            }
        }

        OnlineServiceClient.sendMessage(OnlineMessage.obtain(ChatLeaveMessage.obtain(list)), new OnlineSendMessageCallback() {
            @Override
            public void onProgress(OnlineMessage message, int progress) {

            }

            @Override
            public void onSuccess(OnlineMessage message) {
                view.commitSuccess();
            }

            @Override
            public void onError(OnlineMessage message, int errorCode, String errorDesc) {
                view.showToast(errorDesc, true);
            }
        });
    }
}
