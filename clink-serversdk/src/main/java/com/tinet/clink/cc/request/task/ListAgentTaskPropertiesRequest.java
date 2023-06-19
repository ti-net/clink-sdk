package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.task.ListAgentTaskPropertiesResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;


/**
 * 获取座席外呼任务列表
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class ListAgentTaskPropertiesRequest extends AbstractRequestModel<ListAgentTaskPropertiesResponse> {

    /**
     * 座席工号
     */
    private String cno;

    public ListAgentTaskPropertiesRequest() {
        super(PathEnum.ListAgentTaskProperties.value(), HttpMethodType.GET);
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

    @Override
    public Class<ListAgentTaskPropertiesResponse> getResponseClass() {
        return ListAgentTaskPropertiesResponse.class;
    }

}
