package com.rhythm.user.mapper;

import com.rhythm.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzpei
 * @since 2021-04-03
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select rpstId from user_rpst where userId = #{userId}")
    List<Integer> selectRpstIdsByUserId(Integer userId);

    @Delete("delete from user_rpst where userId = #{userId}")
    void delRelationByUserId(Integer userId);

    void addRelation(@Param("userId") Integer userId, @Param("rpstIds") List<Integer> rpstIds);

    @Select("select r.name from role r where r.id in(select roleId from user_role where userId = #{userId})")
    HashSet<String> getRoles(@Param("userId") Integer userId);

}
