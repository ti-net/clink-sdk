package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.kb.request.ChatUpdateConversationRequest;
import com.tinet.clink.kb.request.SearchKnowledgeOnOpenRequest;
import com.tinet.clink.kb.response.ChatUpdateConversationResponse;
import com.tinet.clink.kb.response.SearchKnowledgeOnOpenResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class SearchKnowledgeTest extends AbstractTest {


    @Test
    public void search() {

        ClientConfiguration configuration = new ClientConfiguration(
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        SearchKnowledgeOnOpenRequest request = new SearchKnowledgeOnOpenRequest();
        request.setKeyword("2");

        try {
            SearchKnowledgeOnOpenResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
