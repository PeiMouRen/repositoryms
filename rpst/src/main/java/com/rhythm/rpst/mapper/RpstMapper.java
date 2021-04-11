package com.rhythm.rpst.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.rpst.entity.Rpst;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-11
 */
@Repository
public interface RpstMapper extends BaseMapper<Rpst> {

    Page<Rpst> selectRpstsByUserId(Page page, Integer userId);
}
