package com.rhythm.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhythm.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends BaseMapper<User> {
}
