package com.rhythm.product.mapper;

import com.rhythm.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-15
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    void updateInventory(@Param("rpstId") Integer rpstId, @Param("productId") Integer productId,
                         @Param("productNum") Integer productNum, @Param("operate") Integer operate);

    @Delete("delete from rpst_product where rpstId = #{rpstId}")
    void deleteAllInventory(@Param("rpstId") Integer rpstId);
}
