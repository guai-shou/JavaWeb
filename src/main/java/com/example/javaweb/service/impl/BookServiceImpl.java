package com.example.javaweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.example.javaweb.entity.vo.BookVo;
import com.example.javaweb.mapper.BookMapper;
import com.example.javaweb.result.Result;
import com.example.javaweb.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Value("${upload.fileAddress}")
    private String fileAddress;

    @Override
    public Page<Book> pageQuery(Integer current, Integer size, BookVo bookVo) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 根据 QueryBook 对象过滤 分页查询
        Optional.ofNullable(bookVo)
                .ifPresent(book -> {
                    Optional.ofNullable(book.getBookName()).ifPresent(bookName -> {wrapper.like("book_name", bookName);});
                    Optional.ofNullable(book.getBookPriceBegin()).ifPresent(bookPriceBegin -> {wrapper.ge("book_price", bookPriceBegin);});
                    Optional.ofNullable(book.getBookPriceEnd()).ifPresent(bookPriceEnd -> {wrapper.le("book_price", bookPriceEnd);});
                    Optional.ofNullable(book.getBookStock()).ifPresent(bookStock -> {wrapper.eq("book_stock", bookStock);});
                    Optional.ofNullable(book.getBookSales()).ifPresent(bookSales -> {wrapper.eq("book_sales", bookSales);});
                    Optional.ofNullable(book.getBookDescription()).ifPresent(bookDescription -> {wrapper.like("book_description", bookDescription);});
                });
        // 是否要查询已删除的书籍
        Page<Book> page = new Page<>(current, size);
        return baseMapper.selectPage(page, wrapper);
    }


    @Override
    public void updateStockById(Integer bid, Integer updateNumber) {
        baseMapper.updateStockById(bid, updateNumber);
    }

    @Override
    public List<Book> selectByBookName(BookVo bookVo) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 根据 QueryBook 对象过滤 分页查询
        Optional.ofNullable(bookVo).flatMap(book -> Optional.ofNullable(book.getBookName())).ifPresent(bookName -> {
            wrapper.like("book_name", bookName);
        });
        return baseMapper.selectList(wrapper);
    }


    @Override
    public boolean save(Book book) {
        if (book.getBookImg().contains(fileAddress)) {
            return super.save(book);
        }
        // 配置传入数据库的路径
        book.setBookImg(fileAddress + book.getBookImg());
        return super.save(book);
    }

    @Override
    public boolean saveOrUpdate(Book book) {
        if (book.getBookImg().contains(fileAddress)) {
            return super.saveOrUpdate(book);
        }
        if (StringUtils.hasLength(book.getBookImg())) {
            book.setBookImg(fileAddress + book.getBookImg());
        }
        return super.saveOrUpdate(book);
    }
}
