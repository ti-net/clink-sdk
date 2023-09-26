package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatNewQueueResponse;
import com.tinet.clink.cc.response.stat.StatQueueResponse;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * @author yuqiang
 * @date 2023/9/11
 **/
public class StatNewQueueRequest extends AbstractStatRequest<StatNewQueueResponse> {

    /**
     * 统计方式 2-汇总 3-分时累计
     */
    private Integer statisticMethod;

    public Integer getStatisticMethod() {
        return statisticMethod;
    }

    public void setStatisticMethod(Integer statisticMethod) {
        this.statisticMethod = statisticMethod;
        if (statisticMethod != null && statisticMethod != 2 && statisticMethod != 3) {
            throw new IllegalArgumentException("statisticMethod must be between 2 and 3 !");
        }
        if (statisticMethod != null) {
            putQueryParameter("statisticMethod", statisticMethod);
        }
    }

    @Override
    public Class<StatNewQueueResponse> getResponseClass() {
        return StatNewQueueResponse.class;
    }

    public StatNewQueueRequest() {
        super(PathEnum.StatNewQueue.value(), HttpMethodType.POST);
    }

}
