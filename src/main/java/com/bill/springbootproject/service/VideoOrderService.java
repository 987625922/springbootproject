package com.bill.springbootproject.service;

import com.bill.springbootproject.domain.VideoOrder;
import com.bill.springbootproject.dto.VideoOrderDto;

/**
 * 订单接口
 */
public interface VideoOrderService {

    /**
     * 下单接口
     *
     * @param videoOrderDto
     * @return
     */
    VideoOrder save(VideoOrderDto videoOrderDto) throws Exception;

}
