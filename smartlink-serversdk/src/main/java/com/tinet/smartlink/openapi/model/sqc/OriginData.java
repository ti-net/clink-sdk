package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 王大宝
 * @date 2019/8/19
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginData implements Serializable {

    private String name;

    private String value;
}
