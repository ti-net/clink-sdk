package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.model.sqc.BusinessPriority;
import com.tinet.smartlink.openapi.response.sqc.SaveCdrBusinessPriorityResponse;

import java.util.List;

/**
 * @author： 许成
 * @date： 2021/10/14 15:38
 * @description：
 */
public class SaveCdrBusinessPriorityRequest extends BaseRequest<SaveCdrBusinessPriorityResponse> {
    private List<BusinessPriority> param;
    @Override
    public Class<SaveCdrBusinessPriorityResponse> getResponseClass() {
        return SaveCdrBusinessPriorityResponse.class;
    }

    public SaveCdrBusinessPriorityRequest() {
        super("/sqc/saveCdrBusinessPriority", HttpMethodType.POST);
    }

    public List<BusinessPriority> getParam() {
        return param;
    }

    public void setParam(List<BusinessPriority> param) {
        this.param = param;

        if (param != null) {
            putBodyParameter("param", param);
        }
    }
}
