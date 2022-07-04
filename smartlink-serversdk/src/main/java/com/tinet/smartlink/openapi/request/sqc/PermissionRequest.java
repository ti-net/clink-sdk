package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.ProductEnum;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PermissionResponse;

/**
 * 获取权限列表
 * @author 王大宝
 */
public class PermissionRequest extends BaseRequest<PermissionResponse> {


    /**
     * 产品
     */
    private String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(ProductEnum productEnum) {
        this.product = productEnum.getValue();
        if (product != null) {
            putQueryParameter("product", product);
        }
    }

    public PermissionRequest() {

        super("/sqc/permission", HttpMethodType.GET);
    }

    @Override
    public Class<PermissionResponse> getResponseClass() {
        return PermissionResponse.class;
    }
}
