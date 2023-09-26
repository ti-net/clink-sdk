package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class for:
 *
 * @author yinzk
 * @date 2020/8/24
 */
@Data
@JsonIgnoreProperties
public class PortraitDetail {
    /**
     * 画像细节(是否为人为新增,0:false,1:true)
     */
    private Integer added = 0;
    /**
     * 画像细节(标记该标签的话单id集合)
     */
    private Set<String> cdrIds = new LinkedHashSet<>();
    /**
     * 画像细节(是否被删除,0:false,1:true)
     */
    private Integer deleted = 0;
    /**
     * 画像细节(画像标记操作细节，暂不适用)
     */
    private String message;
    /**
     * 画像细节(画像名称)
     */
    private String name;
    /**
     * 画像细节(画像组名，customerPortraitType.name)
     */
    private String portraitTypeName;
    /**
     * 画像细节(质检类型，0：质检追加，1：人为追加)
     */
    private Short qcType = 0;
    /**
     * 画像细节(话术开始时间)
     */
    private Long startTime;
}

