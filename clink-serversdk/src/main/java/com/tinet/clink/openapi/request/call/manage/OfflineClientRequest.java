package com.tinet.clink.openapi.request.call.manage;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.manage.OfflineClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 置闲请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  OfflineClientRequest extends AbstractRequestModel<OfflineClientResponse> {

    /**
     * 被操作的座席号
     */
    private String cno;

    public OfflineClientRequest() {
        super(PathEnum.OfflineClient.value(), HttpMethodType.POST);
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    @Override
    public Class<OfflineClientResponse> getResponseClass() {
        return OfflineClientResponse.class;
    }
}
