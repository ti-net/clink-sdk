package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.Permission;
import com.tinet.smartlink.openapi.model.sqc.PlatformEnum;
import com.tinet.smartlink.openapi.model.sqc.ProductEnum;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.JumpUserCheckResponse;

import java.util.List;

/**
 * 创建录音文件转写任务的request
 * @author 王大宝
 */
public class JumpUserCheckRequest extends BaseRequest<JumpUserCheckResponse> {

    /**
     * 权限列表
     */
    private List<Permission> permissionList;
    /**
     * 企业id
     */
    private String enterpriseId;
    /**
     * 企业id集合
     */
    private List<String> enterpriseIdList;
    /**
     * 平台
     */
    private String platform;
    /**
     * 产品
     */
    private String product;
    /**
     * 用户名
     */
    private String username;
    /**
     * 该用户能查看列表中的座席的质检数据。（当集合中没有数据代表可以查看企业下所有座席的数据）
     */
    private List<String> conList;
    /**
     * 该用户能查看列表中队列号所对应的每个队列里的座席质检数据。（当集合中没有数据代表可以查看企业下所有座席的数据）
     */
    private List<String> qnoList;

    /**
     * 角色名称
     */
    private String role;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
        if (permissionList != null) {
            putBodyParameter("permissionList", permissionList);
        }
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
        if (enterpriseId != null) {
            putBodyParameter("enterpriseId", enterpriseId);
        }
    }

    public List<String> getEnterpriseIdList() {
        return enterpriseIdList;
    }

    public void setEnterpriseIdList(List<String> enterpriseIdList) {
        this.enterpriseIdList = enterpriseIdList;
        if (enterpriseIdList != null) {
            putBodyParameter("enterpriseIdList", enterpriseIdList);
        }
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platformEnum) {
        this.platform = platformEnum.getValue();
        if (platform != null) {
            putBodyParameter("platform", platform);
        }
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(ProductEnum productEnum) {
        this.product = productEnum.getValue();
        if (product != null) {
            putBodyParameter("product", product);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        if (username != null) {
            putBodyParameter("username", username);
        }
    }

    public List<String> getConList() {
        return conList;
    }

    public void setConList(List<String> conList) {
        this.conList = conList;
        if (conList != null) {
            putBodyParameter("conList", conList);
        }
    }

    public List<String> getQnoList() {
        return qnoList;
    }

    public void setQnoList(List<String> qnoList) {
        this.qnoList = qnoList;
        if (qnoList != null) {
            putBodyParameter("qnoList", qnoList);
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        if (role != null) {
            putBodyParameter("role", role);
        }
    }

    public JumpUserCheckRequest() {
        super("/sqc/jumpUser/check", HttpMethodType.POST);
    }

    @Override
    public Class getResponseClass() {
        return JumpUserCheckResponse.class;
    }
}
