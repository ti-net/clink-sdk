package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.kb.model.CorpusRecordModel;
import com.tinet.clink.kb.request.CreateCorpusRequest;
import com.tinet.clink.kb.request.DeleteCorpusRequest;
import com.tinet.clink.kb.request.ListCorporaRequest;
import com.tinet.clink.kb.request.UpdateCorpusRequest;
import com.tinet.clink.kb.response.CreateCorpusResponse;
import com.tinet.clink.kb.response.DeleteCorpusResponse;
import com.tinet.clink.kb.response.ListCorporaResponse;
import com.tinet.clink.kb.response.UpdateCorpusResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feizq
 * @date 2022/06/16
 **/
public class CorpusTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listCorpus() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListCorporaRequest request = new ListCorporaRequest();
        request.setBotId("996712");
        request.setSqId(270138);
        request.setOffset(0);
        request.setLimit(100);

        try {
            ListCorporaResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getCorpora()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createCorpus() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        CreateCorpusRequest request = new CreateCorpusRequest();
        request.setBotId("581926");
        List<CorpusRecordModel> records = new ArrayList<>();
        CorpusRecordModel model = new CorpusRecordModel();
        model.setSqId(167189);
        List<CorpusRecordModel.Corpus> corpusList = new ArrayList<>();
        CorpusRecordModel.Corpus corpus = new CorpusRecordModel.Corpus();
        corpus.setCorpusName("创建接口增加语料测试");
        CorpusRecordModel.Corpus corpus1 = new CorpusRecordModel.Corpus();
        corpus1.setCorpusName("创建接口增加语料测试1");
        corpusList.add(corpus);
        corpusList.add(corpus1);
        model.setCorpusList(corpusList);
        records.add(model);
        request.setRecords(records);

        try {
            CreateCorpusResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCorpus() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        UpdateCorpusRequest request = new UpdateCorpusRequest();
        request.setBotId("581926");
        request.setSourceCorpusName("创建接口增加语料测试");
        request.setTargetCorpusName("创建接口增加语料测试11");
        request.setSqId(167189);

        try {
            UpdateCorpusResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCorpus() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteCorpusRequest request = new DeleteCorpusRequest();
        request.setBotId("581926");
        request.setSqId(167189);
        request.setCorpusNames(new ArrayList<String>(){{
            add("创建接口增加语料测试11");
        }});

        try {
            DeleteCorpusResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
