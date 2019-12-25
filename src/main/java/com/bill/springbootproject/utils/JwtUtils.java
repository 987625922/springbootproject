package com.bill.springbootproject.utils;

import com.bill.springbootproject.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * jwt的token生成和校验工具
 * 用于给客户标识身份，需要前端用代码自己带上（存在header或者param中），
 * 不同于cookie和session存在于浏览器中
 */
public class JwtUtils {
    //发起人
    public static final String SUBJECT = "SUBJECT";
    //设置用户加密的有效时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;
    //秘钥
    public static final String APPSECRET = "bill_secret";

    /**
     * 生成加密的jwt
     *
     * @param user 用户
     * @return
     */
    public static String geneJsonWebToken(User user) {
        if (user == null || user.getId() == null || user.getName() == null
                || user.getHeadImg() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId()).claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date()) //设置发送时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //设置有效时间
                .signWith(SignatureAlgorithm.HS256, APPSECRET) //设置加密规则和秘钥
                .compact();
        return token;
    }

    /**
     * 校验token
     *
     * @param token 加密的字符串
     * @return 错误和加密过期都会返回 NULL
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser() //解密
                    .setSigningKey(APPSECRET) //设置秘钥
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

}
