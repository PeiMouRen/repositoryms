package com.rhythm.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.user.entity.User;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private ObjectMapper objectMapper;

    @RequiresRoles(value = "manager")
    @RequestMapping(value = "/user-manage")
    public String userManage() {
        return "user-manage";
    }

    @RequestMapping(value = "/rpst-manage")
    public String rpstManage(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "rpst-manage";
    }

    @RequestMapping(value = "/rpst-info")
    public String rpstInfo(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "rpst-info";
    }

    @RequestMapping(value = "/product-manage")
    public String productManage(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "product-manage";
    }

    @RequestMapping(value = "/order-manage")
    public String orderManage(Model model, HttpSession session) {
        try {
            User user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
            model.addAttribute("userLevel", user.getLevel());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "order-manage";
    }


}
