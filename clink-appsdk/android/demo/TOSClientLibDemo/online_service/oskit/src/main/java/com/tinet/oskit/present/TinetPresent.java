package com.tinet.oskit.present;

import com.tinet.oskit.view.TinetView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetPresent
 * @Author: liuzr
 * @CreateDate: 2021-08-23 09:35
 * @Description:
 */
public class TinetPresent<T extends TinetView> {

    /**
     * 默认加载会话消息个数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    protected T view;

    public TinetPresent(T view) {
        this.view = view;
    }
}
