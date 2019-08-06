package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.cdr.*;
import com.tinet.clink.openapi.response.cdr.*;

/**
 * 通话记录单元测试
 *
 * @author huwk
 * @date 2018/10/23
 */
public class CdrRecordTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();
    /**
     * 呼入通话记录列表单元测试
     */
//    @Test
    public void listCdrIbsTest() throws ServerException, ClientException, JsonProcessingException {

        ListCdrIbsRequest request = new ListCdrIbsRequest();
        ListCdrIbsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrIbs()));
    }

    /**
     * 呼入通话记录详情单元测试
     */
//    @Test
    public void describeCdrIbTest() throws ServerException, ClientException, JsonProcessingException {

        DescribeCdrIbRequest request = new DescribeCdrIbRequest();
        request.setMainUniqueId("AWS_DEV_MEDIA_SERVER_8-1540431801.5");
        DescribeCdrIbResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrIb()));
    }
    /**
     * 呼入通话记录明细详情单元测试
     */
//    @Test
    public void describeCdrIbDetailsTest() throws ServerException, ClientException, JsonProcessingException {

        DescribeCdrIbDetailsRequest request = new DescribeCdrIbDetailsRequest();
        request.setMainUniqueId("AWS_DEV_MEDIA_SERVER_8-1540431801.5");
        DescribeCdrIbDetailsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrIbDetails()));
    }

    /**
     * 同步呼入通话记录详情单元测试
     */
//    @Test
    public void copyCdrIbsTest() throws ServerException, ClientException, JsonProcessingException {

        CopyCdrIbsRequest request = new CopyCdrIbsRequest();
        request.setDate("20181025");
        CopyCdrIbsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrIbs()));
    }

    /**
     * 同步呼入通话记录明细详情单元测试
     */
//    @Test
    public void copyCdrIbDetailsTest() throws ServerException, ClientException, JsonProcessingException {

        CopyCdrIbDetailsRequest request = new CopyCdrIbDetailsRequest();
        request.setDate("20181025");
        CopyCdrIbDetailsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrIbDetails()));
    }

//==========================================================================================
    /**
     * 外呼通话记录列表单元测试
     */
//    @Test
    public void listCdrObsTest() throws ServerException, ClientException, JsonProcessingException {

        ListCdrObsRequest request = new ListCdrObsRequest();
        ListCdrObsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrObs()));
    }

    /**
     * 外呼通话记录详情单元测试
     */
//    @Test
    public void describeCdrObTest() throws ServerException, ClientException, JsonProcessingException {

        DescribeCdrObRequest request = new DescribeCdrObRequest();
        request.setMainUniqueId("AWS_DEV_MEDIA_SERVER_8-1540435106.22");
        DescribeCdrObResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrOb()));
    }
    /**
     * 外呼通话记录明细详情单元测试
     */
//    @Test
    public void describeCdrObDetailsTest() throws ServerException, ClientException, JsonProcessingException {

        DescribeCdrObDetailsRequest request = new DescribeCdrObDetailsRequest();
        request.setMainUniqueId("AWS_DEV_MEDIA_SERVER_8-1540435106.22");
        DescribeCdrObDetailsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrObDetails()));
    }

    /**
     * 同步外呼通话记录详情单元测试
     */
//    @Test
    public void copyCdrObsTest() throws ServerException, ClientException, JsonProcessingException {

        CopyCdrObsRequest request = new CopyCdrObsRequest();
        request.setDate("20181025");
        CopyCdrObsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrObs()));
    }
    /**
     * 同步外呼通话记录明细详情单元测试
     */
//    @Test
    public void copyCdrObDetailsTest() throws ServerException, ClientException, JsonProcessingException {

        CopyCdrObDetailsRequest request = new CopyCdrObDetailsRequest();
        request.setDate("20181025");
        CopyCdrObDetailsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrObDetails()));
    }
    //=============================================================================
    /**
     * 留言记录列表单元测试
     */
//    @Test
    public void listCommentsTest() throws ServerException, ClientException, JsonProcessingException {

        ListCommentsRequest request = new ListCommentsRequest();
        ListCommentsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrComments()));
    }

    /**
     * 满意度调查列表单元测试
     */
//    @Test
    public void listInvestigationsTest() throws ServerException, ClientException, JsonProcessingException {

        ListInvestigationsRequest request = new ListInvestigationsRequest();
        ListInvestigationsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrInvestigations()));
    }

}
