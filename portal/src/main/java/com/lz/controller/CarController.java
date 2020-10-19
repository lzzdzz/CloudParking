package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.entity.PageResult;
import com.lz.entity.Result;
import com.lz.pojo.area.Area;
import com.lz.pojo.car.Car;
import com.lz.service.area.AreaService;
import com.lz.service.car.CarService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {

    @Reference
    private CarService carService;

    @Reference
    private AreaService areaService;

    @RequestMapping("/login")
    public String test(Model model){

        return "index";
    }

    @RequestMapping(value = "/findAll")
    public List<Car> findAll(){
        return carService.findAll();
    }


    @GetMapping("/findPage")
    public PageResult<Car> findPage(int page, int size){
        return carService.findPage(page,size);
    }

    @GetMapping("/findById")
    public Car findById(Integer id){
        return carService.findById(id);
    }

    @PostMapping("/findList")
    public List<Car> findList(@RequestBody Map seachmap){
        return carService.findList(seachmap);
    }


    @PostMapping("/findResultPage")
    public PageResult<Car> findPage(@RequestBody Map seachmap,int page,int size){
        return carService.findPage(seachmap,page,size);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Car Car){
        Car.setMoney(0);

        carService.add(Car);
        Area area = new Area();
        area.setAreaname(Car.getAreaname());
        area.setCarnum(Car.getCarnum());

        areaService.add(area);
        return new Result();
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Car Car){
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String fromDate = simpleFormat.format(Car.getStarttime());
            String toDate = simpleFormat.format(Car.getEndtime());
            long from = 0;
            from = simpleFormat.parse(fromDate).getTime();
            long to = simpleFormat.parse(toDate).getTime();
            int hours = (int) ((to - from)/(1000 * 60 * 60));
            Car.setMoney(hours*10+5);
            carService.update(Car);
            return new Result();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Result();
    }
    @GetMapping("/delete")
    public Result delete(Integer id){
        carService.delete(id);
        return new Result();
    }
}
