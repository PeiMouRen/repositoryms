package com.rhythm.rpst.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.rpst.entity.Rpst;
import com.rhythm.rpst.service.IRpstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzpei
 * @since 2021-04-11
 */
@RestController
@RequestMapping("/rpst")
public class RpstController {

    @Autowired
    private IRpstService rpstService;

    @GetMapping(value = "/rpsts")
    public String getRpsts(Page page, HttpSession session) {
        int userId = 1;
        page = rpstService.getRpstsByUserId(page, userId);
        return "";
    }

}
