package com.example.javaweb.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.Book;
import com.example.javaweb.entity.vo.BookVo;
import com.example.javaweb.result.Result;
import com.example.javaweb.service.IBookService;
import com.example.javaweb.utils.FileDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;
    /* 1.查询全部 */
    @GetMapping("/selectAll")
    public Result<List<Book>> selectAll() {
        return Result.ok(bookService.list());
    }
    /* 2.根据ID查询商品 */
    @GetMapping("/select/{id}")
    public Result<Book> selectBookById(@PathVariable("id") Integer id) {
        return Result.ok(bookService.getById(id));
    }
    /* 3.分页查询 */
    @PostMapping("/{current}/{limit}")
    public Result<Page<Book>> pageQuery(@PathVariable("current") Integer current,
                                  @PathVariable("limit") Integer limit,
                                  @RequestBody(required = false) BookVo bookVo) {
        Page<Book> bookPage = bookService.pageQuery(current, limit, bookVo);
        return Result.ok(bookPage);
    }
    /* 4.删除书籍 */
    @DeleteMapping("/delete/{id}")
    public Result<Book> deleteBookById(@PathVariable("id") Integer id) {
        boolean b = bookService.removeById(id);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }
    /* 5.批量删除书籍 */
    @DeleteMapping("/delete")
    public Result<Book> deleteBook(@RequestBody List<Integer> idList) {
        boolean b = bookService.removeByIds(idList);
        if(b) {
            return Result.ok();
        }
        return Result.fail();
    }
    /* 6.增加书籍 */
    @PostMapping("/addBook")
    public Result<Book> addBook(@RequestBody Book book) {
        boolean b = bookService.save(book);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }
    /* 7.修改书籍 */
    @PutMapping("/updateBook")
    public Result<Book> updateBook(@RequestBody Book book) {
        boolean b = bookService.saveOrUpdate(book);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }
    /* 8.根据书名查询书籍 */
    @PostMapping("/select")
    public Result<List<Book>> selectByBookName(@RequestBody BookVo bookVo) {
        List<Book> bookList = bookService.selectByBookName(bookVo);
        return Result.ok(bookList);
    }
    /* 9.商品文件导出 */
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        List<Book> list = bookService.list(null);
        try {
            FileDownload.download(response, Book.class, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
