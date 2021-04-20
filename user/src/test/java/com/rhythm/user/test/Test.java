package com.rhythm.user.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.user.Enum.UserLevel;
import com.rhythm.user.UserApplication;
import com.rhythm.user.entity.User;
import com.rhythm.user.mapper.UserMapper;
import com.rhythm.user.service.IUserService;
import com.rhythm.user.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class Test {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @org.junit.Test
    public void userTest() {
        HashSet<String> set = userMapper.getRoles(1);
        log.info("roles: " + set);

    }

}
