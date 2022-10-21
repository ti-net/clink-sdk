package com.tinet.clink.openapi.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;
import java.util.List;
import java.util.Map;

/**
 * @author yuanfeng@ti-net.com.cn
 * @date 2021/7/20 17:08
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatInvestigationByHotlinesResponse extends PagedResponse {

  public List<Map<String,Object>> statInvestigationByHotlines;

  public List<Map<String, Object>> getStatInvestigationByHotlines() {
    return statInvestigationByHotlines;
  }

  public void setStatInvestigationByHotlines(List<Map<String, Object>> statInvestigationByHotlines) {
    this.statInvestigationByHotlines = statInvestigationByHotlines;
  }
}
