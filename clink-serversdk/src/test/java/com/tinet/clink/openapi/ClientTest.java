package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.tinet.clink.cc.model.ClientDetailModel;
import com.tinet.clink.cc.model.ClientObClidModel;
import com.tinet.clink.cc.model.ClientPermission;
import com.tinet.clink.cc.model.ClientSearchResultModel;
import com.tinet.clink.cc.request.client.*;
import com.tinet.clink.cc.response.client.*;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.core.utils.AesUtil;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author lizy
 * @date 2018/10/23
 */
public class ClientTest extends AbstractTest{

    @Test
    public void createClientTest() throws ServerException, ClientException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
        CreateClientRequest clientsRequest = new CreateClientRequest();
        ClientPermission permission = new ClientPermission();
        permission.setSms(1);
        permission.setChat(1);
        clientsRequest.setPermission(permission);

        clientsRequest.setCno("775566");
        clientsRequest.setName("张彤");
        clientsRequest.setAreaCode("010");
        clientsRequest.setClidRule(1);
        clientsRequest.setClidType(3);
        clientsRequest.setPassword(AesUtil.encrypt("Aa123456",configuration.getAccessKeySecret()));
        clientsRequest.setObClidDefaultType(1);
        clientsRequest.setClidDefault(new String[]{"01066541759"});
        clientsRequest.setDynamicTelGroupIdDefault(152);
        CreateClientResponse response = client.getResponseModel(clientsRequest);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void updateClientTest() throws ServerException, ClientException, JsonProcessingException {
        UpdateClientRequest updateClientRequest = new UpdateClientRequest();
        ClientPermission permission = new ClientPermission();
        permission.setSms(0);
        permission.setChat(0);
        updateClientRequest.setPermission(permission);

        updateClientRequest.setCno("775566");
        updateClientRequest.setName("张彤11");
        updateClientRequest.setAreaCode("010");
        updateClientRequest.setChatLimit(1);
        updateClientRequest.setChatLimitNum(20);
        updateClientRequest.setObClidDefaultType(1);
        updateClientRequest.setClidRule(1);
        updateClientRequest.setClidType(3);
        updateClientRequest.setClidDefault(new String[]{"01066541759","01066541539"});
//        updateClientRequest.setDynamicTelGroupIdDefault(131);
        UpdateClientResponse response = client.getResponseModel(updateClientRequest);
        System.out.println(mapper.writeValueAsString(response));
    }

    //@Test
    public void deleteClientTest() throws ServerException, ClientException{
        DeleteClientRequest deleteClientRequest = new DeleteClientRequest();
        deleteClientRequest.setCno("2000");
        DeleteClientResponse response = client.getResponseModel(deleteClientRequest);

        System.out.println(response.getCno());
    }

    //@Test
    public void deleteClientTelTest() throws ServerException, ClientException{
        DeleteClientTelRequest deleteClientTelRequest = new DeleteClientTelRequest();
        deleteClientTelRequest.setCno("2000");
        deleteClientTelRequest.setTel("15210726818");
        DeleteClientTelResponse response = client.getResponseModel(deleteClientTelRequest);

        System.out.println(response.getRequestId());
    }

    //@Test
    public void listClientTelTest() throws ServerException, ClientException{
        ListClientTelsRequest listClientTelsRequest = new ListClientTelsRequest();
        listClientTelsRequest.setCno("2000");
        listClientTelsRequest.setTelType(1);
        ListClientTelsResponse response = client.getResponseModel(listClientTelsRequest);

        System.out.println(response.getRequestId());
    }



    @Test
    public void listClientsTest() throws ServerException, ClientException{
        ListClientsRequest listClientsRequest = new ListClientsRequest();
        ListClientsResponse response = client.getResponseModel(listClientsRequest);
        List<ClientSearchResultModel> clients = response.getClients();
        for (ClientSearchResultModel clientSearchResultModel : clients) {
            System.out.println(clientSearchResultModel.toString());
        }
    }

    @Test
    public void describeClient() throws ServerException, ClientException, JsonProcessingException {
        DescribeClientRequest describeClientRequest = new DescribeClientRequest();
        describeClientRequest.setCno("0611");
        DescribeClientResponse response = client.getResponseModel(describeClientRequest);

        ClientDetailModel client =  response.getClient();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(client));

    }

    @Test
    public void describeClientObClid() throws ServerException, ClientException, JsonProcessingException {
        DescribeClientObClidRequest describeClientObClidRequest = new DescribeClientObClidRequest();
        describeClientObClidRequest.setCno("2029");
        DescribeClientObClidResponse response = client.getResponseModel(describeClientObClidRequest);

        ClientObClidModel obClid =  response.getClientObClid();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obClid));

    }

    //@Test
    public void bindClientTel() throws ServerException, ClientException {
        BindClientTelRequest bindClientTelRequest = new BindClientTelRequest();
        bindClientTelRequest.setCno("2000");
        bindClientTelRequest.setTel("15210728889");
        bindClientTelRequest.setIsBind(1);
        BindClientTelResponse response = client.getResponseModel(bindClientTelRequest);

        response.getRequestId();
    }

    //@Test
    public void unbindClientTel() throws ServerException, ClientException {
        UnbindClientTelRequest unbindClientTelRequest = new UnbindClientTelRequest();
        unbindClientTelRequest.setCno("2000");
        UnbindClientTelResponse response = client.getResponseModel(unbindClientTelRequest);
        response.getRequestId();
    }

    //@Test
    public void bindClientTelVerification() throws ServerException, ClientException {
        BindClientTelVerificationRequest bindClientTelVerificationRequest = new BindClientTelVerificationRequest();
        bindClientTelVerificationRequest.setCno("2000");
        bindClientTelVerificationRequest.setTel("15210726818");
        BindClientTelVerificationResponse response = client.getResponseModel(bindClientTelVerificationRequest);
        response.getRequestId();
    }

    //@Test
    public void bindClientTelConfirm() throws ServerException, ClientException {
        BindClientTelConfirmedRequest bindClientTelConfirmedRequest = new BindClientTelConfirmedRequest();
        bindClientTelConfirmedRequest.setCno("2000");
        bindClientTelConfirmedRequest.setTel("15210726818");
        bindClientTelConfirmedRequest.setVerificationCode("0168");
        BindClientTelConfirmedResponse response = client.getResponseModel(bindClientTelConfirmedRequest);
        response.getRequestId();
    }

}
