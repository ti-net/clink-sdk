package com.tinet.clink.ticket.request.stat;


import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.List;

/**
 * @author Chenjf
 * @date 2020/2/24 15:40
 **/
public abstract class AbstractStatRequest<T extends ResponseModel> extends AbstractRequestModel<T> {

    /**
     * 同步日期 时间格式 yyyyMMdd
     */
    protected String date;

    /**
     * 需要展示的字段
     */
    protected List<String> fields;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if (date != null) {
            putQueryParameter("date", date);
        }
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
        if (fields != null) {
            putQueryParameter("fields", convertListToString(fields));
        }
    }

    public AbstractStatRequest(String path) {
        super(path);
    }

    public AbstractStatRequest(String path, HttpMethodType httpMethod) {
        super(path, httpMethod);
    }

    private static String convertListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return String.join(",", list.toArray(new String[0]));
        }
    }
}
