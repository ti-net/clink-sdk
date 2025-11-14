package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.model.sqc.SemantemeEnumerateDetail;
import lombok.Data;

import java.util.List;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/29
 **/
@Data
public class SemantemeEntityDetailResponse{

    /**
     * 实体id
     */
    private String entityId;

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
     * 枚举词list
     */
    private List<SemantemeEnumerateDetail> semantemeEnumerateDetails;
}
