package com.tinet.clink.livechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author wangli
 * @date 2022-05-30 7:17 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientObClidModel {

    /**
     * 外显号
     */
    private List<String> clid;

    /**
     * 外显类型
     */
    private Integer clidType;

    /**
     * 外显号配置
     */
    private List<ClidArea> clidArea;

    /**
     * 外显规则
     */
    private Integer clidRule;

    /**
     * 轮选类型
     */
    private Integer recurrentselectionType;

    /**
     * 轮选值
     */
    private Integer recurrentselectionValue;

    public List<String> getClid() {
        return clid;
    }

    public void setClid(List<String> clid) {
        this.clid = clid;
    }

    public List<ClidArea> getClidArea() {
        return clidArea;
    }

    public void setClidArea(List<ClidArea> clidArea) {
        this.clidArea = clidArea;
    }

    public Integer getClidRule() {
        return clidRule;
    }

    public void setClidRule(Integer clidRule) {
        this.clidRule = clidRule;
    }

    public Integer getRecurrentselectionType() {
        return recurrentselectionType;
    }

    public void setRecurrentselectionType(Integer recurrentselectionType) {
        this.recurrentselectionType = recurrentselectionType;
    }

    public Integer getRecurrentselectionValue() {
        return recurrentselectionValue;
    }

    public void setRecurrentselectionValue(Integer recurrentselectionValue) {
        this.recurrentselectionValue = recurrentselectionValue;
    }

    public Integer getClidType() {
        return clidType;
    }

    public void setClidType(Integer clidType) {
        this.clidType = clidType;
    }
}
