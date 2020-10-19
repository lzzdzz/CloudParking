package com.lz.service.log;

import com.lz.entity.PageResult;
import com.lz.pojo.log.Log;

import java.util.List;
import java.util.Map;

public interface LogService {
    public List<Log> findAll();
    public PageResult<Log> findPage(int page, int size);
    public List<Log> findList(Map<String,Object> searchMap);
    public PageResult<Log> findPage(Map<String,Object> searchMap,int page,int size);
    public Log findById(Integer id);
    public void add(Log Log);
    public void update(Log Log);
    public void delete(Integer id);
}
