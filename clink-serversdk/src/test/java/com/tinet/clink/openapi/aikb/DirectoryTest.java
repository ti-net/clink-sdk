package com.tinet.clink.openapi.aikb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.aikb.KnowledgeType;
import com.tinet.clink.aikb.model.FaqAnswerModel;
import com.tinet.clink.aikb.request.DirectoryCreateRequest;
import com.tinet.clink.aikb.request.FaqCreateRequest;
import com.tinet.clink.aikb.response.DirectoryCreateResponse;
import com.tinet.clink.aikb.response.FaqCreateResponse;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class DirectoryTest extends AbstractTest {

    @Test
    public void directoryCreate() {

        ClientConfiguration configuration = new ClientConfiguration(
                "e8a61c46f20809457831f281f973feef",           // AccessKeyId
                "49z710c29PMZhi67j203");     // AccessKeySecret
        configuration.setHost("clink2-openapi-dev.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        DirectoryCreateRequest request = new DirectoryCreateRequest();
        request.setRepositoryId(103681);
        request.setName("目录测试");
        request.setType(KnowledgeType.FAQ);

        try {
            DirectoryCreateResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
