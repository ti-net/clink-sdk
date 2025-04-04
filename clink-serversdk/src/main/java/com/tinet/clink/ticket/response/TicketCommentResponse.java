package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @description 类说明
 * @Author DengJie
 * @Date 2022/04/21
 */
public class TicketCommentResponse extends ResponseModel {
    private TicketCommentResult result;

    public static class TicketCommentResult {
        private List<String> files;

        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }
    }

    public TicketCommentResult getResult() {
        return result;
    }

    public void setResult(TicketCommentResult result) {
        this.result = result;
    }
}
