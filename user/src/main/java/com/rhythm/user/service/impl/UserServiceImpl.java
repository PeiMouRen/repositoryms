package com.rhythm.user.service.impl;

import com.rhythm.user.entity.User;
import com.rhythm.user.mapper.UserMapper;
import com.rhythm.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
