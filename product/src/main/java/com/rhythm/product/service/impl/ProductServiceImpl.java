package com.rhythm.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.Rpst;
import com.rhythm.common.result.Result;
import com.rhythm.product.Enum.ProductOperate;
import com.rhythm.product.entity.Product;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhythm.product.service.inter.IRpstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IRpstService rpstService;

    @Override
    public Result updateInventory(Integer rpstId, Integer productId, Integer productNum, Integer operate) {
        Result result = new Result();
        log.info(rpstService.getRpst(rpstId).toString());
        Map<String, Object> rpst = (Map<String, Object>)(rpstService.getRpst(rpstId).getData());
        int size = (int)rpst.get("size");
        Map<String, Integer> map = productMapper.selectInventory(rpstId, productId);
        if (operate == ProductOperate.IN.getOperate()) {
            // 入库先判断库存中有没有，没有的话先插入
            if (map == null) {
                // 没有库存，判断入库的数量是否超标
                if (productNum > size) {
                    result.setCode(201);
                    result.setMessage("入库失败，仓库容量不足！");
                    return result;
                } else {
                    productMapper.insertInventory(rpstId, productId, productNum);
                    result.setCode(200);
                    return result;
                }
            } else {
                int oldNum = map.get("productNum");
                if (productNum + oldNum > size) {
                    result.setCode(201);
                    result.setMessage("入库失败，仓库容量不足！");
                    return result;
                } else {
                    productMapper.updateInventory(rpstId, productId, productNum, operate);
                    result.setCode(200);
                    return result;
                }
            }
        } else {
            int oldNum = map.get("productNum");
            if (productNum > oldNum) {
                result.setCode(201);
                result.setMessage("出库失败，库存不足！");
                return result;
            } else {
                productMapper.updateInventory(rpstId, productId, productNum, operate);
                result.setCode(200);
                return result;
            }
        }
    }

    @Override
    public Page getInventory(Page page, Integer rpstId) {
        page = productMapper.selectInventory(page, rpstId);
        List<Product> records = new ArrayList<>();
        for (Object obj : page.getRecords()) {
            Map<String, Integer> map = (Map<String, Integer>)obj;
            Product product = productMapper.selectById(map.get("productId"));
            product.setProductNum(map.get("productNum"));
            records.add(product);
        }
        page.setRecords(records);
        return page;
    }
}
