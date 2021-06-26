package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.kb.DescribeArticleRequest;
import com.tinet.clink.openapi.request.kb.ListArticlesRequest;
import com.tinet.clink.openapi.response.kb.DescribeArticleResponse;
import com.tinet.clink.openapi.response.kb.ListArticlesResponse;
import org.junit.Test;

/**
 * 文章列表请求示例
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListArticleTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void article() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DescribeArticleRequest request = new DescribeArticleRequest();
        request.setId(3653);

        try {
            DescribeArticleResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getArticle()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listArticle() {

        /*// 创建访问服务的客户端实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListArticlesRequest request = new ListArticlesRequest();

        // 赋值参数
        request.setKbType(0);
        request.setDirectoryId(1245);
        request.setKbId(771);
        /*request.setKeyword("欢迎");
        request.setOrder("none");*/
        request.setOffset(0);
        request.setLimit(20);

        try {
            ListArticlesResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response));
            System.out.println(response.getArticles().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
