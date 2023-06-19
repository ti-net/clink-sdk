package com.tinet.clink.openapi.task;

import com.tinet.clink.cc.request.cdr.ListTodayCdrsByCnoRequest;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.util.JsonUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
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
        // 任务清单列表
//        ListAgentTaskInventoriesRequest request = new ListAgentTaskInventoriesRequest();
//        // 25127
//        request.setCno("2021");
//        request.setTaskId(25127);

// 任务清单详情
//        AgentTaskInventoryDetailRequest request = new AgentTaskInventoryDetailRequest();
//        request.setCno("2021");
//        request.setTaskId(25127);
//        request.setInventoryId(1109184);

        // 处理 - 任务明细
//        UpdateTaskInventoryHandleStatusRequest request = new UpdateTaskInventoryHandleStatusRequest();
//        request.setInventoryId(1109184);
//        request.setHandleStatus(1);
//        request.setHandleStatusDetail("已处理");

        // 任务执行状态
//        TaskPropertyExecStatusesRequest request = new TaskPropertyExecStatusesRequest();

        // 更新明细 - 任务明细
//        UpdateTaskInventoryFormInfoRequest request = new UpdateTaskInventoryFormInfoRequest();
//        request.setInventoryId(1109184);
//        request.setCustomerName("张三");
//        CustomizeField[] customzie = new CustomizeField[1];
//        customzie[0] = new CustomizeField();
//        customzie[0].setName("asd");
//        customzie[0].setValue("123");
//        customzie[0].setType(11);
//        request.setCustomize(customzie);

//        ListEnterpriseNoAnswerRequest request = new ListEnterpriseNoAnswerRequest();
//        request.setCno("2021");

//        HandleEnterpriseNoAnswerRequest request =new HandleEnterpriseNoAnswerRequest();
//        request.setNaId(4073);
//        request.setRemark("接口处理");

//        ListOrderCallbackRequest request = new ListOrderCallbackRequest();
//        request.setCno("2021");

//        HandleOrderCallbackRequest request =new HandleOrderCallbackRequest();
//        request.setId(159);
//        request.setCno("2021");
        // 今日通话记录，字段缺少的比较严重， { "callId" : null, "uniqueId" : "medias_1-1686542927.83", "hotline" : "02138276434",
        //    "customerNumber" : "181****5408", "customerNumberEncrypt" :
        //    "TYTM5NThhOGZkYmFhMzFjN2Y0Zjk5MDUyZDM1ZDUzNGE=", "customerProvince" : "四川", "customerCity" : "成都", "qno" :
        //    "1211", "cno" : "2021", "clientName" : null, "clientNumber" : null, "clientRingingTime" : null, "callType"
        //    : "呼入", "startTime" : 1686542929, "endTime" : null, "bridgeTime" : null, "bridgeDuration" : null,
        //    "totalDuration" : null, "ivrName" : "yinzkTest", "status" : null, "statusCode" : null, "endReason" : null,
        //    "sipCause" : null, "recordFile" : null, "userField" : null, "mark" : null, "tags" : null, "qname" :
        //    "yinzk(闲人免进队列！！)", "markData" : null, "joinQueueTime" : null, "leaveQueueTime" : null, "ibWaitDuration" :
        //    null, "clientOffhookTime" : null, "sayVoiceDuration" : null, "ibRingingDuration" : null,
        //    "queueAnswerInTime" : null, "evaluation" : null, "hotlineName" : null, "firstJoinQueueTime" : null }
//        ListHistoryCdrsRequest request = new ListHistoryCdrsRequest();
//        request.setLimit(10);
//        request.setOffset(0);

        ListTodayCdrsByCnoRequest request = new ListTodayCdrsByCnoRequest();
        request.setCno("2021");
        request.setLimit(10);
        request.setStartTime(OffsetDateTime.now().toEpochSecond());

        try {
            ResponseModel response = client.getResponseModel(request);
            System.out.println(JsonUtils.toJson(response, true));
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
