package com.example.javaweb.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/25 14:45
 */
@Getter
@Setter
@TableName("notice")
public class Notice extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("a_id")
    private Integer aid;

    @TableField("title")
    private String title;

    @TableField("details")
    private String details;
}
