package com.tinet.clink.openapi;

import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.request.cdr.DownloadRecordFileRequest;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wangyl
 * @date 2019/7/13
 */
public class DownloadRecordFileTest {

    public static void main(String[] args) {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "02f43c284b83e103ea3101f443a1c8c3",          // AccessKeyId
                "D3f67o53487t1xfom5jT");     // AccessKeySecret

        configuration.setHost("api-bj-test.clink.cn");
        Client client = new Client(configuration);

        // 创建API请求并设置参数
        DownloadRecordFileRequest request = new DownloadRecordFileRequest();
        request.setMainUniqueId("medias_2-1562918706.13");

        InputStream inputStream = null;
        String filename;

        try {
            HttpResponse response = client.doAction(request);
            inputStream = response.getEntity().getContent();
            filename = response.getHeaders("Content-Disposition")[0].getValue().substring(21);

            File f = new File(filename);
            FileOutputStream os = new FileOutputStream(f);
            byte[] buffer = new byte[4096];
            int r;
            while ((r = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, r);
            }
            os.flush();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
