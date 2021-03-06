package com.rhythm.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.user.entity.User;
import com.rhythm.user.service.IUserService;
import com.rhythm.user.service.inter.IRpstService;
import com.rhythm.user.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @PostMapping(value = "/user")
    public Result addUser(@RequestBody User user) {
        log.info("新增用户，" + user.toString());
        user.setPassword(CommonUtil.getMD5String("123456"));
        if (userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null) {
            Result result = Result.error();
            result.setMessage("新增失败，该用户已存在！");
            return result;
        }
        userService.addUser(user);
        return Result.ok();
    }

    @ResponseBody
    @PutMapping(value = "/user")
    public Result updateUser(@RequestBody User user) {
        log.info("更新用户信息，" + user.toString());
        userService.updUser(user);
        Result result = Result.ok();
        result.setData(user);
        return result;
    }

    @ResponseBody
    @DeleteMapping(value = "/user/{id}")
    public Result DeleteUser(@PathVariable Integer id) {
        userService.delUser(id);
        return Result.ok();
    }

    @ResponseBody
    @GetMapping(value = "/user/{id}")
    public Result getUser(@PathVariable Integer id) {
        Result result = Result.ok();
        result.setData(userService.getById(id));
        return result;
    }

    @ResponseBody
    @GetMapping(value = "/users")
    public Result users(Page page) {
        page = userService.getUsers(page);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
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
    public String login(User user, Model model, HttpServletRequest request) {
        user.setPassword(CommonUtil.getMD5String(user.getPassword())); // 密码加密

        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            // 验证一下shiro存入的session是否在httpsession中可以拿到
            //log.info("验证session域：" + ((User)request.getSession().getAttribute("user")).toString());
            log.info("验证session域：" + ((int)request.getSession().getAttribute("userId")));
            model.addAttribute("username", user.getUsername());
            return "index";
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

}
