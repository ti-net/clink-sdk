package com.tinet.clink.openapi;

import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.cdr.DescribeRecordFileUrlRequest;
import com.tinet.clink.openapi.request.cdr.DownloadRecordFileRequest;
import com.tinet.clink.openapi.response.cdr.DescribeRecordFileUrlResponse;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wangyl
 * @date 2019/8/28
 */
public class RecordFileTest extends AbstractTest {

    String obUniqueId = "medias_1-1566987273.10";
    String ibUniqueId = "medias_1a.21";

    @Test
    public void describeRecordFileUrl() {
        DescribeRecordFileUrlRequest request = new DescribeRecordFileUrlRequest();
        request.setMainUniqueId(ibUniqueId);
        request.setRecordSide(2);
        request.expires(86400);

        try {
            DescribeRecordFileUrlResponse response = client.getResponseModel(request);
            System.out.println(response.getRecordFileUrl());
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadRecordFile() {
        DownloadRecordFileRequest request = new DownloadRecordFileRequest();
        request.setMainUniqueId(ibUniqueId);
//        request.setRecordSide(2);
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
