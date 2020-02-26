package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ResponseModel;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * @author Chenjf
 * @date 2020/2/24 15:40
 **/
public abstract class AbstractStatRequest<T extends ResponseModel> extends AbstractRequestModel<T> {

    /**
     * 开始时间 时间格式 yyyy-MM-dd
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

    protected static String convertListToString(List objList) {
        if (objList == null || objList.size() == 0) {
            return null;
        } else {
            return objList.toString().substring(1, objList.toString().length() - 1);
        }
    }
}
