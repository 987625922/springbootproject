package com.bill.springbootproject.service;

import com.bill.springbootproject.domain.Video;

import java.util.List;

/**
 * 视频业务类接口
 */
public interface VideoService {

    List<Video> findAll();

    Video findById(long id);

    int update(Video video);

    int delect(long id);
}
