package com.tinet.clink.cc.model;

/**
 * Class For:
 * 智能助手 - 知识推荐
 *
 * @author Tinet-yinzk
 * @date 2024/3/19 15:16
 */
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantQcResult {
    /**
     * 质检项
     */
    private String qcItem;
    /**
     * 检测结果
     */
    private String result;

    public String getQcItem() {
        return qcItem;
    }

    public void setQcItem(String qcItem) {
        this.qcItem = qcItem;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
