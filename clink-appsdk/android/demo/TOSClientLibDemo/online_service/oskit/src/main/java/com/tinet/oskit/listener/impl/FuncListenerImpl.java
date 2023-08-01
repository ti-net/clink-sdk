package com.tinet.oskit.listener.impl;

import com.tinet.oskit.fragment.SessionFragment;
import com.tinet.oskit.listener.FuncListener;
import com.tinet.oskit.model.Function;

import static com.tinet.oskit.fragment.SessionFragment.REQUEST_CAMERA_PERMISSION;
import static com.tinet.oskit.fragment.SessionFragment.REQUEST_CAMERA_SHOOT_PERMISSION;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FuncListenerImpl
 * @Author: liuzr
 * @CreateDate: 2021-08-24 17:36
 * @Description:
 */
public class FuncListenerImpl implements FuncListener {

    private SessionFragment sessionFragment;

    public FuncListenerImpl(SessionFragment sessionFragment) {
        this.sessionFragment = sessionFragment;
    }

    @Override
    public final void onClick(Function info) {
        if (info.getType() == Function.TYPE_SYSTEM) {
            if (info.getTypeId() == Function.SEND_IMAGE) {
                sessionFragment.requestCameraPermission(REQUEST_CAMERA_PERMISSION);
            } else if (info.getTypeId() == Function.SEND_VIDEO) {
                sessionFragment.requestCameraPermission(REQUEST_CAMERA_SHOOT_PERMISSION);
            } else if (info.getTypeId() == Function.SEND_FILE) {
                sessionFragment.selectFile();
            } else if (info.getTypeId() == Function.CHAT_OVER) {
                sessionFragment.closeSession();
            } else if (info.getTypeId() == Function.TO_ONLINE) {
                sessionFragment.toOnline();
            } else {
                onFuncClick(info);
            }
        } else {
            onFuncClick(info);
        }
    }

    /**
     * 自定义实现此方法
     *
     * @param func 定义的方法
     */
    public void onFuncClick(Function func) {
    }

}
