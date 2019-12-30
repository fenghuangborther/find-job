package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqLoginLog;
import com.fenghuang.job.view.LoginLogView;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 16:01
 * @Email: 15290810931@163.com
 */
public interface LoginLogService {
    /**
     *根据条件查询日志信息且分页
     * @param reqLoginLog
     * @return
     */
    PageInfo<LoginLogView> findLoginLogPage(ReqLoginLog reqLoginLog);

    /**
     * 根据userId|状态| 日期|ip 条件进行查询登录日志
     * @param reqLoginLog
     * @return
     */
    List<LoginLogView> findLogin(ReqLoginLog reqLoginLog);

    /**
     * 添加登录日志
     * @param reqLoginLog
     * @return
     */
    int insertLoginLog(ReqLoginLog reqLoginLog);
}
