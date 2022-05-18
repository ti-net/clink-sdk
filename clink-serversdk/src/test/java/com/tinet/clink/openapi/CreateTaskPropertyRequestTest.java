package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.model.CreateTaskInventoryModel;
import com.tinet.clink.openapi.model.TaskInventoryCustomizeFieldModel;
import com.tinet.clink.openapi.request.call.task.CreateTaskPropertyRequest;
import com.tinet.clink.openapi.response.call.task.CreateTaskPropertyResponse;
import org.junit.Test;

/**
 * @author: wangpw
 * @date: 2022/5/17
 * @description:
 */
public class CreateTaskPropertyRequestTest extends AbstractTest {

    @Test
    public void createTaskPropertyTest() throws Exception {
        CreateTaskPropertyRequest request = new CreateTaskPropertyRequest();
        //外呼任务名称
        request.setName("任务名称111");
        //是否分配，0：不分配；1：分配
        request.setAssignation(1);
        //分配规则 0：顺序分配 ，1： 随机分配
        request.setAssignationType(1);
        //分配座席号
        String[] cnos = new String[]{"0611"};
        request.setCnos(cnos);
        //号码排重模式: 0：不排重；1：本任务排重， 2：任务间排重
        request.setDuplicateStrategy(1);
        //创建人姓名
        request.setCreatorName("wangpw");
        request.setStart(1);
        //任务详情
        CreateTaskInventoryModel[] taskInventory = new CreateTaskInventoryModel[1];
        taskInventory[0] = new CreateTaskInventoryModel();
        taskInventory[0].setCustomerName("customer1");
        taskInventory[0].setCustomerTel("15176019948");
        taskInventory[0].setRemark("remark1");
        //自定义字段
        TaskInventoryCustomizeFieldModel[] customize = new TaskInventoryCustomizeFieldModel[1];
        customize[0] = new TaskInventoryCustomizeFieldModel();
        customize[0].setName("来源渠道");
        customize[0].setValue("企知道");
        taskInventory[0].setCustomize(customize);
        request.setTaskInventory(taskInventory);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("request-" + mapper.writeValueAsString(request));
        CreateTaskPropertyResponse response = client.getResponseModel(request);
        System.out.println("response-" + mapper.writeValueAsString(response));
    }
}
