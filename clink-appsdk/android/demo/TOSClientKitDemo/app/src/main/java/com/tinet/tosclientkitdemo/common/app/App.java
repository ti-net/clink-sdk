package com.tinet.tosclientkitdemo.common.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.tencent.bugly.crashreport.CrashReport;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.TImageLoader;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oskit.manager.TOSClientKitConfig;
import com.tinet.oskit.model.TTextPatternRule;
import com.tinet.oslib.common.PlatformDefine;
import com.tinet.oslib.config.TOSInitOption;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.tosclientkitdemo.BuildConfig;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.constants.PlatformDefaultInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: App
 * @Author: zhangping
 * @CreateDate: 2022/6/27 15:44
 * @Description: 描述说明
 */
public class App extends Application {

    public static int CHOOSE_THEME_INDEX = 0;


    /**
     * 匹配手机号正则
     */
    public static final String PATTERN_REGEX_PHONE_NUMBER = "1[345678]\\d{9}";

    /**
     * 匹配手机号MESSAGE_TYPE
     */
    public static final String TEXT_PATTERN_MESSAGE_TYPE_PHONE_NUMBER = "phoneNumber";

    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {
            //初始化日志上报
            CrashReport.initCrashReport(getApplicationContext(), "ebb6b84a11", false);
        }

        initTOSSDK();
    }

    private void initTOSSDK() {
        //PlantformInfo与接入无关，接入时直接申请：accessId，accessSecret，enterpriseId填入即可
        PlantformInfo info = PlantformUtil.getPlantform(this);
        String accessId = info == null ? PlatformDefaultInfo.accessId : info.getAccessId();
        String accessSecret = info == null ? PlatformDefaultInfo.accessSecret : info.getAccessSecret();
        long enterpriseId = info == null ? PlatformDefaultInfo.enterpriseId : info.getEnterpriseId();
        PlatformDefine define = info == null ? PlatformDefaultInfo.define : info.getPlatform();

        TOSInitOption tOSInitOption = new TOSInitOption();
        tOSInitOption.setAccessId(accessId);
        tOSInitOption.setAccessSecret(accessSecret);
        tOSInitOption.setEnterpriseId(enterpriseId);
        tOSInitOption.setApiUrl(define.getApiUrl());
        tOSInitOption.setOnlineUrl(define.getOnlineUrl());
        tOSInitOption.setDebug(true);

        //此处添加环境标识参数等可配参数
        if (info != null)
            if ("Kt".equals(info.getType())) {
                Map<String, Object> headers = new HashMap<>();
                headers.put("deBugEnv", "ktTest");
                tOSInitOption.setAdvanceParams(headers);
            }
        if (info == null && PlatformDefaultInfo.define == PlatformDefine.Kt) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("deBugEnv", "ktTest");
            tOSInitOption.setAdvanceParams(headers);
        }

        // : 2022/7/21 设置快捷入口
        quickEnter(true);

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

        // : 2022/10/13 配置超链接高亮显示规则
        List<TTextPatternRule> tTextPatternRules = new ArrayList<>();
        tTextPatternRules.add(new TTextPatternRule(Pattern.compile(PATTERN_REGEX_PHONE_NUMBER, Pattern.CASE_INSENSITIVE),Color.parseColor("#1366dc"),TEXT_PATTERN_MESSAGE_TYPE_PHONE_NUMBER));

        TOSClientKitConfig tosClientKitConfig = new TOSClientKitConfig.Builder()
//                .setTCustomizationUI(tCustomizationUI)//配置自定义UI
                .setTextHighLightRuleList(tTextPatternRules)
                .build();

        TOSClientKit.setTosClientKitConfig(tosClientKitConfig);

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

    /**
     * 快捷入口测试数据
     *
     * @param enable
     */
    private void quickEnter(boolean enable) {
        if (enable) {
            ArrayList message = new ArrayList<>();
            message.add(new LabeInfo("订单号", "1234567890"));
            message.add(new LabeInfo("服务地区", "北京市"));
            message.add(new LabeInfo("服务", "满意"));
            message.add(new LabeInfo("师傅", "金师傅"));
            message.add(new LabeInfo("产品类型", "电子产品"));
            message.add(new LabeInfo("师傅电话", "12345678900"));
            message.add(new LabeInfo("订单状态", "已完成"));
            TOSClientKit.updateSessionWindowQuickEntrys(message);
        } else {
            TOSClientKit.clearSessionWindowQuickEntrys();
        }
    }
}
