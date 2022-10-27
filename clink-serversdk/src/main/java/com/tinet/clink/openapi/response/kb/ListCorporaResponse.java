package com.tinet.clink.openapi.response.kb;


import com.tinet.clink.openapi.model.CorpusModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 语料列表响应实体
 *
 * @author feizq
 * @date 2022/07/26
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListCorporaResponse extends ResponseModel {

    private List<CorpusModel> corpora;

    public List<CorpusModel> getCorpora() {
        return corpora;
    }

    public void setCorpora(List<CorpusModel> corpora) {
        this.corpora = corpora;
    }
}
