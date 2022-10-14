package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.DeleteClientResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 删除座席请求
 *
 * @author lizy
 * @date 2018/10/11
 */
public class DeleteClientRequest extends AbstractRequestModel<DeleteClientResponse> {

    /**
     * 座席号
     */
    private String cno;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public DeleteClientRequest() {
        super(PathEnum.DeleteClient.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteClientResponse> getResponseClass() {
        return DeleteClientResponse.class;
    }
}
