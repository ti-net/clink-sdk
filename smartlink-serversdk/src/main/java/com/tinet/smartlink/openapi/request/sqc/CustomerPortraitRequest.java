package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.CustomerPortraitResponse;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for:
 *
 * @author yinzk
 * @date 2020/8/24
 */
public class CustomerPortraitRequest extends BaseRequest<CustomerPortraitResponse> {

    /**
     * 分页起始，默认为 0
     **/
    protected Integer offset = 0;

    /**
     * 分页大小，默认为 10
     **/
    protected Integer limit;
    /**
     * 客户号码
     */
    private Set<String> customerNumber = new HashSet<>();
    /**
     * 必须包含的标签
     */
    private List<String> portraits;

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public void setCustomerNumber(Set<String> customerNumber) {
        this.customerNumber = customerNumber;
        if (!CollectionUtils.isEmpty(customerNumber)) {
            putQueryParameter("customerNumber", customerNumber.toString().substring(1, customerNumber.toString().length() - 1));
        }
    }

    public void setPortraits(List<String> portraits) {
        this.portraits = portraits;
        if (!CollectionUtils.isEmpty(portraits)) {
            putQueryParameter("portraits", portraits.toString().substring(1, portraits.toString().length() - 1));
        }
    }

    public CustomerPortraitRequest() {
        super("/sqc/customerPortrait", HttpMethodType.GET);
    }

    @Override
    public Class<CustomerPortraitResponse> getResponseClass() {
        return CustomerPortraitResponse.class;
    }
}
