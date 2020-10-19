package com.lz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lz.dao.CarMapper;
import com.lz.entity.PageResult;
import com.lz.pojo.car.Car;
import com.lz.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> findAll() {
        return carMapper.selectAll();
    }

    @Override
    public PageResult<Car> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Car> pageresult = (Page<Car>) carMapper.selectAll();
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public List<Car> findList(Map<String, Object> searchMap) {
        Example example = creatExample(searchMap);
        return carMapper.selectByExample(example);
    }

    @Override
    public PageResult<Car> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = creatExample(searchMap);
        Page<Car> pageresult = (Page<Car>) carMapper.selectByExample(example);
        return new PageResult<>(pageresult.getTotal(),pageresult.getResult());
    }

    @Override
    public Car findById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Car car) {
        carMapper.insert(car);
    }

    @Override
    public void update(Car car) {
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public void delete(Integer id) {
        carMapper.deleteByPrimaryKey(id);
    }

    public Example creatExample(Map<String, Object> searchMap){
        Example example = new Example(Car.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            if(searchMap.get("carnum")!=null&&!"".equals(searchMap.get("carnum"))){
                criteria.andLike("carnum","%"+(String)searchMap.get("carnum")+"%");
            }
        }
        return example;
    }
}
