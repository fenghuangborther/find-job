package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/23 18:03
 * @Email: 15290810931@163.com
 */
public enum BannerImgStatusEnum {

    NORMAL(1,"正常"),
    INVALID(2,"失效")
    ;

    BannerImgStatusEnum(Integer code, String msg) {
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
