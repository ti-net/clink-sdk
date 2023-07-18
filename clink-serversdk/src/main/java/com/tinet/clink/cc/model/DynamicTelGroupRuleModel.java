package com.tinet.clink.cc.model;

/**
 * @author libin
 * @date 2023-06-28 09:36
 */
public class DynamicTelGroupRuleModel {
    /**
     * id
     */
    private Integer id;
    /**
     * 规则名称
     */
    private String name;

    /**
     * 选中的号码集合 （用于支持根据号码查询规则的需求）
     */
    private String[] selectedTels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSelectedTels() {
        return selectedTels;
    }

    public void setSelectedTels(String[] selectedTels) {
        this.selectedTels = selectedTels;
    }
}
