package com.tinet.clink.crm.request.customer;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.model.IdValue;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.customer.UpdateCustomerResponse;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class UpdateCustomerRequest extends AbstractRequestModel<UpdateCustomerResponse> {


    /**
     * 客户资料id
     */
    private Integer id;

    /**
     * 访客标识
     */
    private String visitorId;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 电话
     */
    private String[] tel;

    /**
     * 客户等级，0：普通、1：VIP
     */
    private Integer level;

    /**
     * 归属类型，0：全体共享、1：队列共享、2：座席私有、3：无归属
     */
    private Integer shareType;

    /**
     * 归属
     */
    private Integer share;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 地址
     */
    private String address;

    /**
     * 外部id(第三方平台id)
     */
    private String externalId;

    /**
     * 自定义字段
     */
    private IdValue[] customize;

    /**
     * 更新方式  0: 更新/ 1: 可覆盖
     */
    private Integer renovate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        if (name != null) {
            putBodyParameter("name", name);
        }
    }

    public String[] getTel() {
        return tel;
    }

    public void setTel(String[] tel) {
        this.tel = tel;

        if (tel != null) {
            putBodyParameter("tel", tel);
        }

    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;

        if (level != null) {
            putBodyParameter("level", level);
        }
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;

        if (shareType != null) {
            putBodyParameter("shareType", shareType);
        }
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;

        if (share != null) {
            putBodyParameter("share", share);
        }
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;

        if (sex != null) {
            putBodyParameter("sex", sex);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        if (email != null) {
            putBodyParameter("email", email);
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;

        if (remark != null) {
            putBodyParameter("remark", remark);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

        if (address != null) {
            putBodyParameter("address", address);
        }
    }

    public IdValue[] getCustomize() {
        return customize;
    }

    public void setCustomize(IdValue[] customize) {
        this.customize = customize;

        if (customize != null) {
            putBodyParameter("customize", customize);
        }
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;

        if (externalId != null) {
            putBodyParameter("externalId", externalId);
        }
    }

    public Integer getRenovate() {
        return renovate;
    }

    public void setRenovate(Integer renovate) {
        this.renovate = renovate;

        if (renovate != null) {
            putBodyParameter("renovate", renovate);
        }
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public UpdateCustomerRequest() {
        super(PathEnum.UpdateCustomer.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateCustomerResponse> getResponseClass() {
        return UpdateCustomerResponse.class;
    }
}
