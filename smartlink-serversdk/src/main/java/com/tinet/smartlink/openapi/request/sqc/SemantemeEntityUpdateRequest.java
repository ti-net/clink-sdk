package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.SemantemeEnumerateAddUpdate;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.SemantemeEntityBaseResponse;
import lombok.Data;

import java.util.List;

/**
 * @author : chen.wang
 * @description : 语义实体更新请求对象
 * @date : 2023/5/26
 **/
@Data
public class SemantemeEntityUpdateRequest  extends BaseRequest<SemantemeEntityBaseResponse> {

    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 实体id
     */
    private Long entityId;

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
     * 更新枚举词list
     */
    private List<SemantemeEnumerateAddUpdate> semantemeEnumerateAddUpdates;


    public SemantemeEntityUpdateRequest() {
        super("/sqc/semanteme/entity/update", HttpMethodType.POST);
    }

    @Override
    public Class<SemantemeEntityBaseResponse> getResponseClass() {
        return SemantemeEntityBaseResponse.class;
    }
}
