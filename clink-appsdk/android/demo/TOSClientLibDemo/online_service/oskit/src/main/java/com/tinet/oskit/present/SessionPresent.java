package com.tinet.oskit.present;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.manager.TOSClientKitConfig;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.common.OnlineMessageStatus;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.listener.ChatInfoCallback;
import com.tinet.oslib.listener.InvestigationListener;
import com.tinet.oslib.listener.OnlineConnectStatusListener;
import com.tinet.oslib.listener.OnlineEventListener;
import com.tinet.oslib.listener.OnlineMessageHistoryCallback;
import com.tinet.oslib.listener.OnlineMessageListener;
import com.tinet.oslib.listener.OnlineSendMessageCallback;
import com.tinet.oslib.listener.RequestInvestigationListener;
import com.tinet.oslib.listener.SettingCallback;
import com.tinet.oslib.listener.SubmitInvestigationListener;
import com.tinet.oslib.listener.VisitorReadyListener;
import com.tinet.oslib.manager.InvestigationManager;
import com.tinet.oslib.manager.OnlineManager;
import com.tinet.oslib.manager.OnlineMessageFailureManager;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.tinet.oslib.manager.OnlineQueueManager;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.bean.InvestigationStar;
import com.tinet.oslib.model.bean.OnlineSetting;
import com.tinet.oslib.model.bean.SessionInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.CardMessage;
import com.tinet.oslib.model.message.content.ChatBridgeMessage;
import com.tinet.oslib.model.message.content.ChatCloseMessage;
import com.tinet.oslib.model.message.content.ChatInputHintMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;
import com.tinet.oslib.model.message.content.ChatResponseMessage;
import com.tinet.oslib.model.message.content.FileMessage;
import com.tinet.oslib.model.message.content.ImageMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.TextMessage;
import com.tinet.oslib.model.message.content.VideoMessage;
import com.tinet.oslib.model.message.content.VoiceMessage;
import com.tinet.threepart.tools.TFileUtils;
import com.tinet.threepart.tools.TMediaFile;
import com.tinet.oskit.R;
import com.tinet.oskit.model.DataTemplate;
import com.tinet.oskit.model.Function;
import com.tinet.oskit.view.SessionView;
import com.tinet.threepart.tools.TUIUtils;
import com.tinet.timclientlib.common.constans.TMessageStatus;
import com.tinet.timclientlib.manager.TIMMqttManager;
import com.tinet.timclientlib.utils.TLogUtils;

import com.tinet.timclientlib.utils.TNtpUtils;
import com.tinet.timclientlib.utils.TStringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionFragment
 * @Author: liuzr
 * @CreateDate: 2021-08-23 09:37
 * @Description:
 */
public class SessionPresent extends TinetPresent<SessionView> {

    /**
     * 用于分页加载会话信息
     */
    public Long lastMessageTime = null;

    /**
     * 会话是否打开着
     * 会话关闭的情况下是不允许会话的
     */
    private boolean isSessionOpen = false;
    private OnlineConnectStatusListener onlineConnectStatusListener;

    public boolean isSessionOpen() {
        return isSessionOpen;
    }

    public void setSessionOpen(boolean sessionOpen) {
        isSessionOpen = sessionOpen;

        TLogUtils.d("会话状态："+sessionOpen);

        if (sessionOpen) {
            view.onSessionOpen();
        } else {
            view.onSessionClosed();
        }
    }

    public SessionPresent(SessionView view) {
        super(view);
    }

    /**
     * 加载历史会话
     */
    public void loadHistory() {
        final boolean isRefresh = null == lastMessageTime;
        OnlineServiceClient.getMessageHistory(isRefresh ? null : lastMessageTime, DEFAULT_PAGE_SIZE, new OnlineMessageHistoryCallback() {
            @Override
            public void onSuccess(List<OnlineMessage> data) {
                try {
                    handleSendFailureMessage(data);
                }catch (Exception e){}
                DataTemplate<OnlineMessage> template = new DataTemplate<>(isRefresh, data);
                if (null != data && data.size() > 0) {
                    OnlineMessage message = data.get(data.size() - 1);
                    lastMessageTime = message.getOnlineContent().getCreateTime();
                }

                view.loadHistoryResult(template, true);
            }

            @Override
            public void onError(int errorCode, String errorDesc) {
                view.loadHistoryResult(null, false);
            }
        });
    }

    /**
     * 处理发送失败的消息
     * @param history
     */
    private void handleSendFailureMessage(List<OnlineMessage> history){
        List<OnlineMessage> list = OnlineMessageFailureManager.getInstance().getAllFailureMessage();

        if(list == null || list.size() == 0){
            return;
        }

        if(history == null || history.size() == 0){
            history = new ArrayList<>();
            for (OnlineMessage message : list){
                history.add(message);
            }
        }else{
            //history是以发送时间降序排列，即第一条数据是最新发送的
            //所以处理区间为[lastMessageTime,history[0]],[history[0],history[1]]...
            //依次根据list的发送时间插入到history队列中

            long start = lastMessageTime == null? TNtpUtils.getRealTimeMillis():lastMessageTime;
            //先处理[lastMessageTime,history[0]]
            long end = history.get(0).getSendTime();
            handleSendFailureByTimeSpans(history,list,0,start,end);

            if(history.size() > 1){
                //时间区间为现在到这一条消息
                for (int i=0;i<history.size()-1;i++){
                    start = history.get(i).getSendTime();
                    end = history.get(i+1).getSendTime();
                    handleSendFailureByTimeSpans(history,list,i+1,start,end);
                }
            }
        }
    }

    /**
     * 处理发送失败的消息
     * 由于断线重连拉取历史消息覆盖当前消息，会导致发送失败的消息丢失。
     * @param history
     * @param list
     * @param index
     * @param start
     * @param end
     */
    private void handleSendFailureByTimeSpans(List<OnlineMessage> history,List<OnlineMessage> list,int index,Long start,Long end){
        List<OnlineMessage> messages = new ArrayList<>();

        for(int i=list.size()-1;i>=0;i--){
            OnlineMessage message = list.get(i);
            long sendTime = message.getSendTime();
            if(sendTime >= end && sendTime <= start){
                messages.add(message);
                list.remove(i);
            }
        }

        if(null != messages && messages.size()>0){
            history.addAll(index,messages);
        }
    }

    /**
     * 获取评价状态
     */
    public void getChatInfo(String mainUniqueId, final String uniqueId) {
        OnlineManager.getChatInfo(mainUniqueId, uniqueId, new ChatInfoCallback() {
            @Override
            public void onSuccess(JSONObject data) {
                try {
                    if (data.isNull(ChatInvestigationMessage.ALREADYINVESTIGATION))
                        data.put(ChatInvestigationMessage.ALREADYINVESTIGATION, 0);

                    if (data.getInt(ChatInvestigationMessage.ALREADYINVESTIGATION) == 1)
                        view.investigation(uniqueId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    /**
     * 发送资源
     *
     * @param messageType 消息类型
     * @param filePath    资源路径
     */
    public void send(int messageType, String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }

        File file = new File(filePath);
        if (file.isFile() && file.exists()) {

//            if (TOSClientKit.getTosClientKitConfig() != null) {
//                OnlineSetting onlineSetting = TOSClientKit.getTosClientKitConfig().getOnlineSetting();
//                if (onlineSetting != null)
//                    if (TFileUtils.getFileSize(file) > onlineSetting.getAppSettings() || onlineSetting.getAppSettings() > 0) {
//                        view.fileSizeLimit(messageType);
//                        return;
//                    }
//            }
            OnlineSetting onlineSetting = new OnlineSetting();
            if (onlineSetting.getAppSettings() != null)
                if (TFileUtils.getFileSize(file) > onlineSetting.getAppSettings()) {
                    view.fileSizeLimit(messageType);
                    return;
                }
            if (OnlineMessageType.VOICE == messageType) {
                sendVoice(filePath);
            } else if (OnlineMessageType.VIDEO == messageType) {
                sendVideo(filePath);
            } else if (OnlineMessageType.IMAGE == messageType) {
                sendImage(filePath);
            } else if (OnlineMessageType.FILE == messageType) {
                sendFile(filePath);
            }
        }
    }

    /**
     * 批量发送资源
     * 此处只有可能是图片或视频
     */
    public void send(List<String> filePaths) {
        if (filePaths == null || filePaths.size() == 0) {
            return;
        }

        for (String path : filePaths) {
            if (TMediaFile.isVideoFileType(path)) {
                send(OnlineMessageType.VIDEO, path);
            } else {
                send(OnlineMessageType.IMAGE, path);
            }
        }
    }

    /**
     * 发送图片
     *
     * @param filePath 图片地址
     */
    public void sendImage(final String filePath) {
        File imageFile = new File(filePath);
        if (imageFile.exists() && imageFile.isFile()) {
            OnlineMessage message = OnlineMessage.obtain(ImageMessage.obtain(filePath));
            sendMessage(message);
        }
    }

    /**
     * 发送视频
     *
     * @param localPath 视频本地地址
     */
    public void sendVideo(final String localPath) {
        File imageFile = new File(localPath);
        if (imageFile.exists() && imageFile.isFile()) {
            OnlineMessage message = OnlineMessage.obtain(VideoMessage.obtain(localPath));
            sendMessage(message);
        }
    }


    /**
     * 发送文件
     *
     * @param localPath 文件本地地址
     */
    public void sendFile(final String localPath) {
        File file = new File(localPath);
        if (file.exists() && file.isFile()) {
            OnlineMessage message = OnlineMessage.obtain(FileMessage.obtain(localPath, file.getName()));
            sendMessage(message);
        }
    }

    /**
     * 发送语音
     *
     * @param audioPath 语音路径
     */
    public void sendVoice(final String audioPath) {
        File imageFile = new File(audioPath);
        if (imageFile.exists() && imageFile.isFile()) {
            OnlineMessage message = OnlineMessage.obtain(VoiceMessage.obtain(audioPath));
            sendMessage(message);
        }
    }

    /**
     * 发送文本消息
     *
     * @param message 消息内容
     */
    public void sendText(String message) {
        if (TextUtils.isEmpty(message)) {
            view.showToast(R.string.ti_send_message_not_allow_null, true);
            return;
        }

        final TextMessage onlineTextMessage = TextMessage.obtain(message);
        OnlineMessage onlineMessage = OnlineMessage.obtain(onlineTextMessage);

        sendMessage(onlineMessage);
    }

    /**
     * 发送文本消息，并设置回传knowledge json字符串
     *
     * @param message 消息内容
     */
    public void sendText(String message, String knowledge) {
        if (TextUtils.isEmpty(message)) {
            view.showToast(R.string.ti_send_message_not_allow_null, true);
            return;
        }

        final TextMessage onlineTextMessage = TextMessage.obtain(message, knowledge);
        OnlineMessage onlineMessage = OnlineMessage.obtain(onlineTextMessage);

        sendMessage(onlineMessage);
    }

    /**
     * 卡片消息
     *
     * @param cardInfo 卡片消息内容
     */
    public void sendCard(JSONObject cardInfo) {
        final CardMessage onlineCardMessage = CardMessage.obtain(cardInfo, OnlineMessageType.CARD);
        OnlineMessage onlineMessage = OnlineMessage.obtain(onlineCardMessage);
        sendMessage(onlineMessage);
    }

    /**
     * 卡片消息
     * @param cardInfo
     */
    public void sendCard(CardInfo cardInfo){
        sendCard(CardInfo.putCardInfo(cardInfo));
    }

    /**
     * 发送事件
     * 事件不需要回调
     *
     * @param onlineMessage
     */
    public void sendEvent(final OnlineMessage onlineMessage) {
        if (!isSessionOpen) {
            return;
        }

        OnlineServiceClient.sendMessage(onlineMessage, null);
    }

    private OpenSessionAndSendMessage mOpenSessionAndSendMessage;


    /**
     * 打开会话且发送消息
     * */
    public class OpenSessionAndSendMessage implements VisitorReadyListener{

        private OnlineMessage message;

        public OpenSessionAndSendMessage(OnlineMessage message){
            this.message = message;
        }

        @Override
        public void onReady(SessionInfo sessionInfo) {

        }

        @Override
        public void onError(Exception e) {

        }

        public OnlineMessage getMessage() {
            return message;
        }
    }

    /**
     * 发送消息
     */
    public void sendMessage(final OnlineMessage onlineMessage) {
        if (TStringUtils.isEmpty(onlineMessage.getMessageUUID())) {
            onlineMessage.setMessageUUID(UUID.randomUUID().toString());
        }else{
            //UUID 不为空的情况下，说明是重发

        }
//        if (!OnlineServiceClient.isConnected()) {
//            TUIUtils.postTaskSafely(new Runnable() {
//                @Override
//                public void run() {
//                    onlineMessage.setStatus(TMessageStatus.MSG_STATUS_SEND_FAIL);
//                    view.sendMessageProgress(onlineMessage, 0);
//                }
//            });
//            return;
//        }

        if(!isSessionOpen){
            TLogUtils.d("会话关闭了，重新打开会话");
            openSession(mOpenSessionAndSendMessage = new OpenSessionAndSendMessage(onlineMessage));
            return;
        }

//        if (!isSessionOpen) {
//            try {
//                final HashMap<String, Object> params = new HashMap<>();
//                params.put(TOperatorLog.KEY_TAG, TIMBaseManager.getInstance().getConnectOption());
//                params.put(TOperatorLog.KEY_OPERATION, TOperatorLog.OPERATION_CONNECT);
//                params.put(TOperatorLog.KEY_STEP, "发送消息");
//                params.put(TOperatorLog.KEY_CONTENT, "当前连接状态:" + OnlineServiceClient.isConnected());
//                TOperatorLog.upload(params);
//            } catch (Exception e) {
//            }
//
//            //会话未打开时，发送消息直接置为消息发送失败
//            TUIUtils.postTaskSafely(new Runnable() {
//                @Override
//                public void run() {
//                    onlineMessage.setStatus(TMessageStatus.MSG_STATUS_SEND_FAIL);
//                    view.sendMessageProgress(onlineMessage, 0);
//                }
//            });
//            return;
//        }

        // : 2023/4/26 回调消息发送中
        view.sendMessageProgress(onlineMessage, 0);

        OnlineServiceClient.sendMessage(onlineMessage, new OnlineSendMessageCallback() {
            @Override
            public void onProgress(final OnlineMessage message, final int progress) {
                if (null == lastMessageTime) {
                    //当前列表为空，只有来一条数据，那么历史记录就要从这条数据的时间开始算
                    lastMessageTime = message.getOnlineContent().getCreateTime();
                }
                TUIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        // : 2023/4/26 处理发送视频时闪烁问题，由于progress未实际使用，则不返回
//                            view.sendMessageProgress(message, 0);
                    }
                });
            }

            @Override
            public void onSuccess(final OnlineMessage message) {
                TUIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        OnlineServiceMessage serviceMessage = message.getOnlineContent();
                        if (serviceMessage instanceof ChatInputHintMessage) {
                            //预知消息不需要回调
                            return;
                        }

                        view.sendMessageProgress(message, -1);
                    }
                });
            }

            @Override
            public void onError(final OnlineMessage message, int errorCode, final String errorDesc) {
                TUIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        view.showToast(errorDesc, true);

                        if (message != null) {
                            view.sendMessageProgress(message, -1);
                        }
                    }
                });
            }
        });
    }

    /**
     * 更多功能
     */
    public void initFunctions() {
        List<Function> funs = new ArrayList<>();
        funs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_IMAGE));
        funs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_VIDEO));
        funs.add(new Function(Function.TYPE_SYSTEM, Function.SEND_FILE));
        funs.add(new Function(Function.TYPE_SYSTEM, Function.TO_ONLINE));
        funs.add(new Function(Function.TYPE_SYSTEM, Function.CHAT_OVER));

        view.funcList(funs);
    }

    /**
     * 注册新消息监听
     */
    public void registerListener() {
        onlineConnectStatusListener = new OnlineConnectStatusListener() {
            @Override
            public void onConnecting() {

            }

            @Override
            public void onConnected() {
                TLogUtils.i("onConnected........连接成功：" + OnlineServiceClient.isSessionOpen());
                if (!OnlineServiceClient.isSessionOpen()) {
                    openSession(null);
                }
            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onReConnecting() {

            }

            @Override
            public void onReconnected() {
                TLogUtils.i("onReconnected........服务重连，连接成功：" + OnlineServiceClient.isSessionOpen());
                if (!OnlineServiceClient.isSessionOpen()) {
                    openSession(null);
                } else {
                    lastMessageTime = null;
                    loadHistory();
                }
                if (OnlineServiceClient.getCurrentSessionInfo() != null) {
                    TLogUtils.i("重连成功，调用访客上线");
                    OnlineServiceClient.chatOnline(null);
                    OnlineManager.visitorReadReceipt();
                }
            }

            @Override
            public void onKickOut() {
                view.showToast("您的账号已在其他设备登录", true);
            }
        };
        OnlineServiceClient.addOnlineConntectStatusListener(onlineConnectStatusListener);

        if (OnlineServiceClient.isConnected()) {
//            if (!OnlineServiceClient.isSessionOpen()) {
//                openSession(null);
//            }else{
//                //会话已经打开了，直接拉取历史消息
//                loadHistory();
//            }
            openSession(null);
        } else {
            if (!OnlineServiceClient.isConnecting()) {
                OnlineServiceClient.connect(null);
            }
        }

        //消息监听
        OnlineServiceClient.addOnlineMessageListener(onlineMessageListener);

        //事件监听
        OnlineServiceClient.addOnlineEventListener(eventListener);
    }

    private OnlineMessageListener onlineMessageListener = new OnlineMessageListener() {
        @Override
        public void onMessage(final OnlineMessage message) {
            if (null == lastMessageTime) {
                //当前列表为空，只有来一条数据，那么历史记录就要从这条数据的时间开始算
                lastMessageTime = message.getOnlineContent().getCreateTime();
            }

            TUIUtils.postTaskSafely(() -> {
                if(isVisibleToUser) {
                    OnlineManager.visitorReadReceipt();
                }

                //收到的消息可能不是当前会话的消息
                SessionInfo sessionInfo = OnlineServiceClient.getCurrentSessionInfo();
                if (null != sessionInfo) {
                    OnlineServiceMessage content = message.getOnlineContent();
                    if (null != content && !TextUtils.isEmpty(sessionInfo.getMainUniqueId())) {
                        if (sessionInfo.getMainUniqueId().equals(content.getMainUniqueId())) {
                            view.onReceiverMessage(message);
                        }
                    }
                }

                //TODO 机器人欢迎语先来
                openAndSend();
            });
        }
    };

    private boolean isVisibleToUser;

    public void setUserVisibleHint(boolean isVisibleToUser){
        this.isVisibleToUser = isVisibleToUser;

        if(isVisibleToUser){
            OnlineManager.visitorReadReceipt();
        }
    }

    /**
     * 需要配置机器人欢迎语
     * */
    private boolean canOpenAndSend = false;

    private void openAndSend() {
        if(canOpenAndSend) {
            if (mOpenSessionAndSendMessage != null) {
                sendMessage(mOpenSessionAndSendMessage.getMessage());
                mOpenSessionAndSendMessage = null;
            }

            canOpenAndSend = false;
        }
    }

    private OnlineEventListener eventListener = new OnlineEventListener() {
        @Override
        public void chatOpen(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    setSessionOpen(true);


                }
            });
        }

        @Override
        public void chatClose(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    OnlineQueueManager.exitQueue(true);

                    OnlineServiceMessage investigationMessage = message.getOnlineContent();
                    ChatCloseMessage chatCloseMessage = (ChatCloseMessage) investigationMessage;
                    if (chatCloseMessage.hasEndMessage())
                        view.onReceiverMessage(message);

                    setSessionOpen(false);
                }
            });
        }

        //人工接入事件
        @Override
        public void chatBridge(final OnlineMessage message) {
            OnlineServiceMessage mOnlineServiceMessage = message.getOnlineContent();
            if (mOnlineServiceMessage instanceof ChatBridgeMessage) {
                final ChatBridgeMessage mChatBridgeMessage = (ChatBridgeMessage) mOnlineServiceMessage;
                if (!mChatBridgeMessage.isEmpty()) {
                    // : 2022/9/6 拆分实时接收到欢迎语和介绍语前端显示为2条独立消息（后端发送为一条消息）
                    final List<String> welcome = mChatBridgeMessage.getWelcome();
                    mChatBridgeMessage.setShowWelcome(true);
                    message.setMessageContent(mChatBridgeMessage);
                    if (welcome != null && welcome.size() > 0) {
                        TUIUtils.postTaskSafely(() -> view.onReceiverMessage(message));
                    }
                    if (TStringUtils.isNotEmpty(mChatBridgeMessage.getClientIntroduce())) {
                        TUIUtils.postTaskSafely(() -> {
                            mChatBridgeMessage.setShowWelcome(false);
                            final OnlineMessage clientMessage = new OnlineMessage(message);
                            clientMessage.setMessageContent(mChatBridgeMessage);
                            view.onReceiverMessage(clientMessage);
                        }, 20);
                    }
                }
            }

            TUIUtils.postTaskSafely(() -> {
                OnlineQueueManager.notifyQueueChanged(message);
            });
        }

        @Override
        public void chatQueue(final OnlineMessage message) {
            TUIUtils.postTaskSafely(() -> {
                OnlineQueueManager.notifyQueueChanged(message);
                view.onReceiverMessage(message);
            });
        }

        @Override
        public void chatLocation(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    OnlineQueueManager.notifyQueueChanged(message);
                    view.onReceiverMessage(message);
                }
            });
        }

        @Override
        public void chatLeaveMessage(final OnlineMessage message) {
            TUIUtils.postTaskSafely(() -> {
                OnlineServiceMessage contentMessage = message.getOnlineContent();
                if (contentMessage instanceof ChatLeaveMessage) {
                    view.chatLeaveMessage((ChatLeaveMessage) contentMessage);
                }
            });
        }

        @Override
        public void chatInvestigation(final OnlineMessage message) {

            TUIUtils.postTaskSafely(() -> {
                OnlineServiceMessage investigationMessage = message.getOnlineContent();
                final JSONObject messageBody = investigationMessage.getBody();
                try {
                    messageBody.put(ChatInvestigationMessage.ALREADYINVESTIGATION, 0);
                    messageBody.put(ChatInvestigationMessage.UNIQUEID, messageBody.getString("messageUniqueId"));
                    messageBody.put(ChatInvestigationMessage.MAINUNIQUEID, OnlineServiceClient.getCurrentSessionInfo().getMainUniqueId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (investigationMessage instanceof ChatInvestigationMessage) {
                    ChatInvestigationMessage mChatInvestigationMessage = (ChatInvestigationMessage) investigationMessage;
//                        view.investigation(mChatInvestigationMessage);
                    view.onReceiverMessage(message);

                    if (mChatInvestigationMessage.hasEndMessage()) {
                        ChatInvestigationMessage chatInvestigationMessage = mChatInvestigationMessage.copyPrompt();
                        view.onReceiverMessage(OnlineMessage.copy(chatInvestigationMessage));
                    }

                    if (mChatInvestigationMessage.isClose()) {
                        setSessionOpen(false);
                    }
                }
            });
        }

        //机器人接入事件
        @Override
        public void robotBridge(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    canOpenAndSend = true;
                    view.onReceiverMessage(message);
                }
            });
        }

        @Override
        public void withdraw(final String messageId) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    view.withdraw(messageId);
                }
            });
        }

        @Override
        public void chatSwitch(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    view.onReceiverMessage(message);
                }
            });
        }

        @Override
        public void chatLeadingWords(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    view.onReceiverMessage(message);
                }
            });
        }

        @Override
        public void chatInquiry(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    view.onReceiverMessage(message);
                }
            });
        }

        @Override
        public void chatResponse(final OnlineMessage message) {
            TUIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    if (null != message && null != message.getOnlineContent()) {
                        OnlineServiceMessage content = message.getOnlineContent();
                        if (content instanceof ChatResponseMessage) {
                            ChatResponseMessage responseMessage = (ChatResponseMessage) content;
                            if (responseMessage.isSessionClose()) {
                                setSessionOpen(false);
                            } else if (responseMessage.getCode() == ChatResponseMessage.SEND_SUCCESS) {
                                if (responseMessage.getSendStatus() == OnlineMessageStatus.SENSITIVE_WORDS && responseMessage.getMessageUUID() != null) {
                                    view.updateMessageStatusByMessageUUID(responseMessage.getMessageUUID(), TMessageStatus.MSG_STATUS_SEND_FAIL_SENSITIVE_WORDS);
                                }
                            }
                        }
                    }
                }
            });
        }
    };

    /**
     * 取消新消息监听
     */
    public void unRegisterListener() {
        OnlineServiceClient.removeOnlineEventListener(eventListener);
        OnlineServiceClient.removeOnlineMessageListener(onlineMessageListener);
        if (onlineConnectStatusListener != null) {
            OnlineServiceClient.removeOnlineConnectStatusListener(onlineConnectStatusListener);
        }
    }

    /**
     * 打开会话
     */
    public void openSession(VisitorReadyListener listener) {
        if (OnlineServiceClient.isConnected()) {
            try {
                TIMMqttManager.getInstance().startSendPing();
            } catch (Exception e) {
            }
            OnlineServiceClient.visitorReady(new VisitorReadyListener() {
                @Override
                public void onReady(SessionInfo sessionInfo) {
                    OnlineManager.getSetting(new SettingCallback() {
                        @Override
                        public void onSuccess(OnlineSetting onlineSetting) {
                            TOSClientKitConfig tosClientKitConfig = new TOSClientKitConfig.Builder()
                                    .setOnlineSetting(onlineSetting)
                                    .build();
                            TOSClientKit.setTOSClientKitConfig(tosClientKitConfig);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                    loadHistory();
                    TLogUtils.d("openSession = " + sessionInfo.toString());

                    if(null != listener){
                        listener.onReady(sessionInfo);
                    }
                }

                @Override
                public void onError(Exception e) {
                    TLogUtils.d("openSession = " + e.getMessage());
                    if(null != listener){
                        listener.onError(e);
                    }
                }
            });
        }
    }

    /**
     * 满意度评论消息的ID
     */
    private String investigationMessageUniqueId;

    /**
     * 处理第一次退出时显示满意度评价
     * @param context
     */
    public void handleFirstOutInvestigation(Context context){
        if(TOSClientKit.getCurrentOnlineStatus() == OnlineMessageManager.STATUS_ONLINE) {
            SessionInfo sessionInfo = OnlineServiceClient.getCurrentSessionInfo();
            if (sessionInfo != null) {
                //判断是否弹出过对话框或是否已评价
                String key = sessionInfo.getMainUniqueId();

                SharedPreferences sp = context.getApplicationContext()
                    .getSharedPreferences("investigation",
                        MODE_PRIVATE);
                boolean hasMainUniqueId = sp.getBoolean(key, false);
                if (!hasMainUniqueId) {
                    //没有弹出过
                    //是否提交过
                    InvestigationManager.isRecordInvestigation(key, result -> {
                        if (result) {
                            sp.edit().putBoolean(key, true).apply();
                            view.investigationResult(false);
                        } else {
                            //没有提交过满意度，生成满意度
                            InvestigationManager.requestInvestigation(
                                new RequestInvestigationListener() {
                                    @Override
                                    public void onError(Exception e) {
                                        view.investigationResult(false);
                                    }

                                    @Override
                                    public void onSuccess(String messageUniqueId) {
                                        investigationMessageUniqueId = messageUniqueId;
                                        sp.edit().putBoolean(key, true).apply();
                                        view.investigationResult(true);
                                    }
                                });
                        }
                    });
                } else {
                    //弹出过
                    view.investigationResult(false);
                }

            } else {
                view.investigationResult(false);
            }
        }else{
            view.investigationResult(false);
        }
    }

    /**
     * 提交满意度
     * @param investigationStar
     * @param starTabs
     */
    public void submitInvestigation(InvestigationStar investigationStar,
        List<String> starTabs){
        if(TextUtils.isEmpty(investigationMessageUniqueId)){
            view.onSubmitInvestigationResult(false,null,new RuntimeException("not allow investigation"));
            return;
        }

        InvestigationManager.submitInvestigation(investigationMessageUniqueId, investigationStar, starTabs,
            new SubmitInvestigationListener() {
                @Override
                public void onError(Exception e) {
                    view.onSubmitInvestigationResult(false,null,e);
                }

                @Override
                public void onError(String msg) {
                    view.onSubmitInvestigationResult(false,msg,null);
                }

                @Override
                public void onSuccess() {
                    view.onSubmitInvestigationResult(true,null,null);
                }
            });
    }
}
