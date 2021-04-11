package com.rhythm.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    @RequestMapping(value = "/user-manage")
    public String userManage() {
        return "user-manage";
    }

    @RequestMapping(value = "/test1")
    public String test1() {
        return "welcome";
    }

    @RequestMapping(value = "/test2")
    public String test2() {
        return "success";
    }
}
