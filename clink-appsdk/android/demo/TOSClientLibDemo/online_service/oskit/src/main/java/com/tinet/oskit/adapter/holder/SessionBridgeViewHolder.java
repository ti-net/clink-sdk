package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.SessionHtmlAdapter;
import com.tinet.oskit.adapter.decoration.HorizontalItemDecoration;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatBridgeMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.spanhtml.JsoupUtil;
import com.tinet.spanhtml.bean.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionBridgViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-28 09:10
 * @Description: 欢迎语
 */
public class SessionBridgeViewHolder extends SessionViewHolder {

    private RecyclerView wvText, wvIntroduceText;

    private HorizontalItemDecoration itemDecoration;

    private SessionHtmlAdapter adapter;
    private SessionHtmlAdapter adapterWelcome;

    public SessionBridgeViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        itemDecoration = new HorizontalItemDecoration(itemView.getContext().getResources().getDimensionPixelSize(R.dimen.ti_msg_html_divider));

        wvText = itemView.findViewById(R.id.wvText);
        wvIntroduceText = itemView.findViewById(R.id.wvIntroduceText);
        wvText.addItemDecoration(itemDecoration);
        wvIntroduceText.addItemDecoration(itemDecoration);

        adapter = new SessionHtmlAdapter(listener);
        wvIntroduceText.setAdapter(adapter);

        adapterWelcome = new SessionHtmlAdapter(listener);
        wvText.setAdapter(adapterWelcome);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);

        OnlineServiceMessage message = info.getOnlineContent();
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), wvIntroduceText);
        ModifyUiUtils.modifyBubble(itemView.getContext(), message.getSenderType(), wvText);

        if (message instanceof ChatBridgeMessage) {
            ChatBridgeMessage bridgeMessage = (ChatBridgeMessage) message;
            List<String> welcomes = bridgeMessage.getWelcome();
            wvText.setVisibility(View.GONE);
            if (welcomes != null && welcomes.size() > 0 && bridgeMessage.isShowWelcome()) {
                ArrayList<Html> list = new ArrayList<>();
                for (String welcome : welcomes) {
                    list.addAll(JsoupUtil.parseHtml(welcome));
                }

                adapterWelcome.setData(list);

                if (list.size() == 0) {
                    wvText.setVisibility(View.GONE);
                } else {
                    wvText.setVisibility(View.VISIBLE);
                }
            }

            wvIntroduceText.setVisibility(View.GONE);
            if (null != wvIntroduceText && !bridgeMessage.isShowWelcome()) {
                if (TextUtils.isEmpty(bridgeMessage.getClientIntroduce())) {
                    wvIntroduceText.setVisibility(View.GONE);
                } else {
                    wvIntroduceText.setVisibility(View.VISIBLE);
                }

                adapter.setData(JsoupUtil.parseHtml(bridgeMessage.getClientIntroduce()));
            }
        }
    }
}
