package com.ego.services.base.facade.common;

/**
 *  是否已自荐过
 * @Auther: wangyu
 */
public enum IsProposeOneself {

    NEVER((byte)0,"未自荐过"),
    HAVE((byte)1,"过过/不通过 或 自荐过程中(");


    private byte code;

    private String msg;

    IsProposeOneself(Byte code,String mas){
        this.code = code;
        this.msg = mas;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Byte getCode() {
        return code;
    }
}
