package com.example.javaweb.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("user")
@ToString
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("uname")
    private String uname;
    @TableField("passwd")
    private String passwd;
    @TableField("phone")
    private String phone;
    @TableField("sex")
    private String sex;
    @TableField("nickname")
    private String nickname;
    @TableField("portrait")
    private String portrait;
    @TableField("cargo_name")
    private String cargoName;
    @TableField("cargo_phone")
    private String cargoPhone;
    @TableField("cargo_address")
    private String cargoAddress;
}
