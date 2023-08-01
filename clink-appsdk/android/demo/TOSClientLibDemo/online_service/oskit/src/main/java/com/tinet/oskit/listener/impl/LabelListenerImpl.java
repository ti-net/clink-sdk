package com.tinet.oskit.listener.impl;

import com.tinet.oskit.fragment.SessionFragment;
import com.tinet.oskit.listener.LabelListener;
import com.tinet.oslib.model.bean.LabeInfo;

public class LabelListenerImpl implements LabelListener {

    private SessionFragment sessionFragment;

    public LabelListenerImpl(SessionFragment sessionFragment) {
        this.sessionFragment = sessionFragment;
    }

    @Override
    public final void onClick(LabeInfo info) {
        onLabelClick(info);
    }

    /**
     * 自定义实现此方法
     *
     * @param info 定义的方法
     */
    public void onLabelClick(LabeInfo info) {
    }

}