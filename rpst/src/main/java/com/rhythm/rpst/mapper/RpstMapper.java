package com.rhythm.rpst.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rhythm.common.entity.User;
import com.rhythm.rpst.entity.Rpst;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<User> selectUsersByRpstId(Integer rpstId);

    @Delete("delete from user_rpst where rpstId = #{rpstId}")
    void delRelationByRpstId(Integer rpstId);

    void addRelation(@Param("rpstId") Integer rpstId, @Param("userIds") List<Integer> userIds);

}
