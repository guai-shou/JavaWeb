package com.example.javaweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javaweb.entity.Notice;
import com.example.javaweb.entity.vo.NoticeVo;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 21:19
 */
public interface INoticeService extends IService<Notice> {
    void addNotice(NoticeVo noticeVo);

    void deleteNotice(Integer id);

    void updateNotice(NoticeVo noticeVo);

    NoticeVo selectNew();

    List<NoticeVo> selectAll();

    NoticeVo selectById(Integer id);
}
