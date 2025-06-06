package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.model.ClidArea;
import com.tinet.clink.cc.model.ClientPermission;
import com.tinet.clink.cc.response.client.UpdateClientResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


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
     * 号码类型:
     * 0:号码,1:动态号码组
     */
    private Integer assignType;

    /**
     * 动态外呼组名称
     * dynamicTelGroupRule.name
     */
    private String dynamicTelGroupName;

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

    /**
     * 呼入整理类型，1：队列；2：座席。
     */
    private Integer ibWrapupType;

    /**
     * 呼入整理时长，客户来电座席接听，双方通话结束后座席的话后处理时长，
     * 此期间座席不接收新的客户来电。取值范围：0～3600秒。
     */
    private Integer ibWrapupTime;


    /**
     * 缺省号码类型
     * 0：号码 1：动态号码组 默认 0
     */
    private Integer obClidDefaultType;

    /**
     * 缺省外显号码
     */
    private String[] clidDefault;

    /**
     * 缺省动态外呼组id
     */
    private Integer dynamicTelGroupIdDefault;

    /**
     * 云号码外呼开关；0-关，1-开
     */
    private Integer cloudNumberEnabled;
    /**
     * 云号码模式 1-4 依次对应：工作号（实体卡）、工
     * 作号+XB模式、AXB两端呼、RTC+PSTN外呼：
     */
    private Integer[] cloudNumberModes;


    public Integer getObClidDefaultType() {
        return obClidDefaultType;
    }

    public void setObClidDefaultType(Integer obClidDefaultType) {
        this.obClidDefaultType = obClidDefaultType;
        if (obClidDefaultType != null) {
            putBodyParameter("obClidDefaultType", obClidDefaultType);
        }
    }

    public String[] getClidDefault() {
        return clidDefault;
    }

    public void setClidDefault(String[] clidDefault) {
        this.clidDefault = clidDefault;
        if (clidDefault != null) {
            putBodyParameter("clidDefault", clidDefault);
        }
    }

    public Integer getDynamicTelGroupIdDefault() {
        return dynamicTelGroupIdDefault;
    }

    public void setDynamicTelGroupIdDefault(Integer dynamicTelGroupIdDefault) {
        this.dynamicTelGroupIdDefault = dynamicTelGroupIdDefault;
        if (dynamicTelGroupIdDefault != null) {
            putBodyParameter("dynamicTelGroupIdDefault", dynamicTelGroupIdDefault);
        }
    }

    public Integer getIbWrapupType() {
        return ibWrapupType;
    }

    public void setIbWrapupType(Integer ibWrapupType) {
        this.ibWrapupType = ibWrapupType;
        if (ibWrapupType != null) {
            putBodyParameter("ibWrapupType", ibWrapupType);
        }
    }

    public Integer getIbWrapupTime() {
        return ibWrapupTime;
    }

    public void setIbWrapupTime(Integer ibWrapupTime) {
        this.ibWrapupTime = ibWrapupTime;
        if (ibWrapupTime != null) {
            putBodyParameter("ibWrapupTime", ibWrapupTime);
        }
    }

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

    public Integer getAssignType() {
        return assignType;
    }

    public void setAssignType(Integer assignType) {
        this.assignType = assignType;
        if (assignType != null) {
            putBodyParameter("assignType", assignType);
        }
    }

    public String getDynamicTelGroupName() {
        return dynamicTelGroupName;
    }

    public void setDynamicTelGroupName(String dynamicTelGroupName) {
        this.dynamicTelGroupName = dynamicTelGroupName;
        if (dynamicTelGroupName != null) {
            putBodyParameter("dynamicTelGroupName", dynamicTelGroupName);
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


    public Integer getCloudNumberEnabled() {
        return cloudNumberEnabled;
    }

    public void setCloudNumberEnabled(Integer cloudNumberEnabled) {
        this.cloudNumberEnabled = cloudNumberEnabled;
        if (cloudNumberEnabled != null) {
            putBodyParameter("cloudNumberEnabled", cloudNumberEnabled);
        }
    }

    public Integer[] getCloudNumberModes() {
        return cloudNumberModes;
    }

    public void setCloudNumberModes(Integer[] cloudNumberModes) {
        this.cloudNumberModes = cloudNumberModes;
        if (cloudNumberModes != null) {
            putBodyParameter("cloudNumberModes", cloudNumberModes);
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
