package com.rhythm.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    Page selectInventory(@Param("page")Page page, @Param("rpstId") Integer rpstId);

    @Select("select * from rpst_product where rpstId = #{rpstId} and productId = #{productId}")
    Map<String, Integer> selectInventory1(@Param("rpstId") Integer rpstId, @Param("productId") Integer productId);

    @Insert("insert into rpst_product value(#{rpstId}, #{productId}, #{productNum})")
    void insertInventory(@Param("rpstId") Integer rpstId, @Param("productId") Integer productId,
                         @Param("productNum") Integer productNum);

    @Select("select distinct type from product")
    List<String> getProductTypes();

    @Select("SELECT COUNT(num) FROM(SELECT rp.rpstId, p.size * rp.productNum AS num FROM rpst_product rp JOIN product p ON rp.productId = p.id) temp WHERE temp.rpstId = #{rpstId}")
    Integer getNum(@Param("rpstId") Integer rpstId);

    @Select("SELECT COUNT(*) FROM rpst_product WHERE productId = #{productId}")
    Integer getUsed(@Param("productId") Integer productId);

    Page getByFilter(@Param("page")Page page, @Param("product") Product product);

}
