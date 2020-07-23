package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.ivr.ListIvrNodesRequest;
import com.tinet.clink.openapi.request.ivr.ListIvrsRequest;
import com.tinet.clink.openapi.response.ivr.ListIvrNodesResponse;
import com.tinet.clink.openapi.response.ivr.ListIvrsResponse;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 语音导航设置接口测试
 *
 * @author huwk
 * @date 2018/11/15
 **/
@Ignore
public class IvrTest extends AbstractTest{

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询ivr列表单元测试
     */
    @Test
    public void listIvrsTest() throws ServerException, ClientException, JsonProcessingException {

        ListIvrsRequest request = new ListIvrsRequest();

        ListIvrsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getIvrs()));
    }

    /**
     * 查询ivr节点列表单元测试
     */
    @Test
    public void listIvrNodesTest() throws ServerException, ClientException, JsonProcessingException {

        ListIvrNodesRequest request = new ListIvrNodesRequest();
        request.setIvrId(5732);

        ListIvrNodesResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getIvrNodes()));
    }

}
