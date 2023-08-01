package com.tinet.online;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.tinet.online.test.PlantformInfo;
import com.tinet.online.test.PlantformUtil;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.TImageLoader;
import com.tinet.oskit.manager.TCustomizationUI;
import com.tinet.oskit.manager.TOSClientKitConfig;
import com.tinet.oskit.manager.TUiManager;
import com.tinet.oskit.manager.TUiManager.UIProvider;
import com.tinet.oskit.model.TTextPatternRule;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.common.PlatformDefine;
import com.tinet.oslib.config.TOSInitOption;
import com.tinet.oslib.listener.OnlineMessageListener;
import com.tinet.oslib.listener.OnlineSendMessageCallback;
import com.tinet.oslib.manager.OnlineMessageManager;
import com.didichuxing.doraemonkit.DoKit;
import com.didichuxing.doraemonkit.DoKit.Builder;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatBridgeMessage;
import com.tinet.oslib.model.message.content.ChatLocationMessage;
import com.tinet.oslib.model.message.content.ChatQueueMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.timclientlib.utils.TLogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**

 * @ProjectName: TIMSDK
 * @ClassName: App
 * @Author: liuzr
 * @CreateDate: 2021-08-23 14:42
 * @Description:
 */
public class App extends MultiDexApplication {

    /**
     * 匹配手机号正则
     */
    public static final String PATTERN_REGEX_PHONE_NUMBER = "1[345678]\\d{9}";

    public static final String TEXT_PATTERN_MESSAGE_TYPE_PHONE_NUMBER = "phoneNumber";

    @Override
    public void onCreate() {
        super.onCreate();

//        new Builder(this).build();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        initOnlineService();
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

        OnlineMessageManager.addOnlineMessageListener(new OnlineMessageListener() {
            @Override
            public void onMessage(OnlineMessage message) {
                TLogUtils.d("收到新消息，消息类型：" + message.getOnlineContent().getClass().getName() + ",消息内容" + message.getOnlineContent().getContent());
            }
        });

        TUiManager.registerViewHolder("chatQueue",new TUiManager.UIConfig(R.layout.queue,
            new UIProvider() {

            private TextView tvQueue;

                @Override
                public void initView(View itemView) {
                    tvQueue = itemView.findViewById(R.id.tvQueue);
                    Button btnQueue = itemView.findViewById(R.id.btnQueue);
                    btnQueue.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OnlineServiceClient.cancelQueue(new OnlineSendMessageCallback() {
                                @Override
                                public void onProgress(OnlineMessage onlineMessage, int i) {

                                }

                                @Override
                                public void onSuccess(OnlineMessage onlineMessage) {
                                    Toast.makeText(App.this,"结束排队了",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(OnlineMessage onlineMessage, int i, String s) {

                                }
                            });
                        }
                    });
                }

                @Override
                public void update(OnlineMessage info) {
                    tvQueue.setText("");

                    OnlineServiceMessage message = info.getOnlineContent();

                    if(message instanceof ChatQueueMessage){
                        //排队消息，放弃排队
                        final ChatQueueMessage queueMessage = (ChatQueueMessage)message;
                        if(!queueMessage.isLeaveQueue()) {
                            String str = queueMessage.getLocation();
                            tvQueue.setText(str);

                            TLogUtils.d(str);
                        }else{
                            tvQueue.setText(queueMessage.getLocation());
                            TLogUtils.d(queueMessage.getLocation());
                        }
                    }
                }
            }));

    }

    private void initOnlineService() {
        //PlantformInfo与接入无关，接入时直接申请：accessId，accessSecret，enterpriseId填入即可
        PlantformInfo info = PlantformUtil.getPlantform(this);
        String accessId = info == null ? Constant.accessId : info.getAccessId();
        String accessSecret = info == null ? Constant.accessSecret : info.getAccessSecret();
        long enterpriseId = info == null ? Constant.enterpriseId : info.getEnterpriseId();
        PlatformDefine define = info == null ? Constant.define : info.getPlatform();

        TLogUtils.d("enterpriseId=" + enterpriseId + "\naccessId=" + accessId + "\naccessSecret=" + accessSecret + "\ndefine=" + define);

        TOSInitOption tOSInitOption = new TOSInitOption();
        tOSInitOption.setAccessId(accessId);
        tOSInitOption.setAccessSecret(accessSecret);
        tOSInitOption.setEnterpriseId(enterpriseId);
        tOSInitOption.setApiUrl(define.getApiUrl());
        tOSInitOption.setOnlineUrl(define.getOnlineUrl());
        tOSInitOption.setDebug(BuildConfig.DEBUG);

        Map<String, Object> headers = new HashMap<>();
        if (info != null && "Kt".equals(info.getType())) {
            // : 2022/6/23  此处添加KT环境标识参数等可配参数
            headers.put("deBugEnv", "ktTest");
            tOSInitOption.setAdvanceParams(headers);
        }

        headers.put("地址1", "南京市");
        headers.put("渠道1", "南京");
        headers.put("用户测试", "测试数据");
        headers.put("test1", "测试数据1");

        TOSClientKit.initSDK(this, tOSInitOption, new TImageLoader() {
            @Override
            public void loadImage(ImageView imageView, Object uri) {
                GlideUrl glideUrl = new GlideUrl((String) uri, new LazyHeaders.Builder()
                        .addHeader("X-Virtual-Env", "dev.chat")
                        .build());
                boolean contain = (info != null && "Kt".equals(info.getType())) ? ((String) uri).contains("https") : false;
                Glide.with(imageView.getContext())
                        .load(contain ? glideUrl : uri)
                        .error(R.drawable.ti_ic_load_default_image)
                        .placeholder(R.drawable.ti_ic_load_default_image)
                        .into(imageView);
            }

            @Override
            public void loadImage(ImageView imageView, Object uri, int placeholderImg, int errorImg) {
                GlideUrl glideUrl = new GlideUrl((String) uri, new LazyHeaders.Builder()
                        .addHeader("X-Virtual-Env", "dev.chat")
                        .build());
                boolean contain = (info != null && "Kt".equals(info.getType())) ? ((String) uri).contains("https") : false;
                Glide.with(imageView.getContext())
                        .load(contain ? glideUrl : uri)
                        .override(CustomTarget.SIZE_ORIGINAL, CustomTarget.SIZE_ORIGINAL)
                        .error(errorImg)
                        .placeholder(placeholderImg)
                        .into(imageView);
            }

            @Override
            public void loadImage(ImageView imageView, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
                GlideUrl glideUrl = new GlideUrl((String) uri, new LazyHeaders.Builder()
                        .addHeader("X-Virtual-Env", "dev.chat")
                        .build());
                boolean contain = (info != null && "Kt".equals(info.getType())) ? ((String) uri).contains("https") : false;
                Glide.with(imageView.getContext())
                        .load(contain ? glideUrl : uri)
                        .override(originalWidth, originalHeight)
                        .listener(new ImageRequestListener(listener))
                        .error(R.drawable.ti_ic_load_default_image)
                        .placeholder(R.drawable.ti_ic_load_default_image).into(imageView);

            }

            @Override
            public void loadImage(Context context, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
                GlideUrl glideUrl = new GlideUrl((String) uri, new LazyHeaders.Builder()
                        .addHeader("X-Virtual-Env", "dev.chat")
                        .build());
                boolean contain = (info != null && "Kt".equals(info.getType())) ? ((String) uri).contains("https") : false;
                Glide.with(context)
                        .load(contain ? glideUrl : uri)
                        .override(originalWidth, originalHeight)
                        .into(new CustomTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                if (null != listener) {
                                    listener.onResourceReady(resource);
                                }
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                if (null != listener) {
                                    listener.onLoadFailed();
                                }
                            }
                        });
            }
        });

        // : 2022/8/1 测试自定义配置UI
        TCustomizationUI tCustomizationUI = new TCustomizationUI();//自定义UI对象
        tCustomizationUI.sendBubbleBackground = R.drawable.ti_bg_color;//发送端气泡背景
        tCustomizationUI.receiveBubbleBackground = R.drawable.ti_bg_color;//接收端气泡背景
        tCustomizationUI.sendBubbleBackgroundColor = getResources().getColor(R.color.ti_red);//发送端气泡背景颜色
        tCustomizationUI.receiveBubbleBackgroundColor = getResources().getColor(R.color.ti_red);//接收端气泡背景颜色

        tCustomizationUI.sessionBackgroundColor = getResources().getColor(R.color.ti_session_func_item_text_color);//聊天背景颜色
        tCustomizationUI.msgTimeColor = getResources().getColor(R.color.ti_guess_text_color);//时间字体文字颜色
        tCustomizationUI.inputAreaBgColor = getResources().getColor(R.color.ti_guess_text_color);//输入区域背景颜色
        tCustomizationUI.inputAreaVoicePressTextColor = getResources().getColor(R.color.ti_guess_text_color);//输入区域按住录音按钮文字颜色
        tCustomizationUI.inputBoxHintText = "hint_text";//会话页面输入框hint文案
        tCustomizationUI.chatAvatarRadius = R.dimen.fab_margin;//头像圆角弧度

        tCustomizationUI.showVisitorNickname = false;//访客昵称
        tCustomizationUI.showVisitAvatar = false;//访客头像
        tCustomizationUI.showAgentRobotNickname = false;//客服、机器人昵称
        tCustomizationUI.showAgentRobotAvatar = false;//客服、机器人头像
        tCustomizationUI.showVoiceButton = false;//语音按钮

        // : 2022/10/13 配置文本消息含超链接高亮显示规则
        List<TTextPatternRule> tTextPatternRules = new ArrayList<>();
        tTextPatternRules.add(new TTextPatternRule(Pattern.compile(PATTERN_REGEX_PHONE_NUMBER, Pattern.CASE_INSENSITIVE),getResources().getColor(R.color.ti_receive_super_line_text_color),TEXT_PATTERN_MESSAGE_TYPE_PHONE_NUMBER));

        TOSClientKitConfig tosClientKitConfig = new TOSClientKitConfig.Builder()
//                .setTCustomizationUI(tCustomizationUI)//配置自定义UI
                .setTextHighLightRuleList(tTextPatternRules)
                .build();

        TOSClientKit.setTOSClientKitConfig(tosClientKitConfig);

    }

    class ImageRequestListener implements RequestListener<Drawable> {

        private TImageLoaderListener listener;

        public ImageRequestListener(TImageLoaderListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            if (null != listener) {
                listener.onLoadFailed();
            }

            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            if (null != listener) {
                listener.onResourceReady(resource);
            }
            return false;
        }
    }

}
