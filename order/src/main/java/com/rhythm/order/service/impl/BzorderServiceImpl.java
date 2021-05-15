package com.rhythm.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.order.entity.Bzorder;
import com.rhythm.order.mapper.BzorderMapper;
import com.rhythm.order.service.IBzorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-19
 */
@Service
public class BzorderServiceImpl extends ServiceImpl<BzorderMapper, Bzorder> implements IBzorderService {

    @Autowired
    private BzorderMapper bzorderMapper;

    @Override
    public Page getByFilter(Page page, Bzorder order) {
        return bzorderMapper.getByFilter(page, order);
    }
}
