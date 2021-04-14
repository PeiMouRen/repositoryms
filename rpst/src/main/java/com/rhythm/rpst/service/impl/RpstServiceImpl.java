package com.rhythm.rpst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.User;
import com.rhythm.rpst.entity.Rpst;
import com.rhythm.rpst.mapper.RpstMapper;
import com.rhythm.rpst.service.IRpstService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Rpst> getRpsts(Page<Rpst> page) {
        page = rpstMapper.selectPage(page, new QueryWrapper<>());
        for (Rpst rpst: page.getRecords()) {
            rpst.setUserIds(rpstMapper.selectUserIdsByRpstId(rpst.getId()));
        }
        return page;
    }

    @Override
    public Page<Rpst> getRpstsByUserId(Page<Rpst> page, Integer userId) {
        page = rpstMapper.selectRpstsByUserId(page, userId);
        for (Rpst rpst: page.getRecords()) {
            rpst.setUserIds(rpstMapper.selectUserIdsByRpstId(rpst.getId()));
        }
        return page;
    }

    @Override
    public void addRpst(Rpst rpst) {
        rpstMapper.insert(rpst);
        rpstMapper.addRelation(rpst.getId(), rpst.getUserIds());
    }

    @Override
    public void delRpst(Integer rpstId) {
        rpstMapper.deleteById(rpstId);
        rpstMapper.delRelationByRpstId(rpstId);
    }

    @Override
    public void updRpst(Rpst rpst) {
        rpstMapper.updateById(rpst);

        rpstMapper.delRelationByRpstId(rpst.getId());
        rpstMapper.addRelation(rpst.getId(), rpst.getUserIds());
    }

}
