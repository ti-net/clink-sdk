package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.AutoItem;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.ScoreCorrectionResponse;

import java.util.List;

/**
 * 获取通话质检详情
 * @author 王大宝
 * @date 2019/9/4
 */
public class ScoreCorrectionRequest extends BaseRequest<ScoreCorrectionResponse> {

    /**
     * 录音记录的唯一标识，uniqueid
     */
    private String uniqueId;
    /**
     * 企业id
     */
    private String userId;
    /**
     * 人工质检得分
     */
    private Double qcReviewScore;
    /**
     * 质检员
     */
    private String qcUser;
    /**
     * 质检项评分纠正集合
     */
    private List<AutoItem> autoItems;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putBodyParameter("uniqueId", uniqueId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public Double getQcReviewScore() {
        return qcReviewScore;
    }

    public void setQcReviewScore(Double qcReviewScore) {
        this.qcReviewScore = qcReviewScore;
        if (qcReviewScore != null) {
            putBodyParameter("qcReviewScore", qcReviewScore);
        }
    }

    public String getQcUser() {
        return qcUser;
    }

    public void setQcUser(String qcUser) {
        this.qcUser = qcUser;
        if (qcUser != null) {
            putBodyParameter("qcUser", qcUser);
        }
    }

    public List<AutoItem> getAutoItems() {
        return autoItems;
    }

    public void setAutoItems(List<AutoItem> autoItems) {
        this.autoItems = autoItems;
        if (autoItems != null) {
            putBodyParameter("autoItems", autoItems);
        }
    }

    public ScoreCorrectionRequest() {
        super("/sqc/saveScoreCorrection", HttpMethodType.POST);
    }

    @Override
    public Class<ScoreCorrectionResponse> getResponseClass() {
        return ScoreCorrectionResponse.class;
    }
}
