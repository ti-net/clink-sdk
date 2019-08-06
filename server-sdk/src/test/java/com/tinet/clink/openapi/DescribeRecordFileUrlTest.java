package com.tinet.clink.openapi;

import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.cdr.DescribeRecordFileUrlRequest;
import com.tinet.clink.openapi.response.cdr.DescribeRecordFileUrlResponse;

/**
 * @author Wangyl
 * @date 2019/7/13
 */
public class DescribeRecordFileUrlTest {

    public static void main(String[] args) {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "02f43c284b83e103ea3101f443a1c8c3",          // AccessKeyId
                "D3f67o53487t1xfom5jT");     // AccessKeySecret

        configuration.setHost("api-bj-test.clink.cn");
        Client client = new Client(configuration);

        // 创建API请求并设置参数
        DescribeRecordFileUrlRequest request = new DescribeRecordFileUrlRequest();
        request.setMainUniqueId("medias_2-1562918706.13");

        try {
            DescribeRecordFileUrlResponse response = client.getResponseModel(request);
            System.out.println(response.getRecordFileUrl());
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
