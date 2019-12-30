package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/26 16:32
 * @Email: 15290810931@163.com
 */
public enum MessageTypeEnum {

    REGISTER(1,"注册"),
    FIND_BANK_PASSWORD(2,"找回密码"),
    LOGIN(3,"登录");

    MessageTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String  msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
