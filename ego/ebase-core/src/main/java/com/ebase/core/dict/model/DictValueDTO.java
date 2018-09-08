package com.ebase.core.dict.model;

import java.io.Serializable;

/**
 * 字典DTO
 * @author jinhangzhan
 *
 */
public class DictValueDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7785408064564174548L;
	/**
	 * 
	 */
	private String codeId;			//字典标识
	private String codeType;		//字典类型
	private String codeName;		//字典名称
	private String codeValue;		//字典值
	private String codeValueDesc;   //字典值描述
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
    public String getCodeValueDesc() {
        return codeValueDesc;
    }
    public void setCodeValueDesc(String codeValueDesc) {
        this.codeValueDesc = codeValueDesc;
    }
}
