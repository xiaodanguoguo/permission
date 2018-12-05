package com.ego.services.juri.facade.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册类型
 * @Auther: wangyu
 */
public enum RegisterEnum {

    AUTONOMY((byte)0,"用户自主注册"),
    DISTRIBUTION((byte)1,"后台管理员分配");


    private byte code;

    private String msg;

    RegisterEnum(Byte code,String mas){
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


    private static Map<Byte,RegisterEnum> tmpMap = new HashMap<>(RegisterEnum.values().length);

    public static RegisterEnum getRegisterEnum(Byte lifeCycle) {

        RegisterEnum registerEnum = tmpMap.get(lifeCycle);
        if(registerEnum == null){
            for (RegisterEnum cycleEnum : RegisterEnum.values()){
                tmpMap.put(cycleEnum.getCode(),cycleEnum);
                if (cycleEnum.getCode().equals(lifeCycle)){
                    registerEnum = cycleEnum;
                }
            }
        }
        return registerEnum;
    }
}
