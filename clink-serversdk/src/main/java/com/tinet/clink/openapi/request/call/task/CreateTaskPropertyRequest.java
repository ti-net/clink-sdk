package com.tinet.clink.openapi.request.call.task;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.CreateTaskInventoryModel;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.task.CreateTaskPropertyResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Arrays;

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
     * 外呼任务整理时长
     */
    private Integer wrapupTime;

    /**
     * 座席私有优先，0：关闭；1：开启；
     */
    private Integer privatePreferred;

    /**
     * 是否分配，0：不分配；1：分配
     */
    private Integer assignation;

    /**
     * 分配规则 0：顺序分配 ，1： 随机分配
     */
    private Integer assignationType;

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
     * 任务详情
     */
    private CreateTaskInventoryModel[] taskInventory;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putBodyParameter("name", name);
        }
    }

    public Integer getWrapupTime() {
        return wrapupTime;
    }

    public void setWrapupTime(Integer wrapupTime) {
        this.wrapupTime = wrapupTime;
        if (wrapupTime != null) {
            putBodyParameter("wrapupTime", wrapupTime);
        }
    }

    public Integer getPrivatePreferred() {
        return privatePreferred;
    }

    public void setPrivatePreferred(Integer privatePreferred) {
        this.privatePreferred = privatePreferred;
        if (privatePreferred != null) {
            putBodyParameter("privatePreferred", privatePreferred);
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

    public CreateTaskInventoryModel[] getTaskInventory() {
        return taskInventory;
    }

    public void setTaskInventory(CreateTaskInventoryModel[] taskInventory) {
        this.taskInventory = taskInventory;
        if (taskInventory != null) {
            putBodyParameter("taskInventory", taskInventory);
        }
    }

    public CreateTaskPropertyRequest() {
        super(PathEnum.CreateTaskProperty.value(), HttpMethodType.POST);
    }


    @Override
    public Class<CreateTaskPropertyResponse> getResponseClass() {
        return CreateTaskPropertyResponse.class;
    }

    @Override
    public String toString() {
        return "CreateTaskPropertyRequest{" +
                "name='" + name + '\'' +
                ", wrapupTime=" + wrapupTime +
                ", privatePreferred=" + privatePreferred +
                ", assignation=" + assignation +
                ", assignationType=" + assignationType +
                ", cnos=" + Arrays.toString(cnos) +
                ", duplicateStrategy=" + duplicateStrategy +
                ", creatorName='" + creatorName + '\'' +
                ", taskInventory=" + Arrays.toString(taskInventory) +
                "} " + super.toString();
    }
}
