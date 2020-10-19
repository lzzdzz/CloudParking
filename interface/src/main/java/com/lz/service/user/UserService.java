package com.lz.service.user;



import com.lz.entity.PageResult;
import com.lz.pojo.user.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();
    public PageResult<User> findPage(int page,int size);
    public List<User> findList(Map<String,Object> searchMap);
    public PageResult<User> findPage(Map<String,Object> searchMap,int page,int size);
    public User findById(Integer id);
    public void add(User user);
    public void update(User user);
    public void delete(Integer id);
}
