package com.tinet.clink.cc.response.rasr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.cc.model.RasrDialogModel;
import com.tinet.clink.cc.model.RasrResultModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询AI对话转写记录
 *
 * @author wangpw
 * @date 2023年12月25日
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RobotRasrResponse extends ResponseModel {

    private RasrResultModel rasrResult;


    public RasrResultModel getRasrResult() {
        return rasrResult;
    }

    public void setRasrResult(RasrResultModel rasrResult) {
        this.rasrResult = rasrResult;
    }
}
