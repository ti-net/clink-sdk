package com.tinet.clink.cc.response.voiceMail;

import com.tinet.clink.cc.model.VoiceMail;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * Class For:
 * 留言箱列表查询接口
 *
 * @author Tinet-yinzk
 * @date 2023/8/29 14:17
 */
public class ListVoiceMailsResponse extends PagedResponse {
    /**
     * 留言箱列表
     */
    private List<VoiceMail> voiceMails;

    public List<VoiceMail> getVoiceMails() {
        return voiceMails;
    }

    public void setVoiceMails(List<VoiceMail> voiceMails) {
        this.voiceMails = voiceMails;
    }
}
