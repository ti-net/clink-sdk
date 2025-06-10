package com.tinet.clink.livechat.request;


import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.StatInvestigationCnoPageResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在线客服-满意度报表 Request
 *
 * @author midong
 * @date 2024/11/11
 */
public class StatInvestigationCnoPageRequest extends AbstractStatRequest<StatInvestigationCnoPageResponse> {

    private String startTime;

    private String endTime;

    @Deprecated
    private List<Integer> appType;

    private List<Integer> contactTypes;

    private List<String> cnos;

    private List<String> qnos;

    private Integer pageSize;

    private Integer pageIndex;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public List<Integer> getAppType() {
        return appType;
    }

    public void setAppType(List<Integer> appType) {
        this.appType = appType;
        if (appType != null && appType.size() != 0) {
            putQueryParameter("appType", convertIntegerListToString(appType));
        }
    }

    public List<Integer> getContactTypes() {
        return contactTypes;
    }

    public void setContactTypes(List<Integer> contactTypes) {
        this.contactTypes = contactTypes;
        if (contactTypes != null && contactTypes.size() != 0) {
            putQueryParameter("contactTypes", convertIntegerListToString(contactTypes));
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public List<String> getCnos() {
        return cnos;
    }

    public void setCnos(List<String> cnos) {
        this.cnos = cnos;
        if (cnos != null && cnos.size() != 0) {
            putQueryParameter("cnos", convertStringListToString(cnos));
        }
    }

    public List<String> getQnos() {
        return qnos;
    }

    public void setQnos(List<String> qnos) {
        this.qnos = qnos;
        if (qnos != null && qnos.size() != 0) {
            putQueryParameter("qnos", convertStringListToString(qnos));
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if (pageSize != null) {
            putQueryParameter("pageSize", pageSize);
        }
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        if (pageIndex != null) {
            putQueryParameter("pageIndex", pageIndex);
        }
    }

    @Override
    public Class<StatInvestigationCnoPageResponse> getResponseClass() {
        return StatInvestigationCnoPageResponse.class;
    }

    public StatInvestigationCnoPageRequest() {
        super(PathEnum.statInvestigationCnoPage.value(), HttpMethodType.POST);
    }

    private static String convertStringListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.stream().collect(Collectors.joining(","));
        }
    }

    private static String convertIntegerListToString(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.stream().map(Object::toString).collect(Collectors.joining(","));
        }
    }
}
