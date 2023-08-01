package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.timclientlib.utils.TNtpUtils;
import com.tinet.threepart.tools.TClickUtil;
import com.tinet.timclientlib.common.constans.TMessageDirection;
import com.tinet.timclientlib.common.constans.TMessageType;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionRevokeViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 10:39
 * @Description: 撤消消息
 */
public class SessionRevokeViewHolder extends SessionViewHolder {

    /**
     * 撤消消息重编辑时间限制
     * */
    private static final long REVOKE_MESSAGE_RE_EDIT_TIME_LIMIT = 24 * 60 * 60 * 1000;

    /**
     * 显示撤消
     * */
    private TextView tvNotification;
    /**
     * 重新编辑
     * */
    private TextView tvReEdit;

    public SessionRevokeViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        tvNotification = itemView.findViewById(R.id.tvNotification);
        tvReEdit = itemView.findViewById(R.id.tvReEdit);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        if (info.getMessageDirection() == TMessageDirection.SEND) {
            tvNotification.setText(itemView.getContext().getString(R.string.ti_revoke_message,itemView.getContext().getString(R.string.ti_you)));

            if (TMessageType.TEXT.equals(info.getType())) {
                // content为空的时候会走else,不会走这里，所以这里不需要判空
                // 重新编辑 24小时内
                final long passTime = TNtpUtils.getRealTimeMillis() - info.getSendTime();
                if (passTime < REVOKE_MESSAGE_RE_EDIT_TIME_LIMIT) {
                    tvReEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null && TClickUtil.isNotFastClick()) {
//                                listener.reEditMessage(info);
                            }
                        }
                    });
                    tvReEdit.setVisibility(View.VISIBLE);
                } else {
                    tvReEdit.setOnClickListener(null);
                    tvReEdit.setVisibility(View.GONE);
                }
            } else {
                tvReEdit.setOnClickListener(null);
                tvReEdit.setVisibility(View.GONE);
            }
        } else {
            // 客服名称
            tvReEdit.setOnClickListener(null);
            tvReEdit.setVisibility(View.GONE);
        }
    }
}
