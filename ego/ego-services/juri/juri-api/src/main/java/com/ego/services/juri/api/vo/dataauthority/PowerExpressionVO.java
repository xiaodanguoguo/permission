package com.ego.services.juri.api.vo.dataauthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 */
public class PowerExpressionVO implements Serializable {
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

    private String opt;

    private Long[] acctIds;

    private Long[] roleIds;

    private String[] orgIds;

    private List<PrivConditionConfigVO> acctConfig;

    private List<PrivConditionConfigVO> roleConfig;

    private List<PrivConditionConfigVO> orgConfig;

    private List<OrgPrivOrgIdVO> orgPrivRelations;

    private List<RolePrivRelationVO> rolePrivRelations;

    private List<AccountPrivRelationVO> accountPrivRelations;


    private List<PrivRelationVO> children;

    private Long acctId;

    private int pageSize =10;

    private int pageNum = 1;

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

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public List<OrgPrivOrgIdVO> getOrgPrivRelations() {
        return orgPrivRelations;
    }

    public void setOrgPrivRelations(List<OrgPrivOrgIdVO> orgPrivRelations) {
        this.orgPrivRelations = orgPrivRelations;
    }

    public List<RolePrivRelationVO> getRolePrivRelations() {
        return rolePrivRelations;
    }

    public void setRolePrivRelations(List<RolePrivRelationVO> rolePrivRelations) {
        this.rolePrivRelations = rolePrivRelations;
    }

    public List<AccountPrivRelationVO> getAccountPrivRelations() {
        return accountPrivRelations;
    }

    public void setAccountPrivRelations(List<AccountPrivRelationVO> accountPrivRelations) {
        this.accountPrivRelations = accountPrivRelations;
    }

    public List<PrivRelationVO> getChildren() {
        return children;
    }

    public void setChildren(List<PrivRelationVO> children) {
        this.children = children;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Long[] getAcctIds() {
        return acctIds;
    }

    public void setAcctIds(Long[] acctIds) {
        this.acctIds = acctIds;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public String[] getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String[] orgIds) {
        this.orgIds = orgIds;
    }

    public List<PrivConditionConfigVO> getAcctConfig() {
        return acctConfig;
    }

    public void setAcctConfig(List<PrivConditionConfigVO> acctConfig) {
        this.acctConfig = acctConfig;
    }

    public List<PrivConditionConfigVO> getRoleConfig() {
        return roleConfig;
    }

    public void setRoleConfig(List<PrivConditionConfigVO> roleConfig) {
        this.roleConfig = roleConfig;
    }

    public List<PrivConditionConfigVO> getOrgConfig() {
        return orgConfig;
    }

    public void setOrgConfig(List<PrivConditionConfigVO> orgConfig) {
        this.orgConfig = orgConfig;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
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