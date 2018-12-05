package com.ego.services.juri.facade.common;

/**
 * 申请单状态
 * @Auther: wangyu
 */
public enum ApplyFormStatusEnum {

    AUDIT((byte)1,"待审核"),
    TOTAL_ENTRY((byte)2,"已总入"),
    HAVE_REFUSED((byte)3,"已拒绝");


    private byte code;

    private String msg;

    ApplyFormStatusEnum(Byte code,String mas){
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
