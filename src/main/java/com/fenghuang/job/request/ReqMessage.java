package com.fenghuang.job.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 16:03
 * @Email: 15290810931@163.com
 */
@Data
public class ReqMessage implements Serializable{

    private String mobile;

    private String signId;

    private String messageId;

    private String ip;
}
