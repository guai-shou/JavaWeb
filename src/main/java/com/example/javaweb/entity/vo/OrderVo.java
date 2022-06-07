package com.example.javaweb.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.javaweb.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 9:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVo {

    private String orderSerial;

    private List<Integer> bookNumber;

    private List<Book> bookList;

    private Integer isPay;

    private Date payTime;
}
