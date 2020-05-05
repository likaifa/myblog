package com.lkf.service;

import com.lkf.dao.UserRepository;
import com.lkf.pojo.User;
import com.lkf.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : lkf
 * @create : 2020-02-08 - 0:14
 * @describe:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
