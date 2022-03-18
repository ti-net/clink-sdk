package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.ListCdrIbsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询呼入通话记录列表请求
 *
 * @author huwk
 * @date 2018/10/23
 **/
public class ListCdrIbsRequest extends AbstractRequestModel<ListCdrIbsResponse> {

    /**
     * 座席号，要求只能是 4-6 位数字
     */
    private String cno;

    private String cname;

    private String startDate;
    private String endDate;

    /**
     * 偏移量，范围 0-99990。默认值为 0，但limit + offset 不允许超过100000
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100。默认值为 10，但limit + offset 不允许超过100000
     */
    private Integer limit;

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
        if (cname != null) {
            putQueryParameter("cname", cname);
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        if (startDate != null) {
            putQueryParameter("startDate", startDate);
        }
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        if (endDate != null) {
            putQueryParameter("endDate", endDate);
        }
    }

    public ListCdrIbsRequest() {
        super(PathEnum.ListCdrIbs.value(), HttpMethodType.GET);
    }



    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }







    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
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



    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }



    @Override
    public Class<ListCdrIbsResponse> getResponseClass() {
        return ListCdrIbsResponse.class;
    }
}

