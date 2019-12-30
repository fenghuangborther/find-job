package com.fenghuang.job.view;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 17:12
 * @Email: 15290810931@163.com
 */
@Data
public class JSONMessage implements Serializable {

   private Integer code;
   private String message;
   private String data;
}
