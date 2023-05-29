package com.tinet.clink.openapi;

import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.ChatMessageRequest;
import com.tinet.clink.ticket.model.Field;
import com.tinet.clink.ticket.model.TicketFormModel;
import com.tinet.clink.ticket.model.TicketHistoryForm;
import com.tinet.clink.ticket.model.TicketSaveModel;
import com.tinet.clink.ticket.request.GetTicketDetailRequest;
import com.tinet.clink.ticket.request.ListTicketRequest;
import com.tinet.clink.ticket.request.TicketSaveRequest;
import com.tinet.clink.ticket.response.GetTicketDetailResponse;
import com.tinet.clink.ticket.response.ListTicketResponse;
import com.tinet.clink.ticket.response.TicketSaveResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class TicketTest extends AbstractTest{

    @Test
    public void list(){
        ListTicketRequest listTicketRequest=new ListTicketRequest();

        listTicketRequest.setStartTime("2020-05-01 00:00:00");
        listTicketRequest.setEndTime("2020-08-10 23:59:59");
        listTicketRequest.setOffset(0);
        listTicketRequest.setLimit(10);
        try {
            ListTicketResponse response=client.getResponseModel(listTicketRequest);

            System.out.println(response);

            System.out.println(111);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getTicketDeatil() {
        GetTicketDetailRequest getTicketDetailRequest = new GetTicketDetailRequest();

        getTicketDetailRequest.setId(372957);
        try {
            GetTicketDetailResponse response = client.getResponseModel(getTicketDetailRequest);

            TicketHistoryForm[] forms = response.getTicketDetail().getForms();
            if(Objects.nonNull( response.getTicketDetail()) && Objects.nonNull(response.getTicketDetail().getForms())){
                Integer formId = forms[0].getFormId();
                String formName = forms[0].getFormName();
                System.out.println(formId + "-----" + formName);

                System.out.println(111);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTicket(){
        int count=3;
        for(int i=0;i<count;i++){
            saveTicketSample(0);
        }

    }

    private void saveTicketSample(int i){
        // 创建请求的request
        TicketSaveRequest ticketSaveRequest = new TicketSaveRequest();

        // 构建工单保存实体对象
        TicketSaveModel ticketSaveModel = new TicketSaveModel();
        ticketSaveModel.setWorkflowId(3767);
        ticketSaveModel.setClose(0);
        ticketSaveModel.setHandlerType(0);
        ticketSaveModel.setHandlerId(-7049);
        ticketSaveModel.setTopic("测试创建工单"+i);
        ticketSaveModel.setStateSelected("处理中");


        Field fieldx = new Field();
        fieldx.setId(120102);
        fieldx.setName("cy属性附件");
        fieldx.setRequired(0);
        fieldx.setType(14);

        Field fieldx1 = new Field();
        fieldx1.setId(90379);
        fieldx1.setName("lint单行文本");
        fieldx1.setRequired(1);
        fieldx1.setType(1);
        fieldx1.setValue("单行文本");

        Field fieldx2 = new Field();
        fieldx2.setId(120097);
        fieldx2.setName("cy属性多行");
        fieldx2.setRequired(1);
        fieldx2.setType(5);
        fieldx2.setValue("多行");

        Field[] systemFields=new Field[]{fieldx,fieldx1,fieldx2};
        ticketSaveModel.setCustomizeSystemFields(systemFields);

        List<File> fileList = new ArrayList<>();

        File file = new File("/Users/caowenyi/Pictures/秒表.jpg");

        fileList.add(file);

        // 存放需要上传的附件的map对象，key为自定义字段的id，value为该字段需要保存的附件集合
        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("120102", fileList);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(9419);

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(93915);
        field.setName("ccy数值1");
        field.setRequired(0);
        field.setType(2);

        fieldList.add(field);

        // 将表单中的字段集合赋值到表单对象中
        ticketFormModel.setFields(fieldList);

        // 将表单对象赋值到需要保存的工单对象中
        ticketSaveModel.setForm(ticketFormModel);

        //将请求参数赋值到 request中
        ticketSaveRequest.setModel(ticketSaveModel);
        ticketSaveRequest.setFileMap(fileMap);



        TicketSaveResponse ticketSaveResponse=null;

        try {
            ticketSaveResponse = client.getResponseModel(ticketSaveRequest);
            System.out.println(111);
        } catch (Exception e) {
            System.out.println(ticketSaveResponse);
            e.printStackTrace();
        }
    }
}