package com.ebase.core.dict.model;

import java.io.Serializable;

/**
 * entity:DictRegion
 * 
 * @author zx.dev
 * @date 2016-1-27
 */
public class DictTreeDTOResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2817620359961408409L;
	/**
	 * 
	 */
	private String	dictTreeCode;		 /* 节点标识 */ 
	private String	parentCode;		 /* 上级节点编码 */ 
	private String	dictTreeName;		 /* 节点名称 */ 
	private Integer childSize;			 /* 子节点数量 */			
    private Integer isOption;/*是否可选*/
    
	public Integer getChildSize() {
		return childSize;
	}

	public void setChildSize(Integer childSize) {
		this.childSize = childSize;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDictTreeCode() {
		return dictTreeCode;
	}

	public void setDictTreeCode(String dictTreeCode) {
		this.dictTreeCode = dictTreeCode;
	}

	public String getDictTreeName() {
		return dictTreeName;
	}

	public void setDictTreeName(String dictTreeName) {
		this.dictTreeName = dictTreeName;
	}

	public Integer getIsOption() {
		return isOption;
	}

	public void setIsOption(Integer isOption) {
		this.isOption = isOption;
	}
}
