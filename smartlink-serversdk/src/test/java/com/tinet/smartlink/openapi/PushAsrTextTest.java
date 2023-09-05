package com.tinet.smartlink.openapi;

import com.tinet.smartlink.openapi.exceptions.ClientException;
import com.tinet.smartlink.openapi.exceptions.ServerException;
import com.tinet.smartlink.openapi.model.sqc.AsrText;
import com.tinet.smartlink.openapi.model.sqc.SentenceResult;
import com.tinet.smartlink.openapi.request.sqc.PushAsrTextRequest;
import com.tinet.smartlink.openapi.response.sqc.PushAsrTextResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Smartlink SDK测试类
 * 推送转写文本
 *
 * @author Ailos
 * @version 1.0
 * @date 2022/8/8 16:51
 */
public class PushAsrTextTest {
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
        PushAsrTextRequest request = new PushAsrTextRequest();
        request.setUniqueId("f93902e8e14f4431997dbf72fd39fbf2-1660032894.1238");
        request.setUserId("1234567");
        request.setRecordSide("client");
        // 设置转写文本参数
        AsrText asrText = new AsrText();
        asrText.setBizDuration(34180);
        // 设置转写结果参数
        List<SentenceResult> result = new ArrayList<>();
        SentenceResult sentenceResult = new SentenceResult();
        sentenceResult.setChannelId(1);
        sentenceResult.setBeginTime(1410);
        sentenceResult.setEndTime(2330);
        sentenceResult.setEmotionValue(4);
        sentenceResult.setSilenceDuration(0);
        sentenceResult.setSpeechRate(65);
        sentenceResult.setText("World");
        result.add(sentenceResult);
        asrText.setResult(result);
        request.setAsrText(asrText);

        PushAsrTextResponse response;
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
