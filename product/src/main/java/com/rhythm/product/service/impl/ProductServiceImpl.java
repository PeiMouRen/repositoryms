package com.rhythm.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.product.entity.Product;
import com.rhythm.product.mapper.ProductMapper;
import com.rhythm.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void updateInventory(Integer rpstId, Integer productId, Integer productNum, Integer operate) {
        productMapper.updateInventory(rpstId, productId, productNum, operate);
    }

    @Override
    public Page getInventory(Page page, Integer rpstId) {
        return productMapper.selectInventory(page, rpstId);
    }
}
