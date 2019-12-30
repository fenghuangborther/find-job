package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 15:44
 * @Email: 15290810931@163.com
 */
public enum ExamineStatusEnum {

    AUDITED(1,"待审核"),
    PASSED(2,"通过"),
    REJECTED(3,"驳回");

    ExamineStatusEnum(Integer code, String msg) {
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
