package com.rhythm.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.common.entity.Rpst;
import com.rhythm.common.result.Result;
import com.rhythm.product.entity.Product;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.inter.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class test {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IRpstService rpstService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() {
        Page page = new Page(0,2);
        Product product = new Product();
        product.setId(13);
        //product.setType("类型2");
        product.setRpstId(0);
        page = productMapper.getByFilter(page, product);
        log.info(page.getTotal() + "");
        log.info(page.getRecords().toString());
    }


}
