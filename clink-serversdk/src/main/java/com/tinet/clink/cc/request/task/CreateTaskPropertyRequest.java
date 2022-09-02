package com.tinet.clink.cc.request.task;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.CreateTaskInventoryModel;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.task.CreateTaskPropertyResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 外呼任务创建请求
 *
 * @author wangpw
 *
 **/
public class CreateTaskPropertyRequest extends AbstractRequestModel<CreateTaskPropertyResponse> {

    /**
     * 外呼任务名称
     */
    private String name;

    /**
     * 是否分配，0：不分配；1：分配
     */
    private Integer assignation;

    /**
     * 分配规则 0：顺序分配 ，1： 随机分配
     */
    private Integer assignationType;

    /**
     * 选定外呼任务类型，1：已有外呼任务；2：新建外呼任务
     */
    private Integer taskType;

    /**
     * 分配座席号
     */
    private String[] cnos;

    /**
     * 号码排重模式: 0：不排重；1：本任务排重， 2：任务间排重
     */
    private Integer duplicateStrategy;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 是否自动启用 1：启动 0：不启动，默认不启动
     */
    private Integer start;

    /**
     * 表单模板名称
     */
    private String formName;

    /**
     * 任务详情
     */
    private CreateTaskInventoryModel[] taskInventories;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putBodyParameter("name", name);
        }
    }

    public Integer getAssignation() {
        return assignation;
    }

    public void setAssignation(Integer assignation) {
        this.assignation = assignation;
        if (assignation != null) {
            putBodyParameter("assignation", assignation);
        }
    }

    public Integer getAssignationType() {
        return assignationType;
    }

    public void setAssignationType(Integer assignationType) {
        this.assignationType = assignationType;
        if (assignationType != null) {
            putBodyParameter("assignationType", assignationType);
        }
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
        if (taskType != null) {
            putBodyParameter("taskType", taskType);
        }
    }

    public String[] getCnos() {
        return cnos;
    }

    public void setCnos(String[] cnos) {
        this.cnos = cnos;
        if (cnos != null) {
            putBodyParameter("cnos", cnos);
        }
    }

    public Integer getDuplicateStrategy() {
        return duplicateStrategy;
    }

    public void setDuplicateStrategy(Integer duplicateStrategy) {
        this.duplicateStrategy = duplicateStrategy;
        if (duplicateStrategy != null) {
            putBodyParameter("duplicateStrategy", duplicateStrategy);
        }
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        if (creatorName != null) {
            putBodyParameter("creatorName", creatorName);
        }
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
        if (formName != null) {
            putBodyParameter("formName", formName);
        }
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
        if (start != null) {
            putBodyParameter("start", start);
        }
    }

    public CreateTaskInventoryModel[] getTaskInventories() {
        return taskInventories;
    }

    public void setTaskInventories(CreateTaskInventoryModel[] taskInventories) {
        this.taskInventories = taskInventories;
        if (taskInventories != null) {
            putBodyParameter("taskInventories", taskInventories);
        }
    }


    public CreateTaskPropertyRequest() {
        super(PathEnum.CreateTaskProperty.value(), HttpMethodType.POST);
    }


    @Override
    public Class<CreateTaskPropertyResponse> getResponseClass() {
        return CreateTaskPropertyResponse.class;
    }

}
