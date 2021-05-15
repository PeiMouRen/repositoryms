package com.rhythm.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.order.entity.Bzorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-19
 */
@Repository
public interface BzorderMapper extends BaseMapper<Bzorder> {

    Page getByFilter(@Param("page")Page page, @Param("order") Bzorder order);
}
