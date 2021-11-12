package com.tinet.clink.openapi.zb;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.model.Field;
import com.tinet.clink.openapi.request.zb.ticket.ZbListTicketRequest;
import com.tinet.clink.openapi.request.zb.ticket.ZbTicketAddRecordRequest;
import com.tinet.clink.openapi.request.zb.ticket.ZbTicketDetailRequest;
import com.tinet.clink.openapi.request.zb.ticket.ZbTicketUpdateStatusRequest;
import com.tinet.clink.openapi.response.zb.ticket.ZbListTicketResponse;
import com.tinet.clink.openapi.response.zb.ticket.ZbTicketAddRecordResponse;
import com.tinet.clink.openapi.response.zb.ticket.ZbTicketDetailResponse;
import com.tinet.clink.openapi.response.zb.ticket.ZbTicketUpdateStatusResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * zb工单请求sdk测试示例
 *
 * @author liuhy
 * @date: 2021/11/3
 **/
public class ZbTicketTest extends AbstractTest {

    @Test
    public void addRecord() throws ServerException, ClientException {

        // 创建请求的实体request
        ZbTicketAddRecordRequest ticketAddRecordRequest = new ZbTicketAddRecordRequest();
        // 设置工单id和工单状态
        ticketAddRecordRequest.setId(1);
        ticketAddRecordRequest.setStatus(2);

        // 设置要修改的自定义字段
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(1);
        field.setName("处理方案");
        field.setValue("这是我的方案");

        Field field1 = new Field();
        field.setId(2);
        field.setName("工单图片");
        field.setValue("url1,url2");

        fieldList.add(field);
        fieldList.add(field1);

        ticketAddRecordRequest.setFields(fieldList.toArray(new Field[fieldList.size()]));

        // 请求
        ZbTicketAddRecordResponse recordResponse = client.getResponseModel(ticketAddRecordRequest);

    }

    @Test
    public void updateStatus() throws ServerException, ClientException {



        // 创建请求的实体request
        ZbTicketUpdateStatusRequest zbTicketUpdateStatusRequest = new ZbTicketUpdateStatusRequest();
        // 设置工单id和工单状态
        zbTicketUpdateStatusRequest.setStatus(2);
        zbTicketUpdateStatusRequest.setTicketId(73);

        // 请求
        ZbTicketUpdateStatusResponse recordResponse = client.getResponseModel(zbTicketUpdateStatusRequest);
        System.out.println(1);

    }

    @Test
    public void getTicketDeatil() {
        ZbTicketDetailRequest getTicketDetailRequest = new ZbTicketDetailRequest();

        getTicketDetailRequest.setId(20);
        try {
            ZbTicketDetailResponse response = client.getResponseModel(getTicketDetailRequest);

            System.out.println(response);

            System.out.println(111);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void list(){
        ZbListTicketRequest listTicketRequest=new ZbListTicketRequest();

        listTicketRequest.setStartTime("2020-05-01 00:00:00");
        listTicketRequest.setEndTime("2021-11-10 23:59:59");
        listTicketRequest.setOffset(0);
        listTicketRequest.setLimit(10);
        listTicketRequest.setsCode("S001955");
        listTicketRequest.setLevel(0);
        listTicketRequest.setHandleStatus(0);
        listTicketRequest.setHandlerType(0);

        try {
            ZbListTicketResponse response=client.getResponseModel(listTicketRequest);

            System.out.println(response);
            System.out.println(111);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}