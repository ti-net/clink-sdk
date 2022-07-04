package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.AsrText;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushAsrTextResponse;

/**
 * 推送AsrText文件
 *
 * @author wenjd
 * @date 2019/04/01
 */
public class PushAsrTextRequest extends BaseRequest<PushAsrTextResponse> {

    /**
     * 记录唯一标识
     */
    private String uniqueId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 转写文本对象
     */
    private AsrText asrText;

    /**
     * N：双轨先说话侧 ，OUT：双轨后说话侧，ALL：单轨不分侧
     */
    private String recordSide;

    /**
     * 是否开启快速质检功能，true:开启，false：关闭
     */
    private Boolean fastAsr = false;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putBodyParameter("uniqueId", uniqueId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public Boolean getFastAsr() {
        return fastAsr;
    }

    public void setFastAsr(Boolean fastAsr) {
        this.fastAsr = fastAsr;
        if (fastAsr != null) {
            putBodyParameter("fastAsr", fastAsr);
        }
    }

    public AsrText getAsrText() {
        return asrText;
    }

    public void setAsrText(AsrText asrText) {
        this.asrText = asrText;
        if (asrText != null) {
            putBodyParameter("asrText", asrText);
        }
    }

    public String getRecordSide() {
        return recordSide;
    }

    public void setRecordSide(String recordSide) {
        this.recordSide = recordSide;
        if (recordSide != null) {
            putBodyParameter("recordSide", recordSide);
        }
    }

    public PushAsrTextRequest() {
        super("/sqc/asr_text", HttpMethodType.POST);
    }

    @Override
    public Class<PushAsrTextResponse> getResponseClass() {
        return PushAsrTextResponse.class;
    }
}
