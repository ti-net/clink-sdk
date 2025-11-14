package com.tinet.clink.cc.response.cdr;

import com.tinet.clink.cc.model.CdrAudioFlowNodeModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询通话录音地址响应
 *
 * @author Wangyl
 * @date 2019/7/9
 */
public class DescribeRecordFileUrlResponse extends ResponseModel {
    private String recordFileUrl;

    private List<CdrAudioFlowNodeModel> audioFlows;

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
    }

    public List<CdrAudioFlowNodeModel> getAudioFlows() {
        return audioFlows;
    }

    public void setAudioFlows(List<CdrAudioFlowNodeModel> audioFlows) {
        this.audioFlows = audioFlows;
    }
}
