# Clink-SDK
欢迎使用天润融通 “智慧服务平台” 开发者工具套件（SDK）。SDK 对 API 接口调用进行了封装，让您不用复杂编程即可实现 “智慧服务平台” 与自有系统的集成，管理 “智慧服务平台” 的数据配置、呼叫控制、通话记录等各种资源。

## 环境准备
和直接调用 API 接口一样，您也需要一个访问密钥：AccessKeyID/AccessKeySecret。 访问密钥对可以通过登录系统管理后台，在【系统管理-系统对接-接口密钥】模块自助生成。每个访问密钥对可以设置不同的接口访问控制权限。
目前，我们仅支持 Java 语言的 SDK，您需要一个基础的 Java 开发环境，JDK 版本 >= 1.6。

## 安装SDK 
如果您使用 Apache Maven 来管理 Java 项目，只需在项目的 pom.xml 中文件加入相应的依赖项即可
```
<dependency>
    <groupId>com.ti-net</groupId>
    <artifactId>clink-serversdk</artifactId>
    <version>2.0.16</version>
</dependency>
```

## 使用 SDK
以下代码示例展示了使用 SDK 完成服务调用的 3 个主要步骤：  
1. 创建并初始化 Client 实例。  
2. 创建 API 请求并设置参数。  
3. 发起请求并处理应答或异常  

```
package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.queue.ListQueuesRequest;
import com.tinet.clink.openapi.response.config.queue.ListQueuesResponse;

public class Test {
    public static void main(String[] args) {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "<your-access-key-id>",          // AccessKeyId
                "<your-access-key-secret>");     // AccessKeySecret

        Client client = new Client(configuration);

        // 创建API请求并设置参数
        ListQueuesRequest request = new ListQueuesRequest();
        request.setLimit(10);
        request.setOffset(0);

        // 发起请求并处理应答或异常
        ListQueuesResponse response;
        ObjectMapper mapper = new ObjectMapper();

        try {
            response = client.getResponseModel(request);
            try {
                System.out.println(mapper.writeValueAsString(response.getQueues()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
```
# Change Log
### V 2.0.15
```
1.增加httpClient线程池空闲线程回收机制
2.增加失败请求重试机制，最多重试三次
3.补全外呼接口遗漏参数
```
### V 2.0.16
```
1.记录查询接口-外呼通话记录-查询外呼通话记录列表、查询外呼通话记录详情、同步外呼通话记录，返回结果增加虚拟号码
2.记录查询接口-通话录音-下载通话录音文件、查询通话记录地址，增加wav合成文件获取
```

### V 2.0.17
```
1.短信发送接口-管理员和座席可以通过接口进行短信发送
```

### V 2.0.19
```
1.报表接口-呼叫中心-座席工作量报表
2.报表接口-呼叫中心-队列报表
```

### V 2.0.20
```
1.配置管理接口-队列设置-新增队列，增加sayCno、vipSupport属性设置
2.配置管理接口-队列设置-更新队列，增加sayCno、vipSupport属性设置
```
### V 2.0.21
```
1.报表接口-呼叫中心-座席状态统计报表
```