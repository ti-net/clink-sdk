package com.tinet.oskit.listener;

import android.view.View;

import com.tinet.oslib.model.bean.LogisticsCardInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.ChatMiniProgramCardMessage;
import com.tinet.oslib.model.message.content.TextMessage;

import java.util.ArrayList;

import androidx.annotation.NonNull;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionClickListener
 * @Author: liuzr
 * @CreateDate: 2021-08-23 10:28
 * @Description: 会话消息单击、长按等回调
 */
public interface SessionClickListener {

    /**
     * 单击
     */
    void onClick(View itemView, OnlineMessage message);

    /**
     * 长按
     */
    void onLongClick(View itemView, OnlineMessage info);

    /**
     * 点击卡片消息
     */
    void onCardMessageClick(View itemView, OnlineMessage message);

    /**
     * 重新编辑消息
     */
    void reEditMessage(TextMessage message);

    /**
     * 重新发送消息
     *
     * @param message 发送失败的消息重新发送
     */
    void resendMessage(OnlineMessage message);

    /**
     * 图片消息点击
     *
     * @param messages 列表中所有的图片消息的图片
     * @param index    索引
     */
    void onImageMessageClick(ArrayList<String> messages, int index);

    /**
     * 发送卡片消息
     *
     * @param info 卡片消息
     */
    void onCardMessageClickSendOut(OnlineMessage info);

    /**
     * 提了一个问题，发送一条普通的文本信息
     */
    void onQuestionRequest(String info);

    /**
     * 提了一个问题，发送一条普通的文本信息
     */
    void onQuestionRequest(String info, String knowledge);

    /**
     * 超链接点击事件
     *
     * @param url 跳转地址
     */
    void onLinkClick(String url);

    /**
     * 高亮文本点击事件
     *
     * @param content          点击文本内容
     * @param messageEventType 文本类型
     */
    void onLinkClick(String content, String messageEventType);

    /**
     * 小程序卡片点击事件
     *
     * @param miniProgramCardMessage 小程序卡片消息体
     */
    void onMiniProgramCardClick(ChatMiniProgramCardMessage miniProgramCardMessage);

    /**
     * 物流卡片按钮点击事件
     *
     * @param logisticsCardInfo 物流卡片消息体
     */
    void onLogisticsCardButtonClick(LogisticsCardInfo logisticsCardInfo);

    /**
     * 视频播放
     *
     * @param url 视频地址
     */
    void videoPlay(String url);

    /**
     * 放弃排队
     */
    void cancelQueue();

    /**
     * 下载文件
     *
     * @param url  文件url
     * @param name 文件名
     */
    void downloadFile(String url, String name);

    /**
     * 评价点击事件
     *
     * @param message 文件url
     */
    void onEvaluateInvestigationClick(ChatInvestigationMessage message);

    /**
     * 开始权限申请回调
     *
     * @param permissions 对应的权限组
     * @param requestCode REQUEST_AUDIO_PERMISSION （1661）  申请语音权限
     *                    REQUEST_CAMERA_PERMISSION  （1662）  申请相机权限
     *                    REQUEST_CAMERA_SHOOT_PERMISSION  （1663）   申请相机、语音权限 -- 拍摄
     *                    REQUEST_FILE_PERMISSION （1664）    申请文件权限 -- 文件
     */
    void onStartRequestPermissionsCallback(@NonNull String[] permissions, int requestCode);

}
