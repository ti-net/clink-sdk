package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.rasr.RobotRasrRequest;
import com.tinet.clink.cc.response.rasr.RobotRasrResponse;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class RobotRasrTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws Exception {

        RobotRasrRequest request = new RobotRasrRequest();
        request.setMainUniqueId("medias_1-1703239426.143");

        RobotRasrResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
