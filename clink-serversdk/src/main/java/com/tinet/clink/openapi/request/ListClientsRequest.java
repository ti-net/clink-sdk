package com.tinet.clink.openapi.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.ListClientsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询座席列表请求
 *
 * @author lizy
 * @date 2018/10/29
 */
public class ListClientsRequest extends AbstractRequestModel<ListClientsResponse> {

    private String cno;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    public ListClientsRequest() {
        super(PathEnum.ListClients.value(), HttpMethodType.GET);
    }


    @Override
    public Class<ListClientsResponse> getResponseClass() {
        return ListClientsResponse.class;
    }

}
