package com.bill.springbootproject.mapper;

import com.bill.springbootproject.domain.Video;
import com.bill.springbootproject.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Video数据访问层
 */
public interface VideoMapper {

    @Select("SELECT * FROM video")
    List<Video> findAll();

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video findById(long id);

    /**
     * 动态sql更新Video表
     */
    //    @Update("UPDATE video SET title=#{title} WHERE id = #{id}")
    @UpdateProvider(type = VideoProvider.class,method = "updateVideo")
    int update(Video video);

    @Delete("DELETE FROM video WHERE id=#{id}")
    int delect(long id);

    /**
     * useGeneratedKeys表示主键id为自增
     * keyProperty bean里面的id
     * keyColumn 数据库id
     * @param video
     * @return
     */
    @Insert("INSERT INTO video(title,summary,cover_img,view_num,price,create_time,online,point)" +
            "VALUES (#{title},#{summary},#{coverImg},#{viewNum},#{price},#{createTime},#{online},#{point})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save(Video video);
}
