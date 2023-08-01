package com.tinet.oskit.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oskit.widget.web.TinetWebView;
import com.tinet.oskit.widget.web.WebViewListener;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

import java.util.ArrayList;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionRobotHtmlViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-01 17:34
 * @Description:
 */
public class SessionRobotHtmlViewHolder extends SessionViewHolder {

    private TinetWebView wvName;

    public SessionRobotHtmlViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        wvName = itemView.findViewById(R.id.wvName);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), wvName);

        wvName.setListener(new WebViewListener() {
            @Override
            public void onLinkClick(String url) {
                if (null != listener) {
                    listener.onLinkClick(url);
                }
            }

            @Override
            public void viewImage(String str) {
                if (null != listener) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(str);
                    listener.onImageMessageClick(list, 0);
                }
            }

            @Override
            public void onVideoPlay(String url) {
                if (null != listener) {
                    listener.videoPlay(url);
                }
            }
        });
        wvName.loadData(message.getContent());
    }
}
