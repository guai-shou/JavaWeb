package com.example.javaweb.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/29 21:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartVo {

    private Integer id;

    private String bookName;

    private String bookImg;

    private BigDecimal bookPrice;

    private Integer bookStock;

    private Integer bookSales;

    private String bookDescription;

    private Integer bookNumber;
}
