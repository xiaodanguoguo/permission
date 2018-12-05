package com.ego.services.juri.api.vo.dataauthority;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class TheMetadataVO implements Serializable {
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
     *   表名CODE
     */
    private String tableNameCode;

    /**
     * Database Column Remarks:
     *   表描述
     */
    private String tableDescribe;

    /**
     * Database Column Remarks:
     *   主键名称
     */
    private String keyName;

    /**
     * Database Column Remarks:
     *   主键CODE
     */
    private String keyCode;

    /**
     * Database Column Remarks:
     *   主键类型
     */
    private Byte keyType;

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
    private Boolean status;

    private String opt;

    private Long sysId;

    private int pageSize =10;

    private int pageNum = 1;
    /**
     */
    private static final long serialVersionUID = 1L;

    public Long getSysId() {
        return sysId;
    }

    public void setSysId(Long sysId) {
        this.sysId = sysId;
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

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
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

    public String getTableNameCode() {
        return tableNameCode;
    }

    public void setTableNameCode(String tableNameCode) {
        this.tableNameCode = tableNameCode == null ? null : tableNameCode.trim();
    }

    public String getTableDescribe() {
        return tableDescribe;
    }

    public void setTableDescribe(String tableDescribe) {
        this.tableDescribe = tableDescribe == null ? null : tableDescribe.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode == null ? null : keyCode.trim();
    }

    public Byte getKeyType() {
        return keyType;
    }

    public void setKeyType(Byte keyType) {
        this.keyType = keyType;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tableName=").append(tableName);
        sb.append(", tableNameCode=").append(tableNameCode);
        sb.append(", tableDescribe=").append(tableDescribe);
        sb.append(", keyName=").append(keyName);
        sb.append(", keyCode=").append(keyCode);
        sb.append(", keyType=").append(keyType);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}