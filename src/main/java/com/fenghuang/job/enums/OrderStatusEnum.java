package com.fenghuang.job.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 22:04
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public enum OrderStatusEnum {
    WAIT_BEGIN( 1, "待开始" ),
    PROCESSING( 2, "进行中" ),
    DEAL_SUCCESS( 3, "交易成功" ),
    DEAL_CANCEL( 4, "交易取消[用户]" ),
    DEAL_CLOSE( 5, "交易关闭[商家]" );

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

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
