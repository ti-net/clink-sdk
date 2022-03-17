package com.tinet.clink.openapi.response.log;

import com.tinet.clink.openapi.model.OperationLogModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询座席操作日志列表响应
 *
 * @author wangli
 * @date 2022-03-09 5:09 PM
 */
public class ListOperationLogsResponse extends PagedResponse {

    /**
     * 座席操作日志列表
     */
    List<OperationLogModel> operationLogs;

    public List<OperationLogModel> getOperationLogs() {
        return operationLogs;
    }

    public void setOperationLogs(List<OperationLogModel> operationLogs) {
        this.operationLogs = operationLogs;
    }

}
