package com.rhythm.rpst.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.Enum.UserLevel;
import com.rhythm.common.entity.User;
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
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/rpsts")
    public Result getRpsts(Page page, HttpSession session) {
        User user = null;
        try {
            user = objectMapper.readValue((String)session.getAttribute("user"), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            if (UserLevel.ADMIN.getLevel() == user.getLevel()) {
                page = rpstService.getRpsts(page);
            } else {
                page = rpstService.getRpstsByUserId(page, user.getId());
            }
        }
        Result result = Result.ok();
        result.setTotal(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

    @PostMapping(value = "/rpst")
    public Result addRpst(@RequestBody Rpst rpst) {
        log.info("新增仓库，" + rpst.toString());
        if (rpstService.getOne(new QueryWrapper<Rpst>().eq("name", rpst.getName())) != null) {
            Result result = Result.error();
            result.setMessage("新增失败,该仓库已存在！");
            return result;
        }
        rpstService.addRpst(rpst);
        return Result.ok();
    }

    @PutMapping(value = "/rpst")
    public Result updateRpst(@RequestBody Rpst rpst) {
        log.info("更新仓库信息，" + rpst.toString());
        rpstService.updRpst(rpst);
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
