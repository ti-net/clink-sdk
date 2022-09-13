package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author yuanfeng@ti-net.com.cn
 * @date 2021/7/20 17:08
 */
public class StatInvestigationByHotlinesResponse extends PagedResponse {

  public List<Map<String,Object>> statInvestigationByHotlines;

  public List<Map<String, Object>> getStatInvestigationByHotlines() {
    return statInvestigationByHotlines;
  }

  public void setStatInvestigationByHotlines(List<Map<String, Object>> statInvestigationByHotlines) {
    this.statInvestigationByHotlines = statInvestigationByHotlines;
  }
}
