package com.rhythm.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.order.entity.Order;
import com.rhythm.order.mapper.OrderMapper;
import com.rhythm.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhythm.order.service.inter.IProductService;
import com.rhythm.order.service.inter.IRpstService;
import com.rhythm.order.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRpstService rpstService;
    @Autowired
    private IProductService productService;

    @Override
    public Page<Order> getOrders(Page<Order> page) {
        page = orderMapper.selectPage(page, new QueryWrapper<>());
        List<Order> orders =  page.getRecords();

        for (Order order : orders) {
            userService.getUser(order.getUserId()).getData();
        }

        return null;
    }
}
