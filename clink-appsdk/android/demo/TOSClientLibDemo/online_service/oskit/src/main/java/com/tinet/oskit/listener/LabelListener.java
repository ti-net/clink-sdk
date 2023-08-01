package com.tinet.oskit.listener;


import com.tinet.oslib.model.bean.LabeInfo;

public interface LabelListener {

    /**
     * 快捷入口点击回调
     *
     * @param func 功能信息
     */
    void onClick(LabeInfo func);

}