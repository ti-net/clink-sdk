package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.model.sqc.SemantemeEnumerateAddUpdate;
import com.tinet.smartlink.openapi.response.sqc.SemantemeEntityBaseResponse;
import lombok.Data;

import java.util.List;

/**
 * @author : chen.wang
 * @description : 语义实体新增请求对象
 * @date : 2023/5/26
 **/
@Data
public class SemantemeEntityAddRequest extends BaseRequest<SemantemeEntityBaseResponse> {

    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 实体参数名
     */
    private String entityCode;

    /**
     * 实体描述
     */
    private String description;

    /**
     * 实体正则
     */
    private String entityRegex;

    /**
     * 新增枚举词list
     */
    private List<SemantemeEnumerateAddUpdate> semantemeEnumerateAddUpdates;


    public SemantemeEntityAddRequest() {
        super("/sqc/semanteme/entity/add", HttpMethodType.POST);
    }

    @Override
    public Class<SemantemeEntityBaseResponse> getResponseClass() {
        return SemantemeEntityBaseResponse.class;
    }
}
