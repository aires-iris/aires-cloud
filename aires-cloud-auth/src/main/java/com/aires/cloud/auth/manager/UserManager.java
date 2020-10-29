package com.aires.cloud.auth.manager;

import com.aires.cloud.auth.mapper.MenuMapper;
import com.aires.cloud.auth.mapper.UserMapper;
import com.aires.cloud.common.entity.system.Menu;
import com.aires.cloud.common.entity.system.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

     public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        List<String> perms = new ArrayList<>();
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}