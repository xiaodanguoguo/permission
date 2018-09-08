package com.ebase.core.dict.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * entity:DictRegion
 * 
 * @author zx.dev
 * @date 2016-1-27
 */
public class DictTreeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8366746405324805402L;
	/**
	 * 
	 */
	@JsonIgnore
	private DictTreeDTO parentNode;		 //	父节点
	private String	dictTreeCode;		 // 节点标识 
	private String	parentCode;			 // 上级节点标识 
	private String	dictTreeName;		 // 节点名称
	private String  iconUrl;     /*图标*/
	private List<DictTreeDTO> children = new ArrayList<DictTreeDTO>();  // 子节点
	
	// Constructor
	public DictTreeDTO() {
		this.children = new ArrayList<DictTreeDTO>();
	}

	public List<DictTreeDTO> getChildren() {
		return children;
	}

	public void setChildren(List<DictTreeDTO> children) {
		this.children = children;
	}

	public DictTreeDTO getParentNode() {
		return parentNode;
	}

	public void setParentNode(DictTreeDTO parentNode) {
		this.parentNode = parentNode;
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

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DictTreeDTO) {
			DictTreeDTO dictTree = (DictTreeDTO)obj;
			return dictTree.getDictTreeCode().equals(this.getDictTreeCode());
		}
		return false;
	}
}
