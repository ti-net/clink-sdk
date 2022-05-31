package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author wangli
 * @date 2022-05-30 7:17 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientObClidModel {

    /**
     * 外显号码类型，0-全部,1-本地优先,2-座席指定,3-智能外显
     */
    private Integer obClidLeftRule;

    /**
     * 外显规则：1，随机 2，轮选
     */
    private Integer obClidChooseRule;

    /**
     * 外显号
     */
    private List<TrunkModel> obClids;

    public Integer getObClidLeftRule() {
        return obClidLeftRule;
    }

    public void setObClidLeftRule(Integer obClidLeftRule) {
        this.obClidLeftRule = obClidLeftRule;
    }

    public Integer getObClidChooseRule() {
        return obClidChooseRule;
    }

    public void setObClidChooseRule(Integer obClidChooseRule) {
        this.obClidChooseRule = obClidChooseRule;
    }

    public List<TrunkModel> getObClids() {
        return obClids;
    }

    public void setObClids(List<TrunkModel> obClids) {
        this.obClids = obClids;
    }
}
