package com.tinet.clink.openapi.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;
import java.util.List;
import java.util.Map;

/**
 * @author yuanfeng@ti-net.com.cn
 * @date 2021/7/20 16:53
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatInvestigationByCnoResponse extends PagedResponse {

  private List<Map<String, Object>> statInvestigationByCno;

  public List<Map<String, Object>> getStatInvestigationByCno() {
    return statInvestigationByCno;
  }

  public void setStatInvestigationByCno(List<Map<String, Object>> statInvestigationByCno) {
    this.statInvestigationByCno = statInvestigationByCno;
  }
}
