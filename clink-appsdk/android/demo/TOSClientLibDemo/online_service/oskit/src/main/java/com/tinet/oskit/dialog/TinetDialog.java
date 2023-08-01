package com.tinet.oskit.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import com.tinet.oskit.R;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetDialog
 * @Author: liuzr
 * @CreateDate: 2021-09-09 13:22
 * @Description: 对话框基类
 */
public abstract class TinetDialog extends AppCompatDialogFragment implements DialogInterface.OnShowListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NO_TITLE, R.style.tinetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        initView(view);
        return view;
    }

    /**
     * 布局id
     */
    abstract int layoutId();

    /**
     * 初始化视图
     */
    abstract void initView(View view);

    void show(FragmentManager manager) {
        super.show(manager, this.getClass().getName());
    }

    @Override
    public void onShow(DialogInterface dialog) {

    }

    @Override
    public void onStart() {
        super.onStart();


        Window win = getDialog().getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        switch (location()) {
            case top:
                params.gravity = Gravity.TOP;
                break;
            case center:
                params.gravity = Gravity.CENTER;
                break;
            case bottom:
                params.gravity = Gravity.BOTTOM;
                break;
        }

        if (isWidthMatchParent()) {
            params.width = MATCH_PARENT;
        } else {
            params.width = WRAP_CONTENT;
        }

        if (isHeightMatchParent()) {
            params.height = MATCH_PARENT;
        } else {
            params.height = WRAP_CONTENT;
        }

        win.setAttributes(params);
    }

    protected boolean isWidthMatchParent() {
        return true;
    }

    protected boolean isHeightMatchParent() {
        return false;
    }

    protected DialogLocation location() {
        return DialogLocation.center;
    }

    /**
     * 对话框位置
     */
    public enum DialogLocation {
        /**
         * 显示在顶部
         * 自带从顶部滑入动画
         */
        top,

        /**
         * 显示在中间
         * 无动画
         */
        center,

        /**
         * 显示在底部
         * 自带从底部滑入动画
         */
        bottom
    }

}
