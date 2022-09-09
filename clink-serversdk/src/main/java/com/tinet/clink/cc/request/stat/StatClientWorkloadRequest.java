package com.tinet.clink.cc.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatClientWorkloadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatClientWorkloadRequest extends AbstractStatRequest<StatClientWorkloadResponse> {

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
    public Class<StatClientWorkloadResponse> getResponseClass() {
        return StatClientWorkloadResponse.class;
    }

    public StatClientWorkloadRequest() {
        super(PathEnum.StatClientWorkload.value(), HttpMethodType.POST);
    }

}
