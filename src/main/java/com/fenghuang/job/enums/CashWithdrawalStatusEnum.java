package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/26 18:07
 * @Email: 15290810931@163.com
 */
public enum CashWithdrawalStatusEnum {

    PROCESSING(1,"处理中"),
    SUCCESS(2,"成功"),
    FAIL(3,"失败");

    CashWithdrawalStatusEnum(Integer code, String msg) {
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
