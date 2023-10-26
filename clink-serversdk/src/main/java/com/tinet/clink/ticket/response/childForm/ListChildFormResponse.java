package com.tinet.clink.ticket.response.childForm;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.ticket.model.childForm.ListChildFormResultModel;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
public class ListChildFormResponse extends PagedResponse {

    /**
     * 工单实体对象集合
     */
    List<ListChildFormResultModel> forms;
}