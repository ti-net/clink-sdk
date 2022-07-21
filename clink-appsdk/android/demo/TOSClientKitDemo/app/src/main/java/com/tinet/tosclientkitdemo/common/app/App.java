package com.tinet.tosclientkitdemo.common.app;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tencent.bugly.crashreport.CrashReport;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.TImageLoader;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oslib.common.PlatformDefine;
import com.tinet.oslib.config.TOSInitOption;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.constants.PlatformDefaultInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformUtil;
import com.tinet.tosclientkitdemo.utils.TLogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化日志上报
        CrashReport.initCrashReport(getApplicationContext(), "ebb6b84a11", false);

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
                //以glide为示例
                Glide.with(imageView.getContext())
                        .load(uri)
                        .error(R.drawable.ti_ic_load_default_image)
                        .placeholder(R.drawable.ti_ic_load_default_image)
                        .into(imageView);
            }

            @Override
            public void loadImage(ImageView imageView, Object uri, int placeholderImg, int errorImg) {
                Glide.with(imageView.getContext())
                        .load(uri)
                        .override(CustomTarget.SIZE_ORIGINAL, CustomTarget.SIZE_ORIGINAL)
                        .error(errorImg)
                        .placeholder(placeholderImg)
                        .into(imageView);
            }

            @Override
            public void loadImage(ImageView imageView, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
                Glide.with(imageView.getContext()).load(uri)
                        .override(originalWidth, originalHeight)
                        .error(R.drawable.ti_ic_load_default_image)
                        .placeholder(R.drawable.ti_ic_load_default_image).into(imageView);

            }

            @Override
            public void loadImage(Context context, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
                Glide.with(context).load(uri).override(originalWidth, originalHeight).into(new CustomTarget<Drawable>() {
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
