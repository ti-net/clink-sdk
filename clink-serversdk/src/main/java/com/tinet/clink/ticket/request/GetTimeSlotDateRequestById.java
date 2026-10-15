package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.GetTicketDetailResponse;
import com.tinet.clink.ticket.response.GetTimeSlotDateResponse;

import java.util.List;

/**
 * 获取工单详情
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class GetTimeSlotDateRequestById extends AbstractRequestModel<GetTimeSlotDateResponse> {



    private Integer formId;

    private Integer fieldId;

    private Integer skillId;

    private Integer timeSlotCount;

    private List<String> timeSlotDates;

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

    public Integer getTimeSlotCount() {
        return timeSlotCount;
    }

    public void setTimeSlotCount(Integer timeSlotCount) {
        this.timeSlotCount = timeSlotCount;
        if (timeSlotCount != null) {
            putBodyParameter("timeSlotCount", timeSlotCount);
        }
    }

    public List<String> getTimeSlotDates() {
        return timeSlotDates;
    }

    public void setTimeSlotDates(List<String> timeSlotDates) {
        this.timeSlotDates = timeSlotDates;
        if (timeSlotDates != null) {
            putBodyParameter("timeSlotDates", timeSlotDates);
        }
    }


    public GetTimeSlotDateRequestById() {
        super(PathEnum.GetTimeSlotOptionalDateById.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetTimeSlotDateResponse> getResponseClass() {
        return GetTimeSlotDateResponse.class;
    }
}
