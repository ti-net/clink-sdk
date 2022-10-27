package com.tinet.clink.openapi.response.ivr;

import com.tinet.clink.openapi.model.IvrModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询语音导航列表响应
 *
 * @author huwk
 * @date 2018/11/15
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListIvrsResponse extends ResponseModel {

    /**
     * ivr对象列表
     */
    private List<IvrModel> ivrs;

    public List<IvrModel> getIvrs() {
        return ivrs;
    }

    public void setIvrs(List<IvrModel> ivrs) {
        this.ivrs = ivrs;
    }
}
