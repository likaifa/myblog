package com.lkf.service;

import com.lkf.pojo.User;

/**
 * @author : lkf
 * @create : 2020-02-08 - 0:12
 * @describe:
 */
public interface UserService {

    User checkUser(String username, String password);
}
