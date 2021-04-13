package com.rhythm.rpst.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.result.Result;
import com.rhythm.rpst.entity.Rpst;
import com.rhythm.rpst.service.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getRpsts(Page page, HttpSession session) {
        int userId = 1;
        Object userIdObj = session.getAttribute("userId");
        log.info("验证user对象的存储：" + session.getAttribute("user"));
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
        page = rpstService.getRpstsByUserId(page, userId);
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

    @PostMapping(value = "/rpst")
    public Result addRpst(@RequestBody Rpst rpst) {
        log.info("新增仓库，" + rpst.toString());
        rpstService.save(rpst);
        return Result.ok();
    }

    @PutMapping(value = "/rpst")
    public Result updateRpst(@RequestBody Rpst rpst) {
        log.info("更新仓库信息，" + rpst.toString());
        rpstService.updateById(rpst);
        Result result = Result.ok();
        result.setData(rpst);
        return result;
    }

    @DeleteMapping(value = "/rpst/{id}")
    public Result DeleteRpst(@PathVariable Integer id) {
        log.info("删除仓库信息，仓库id为：" + id);
        rpstService.removeById(id);
        return Result.ok();
    }

    @GetMapping(value = "/rpst/{id}")
    public Result getRpst(@PathVariable Integer id) {
        Result result = Result.ok();
        result.setData(rpstService.getById(id));
        return result;
    }

}
