package com.bill.springbootproject.controller;

import com.bill.springbootproject.config.WeChatConfig;
import com.bill.springbootproject.domain.JsonData;
import com.bill.springbootproject.domain.User;
import com.bill.springbootproject.service.UserService;
import com.bill.springbootproject.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信登录controller
 * 微信扫码登录和登录成功后从微信服务器回调到我们接口的controller
 * 回调到code -》 get请求获取 -》 access_token -》 get请求 -》 获取到用户信息
 * https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html
 */
@Controller
@RequestMapping("/api/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    /**
     * 拼装微信扫一扫登录url
     * http://localhost:8080/api/wechat/login_url?access_page=www.xdclass.net
     *
     * @param accessPage 回调地址
     * @return
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //获取开放平台重定向地址

        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK"); //进行编码

        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(), weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }


    /**
     * 扫码登录回调，给微信服务器那边回调到这个接口
     *
     * @param code
     * @param state
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code", required = true) String code,
                                   String state, HttpServletResponse response) throws IOException {
        User user = userService.saveWeChatUser(code);
        if (user != null) {
            //生成jwt
            String token = JwtUtils.geneJsonWebToken(user);
            // state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转
            response.sendRedirect(state + "?token=" + token + "&head_img=" + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8"));
        }
    }


}
