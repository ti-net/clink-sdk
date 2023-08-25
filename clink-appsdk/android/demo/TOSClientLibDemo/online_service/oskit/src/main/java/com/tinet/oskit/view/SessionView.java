package com.tinet.oskit.view;

import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oskit.model.DataTemplate;
import com.tinet.oskit.model.Function;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;

import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionView
 * @Author: liuzr
 * @CreateDate: 2021-08-23 09:37
 * @Description:
 */
public interface SessionView extends TinetView {

    /**
     * 加载历史会话
     *
     * @param template  数据集
     * @param isSuccess 数据加载成功或失败标识
     */
    void loadHistoryResult(DataTemplate<OnlineMessage> template, Boolean isSuccess);

    /**
     * 发送消息
     *
     * @param message 已发送的消息
     */
    void onMessageSend(OnlineMessage message);

    /**
     * 消息发送进度，用于文件、图片、音频、视频
     *
     * @param message  消息
     * @param progress 进度【0，100】
     */
    void sendMessageProgress(OnlineMessage message, int progress);

    /**
     * 更多功能列表
     *
     * @param funcs 功能列表
     */
    void funcList(List<Function> funcs);

    /**
     * 撤消消息
     *
     * @param message
     */
    void onRevokeMessage(OnlineMessage message);

    /**
     * 接收到一条新消息
     *
     * @param message 新消息体
     */
    void onReceiverMessage(OnlineMessage message);

    /**
     * 删除消息
     *
     * @param message 删除的消息集
     */
    void onMessageDelete(List<OnlineMessage> message);

    /**
     * 清空消息
     */
    void onClearMessage();

    /**
     * 将消息置为已读
     *
     * @param msgId 消息ID
     */
    void setMessageRead(String msgId);

    /**
     * 会话建立
     */
    void onSessionOpen();

    /**
     * 会话关闭
     */
    void onSessionClosed();

    /**
     * 撤回一条消息
     *
     * @param messageId 被撤回消息ID
     */
    void withdraw(String messageId);

    /**
     * 文件大小限制
     *
     * @param fileType 文件类型
     */
    void fileSizeLimit(int fileType);

    /**
     * 留言
     *
     * @param message
     */
    void chatLeaveMessage(ChatLeaveMessage message);

    /**
     * 修改评价状态
     */
    void investigation(String uniqueId);

    /**
     * 根据MessageUUID更新消息状态
     * @param messageUUID
     */
    void updateMessageStatusByMessageUUID(String messageUUID, int status);

    /**
     * 满意度
     * @param result true没有弹起过满意度，也没有提交过满意度
     */
    void investigationResult(boolean result);

    /**
     * 提交满意度结果
     * @param result
     * @param e 异常信息
     * @param msg 异常原因
     */
    void onSubmitInvestigationResult(boolean result,String msg,Exception e);
}
