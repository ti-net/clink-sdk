package com.tinet.clink.openapi.ticket.childForm;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.childForm.FormConfigModel;
import com.tinet.clink.ticket.model.childForm.OpenapiFormFieldModel;
import com.tinet.clink.ticket.request.childForm.UpdateChildFormRequest;
import com.tinet.clink.ticket.response.childForm.UpdateChildFormResponse;
import org.junit.Test;

import java.util.ArrayList;


public class UpdateChildFormTest extends AbstractTest {

    @Test
    public void updateChildForm() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);
        // 创建请求的request
        UpdateChildFormRequest updateChildFormRequest = new UpdateChildFormRequest();
        updateChildFormRequest.setFormId(28614);
        // 请求参数
        // 子表单名称
        updateChildFormRequest.setName("测试接口子表单更新");
        updateChildFormRequest.setDescription("测试接口更新子表单");
        updateChildFormRequest.setCascade(1);
        // 导出展示方式
        FormConfigModel configModel = new FormConfigModel();
        configModel.setExportColumnMode(1);
        updateChildFormRequest.setFormConfig(configModel);

        // 子表单下的字段
        ArrayList<OpenapiFormFieldModel> fieldModels = new ArrayList<>();
        OpenapiFormFieldModel fieldModel = new OpenapiFormFieldModel();
        fieldModel.setFieldId(95262);
        fieldModel.setGroup("1,11,12");
        fieldModel.setGroupDesc("1,11,12描述2");
        fieldModel.setRequired(1);
        fieldModel.setType(8);
        fieldModel.setPriority(1);
        fieldModels.add(fieldModel);
        updateChildFormRequest.setFields(fieldModels);

        try {
            // 将请求参数赋值到 request中
            UpdateChildFormResponse ticketCloseResponse = client.getResponseModel(updateChildFormRequest);
            System.out.println(ticketCloseResponse.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
