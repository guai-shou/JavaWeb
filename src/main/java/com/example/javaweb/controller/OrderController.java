package com.example.javaweb.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Order;
import com.example.javaweb.entity.vo.OrderVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
//@SaCheckLogin
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /* 查询全部订单 */
    @GetMapping("/selectAll")
    public Result<List<OrderVo>> selectAll() {
        List<OrderVo> orderVoList = orderService.selectAll();
        if (orderVoList != null) {
            return Result.ok(orderVoList);
        }
        return Result.fail();
    }

    /* 根据商品名字查询订单 */
    @GetMapping("/select/{bookName}")
    public Result<List<OrderVo>> select(@PathVariable("bookName") String bookName) {
        List<OrderVo> orderVoList = orderService.select(bookName);
        if (orderVoList != null) {
            return Result.ok(orderVoList);
        }
        return Result.fail();
    }

    /* 根据订单编号查询订单 */
    @GetMapping("/select/orderSerial/{orderSerial}")
    public Result<OrderVo> selectOrderByOrderSerial(@PathVariable("orderSerial") String orderSerial) {
        OrderVo orderVo = orderService.selectByOrderSerial(orderSerial);
        if (orderVo != null) {
            return Result.ok(orderVo);
        }
        return Result.build(null, ResultEnum.ORDER_NOT_FOUND);
    }

    /* 指定商品名称的分页查询订单 */
    @GetMapping("/{bookName}/{current}/{limit}")
    public Result<Page<OrderVo>> pageQuery(@PathVariable("bookName") String bookName,
                                           @PathVariable("current") Integer current,
                                           @PathVariable("limit") Integer limit) {
        return Result.ok(orderService.pageQuery(bookName, current, limit));
    }
    /* 分页查询订单 */
    @GetMapping("/{current}/{limit}")
    public Result<Page<OrderVo>> pageQuery(@PathVariable("current") Integer current,
                                           @PathVariable("limit") Integer limit) {
        return Result.ok(orderService.pageQuery(current, limit));
    }

    /* 添加订单 */
    @PutMapping("/addOrder")
    public Result<OrderVo> addOrder(@RequestBody OrderVo orderVo) {
        try {
            orderVo = orderService.addOrder(orderVo);
            if (orderVo != null) {
                return Result.ok(orderVo);
            }
            return Result.fail();
        }catch (JavaWebException e) {
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 支付订单 */
    @PutMapping("/payOrder")
    public Result<OrderVo> payOrder(@RequestBody OrderVo orderVo) {
        if (orderVo == null || orderVo.getOrderSerial() == null) {
            return Result.build(null, ResultEnum.PARAMETERS_NOT);
        }
        try {
            OrderVo order = orderService.payOrder(orderVo.getOrderSerial());
            if (order != null) {
                return Result.ok(order);
            }
            return Result.fail();
        }catch (JavaWebException e) {
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 删除订单 */
    @DeleteMapping("/deleteOrder")
    public Result<Void> deleteOrder(@RequestBody OrderVo orderVo) {
        try {
            orderService.deleteOrder(orderVo);
            return Result.ok();
        }catch (JavaWebException e) {
            return Result.build(e.getCode(), e.getMessage());
        }
    }


}
