package com.bill.springbootproject.service;

import com.bill.springbootproject.domain.User;

/**
 * 用户业务接口类
 */
public interface UserService {


    User saveWeChatUser(String code);

}
