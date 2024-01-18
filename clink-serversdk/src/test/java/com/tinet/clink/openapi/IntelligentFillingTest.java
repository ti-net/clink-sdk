package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.intelligent.IntelligentFillingInsightRequest;
import com.tinet.clink.cc.response.intelligent.IntelligentFillingInsightResponse;
import org.junit.Test;

/**
 * @author Wangyl
 * @date 2019/8/28
 */
public class IntelligentFillingTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void intelligentFillingTest() throws Exception {
        IntelligentFillingInsightRequest request = new IntelligentFillingInsightRequest();
        request.setMainUniqueId("medias_1-1704734238.248");
        request.setFormName("wwd1");
        request.setType(1);
        request.setCno("0611");

        IntelligentFillingInsightResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));

    }
}
