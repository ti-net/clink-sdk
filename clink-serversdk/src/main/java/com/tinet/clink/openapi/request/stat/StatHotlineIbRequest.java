package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatHotlineIbResponse;
import com.tinet.clink.openapi.response.stat.StatQueueResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatHotlineIbRequest extends AbstractStatRequest<StatHotlineIbResponse> {

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
    public Class<StatHotlineIbResponse> getResponseClass() {
        return StatHotlineIbResponse.class;
    }

    public StatHotlineIbRequest() {
        super(PathEnum.StatHotlineIb.value(), HttpMethodType.POST);
    }

}
