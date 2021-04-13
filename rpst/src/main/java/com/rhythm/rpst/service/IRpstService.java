package com.rhythm.rpst.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.rpst.entity.Rpst;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzpei
 * @since 2021-04-11
 */
public interface IRpstService extends IService<Rpst> {

    Page<Rpst> getRpstsByUserId(Page<Rpst> page, Integer userId);

}
