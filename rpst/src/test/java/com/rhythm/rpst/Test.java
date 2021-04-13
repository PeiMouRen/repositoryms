package com.rhythm.rpst;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.rpst.entity.Rpst;
import com.rhythm.rpst.mapper.RpstMapper;
import com.rhythm.rpst.service.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpstApplication.class)
public class Test {

    @Autowired
    private RpstMapper rpstMapper;
    @Autowired
    private IRpstService rpstService;

    @org.junit.Test
    public void test() {
        Page<Rpst> page = new Page(2, 2);
        page = rpstService.getRpstsByUserId(page, 1);
        List<Rpst> rpsts = page.getRecords();
        for (Rpst rpst: rpsts) {
            System.out.println(rpst.toString());
        }
    }
}
