package com.tinet.clink.openapi.task;

import com.tinet.clink.cc.request.monitor.AgentStatusSubtotalRequest;
import com.tinet.clink.cc.response.monitor.AgentStatusSubtotalResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.util.JsonUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Class For:
 *
 * @author Tinet-yinzk
 * @date 2023/6/9 10:18
 */
public class TaskPropertyTest extends AbstractTest {

    @Test
    public void testAgentStatusSubtotal() {
        AgentStatusSubtotalRequest request = new AgentStatusSubtotalRequest();
        request.setCno("2021");
        try {
            AgentStatusSubtotalResponse response = client.getResponseModel(request);
            System.out.println(JsonUtils.toJson(response));
        } catch (ClientException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDate() {

        // sdk 生成 dateStr 逻辑
        Date sdkDate = new Date();
        long sdkTime = sdkDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateStr = sdf.format(sdkDate);

        System.out.printf("dateStr: %s\n", dateStr);
        long endTime;

        try {
            // auth 解析逻辑
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = sdf2.parse(dateStr);
            endTime = date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("sdkTime: %s, \nendTime: %s ,eq:%s\n", sdkTime, endTime, Objects.equals(sdkTime, endTime));
    }
}
