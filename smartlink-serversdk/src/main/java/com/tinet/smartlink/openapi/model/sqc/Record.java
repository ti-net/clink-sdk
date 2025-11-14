package com.tinet.smartlink.openapi.model.sqc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 王大宝
 * @date 2019/7/22
 */
@Data
public class Record implements Serializable {

    /**
     * 录音下载地址
     */
    private String fileUrl;

    /**
     * 如果录音做了分轨，该录音是双轨录音的一侧，必须指定该录音文件是 agent/client ，便于区分两侧。agent是坐席侧client是客户侧，不传值默认是单轨录音.
     */
    private String recordSide;
    /**
     * 录音地址是否长期有效
     */
    private boolean erpetualUrl = false;
    /**
     * 录音是否需要存储
     */
    private boolean storageRecord = false;

    /**
     * 录音id
     */
    private String fileId;

}
