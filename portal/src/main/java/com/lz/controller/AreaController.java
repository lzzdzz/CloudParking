package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.entity.PageResult;
import com.lz.entity.Result;
import com.lz.pojo.area.Area;
import com.lz.service.area.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Reference
    private AreaService areaService;

    @RequestMapping(value = "/findAll")
    public List<Area> findAll(){
        return areaService.findAll();
    }


    @GetMapping("/findPage")
    public PageResult<Area> findPage(int page, int size){
        return areaService.findPage(page,size);
    }

    @GetMapping("/findById")
    public Area findById(Integer id){
        return areaService.findById(id);
    }

    @PostMapping("/findList")
    public List<Area> findList(@RequestBody Map seachmap){
        return areaService.findList(seachmap);
    }

    @PostMapping("/findResultPage")
    @ResponseBody
    public PageResult<Area> findPage(@RequestBody Map seachmap,int page,int size){
        return areaService.findPage(seachmap,page,size);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Area area){
        areaService.add(area);
        return new Result();
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Area area){
        areaService.update(area);
        return new Result();
    }
    @GetMapping("/delete")
    public Result delete(Integer id){
        areaService.delete(id);
        return new Result();
    }
}
