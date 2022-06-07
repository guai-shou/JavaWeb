package com.example.javaweb.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/25 14:47
 */
@Data
public class BaseEntity {
    @TableField("deleted")
    @TableLogic
    @ExcelIgnore
    private Integer deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ExcelIgnore
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private Date updateTime;
}
