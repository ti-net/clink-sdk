package com.tinet.smartlink.openapi.model.sqc;

import java.io.Serializable;
import java.util.List;

/**
 * 转写文本对象
 *
 * @author wenjd
 * @date 2019/04/03
 */
public class AsrText implements Serializable {

    /**
     * 识别的录音时长
     */
    private Integer bizDuration;

    /**
     * 转写的结果数据
     */
    private List<SentenceResult> result;


    public Integer getBizDuration() {
        return bizDuration;
    }

    public void setBizDuration(Integer bizDuration) {
        this.bizDuration = bizDuration;
    }

    public List<SentenceResult> getResult() {
        return result;
    }

    public void setResult(List<SentenceResult> result) {
        this.result = result;
    }
}
