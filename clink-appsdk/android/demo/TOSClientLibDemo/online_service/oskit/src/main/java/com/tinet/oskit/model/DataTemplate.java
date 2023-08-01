package com.tinet.oskit.model;

import java.util.List;

import static com.tinet.oskit.present.TinetPresent.DEFAULT_PAGE_SIZE;

/**
 * @ProjectName: TIMSDK
 * @ClassName: DataTemplate
 * @Author: liuzr
 * @CreateDate: 2021-08-23 10:09
 * @Description:
 */
public class DataTemplate<Data> {

    public DataTemplate(boolean isRefresh, List<Data> data) {
        this.refresh = isRefresh;
        this.data = data;
    }

    /**
     * 是否为刷新
     */
    private Boolean refresh;

    /**
     * 数据集
     */
    private List<Data> data;

    /**
     * 是否还有更多数据（用于分页）
     */
    public Boolean hasMore() {
        return data != null && data.size() >= DEFAULT_PAGE_SIZE;
    }

    public List<Data> getList() {
        return data;
    }

    public Boolean isRefresh() {
        return refresh;
    }

    public Boolean hasData() {
        return data != null && data.size() > 0;
    }

    public int getCount() {
        if (data == null)
            return 0;

        return data.size();
    }
}
