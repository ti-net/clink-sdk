package com.tinet.smartlink.openapi.request.sqc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushProductCommentResponse;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PushProductCommentRequest extends BaseRequest<PushProductCommentResponse> {
    private String accountLoginName;

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
        putBodyParameter("accountLoginName", accountLoginName);
    }

    private String enterpriseId;

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
        putBodyParameter("enterpriseId", enterpriseId);
    }

    // 商品id
    private String productId;

    public void setProductId(String productId) {
        this.productId = productId;
        putBodyParameter("productId", productId);
    }

    // 商品名称
    private String productName;

    public void setProductName(String productName) {
        this.productName = productName;
        putBodyParameter("productName", productName);
    }

    // 商品类型
    private String productType;

    public void setProductType(String productType) {
        this.productType = productType;
        putBodyParameter("productType", productType);
    }

    // 生产商
    private String producerId;

    public void setProducerId(String producerId) {
        this.producerId = producerId;
        putBodyParameter("producerId", producerId);
    }

    // 生产商名称
    private String producerName;

    public void setProducerName(String producerName) {
        this.producerName = producerName;
        putBodyParameter("producerName", producerName);
    }

    /**
     * 评价类型
     * 0：普通评价；
     * 1：追评；
     * 默认为0
     */
    private Integer commentType = 0;

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
        putBodyParameter("commentType", commentType);
    }

    // 内容
    private String commentContent;

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
        putBodyParameter("commentContent", commentContent);
    }

    // 评价时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentTime;

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
        putBodyParameter("commentTime", commentTime);
    }

    // 用户id
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
        putBodyParameter("userId", userId);
    }
    /**
     * 自定义数据
     * 日期时间格式：yyyy-MM-dd HH:mm:ss
     * 日期格式：yyyy-MM-dd
     */
    private Map<String,Object> customData;

    public void setCustomData(Map<String,Object> customData) {
        this.customData = customData;
        putBodyParameter("customData", customData);
    }
    public Map<String,Object> getCustomData() {
        return customData;
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public String getProducerId() {
        return producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public String getUserId() {
        return userId;
    }

    public PushProductCommentRequest() {
        super("/sqc/productComment/push", HttpMethodType.POST);
    }

    @Override
    public Class<PushProductCommentResponse> getResponseClass() {
        return PushProductCommentResponse.class;
    }
}
