package com.lz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lz.dao.UserMapper;
import com.lz.pojo.user.User;
import com.lz.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
