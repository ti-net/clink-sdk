package com.tinet.clink.openapi.request.monitor;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.monitor.AgentStatusDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询座席状态详情
 *
 * @author yinzk
 * @date 202/2/9
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  AgentStatusDetailRequest extends AbstractRequestModel<AgentStatusDetailResponse> {

    /**
     * 队列号数组
     */
    private String cno;

    public AgentStatusDetailRequest() {
        super(PathEnum.AgentStatusDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<AgentStatusDetailResponse> getResponseClass() {
        return AgentStatusDetailResponse.class;
    }


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            putQueryParameter("cno", cno);
        }
    }
}
