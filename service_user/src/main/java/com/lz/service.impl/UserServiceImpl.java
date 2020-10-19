package com.lz.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lz.entity.PageResult;
import com.lz.pojo.user.User;
import com.lz.service.user.UserService;
import com.lz.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageResult<User> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<User> pageresult = (Page<User>) userMapper.selectAll();
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public List<User> findList(Map<String, Object> searchMap) {
        Example example = creatExample(searchMap);
        return userMapper.selectByExample(example);
    }

    @Override
    public PageResult<User> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = creatExample(searchMap);
        Page<User> pageresult = (Page<User>) userMapper.selectByExample(example);
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public Example creatExample(Map<String, Object> searchMap){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            if(searchMap.get("username")!=null&&!"".equals(searchMap.get("username"))){
                criteria.andLike("username","%"+(String)searchMap.get("username")+"%");
            }
        }
        return example;
    }
}
