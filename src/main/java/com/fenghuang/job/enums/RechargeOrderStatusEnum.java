package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 15:39
 * @Email: 15290810931@163.com
 */
public enum RechargeOrderStatusEnum {

    SUCCESS(1,"成功"),
    FAIL(2,"失败"),
    PROCESSING(3,"处理中");

    RechargeOrderStatusEnum(Integer code, String msg) {
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
