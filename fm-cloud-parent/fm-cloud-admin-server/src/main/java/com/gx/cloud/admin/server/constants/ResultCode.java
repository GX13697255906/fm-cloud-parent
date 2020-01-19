package com.gx.cloud.admin.server.constants;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ResultCode implements Serializable {

    SUCCESS(1,"请求执行成功"),
    FAUL(2,"请求执行失败");

    private static final Map<Integer,String> map = new HashMap<>();

    static {
        for(ResultCode resultCode: EnumSet.allOf(ResultCode.class)){
            map.put(resultCode.code,resultCode.desc);
        }
    }

    private ResultCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static String getMessage(int code){
        return map.get(code);
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
