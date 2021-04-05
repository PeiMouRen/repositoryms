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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class Test {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @org.junit.Test
    public void userTest() {
       /* User user = new User();
        user.setUsername("admin");
        user.setPassword(CommonUtil.getMD5String("admin"));
        user.setPhone("12345612341");
        user.setLevel(UserLevel.ADMIN.getLevel());
        userMapper.insert(user);*/
        Page<User> page = new Page<>(1, 2);
        page = userService.page(page, new QueryWrapper<User>());
        System.out.println(page.getTotal());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        for (User user: page.getRecords()) {
            System.out.println(user);
        }
        System.out.println(page.toString());

    }

}
