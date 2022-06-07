package com.example.javaweb.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class Book extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty("编号")
    private Integer id;
    @TableField("book_name")
    @ExcelProperty("书名")
    private String bookName;
    @TableField("book_img")
    @ExcelProperty("书籍图片")
    private String bookImg;
    @TableField("book_price")
    @ExcelProperty("价格")
    private BigDecimal bookPrice;
    @TableField("book_stock")
    @ExcelProperty("库存")
    private Integer bookStock;
    @TableField("book_sales")
    @ExcelProperty("销量")
    private Integer bookSales;
    @TableField("book_description")
    @ExcelProperty("书籍描述")
    private String bookDescription;
}
