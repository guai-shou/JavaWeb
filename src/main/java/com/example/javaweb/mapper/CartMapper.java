package com.example.javaweb.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javaweb.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select b_id from cart where u_id = #{uid};")
    List<Integer> selectBidByUid(@Param("uid") Integer uid);
    @Select("select * from cart where u_id = #{uid};")
    List<Cart> selectListByUid(@Param("uid") Integer uid);
}
