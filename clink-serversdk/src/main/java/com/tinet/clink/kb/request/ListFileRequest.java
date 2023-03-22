package com.tinet.clink.kb.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.ListArticlesResponse;
import com.tinet.clink.kb.response.ListFileResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author 周先康
 * @create 2023/3/7 10:48
 */
public class ListFileRequest extends AbstractRequestModel<ListFileResponse> {

    private Integer kbId;

    private Integer directoryId;

    private String title;

    private Boolean pinyinStatus;

    private String order;

    private Integer offset;

    private Integer limit;

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
        if (kbId != null) {
            putBodyParameter("kbId", kbId);
        }
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
        if (directoryId != null) {
            putBodyParameter("directoryId", directoryId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (directoryId != null) {
            putBodyParameter("title", title);
        }
    }

    public Boolean getPinyinStatus() {
        return pinyinStatus;
    }

    public void setPinyinStatus(Boolean pinyinStatus) {
        this.pinyinStatus = pinyinStatus;
        if (pinyinStatus != null) {
            putBodyParameter("pinyinStatus", pinyinStatus);
        }
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
        if (order != null) {
            putBodyParameter("order", order);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putBodyParameter("offset", offset);
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

    public ListFileRequest() {
        super(PathEnum.ListFile.value(), HttpMethodType.POST);
    }



    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    @Override
    public Class<ListFileResponse> getResponseClass() {
        return ListFileResponse.class;
    }
}
