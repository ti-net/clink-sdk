package com.tinet.clink.openapi.request.zb.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.ListTicketResponse;
import com.tinet.clink.openapi.response.zb.ticket.ZbListTicketResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单记录查询
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ZbListTicketRequest extends AbstractRequestModel<ZbListTicketResponse> {


    /**
     * 工单id
     */
    private Integer id;

    /**
     * 工单的处理状态 不传为状态 0：待领取 2：处理中 3：已撤销 4：已完成
     */
    private Integer handleStatus;


    /**
     * 处理类型。 0: U客， 1: 钉钉，当为1时传值handlerName，当值为0时传值sCode。
     */
    private Integer handlerType;

    /**
     * 处理人id
     */
    private String handlerName;

    /**
     * 加盟商编码。（如果是钉钉时sCode为空，如果是U都传sCode）
     */
    private String sCode;

    /**
     * 工单类型（咨询/报修/投诉）
     */
    private String ticketType;

    /**
     * 是否查看自己，1-是，0-否，默认传1
     */
    private Integer myself;

    /**
     * 优先级 不传为所有优先级 0：低、1：中、2：高、3：紧急
     */
    private Integer level;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;


    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 客户号码
     */
    private String customerTel;

    /**
     * 偏移量，范围 0-10000，默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100，默认值为 10
     */
    private Integer limit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
        if (sCode != null) {
            putQueryParameter("sCode", sCode);
        }
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
        if (ticketType != null) {
            putQueryParameter("ticketType", ticketType);
        }
    }

    public Integer getMyself() {
        return myself;
    }

    public void setMyself(Integer myself) {
        this.myself = myself;
        if (myself != null) {
            putQueryParameter("myself", myself);
        }
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
        if (handleStatus != null) {
            putQueryParameter("handleStatus", handleStatus);
        }
    }

    public Integer getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(Integer handlerType) {
        this.handlerType = handlerType;
        if (handlerType != null) {
            putQueryParameter("handlerType", handlerType);
        }
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
        if (handlerName != null) {
            putQueryParameter("handlerName", handlerName);
        }
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        if (level != null) {
            putQueryParameter("level", level);
        }
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        if (customerAddress != null) {
            putQueryParameter("customerAddress", customerAddress);
        }
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
        if (customerTel != null) {
            putQueryParameter("customerTel", customerTel);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public ZbListTicketRequest() {
        super(PathEnum.ZbListTicket.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ZbListTicketResponse> getResponseClass() {
        return ZbListTicketResponse.class;
    }

}