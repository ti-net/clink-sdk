package com.tinet.clink.openapi;

import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.cc.request.cdr.DescribeDetailRecordFileUrlRequest;
import com.tinet.clink.cc.request.cdr.DescribeRecordFileUrlRequest;
import com.tinet.clink.cc.request.cdr.DownloadDetailRecordFileRequest;
import com.tinet.clink.cc.request.cdr.DownloadRecordFileRequest;
import com.tinet.clink.cc.response.cdr.DescribeDetailRecordFileUrlResponse;
import com.tinet.clink.cc.response.cdr.DescribeRecordFileUrlResponse;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Wangyl
 * @date 2019/8/28
 */
public class RecordFileTest extends AbstractTest {

    String obUniqueId = "medias_1-1604542113.9";
    String ibUniqueId = "medias_1-1609221606.6";
    String MainUniqueId = "medias_1-1645776905.37";
    //    String MainUniqueId = "medias_1-1645685191.126";
    String uniqueId = "medias_1-1645685239.128";

    @Test
    public void describeRecordFileUrl() {
        DescribeRecordFileUrlRequest request = new DescribeRecordFileUrlRequest();
        request.setMainUniqueId(ibUniqueId);
        request.expires(86400);
        request.setTimeout(8000L);
        // test

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
    public void describeDetailRecordFileUrl() {
        DescribeDetailRecordFileUrlRequest request = new DescribeDetailRecordFileUrlRequest();
        request.setMainUniqueId(ibUniqueId);
        request.setUniqueId(ibUniqueId);
        request.setTimeout(7200L);


        try {
            DescribeDetailRecordFileUrlResponse response = client.getResponseModel(request);
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
        request.setMainUniqueId("medias_0-1650035747.183349");
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            HttpResponse response = client.doAction(request);
            inputStream = response.getEntity().getContent();

            String filename = response.getHeaders("Content-Disposition")[0].getValue().substring(21);
            File file = new File("/var/log/" + filename);

            outputStream = new FileOutputStream(file);

            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }

                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void downloadDetailRecordFile() {
        DownloadDetailRecordFileRequest request = new DownloadDetailRecordFileRequest();
        request.setMainUniqueId(MainUniqueId);
        request.setUniqueId(uniqueId);
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
