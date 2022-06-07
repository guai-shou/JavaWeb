package com.example.javaweb.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@TableName("indent")
@ToString
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("u_id")
    private Integer uid;

    @TableField("order_serial")
    private String orderSerial;

    @TableField("book_number")
    private String bookNumber;

    @TableField("book_list")
    private String bookList;

    @TableField("is_pay")
    private Integer isPay;

    @TableField("pay_time")
    private Date payTime;

}
