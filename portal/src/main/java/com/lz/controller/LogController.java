package com.lz.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lz.entity.PageResult;
import com.lz.entity.Result;
import com.lz.pojo.log.Log;
import com.lz.service.log.LogService;
import org.springframework.web.bind.annotation.*;
import util.LogExcle;
import util.Manager;
import util.Message;
import util.UUIDUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController {

    @Reference
    private LogService logService;

    @RequestMapping(value = "/findAll")
    public List<Log> findAll(){
        return logService.findAll();
    }


    @GetMapping("/findPage")
    public PageResult<Log> findPage(int page, int size){
        return logService.findPage(page,size);
    }

    @GetMapping("/findById")
    public Log findById(Integer id){
        return logService.findById(id);
    }

    @PostMapping("/findList")
    public List<Log> findList(@RequestBody Map seachmap){
        return logService.findList(seachmap);
    }

    @PostMapping("/findResultPage")
    public PageResult<Log> findPage(@RequestBody Map seachmap,int page,int size){
        return logService.findPage(seachmap,page,size);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Log log){
        logService.add(log);
        return new Result();
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Log log){
        logService.update(log);
        return new Result();
    }
    @GetMapping("/delete")
    public Result delete(Integer id){
        logService.delete(id);
        return new Result();
    }

    @RequestMapping("/excle")
    public Message buildExcle(Integer id){
        Log log = logService.findById(id);
        File file = null;
        try {
            String fileName = UUIDUtil.getUUID() + ".xls";
            String path = "E:\\data" + fileName;
            String uploadPath = "resources/admin/excel/" + fileName;
            LogExcle.buildExcel(path,log);
            file = new File(path);
            if (file.exists()) {
                if (Manager.upload(uploadPath, file)) {
                    Message message = Message.success("获取成功");
                    message.setData("http://oss-cn-beijing.aliyuncs.com/excel/" + fileName);
                    return message;
                } else {
                    return Message.error("上传失败");
                }
            } else {
                return Message.error("文件不存在");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Message.error("系统错误");
        }
    }
}
