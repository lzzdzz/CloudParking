package com.lz.service.car;

import com.lz.entity.PageResult;
import com.lz.pojo.car.Car;

import java.util.List;
import java.util.Map;

public interface CarService {
    public List<Car> findAll();
    public PageResult<Car> findPage(int page, int size);
    public List<Car> findList(Map<String,Object> searchMap);
    public PageResult<Car> findPage(Map<String,Object> searchMap,int page,int size);
    public Car findById(Integer id);
    public void add(Car Car);
    public void update(Car Car);
    public void delete(Integer id);
}
