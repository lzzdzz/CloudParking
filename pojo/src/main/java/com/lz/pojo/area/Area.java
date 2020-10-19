package com.lz.pojo.area;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_area")
public class Area implements Serializable {
    @Id
    private Integer id;
    private String carnum;
    private String areaname;
    private String areasize;

    public Area(Integer id, String carnum, String areaname, String areasize) {
        id = id;
        this.carnum = carnum;
        this.areaname = areaname;
        this.areasize = areasize;
    }

    public Area() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAreasize() {
        return areasize;
    }

    public void setAreasize(String areasize) {
        this.areasize = areasize;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", carnum='" + carnum + '\'' +
                ", areaname='" + areaname + '\'' +
                ", areasize='" + areasize + '\'' +
                '}';
    }
}
