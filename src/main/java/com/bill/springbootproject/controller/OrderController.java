package com.bill.springbootproject.controller;

import com.bill.springbootproject.dto.VideoOrderDto;
import com.bill.springbootproject.service.VideoOrderService;
import com.bill.springbootproject.utils.IpUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 下单接口
 * 微信支付文档
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_3
 */
@RestController
@RequestMapping("/user/api/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 添加视频购买订单并返回微信二维码
     *
     * @param videoId  视频id
     * @param response
     * @return
     * @throws Exception =====
     *                   微信支付说明文档
     *                   https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_1
     */
    @GetMapping("buy")
    public void saveOrder(@RequestParam(value = "video_id", required = true) int videoId, HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request);
        int userId = (Integer) request.getAttribute("user_id");
//        int userId = 1;
//        String ip = "120.25.1.43";

        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);

        String codeUrl = videoOrderService.save(videoOrderDto);
        if (codeUrl == null) {
            throw new NullPointerException();
        }

        try {
            //生成二维码配置
            Map<EncodeHintType, Object> hints = new HashMap<>();

            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400, hints);
            OutputStream out = response.getOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
