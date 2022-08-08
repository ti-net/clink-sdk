package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.CreateAnswerResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 创建答案请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class CreateAnswerRequest extends AbstractRequestModel<CreateAnswerResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准ID
     */
    private Integer sqId;
    /**
     * 答案内容
     */
    private String answer;
    /**
     * 属性
     */
    private Object property;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 日期类型; 0：无，1：每天
     */
    private Integer periodType;
    /**
     * 关联相关问ID集合
     */
    private String[] relatedSqIdList;
    /**
     * 解析器
     */
    private String[] parsers;
    /**
     * 标签ID集合
     */
    private Integer[] tagIdList;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
        if (sqId != null) {
            putBodyParameter("sqId", sqId);
        }
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        if (answer != null) {
            putBodyParameter("answer", answer);
        }
    }

    public Object getProperty() {
        return property;
    }

    public void setProperty(Object property) {
        this.property = property;
        if (property != null) {
            putBodyParameter("property", property);
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putBodyParameter("startTime", startTime);
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putBodyParameter("endTime", endTime);
        }
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
        if (periodType != null) {
            putBodyParameter("periodType", periodType);
        }
    }

    public String[] getRelatedSqIdList() {
        return relatedSqIdList;
    }

    public void setRelatedSqIdList(String[] relatedSqIdList) {
        this.relatedSqIdList = relatedSqIdList;
        if (relatedSqIdList != null) {
            putBodyParameter("relatedSqIdList", relatedSqIdList);
        }
    }

    public String[] getParsers() {
        return parsers;
    }

    public void setParsers(String[] parsers) {
        this.parsers = parsers;
        if (parsers != null) {
            putBodyParameter("parsers", parsers);
        }
    }

    public Integer[] getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(Integer[] tagIdList) {
        this.tagIdList = tagIdList;
        if (tagIdList != null) {
            putBodyParameter("tagIdList", tagIdList);
        }
    }

    public CreateAnswerRequest() {
        super(PathEnum.CreateAnswer.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateAnswerResponse> getResponseClass() {
        return CreateAnswerResponse.class;
    }
}
