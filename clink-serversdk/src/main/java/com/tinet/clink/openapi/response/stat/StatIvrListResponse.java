package com.tinet.clink.openapi.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatIvrListResponse extends PagedResponse {

    private List<Map<String,Object>> statIvrList;

    public List<Map<String, Object>> getStatIvrList() {
        return statIvrList;
    }

    public void setStatIvrList(List<Map<String, Object>> statIvrList) {
        this.statIvrList = statIvrList;
    }
}
