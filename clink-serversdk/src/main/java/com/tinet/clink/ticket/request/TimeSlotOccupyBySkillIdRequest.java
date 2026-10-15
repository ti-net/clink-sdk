package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.GetTimeSlotDateResponse;
import com.tinet.clink.ticket.response.TimeSlotOccupyBySkillIdResponse;

import java.util.List;

/**
 * 获取工单详情
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class TimeSlotOccupyBySkillIdRequest extends AbstractRequestModel<TimeSlotOccupyBySkillIdResponse> {



    private Integer formId;

    private Integer fieldId;

    private Integer skillId;

    private String timeSlotDate;

    private String startTime;

    private String endTime;



    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
        if (formId != null) {
            putBodyParameter("formId", formId);
        }
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
        if (fieldId != null) {
            putBodyParameter("fieldId", fieldId);
        }
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
        if (skillId != null) {
            putBodyParameter("skillId", skillId);
        }
    }

    public String getTimeSlotDate() {
        return timeSlotDate;
    }

    public void setTimeSlotDate(String timeSlotDate) {
        this.timeSlotDate = timeSlotDate;
        if (timeSlotDate != null) {
            putBodyParameter("timeSlotDate", timeSlotDate);
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

    public TimeSlotOccupyBySkillIdRequest() {
        super(PathEnum.TimeSlotOccupyBySkillId.value(), HttpMethodType.POST);
    }

    @Override
    public Class<TimeSlotOccupyBySkillIdResponse> getResponseClass() {
        return TimeSlotOccupyBySkillIdResponse.class;
    }
}
