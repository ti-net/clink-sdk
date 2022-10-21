package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatInvestigationByHotlinesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author yuanfeng@ti-net.com.cn
 * @date 2021/7/20 17:21
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatInvestigationByHotlinesRequest extends AbstractStatRequest<StatInvestigationByHotlinesResponse> {

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
