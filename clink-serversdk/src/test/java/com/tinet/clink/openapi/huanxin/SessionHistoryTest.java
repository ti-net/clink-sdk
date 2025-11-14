package com.tinet.clink.openapi.huanxin;

import com.tinet.clink.huanxin.request.SessionHisRequest;
import com.tinet.clink.huanxin.request.SessionMsgRequest;
import com.tinet.clink.huanxin.response.SessionHisResponse;
import com.tinet.clink.huanxin.response.SessionMsgResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

public class SessionHistoryTest extends AbstractTest {

    @Test
    public void sessionHistorys() throws Exception {
        SessionHisRequest request = new SessionHisRequest();
        request.setPer_page(10);
        request.setPage(1);
        SessionHisResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

    @Test
    public void sessionMsgs() throws Exception {
        SessionMsgRequest request = new SessionMsgRequest();
        request.setSize(10);
        request.setPage(0);
        request.setSessionServiceId("26ac4b2f-3fc8-4951-a4b7-e202b8109c4a");
        SessionMsgResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }
}
