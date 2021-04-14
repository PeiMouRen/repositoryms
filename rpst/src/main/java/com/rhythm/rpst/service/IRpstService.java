package com.rhythm.rpst.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.rpst.entity.Rpst;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-11
 */
public interface IRpstService extends IService<Rpst> {

    Page<Rpst> getRpsts(Page<Rpst> page);

    Page<Rpst> getRpstsByUserId(Page<Rpst> page, Integer userId);

    void addRpst(Rpst rpst);

    void delRpst(Integer rpstId);

    void updRpst(Rpst rpst);

}
