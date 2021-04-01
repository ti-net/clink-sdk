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
    <version>2.0.35</version>
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

### V 2.0.22
```
1.呼叫控制管理接口-网上回呼-网上回呼
```

### V 2.0.23
```
1.增加工具类EncryptUtil
```

### V 2.0.24
```
1.查询呼入通话记录列表 返回值增加标签字段[tags]
2.查看呼入通话记录 返回值增加标签字段[tags]
3.同步呼入通话记录 返回值增加标签字段[tags]
4.查询外呼通话记录列表 返回值增加标签字段[tags]
5.查看外呼通话记录 返回值增加标签字段[tags]
6.同步外呼通话记录 返回值增加标签字段[tags]
```

### V 2.0.25
```
1.创建客户资料 增加外部id字段[externalId]
2.更新客户资料 增加外部id字段[externalId]
3.增加根据外部id更新客户资料[UpdateCustomerByExternalIdRequest]
```

### V 2.0.26
```
1.设置-语音导航设置-查询语音导航列表，修改因参数名称错误而导致的返回值丢失问题
2.设置-语音导航设置-查询语音导航节点列表，修改因参数名称错误而导致的返回值丢失问题
```

### V 2.0.28
```
1.报表接口-呼叫中心-中继报表-来电分析
2.报表接口-呼叫中心-中继报表-接听率分析
3.报表接口-呼叫中心-外呼报表-预览外呼
4.webcall请求接口增加参数clidType
5.记录查询接口-在线客服-会话记录查询
6.记录查询接口-在线客服-会话详情查询
7.记录查询接口-在线客服-留言记录查询
8.记录查询接口-在线客服-满意度记录查询
9.记录查询接口-在线客服-聊天记录查询
```
### V 2.0.32
```
1.记录查询接口-通话录音-增加获取通话详情录音地址接口
```

### V 2.0.33
```
1.工单接口-工单模块-增加工单创建接口
2.工单接口-工单模块-增加工单更新接口
3.工单接口-工单模块-增加工单模板列表查询接口
4.工单接口-工单模块-增加工单模板查询接口
```

### V 2.0.34
```
客户中心接口-客户资料模块-增加客户资料删除接口
```
### V 2.0.35
```
数据-质检记录-获取质检转写结果
```

### V 2.0.36
```
业务记录接口-业务记录-增加业务记录详情接口
```
### V 2.0.37
```
增值功能-增加手机号码状态检测接口
```
