package com.tinet.oskit.view;

import androidx.annotation.StringRes;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetView
 * @Author: liuzr
 * @CreateDate: 2021-08-23 09:35
 * @Description:
 */
public interface TinetView {

    /**
     * 显示toast
     *
     * @param stringID 消息资源ID
     * @param isShort  显示时长标识
     */
    void showToast(@StringRes int stringID, boolean isShort);

    /**
     * 显示toast
     *
     * @param message 消息
     * @param isShort 显示时长标识
     */
    void showToast(String message, boolean isShort);

    /**
     * 显示toast
     *
     * @param stringID 消息资源ID
     * @param isShort  显示时长标识
     * @param message  可变参数
     */
    void showToast(@StringRes int stringID, boolean isShort, Object... message);

}
