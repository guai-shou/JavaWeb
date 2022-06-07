package com.example.javaweb.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/25 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoVo {
    private String cargoName;

    private String cargoPhone;

    private String cargoAddress;
}
