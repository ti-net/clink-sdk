package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.model.Field;
import com.tinet.clink.openapi.model.TicketFormModel;
import com.tinet.clink.openapi.model.TicketSaveModel;
import com.tinet.clink.openapi.request.ticket.TicketSaveRequest;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * @author huwk
 * @date 2020/11/17
 **/
public class TicketSaveTest extends AbstractTest {

    @Test
    public void saveTicket() {

        TicketSaveRequest ticketSaveRequest = new TicketSaveRequest();

        TicketSaveModel ticketSaveModel=new TicketSaveModel();
        ticketSaveModel.setWorkflowId(707);
        ticketSaveModel.setClose(0);
        ticketSaveModel.setCreateTime(new Date());
        ticketSaveModel.setHandlerType(1);
        ticketSaveModel.setHandlerId(32939);
        ticketSaveModel.setLevel(1);
        ticketSaveModel.setTopic("奇虎模板测试创建工单");
        ticketSaveModel.setStateSelected("9");

        TicketFormModel ticketFormModel=new TicketFormModel();
        ticketFormModel.setId(1890);

        List<Field> fieldList=new ArrayList<>();

        Field field=new Field();

        field.setId(33929);
        field.setName("kk_串号");
        field.setRequired(0);
        field.setType(1);
        field.setValue("100");

        Field field1=new Field();

        field1.setId(33930);
        field1.setName("kk_产品名称");
        field1.setRequired(0);
        field1.setType(1);
        field1.setValue("复选1,复选2,复选3");

        Field field2=new Field();


        field2.setId(31075);
        field2.setName("gq文件1");
        field2.setType(14);
        field2.setRequired(0);
//
        fieldList.add(field);
        fieldList.add(field1);
        fieldList.add(field2);

        ticketFormModel.setFields(fieldList);


        ticketSaveModel.setForm(ticketFormModel);

        ticketSaveRequest.setModel(ticketSaveModel);


        List<File> fileList=new ArrayList<>();

        File file=new File("E:\\工单信息 - 副本.xlsx");

        fileList.add(file);


        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("31075",fileList);

        ticketSaveRequest.setFileMap(fileMap);

        TicketSaveResponse ticketSaveResponse;

        try {
            ticketSaveResponse = client.getResponseModel(ticketSaveRequest);
            System.out.println(111);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
