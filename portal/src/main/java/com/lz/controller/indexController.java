package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.pojo.area.Area;
import com.lz.pojo.user.User;
import com.lz.service.area.AreaService;
import com.lz.service.car.CarService;
import com.lz.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class indexController {

    @Reference
    private UserService userService;

    @Reference
    private AreaService areaService;

    @Reference
    private CarService carService;

    @RequestMapping("/login")
    public String test(Model model,User user){

        return "index";
    }


    @GetMapping("/test2")
    public String index(Model model){
        List<User> userlist = userService.findAll();
        model.addAttribute("userlist",userlist);
        return "test";
    }

    @RequestMapping("/cars")
    public String cars(Model model){
        model.addAttribute("carslist","carslist");
        return "index";
    }
    @RequestMapping("/index")
    public String indexPage(Model model){
        model.addAttribute("carslist","carslist");
        return "index";
    }
    @RequestMapping("/area")
    public String areaTest(Model model){
        List<Area> areaList = areaService.findAll();
        model.addAttribute("areaList",areaList);
        return "area";
    }
}
