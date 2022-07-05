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
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.constants.PlatformDefaultInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformUtil;

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
}
