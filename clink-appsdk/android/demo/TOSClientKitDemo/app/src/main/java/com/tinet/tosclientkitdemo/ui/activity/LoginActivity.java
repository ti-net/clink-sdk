package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.listener.TImageLoader;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oslib.common.PlatformDefine;
import com.tinet.oslib.config.TOSConnectOption;
import com.tinet.oslib.config.TOSInitOption;
import com.tinet.oslib.listener.OnlineConnectResultCallback;
import com.tinet.timclientlib.utils.TStringUtils;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;
import com.tinet.tosclientkitdemo.common.constants.PlatformDefaultInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformInfo;
import com.tinet.tosclientkitdemo.common.platform.PlantformUtil;
import com.tinet.tosclientkitdemo.utils.TLogUtils;
import com.tinet.tosclientkitdemo.utils.ToastUtils;
import com.tinet.tosclientkitdemo.widget.EditTextWithDelete;
import com.zp.customdialoglib.loading.ProgressDialogHandler;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditTextWithDelete etEnterpriseId;
    private EditTextWithDelete etAccessId;
    private EditTextWithDelete etAccessSecret;
    private EditTextWithDelete etVisitorId;
    private View tvEnter;
    private ImageView ivTogglePassword;
    private boolean isShowAccessSecret = false;

    final static int COUNTS = 4;// 点击次数
    final static long DURATION = 1000;// 规定有效时间
    long[] mHits = new long[COUNTS];


    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();

        findViewById(R.id.toolbar_back).setOnClickListener(this);

        ivTogglePassword = findViewById(R.id.iv_toggle_password);
        ivTogglePassword.setVisibility(View.GONE);
        ivTogglePassword.setOnClickListener(this);

        findViewById(R.id.iv_logo).setOnClickListener(this);

        tvEnter = findViewById(R.id.tv_enter);
        tvEnter.setOnClickListener(this);

        etEnterpriseId = findViewById(R.id.et_enterprise_id);
        etAccessId = findViewById(R.id.et_access_id);
        etAccessSecret = findViewById(R.id.et_access_secret);
        etVisitorId = findViewById(R.id.et_visitor_id);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputContent();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etEnterpriseId.addTextChangedListener(textWatcher);
        etAccessId.addTextChangedListener(textWatcher);
        etAccessSecret.addTextChangedListener(textWatcher);
        checkInputContent();
    }

    private void checkInputContent() {
        if (etEnterpriseId.getText().toString().length() > 0
                && etAccessId.getText().toString().length() > 0
                && etAccessSecret.getText().toString().length() > 0) {
            tvEnter.setEnabled(true);
            tvEnter.setAlpha(1F);
        } else {
            tvEnter.setEnabled(false);
            tvEnter.setAlpha(0.6F);
        }

        if (etAccessSecret.getText().toString().length() > 0) {
            ivTogglePassword.setVisibility(View.VISIBLE);
        } else {
            ivTogglePassword.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;
            case R.id.tv_enter:
                initTOSSDK();//根据填入参数重新初始化
                enterSession();//根据填入参数重新进入会话页面
                break;
            case R.id.iv_toggle_password:
                showAccessSecret();
                break;
            case R.id.iv_logo:
                continuousClick(COUNTS, DURATION);
                break;
        }
    }

    private void enterSession() {
        Map<String, Object> extraInfo = new HashMap<>();
        extraInfo.put("tinetAddress", "江苏南京");
        extraInfo.put("tinetSex", "男");
        extraInfo.put("tinetType", "用户端（安卓）");
        extraInfo.put("tinetAge", 36);

        // : 2022/6/23 改版后直接连接，无需点击登录按钮再连接
        TOSConnectOption tOSConnectOption = new TOSConnectOption();
        if (TStringUtils.isNotEmpty(etVisitorId.getText().toString())) {
            tOSConnectOption.setVisitorId(etVisitorId.getText().toString());
        }
        tOSConnectOption.setNickname("快速接入测试名称");
        tOSConnectOption.setHeadUrl("https://img2.baidu.com/it/u=1229468480,2938819374&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500");
        tOSConnectOption.setMobile("135xxxx9206");
        tOSConnectOption.setAdvanceParams(extraInfo);

        ProgressDialogHandler progressDialogHandler = new ProgressDialogHandler(LoginActivity.this, true);
        progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        TOSClientKit.connect(tOSConnectOption, new OnlineConnectResultCallback() {
            @Override
            public void onSuccess() {
                progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
                startActivity(new Intent(LoginActivity.this, SessionActivity.class));
            }

            @Override
            public void onError(int errorCode, String errorDesc) {
                TLogUtils.e(errorDesc);
                ToastUtils.showShortToast(LoginActivity.this, errorDesc);
                progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            }
        });
    }


    private void initTOSSDK() {
        //PlantformInfo与接入无关，接入时直接申请：accessId，accessSecret，enterpriseId填入即可
        PlantformInfo info = PlantformUtil.getPlantform(this);
        String accessId = etAccessId.getText().toString();
        String accessSecret = etAccessSecret.getText().toString();
        long enterpriseId = Long.valueOf(etEnterpriseId.getText().toString());
        PlatformDefine define = info == null ? PlatformDefaultInfo.define : info.getPlatform();


        TOSInitOption tOSInitOption = new TOSInitOption();
        tOSInitOption.setAccessId(accessId);
        tOSInitOption.setAccessSecret(accessSecret);
        tOSInitOption.setEnterpriseId(enterpriseId);
        tOSInitOption.setApiUrl(define.getApiUrl());
        tOSInitOption.setOnlineUrl(define.getOnlineUrl());
        tOSInitOption.setDebug(true);

        //  此处添加环境标识参数等可配参数
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

    public void showAccessSecret() {
        isShowAccessSecret = !isShowAccessSecret;
        if (isShowAccessSecret) {
            etAccessSecret.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etAccessSecret.setSelection(etAccessSecret.length());
            ivTogglePassword.setImageResource(R.mipmap.browse_off);
        } else {
            etAccessSecret.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etAccessSecret.setSelection(etAccessSecret.length());
            ivTogglePassword.setImageResource(R.mipmap.browse);
        }
    }


    private void continuousClick(int count, long time) {
        //每次点击时，数组向前移动一位
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //为数组最后一位赋值
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
            mHits = new long[COUNTS];//重新初始化数组
            etVisitorId.setVisibility(etVisitorId.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }
}