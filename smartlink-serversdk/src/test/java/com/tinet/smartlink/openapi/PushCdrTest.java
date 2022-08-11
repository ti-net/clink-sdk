package com.tinet.smartlink.openapi;

import com.tinet.smartlink.openapi.exceptions.ClientException;
import com.tinet.smartlink.openapi.exceptions.ServerException;
import com.tinet.smartlink.openapi.model.sqc.Record;
import com.tinet.smartlink.openapi.request.sqc.PushCdrRequest;
import com.tinet.smartlink.openapi.response.sqc.PushCdrResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Smartlink SDK测试类
 * 推送话单
 *
 * @author Ailos
 * @version 1.0
 * @date 2022/8/8 16:51
 */
public class PushCdrTest {
    @Test
    public void test() {
        SmartlinkClientConfiguration configuration = new SmartlinkClientConfiguration(
                "<your-access-key-id>",          // AccessKeyId
                "<your-access-key-secret>");     // AccessKeySecret

        /*
            设置平台请求地址：
                测试环境：smartlink-sqc-openapi-test.tinetcloud.com
                生产环境：smartlink-sqc-openapi.tinetcloud.com
        */
        configuration.setHost("smartlink-sqc-openapi-test.tinetcloud.com");

        SmartlinkClient smartlinkClient = new SmartlinkClient(configuration);

        // 创建API请求并设置参数
        PushCdrRequest request = new PushCdrRequest();
        request.setUniqueId("f93902e8e14f4431997dbf72fd39fbf2-1660032894.1238");
        request.setUserId("1234567");
        request.setCdrType("cdr_ob_customer");
        request.setCallType(3);
        request.setCdrSource(2);
        request.setStartTime(1660032894L);
        request.setEndTime(1660032894L);
        request.setAnswerTime(1660032894L);
        request.setBridgeTime(1660032894L);
        request.setBridgeDuration(10);
        request.setTotalDuration(10);
        request.setStatus(52);
        request.setEndReason(1);
        request.setRecordFile("demo.mp3");
        // 如果需要录音转写，则设置录音文件信息
        Record record = new Record();
        // 文件地址
        record.setFileUrl("http://xxx.com/demo.mp3");
        // 录音是否长期有效
        record.setErpetualUrl(true);
        // 设置records参数
        List<Record> recordList = new ArrayList<>();
        recordList.add(record);
        request.setRecords(recordList);

        PushCdrResponse response;
        try {
            // 发起API调用并获得返回结果
            response = smartlinkClient.getResponseModel(request);
            // 打印返回结果
            System.out.println(response);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
