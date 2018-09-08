package com.ebase.utils;

public enum PramType {


    DELETE("delete",1),
    UPDATE("update",2),
    INSERT("insert",3);

    private String msg;

    private Integer code;


    PramType(String mas, Integer code){
        this.msg = mas;
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
