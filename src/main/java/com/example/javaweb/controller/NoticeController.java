package com.example.javaweb.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.example.javaweb.entity.Notice;
import com.example.javaweb.entity.vo.NoticeVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.result.Result;
import com.example.javaweb.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 21:20
 */
@RestController
@RequestMapping("/notice")
//@SaCheckPermission("notice")
@CrossOrigin
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /* 增加公告 */
    @PostMapping("addNotice")
    public Result<Void> addNotice(@RequestBody NoticeVo noticeVo) {
        try {
            noticeService.addNotice(noticeVo);
            return Result.ok();
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 删除公告 */
    @DeleteMapping("deleteNotice/{id}")
    public Result<Void> deleteNotice(@PathVariable("id") Integer id) {
        try {
            noticeService.deleteNotice(id);
            return Result.ok();
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 更新公告 */
    @PutMapping("updateNotice")
    public Result<Void> updateNotice(@RequestBody NoticeVo noticeVo) {
        try {
            noticeService.updateNotice(noticeVo);
            return Result.ok();
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 查询全部公告 */
    @GetMapping("selectAll")
    public Result<List<NoticeVo>> selectAll() {
        try {
            List<NoticeVo> noticeVos = noticeService.selectAll();
            return Result.ok(noticeVos);
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 根据ID查询公告 */
    @GetMapping("select/{id}")
    public Result<NoticeVo> selectById(@PathVariable("id") Integer id) {
        return Result.ok(noticeService.selectById(id));
    }


    /* 展示最新一条公告, 根据创建时间与更新时间进行排序展示最新 */
    @GetMapping("select/new")
    public Result<NoticeVo> selectNew() {
        return Result.ok(noticeService.selectNew());
    }

}
