package com.tinet.oskit.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.tinet.oskit.adapter.holder.CustomerDefineViewHolder;
import com.tinet.oskit.adapter.holder.RobotQuickReplyViewHolder;
import com.tinet.oskit.adapter.holder.SessionBridgeViewHolder;
import com.tinet.oskit.adapter.holder.SessionInvestigationViewHolder;
import com.tinet.oskit.adapter.holder.SessionChatLeadingWordsViewHolder;
import com.tinet.oskit.adapter.holder.SessionChatLeaveViewHolder;
import com.tinet.oskit.adapter.holder.SessionFileViewHolder;
import com.tinet.oskit.adapter.holder.SessionHtmlViewHolder;
import com.tinet.oskit.adapter.holder.SessionKnowledgeCommonViewHolder;
import com.tinet.oskit.adapter.holder.SessionLogisticsCardViewHolder;
import com.tinet.oskit.adapter.holder.SessionMiniProgramCardViewHolder;
import com.tinet.oskit.adapter.holder.SessionNoShowViewHolder;
import com.tinet.oskit.adapter.holder.SessionNotifyViewHolder;
import com.tinet.oskit.adapter.holder.SessionQueueViewHolder;
import com.tinet.oskit.adapter.holder.SessionRobotBridgeViewHolder;
import com.tinet.oskit.adapter.holder.SessionRobotGroupViewHolder;
import com.tinet.oskit.adapter.holder.SessionRobotHtmlViewHolder;
import com.tinet.oskit.adapter.holder.SessionPreSendCardViewHolder;
import com.tinet.oskit.adapter.holder.SessionUnknownViewHolder;
import com.tinet.oskit.adapter.holder.SessioncardViewHolder;
import com.tinet.oskit.adapter.holder.SessioncardViewHolder.SessionCardListener;
import com.tinet.oskit.manager.TUiManager;
import com.tinet.oskit.manager.TUiManager.UIConfig;
import com.tinet.oskit.tool.TCommonUtils;
import com.tinet.oslib.common.OnlineEvent;
import com.tinet.oslib.common.OnlineMessageSenderType;
import com.tinet.oslib.common.OnlineMessageStatus;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.ImageMessage;
import com.tinet.oslib.model.message.content.ChatLocationMessage;
import com.tinet.oslib.model.message.content.ChatQueueMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.UnknownMessage;
import com.tinet.timclientlib.utils.TStringUtils;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.SessionAudioViewHolder;
import com.tinet.oskit.adapter.holder.SessionImageViewHolder;
import com.tinet.oskit.adapter.holder.SessionRevokeViewHolder;
import com.tinet.oskit.adapter.holder.SessionTextViewHolder;
import com.tinet.oskit.adapter.holder.SessionVideoViewHolder;
import com.tinet.oskit.adapter.holder.SessionViewHolder;
import com.tinet.oskit.listener.SessionClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-20 14:22
 * @Description: 会话适配器基类
 */
public class SessionAdapter extends TinetAdapter<OnlineMessage, SessionViewHolder> {

    public interface OnImageClickListener {

        void onImageClick(OnlineMessage info);

    }

    /**
     * 当前会话的排队信息
     * 有排队消息，代表在排队中
     */
    private OnlineMessage chatQueue;

    /**
     * 记录满意度已选结果数据
     */
    private Map<String, Object> checkedRecordByInvestigation = new HashMap<>();

    /**
     * 列表添加消息入口
     * @param message
     */
    private void addMessage(OnlineMessage message){
        super.addFirstItem(message);
    }

    @Override
    public void addFirstItem(OnlineMessage model) {
        if (OnlineEvent.CHAT_QUEUE.equals(model.getEvent())) {
            //排队消息
            chatQueue = model;
            super.addFirstItem(model);
        } else if (OnlineEvent.CHAT_LOCATION.equals(model.getEvent())) {
            //排队播报信息
            if (null != chatQueue) {
                OnlineServiceMessage content = chatQueue.getOnlineContent();
                if (content instanceof ChatQueueMessage) {
                    ChatQueueMessage queueMessage = (ChatQueueMessage) content;
                    queueMessage.setLocation(((ChatLocationMessage) model.getOnlineContent()).getLocation());
                }
            }

            if (null != chatQueue) {
                removeItem(chatQueue);
                addFirstItem(chatQueue);
            }

            addMessage(model);
        }else{
            int type = getItemViewType(model);
            if(type != R.layout.frg_session_no_show){
                addMessage(model);
            }
        }
    }

    /**
     * 撤回一条消息
     *
     * @param messageId 被撤回消息的消息ID
     */
    public void withdraw(String messageId) {
        OnlineMessage message = getMessageById(messageId);
        if (null != message) {
            message.getOnlineContent().setSendStatus(OnlineMessageStatus.REVOKE_MESSAGE);
            updateItem(message);
        }
    }

    /**
     * 根据消息ID获取消息
     *
     * @param messageUniqueId 消息ID
     */
    private OnlineMessage getMessageById(String messageUniqueId) {
        if (TextUtils.isEmpty(messageUniqueId)) {
            return null;
        }

        for (OnlineMessage message : getData()) {
            OnlineServiceMessage serviceMessage = message.getOnlineContent();
            if (messageUniqueId.equals(serviceMessage.getMessageUniqueId())) {
                return message;
            }
        }

        return null;
    }

    @Override
    public void setData(List<OnlineMessage> data) {
        super.setData(data);

        //排队信息先来，历史数据后来，会造成排队信息被盖
        if(chatQueue != null && null != data && data.size()>0){
            OnlineMessage lastMessage = data.get(0);
            Long sendTime = lastMessage.getSendTime();
            Long queueTime = chatQueue.getSendTime();

            if(sendTime == null || queueTime == null){
                return;
            }

            if(queueTime > sendTime){
                addFirstItem(chatQueue);
            }
        }
    }

    private SessionClickListener listener;

    public SessionAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    /**
     * 用户高度集成时，可继承此类实现viewHolder
     */
    @CallSuper
    @Override
    protected final SessionViewHolder viewHolder(View itemView, int viewType) {
        SessionViewHolder viewHolder = null;
        if (viewType == R.layout.frg_session_text_send || viewType == R.layout.frg_session_text_receive) {
            //文本接收、发送
            viewHolder = new SessionTextViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_image_send || viewType == R.layout.frg_session_image_receive) {
            //图片接收、发送
            viewHolder = new SessionImageViewHolder(itemView, listener, new OnImageClickListener() {
                @Override
                public void onImageClick(OnlineMessage info) {
                    int index = -1;
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = getItemCount() - 1; i >= 0; i--) {
                        OnlineMessage message = getItem(i);
                        if (null != message.getOnlineContent().getMessageType() && OnlineMessageType.IMAGE == message.getOnlineContent().getMessageType()) {
                            ImageMessage imageMessage = (ImageMessage) message.getOnlineContent();
                            list.add(imageMessage.getUri());

                            if (info == message) {
                                index = list.size() - 1;
                            }
                        }
                    }

                    if (null != listener) {
                        listener.onImageMessageClick(list, index);
                    }
                }
            });
        } else if (viewType == R.layout.frg_session_video_send || viewType == R.layout.frg_session_video_receive) {
            //视频接收、发送
            viewHolder = new SessionVideoViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_voice_send || viewType == R.layout.frg_session_voice_receive) {
            //音频接收、发送
            viewHolder = new SessionAudioViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_file_receive || viewType == R.layout.frg_session_file_send) {
            //文件接收、发送
            viewHolder = new SessionFileViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_card_receive || viewType == R.layout.frg_session_card_send) {
            //商品卡片接收、发送
            viewHolder = new SessioncardViewHolder(itemView, listener, new SessionCardListener() {
                @Override
                public void onClick(OnlineMessage info) {
                    notifyItemChanged(info);
                }
            });
        } else if (viewType == R.layout.frg_session_logistics_card_receive) {
            //物流卡片接收
            viewHolder = new SessionLogisticsCardViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_revoke) {
            //撤消的消息
            viewHolder = new SessionRevokeViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_bridge) {
            //欢迎消息
            viewHolder = new SessionBridgeViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_chat_leave) {
            //留言
            viewHolder = new SessionChatLeaveViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_robot_bridge) {
            viewHolder = new SessionRobotBridgeViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_robot_group) {
            //机器人组合消息
            viewHolder = new SessionRobotGroupViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_notify) {
            //系统通知提示
            viewHolder = new SessionNotifyViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_robot_html) {
            //机器人富文本消息
            viewHolder = new SessionRobotHtmlViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_queue) {
            //进入排队提示
            viewHolder = new SessionQueueViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_html) {
            //机器人富文本消息
            viewHolder = new SessionHtmlViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_chat_leading_words) {
            //引导语提示
            viewHolder = new SessionChatLeadingWordsViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_sendout_card_send) {
            //卡片一样式
            viewHolder = new SessionPreSendCardViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_chat_investigation) {
            //满意度
            viewHolder = new SessionInvestigationViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_robot_quick_reply) {
            //机器人快捷回复
            viewHolder = new RobotQuickReplyViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_mini_program_card) {
            //小程序卡片
            viewHolder = new SessionMiniProgramCardViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_knowledge_common) {
            //机器人富文本消息
            viewHolder = new SessionKnowledgeCommonViewHolder(itemView, listener);
        } else if(viewType == R.layout.frg_session_unknown){
            viewHolder = new SessionUnknownViewHolder(itemView,listener);
        }else {
            UIConfig config = TUiManager.getUiConfigByLayoutId(viewType);
            if(null != config){
                return new CustomerDefineViewHolder(itemView,listener,config.getProvider());
            }else {
                viewHolder = new SessionNoShowViewHolder(itemView, listener);
            }
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        OnlineMessage info = getItem(position);
        OnlineMessage preInfo = position == getItemCount() - 1 ? null : getItem(position + 1);

        if (holder instanceof SessionQueueViewHolder) {
            SessionQueueViewHolder viewHolder = (SessionQueueViewHolder) holder;
            viewHolder.update(info, null != chatQueue);
        } else if (holder instanceof SessionInvestigationViewHolder) {
            SessionInvestigationViewHolder viewHolder = (SessionInvestigationViewHolder) holder;
            viewHolder.setCurrentCheckedRecord(checkedRecordByInvestigation);
            viewHolder.update(info, preInfo);
        } else {
            holder.update(info, preInfo);
        }
    }


    /**
     * 更新某一条记录
     *
     * @return true  更新成功
     * false 更新失败，当前列表中没有这条数据，则认为是新消息，在第一位中加入
     */
    public boolean updateItem(OnlineMessage OnlineMessage) {
        int index = -1;
        for (int i = 0; i < getItemCount(); i++) {
            OnlineMessage message = getItem(i);
            if (TStringUtils.isNotEmpty(OnlineMessage.getMessageUUID())
                    && message.getMessageUUID() != null
                    && message.getMessageUUID().equals(OnlineMessage.getMessageUUID())) {
                index = i;
                break;
            }
        }

        if (index >= 0 && index < getItemCount()) {
            setItem(index, OnlineMessage);
            return true;
        } else {
            addFirstItem(OnlineMessage);
            return false;
        }
    }

    public void updateMessageStatusByMessageUUID(String messageUUID, int status) {
        int index = -1;
        for (int i = 0; i < getItemCount(); i++) {
            OnlineMessage message = getItem(i);
            if (message.getMessageUUID() != null
                    && message.getMessageUUID().equals(messageUUID)) {
                index = i;
                break;
            }
        }

        if (index >= 0 && index < getItemCount()) {
            OnlineMessage message = getItem(index);
            message.setStatus(status);
            setItem(index, message);
        }
    }

    /**
     * 删除消息
     */
    public void deleteMessage(OnlineMessage message) {
        for (int i = 0; i < getItemCount(); i++) {
            OnlineMessage msg = getItem(i);
            if (msg != null && TStringUtils.isNotEmpty(msg.getMessageUUID()) && message != null && TStringUtils.isNotEmpty(message.getMessageUUID()) && msg.getMessageUUID().equals(message.getMessageUUID())) {
                removeItem(i);
                break;
            }
        }
    }

    private int getItemViewType(OnlineMessage msg){
        OnlineServiceMessage message = msg.getOnlineContent();
        if ((null != message.getSendStatus() && message.getSendStatus() == OnlineMessageStatus.REVOKE_MESSAGE) ||
            OnlineEvent.CHAT_CLOSE.equals(msg.getEvent()) || OnlineEvent.CHAT_INVESTIGATION.equals(msg.getEvent())) {
            if (OnlineEvent.CHAT_INVESTIGATION.equals(msg.getEvent())) {
                ChatInvestigationMessage chatInvestigationMessage = (ChatInvestigationMessage) message;
                if (chatInvestigationMessage.flag)
                    return R.layout.frg_session_notify;
            } else
                return R.layout.frg_session_notify;
        }

        if(message instanceof UnknownMessage){
            return R.layout.frg_session_unknown;
        }

        if(TUiManager.hasUiConfig(msg.getEvent())){
            return TUiManager.getUIConfig(msg.getEvent()).getLayoutId();
        }else {
            if (OnlineEvent.CHAT_BRIDGE.equals(msg.getEvent())) {
                //接通座席事件
                return R.layout.frg_session_bridge;
            } else if (OnlineEvent.CHAT_MESSAGE.equals(msg.getEvent())) {
                return handleOnlineTextMessage(msg);
            } else if (OnlineEvent.CHAT_QUEUE.equals(msg.getEvent())) {
                return R.layout.frg_session_queue;
            } else if (OnlineEvent.CHAT_LEAVE_MESSAGE.equals(msg.getEvent())) {
                //留言
                return R.layout.frg_session_chat_leave;
            } else if (OnlineEvent.CHAT_INVESTIGATION.equals(msg.getEvent())) {
                //满意度
//            return R.layout.frg_session_text_receive;
                return R.layout.frg_session_chat_investigation;
            } else if (OnlineEvent.CHAT_LEADING_WORDS.equals(msg.getEvent())) {
                //引导语
                return R.layout.frg_session_chat_leading_words;
            }
        }

        return R.layout.frg_session_no_show;
    }

    @CallSuper
    @Override
    public final int getItemViewType(int position) {
        OnlineMessage msg = getItem(position);
        return getItemViewType(msg);
    }

    /**
     * 处理文本消息
     */
    private int handleOnlineTextMessage(OnlineMessage msg) {
        int type = R.layout.frg_session_text_receive;

        OnlineServiceMessage message = msg.getOnlineContent();

        Integer messageType = msg.getOnlineContent().getMessageType();
        if (message.getSenderType() == OnlineMessageSenderType.VISITOR) {
            type = R.layout.frg_session_text_send;

            if (null != messageType) {
                switch (messageType) {
                    case OnlineMessageType.IMAGE:
                        type = R.layout.frg_session_image_send;
                        break;
                    case OnlineMessageType.VOICE:
                        type = R.layout.frg_session_voice_send;
                        break;
                    case OnlineMessageType.VIDEO:
                        type = R.layout.frg_session_video_send;
                        break;
                    case OnlineMessageType.FILE:
                        type = R.layout.frg_session_file_send;
                        break;
                    case OnlineMessageType.CHAT_LEAVE_RECEIVE_MESSAGE:
                        type = R.layout.frg_session_text_send;
                        break;
                    case OnlineMessageType.CARD:
                        type = R.layout.frg_session_card_send;
                        break;
                    case OnlineMessageType.SENDOUT_CARD:
                        type = R.layout.frg_session_sendout_card_send;
                        break;
                    default:
                        type = R.layout.frg_session_text_send;
                        break;
                }
            }
        } else {
            if (null != messageType) {
                switch (messageType) {
                    case OnlineMessageType.ROBOT_GROUP:
                        type = R.layout.frg_session_robot_group;
                        break;
                    case OnlineMessageType.TEXT:
                        //文本消息，如果是系统消息或通知，则灰色居中展示
                        if (OnlineMessageSenderType.SYSTEM == msg.getOnlineContent().getSenderType() ||
                                OnlineMessageSenderType.NOTIFY == msg.getOnlineContent().getSenderType()) {
                            type = R.layout.frg_session_notify;
                        } else {
                            type = R.layout.frg_session_text_receive;
                        }
                        break;
                    case OnlineMessageType.CHAT_LEAVE_RECEIVE_MESSAGE:
                        type = R.layout.frg_session_text_receive;
                        break;
                    case OnlineMessageType.IMAGE:
                        type = R.layout.frg_session_image_receive;
                        break;
                    case OnlineMessageType.VOICE:
                        type = R.layout.frg_session_voice_receive;
                        break;
                    case OnlineMessageType.VIDEO:
                        type = R.layout.frg_session_video_receive;
                        break;
                    case OnlineMessageType.FILE:
                        type = R.layout.frg_session_file_receive;
                        break;
                    case OnlineMessageType.ROBOT_HTML:
                        //机器人富文本消息，机器人富文本消息有可能是单纯的文本消息，需要判断是否包含了富文本信息
//                        if (!HtmlHelper.hasHTMLTags(message.getContent())) {
//                            type = R.layout.frg_session_text_receive;
//                        } else {
//                            type = R.layout.frg_session_robot_html;
//                        }

                        //机器人富文本消息只有可能是接收的数据
                        type = R.layout.frg_session_receive_html;

                        break;
                    case OnlineMessageType.KNOWLEDGE_FILE:
                        type = R.layout.frg_session_file_receive;
                        break;
                    case OnlineMessageType.CARD:
                        // : 2022/12/30 需要判断物流卡片还是商品卡片
                        if (TCommonUtils.isLogisticsCardMessage(message.getContent())) {
                            type = R.layout.frg_session_logistics_card_receive;
                        } else {
                            type = R.layout.frg_session_card_receive;
                        }
                        break;
                    case OnlineMessageType.INVESTIGATION:
                        type = R.layout.frg_session_chat_investigation;
                        break;
                    case OnlineMessageType.ROBOT_QUICK_REPLY:
                        type = R.layout.frg_session_robot_quick_reply;
                        break;
                    case OnlineMessageType.MINI_PROGRAM_CARD:
                        type = R.layout.frg_session_receive_mini_program_card;
                        break;
                    case OnlineMessageType.KNOWLEDGE_COMMON:
                        type = R.layout.frg_session_receive_knowledge_common;
                        break;
                }
            }
        }

        return type;
    }

    /**
     * 点击图片消息处理
     */
    private class ImageMessageClickListener implements View.OnClickListener {

        /**
         * 当前索引
         */
        private int position = 0;

        public ImageMessageClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            int index = position;
            ArrayList<String> list = new ArrayList<>();
            for (int i = getItemCount() - 1; i >= 0; i--) {
                OnlineMessage message = getItem(i);
                if (null != message.getOnlineContent().getMessageType() && OnlineMessageType.IMAGE == message.getOnlineContent().getMessageType()) {
                    ImageMessage imageMessage = (ImageMessage) message.getOnlineContent();
                    list.add(imageMessage.getUri());
                }

                if (i == position) {
                    index = list.size() - 1;
                }
            }

            if (null != listener) {
                listener.onImageMessageClick(list, index);
            }

        }
    }


}
