package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.ListCdrsScoreResponse;

import java.util.List;

/**
 * @author 杨冬雨
 * @Date 2022/7/22 10:41
 * @Description
 */
public class ListCdrsQcScoreRequest extends BaseRequest<ListCdrsScoreResponse> {

    /**
     * 企业id(必传)
     */
    private String enterpriseId;

    /**
     * uniqueIdList
     */
    private List<String> uniqueIdList;

    /**
     * 呼叫开始时间
     */
    private String callTimeStart;

    /**
     * 呼叫结束时间
     */
    private String callTimeEnd;

    private Integer offset;

    private Integer limit;

    private String accountLoginName;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
        if(enterpriseId != null){
            putQueryParameter("enterpriseId",enterpriseId);
        }
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
        if(enterpriseId != null){
            putQueryParameter("accountLoginName",accountLoginName);
        }
    }


    public List<String> getUniqueIdList() {
        return uniqueIdList;
    }

    public void setUniqueIdList(List<String> uniqueIdList) {
        this.uniqueIdList = uniqueIdList;
        if(uniqueIdList != null){
            putQueryParameter("uniqueIdList",uniqueIdList);
        }
    }

    public String getCallTimeStart() {
        return callTimeStart;
    }

    public void setCallTimeStart(String callTimeStart) {
        this.callTimeStart = callTimeStart;
        if(callTimeStart != null){
            putQueryParameter("callTimeStart",callTimeStart);
        }
    }

    public String getCallTimeEnd() {
        return callTimeEnd;
    }

    public void setCallTimeEnd(String callTimeEnd) {
        this.callTimeEnd = callTimeEnd;
        if(callTimeEnd != null){
            putQueryParameter("callTimeEnd",callTimeEnd);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if(offset != null){
            putQueryParameter("offset",offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if(limit != null){
            putQueryParameter("limit",limit);
        }
    }

    public ListCdrsQcScoreRequest() {
        super("/sqc/cdr/list", HttpMethodType.GET);
    }

    @Override
    public Class<ListCdrsScoreResponse> getResponseClass() {
        return ListCdrsScoreResponse.class;
    }
}
