package com.rhythm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.user.entity.User;
import com.rhythm.user.mapper.UserMapper;
import com.rhythm.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> getUsers(Page<User> page) {
        page = userMapper.selectPage(page, new QueryWrapper<User>().ne("level", UserLevel.ADMIN.getLevel()));
        for (User user: page.getRecords()) {
            user.setRpstIds(userMapper.selectRpstIdsByUserId(user.getId()));
        }
        return page;
    }

    @Override
    public void addUser(User user) {
        user.setLevel(UserLevel.OPERATOR_ADVANCED.getLevel());
        userMapper.insert(user);
        userMapper.addRelation(user.getId(), user.getRpstIds());
    }

    @Override
    public void delUser(Integer userId) {
        userMapper.deleteById(userId);
        userMapper.delRelationByUserId(userId);
    }

    @Override
    public void updUser(User user) {
        userMapper.updateById(user);

        userMapper.delRelationByUserId(user.getId());
        userMapper.addRelation(user.getId(), user.getRpstIds());
    }
}
