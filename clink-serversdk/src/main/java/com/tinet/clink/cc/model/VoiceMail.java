package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Class For:
 * 留言箱实体
 *
 * @author Tinet-yinzk
 * @date 2023/8/29 14:18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoiceMail {
    /**
     * 留言箱名
     */
    private String name;
    /**
     * 留言箱类型，1：公共留言箱，2：队列私有留言箱，3：座席私有留言箱
     */
    private Integer ownerType;
    /**
     * 留言箱归属，type=1 时 owner 为空，type=2时 owner是队列号，type=3时owner是座席号
     */
    private String owner;
    /**
     * 留言箱ID
     */
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
