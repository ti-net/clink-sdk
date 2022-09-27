package com.tinet.clink.openapi.model;


/**
 * 获取企业可用于查询客户资料的字段
 *
 * @author jiangyang
 * @date 2019/11/12
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  BusinessCustomizeFields {

    /**
     * 查询字段id
     */
    private Integer id;

    /**
     * 查询字段对应的中文name
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
