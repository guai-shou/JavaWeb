package com.example.javaweb.entity.vo;

import lombok.*;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/24 13:27
 * 用于接受前端返回的查询书籍对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVo {
    private String bookName;

    private Integer bookPriceBegin;

    private Integer bookPriceEnd;

    private Integer bookStock;

    private Integer bookSales;

    private String bookDescription;
}
