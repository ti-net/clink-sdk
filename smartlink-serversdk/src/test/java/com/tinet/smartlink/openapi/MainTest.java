package com.tinet.smartlink.openapi;

import com.tinet.smartlink.openapi.exceptions.ClientException;
import com.tinet.smartlink.openapi.exceptions.ServerException;
import com.tinet.smartlink.openapi.model.sqc.Record;
import com.tinet.smartlink.openapi.request.sqc.PushCdrRequest;
import com.tinet.smartlink.openapi.response.sqc.PushCdrResponse;
import org.apache.http.HttpHost;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 侯法超
 * @date 2021/1/14
 */
public class MainTest {
    @Test
    public  void test1() {
        SmartlinkClientConfiguration configuration = new SmartlinkClientConfiguration();
        // 这些是必须设置的参数
        configuration.setAccessKeyId("59F6WZYJ6PT4G879D318");
        configuration.setAccessKeySecret("u610p9q44llvd1c0qluj06yq3d1946kz");
        configuration.setHost(new HttpHost("smartlink-sqc-openapi-test.tinetcloud.com"));

        SmartlinkClient smartlinkClient = new SmartlinkClient(configuration);

        PushCdrRequest pushCdrRequest = new PushCdrRequest();
        pushCdrRequest.setUserId("8000559");
        pushCdrRequest.setAgentName("工号1002");
        pushCdrRequest.setAgentNumber("cno002");
        pushCdrRequest.setAsrProvider("Tinet");
        pushCdrRequest.setBridgeDuration(54);
        pushCdrRequest.setCallType(3);
        pushCdrRequest.setCdrType("cdr_ob_customer");
        pushCdrRequest.setCdrSource(3);
        pushCdrRequest.setAnswerTime(1656645292L);
        pushCdrRequest.setBridgeTime(1656645292L);
        pushCdrRequest.setCno("cno002");
        pushCdrRequest.setQno("qno002");
        pushCdrRequest.setCustomerNumber("13912345678");
        pushCdrRequest.setQname("队列1002");
        pushCdrRequest.setEndReason(1);
        pushCdrRequest.setEndTime(1656645292L);
        pushCdrRequest.setStartTime(1656645292L);
        pushCdrRequest.setStatus(22);
        pushCdrRequest.setTotalDuration(54);
        // 文件名
        pushCdrRequest.setRecordFile("test01.wav");
        Record recordAgent = new Record();
        String fileUrlAgent = "https://rms-asr-test.oss-cn-beijing.aliyuncs.com/smartlink/8005354/2022-01-25/17394413884%E5%8F%B2%E7%8E%89%E9%BE%99_2022-01-19%2018_48_09.wav?Expires=1643084142&OSSAccessKeyId=TMP.3Keib3DtP3w8w8zRuCKSmPBCHxvGDQxme3BCkfWsuSzeKnAAP4UuCXB8y1AZ2dkgs4j6qej7hBUDDzgz5BiHGYjMbp2SVZ&Signature=UABsKp9CYILyh1eFjVJ3j8sbTT4%3D";
        recordAgent.setFileUrl(fileUrlAgent);

        // 录音是否长期有效
        recordAgent.setErpetualUrl(true);
        List<Record> list = new ArrayList<>();
        list.add(recordAgent);
        pushCdrRequest.setRecords(list);

        /*List<OriginData> originDataList = new ArrayList<>();
        OriginData originData=new OriginData();
        originData.setName("qno_test");
        originData.setValue("outside_shengteng");
        originDataList.add(originData);
        pushCdrRequest.setOriginDataList(originDataList);*/

        Random random = new Random();
        String uniqueid = UUID.randomUUID().toString().replace("-", "") + "-" + pushCdrRequest.getStartTime() + "." + random.nextInt(1000);
        pushCdrRequest.setUniqueId(uniqueid);
        pushCdrRequest.setMainUniqueId(uniqueid);

        PushCdrResponse responseModel = null;
        try {
            responseModel = smartlinkClient.getResponseModel(pushCdrRequest);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        System.out.println(responseModel);
    }

}
