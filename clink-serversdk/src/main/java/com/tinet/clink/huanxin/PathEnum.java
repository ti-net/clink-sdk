package com.tinet.clink.huanxin;


/**
 * 请求路径
 *
 * @author tian.jie
 * @date 2024-01-04 09:39
 */
public enum PathEnum {
    //查询历史会话列表
    QUERY_SESSION_HISTORYS("huanxin/get_session_historys"),
    //查询历史会话详情
    QUERY_SESSION_MSGS("huanxin/get_session_msgs"),

    //获取坐席列表
    LIST_AGENT("huanxin/list_agent"),

    //新增坐席
    CREATE_AGENT("huanxin/create_agent"),

    //修改坐席
    UPDATE_AGENT("huanxin/update_agent"),

    //删除坐席
    DELETE_AGENT("huanxin/delete_agent"),

    //IM注册新用户
    IM_REGISTER_USER("huanxin/im_register_user"),

    //删除坐席
    IM_UPDATE_PASSWORD("huanxin/im_update_password"),




    ;

    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
