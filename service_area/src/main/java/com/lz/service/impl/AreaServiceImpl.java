package com.lz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lz.dao.AreaMapper;
import com.lz.entity.PageResult;
import com.lz.pojo.area.Area;
import com.lz.service.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> findAll() {
        return areaMapper.selectAll();
    }

    @Override
    public PageResult<Area> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Area> pageresult = (Page<Area>) areaMapper.selectAll();
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public List<Area> findList(Map<String, Object> searchMap) {
        Example example = creatExample(searchMap);
        return areaMapper.selectByExample(example);
    }

    @Override
    public PageResult<Area> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = creatExample(searchMap);
        Page<Area> pageresult = (Page<Area>) areaMapper.selectByExample(example);
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public Area findById(Integer id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Area user) {

    }

    @Override
    public void update(Area user) {

    }

    @Override
    public void delete(Integer id) {
        areaMapper.deleteByPrimaryKey(id);
    }
    public Example creatExample(Map<String, Object> searchMap){
        Example example = new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            if(searchMap.get("areaname")!=null&&!"".equals(searchMap.get("areaname"))){
                criteria.andLike("areaname","%"+(String)searchMap.get("areaname")+"%");
            }
        }
        return example;
    }
}
