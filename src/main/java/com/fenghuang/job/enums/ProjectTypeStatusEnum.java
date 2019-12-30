package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/22 11:45
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public enum  ProjectTypeStatusEnum {

    NORMAL(1,"正常"),
    PROHIBIT(2,"禁用");

    ProjectTypeStatusEnum(Integer code, String msg) {
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
