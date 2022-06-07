package com.example.javaweb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/23 19:51
 */
public class MysqlGenerator {

    private static final String DIR = System.getProperty("user.dir");

    public static void main(String[] args) {


        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://192.168.145.129/JavaWeb", "root", "root").build();

        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .fileOverride()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .outputDir(DIR + "/src/main/java")
                .build();

        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.example.javaweb")
                .build();

        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable().build();

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .entityBuilder()
                .enableLombok()
                .enableRemoveIsPrefix()
                .enableTableFieldAnnotation()
                .logicDeleteColumnName("deleted")
                .logicDeletePropertyName("deleteFlag")
                .idType(IdType.AUTO)
                .build();

        new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .template(templateConfig)
                .packageInfo(packageConfig)
                .strategy(strategyConfig)
                .execute();



    }
}
