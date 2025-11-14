package com.tinet.smartlink.openapi;

import com.tinet.smartlink.openapi.exceptions.ClientException;
import com.tinet.smartlink.openapi.exceptions.ServerException;
import com.tinet.smartlink.openapi.request.sqc.CdrSqcDetailsRequest;
import com.tinet.smartlink.openapi.response.sqc.CdrSqcDetailsResponse;
import org.junit.Test;

/**
 * Smartlink SDK测试类
 * 查询话单详情
 *
 * @author Ailos
 * @version 1.0
 * @date 2022/8/8 16:51
 */
public class CdrSqcDetailsTest {
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
        CdrSqcDetailsRequest request = new CdrSqcDetailsRequest();
        request.setUniqueId("f93902e8e14f4431997dbf72fd39fbf2-1660032894.1238");
        request.setUserId("1234567");

        CdrSqcDetailsResponse response;
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
