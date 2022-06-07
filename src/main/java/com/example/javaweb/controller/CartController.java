package com.example.javaweb.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.example.javaweb.entity.Cart;
import com.example.javaweb.entity.vo.BookVo;
import com.example.javaweb.entity.vo.CartVo;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
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
@SaCheckLogin
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private ICartService cartService;
    /* 查询全部购物车 */
    @GetMapping("selectAll")
    public Result<List<CartVo>> selectAll() {
        return Result.ok(cartService.selectUserCart());
    }
    /* 根据商品名称查询购物车 */
    @GetMapping("select/{current}/{limit}/{bookName}")
    public Result<Page<CartVo>> selectByBookName(@PathVariable("current") Integer current,
                                               @PathVariable("limit") Integer limit,
                                               @PathVariable("bookName") String bookName) {
        return Result.ok(cartService.selectUserCartByBookName(current, limit, bookName));
    }
    /* 分页查询购物车列表 */
    @GetMapping("/{current}/{limit}")
    public Result<Page<Book>> pageQuery(@PathVariable("current") Integer current,
                                              @PathVariable("limit") Integer limit) {
        return Result.ok(cartService.pageQuery(current, limit));
    }
    /* 添加商品到购物车 */
    @PostMapping("add/{id}")
    public Result<Book> add(@PathVariable("id")Integer id) {
        Book book = cartService.addBookUserCart(id);
        if (book != null) {
            return Result.ok(book);
        }
        return Result.build(null, ResultEnum.BOOK_NOT_FOUND);
    }
    /* 修改购物车商品数量 */
    @PutMapping("update")
    public Result<CartVo> updateUserCart(@RequestBody Cart cart) {
        System.out.println(cart);
        if (cart == null || cart.getBid() == null) {
            return Result.build(null, ResultEnum.PARAMETERS_NOT);
        }
        CartVo cartVo = cartService.updateUserCart(cart);
        if (cartVo != null) {
            return Result.ok(cartVo);
        }
        return Result.build(null, ResultEnum.BOOK_NOT_FOUND);
    }
    /* 删除购物车中的商品 */
    @DeleteMapping("delete/{id}")
    public Result<Book> deleteUserCartById(@PathVariable("id") Integer id) {
        Book book = cartService.deleteUserCartById(id);
        if (book != null) {
            return Result.ok(book);
        }
        return Result.build(null, ResultEnum.CART_NOT_BOOK);
    }
    /* 批量删除购物车中的商品 */
    @DeleteMapping("delete")
    public Result<List<Book>> deleteUserCart(@RequestBody List<Integer> idList) {
        List<Book> bookList = cartService.deleteUserCart(idList);
        if (bookList != null) {
            return Result.ok(bookList);
        }
        return Result.build(null, ResultEnum.CART_NOT_BOOKS);
    }
}
