package com.tinet.clink.openapi.request.monitor;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.constant.QueueStatusScope;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.monitor.QueueStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询队列状态请求对象
 *
 * @author wangll
 * @date 2019/11/20
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  QueueStatusRequest extends AbstractRequestModel<QueueStatusResponse> {

    /**
     * 队列号数组
     */
    private String[] qnos;

    /**
     * 返回值范围
     * all:全部返回，queueParams：队列参数；agentStatus：队列成员；queueEntries:排队成员
     */
    private String statusScope = QueueStatusScope.STATUS_SCOPE_ALL;

    public QueueStatusRequest() {
        super(PathEnum.QueueStatus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<QueueStatusResponse> getResponseClass() {
        return QueueStatusResponse.class;
    }

    public String[] getQnos() {
        return qnos;
    }

    public void setQnos(String[] qnos) {
        this.qnos = qnos;
        if (qnos != null) {
            for (int i = 0; i < qnos.length; i++) {
                putQueryParameter("qnos[" + i + "]", qnos[i]);
            }
        }
    }

    public String getStatusScope() {
        return statusScope;
    }

    public void setStatusScope(String statusScope) {
        this.statusScope = statusScope;
        if (statusScope != null) {
            putQueryParameter("statusScope", statusScope);
        }
    }
}
