package com.tinet.clink.openapi.model;

/**
 * 外呼任务详情创建model
 *
 * @author: wangpw
 * @date: 2022/5/17
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateTaskPropertyResponseModel {
    /**
     * 成功数
     */
    private Integer successCount;

    /**
     * 非法数
     */
    private Integer invalidCount;

    /**
     * 重复数
     */
    private Integer duplicateCount;

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(Integer invalidCount) {
        this.invalidCount = invalidCount;
    }

    public Integer getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(Integer duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    @Override
    public String toString() {
        return "TaskPropertyCreateResponseModel{" +
                "successCount=" + successCount +
                ", invalidCount=" + invalidCount +
                ", duplicateCount=" + duplicateCount +
                '}';
    }
}
