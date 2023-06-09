package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.task.ListAgentTaskInventoriesResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 获取座席外呼任务明细列表 请求
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class ListAgentTaskInventoriesRequest extends AbstractRequestModel<ListAgentTaskInventoriesResponse> {

    /**
     * 座席工号
     */
    private String cno;

    public ListAgentTaskInventoriesRequest() {
        super(PathEnum.ListAgentTaskInventories.value(), HttpMethodType.GET);
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    @Override
    public Class<ListAgentTaskInventoriesResponse> getResponseClass() {
        return ListAgentTaskInventoriesResponse.class;
    }

}
