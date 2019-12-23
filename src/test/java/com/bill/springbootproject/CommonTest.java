package com.bill.springbootproject;

import com.bill.springbootproject.domain.User;
import com.bill.springbootproject.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

public class CommonTest {


    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(99);
        user.setHeadImg("www.baidu.com");
        user.setName("user");
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    public void testCheck(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTVUJKRUNUIiwiaWQiOjk5LCJuYW1lIjoidXNlciIsImltZyI6Ind3dy5iYWlkdS5jb20iLCJpYXQiOjE1NzY5ODU5OTMsImV4cCI6MTU3NzU5MDc5M30.gPthdGkxD1BRcWCjwFwA511SSP5wW7vWtI0QTh5-G9A";
        Claims claims = JwtUtils.checkJWT(token);
        if (claims != null){
            String name = (String)claims.get("name");
            int id  = (Integer)claims.get("id");
            String img = (String)claims.get("img");
            System.out.println("name:"+name+",id:"+id+",img:"+img);
        }else {
            System.out.println("密文不对");
        }
    }
}
