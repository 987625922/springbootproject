package com.bill.springbootproject.controller;

import com.bill.springbootproject.config.WeChatConfig;
import com.bill.springbootproject.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用controller
 *
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private WeChatConfig weChatConfig;

    @RequestMapping("test1")
    public String test1() {
        return weChatConfig.getAppId() + "  " + weChatConfig.getAppsecret();
    }

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("test2")
    public Object test2() {
        return videoMapper.findAll();
    }

    @RequestMapping("test3")
    public Object test3() {
        return videoMapper.findById(1);
    }

}
