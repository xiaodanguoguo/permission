package com.ego.services.juri.facade.model.dataauthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 */
public class AccountPrivRelation implements Serializable {
    /**
     * Database Column Remarks:
     *   ID
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   账户标识
     */
    private Long acctId;

    /**
     * Database Column Remarks:
     *   关系类型
     */
    private Byte relationType;

    /**
     * Database Column Remarks:
     *   关系标识
     */
    private Long relationId;

    /**
     * Database Column Remarks:
     *   创建人
     */
    private String createdBy;

    /**
     * Database Column Remarks:
     *   创建日期
     */
    private Date createdDate;

    /**
     * Database Column Remarks:
     *   修改人
     */
    private String updatedBy;

    /**
     * Database Column Remarks:
     *   修改时间
     */
    private Date updatedTime;

    /**
     * Database Column Remarks:
     *   是否删除
     */
    private Byte deleteStatus;

    /**
     * Database Column Remarks:
     *   备注
     */
    private String remark;

    /**
     * Database Column Remarks:
     *   0:停用;1:启用
     */
    private Byte status;

    /**
     */
    private Long powerExpressionId;

    private String acctTitle;

    private List<PrivConditionConfig> children;

    /**
     */
    private static final long serialVersionUID = 1L;

    public String getAcctTitle() {
        return acctTitle;
    }

    public void setAcctTitle(String acctTitle) {
        this.acctTitle = acctTitle;
    }

    public List<PrivConditionConfig> getChildren() {
        return children;
    }

    public void setChildren(List<PrivConditionConfig> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Byte getRelationType() {
        return relationType;
    }

    public void setRelationType(Byte relationType) {
        this.relationType = relationType;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Byte getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Byte deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getPowerExpressionId() {
        return powerExpressionId;
    }

    public void setPowerExpressionId(Long powerExpressionId) {
        this.powerExpressionId = powerExpressionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", acctId=").append(acctId);
        sb.append(", relationType=").append(relationType);
        sb.append(", relationId=").append(relationId);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", powerExpressionId=").append(powerExpressionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}