package com.tinet.clink.openapi.ticket;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author huwk
 * @date 2020/11/16
 **/
public class HttpTest {

    public static String httpClientUploadFile(String url, File file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        //每个post参数之间的分隔。随意设定，只要不会和其他的字符串重复即可。
        String boundary ="--------------4585696313564699";
        try {
            //文件名
            String fileName = file.getName();
            HttpPost httpPost = new HttpPost(url);
            //设置请求头
//            httpPost.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
            httpPost.setHeader("enterpriseId","3000000");

            //HttpEntity builder
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //字符编码
            builder.setCharset(Charset.forName("UTF-8"));
            //模拟浏览器
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //boundary
//            builder.setBoundary(boundary);
            //multipart/form-data
            builder.addPart("123456",new FileBody(file));
            builder.addPart("123456",new FileBody(file));
            builder.addPart("123456",new FileBody(file));
            // binary
//            builder.addBinaryBody("name=\"multipartFile\"; filename=\"test.docx\"", new FileInputStream(file), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            //其他参数
            builder.addTextBody("model", "{\"topic\":\"无\",\"level\":0,\"state\":[\"小莹莹自定义状态\"],\"form\":{\"formId\":1632,\"formName\":\"测试0811\",\"operator\":\"我是你的宽哥哥哈哈(9999)\",\"fields\":[{\"id\":27169,\"type\":14,\"name\":\"yy附件\"}]},\"storing\":0,\"customerId\":74973,\"source\":0,\"close\":0,\"tags\":[],\"workflowId\":514,\"type\":2,\"handlerId\":141457,\"handlerType\":0,\"stateSelected\":\"小莹莹自定义状态\",\"callId\":\"medias_1-1605345565.27\",\"relateTicketId\":[],\"focus\":[]}",  ContentType.create("text/plain", Consts.UTF_8));
            //HttpEntity
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            // 执行提交
            HttpResponse response = httpClient.execute(httpPost);
            //响应
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println("result"+result);
        return result;
    }

    public static void main(String[] args) {
        httpClientUploadFile("http://agent.clink.com:8092/upload",new File("C:\\Users\\VENKY\\Desktop\\服务总结批量导出数据-2020.10.10.xls"));
    }
}
