package com.tinet.clink.kb.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.kb.model.ChatQaResponseModel;
import com.tinet.clink.kb.model.SearchKnowledgeOnOpenModel;

import java.util.List;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class SearchKnowledgeOnOpenResponse extends ResponseModel {

    private List<SearchKnowledgeOnOpenModel> result;

    public List<SearchKnowledgeOnOpenModel> getResult() {
        return result;
    }

    public void setResult(List<SearchKnowledgeOnOpenModel> result) {
        this.result = result;
    }
}
