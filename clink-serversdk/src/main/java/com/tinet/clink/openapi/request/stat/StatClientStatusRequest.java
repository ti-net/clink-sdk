package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatClientStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatClientStatusRequest extends AbstractStatRequest<StatClientStatusResponse> {

    private Integer startHour;

    private Integer endHour;


    public void setStartHour(Integer startHour) {
        this.startHour = startHour;

        if (startHour != null) {
            putQueryParameter("startHour", startHour);
        }
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;

        if (endHour != null) {
            putQueryParameter("endHour", endHour);
        }
    }

    public Integer getEndHour() {
        return endHour;
    }

    public Integer getStartHour() {
        return startHour;
    }

    @Override
    public Class<StatClientStatusResponse> getResponseClass() {
        return StatClientStatusResponse.class;
    }

    public StatClientStatusRequest() {
        super(PathEnum.StatClientStatus.value(), HttpMethodType.POST);
    }

}
