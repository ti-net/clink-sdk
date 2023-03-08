package com.tinet.clink.openapi.ticket;

import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.TicketRecordFilesGenerateRequest;
import com.tinet.clink.ticket.request.TicketRecordFilesStatusRequest;
import com.tinet.clink.ticket.request.TicketRecordFilesUrlRequest;
import com.tinet.clink.ticket.response.TicketRecordFilesGenerateResponse;
import com.tinet.clink.ticket.response.TicketRecordFilesStatusResponse;
import com.tinet.clink.ticket.response.TicketRecordFilesUrlResponse;
import org.junit.Test;

/**
 * 工单自定义导出接口
 *
 * @author wangli
 * @date 2023-02-23 20:49
 */
public class TicketRecordFilesGenerateTest extends AbstractTest {

    @Test
    public void generate() throws ServerException, ClientException {

        TicketRecordFilesGenerateRequest request = new TicketRecordFilesGenerateRequest();
        request.setType(1);
        TicketRecordFilesGenerateResponse responseModel = client.getResponseModel(request);
        System.out.println(responseModel.getResult());

    }

    @Test
    public void status() throws ServerException, ClientException {

        TicketRecordFilesStatusRequest request = new TicketRecordFilesStatusRequest();
        request.setType(1);
        TicketRecordFilesStatusResponse responseModel = client.getResponseModel(request);
        System.out.println(responseModel.getStatus());

    }

    @Test
    public void url() throws ServerException, ClientException {

        TicketRecordFilesUrlRequest request = new TicketRecordFilesUrlRequest();
        request.setType(1);
        TicketRecordFilesUrlResponse responseModel = client.getResponseModel(request);
        System.out.println(responseModel.getUrl());

    }

}
