package com.rhythm.rpst.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.rpst.entity.Rpst;
import com.rhythm.rpst.service.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzpei
 * @since 2021-04-11
 */
@Slf4j
@RestController
@RequestMapping("/rpst")
public class RpstController {

    @Autowired
    private IRpstService rpstService;

    @GetMapping(value = "/rpsts")
    public Result getRpsts(long current, long size, HttpSession session) {
        int userId = 1;
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj != null) {
            userId = (int)userIdObj;
            log.info("获取到userid：" + userId);
        } else {
            log.info("没有获取到userid");
        }
        Enumeration<String> test = session.getAttributeNames();
        log.info("获取session中所有属性：");
        while (test.hasMoreElements()) {
            String name = test.nextElement().toString();
            log.info(name + ": " + session.getAttribute(name));
        }
        Page page = new Page(current,size);
        page = rpstService.getRpstsByUserId(page, userId);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

}
