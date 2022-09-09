package com.tinet.clink.cc.model;

/**
 * 添加客户资料数据模型
 *
 * @author wangtao
 * @date 2018/12/10
 */
public class CustomerModel {

    /**
     * 客户资料id
     */
    private Integer id;

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
     * 自定义字段
     */
    private IdValue[] customize;


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

    public String[] getTel() {
        return tel;
    }

    public void setTel(String[] tel) {
        this.tel = tel;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IdValue[] getCustomize() {
        return customize;
    }

    public void setCustomize(IdValue[] customize) {
        this.customize = customize;
    }
}
