package com.tinet.clink.cc.request.cdr;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.cc.response.cdr.DownloadRecordFileResponse;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 下载通话录音文件请求
 *
 * @author Wangyl
 * @date 2019/7/9
 */
public class DownloadRecordFileRequest extends AbstractRequestModel<DownloadRecordFileResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    /**
     *  非必选参数,不传该参数时获取普通mp3格式通话录音,
     *  传参数时获取双轨录音的某一侧录音: 1-客户侧,2-座席侧
     */
    private Integer recordSide;

    /**
     * 非必选参数,不传该参数时获取通话录音的 url,
     * 传参数时获得其他类型文件的 url: "voicemail"-留言记录
     *
     * @Author liyj
     * @Date 2023-06-15 10:16
     **/
    private String recordType;

    /**
     * 下载类型，传值"mp3"时生效
     *     若传递fixType，则参数recordSide效果变更为：recordSide不传递则获取mp3格式两侧合成录音，
     *     传递时支持获取mp3格式双轨录音。1：双轨录音客户侧，2：双轨录音座席侧，3：两侧合成录音。
     *     需要客户开启"mp3录音类型为混音且分线"。
     */
    private String fixType;

    public DownloadRecordFileRequest() {
        super(PathEnum.DownloadRecordFile.value(), HttpMethodType.GET);
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    @Override
    public Class<DownloadRecordFileResponse> getResponseClass() {
        return DownloadRecordFileResponse.class;
    }

    public Integer getRecordSide() {
        return recordSide;
    }

    public void setRecordSide(Integer recordSide) {
        this.recordSide = recordSide;
        if (recordSide != null) {
            putQueryParameter("recordSide", recordSide);
        }
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
        if (recordType != null) {
            putQueryParameter("recordType", recordType);
        }
    }

    public void setFixType(String fixType) {
        this.fixType = fixType;
        if (fixType != null) {
            putQueryParameter("fixType", fixType);
        }
    }

}
