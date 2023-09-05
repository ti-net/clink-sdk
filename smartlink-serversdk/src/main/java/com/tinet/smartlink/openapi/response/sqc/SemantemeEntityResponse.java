package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.model.sqc.SemantemeEnumerateFail;
import lombok.Data;

import java.util.List;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/29
 **/
@Data
public class SemantemeEntityResponse{
    /**
     * 新增枚举词失败list
     */
    private List<SemantemeEnumerateFail> semantemeEnumerateFails;
}
