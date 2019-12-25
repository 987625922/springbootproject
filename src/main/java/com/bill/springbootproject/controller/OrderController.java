package com.bill.springbootproject.controller;

import com.bill.springbootproject.domain.JsonData;
import com.bill.springbootproject.dto.VideoOrderDto;
import com.bill.springbootproject.service.VideoOrderService;
import com.bill.springbootproject.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单接口
 * 微信支付文档
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_3
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    @GetMapping("add")
    public JsonData saveOrder(@RequestParam(value = "video_id", required = true) int videoId,
                              HttpServletRequest request) throws Exception {
        String ip = "120.25.1.43";
//        String ip = IpUtils.getIpAddr(request);
        //int userId = request.getAttribute("user_id");
        int userId = 1;
        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);

        String codeUrl = videoOrderService.save(videoOrderDto);
        //生成二维码

        return JsonData.buildSuccess("下单成功");
    }


}
