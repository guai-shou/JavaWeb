package com.example.javaweb.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.example.javaweb.entity.Cart;
import com.example.javaweb.entity.vo.CartVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.mapper.CartMapper;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IBookService;
import com.example.javaweb.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Autowired
    private IBookService bookService;

    /* 查找用户购物车 */
    @Override
    public List<CartVo> selectUserCart() {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        // 返回用户的购物车列表
        List<Cart> cartList = baseMapper.selectListByUid(uid);
        ArrayList<Integer> bids = new ArrayList<>();
        // 返回的CartVo
        ArrayList<CartVo> cartVos = new ArrayList<>();

        for (Cart cart : cartList) {
            bids.add(cart.getBid());
        }
        // 从用户的购物车列表中 指定书名查找
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (bids.size() <= 0) {
            return null;
        }
        wrapper.in("id", bids);
        // 获取图书列表，进行返回赋值操作
        List<Book> bookList = bookService.list(wrapper);
        setCartVo(cartList, cartVos, bookList);
        return cartVos;
    }
    /* 指定名称查找用户购物车 */
    @Override
    public Page<CartVo> selectUserCartByBookName(Integer current, Integer limit, String bookName) {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        // 返回用户的购物车列表
        List<Cart> cartList = baseMapper.selectListByUid(uid);
        ArrayList<Integer> bids = new ArrayList<>();
        // 返回的CartVo
        ArrayList<CartVo> cartVos = new ArrayList<>();
        for (Cart cart : cartList) {
            bids.add(cart.getBid());
        }

        // 从用户的购物车列表中 指定书名查找
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (bids.size() <= 0) {
            return null;
        }
        wrapper.in("id", bids);
        Optional.ofNullable(bookName).ifPresent(book_name -> {wrapper.like("book_name", book_name);});
        Page<Book> page = new Page<>(current, limit);
        Page<CartVo> cartVoPage = new Page<>(current, limit);
        page = bookService.page(page, wrapper);

        // 获取图书列表，进行返回赋值操作
        List<Book> bookList = page.getRecords();
        setCartVo(cartList, cartVos, bookList);
        cartVoPage.setTotal(cartVos.size());
        cartVoPage.setRecords(cartVos);

        return cartVoPage;
    }

    private void setCartVo(List<Cart> cartList, ArrayList<CartVo> cartVos, List<Book> bookList) {
        for (Book book : bookList) {
            CartVo cartVo = new CartVo();
            BeanUtils.copyProperties(book, cartVo, CartVo.class);
            for (Cart cart : cartList) {
                if (cart.getBid().equals(book.getId())) {
                    cartVo.setBookNumber(cart.getBookNumber());
                    break;
                }
            }
            cartVos.add(cartVo);
        }
    }

    @Override
    public Book addBookUserCart(Integer id) {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        List<Cart> cartList = baseMapper.selectListByUid(uid);
        Cart cart = new Cart();
        for (Cart c : cartList) {
            if (c.getBid().equals(id)) {
                Book book = bookService.getById(id);
                c.setBookNumber(c.getBookNumber() + 1);
                baseMapper.update(cart, null);
                return book;
            }
        }

        Book book = bookService.getById(id);
        if (book == null) {
            return null;
        }

        cart.setUid(uid);
        cart.setBid(id);
        cart.setBookNumber(1);
        baseMapper.insert(cart);
        return book;
    }

    @Override
    public Page<Book> pageQuery(Integer current, Integer limit) {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        List<Integer> bids = baseMapper.selectBidByUid(uid);

        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (bids.size() <= 0) {
            return null;
        }
        wrapper.in("id", bids);
        Page<Book> page = new Page<>(current, limit);
        return bookService.page(page, wrapper);
    }

    /* 修改购物车商品数量 */
    @Override
    public CartVo updateUserCart(Cart cart) {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        Integer bid = cart.getBid();
        if (bid == null) {
            return null;
        }
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", uid).eq("b_id", bid);
        baseMapper.update(cart, wrapper);
        Book book = bookService.getById(bid);
        CartVo cartVo = new CartVo();
        BeanUtils.copyProperties(book, cartVo, CartVo.class);
        cartVo.setBookNumber(cart.getBookNumber());
        return cartVo;
    }

    @Override
    public Book deleteUserCartById(Integer id) {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", uid);
        wrapper.eq("b_id", id);
        int i = baseMapper.delete(wrapper);
        if (i >= 1) {
            return bookService.getById(id);
        }
        return null;
    }

    @Override
    public List<Book> deleteUserCart(List<Integer> idList) {
        Integer uid = Integer.parseInt((String) StpUtil.getLoginId());
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", uid);
        wrapper.in("b_id", idList);
        int i = baseMapper.delete(wrapper);
        if (i >= 1) {
            return bookService.listByIds(idList);
        }
        return null;
    }
}
