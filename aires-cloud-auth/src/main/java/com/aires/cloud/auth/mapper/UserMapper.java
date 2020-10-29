package com.aires.cloud.auth.mapper;

import com.aires.cloud.common.entity.system.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<SystemUser> {
    SystemUser findByName(String username);
}