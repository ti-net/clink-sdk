package com.tinet.oskit.listener;

import com.tinet.oskit.model.Function;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FuncListener
 * @Author: liuzr
 * @CreateDate: 2021-08-24 17:29
 * @Description: 更多功能监听
 */
public interface FuncListener {

    /**
     * 功能点击回调
     *
     * @param func 功能信息
     */
    void onClick(Function func);

}
