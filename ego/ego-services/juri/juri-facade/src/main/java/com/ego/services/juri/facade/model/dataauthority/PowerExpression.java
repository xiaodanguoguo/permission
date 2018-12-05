package com.ego.services.juri.facade.model.dataauthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 */
public class PowerExpression implements Serializable {
    /**
     * Database Column Remarks:
     *   字段标识
     */
    private Long powerExpressionId;

    /**
     * Database Column Remarks:
     *   表ID
     */
    private Long tableId;

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
    private String title;

    /**
     */
    private String orgId;

    private Long[] acctIds;

    private Long[] roleIds;

    private String[] orgIds;

    private List<PrivConditionConfig> acctConfig;

    private List<PrivConditionConfig> roleConfig;

    private List<PrivConditionConfig> orgConfig;

    private List<RolePrivRelation> rolePrivRelations;

    private List<AccountPrivRelation> accountPrivRelations;

    private List<OrgPrivOrgId> orgPrivRelations;

    private List<PrivRelation> children;

    private int num;

    private int size;
    private String fieldName;
    /**
     */
    private static final long serialVersionUID = 1L;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<OrgPrivOrgId> getOrgPrivRelations() {
        return orgPrivRelations;
    }

    public void setOrgPrivRelations(List<OrgPrivOrgId> orgPrivRelations) {
        this.orgPrivRelations = orgPrivRelations;
    }

    public List<RolePrivRelation> getRolePrivRelations() {
        return rolePrivRelations;
    }

    public void setRolePrivRelations(List<RolePrivRelation> rolePrivRelations) {
        this.rolePrivRelations = rolePrivRelations;
    }

    public List<AccountPrivRelation> getAccountPrivRelations() {
        return accountPrivRelations;
    }

    public void setAccountPrivRelations(List<AccountPrivRelation> accountPrivRelations) {
        this.accountPrivRelations = accountPrivRelations;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<PrivRelation> getChildren() {
        return children;
    }

    public void setChildren(List<PrivRelation> children) {
        this.children = children;
    }

    public Long getPowerExpressionId() {
        return powerExpressionId;
    }

    public void setPowerExpressionId(Long powerExpressionId) {
        this.powerExpressionId = powerExpressionId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Long[] getAcctIds() {
        return acctIds;
    }

    public void setAcctIds(Long[] acctIds) {
        this.acctIds = acctIds;
    }

    public String[] getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String[] orgIds) {
        this.orgIds = orgIds;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public List<PrivConditionConfig> getAcctConfig() {
        return acctConfig;
    }

    public void setAcctConfig(List<PrivConditionConfig> acctConfig) {
        this.acctConfig = acctConfig;
    }

    public List<PrivConditionConfig> getRoleConfig() {
        return roleConfig;
    }

    public void setRoleConfig(List<PrivConditionConfig> roleConfig) {
        this.roleConfig = roleConfig;
    }

    public List<PrivConditionConfig> getOrgConfig() {
        return orgConfig;
    }

    public void setOrgConfig(List<PrivConditionConfig> orgConfig) {
        this.orgConfig = orgConfig;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", powerExpressionId=").append(powerExpressionId);
        sb.append(", tableId=").append(tableId);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", title=").append(title);
        sb.append(", orgId=").append(orgId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}