package com.example.javaweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javaweb.entity.vo.OrderVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
public interface IOrderService extends IService<Order> {

    List<OrderVo> selectAll();

    List<OrderVo> select(String bookName);

    Page<OrderVo> pageQuery(String bookName, Integer current, Integer limit);

    Page<OrderVo> pageQuery(Integer current, Integer limit);

    OrderVo addOrder(OrderVo orderVo);

    OrderVo payOrder(String orderSerial);

    void deleteOrder(OrderVo orderVo);

    OrderVo selectByOrderSerial(String orderSerial);
}
