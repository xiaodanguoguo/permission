package com.ego.services.base.facade.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wangyu
 */
public enum SubmitStatusEnum {

    UNSUBMITTED((byte)0,"未提交"),
    SUBMISSION((byte)1,"提交中"),
    EGIS((byte)2,"审核通过");

    private byte code;

    private String msg;

    SubmitStatusEnum(Byte code,String mas){
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


    private static Map<Byte,SubmitStatusEnum> tmpMap = new HashMap<>(SubmitStatusEnum.values().length);

    public static SubmitStatusEnum getSubmitStatusEnum(Byte lifeCycle) {

        SubmitStatusEnum submitStatusEnum = tmpMap.get(lifeCycle);
        if(submitStatusEnum == null){
            for (SubmitStatusEnum cycleEnum : SubmitStatusEnum.values()){
                tmpMap.put(cycleEnum.getCode(),cycleEnum);
                if (cycleEnum.getCode().equals(lifeCycle)){
                    submitStatusEnum = cycleEnum;
                }
            }
        }
        return submitStatusEnum;
    }



}
