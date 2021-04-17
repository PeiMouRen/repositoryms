package com.rhythm.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.product.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class test {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test() {
        Page<Map<String, Integer>> page = new Page(2,1);
        page = productMapper.selectInventory(page,1);
        log.info(page.getTotal() + "");
        for (Map<String, Integer> map : page.getRecords()) {
            log.info(map.toString());
        }
    }
}
