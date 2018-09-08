package com.ego.services.base.facade.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户类型
 * @Auther: wangyu
 */
public enum CustTypeEnum {

    BUSINESS((byte)1,"企业用户"),
    PERSONAL((byte)2,"个人用户");



    private byte code;

    private String msg;

    CustTypeEnum(Byte code,String mas){
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


    private static Map<Byte,CustTypeEnum> tmpMap = new HashMap<>(CustTypeEnum.values().length);

    public static CustTypeEnum getCustTypeEnum(Byte lifeCycle) {

        CustTypeEnum certColourEnum = tmpMap.get(lifeCycle);
        if(certColourEnum == null){
            for (CustTypeEnum cycleEnum : CustTypeEnum.values()){
                tmpMap.put(cycleEnum.getCode(),cycleEnum);
                if (cycleEnum.getCode().equals(lifeCycle)){
                    certColourEnum = cycleEnum;
                }
            }
        }
        return certColourEnum;
    }
}
