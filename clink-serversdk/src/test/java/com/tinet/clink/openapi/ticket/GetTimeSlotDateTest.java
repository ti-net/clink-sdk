package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.Field;
import com.tinet.clink.ticket.model.TicketFormModel;
import com.tinet.clink.ticket.model.TicketUpdateModel;
import com.tinet.clink.ticket.request.GetTicketDetailRequest;
import com.tinet.clink.ticket.request.GetTimeSlotDateRequestById;
import com.tinet.clink.ticket.request.TicketUpdateRequest;
import com.tinet.clink.ticket.request.TimeSlotOccupyBySkillIdRequest;
import com.tinet.clink.ticket.response.GetTicketDetailResponse;
import com.tinet.clink.ticket.response.GetTimeSlotDateResponse;
import com.tinet.clink.ticket.response.TicketUpdateResponse;
import com.tinet.clink.ticket.response.TimeSlotOccupyBySkillIdResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工单详情查询请求示例
 *
 * @date: 2020/11/25
 **/
public class GetTimeSlotDateTest extends AbstractTest {

    @Test
    public void getTimeSlotDate() {

        ClientConfiguration configuration = new ClientConfiguration(
                "dc4854f9f3b68aa7ececc37d494e3a57",          // AccessKeyId
                "G23eFh9m5Z5UCUMpjos7");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        List<String> timeSlotDates = new ArrayList<>();
        timeSlotDates.add("2026-07-21");
        GetTimeSlotDateRequestById request = new GetTimeSlotDateRequestById();
        request.setFieldId(318761);
        request.setFormId(32888);
        request.setSkillId(1464);
        request.setTimeSlotDates(timeSlotDates);

        GetTimeSlotDateResponse getTimeSlotDateResponse;

        try {
            getTimeSlotDateResponse = client.getResponseModel(request);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(getTimeSlotDateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cc0902TimeSlotOccupyBySkillId(){

        ClientConfiguration configuration = new ClientConfiguration(
                "dc4854f9f3b68aa7ececc37d494e3a57",          // AccessKeyId
                "G23eFh9m5Z5UCUMpjos7");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        List<String> timeSlotDates = new ArrayList<>();
        timeSlotDates.add("2026-08-09");
        TimeSlotOccupyBySkillIdRequest request = new TimeSlotOccupyBySkillIdRequest();
        request.setFieldId(318761);
        request.setFormId(32888);
        request.setSkillId(1464);
        request.setTimeSlotDate("2026-08-09");
        request.setStartTime("22:00");
        request.setEndTime("22:15");

        TimeSlotOccupyBySkillIdResponse response;

        try {
            response = client.getResponseModel(request);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cc0902UpdateWorkflowTicket() throws JsonProcessingException {

        ClientConfiguration configuration = new ClientConfiguration(
                "dc4854f9f3b68aa7ececc37d494e3a57",          // AccessKeyId
                "G23eFh9m5Z5UCUMpjos7");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketUpdateRequest ticketUpdateRequest = new TicketUpdateRequest();

        // 请求参数
        TicketUpdateModel ticketUpdateModel = new TicketUpdateModel();
        ticketUpdateModel.setId(1173);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(32888);
        ticketFormModel.setTaskId("StartEvent_1uwp14d-7387a0c6-59ff-4e0c-91a3-40c63352e25d");
        ticketFormModel.setName("超市购物售后");
        ticketFormModel.setTimeSlotOccupyId(2351);

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field2 = new Field();
        field2.setId(291586);
        field2.setValue("2026/08/05 00:00:00");

        Field field3 = new Field();
        field3.setId(318796);
        field3.setValue("23");

        Field field4 = new Field();
        field4.setId(318795);
        field4.setValue("13718193735");

        Field field5 = new Field();
        field5.setId(318762);
        field5.setValue("{\"fieldKey\":\"skill\",\"fieldType\":21,\"productUniqueId\":\"ba0651a8-da9c-4639-a69c-1298291502ec\",\"fieldValue\":\"预约视频客服\"}");

        Field field6 = new Field();
        field6.setId(318761);
        field6.setValue("2026-08-09 22:00-22:15");



        fieldList.addAll(Arrays.asList( field2, field3, field4, field5, field6));

        ticketFormModel.setFields(fieldList);

        ticketUpdateModel.setForm(ticketFormModel);

        ticketUpdateRequest.setModel(ticketUpdateModel);

        System.out.println(mapper.writeValueAsString(ticketUpdateModel));
        TicketUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateRequest.expires(24 * 3600);
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(mapper.writeValueAsString(ticketUpdateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stkjTimeSlotOccupyBySkillId(){

        ClientConfiguration configuration = new ClientConfiguration(
                "8a76addc284eb94acc62468e25275a45",          // AccessKeyId
                "x8nB8bj26Sf6md181n31");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        List<String> timeSlotDates = new ArrayList<>();
        timeSlotDates.add("2026-08-09");
        TimeSlotOccupyBySkillIdRequest request = new TimeSlotOccupyBySkillIdRequest();
        request.setFieldId(309776);
        request.setFormId(34594);
        request.setSkillId(1920);
        request.setTimeSlotDate("2026-08-09");
        request.setStartTime("22:00");
        request.setEndTime("22:15");

        TimeSlotOccupyBySkillIdResponse response;

        try {
            response = client.getResponseModel(request);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStkjWorkflowTicket() throws JsonProcessingException {

        ClientConfiguration configuration = new ClientConfiguration(
                "8a76addc284eb94acc62468e25275a45",          // AccessKeyId
                "x8nB8bj26Sf6md181n31");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketUpdateRequest ticketUpdateRequest = new TicketUpdateRequest();

        // 请求参数
        TicketUpdateModel ticketUpdateModel = new TicketUpdateModel();
        ticketUpdateModel.setId(631472);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(34594);
        ticketFormModel.setTaskId("StartEvent_1uwp14d-aa7c73e5-46b4-468f-8cfc-f0446a9e7c08");
        ticketFormModel.setName("视频客服预约表单");
        ticketFormModel.setTimeSlotOccupyId(362681);

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field2 = new Field();
        field2.setId(309776);
        field2.setValue("2026-08-09 22:00-22:15");

        Field field3 = new Field();
        field3.setId(309763);
        field3.setValue("石头科技官方热线（4009001755）");

        Field field4 = new Field();
        field4.setId(337114);
        field4.setValue("天润测试");

        Field field5 = new Field();
        field5.setId(280081);
        field5.setValue("天润测试");

        Field field6 = new Field();
        field6.setId(309771);
        field6.setValue("13718193735");

        Field field7 = new Field();
        field7.setId(309766);
        field7.setValue("天润测试");

        Field field8 = new Field();
        field8.setId(337154);
        field8.setValue("{\"fieldKey\":\"skill\",\"fieldType\":21,\"productUniqueId\":\"c8661af3-cefb-4d44-9644-18d39788146d\",\"fieldValue\":\"拆机\"}");



        fieldList.addAll(Arrays.asList( field2, field3, field4, field5, field6, field7, field8));

        ticketFormModel.setFields(fieldList);

        ticketUpdateModel.setForm(ticketFormModel);

        ticketUpdateRequest.setModel(ticketUpdateModel);

        System.out.println(mapper.writeValueAsString(ticketUpdateModel));
        TicketUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateRequest.expires(24 * 3600);
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(mapper.writeValueAsString(ticketUpdateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}