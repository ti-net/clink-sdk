package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.OriginData;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PostListCdrSqcDetailsResponse;

import java.util.List;

/**
 * @Author: liurf
 * @Description: 根据质检时间查询ES数据
 * @Date: 2021/10/26 12:15
*/
public class PostListCdrSqcDetailsRequest extends BaseRequest<PostListCdrSqcDetailsResponse> {


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
            putBodyParameter("originDataList",originDataList);
        }
    }

    public String getChannelTime() {
        return channelTime;
    }

    public void setChannelTime(String channelTime) {
        this.channelTime = channelTime;
        if(channelTime != null){
            putBodyParameter("channelTime",channelTime);
        }
    }

    public String getQcTime() {
        return qcTime;
    }

    public void setQcTime(String qcTime) {
        this.qcTime = qcTime;
        if(qcTime != null){
            putBodyParameter("qcTime",qcTime);
        }
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
        if (scrollId != null) {
            putBodyParameter("scrollId", scrollId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putBodyParameter("limit", limit);
        }
    }

    public PostListCdrSqcDetailsRequest() {
        super("/sqc/listCdrSqcDetails", HttpMethodType.POST);
    }

    @Override
    public Class<PostListCdrSqcDetailsResponse> getResponseClass() {
        return PostListCdrSqcDetailsResponse.class;
    }
}
