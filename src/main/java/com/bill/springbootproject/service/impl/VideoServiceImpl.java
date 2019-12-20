package com.bill.springbootproject.service.impl;

import com.bill.springbootproject.domain.Video;
import com.bill.springbootproject.mapper.VideoMapper;
import com.bill.springbootproject.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(long id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delect(long id) {
        return videoMapper.delect(id);
    }
}
