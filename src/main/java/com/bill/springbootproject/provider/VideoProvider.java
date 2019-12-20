package com.bill.springbootproject.provider;

import com.bill.springbootproject.domain.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * video的动态sql语句
 * mybatis Provider
 */
public class VideoProvider {

    /**
     * 动态sql更新Video表
     *
     * 根据传入的值只传有值的参数进入sql
     */
    public String updateVideo(Video video) {
        return new SQL() {
            {
                UPDATE("video");
                //条件写法
                if (video.getTitle() != null) {
                    SET("title=#{title}");
                }
                if (video.getSummary() != null) {
                    SET("summary=#{summary}");
                }
                if (video.getCoverImg() != null) {
                    SET("cover_img=#{coverImg}");
                }
                if (video.getViewNum() != null) {
                    SET("view_num=#{viewNum}");
                }
                if (video.getPrice() != null) {
                    SET("price=#{price}");
                }
                if (video.getCreateTime() != null) {
                    SET("createTime=#{create_time}");
                }
                if (video.getOnline() != null) {
                    SET("online=#{online}");
                }
                if (video.getPoint() != null) {
                    SET("point=#{point}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }
}
