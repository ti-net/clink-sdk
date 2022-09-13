package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatInvestigationByHotlinesResponse;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * @author yuanfeng@ti-net.com.cn
 * @date 2021/7/20 17:21
 */
public class StatInvestigationByHotlinesRequest extends AbstractStatRequest<StatInvestigationByHotlinesResponse> {

  /**
   * 呼叫类型 呼入: ib; 呼出: ob
   */
  private String callType;

  public String getCallType() {
    return callType;
  }

  public void setCallType(String callType) {
    this.callType = callType;
    if (callType != null && !"ib".equals(callType) && !"ob".equals(callType)) {
      throw new IllegalArgumentException("callType must be ib or ob !");
    }
    if (callType != null) {
      putQueryParameter("callType", callType);
    }
  }

  @Override
  public Class<StatInvestigationByHotlinesResponse> getResponseClass() {
    return StatInvestigationByHotlinesResponse.class;
  }

  public StatInvestigationByHotlinesRequest() {
    super(PathEnum.StatInvestigationByHotlines.value(), HttpMethodType.POST);
  }
}
