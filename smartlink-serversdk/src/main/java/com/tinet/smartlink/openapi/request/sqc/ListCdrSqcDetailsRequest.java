package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.model.sqc.OriginData;
import com.tinet.smartlink.openapi.response.sqc.ListCdrSqcDetailsResponse;

import java.util.List;

/**
 * 根据质检时间查询ES数据
 *
 * @author liuhongyu
 * @date 2019/12/20
 **/
public class ListCdrSqcDetailsRequest extends BaseRequest<ListCdrSqcDetailsResponse> {


    /**
     * 呼叫时间 (必传)
     */
    private String channelTime;
    /**
     * 质检时间
     */
    private String qcTime;

    private String scrollId;

    private Integer limit;

    private String userId;

    private List<OriginData> originDataList;

    public List<OriginData> getOriginDataList() {
        return originDataList;
    }

    public void setOriginDataList(List<OriginData> originDataList) {
        this.originDataList = originDataList;
        if(originDataList != null){
            putQueryParameter("originDataList",originDataList);
        }
    }

    public String getChannelTime() {
        return channelTime;
    }

    public void setChannelTime(String channelTime) {
        this.channelTime = channelTime;
        if(channelTime != null){
            putQueryParameter("channelTime",channelTime);
        }
    }

    public String getQcTime() {
        return qcTime;
    }

    public void setQcTime(String qcTime) {
        this.qcTime = qcTime;
        if(qcTime != null){
            putQueryParameter("qcTime",qcTime);
        }
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
        if (scrollId != null) {
            putQueryParameter("scrollId", scrollId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putQueryParameter("userId", userId);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public ListCdrSqcDetailsRequest() {
        super("/sqc/listCdrSqcDetails", HttpMethodType.GET);
    }

    @Override
    public Class<ListCdrSqcDetailsResponse> getResponseClass() {
        return ListCdrSqcDetailsResponse.class;
    }
}
