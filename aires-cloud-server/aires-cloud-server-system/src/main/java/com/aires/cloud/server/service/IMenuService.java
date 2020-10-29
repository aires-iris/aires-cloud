package com.aires.cloud.server.service;


import com.aires.cloud.common.entity.router.VueRouter;
import com.aires.cloud.common.entity.system.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author Aires
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户名查询用户权限信息
     *
     * @param username 用户名
     * @return 权限信息
     */
    Set<String> findUserPermissions(String username);

    /**
     * 通过用户名创建对应的 Vue路由信息
     *
     * @param username 用户名
     * @return 路由信息
     */
    List<VueRouter<Menu>> getUserRouters(String username);
}
