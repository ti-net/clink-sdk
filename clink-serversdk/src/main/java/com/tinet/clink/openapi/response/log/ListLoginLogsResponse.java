package com.tinet.clink.openapi.response.log;

import com.tinet.clink.openapi.model.LoginLogModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询登录日志列表响应
 *
 * @author wangli
 * @date 2022-03-10 5:41 PM
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListLoginLogsResponse extends PagedResponse {

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
