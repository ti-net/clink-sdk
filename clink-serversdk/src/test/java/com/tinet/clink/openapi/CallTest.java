package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.cc.request.control.*;
import com.tinet.clink.cc.request.manage.*;
import com.tinet.clink.cc.response.control.*;
import com.tinet.clink.cc.response.manage.*;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 话务接口测试
 *
 * @author huwk
 * @date 2018/10/29
 **/
@Ignore
public class CallTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 上线单元测试
     */
    @Test
    public void onlineTest() throws ServerException, ClientException, JsonProcessingException {

        OnlineRequest request = new OnlineRequest();
        request.setCno("0021");
        request.setBindTel("13934976774");
        request.setBindType(1);
        request.setStatus(1);

        OnlineResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 下线单元测试
     */
    @Test
    public void offlineTest() throws ServerException, ClientException, JsonProcessingException {

        OfflineRequest request = new OfflineRequest();
        request.setCno("0021");
        request.setUnbindTel(0);

        OfflineResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 下线单元测试
     */
    @Test
    public void offlineClientTest() throws ServerException, ClientException, JsonProcessingException {

        OfflineClientRequest request = new OfflineClientRequest();
        request.setCno("6666");

        OfflineClientResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    /**
     * 置忙单元测试
     */
    @Test
    public void pauseTest() throws ServerException, ClientException, JsonProcessingException {

        PauseRequest request = new PauseRequest();
        request.setCno("0021");
        request.setDescription("置忙");

        PauseResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 置闲单元测试
     */
    @Test
    public void unpauseTest() throws ServerException, ClientException, JsonProcessingException {

        UnpauseRequest request = new UnpauseRequest();
        request.setCno("0021");

        UnpauseResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 外呼单元测试
     */
    @Test
    public void calloutTest() throws ServerException, ClientException, JsonProcessingException {

        CalloutRequest request = new CalloutRequest();
        request.setCno("5818");
        request.setCustomerNumber("17600055818");
        request.setClid("02968214693");

        CalloutResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 外呼取消单元测试
     */
    @Test
    public void calloutCancelTest() throws ServerException, ClientException, JsonProcessingException {

        CalloutCancelRequest request = new CalloutCancelRequest();
        request.setCno("1001");

        CalloutCancelResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 挂机单元测试
     */
    @Test
    public void unlinkTest() throws ServerException, ClientException, JsonProcessingException {

        UnlinkRequest request = new UnlinkRequest();
        request.setCno("1001");

        UnlinkResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 拒接单元测试
     */
    @Test
    public void refuseTest() throws ServerException, ClientException, JsonProcessingException {

        RefuseRequest request = new RefuseRequest();
        request.setCno("1001");

        RefuseResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 转移单元测试
     */
    @Test
    public void transferTest() throws ServerException, ClientException, JsonProcessingException {

        TransferRequest request = new TransferRequest();
        request.setCno("1000");
        request.setTransferType(0);
        request.setTransferNumber("");

        TransferResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 交互单元测试
     */
    @Test
    public void interactTest() throws ServerException, ClientException, JsonProcessingException {

        InteractRequest request = new InteractRequest();
        request.setCno("1000");
        request.setIvrName("测试语音导航");
        request.setIvrNode("");

        InteractResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 咨询单元测试
     */
    @Test
    public void consultTest() throws ServerException, ClientException, JsonProcessingException {

        ConsultRequest request = new ConsultRequest();
        request.setCno("1000");
        request.setConsultType(0);
        request.setConsultNumber("");

        ConsultResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 咨询取消单元测试
     */
    @Test
    public void consultCancelTest() throws ServerException, ClientException, JsonProcessingException {

        ConsultCancelRequest request = new ConsultCancelRequest();
        request.setCno("1000");

        ConsultCancelResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 咨询转移单元测试
     */
    @Test
    public void consultTransferTest() throws ServerException, ClientException, JsonProcessingException {

        ConsultTransferRequest request = new ConsultTransferRequest();
        request.setCno("1000");

        ConsultTransferResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 咨询三方单元测试
     */
    @Test
    public void consultThreewayTest() throws ServerException, ClientException, JsonProcessingException {

        ConsultThreewayRequest request = new ConsultThreewayRequest();
        request.setCno("1000");

        ConsultThreewayResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 咨询接回单元测试
     */
    @Test
    public void unconsultTest() throws ServerException, ClientException, JsonProcessingException {

        UnconsultRequest request = new UnconsultRequest();
        request.setCno("1000");

        UnconsultResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 保持单元测试
     */
    @Test
    public void holdTest() throws ServerException, ClientException, JsonProcessingException {

        HoldRequest request = new HoldRequest();
        request.setCno("1000");

        HoldResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 取消保持单元测试
     */
    @Test
    public void unholdTest() throws ServerException, ClientException, JsonProcessingException {

        UnholdRequest request = new UnholdRequest();
        request.setCno("1000");

        UnholdResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 静音单元测试
     */
    @Test
    public void muteTest() throws ServerException, ClientException, JsonProcessingException {

        MuteRequest request = new MuteRequest();
        request.setCno("1000");
        request.setDirection("all");

        MuteResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 取消静音单元测试
     */
    @Test
    public void unmuteTest() throws ServerException, ClientException, JsonProcessingException {

        UnmuteRequest request = new UnmuteRequest();
        request.setCno("1000");
        request.setDirection("all");

        UnmuteResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 发送按键单元测试
     */
    @Test
    public void dtmfTest() throws ServerException, ClientException, JsonProcessingException {

        DtmfRequest request = new DtmfRequest();
        request.setCno("1000");
        request.setDigits("");
        request.setDirection("all");

        DtmfResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 满意度调查单元测试
     */
    @Test
    public void investigationTest() throws ServerException, ClientException, JsonProcessingException {

        InvestigationRequest request = new InvestigationRequest();
        request.setCno("1000");

        InvestigationResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }
    /*====================================================================================================*/

    /**
     * 监听单元测试
     */
    @Test
    public void spyTest() throws ServerException, ClientException, JsonProcessingException {

        SpyRequest request = new SpyRequest();
        request.setCno("1000");
        request.setSpyType(0);
        request.setSpyNumber("");

        SpyResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 三方单元测试
     */
    @Test
    public void threewayTest() throws ServerException, ClientException, JsonProcessingException {

        ThreewayRequest request = new ThreewayRequest();
        request.setCno("1000");
        request.setThreewayType(0);
        request.setThreewayNumber("");

        ThreewayResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 耳语单元测试
     */
    @Test
    public void whisperTest() throws ServerException, ClientException, JsonProcessingException {

        WhisperRequest request = new WhisperRequest();
        request.setCno("1000");
        request.setWhisperType(0);
        request.setWhisperNumber("");

        WhisperResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 强拆单元测试
     */
    @Test
    public void disconnectTest() throws ServerException, ClientException, JsonProcessingException {

        DisconnectRequest request = new DisconnectRequest();
        request.setCno("1000");
        request.setDisconnectType(0);
        request.setDisconnectNumber("");

        DisconnectResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 强插单元测试
     */
    @Test
    public void bargeTest() throws ServerException, ClientException, JsonProcessingException {

        BargeRequest request = new BargeRequest();
        request.setCno("1000");
        request.setBargeType(0);
        request.setBargeNumber("");

        BargeResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 置忙单元测试
     */
    @Test
    public void pauseClientTest() throws ServerException, ClientException, JsonProcessingException {

        PauseClientRequest request = new PauseClientRequest();
        request.setCno("1000");

        PauseClientResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }

    /**
     * 置闲单元测试
     */
    @Test
    public void unpauseClientTest() throws ServerException, ClientException, JsonProcessingException {

        UnpauseClientRequest request = new UnpauseClientRequest();
        request.setCno("1000");

        UnpauseClientResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }
}
