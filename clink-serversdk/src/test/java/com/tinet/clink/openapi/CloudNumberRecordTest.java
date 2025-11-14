package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.cloudnumber.DownloadCloudNumberRecordFileRequest;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 云手机通话记录单元测试
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 */
public class CloudNumberRecordTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void test() throws Exception {
        DownloadCloudNumberRecordFileRequest request = new DownloadCloudNumberRecordFileRequest();
        request.setUniqueId("medias_1-1710222281.130");

        HttpResponse response = client.doAction(request);

        // 从is 中读取数据写入  m.mp3
        try (InputStream is = response.getEntity().getContent();
             FileOutputStream fos = new FileOutputStream("m.mp3")) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");


    }

}

