package com.example.javaweb.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.example.javaweb.entity.Order;
import com.example.javaweb.entity.User;
import com.example.javaweb.entity.vo.OrderVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.mapper.OrderMapper;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IBookService;
import com.example.javaweb.service.ICartService;
import com.example.javaweb.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javaweb.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICartService cartService;

    @Override
    public List<OrderVo> selectAll() {
        return select(null);
    }

    @Override
    public List<OrderVo> select(String bookName) {
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", id);
        // 查询该用户所有的订单列表信息
        List<Order> orderList = baseMapper.selectList(wrapper);
        // 创建订单列表，包含准确商品信息
        List<OrderVo> orderVoList = getOrderVoList(orderList);

        if (StringUtils.hasLength(bookName)) {
            orderVoList.removeIf(orderVo -> NoContainsBookName(bookName, orderVo));
        }
        return orderVoList;
    }

    @Override
    public Page<OrderVo> pageQuery(String bookName, Integer current, Integer limit) {
        //Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        //wrapper.eq("u_id", id);
        Page<Order> page = new Page<>(current, limit);
        // 查询该用户所有的订单列表信息
        page = baseMapper.selectPage(page, wrapper);
        List<Order> orderList = page.getRecords();
        long total = page.getSize();

        // 创建订单列表，包含准确商品信息
        List<OrderVo> orderVoList = getOrderVoList(orderList);
        Page<OrderVo> pageOrderVo = new Page<>(current, limit);

        if (StringUtils.hasLength(bookName)) {
            orderVoList.removeIf(orderVo -> NoContainsBookName(bookName, orderVo));
        }
        pageOrderVo.setRecords(orderVoList);
        pageOrderVo.setTotal(total);
        return pageOrderVo;
    }

    @Override
    public Page<OrderVo> pageQuery(Integer current, Integer limit) {
        return pageQuery(null, current, limit);
    }

    /* 添加订单 */
    @Transactional
    @Override
    public OrderVo addOrder(OrderVo orderVo) throws JavaWebException {
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        User user = userService.getById(id);
        if (!StringUtils.hasLength(user.getCargoName()) ||
            !StringUtils.hasLength(user.getCargoPhone()) ||
            !StringUtils.hasLength(user.getCargoAddress())) {
            throw new JavaWebException(ResultEnum.USER_NOT_CARGO_INFO);
        }
        if (orderVo.getBookList().size() == 0 ||
            orderVo.getBookNumber().size() == 0) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        // 查询要添加的商品是否还没有库存
        List<Integer> bids = orderVo.getBookList()
                                .stream()
                                .map(Book::getId)
                                .collect(Collectors.toList());
        List<Integer> bookNumber = orderVo.getBookNumber();
        List<Book> bookList = bookService.listByIds(bids);

        if (bookList.size() != bookNumber.size()) throw new JavaWebException(ResultEnum.BOOK_INVALID);

        List<Integer> updateNumber = new ArrayList<>(bookNumber.size());
        for (int i = 0; i < bookList.size(); i++) {
            int bookStock = bookList.get(i).getBookStock();
            int bookNum = bookNumber.get(i);
            if (bookStock < bookNum) {
                throw new JavaWebException(ResultEnum.BOOK_STOCK_LACK);
            }
            updateNumber.add(bookStock - bookNum);
        }

        // 到达这里代表商品可被添加到订单
        Order order = new Order();
        order.setUid(id);
        String orderSerial = UUID.randomUUID().toString();
        order.setOrderSerial(orderSerial);
        orderVo.setOrderSerial(orderSerial);
        orderVo.setBookList(bookList);
        orderVo.setIsPay(0);
        order.setBookNumber(bookNumber.stream().map(String::valueOf).collect(Collectors.joining("#")));
        order.setBookList(bids.stream().map(String::valueOf).collect(Collectors.joining("#")));
        order.setIsPay(0);
        // 数据库事务操作此段代码
        try {
            baseMapper.insert(order);
            for (int i = 0; i < bids.size(); i++) {
                bookService.updateStockById(bids.get(i), updateNumber.get(i));
            }
            cartService.deleteUserCart(bids);
        }catch (Exception e) {
            e.printStackTrace();
            throw new JavaWebException(ResultEnum.BOOK_STOCK_LACK);
        }

        return orderVo;
    }

    /* 支付订单 */
    @Override
    public OrderVo payOrder(String orderSerial) throws JavaWebException {
        baseMapper.updatePayByOrderSerial(orderSerial);
        Order order = baseMapper.selectByOrderSerial(orderSerial);
        if (order == null) {
            throw new JavaWebException(ResultEnum.ORDER_NOT_FOUND);
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        List<OrderVo> orderVoList = getOrderVoList(orderList);
        return orderVoList.get(0);
    }

    @Transactional
    @Override
    public void deleteOrder(OrderVo orderVo) throws JavaWebException {
        if (!StringUtils.hasLength(orderVo.getOrderSerial())) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        Order order = baseMapper.selectByOrderSerial(orderVo.getOrderSerial());
        if (order == null) {
            throw new JavaWebException(ResultEnum.ORDER_NOT_FOUND);
        }
        if (order.getIsPay() == 1) {
            baseMapper.deleteById(order.getId());
        }else {
            // 获取书籍列表id
            List<Integer> bids = Arrays
                    .stream(order.getBookList().split("#"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            // 获取每本书购买的商品数目
            List<Integer> bookNumber = Arrays
                    .stream(order.getBookNumber().split("#"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Book> bookList = bookService.listByIds(bids);

            List<Integer> updateNumber = new ArrayList<>(bookNumber.size());
            if (bookList.size() != bookNumber.size()) throw new JavaWebException(ResultEnum.BOOK_INVALID);
            for (int i = 0; i < bookList.size(); i++) {
                int bookStock = bookList.get(i).getBookStock();
                int bookNum = bookNumber.get(i);
                updateNumber.add(bookStock + bookNum);
            }

            baseMapper.deleteById(order);
            for (int i = 0; i < bids.size(); i++) {
                bookService.updateStockById(bids.get(i), updateNumber.get(i));
            }
        }
    }

    @Override
    public OrderVo selectByOrderSerial(String orderSerial) {
        Order order = baseMapper.selectByOrderSerial(orderSerial);
        if (order == null) {
            return null;
        }
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        List<OrderVo> orderVoList = getOrderVoList(orders);
        return orderVoList.get(0);
    }

    /* 删除订单 */



    // 该订单对象的商品列表中 没有包含 bookName 书名
    private boolean NoContainsBookName(String bookName, OrderVo orderVo) {
        boolean flag = true;
        List<Book> bookList = orderVo.getBookList();
        for (Book book : bookList) {
            if (book.getBookName().contains(bookName)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    // 通过订单列表对象，获取详细商品的订单列表对象
    private List<OrderVo> getOrderVoList(List<Order> orderList) {
        List<OrderVo> orderVoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo, OrderVo.class);
            String bookList = order.getBookList();
            String bookNumberList = order.getBookNumber();
            // 商品id之间使用 # 分割
            if (!bookList.equals("")) {
                List<String> book = Arrays.asList(bookList.split("#"));
                List<Integer> bookNumber = Arrays
                        .stream(bookNumberList.split("#"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                List<Book> books = bookService.listByIds(book);
                orderVo.setBookList(books);
                orderVo.setBookNumber(bookNumber);
            }
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }
}
