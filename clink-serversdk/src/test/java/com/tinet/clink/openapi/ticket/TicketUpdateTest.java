package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.Field;
import com.tinet.clink.openapi.model.TicketFormModel;
import com.tinet.clink.openapi.model.TicketUpdateModel;
import com.tinet.clink.openapi.request.ticket.TicketSaveRequest;
import com.tinet.clink.openapi.request.ticket.TicketUpdateRequest;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import com.tinet.clink.openapi.response.ticket.TicketUpdateResponse;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 工单更新请求示例
 * @date 2020/11/17
 **/
public class TicketUpdateTest extends AbstractTest {

    @Test
    public void updateTicket() {


        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketUpdateRequest ticketUpdateRequest = new TicketUpdateRequest();

        //创建请求对象
        TicketUpdateModel updateModel = new TicketUpdateModel();

        updateModel.setId(162885);
        updateModel.setTopic("你好好好好");
        updateModel.setStateSelected("7");

        // 需要更新的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(1890);

        // 存放需要更新的字段对象集合
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(33929);
        field.setName("kk_串号");
        field.setRequired(0);
        field.setType(1);
        field.setValue("100111122222");

        Field field1 = new Field();
        field1.setId(33930);
        field1.setName("kk_产品名称");
        field1.setRequired(0);
        field1.setType(1);
        field1.setValue("复选1,12311");

        Field field2 = new Field();
        field2.setId(31075);
        field2.setName("gq文件1");
        field2.setType(14);
        field2.setRequired(0);
        field2.setValue("ticket/20201126/8000559/5fd603ce-bfd4-41e8-ae30-b1246ee86538.");
//
        fieldList.add(field);
        fieldList.add(field1);
        fieldList.add(field2);

        ticketFormModel.setFields(fieldList);

        // 需要上传的文件的集合
        List<File> fileList = new ArrayList<>();

        File file = new File("E:\\工单信息 - 副本.xlsx");
        File file1 = new File("E:\\工单信息 - 副本.xlsx");

        fileList.add(file);
        fileList.add(file1);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("31075" + "", fileList);

        updateModel.setForm(ticketFormModel);

        // 将参数 set到请求对象中
        ticketUpdateRequest.setFileMap(fileMap);
        ticketUpdateRequest.setModel(updateModel);
        TicketUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
