package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 语料记录返回对象
 *
 * @author feizq
 * @date 2022/06/15
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CorpusRecordModel {

    /**
     * 标准问ID
     */
    private Integer sqId;
    /**
     * 标准问ID
     */
    private List<Corpus> corpusList;

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
    }

    public List<Corpus> getCorpusList() {
        return corpusList;
    }

    public void setCorpusList(List<Corpus> corpusList) {
        this.corpusList = corpusList;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Corpus {

        /**
         * 语料名
         */
        private String corpusName;

        public String getCorpusName() {
            return corpusName;
        }

        public void setCorpusName(String corpusName) {
            this.corpusName = corpusName;
        }
    }
}
