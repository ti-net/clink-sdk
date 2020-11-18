package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.ticket.TicketUploadRequest;
import com.tinet.clink.openapi.response.ticket.TicketUploadResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * @author huwk
 * @date 2020/11/17
 **/
public class TicketSaveTest extends AbstractTest {

    @Test
    public void  test() throws ServerException, ClientException {

        TicketUploadRequest request = new TicketUploadRequest();

        Map<String, List<File>> fileMap = new HashMap<>();
        File file = new File("C:\\Users\\VENKY\\Desktop\\服务总结批量导出数据-2020.10.10.xls");
        fileMap.put("12345", Collections.singletonList(file));

        TicketSaveModel model = new TicketSaveModel();
//        model.setTopic("哈哈哈");
        model.setCallId("啦啦啦啦啦啦啦啦");
        model.setLevel(1);
        model.setState(new String[] {"啦啦啦哈哈哈","中国人的歌曲"});

//        request.setModel("{\"topic\":\"无\",\"level\":0,\"state\":[\"小莹莹自定义状态\"],\"form\":{\"formId\":1632,\"formName\":\"测试0811\",\"operator\":\"我是你的宽哥哥哈哈(9999)\",\"fields\":[{\"id\":27169,\"type\":14,\"name\":\"yy附件\"}]},\"storing\":0,\"customerId\":74973,\"source\":0,\"close\":0,\"tags\":[],\"workflowId\":514,\"type\":2,\"handlerId\":141457,\"handlerType\":0,\"stateSelected\":\"小莹莹自定义状态\",\"callId\":\"medias_1-1605345565.27\",\"relateTicketId\":[],\"focus\":[]}");
        request.setModel(model);

        request.setFileMap(fileMap);

        TicketUploadResponse response = client.getResponseModel(request);



    }
}
