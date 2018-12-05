package com.ego.services.juri.api.enumvo;

public enum PurchaseStatus {

    SAVE("1","启用");



    private String code;

    private String msg;
    PurchaseStatus(String code, String msg){

        this.code = code;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
    
    public static PurchaseStatus getStatus(String code) {
    	for (PurchaseStatus status : PurchaseStatus.values())
    		if (status.getCode().equals(code))
    			return status;
    	return null;
    }
    
}
