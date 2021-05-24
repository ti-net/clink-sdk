package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.cdr.*;
import com.tinet.clink.openapi.response.cdr.*;
import org.junit.Test;

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
    @Test
    public void listCdrIbsTest() throws ServerException, ClientException, JsonProcessingException {

        ListCdrIbsRequest request = new ListCdrIbsRequest();
        request.setHiddenType(1);
        request.setStartTime(1620468450L);
        request.setEndTime(1620468457L);
        request.setLimit(100);
//        request.setMainUniqueId("medias_1-1620468456.12");
//        request.setStatus(1);
        // request.setCno("1089");
        //  request.setStatus(2);
        //request.setMark(8);
        //request.setProvince("陕西");
        // request.setCity("广州");
        ListCdrIbsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrIbs()));
    }

    /**
     * sqc _ asr
     * @throws ServerException
     * @throws ClientException
     * @throws JsonProcessingException
     */
    @Test
    public void sqcAsr() throws ServerException, ClientException, JsonProcessingException {

        SqcAsrRequest request = new SqcAsrRequest();
        request.setMainUniqueId("medias_1-1606392391.107");
        SqcAsrResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
    /**
     * 呼入通话记录详情单元测试
     */
    @Test
    public void describeCdrIbTest() throws ServerException, ClientException, JsonProcessingException {

        DescribeCdrIbRequest request = new DescribeCdrIbRequest();
        request.setMainUniqueId("medias_1-1620786596.3");
        request.setHiddenType(1);
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
    // @Test
    public void copyCdrIbsTest() throws ServerException, ClientException, JsonProcessingException {

        CopyCdrIbsRequest request = new CopyCdrIbsRequest();
        request.setDate("20191025");
        request.setHiddenType(1);
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
    @Test
    public void listCdrObsTest() throws ServerException, ClientException, JsonProcessingException {

        ListCdrObsRequest request = new ListCdrObsRequest();
        request.setHiddenType(1);
        request.setStartTime(1620645356L);
        request.setEndTime(1620645381L);
        request.setLimit(100);
//        request.setStatus(1);
//        request.setCity("北京");
        ListCdrObsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getCdrObs()));
    }

    /**
     * 外呼通话记录详情单元测试
     */
    //@Test
    public void describeCdrObTest() throws ServerException, ClientException, JsonProcessingException {

        DescribeCdrObRequest request = new DescribeCdrObRequest();
        request.setMainUniqueId("medias_1-1571976270.5");
        request.setHiddenType(1);
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
    @Test
    public void copyCdrObsTest() throws ServerException, ClientException, JsonProcessingException {

        CopyCdrObsRequest request = new CopyCdrObsRequest();
        request.setDate("20191025");
        request.setHiddenType(1);
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

