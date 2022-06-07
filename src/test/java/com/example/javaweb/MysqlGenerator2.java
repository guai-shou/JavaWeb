package com.example.javaweb;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Collections;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/23 19:51
 */
public class MysqlGenerator2 {

    private static final String DIR = System.getProperty("user.dir");

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://192.168.145.129/JavaWeb","root","root")
                .globalConfig(builder -> {
                    builder.fileOverride()
                            // 设置输出目录
                            .outputDir(DIR + "/src/main/java/");
                })
                .packageConfig(builder -> {
                    // 设置父类包名
                    builder.parent("com.example.javaweb")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, DIR + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()
                            .enableSkipView()
                            .entityBuilder()
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            // 数据库逻辑删除字段
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            // ID类型为自动
                            .idType(IdType.AUTO)
                            // 两种填充写法
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE));

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker引擎模板，默认是Velocity
                .execute();

    }
}
