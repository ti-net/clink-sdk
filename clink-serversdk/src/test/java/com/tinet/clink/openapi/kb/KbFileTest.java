package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.kb.model.KbArticleModel;
import com.tinet.clink.kb.request.CreateArticleRequest;
import com.tinet.clink.kb.request.DeleteFileRequest;
import com.tinet.clink.kb.request.ListFileRequest;
import com.tinet.clink.kb.response.CreateArticleResponse;
import com.tinet.clink.kb.response.DeleteFileResponse;
import com.tinet.clink.kb.response.ListFileResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author 周先康
 * @create 2023/3/7 13:42
 */
public class KbFileTest extends KbAbstractTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void filesPage() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListFileRequest request = new ListFileRequest();
        request.setKbId(1040);
        try {
            ListFileResponse responseModel = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(responseModel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteFiles() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteFileRequest request = new DeleteFileRequest();
        String[] ids = {"iVUmoYYBYmvWisIQ_Ugg"};
        String[] filePaths = {"file/openapi/attachment/8001294/1677742038852.zip"};
        request.setFilePaths(filePaths);
        request.setIds(ids);
        try {
            DeleteFileResponse responseModel = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(responseModel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
