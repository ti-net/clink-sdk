package com.tinet.clink.openapi.model;

/**
 * 获取客户资料可用查询条件的响应
 *
 * @author jiangyang
 * @date 2019/11/12
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CustomerSearchResponse {

    /**
     * 查询字段id
     */
    private Integer id;

    /**
     * 字段中文名
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

    @Override
    public String toString() {
        return "CustomerSearchResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
