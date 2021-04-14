package com.rhythm.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Rpst;
import com.rhythm.user.result.Result;
import com.rhythm.user.service.inter.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping(value = "/rpst")
public class RpstController {

    @Autowired
    private IRpstService rpstService;

    @GetMapping(value = "/rpsts")
    public Result rpsts(Page page, HttpSession session) {
        log.info("验证session: " + session.getAttribute("userId"));
        log.info("验证session: " + session.getAttribute("user"));
        log.info("验证page：" + page.getCurrent() + " - " + page.getSize());
        return rpstService.getRpsts(page);
    }

    @PostMapping(value = "/rpst")
    public Result addRpst(@RequestBody Rpst rpst) {
        log.info("新增仓库：" + rpst.toString());
        return rpstService.addRpst(rpst);
    }

    @PutMapping(value = "/rpst")
    public Result updateRpst(@RequestBody Rpst rpst) {
        log.info("更新仓库：" + rpst.toString());
        return rpstService.updateRpst(rpst);
    }

    @DeleteMapping(value = "/rpst/{id}")
    public Result DeleteRpst(@PathVariable Integer id) {
        return rpstService.DeleteRpst(id);
    }

    @GetMapping(value = "/rpst/{id}")
    public Result getRpst(@PathVariable Integer id) {
        return rpstService.getRpst(id);
    }

}
