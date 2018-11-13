package com.ego.services.base.facade.model.dataauthority;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class PrivConditionConfig implements Serializable {
    /**
     * Database Column Remarks:
     *   ID
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   表名
     */
    private String tableName;

    /**
     * Database Column Remarks:
     *   表达式
     */
    private String expression;

    private String accExpression;

    private String roleExpression;

    private String orgExpression;

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
    private Byte expressionType;

    /**
     */
    private Long fieldId;

    /**
     */
    private String fieldName;

    private Byte fieldType;
    /**
     */
    private String fieldValue;

    /**
     */
    private Long powerExpressionId;

    /**
     */
    private static final long serialVersionUID = 1L;

    public Byte getFieldType() {
        return fieldType;
    }

    public void setFieldType(Byte fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAccExpression() {
        return accExpression;
    }

    public void setAccExpression(String accExpression) {
        this.accExpression = accExpression;
    }

    public String getRoleExpression() {
        return roleExpression;
    }

    public void setRoleExpression(String roleExpression) {
        this.roleExpression = roleExpression;
    }

    public String getOrgExpression() {
        return orgExpression;
    }

    public void setOrgExpression(String orgExpression) {
        this.orgExpression = orgExpression;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression == null ? null : expression.trim();
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

    public Byte getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(Byte expressionType) {
        this.expressionType = expressionType;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
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
        sb.append(", tableName=").append(tableName);
        sb.append(", expression=").append(expression);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", expressionType=").append(expressionType);
        sb.append(", fieldId=").append(fieldId);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append(", powerExpressionId=").append(powerExpressionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}