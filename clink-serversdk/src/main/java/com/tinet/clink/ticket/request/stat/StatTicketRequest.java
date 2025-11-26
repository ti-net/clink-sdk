package com.tinet.clink.ticket.request.stat;



import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.stat.StatTicketResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 工单整体统计报表请求
 *
 * @author wangli
 * @date 2022-08-24 10:38 上午
 */
public class StatTicketRequest extends AbstractStatRequest<StatTicketResponse> {

    /**
     * 标签
     */
    private String tag;

    /**
     * 来源渠道
     */
    private Integer[] sources;

    /**
     * 统计方式
     */
    private Integer statisticMethod;

    public List<Integer> getLevels() {
        return levels;
    }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
        if(Objects.nonNull(levels) && !levels.isEmpty()) {
            for (Integer level : levels) {
                putNameValuePairParameter("levels", level.toString());
            }
        }
    }

    public Map<String, String> getSystemFieldMap() {
        return systemFieldMap;
    }

    public void setSystemFieldMap(Map<String, String> systemFieldMap) {
        this.systemFieldMap = systemFieldMap;
        if(Objects.nonNull(systemFieldMap) && !systemFieldMap.isEmpty()) {
            for (Map.Entry<String, String> entry : systemFieldMap.entrySet()) {
                putNameValuePairParameter("systemFieldMap[" + entry.getKey() + "]", entry.getValue());
            }
        }
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
        if(Objects.nonNull(fieldMap) && !fieldMap.isEmpty()) {
          for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
              putNameValuePairParameter("fieldMap[" + entry.getKey() + "]", entry.getValue());
          }
        }
    }

    public List<Integer> getWorkflowIds() {
        return workflowIds;
    }

    public void setWorkflowIds(List<Integer> workflowIds) {
        this.workflowIds = workflowIds;
        if(Objects.nonNull(workflowIds) && !workflowIds.isEmpty()) {
            for (Integer workflowId : workflowIds) {
                putNameValuePairParameter("workflowIds", workflowId.toString());
            }
        }
    }

    /**
     * 优先级
     */
    private List<Integer> levels;

    /**
     * 自定义系统属性字段集合
     */
    private Map<String, String> systemFieldMap;

    /**
     * 自定义字段集合
     */
    private Map<String, String> fieldMap;

    /**
     * 工单模板
     */
    private List<Integer> workflowIds;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
        if (Objects.nonNull(tag)) {
            putBodyParameter("tag", tag);
        }
    }

    public Integer[] getSources() {
        return sources;
    }

    public void setSources(Integer[] sources) {
        this.sources = sources;
        if (Objects.nonNull(sources)) {
            putBodyParameter("sources", sources);
        }
    }

    public Integer getStatisticMethod() {
        return statisticMethod;
    }

    public void setStatisticMethod(Integer statisticMethod) {
        this.statisticMethod = statisticMethod;
        if (!Objects.equals(statisticMethod, 1) && !Objects.equals(statisticMethod, 2)) {
            throw new IllegalArgumentException("statisticMethod must be 1 or 2!");
        }
        putBodyParameter("statisticMethod", statisticMethod);
    }

    @Override
    public Class<StatTicketResponse> getResponseClass() {
        return StatTicketResponse.class;
    }

    public StatTicketRequest() {
        super(PathEnum.StatTicket.value(), HttpMethodType.POST);
    }

}
