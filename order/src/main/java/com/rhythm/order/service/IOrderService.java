package com.rhythm.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-18
 */
public interface IOrderService extends IService<Order> {

    Page<Order> getOrders(Page<Order> page);

}
