package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.DescribeCdrIbResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 查询呼入通话记录详情请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class DescribeCdrIbRequest extends AbstractRequestModel<DescribeCdrIbResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    /**
     * 是否隐藏号码
     */
    private Integer hiddenType;


    /**
     * 需要展示的字段
     * tagNames
     */
    private String[] fields;

    public DescribeCdrIbRequest() {
        super(PathEnum.DescribeCdrIb.value(), HttpMethodType.GET);
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

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
        if (hiddenType != null) {
            putQueryParameter("hiddenType", hiddenType);
        }
    }


    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
        if (fields != null && fields.length > 0) {
            putQueryParameter("fields", String.join(",", fields));
        }
    }

    @Override
    public Class<DescribeCdrIbResponse> getResponseClass() {
        return DescribeCdrIbResponse.class;
    }
}
