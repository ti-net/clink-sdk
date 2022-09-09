package com.tinet.clink.cc.request.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.ClidArea;
import com.tinet.clink.openapi.model.ClientPermission;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.UpdateClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * 座席更新请求
 *
 * @author lizy
 * @date 2018/10/12
 */
public class UpdateClientRequest extends AbstractRequestModel<UpdateClientResponse> {
    /**
     * 座席号
     */

    private String cno;

    /**
     * 座席名称
     */
    private String name;

    /**
     * 座席区号
     */
    private String areaCode;

    /**
     * 座席密码
     */
    private String password;

    /**
     * 座席角色，0普通座席 1班长座席。默认普通座席
     */
    private Integer role;

    /**
     * 启用状态 0停用 1启用。默认启用状态
     */
    private Integer active;

    /**
     * 队列号数组
     */
    private String[] qnos;

    /**
     * 号码隐藏类型，0不隐藏 1全局。默认不隐藏
     */
    private Integer hiddenTel;

    /**
     * 整理时长，座席进行外呼操作后的整理时间，取值范围3 ~ 300秒
     */
    private Integer wrapupTime;

    /**
     * crmId crm编号，与第三方CRM系统对接时，可作为唯一标识
     */
    private String crmId;

    /**
     * 外显号码类型，0-全部,1-本地优先,2-座席指定,3-智能外显
     */
    private Integer clidType;

    /**
     * 外显号码
     */
    private String[] clid;

    /**
     * 外显区号
     */
    private List<ClidArea> clidArea;

    /**
     * 外显规则，
     * <p>
     * <p>
     * （全部）1:按次轮选；2: 按天轮选。
     * （智能外显）1:动态；2: 自定义
     */
    private Integer clidRule;

    /**
     * 轮选方式，1查询队列详情轮选 2按天轮选。当外显规则为轮选时为必选项
     */
    private Integer recurrentselectionType;

    /**
     * 轮选值 1-30
     */
    private Integer recurrentselectionValue;

    /**
     * 座席权限设置
     */
    private ClientPermission permission;

    /**
     * 在线客服座席会话上限开关，0：关闭、1：开启
     */
    private Integer chatLimit;

    /**
     * 在线客服座席会话上限
     */
    private Integer chatLimitNum;

    public Integer getChatLimit() {
        return chatLimit;
    }

    public void setChatLimit(Integer chatLimit) {
        this.chatLimit = chatLimit;
        if (chatLimit != null) {
            putBodyParameter("chatLimit", chatLimit);
        }
    }

    public Integer getChatLimitNum() {
        return chatLimitNum;
    }

    public void setChatLimitNum(Integer chatLimitNum) {
        this.chatLimitNum = chatLimitNum;
        if (chatLimitNum != null) {
            putBodyParameter("chatLimitNum", chatLimitNum);
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

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        if (areaCode != null) {
            putBodyParameter("areaCode", areaCode);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        if (password != null) {
            putBodyParameter("password", password);
        }
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
        if (role != null) {
            putBodyParameter("role", role);
        }
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
        if (active != null) {
            putBodyParameter("active", active);
        }
    }

    public String[] getQnos() {
        return qnos;
    }

    public void setQnos(String[] qnos) {
        this.qnos = qnos;
        if (qnos != null) {
            putBodyParameter("qnos", qnos);
        }
    }

    public Integer getHiddenTel() {
        return hiddenTel;
    }

    public void setHiddenTel(Integer hiddenTel) {
        this.hiddenTel = hiddenTel;
        if (hiddenTel != null) {
            putBodyParameter("hiddenTel", hiddenTel);
        }
    }

    public Integer getWrapupTime() {
        return wrapupTime;
    }

    public void setWrapupTime(Integer wrapupTime) {
        this.wrapupTime = wrapupTime;
        if (wrapupTime != null) {
            putBodyParameter("wrapupTime", wrapupTime);
        }
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
        if (crmId != null) {
            putBodyParameter("crmId", crmId);
        }
    }

    public Integer getClidType() {
        return clidType;
    }

    public void setClidType(Integer clidType) {
        this.clidType = clidType;
        if (clidType != null) {
            putBodyParameter("clidType", clidType);
        }
    }

    public String[] getClid() {
        return clid;
    }

    public void setClid(String[] clid) {
        this.clid = clid;
        if (clid != null) {
            putBodyParameter("clid", clid);
        }
    }

    public List<ClidArea> getClidArea() {
        return clidArea;
    }

    public void setClidArea(List<ClidArea> clidArea) {
        this.clidArea = clidArea;
        if (clidArea != null) {
            putBodyParameter("clidArea", clidArea);
        }
    }

    public Integer getClidRule() {
        return clidRule;
    }

    public void setClidRule(Integer clidRule) {
        this.clidRule = clidRule;
        if (clidRule != null) {
            putBodyParameter("clidRule", clidRule);
        }
    }

    public Integer getRecurrentselectionType() {
        return recurrentselectionType;
    }

    public void setRecurrentselectionType(Integer recurrentselectionType) {
        this.recurrentselectionType = recurrentselectionType;
        if (recurrentselectionType != null) {
            putBodyParameter("recurrentselectionType", recurrentselectionType);
        }
    }

    public Integer getRecurrentselectionValue() {
        return recurrentselectionValue;
    }

    public void setRecurrentselectionValue(Integer recurrentselectionValue) {
        this.recurrentselectionValue = recurrentselectionValue;
        if (recurrentselectionValue != null) {
            putBodyParameter("recurrentselectionValue", recurrentselectionValue);
        }
    }

    public ClientPermission getPermission() {
        return permission;
    }

    public void setPermission(ClientPermission permission) {
        this.permission = permission;
        if (permission != null) {
            putBodyParameter("permission", permission);
        }
    }

    public UpdateClientRequest() {
        super(PathEnum.UpdateClient.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateClientResponse> getResponseClass() {
        return UpdateClientResponse.class;
    }
}
