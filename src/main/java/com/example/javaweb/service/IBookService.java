package com.example.javaweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javaweb.entity.vo.BookVo;
import com.example.javaweb.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
public interface IBookService extends IService<Book> {
    // 分页查询
    Page<Book> pageQuery(Integer current, Integer size, BookVo bookVo);

    void updateStockById(Integer bid, Integer updateNumber);

    List<Book> selectByBookName(BookVo bookVo);
}
