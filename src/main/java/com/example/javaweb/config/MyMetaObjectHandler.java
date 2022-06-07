package com.example.javaweb.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/25 23:53
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insert");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("update....");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public <T, E extends T> MetaObjectHandler strictUpdateFill(MetaObject metaObject, String fieldName, Class<T> fieldType, E fieldVal) {
        //不判断空值情况即可
        //if (metaObject.getValue(fieldName) == null)
         {
            Object obj = fieldVal;
            if (Objects.nonNull(obj)) {
                metaObject.setValue(fieldName, obj);
            }
        }
        return this;
    }
}
