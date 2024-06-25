package com.tinet.clink.openapi.aikb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.aikb.model.FaqAnswerModel;
import com.tinet.clink.aikb.request.ChatListConversationsRequest;
import com.tinet.clink.aikb.request.FaqCreateRequest;
import com.tinet.clink.aikb.response.ChatListConversationsResponse;
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
public class FaqTest extends AbstractTest {

    @Test
    public void faqCreate() {

        ClientConfiguration configuration = new ClientConfiguration(
                "e8a61c46f20809457831f281f973feef",           // AccessKeyId
                "49z710c29PMZhi67j203");     // AccessKeySecret
        configuration.setHost("clink2-openapi-dev.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        FaqCreateRequest request = new FaqCreateRequest();
        request.setRepositoryId(103681);
        request.setDirectoryId(160178);
        request.setQuestion("问题测试");
        List<FaqAnswerModel> answers = new ArrayList<>();
        FaqAnswerModel answer = new FaqAnswerModel();
        answer.setContent("答案测试");
        answer.setType("text");
        answers.add(answer);
        request.setAnswers(answers);

        try {
            FaqCreateResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
