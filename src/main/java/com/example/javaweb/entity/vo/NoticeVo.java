package com.example.javaweb.entity.vo;

import com.example.javaweb.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 21:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeVo {
    private Integer id;

    private String title;

    private String details;

    private Admin admin;

    private Date updateTime;
}
