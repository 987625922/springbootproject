package com.bill.springbootproject.controller;

import com.bill.springbootproject.config.WeChatConfig;
import com.bill.springbootproject.domain.JsonData;
import com.bill.springbootproject.domain.User;
import com.bill.springbootproject.service.UserService;
import com.bill.springbootproject.utils.JwtUtils;
import com.bill.springbootproject.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;

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
     * @param accessPage 登录成功后返回的地址
     * @return
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true)
                                     String accessPage) throws UnsupportedEncodingException {
        //获取开放平台重定向地址（即扫码成功后回调地址）
        String redirectUrl = weChatConfig.getOpenRedirectUrl();
        //进行编码
        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK");
        //对微信统一登录接口进行拼接
        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),
                weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }


    /**
     * 扫码登录回调，给微信服务器那边回调到这个接口
     *
     * @param code     授权临时票据，用于获取access_token
     * @param state    返回状态，一般用于存储用户登录的界面，用于登录完成后返回当前界面
     * @param response =======
     *                 微信登录回调返回说明
     *                 <p>
     *                 用户允许授权后，将会重定向到redirect_uri的网址上，并且带上code和state参数
     *                 <p>
     *                 redirect_uri?code=CODE&state=STATE
     *                 若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数
     *                 <p>
     *                 redirect_uri?state=STATE
     *                 ===========
     *                 第二步：通过code获取access_token
     *                 <p>
     *                 通过code获取access_token和openid
     *                 <p>
     *                 https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=aut
     *                 <p>
     *                 根据access_token，获取用户基本信息
     *                 请求方法
     *                 https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code", required = true) String code,
                                   String state, HttpServletResponse response) throws IOException {
        User user = userService.saveWeChatUser(code);
        if (user != null) {
            //生成jwt
            String token = JwtUtils.geneJsonWebToken(user);
            // state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转
            response.sendRedirect(state + "?token=" + token + "&head_img="
                    + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8"));
        }
    }


    /**
     * 微信支付回调
     * 微信服务器支付完成之后的回调接口
     */
    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        InputStream inputStream = request.getInputStream();

        //BufferedReader是包装设计模式，性能更搞
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream,
                "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        inputStream.close();
        Map<String, String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
        System.out.println(callbackMap.toString());

        SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

        //判断签名是否正确
        if (WXPayUtil.isCorrectSign(sortedMap, weChatConfig.getKey())) {

            if ("SUCCESS".equals(sortedMap.get("result_code"))) {

                String outTradeNo = sortedMap.get("out_trade_no");

//                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);

//                if(dbVideoOrder != null && dbVideoOrder.getState()==0){  //判断逻辑看业务场景
//                    VideoOrder videoOrder = new VideoOrder();
//                    videoOrder.setOpenid(sortedMap.get("openid"));
//                    videoOrder.setOutTradeNo(outTradeNo);
//                    videoOrder.setNotifyTime(new Date());
//                    videoOrder.setState(1);
//                    int rows = videoOrderService.updateVideoOderByOutTradeNo(videoOrder);
//                    if(rows == 1){ //通知微信订单处理成功
//                        response.setContentType("text/xml");
//                        response.getWriter().println("success");
//                        return;
//                    }
//                }
            }
        }
        //都处理失败
        response.setContentType("text/xml");
        response.getWriter().println("fail");

    }


}
