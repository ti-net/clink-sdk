package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushSqcConfigResponse;

import java.util.List;
import java.util.Map;

/**
 * class for:
 * 推送 数据库配置信息
 *
 * @author: yinzk
 * @date: 2019/06/11
 */
public class PushSqcConfigRequest extends BaseRequest<PushSqcConfigResponse> {
    /**
     * 账号名
     */
    private String accountLoginName;


    /**
     * 关键词映射
     */
    private Map<String, List<String>> keywordMap;

    /**
     * 转写校准规则
     */
    private Map<String, String> asrAdjustMap;

    /**
     * 标签
     */
    private List<String> tagList;

    /**
     * 同步操作：add,del
     */
    private String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
        if (operation  != null) {
            putBodyParameter("operation ", operation );
        }
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
            if (accountLoginName != null) {
                putBodyParameter("accountLoginName", accountLoginName);
            }
    }

    public Map<String, String> getAsrAdjustMap() {
        return asrAdjustMap;
    }

    public void setAsrAdjustMap(Map<String, String> asrAdjustMap) {
        this.asrAdjustMap = asrAdjustMap;
        if (asrAdjustMap != null) {
            putBodyParameter("asrAdjustMap", asrAdjustMap);
        }
    }

    public Map<String, List<String>> getKeywordMap() {
        return keywordMap;
    }

    public void setKeywordMap(Map<String, List<String>> keywordMap) {
        this.keywordMap = keywordMap;
        if (keywordMap != null) {
            putBodyParameter("keywordMap", keywordMap);
        }
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
        if (tagList != null) {
            putBodyParameter("tagList", tagList);
        }
    }

    public PushSqcConfigRequest() {
        super("/sqc/db_config", HttpMethodType.POST);
    }


    @Override
    public Class<PushSqcConfigResponse> getResponseClass() {
        return PushSqcConfigResponse.class;
    }
}
