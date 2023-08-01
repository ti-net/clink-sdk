package com.tinet.oskit.fragment;

import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.FormAdapter;
import com.tinet.oskit.present.ChatLeaveMessagePresent;
import com.tinet.oskit.view.ChatLeaveMessageView;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;
import com.tinet.threepart.tools.TClickUtil;

/**
 * 留言
 *
 * @author: liuzr
 * @date: 2021-12-13
 */
public class ChatLeaveMessageFragment extends TinetFragment implements ChatLeaveMessageView {

    public static final String CHAT_LEAVE_MESSAGE_KEY = "chatLeaveMessage";

    private ChatLeaveMessagePresent present;

    private RecyclerView recyclerView;
    private FormAdapter adapter;

    private LinearLayout viewChatLeave;
    private FrameLayout viewContent;

    private TextView tvChatLeaveMessageTitle, tvChatLeaveMessageSuccess;
    private ViewStub vsSuccess;

    private ChatLeaveMessage message;

    @Override
    protected int layoutId() {
        return R.layout.frg_chat_leave_message;
    }

    @Override
    protected void initView() {
        super.initView();

        if (getArguments() != null && getArguments().containsKey(CHAT_LEAVE_MESSAGE_KEY)) {
            message = new ChatLeaveMessage(getArguments().getString(CHAT_LEAVE_MESSAGE_KEY));
        }

        if (null == message) {
            requireActivity().finish();
        }

        present = new ChatLeaveMessagePresent(this);
        present.setMessage(message);

        viewContent = requireView().findViewById(R.id.viewContent);

        viewChatLeave = requireView().findViewById(R.id.viewChatLeave);
        vsSuccess = requireView().findViewById(R.id.vsSuccess);
        tvChatLeaveMessageTitle = requireView().findViewById(R.id.tvChatLeaveMessageTitle);
        requireView().findViewById(R.id.btnLeaveMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TClickUtil.isNotFastClick()) {
                    present.commit();
                }
            }
        });
        recyclerView = requireView().findViewById(R.id.recyclerView);

        adapter = new FormAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(message.getFormBean());

        tvChatLeaveMessageTitle.setText(message.getContent());
    }

    @Override
    public void commitSuccess() {
        //留言成功
        viewChatLeave.setVisibility(View.GONE);
        requireActivity().setTitle("");

        if (tvChatLeaveMessageSuccess == null) {
            tvChatLeaveMessageSuccess = vsSuccess.inflate().findViewById(R.id.tvChatLeaveMessageSuccess);
        }

        tvChatLeaveMessageSuccess.setText(message.getLeaveTip());
    }
}
