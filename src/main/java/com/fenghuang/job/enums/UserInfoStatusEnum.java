package com.fenghuang.job.enums;

import io.swagger.annotations.ApiModel;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/17 14:30
 * @Email: 15290810931@163.com
 */
@ApiModel(value = "用户状态")
public enum UserInfoStatusEnum {

    FROZEN(1,"冻结"),
    NORMAL(2,"正常"),
    ;

    UserInfoStatusEnum(Integer code, String msg) {
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
