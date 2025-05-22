package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.bot.BotTransferRequest;
import com.tinet.clink.cc.response.bot.BotTransferResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 语音导航设置接口测试
 *
 * @author huwk
 * @date 2018/11/15
 **/
@Ignore
public class BotTransferTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询ivr列表单元测试
     */
    @Test
    public void botTransfer() throws ServerException, ClientException, JsonProcessingException {

        BotTransferRequest request = new BotTransferRequest();
        request.expires(86400);
        request.setMainUniqueId("1");
        request.setTransferType("QNO");
        request.setTransferObject("6666");

        BotTransferResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

}
