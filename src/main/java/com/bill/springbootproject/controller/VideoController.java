package com.bill.springbootproject.controller;

import com.bill.springbootproject.domain.Video;
import com.bill.springbootproject.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("page")
    public Object pageVideo() {
        return videoService.findAll();
    }

    @GetMapping("find_by_id")
    public Object findById(long id) {
        return videoService.findById(id);
    }

    @DeleteMapping("del_by_id")
    public Object delById(long id) {
        return videoService.delect(id);
    }

    @PutMapping("up_by_id")
    public Object update(long videoId, String title) {
        Video video = new Video();
        video.setId(videoId);
        video.setTitle(title);
        return videoService.update(video);
    }

}
