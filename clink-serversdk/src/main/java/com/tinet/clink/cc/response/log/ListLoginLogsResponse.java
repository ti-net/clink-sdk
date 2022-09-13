package com.tinet.clink.cc.response.log;

import com.tinet.clink.cc.model.LoginLogModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询登录日志列表响应
 *
 * @author wangli
 * @date 2022-03-10 5:41 PM
 */
public class ListLoginLogsResponse extends PagedResponse {

    /**
     * 登录日志列表
     */
    List<LoginLogModel> loginLogs;

    public List<LoginLogModel> getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(List<LoginLogModel> loginLogs) {
        this.loginLogs = loginLogs;
    }

}
