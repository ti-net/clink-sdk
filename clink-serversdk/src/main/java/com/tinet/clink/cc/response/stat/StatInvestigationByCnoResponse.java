package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author yuanfeng@ti-net.com.cn
 * @date 2021/7/20 16:53
 */
public class StatInvestigationByCnoResponse extends PagedResponse {

  private List<Map<String, Object>> statInvestigationByCno;

  public List<Map<String, Object>> getStatInvestigationByCno() {
    return statInvestigationByCno;
  }

  public void setStatInvestigationByCno(List<Map<String, Object>> statInvestigationByCno) {
    this.statInvestigationByCno = statInvestigationByCno;
  }
}
