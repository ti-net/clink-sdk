package com.tinet.oskit;

import android.content.Context;
import android.widget.ImageView;

import com.lcw.library.imagepicker.ImagePicker;
import com.lcw.library.imagepicker.utils.ImageLoader;
import com.tinet.oskit.manager.TOSClientKitConfig;
import com.tinet.oslib.config.TOSConnectOption;
import com.tinet.oslib.listener.OnLastMessageListener;
import com.tinet.oslib.listener.OnlineConnectResultCallback;
import com.tinet.oslib.listener.OnlineConnectStatusListener;
import com.tinet.oslib.listener.OnlineDisconnectListener;
import com.tinet.oslib.listener.OnlineEventListener;
import com.tinet.oslib.listener.OnlineMessageListener;
import com.tinet.oslib.listener.SessionInfoListener;
import com.tinet.oslib.manager.OnlineConnectManager;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.tinet.oslib.manager.OnlineQuickManager;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.oslib.model.bean.SessionInfo;
import com.tinet.oslib.utils.ConnectHelper;
import com.tinet.threepart.emoji.IImageLoader;
import com.tinet.threepart.emoji.LQREmotionKit;
import com.tinet.oskit.listener.TImageLoader;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.config.TOSInitOption;
import com.tinet.oslib.model.bean.UserInfo;

import java.util.ArrayList;

/**
 * @ProjectName: TIMSDK
 * @ClassName: OnlineKitManager
 * @Author: liuzr
 * @CreateDate: 2021-08-24 17:43
 * @Description: kit 对外统一入口类
 */
public class TOSClientKit {

    /**
     * 图片加载ImageLoader
     */
    private static TImageLoader mImageLoader;

    /**
     * kit公共配置类
     */
    private static TOSClientKitConfig mTOSClientKitConfig;

    /**
     * 初始化SDK
     *
     * @param context
     * @param TOSInitOption * @param accessId      访问标识
     *                      * @param accessSecret  访问秘钥
     *                      * @param enterpriseId  企业号
     *                      * @param debug         是否开启debug模式
     *                      * @param apiUrl        平台apiUrl
     *                      * @param onlineUrl     平台onlineUrl
     *                      * @param advanceParams 自定义可配参数,可为空
     */
    public static void initSDK(Context context, TOSInitOption TOSInitOption, final TImageLoader imageLoader) {
        TOSClientKit.mImageLoader = imageLoader;

        //表情
        LQREmotionKit.init(context, new IImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                imageLoader.loadImage(imageView, path);
            }
        });

        OnlineServiceClient.init(context, TOSInitOption);

        ImagePicker.getInstance().setTitle(context.getString(R.string.ti_camera))//设置标题
                .showImage(true)//设置是否展示图片
                .showVideo(true)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(9);//设置最大选择图片数目(默认为1，单选)
        ImagePicker.getInstance().setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String imagePath) {
                TOSClientKit.getImageLoader().loadImage(imageView, imagePath);
            }

            @Override
            public void loadPreImage(ImageView imageView, String imagePath) {
                TOSClientKit.getImageLoader().loadImage(imageView, imagePath);
            }

            @Override
            public void clearMemoryCache() {

            }
        });
    }

    /**
     * 连接
     *
     * @param tosConnectOption            * @param userId        用户App的userID
     *                                    * @param nickname      昵称
     *                                    * @param headUrl       头像地址
     *                                    * @param mobile        手机号
     *                                    * @param advanceParams 自定义可配参数,可为空
     * @param onlineConnectResultCallback
     */
    public static void connect(TOSConnectOption tosConnectOption, OnlineConnectResultCallback onlineConnectResultCallback) {
        if (tosConnectOption.getAdvanceParams() != null && tosConnectOption.getAdvanceParams().containsKey("enableMqtt") && tosConnectOption.getAdvanceParams().get("enableMqtt") instanceof Boolean && (!((boolean) tosConnectOption.getAdvanceParams().get("enableMqtt")))) {
            ConnectHelper.getConnectHelper().authenticationNotConnect(tosConnectOption.getVisitorId(), tosConnectOption.getNickname(), tosConnectOption.getHeadUrl(), tosConnectOption.getMobile(), tosConnectOption.getAdvanceParams(), onlineConnectResultCallback);
        } else {
            ConnectHelper.getConnectHelper().authentication(tosConnectOption.getVisitorId(), tosConnectOption.getNickname(), tosConnectOption.getHeadUrl(), tosConnectOption.getMobile(), tosConnectOption.getAdvanceParams(), onlineConnectResultCallback);
        }
    }

    /**
     * 断开服务连接
     *
     * @param isReceivePush 是否需要推送
     */
    public static void disConnect(boolean isReceivePush, OnlineDisconnectListener listener) {
        OnlineServiceClient.disConnect(isReceivePush, listener);
    }

    /**
     * 获取当前登录的用户信息
     */
    public static UserInfo getCurrentUserInfo() {
        return OnlineServiceClient.getCurrentUserInfo();
    }

    /**
     * 获取当前会话信息 (同步)
     *
     * @return
     */
    public static SessionInfo getCurrentSessionInfo() {
        return OnlineServiceClient.getCurrentSessionInfo();
    }

    /**
     * 获取当前会话信息（异步）
     *
     * @return
     */
    public static void getCurrentSessionInfo(SessionInfoListener sessionInfoListener) {
        OnlineServiceClient.getCurrentSessionInfo(sessionInfoListener);
    }

    /**
     * 获取图片加载loader
     *
     * @return
     */
    public static TImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * 获取会话未读数及最后一天消息内容
     *
     * @param visitorId
     * @param onLastMessageListener
     */
    public static void getLastMessageInfo(String visitorId, OnLastMessageListener onLastMessageListener) {
        OnlineServiceClient.getLastMessageInfo(visitorId, onLastMessageListener);
    }

    /**
     * 获取当前客服在线状态 （同步）
     *
     * @return
     */
    public static int getCurrentOnlineStatus() {
        return OnlineMessageManager.getCurrentOnlineStatus();
    }

    /**
     * 设置客服在线状态变化事件监听 (异步)
     *
     * @param listener
     */
    public static void setOnlineStatusListener(OnlineMessageManager.OnlineStatusListener listener) {
        OnlineMessageManager.setOnlineStatusListener(listener);
    }

    /**
     * 更新会话窗体快捷入口
     *
     * @param arrayList
     */
    public static void updateSessionWindowQuickEntrys(ArrayList<LabeInfo> arrayList) {
        OnlineQuickManager.getInstance().updateQuicks(arrayList);
    }

    /**
     * 获取SDK版本号
     */
    public static String getSDKVersion() {
        return OnlineServiceClient.getSDKVersion();
    }

    /**
     * 添加消息监听
     *
     * @param onlineMessageListener
     */
    public static void addOnlineMessageListener(OnlineMessageListener onlineMessageListener) {
        OnlineMessageManager.addOnlineMessageListener(onlineMessageListener);
    }

    /**
     * 移除消息监听
     *
     * @param onlineMessageListener
     */
    public static void removeOnlineMessageListener(OnlineMessageListener onlineMessageListener) {
        OnlineMessageManager.removeOnlineMessageListener(onlineMessageListener);
    }

    /**
     * 添加会话事件监听
     *
     * @param onlineEventListener
     */
    public static void addOnlineEventListener(OnlineEventListener onlineEventListener) {
        OnlineMessageManager.addOnlineEventListener(onlineEventListener);
    }

    /**
     * 移除会话事件监听
     *
     * @param onlineEventListener
     */
    public static void removeOnlineEventListener(OnlineEventListener onlineEventListener) {
        OnlineMessageManager.removeOnlineEventListener(onlineEventListener);
    }

    /**
     * 添加服务连接状态监听
     *
     * @param listener
     */
    public static void addOnlineConnectStatusListener(OnlineConnectStatusListener listener) {
        OnlineConnectManager.getConnectManager().addOnlineConnectStatusListener(listener);
    }

    /**
     * 取消服务连接状态监听
     *
     * @param listener
     */
    public static void removeOnlineConnectStatusListener(OnlineConnectStatusListener listener) {
        OnlineConnectManager.getConnectManager().removeOnlineConnectStatusListener(listener);
    }

    /**
     * 获取kit层公共配置类
     *
     * @return
     */
    public static TOSClientKitConfig getTOSClientKitConfig() {
        return mTOSClientKitConfig;
    }

    /**
     * 设置kit层公共配置类
     *
     * @param mTosClientKitConfig
     */
    public static void setTOSClientKitConfig(TOSClientKitConfig mTosClientKitConfig) {
        TOSClientKit.mTOSClientKitConfig = mTosClientKitConfig;
    }

}
