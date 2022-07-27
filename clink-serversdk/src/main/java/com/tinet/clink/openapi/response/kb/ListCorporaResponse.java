package com.tinet.clink.openapi.response.kb;


import com.tinet.clink.openapi.model.CorpusModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author feizq
 * @date 2022/07/26
 **/
public class ListCorporaResponse extends ResponseModel {

    private List<CorpusModel> corpora;

    public List<CorpusModel> getCorpora() {
        return corpora;
    }

    public void setCorpora(List<CorpusModel> corpora) {
        this.corpora = corpora;
    }
}
