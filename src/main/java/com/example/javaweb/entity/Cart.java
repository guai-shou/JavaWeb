package com.example.javaweb.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("cart")
@ToString
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("u_id")
    private Integer uid;

    @TableField("b_id")
    private Integer bid;

    @TableField("book_number")
    private Integer bookNumber;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
