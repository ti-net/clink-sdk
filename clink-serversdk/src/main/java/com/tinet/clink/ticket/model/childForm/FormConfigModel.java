package com.tinet.clink.ticket.model.childForm;

/**
 * 表单配置
 *
 * @author DengJie
 * @date 2023/03/20
 */
public class FormConfigModel {

    /**
     * 子表单导出时的分类展示方式
     *
     * null or 0 整体导出  1 分列导出
     *
     */
    private Integer exportColumnMode;

    public Integer getExportColumnMode() {
        return exportColumnMode;
    }

    public void setExportColumnMode(Integer exportColumnMode) {
        this.exportColumnMode = exportColumnMode;
    }
}
