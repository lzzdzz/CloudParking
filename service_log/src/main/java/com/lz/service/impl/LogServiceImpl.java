package com.lz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lz.dao.LogMapper;
import com.lz.entity.PageResult;
import com.lz.pojo.log.Log;
import com.lz.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    @Override
    public List<Log> findAll() {
        return logMapper.selectAll();
    }

    @Override
    public PageResult<Log> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Log> pageresult = (Page<Log>) logMapper.selectAll();
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public List<Log> findList(Map<String, Object> searchMap) {
        Example example = creatExample(searchMap);
        return logMapper.selectByExample(example);
    }

    @Override
    public PageResult<Log> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = creatExample(searchMap);
        Page<Log> pageresult = (Page<Log>) logMapper.selectByExample(example);
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public Log findById(Integer id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Log log) {
        logMapper.insert(log);
    }

    @Override
    public void update(Log log) {
        logMapper.updateByPrimaryKeySelective(log);
    }

    @Override
    public void delete(Integer id) {
        logMapper.deleteByPrimaryKey(id);
    }

    public Example creatExample(Map<String, Object> searchMap){
        Example example = new Example(Log.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            if(searchMap.get("carnum")!=null&&!"".equals(searchMap.get("carnum"))){
                criteria.andLike("carnum","%"+(String)searchMap.get("carnum")+"%");
            }
        }
        return example;
    }
}
