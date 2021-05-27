package com.tinet.clink.openapi.response.added;

import com.tinet.clink.openapi.model.NumberAppeal;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * Class for:
 *  号码申诉记录 分页查询结果
 * @author yinzk
 * @date 2021/5/27
 */
public class ListNumberAppealResponse extends PagedResponse {
    /**
     * 号码申诉记录
     */
    private List<NumberAppeal> numberAppeals;

    public List<NumberAppeal> getNumberAppeals() {
        return numberAppeals;
    }

    public void setNumberAppeals(List<NumberAppeal> numberAppeals) {
        this.numberAppeals = numberAppeals;
    }
}
