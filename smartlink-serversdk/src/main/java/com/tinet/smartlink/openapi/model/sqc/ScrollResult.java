package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScrollResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String uniqueId;
}
