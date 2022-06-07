package com.example.javaweb.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/31 17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminVo {
    private Integer id;

    private String name;

    private String passwd;

    private List<String> permissionList;
}
