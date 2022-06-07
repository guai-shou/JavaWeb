package com.example.javaweb.mapper;

import com.example.javaweb.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from `indent` where order_serial = #{orderSerial}")
    Order selectByOrderSerial(@Param("orderSerial") String orderSerial);

    @Update("update `indent` set is_pay = 1 where order_serial = #{orderSerial}")
    void updatePayByOrderSerial(@Param("orderSerial") String orderSerial);
}
