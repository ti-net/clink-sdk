package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 留言模板响应
 */
public class GetLeaveMessageTemplateResponse extends ResponseModel {

    /**
     * 留言模板
     */
    private Object template;


    public Object getTemplate() {
        return template;
    }

    public void setTemplate(Object template) {
        this.template = template;
    }

}
