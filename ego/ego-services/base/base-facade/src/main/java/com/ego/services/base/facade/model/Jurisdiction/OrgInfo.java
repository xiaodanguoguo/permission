package com.ego.services.base.facade.model.Jurisdiction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ego.services.base.api.enumvo.Status;



/**
 * 
 * @author zhangx
 *		组织机构表
 */
public class OrgInfo{
	
	private String id;
	
	private String id2;

	

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	/**
	 * 上级机构
	 */
	private String parentId;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 机构代码
	 */
	private String orgCode;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	

	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 0:停用;1:启用
	 */
	private Status status;
	
	/**
	 * 创建人
	 */
	private String createdBy;
	
	/**
	 * 创建时间
	 */
	private Date createdTime;
	
	/**
	 * 修改人
	 */
	private String updatedBy;
	
	/**
	 * 修改时间
	 */
	private Date updatedTime;

	private OrgInfo parent;//parent

	public OrgInfo getParent() {
		return parent;
	}

	public void setParent(OrgInfo parent) {
		this.parent = parent;
	}
	
	private List<OrgInfo> children = new ArrayList<OrgInfo>();
	
	public List<OrgInfo> getChildren() {
		return children;
	}

	public void setChildren(List<OrgInfo> children) {
		this.children = children;
	}
	public void addChild(OrgInfo orgInfo) {
		this.children.add(orgInfo);
	}

	private Integer pageSize = 10;
	
	private Integer pageNum =1;
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;	
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		if (this.status != null)
			return status.getCode();
		return "";
	}

	public void setStatus(String code) {
		this.status = Status.getStatus(code);
		
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

}
