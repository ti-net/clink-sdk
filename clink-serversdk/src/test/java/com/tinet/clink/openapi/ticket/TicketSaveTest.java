package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.Field;
import com.tinet.clink.openapi.model.Tag;
import com.tinet.clink.openapi.model.TicketFormModel;
import com.tinet.clink.openapi.model.TicketSaveModel;
import com.tinet.clink.openapi.request.ticket.TicketSaveRequest;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * 保存工单请求示例
 *
 * @date 2020/11/17
 **/
public class TicketSaveTest extends AbstractTest {

    @Test
    public void saveTicket() {


        ClientConfiguration configuration = new ClientConfiguration(
                "b521465079b08d07b73f54e2f5845f95",          // AccessKeyId
                "8Qe3qaN322Vp3260i288");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketSaveRequest ticketSaveRequest = new TicketSaveRequest();

        // 请求参数
        TicketSaveModel ticketSaveModel = new TicketSaveModel();
        ticketSaveModel.setWorkflowId(1827);
        ticketSaveModel.setClose(0);
        ticketSaveModel.setCreateTime(new Date());
        ticketSaveModel.setHandlerType(0);
        ticketSaveModel.setHandlerId(156263);
        ticketSaveModel.setLevel(3);
        ticketSaveModel.setTopic("lize测接口创建工单");
        ticketSaveModel.setCreatorId(156263);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(5881);
        ticketFormModel.setName("lize测接口");

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(83466);
        field.setName("lize测多行文本");
        field.setRequired(0);
        field.setType(5);
        field.setValue("1234");

        Field field1 = new Field();
        field1.setId(85482);
        field1.setName("ize测单行文本");
        field1.setRequired(0);
        field1.setType(1);
        field1.setValue("4321");

        fieldList.add(field);
        fieldList.add(field1);

        ticketFormModel.setFields(fieldList);


        Field sysField1 = new Field();
        sysField1.setId(86686);
        sysField1.setName("lize自定义属性");
        sysField1.setRequired(0);
        sysField1.setType(1);
        sysField1.setValue("4321");
        ticketSaveModel.setForm(ticketFormModel);


        Field[] customizeSystemFields = new Field[]{sysField1};
        ticketSaveModel.setCustomizeSystemFields(customizeSystemFields);

        List<File> fileList = new ArrayList<>();

        File file = new File("E:\\工单信息 - 副本.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("31075", fileList);
        //将请求参数赋值到 request中
        ticketSaveRequest.setModel(ticketSaveModel);
        ticketSaveRequest.setFileMap(fileMap);

        TicketSaveResponse ticketSaveResponse;

        try {
            ticketSaveResponse = client.getResponseModel(ticketSaveRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(ticketSaveResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
