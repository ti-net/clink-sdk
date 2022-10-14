package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.ListFieldsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * 获取工单字段列表请求
 *
 * @author wangli
 * @date 2022-08-09 3:51 下午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListFieldsRequest extends AbstractRequestModel<ListFieldsResponse> {

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
