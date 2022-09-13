package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.ListFieldsResponse;

import java.util.List;

/**
 * 获取工单字段列表请求
 *
 * @author wangli
 * @date 2022-08-09 3:51 下午
 */
public class ListFieldsRequest extends AbstractRequestModel<ListFieldsResponse> {

    /**
     * 字段ID集合
     */
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
        if (ids != null) {
            putBodyParameter("ids", ids);
        }
    }

    public ListFieldsRequest() {
        super(PathEnum.ListTicketFields.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListFieldsResponse> getResponseClass() {
        return ListFieldsResponse.class;
    }

}
