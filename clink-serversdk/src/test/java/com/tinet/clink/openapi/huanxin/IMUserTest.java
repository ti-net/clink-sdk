package com.tinet.clink.openapi.huanxin;

import com.tinet.clink.huanxin.model.IMRegisterUserModel;
import com.tinet.clink.huanxin.request.IMRegisterUserRequest;
import com.tinet.clink.huanxin.request.IMUpdateUserPwdRequest;
import com.tinet.clink.huanxin.response.IMRegisterUserResponse;
import com.tinet.clink.huanxin.response.IMUserUpdatePwdResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IMUserTest extends AbstractTest {

    @Test
    public void registerUser() throws Exception {
        IMRegisterUserRequest request = new IMRegisterUserRequest();
        List<IMRegisterUserModel> registerUserModels = new ArrayList<>();
        IMRegisterUserModel imRegisterUserModel = new IMRegisterUserModel("tj01", "123456", "田杰");
        registerUserModels.add(imRegisterUserModel);
        request.setRegisterUserModels(registerUserModels);
        IMRegisterUserResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }


    @Test
    public void updateUserPwd() throws Exception {
        IMUpdateUserPwdRequest request = new IMUpdateUserPwdRequest();
        request.setUsername("tj01");
        request.setNewpassword("12345678");
        IMUserUpdatePwdResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }
}
