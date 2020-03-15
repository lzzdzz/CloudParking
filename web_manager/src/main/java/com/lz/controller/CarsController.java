package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.pojo.cars.Cars;
import com.lz.service.cars.CarsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {
    @Reference
    private CarsService carsService;


    @RequestMapping("/findAll")
    public List<Cars> findAll(){
        return carsService.findAll();
    }
}
