package com.aires.cloud.server.service.impl;


import com.aires.cloud.common.constant.AiresConstant;
import com.aires.cloud.common.entity.QueryRequest;
import com.aires.cloud.common.entity.system.LoginLog;
import com.aires.cloud.common.entity.system.SystemUser;
import com.aires.cloud.common.utils.AiresUtil;
import com.aires.cloud.common.utils.SortUtil;
import com.aires.cloud.server.mapper.LoginLogMapper;
import com.aires.cloud.server.service.ILoginLogService;
import com.aires.cloud.server.util.AddressUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Aires
 */

@Service("loginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Override
    public IPage<LoginLog> findLoginLogs(LoginLog loginLog, QueryRequest request) {
        QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(loginLog.getUsername())) {
            queryWrapper.lambda().eq(LoginLog::getUsername, loginLog.getUsername().toLowerCase());
        }
        if (StringUtils.isNotBlank(loginLog.getLoginTimeFrom()) && StringUtils.isNotBlank(loginLog.getLoginTimeTo())) {
            queryWrapper.lambda()
                    .ge(LoginLog::getLoginTime, loginLog.getLoginTimeFrom())
                    .le(LoginLog::getLoginTime, loginLog.getLoginTimeTo());
        }

        Page<LoginLog> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "loginTime", AiresConstant.ORDER_DESC, true);

        return this.page(page, queryWrapper);
    }

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = AiresUtil.getHttpServletRequestIpAddress();
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }

    @Override
    public void deleteLoginLogs(String[] ids) {
        List<String> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
    }

    @Override
    public Long findTotalVisitCount() {
        return this.baseMapper.findTotalVisitCount();
    }

    @Override
    public Long findTodayVisitCount() {
        return this.baseMapper.findTodayVisitCount();
    }

    @Override
    public Long findTodayIp() {
        return this.baseMapper.findTodayIp();
    }

    @Override
    public List<Map<String, Object>> findLastTenDaysVisitCount(SystemUser user) {
        return this.baseMapper.findLastTenDaysVisitCount(user);
    }

    @Override
    public List<LoginLog> findUserLastSevenLoginLogs(String username) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);

        QueryRequest request = new QueryRequest();
        request.setPageNum(1);
        // 近7日记录
        request.setPageSize(7);

        IPage<LoginLog> loginLogs = this.findLoginLogs(loginLog, request);
        return loginLogs.getRecords();
    }
}
