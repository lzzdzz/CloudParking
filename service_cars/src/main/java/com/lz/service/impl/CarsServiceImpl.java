package com.lz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lz.dao.CarsMapper;
import com.lz.pojo.cars.Cars;
import com.lz.service.cars.CarsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    private CarsMapper carsMapper;

    @Override
    public List<Cars> findAll() {
        return carsMapper.selectAll();
    }
}
