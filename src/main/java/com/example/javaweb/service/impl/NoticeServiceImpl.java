package com.example.javaweb.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javaweb.entity.Admin;
import com.example.javaweb.entity.Notice;
import com.example.javaweb.entity.vo.NoticeVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.mapper.NoticeMapper;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IAdminService;
import com.example.javaweb.service.INoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 21:19
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private IAdminService adminService;

    /* 添加公告 */
    @Override
    public void addNotice(NoticeVo noticeVo) {
        Notice notice = getNotice(noticeVo);
        baseMapper.insert(notice);
    }

    /* 删除公告 */
    @Override
    public void deleteNotice(Integer id) {
        if (id == null) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        // 管理员 ID 判断权限
        Integer aid = Integer.parseInt((String) StpUtil.getLoginId());
        boolean b = StpUtil.hasPermission(aid, "BackstageAnnouncement");
        if (!b) {
            throw new JavaWebException(ResultEnum.ADMIN_NOT_PERMISSION);
        }
        baseMapper.deleteById(id);
    }

    @Override
    public void updateNotice(NoticeVo noticeVo) {
        Notice notice = getNotice(noticeVo);
        baseMapper.updateById(notice);
    }

    /* 查询最新一条公告 */
    @Override
    public NoticeVo selectNew() {
        List<NoticeVo> noticeList = baseMapper.selectAll();
        PriorityQueue<NoticeVo> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a.getUpdateTime().after(b.getUpdateTime())) return 1;
            else if (a.getUpdateTime().before(b.getUpdateTime())) return -1;
            else return 0;
        });
        priorityQueue.addAll(noticeList);

        if (priorityQueue.size() > 0) {
            return priorityQueue.peek();
        }
        return null;
    }

    @Override
    public List<NoticeVo> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    public NoticeVo selectById(Integer id) {
        return baseMapper.selectNoticeVoById(id);
    }


    // 参数校验、权限校验
    private Notice getNotice(NoticeVo noticeVo) {
        if (noticeVo == null || !StringUtils.hasLength(noticeVo.getTitle()) ||
                !StringUtils.hasLength(noticeVo.getDetails())) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        // 管理员 ID 判断权限
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        boolean b = StpUtil.hasPermission(id, "BackstageAnnouncement");
        if (!b) {
            throw new JavaWebException(ResultEnum.ADMIN_NOT_PERMISSION);
        }
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeVo, notice, Notice.class);
        notice.setUpdateTime(null);
        notice.setAid(id);
        return notice;
    }
}
