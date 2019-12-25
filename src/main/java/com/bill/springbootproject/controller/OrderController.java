package com.bill.springbootproject.controller;

import com.bill.springbootproject.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 * 微信支付文档
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_3
 */
@RestController
@RequestMapping("/user/api/order")
public class OrderController {


    @GetMapping("add")
    public JsonData saveOrder() {
        return JsonData.buildSuccess("下单成功");
    }


}
