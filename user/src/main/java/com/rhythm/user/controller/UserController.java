package com.rhythm.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.user.entity.User;
import com.rhythm.user.result.Result;
import com.rhythm.user.service.IUserService;
import com.rhythm.user.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @PostMapping(value = "/user")
    public Result addUser(User user) {
        userService.save(user);
        return Result.ok();
    }

    @ResponseBody
    @PutMapping(value = "/user")
    public Result updateUser(User user) {
        userService.updateById(user);
        Result result = Result.ok();
        result.setData("user", user);
        return result;
    }

    @ResponseBody
    @DeleteMapping(value = "/user/{id}")
    public Result DeleteUser(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.ok();
    }

    @ResponseBody
    @GetMapping(value = "/user/{id}")
    public Result getUser(@PathVariable Integer id) {
        Result result = Result.ok();
        result.setData("user", userService.getById(id));
        return result;
    }

    @ResponseBody
    @GetMapping(value = "/users")
    public Result users(Page page) {
        page = userService.page(page);
        return Result.ok(page.getTotal(), page.getCurrent(), page.getSize(), page.getRecords());
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "login";
    }

    /**
     * 用户登录接口
     * @param user user
     * @param request request
     * @return string
     */
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        user.setPassword(CommonUtil.getMD5String(user.getPassword())); // 密码加密

        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            // 验证一下shiro存入的session是否在httpsession中可以拿到
            log.info("验证session域：" + ((User)request.getSession().getAttribute("user")).toString());
            return "user-manage";
        }catch(Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

}
