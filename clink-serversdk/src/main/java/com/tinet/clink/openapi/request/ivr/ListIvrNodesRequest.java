package com.tinet.clink.openapi.request.ivr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ivr.ListIvrNodesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询ivr节点列表请求
 *
 * @author huwk
 * @date 2018/11/15
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListIvrNodesRequest extends AbstractRequestModel<ListIvrNodesResponse> {

    /**
     * 语音导航id
     */
    private Integer ivrId;

    public ListIvrNodesRequest() {
        super(PathEnum.ListIvrNodes.value(), HttpMethodType.GET);
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
        if (ivrId != null) {
            putQueryParameter("ivrId", ivrId);
        }
    }

    @Override
    public Class<ListIvrNodesResponse> getResponseClass() {
        return ListIvrNodesResponse.class;
    }
}
