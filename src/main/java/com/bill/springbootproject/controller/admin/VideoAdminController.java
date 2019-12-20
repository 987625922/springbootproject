package com.bill.springbootproject.controller.admin;

import com.bill.springbootproject.domain.Video;
import com.bill.springbootproject.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * viedo表数据库管理员操作的接口
 * 进行权限划分
 */
@RestController
@RequestMapping("/admin/api/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据id删除视频
     *
     * @param videoId 视频表的id
     * @return
     */
    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam(value = "video_id", required = true) Long videoId) {
        return videoService.delect(videoId);
    }

    /**
     * 根据id更新视频
     *
     * @param video 视频
     */
    @PutMapping("up_by_id")
    public Object update(@RequestBody Video video) {
        return videoService.update(video);
    }

    /**
     * 保存视频对象
     *
     * @param video 视频
     * @return
     */
    @PostMapping("save")
    public Object save(@RequestBody Video video) {
        return videoService.insert(video);
    }

}
