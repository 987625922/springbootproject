package com.bill.springbootproject.service.impl;

import com.bill.springbootproject.config.WeChatConfig;
import com.bill.springbootproject.domain.User;
import com.bill.springbootproject.mapper.UserMapper;
import com.bill.springbootproject.service.UserService;
import com.bill.springbootproject.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * 用户表操作的service
 */
@Service
public class UserServiceImpl implements UserService {

    //微信配置
    @Autowired
    private WeChatConfig weChatConfig;

    //用户数据表操作mapper
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户信息
     * 获取到openid，查看数据库用户是否注册，注册之后返回用户实体
     *
     * @param code 微信登录授权临时票据
     * @return 返回用户信息实体类
     */
    @Override
    public User saveWeChatUser(String code) {
        //通过code拼接获取access_token的链接
        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(),
                weChatConfig.getOpenAppid(), weChatConfig.getOpenAppsecret(), code);
        //获取access_token
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);

        if (baseMap == null || baseMap.isEmpty()) {
            return null;
        }

        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");
        //通过openId查看用户是否注册
        User dbUser = userMapper.findByopenid(openId);
        if (dbUser != null) { //更新用户，直接返回
            return dbUser;
        }

        //根据access_token，获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(), accessToken, openId);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);

        if (baseUserMap == null || baseUserMap.isEmpty()) {
            return null;
        }
        //获取用户信息并插入数据库
        String nickname = (String) baseUserMap.get("nickname");
        Double sexTemp = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||")
                .append(city);
        String finalAddress = sb.toString();
        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"),
                    "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"),
                    "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
        return user;
    }
}
