package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.AsrModel;
import com.tinet.clink.openapi.response.ResponseModel;


/**
 * 数据同步呼入通话记录明细响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  SqcAsrResponse extends ResponseModel {

    /**
     * 结果
     */
    private AsrModel asr;

    public AsrModel getAsr() {
        return asr;
    }

    public void setAsr(AsrModel asr) {
        this.asr = asr;
    }
}
