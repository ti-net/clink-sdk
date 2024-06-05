package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.kb.request.ChatListConversationsRequest;
import com.tinet.clink.kb.request.DescribeFaqMediaUrlRequest;
import com.tinet.clink.kb.request.DescribeFileMediaUrlRequest;
import com.tinet.clink.kb.response.ChatListConversationsResponse;
import com.tinet.clink.kb.response.DescribeFaqMediaUrlResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class MediaTest extends AbstractTest {

    @Test
    public void faqUrlTest() {

        ClientConfiguration configuration = new ClientConfiguration(
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        DescribeFaqMediaUrlRequest request = new DescribeFaqMediaUrlRequest();
        request.setFaqId(2057883);
        request.setFileKey("file/attachment/material/8000002/0866ccbfcd734ad49daada2247845863.png");

        try {
            DescribeFaqMediaUrlResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileUrlTest() {

        ClientConfiguration configuration = new ClientConfiguration(
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        DescribeFileMediaUrlRequest request = new DescribeFileMediaUrlRequest();
        request.setFileId(3103);
        request.setInline(true);


        try {
            ResponseModel responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
