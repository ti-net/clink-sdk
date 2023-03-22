package com.tinet.clink.kb.response;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.kb.model.KbFileModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author 周先康
 * @create 2023/3/7 13:35
 */
public class ListFileResponse extends PagedResponse {

    List<KbFileModel> data;

    public List<KbFileModel> getData() {
        return data;
    }

    public void setData(List<KbFileModel> data) {
        this.data = data;
    }
}
