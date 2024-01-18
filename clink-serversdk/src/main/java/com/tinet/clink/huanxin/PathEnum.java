package com.tinet.clink.huanxin;


/**
 * 请求路径
 *
 * @author tian.jie
 * @date 2024-01-04 09:39
 */
public enum PathEnum {
    //查询历史会话列表
    QUERY_SESSION_HISTORYS("huanxin/hx_get_session_historys"),
    //查询历史会话详情
    QUERY_SESSION_MSGS("huanxin/hx_get_session_msgs"),

    //获取坐席列表
    LIST_AGENT("huanxin/hx_list_agent"),

    //新增坐席
    CREATE_AGENT("huanxin/hx_create_agent"),

    //修改坐席
    UPDATE_AGENT("huanxin/hx_update_agent"),

    //删除坐席
    DELETE_AGENT("huanxin/hx_delete_agent"),

    //IM注册新用户
    IM_REGISTER_USER("huanxin/im_register_user"),

    //删除坐席
    IM_UPDATE_PASSWORD("huanxin/im_update_password"),

    //获取队列列表
    LIST_QUEUE("huanxin/hx_list_queue"),

    //新增队列
    CREATE_QUEUE("huanxin/hx_create_queue"),

    //修改队列
    UPDATE_QUEUE("huanxin/hx_update_queue"),

    //删除队列
    DELETE_QUEUE("huanxin/hx_delete_queue"),




    ;

    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
