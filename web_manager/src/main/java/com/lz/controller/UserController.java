package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.pojo.user.User;
import com.lz.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        System.out.println(userService.findAll().toString());
        List list = userService.findAll();
        System.out.println(list.toString());
        return userService.findAll();
    }
}
