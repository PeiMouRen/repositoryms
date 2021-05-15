package com.rhythm.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.order.entity.Bzorder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-19
 */
public interface IBzorderService extends IService<Bzorder> {

    Page getByFilter(Page page, Bzorder order);
}
