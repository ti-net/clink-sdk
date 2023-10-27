package com.tinet.clink.openapi.ticket.childForm;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.childForm.FormConfigModel;
import com.tinet.clink.ticket.model.childForm.OpenapiFormFieldModel;
import com.tinet.clink.ticket.request.childForm.CreateChildFormRequest;
import com.tinet.clink.ticket.response.childForm.CreateChildFormResponse;
import org.junit.Test;

import java.util.ArrayList;


public class CreateChildFormTest extends AbstractTest {

    @Test
    public void createChildForm() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);

        // 创建请求的request
        CreateChildFormRequest listChildFormRequest = new CreateChildFormRequest();
        // 请求参数
        // 子表单名称
        listChildFormRequest.setName("测试接口创建子表单");
        listChildFormRequest.setDescription("测试接口创建子表单");
        listChildFormRequest.setCascade(1);
        // 导出展示方式
        FormConfigModel configModel = new FormConfigModel();
        configModel.setExportColumnMode(1);
        listChildFormRequest.setFormConfig(configModel);

        // 子表单下的字段
        ArrayList<OpenapiFormFieldModel> fieldModels = new ArrayList<>();
        OpenapiFormFieldModel fieldModel = new OpenapiFormFieldModel();
        fieldModel.setFieldId(95262);
        fieldModel.setGroup("1,11,12");
        fieldModel.setGroupDesc("1,11,12描述");
        fieldModel.setRequired(1);
        fieldModel.setType(8);
        fieldModel.setPriority(1);
        fieldModels.add(fieldModel);
        listChildFormRequest.setFields(fieldModels);

        try {
            // 将请求参数赋值到 request中
            CreateChildFormResponse ticketCloseResponse = client.getResponseModel(listChildFormRequest);
            System.out.println(ticketCloseResponse.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
