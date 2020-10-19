package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.entity.PageResult;
import com.lz.entity.Result;
import com.lz.pojo.user.User;
import com.lz.service.user.UserService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Reference
    private UserService userService;

    @RequestMapping(value = "/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }


    @GetMapping("/findPage")
    public PageResult<User> findPage(int page, int size){
        return userService.findPage(page,size);
    }

    @GetMapping("/findById")
    public User findById(Integer id){
        return userService.findById(id);
    }

    @PostMapping("/findList")
    public List<User> findList(@RequestBody Map seachmap){
        return userService.findList(seachmap);
    }


    @PostMapping("/findResultPage")
    public PageResult<User> findPage(@RequestBody Map seachmap,int page,int size){
        return userService.findPage(seachmap,page,size);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return new Result();
    }
    @RequestMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result();
    }
    @GetMapping("/delete")
    public Result delete(Integer id){
        userService.delete(id);
        return new Result();
    }
}
