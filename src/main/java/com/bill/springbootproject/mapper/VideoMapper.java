package com.bill.springbootproject.mapper;

import com.bill.springbootproject.domain.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Video数据访问层
 */
public interface VideoMapper {

    @Select("SELECT * FROM video")
    List<Video> findAll();

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video findById(long id);

    @Update("UPDATE user SET title=#{title} WHERE id = #{id}")
    int update(Video video);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int delect(long id);
}
