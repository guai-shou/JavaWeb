package com.example.javaweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.example.javaweb.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javaweb.entity.vo.CartVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
public interface ICartService extends IService<Cart> {
    /* 查询用户购物车 */
    List<CartVo> selectUserCart();
    /* 分页查询用户购物车，可增加书名为过滤条件 */
    Page<CartVo> selectUserCartByBookName(Integer current, Integer limit, String bookName);
    /* 更新用户购物车 */
    CartVo updateUserCart(Cart cart);
    /* 通过ID删除用户购物车 */
    Book deleteUserCartById(Integer id);
    /* 删除用户购物车 */
    List<Book> deleteUserCart(List<Integer> idList);
    /* 增加书籍到用户购物车 */
    Book addBookUserCart(Integer id);
    /* 分页查询用户购物车 */
    Page<Book> pageQuery(Integer current, Integer limit);
}
