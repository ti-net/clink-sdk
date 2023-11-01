package com.tinet.clink.crm.request.customer;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.model.IdValue;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.customer.CreateCustomerResponse;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class CreateCustomerRequest extends AbstractRequestModel<CreateCustomerResponse> {


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
     * 来源。24：坐席助手，30：接口创建
     */
    private Integer source;

    /**
     * 客户标签
     */
    private Integer[] labelIds;


    public Integer[] getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(Integer[] labelIds) {
        this.labelIds = labelIds;
        if (labelIds != null) {
            putBodyParameter("labelIds", labelIds);
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public CreateCustomerRequest() {
        super(PathEnum.CreateCustomer.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateCustomerResponse> getResponseClass() {
        return CreateCustomerResponse.class;
    }
}
