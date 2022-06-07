package com.example.javaweb.mapper;

import com.example.javaweb.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Update("update book set book_stock = #{updateNumber} where id = #{bid};")
    void updateStockById(@Param("bid") Integer bid,
                         @Param("updateNumber") Integer updateNumber);

}
