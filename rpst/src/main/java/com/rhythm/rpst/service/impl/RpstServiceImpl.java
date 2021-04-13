package com.rhythm.rpst.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.rpst.entity.Rpst;
import com.rhythm.rpst.mapper.RpstMapper;
import com.rhythm.rpst.service.IRpstService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-11
 */
@Service
public class RpstServiceImpl extends ServiceImpl<RpstMapper, Rpst> implements IRpstService {

    @Autowired
    private RpstMapper rpstMapper;

    @Override
    public Page<Rpst> getRpstsByUserId(Page<Rpst> page, Integer userId) {
        page = rpstMapper.selectRpstsByUserId(page, userId);
        for (Rpst rpst: page.getRecords()) {
            rpst.setUsers(rpstMapper.selectUsersByRpstId(rpst.getId()));
        }
        return page;
    }
}
