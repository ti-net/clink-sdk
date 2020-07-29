package com.tinet.clink.openapi.model;

/**业务记录查询条件
 *
 * @author liuhy
 * @date: 2020/7/28
 **/
public class BusinessSearchModel {


    // 暂时以这两个条件，如果以后用户有需求可以直接在sdk中添加查询条件
    /**
     * 按创建时间查询的起始时间
     */
    private Long startTime;

    private Long endTime;


}