package com.rhythm.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-03
 */
public interface IUserService extends IService<User> {

    Page<User> getUsers(Page<User> page);

    void addUser(User user);

    void delUser(Integer userId);

    void updUser(User user);
}
