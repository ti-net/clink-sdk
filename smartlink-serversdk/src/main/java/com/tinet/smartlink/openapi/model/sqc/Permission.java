package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * 功能权限资源
 *
 * @author 王大宝
 * @date 2019/04/23
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission {


    /**
     * 主键id
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识 唯一
     */
    private String key;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
