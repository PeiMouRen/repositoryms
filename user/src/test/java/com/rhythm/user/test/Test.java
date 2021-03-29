package com.rhythm.user.test;

import com.rhythm.user.UserApplication;
import com.rhythm.user.dao.IUserDao;
import com.rhythm.user.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class Test {

    @Autowired
    private IUserDao userDao;

    @org.junit.Test
    public void Test() {
        User user = new User();
        user.setUsername("test2");
        user.setPassword("1234");
        userDao.insert(user);
        System.out.println("finish!");
    }
}
