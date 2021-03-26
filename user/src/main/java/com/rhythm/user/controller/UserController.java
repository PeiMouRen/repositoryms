package com.rhythm.user.controller;

import com.rhythm.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/admin")
    public String admin(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        System.out.println("1324");
        return "success";
    }

    @RequestMapping(value = "/student")
    public String student() {

        ModelMap modelMap = new ModelMap();
        return "success";
    }

    @RequestMapping(value = "/teacher")
    public String teacher(Model model) {
        return "success";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        System.out.println("hehe");
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

        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            request.getSession().setAttribute("user", user);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

}
