package com.lz.service.area;

import com.lz.entity.PageResult;
import com.lz.pojo.area.Area;

import java.util.List;
import java.util.Map;

public interface AreaService {
    public List<Area> findAll();
    public PageResult<Area> findPage(int page, int size);
    public List<Area> findList(Map<String,Object> searchMap);
    public PageResult<Area> findPage(Map<String,Object> searchMap,int page,int size);
    public Area findById(Integer id);
    public void add(Area user);
    public void update(Area user);
    public void delete(Integer id);

}
