package com.example.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javaweb.entity.Notice;
import com.example.javaweb.entity.vo.NoticeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 21:18
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    List<NoticeVo> selectAll();

    NoticeVo selectNoticeVoById(@Param("id") Integer id);
}
