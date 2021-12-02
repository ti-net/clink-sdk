package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.KbArticleModel;
import com.tinet.clink.openapi.request.kb.*;
import com.tinet.clink.openapi.response.kb.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章列表请求示例
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListArticleTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createArticle() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        CreateArticleRequest request = new CreateArticleRequest();

        request.setKbId(8462);
        request.setKbType(0);
        request.setDirectoryId(2262);
        request.setTitle("新创建文章接口");
        request.setAgentEnabled(1);
        String[] similars = new String[]{"标题一"};
        request.setSimilars(similars);
        request.setTopStatus(0);
        List<KbArticleModel.Answer> answers = new ArrayList<>();
        KbArticleModel.Answer answer = new KbArticleModel.Answer();
        answer.setAnswer("答案好啊一");
        answers.add(answer);
        request.setAnswers(answers);

        try {
            CreateArticleResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getArticle()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateArticle() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        UpdateArticleRequest request = new UpdateArticleRequest();

        request.setId(3086);
        request.setKbId(846);
        request.setKbType(0);
        request.setDirectoryId(2262);
        request.setTitle("新创建文章接口");
        request.setAgentEnabled(1);
        String[] similars = new String[]{"新标题二"};
        request.setSimilars(similars);
        request.setTopStatus(0);
        List<KbArticleModel.Answer> answers = new ArrayList<>();
        KbArticleModel.Answer answer = new KbArticleModel.Answer();
        answer.setAnswer("新答案好啊一");
        answers.add(answer);
        request.setAnswers(answers);

        try {
            UpdateArticleResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getArticle()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteArticle() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteArticleRequest request = new DeleteArticleRequest();
        request.setId(2994);

        try {
            DeleteArticleResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getArticle()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        request.setEnterpriseId(8000559);

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
