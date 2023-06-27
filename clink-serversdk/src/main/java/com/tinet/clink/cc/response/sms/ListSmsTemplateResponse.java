package com.tinet.clink.cc.response.sms;

import com.tinet.clink.cc.model.SmsTemplateModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 短信模板列表查询相应
 *
 * @Author liyj
 * @Date 2023-06-27 15:49
 **/
public class ListSmsTemplateResponse extends PagedResponse {

    List<SmsTemplateModel> smsTemplates;

    public List<SmsTemplateModel> getSmsTemplates() {
        return smsTemplates;
    }

    public void setSmsTemplates(List<SmsTemplateModel> smsTemplates) {
        this.smsTemplates = smsTemplates;
    }
}
