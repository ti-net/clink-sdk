package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.model.CustomerSearchResponse;
import com.tinet.clink.openapi.model.IdValue;
import com.tinet.clink.openapi.request.config.customer.*;
import com.tinet.clink.openapi.response.config.customer.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangyang
 * @date 2019/11/12
 */
public class CustomerTest extends AbstractTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getCustomerParam() throws ServerException, ClientException {

        CustomerParamRequest customerParamRequest = new CustomerParamRequest();
        CustomerParamResponse customerParamResponse = client.getResponseModel(customerParamRequest);

        if (customerParamResponse != null && customerParamResponse.getCustomerParams() != null) {
            try {
                String resultStr = mapper.writeValueAsString(customerParamResponse);
                System.out.println(resultStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void listCustomers() throws ServerException, ClientException {

        //需要查询的参数放入userParamMap中 TODO sdk使用者选择适合自己的参数放入方式
        Map<String, String> userParamMap = new HashMap<String, String>();
        //userParamMap.put("电话", "17600055818");
        //userParamMap.put("性别", "1");
        //userParamMap.put("等级", "1");
        //userParamMap.put("来源", "3");
        //userParamMap.put("客户归属", "{\"shareType\":1,\"share\":\"0000\"}");
        //userParamMap.put("112233有","23");

        //当前企业可以用的查询参数
        CustomerParamRequest customerParamRequest = new CustomerParamRequest();
        CustomerParamResponse customerParamResponse = client.getResponseModel(customerParamRequest);

        //经过可用查询参数过滤后的参数，实际用于查询的map
        Map<String, Object> customerParamMap = new HashMap<String, Object>();

        //拿到可用查询条件，过滤一遍，再拼装你需要的查询条件，将中文名换成对应的id进行查询
        if (customerParamResponse != null && customerParamResponse.getCustomerParams() != null) {
            List<CustomerSearchResponse> CustomerSearchResponses = customerParamResponse.getCustomerParams();

            //参数过滤
            for (Map.Entry<String, String> entry : userParamMap.entrySet()) {
                for (CustomerSearchResponse customerSearchResponse : CustomerSearchResponses) {
                    if (entry.getKey().equals(customerSearchResponse.getName())) {
                        customerParamMap.put(String.valueOf(customerSearchResponse.getId()), entry.getValue());
                    }
                }
            }
        }

        String customerParams = null;
        try {
            customerParams = mapper.writeValueAsString(customerParamMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //开始查询客户资料，拼装参数
        ListCustomerRequest listCustomerRequest = new ListCustomerRequest();
        listCustomerRequest.setOffset(0);
        listCustomerRequest.setLimit(15);
        listCustomerRequest.setStartTime(1575424800L);
        listCustomerRequest.setEndTime(1575475199L);
        listCustomerRequest.setUpdateStartTime(1575428400L);
        listCustomerRequest.setUpdateEndTime(1575430259L);
        listCustomerRequest.setCustomerParams(customerParams);
        ListCustomerResponse customerResponse = client.getResponseModel(listCustomerRequest);

        try {
            String resultStr = mapper.writeValueAsString(customerResponse);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listCustomersFileds() throws ServerException, ClientException {

        //开始查询客户资料，拼装参数
        ListCustomerFieldRequest listCustomerFieldRequest = new ListCustomerFieldRequest();

        ListCustomerFieldResponse responseModel = client.getResponseModel(listCustomerFieldRequest);

        try {
            String resultStr = mapper.writeValueAsString(responseModel);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createCustomer() throws ServerException, ClientException {

        //开始查询客户资料，拼装参数
        CreateCustomerRequest request = new CreateCustomerRequest();

        request.setName("李志颖");
        request.setLevel(0);
        request.setEmail("lizy@ti-net.com.cn");
        request.setSex(0);
        String[] tels = {"152107218891"};
        request.setTel(tels);
        request.setShareType(0);
        request.setAddress("河北省沧州市");

        IdValue[] idValues = new IdValue[1];

        idValues[0] = new IdValue();
        idValues[0].setId(440);
        idValues[0].setValue("1");
        request.setCustomize(idValues);
        CreateCustomerResponse responseModel = client.getResponseModel(request);
        try {
            String resultStr = mapper.writeValueAsString(responseModel);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCustomer() throws ServerException, ClientException {

        //开始查询客户资料，拼装参数
        UpdateCustomerRequest request = new UpdateCustomerRequest();

        request.setName("李志颖");
        request.setLevel(0);
        request.setEmail("lizy@ti-net.com.cn");
        request.setSex(0);
        String[] tels = {"152107218891"};
        request.setTel(tels);
        request.setShareType(3);
        request.setAddress("河北省沧州市");
        request.setId(84176);
        request.setRenovate(0);

        IdValue[] idValues = new IdValue[1];

        idValues[0] = new IdValue();
        idValues[0].setId(440);
        idValues[0].setValue("1234");
        request.setCustomize(idValues);

        UpdateCustomerResponse responseModel = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(responseModel);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateCustomerByExternalId() throws ServerException, ClientException {

        UpdateCustomerByExternalIdRequest updateCustomerByExternalIdRequest = new UpdateCustomerByExternalIdRequest();

        updateCustomerByExternalIdRequest.setId(273795);

        updateCustomerByExternalIdRequest.setName("axaxax");

        updateCustomerByExternalIdRequest.setLevel(0);

        updateCustomerByExternalIdRequest.setShareType(0);

        updateCustomerByExternalIdRequest.setSex(1);

        updateCustomerByExternalIdRequest.setEmail("15533888888@qq.com");

        updateCustomerByExternalIdRequest.setAddress("山ddoasdsngasddddaa");

        String[] tel = {"16741291729"};

        updateCustomerByExternalIdRequest.setTel(tel);

        IdValue[] idValues = new IdValue[1];

        IdValue idValue = new IdValue();
        idValue.setId(344);

        idValue.setValue("xaxxxxaaxax");

        idValues[0] = idValue;

        updateCustomerByExternalIdRequest.setCustomize(idValues);

        updateCustomerByExternalIdRequest.setExternalId("13201a646126");

        UpdateCustomerByExternalIdResponse updateCustomerByExternalIdResponse =
                client.getResponseModel(updateCustomerByExternalIdRequest);

        try {
            String resultStr = mapper.writeValueAsString(updateCustomerByExternalIdResponse);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteCustomer() throws ServerException, ClientException {

        // 开始删除客户资料，拼装参数
        DeleteCustomerRequest request = new DeleteCustomerRequest();

        request.setId(75422);

        DeleteCustomerResponse responseModel = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(responseModel);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
