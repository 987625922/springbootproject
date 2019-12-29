package com.bill.springbootproject.service.impl;


import com.bill.springbootproject.config.WeChatConfig;
import com.bill.springbootproject.domain.User;
import com.bill.springbootproject.domain.Video;
import com.bill.springbootproject.domain.VideoOrder;
import com.bill.springbootproject.dto.VideoOrderDto;
import com.bill.springbootproject.mapper.UserMapper;
import com.bill.springbootproject.mapper.VideoMapper;
import com.bill.springbootproject.mapper.VideoOrderMapper;
import com.bill.springbootproject.service.VideoOrderService;
import com.bill.springbootproject.utils.CommonUtils;
import com.bill.springbootproject.utils.HttpUtils;
import com.bill.springbootproject.utils.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 视频订单操作service
 */
@Slf4j
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private WeChatConfig weChatConfig;

    //视频表的mapper
    @Autowired
    private VideoMapper videoMapper;

    //视频订单表的mapper
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    //用户表的mapper
    @Autowired
    private UserMapper userMapper;

    /**
     * 生成订单实体到插入视频订单表
     *
     * @param videoOrderDto
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String save(VideoOrderDto videoOrderDto) throws Exception {
        //查找视频信息
        Video video = videoMapper.findById(videoOrderDto.getVideoId());

        //查找用户信息
        User user = userMapper.findByid(videoOrderDto.getUserId());

        //生成订单的实体
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setVideoId(video.getId());
        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());
        videoOrder.setDel(0);
        videoOrder.setIp(videoOrderDto.getIp());
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());

        //插入视频订单实体
        videoOrderMapper.insert(videoOrder);

        //获取codeurl
        String codeUrl = unifiedOrder(videoOrder);

        return codeUrl;
    }


    /**
     * 统一下单方法（参数签名通过post方法调用微信统一下单接口，获取code_url，即二维码链接）
     * 微信统一下单接口文档：
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
     *
     * @return
     */
    private String unifiedOrder(VideoOrder videoOrder) throws Exception {

        //生成签名
        SortedMap<String, String> params = new TreeMap<>();
        params.put("appid", weChatConfig.getAppId());
        params.put("mch_id", weChatConfig.getMchId());
        params.put("nonce_str", CommonUtils.generateUUID());
        params.put("body", videoOrder.getVideoTitle());
        params.put("out_trade_no", videoOrder.getOutTradeNo());
        params.put("total_fee", String.valueOf(videoOrder.getTotalFee()));
        params.put("spbill_create_ip", videoOrder.getIp());
        params.put("notify_url", weChatConfig.getPayCallbackUrl());
        params.put("trade_type", "NATIVE");

        //sign签名
        String sign = WXPayUtil.createSign(params, weChatConfig.getKey());
        params.put("sign", sign);

        //map转xml
        String payXml = WXPayUtil.mapToXml(params);

        log.debug("微信统一下单接口，返回：" + payXml);
        //统一下单
        String orderStr = HttpUtils.doPost(WeChatConfig.getUnifiedOrderUrl(), payXml, 4000);
        if (null == orderStr) {
            return null;
        }

        Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        System.out.println(unifiedOrderMap.toString());
        if (unifiedOrderMap != null) {
            //返回微信返回的二维码链接
            return unifiedOrderMap.get("code_url");
        }

        return "";
    }

    /**
     * 根据流水号查找订单
     *
     * @param outTradeNo
     * @return
     */
    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {

        return videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    /**
     * 根据流水号更新订单
     *
     * @param videoOrder
     * @return
     */
    @Override
    public int updateVideoOderByOutTradeNo(VideoOrder videoOrder) {
        return videoOrderMapper.updateVideoOderByOutTradeNo(videoOrder);
    }


}
