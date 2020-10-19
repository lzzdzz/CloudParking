package com.lz.pojo.log;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_log")
public class Log implements Serializable {

    @Id
    private Integer id;
    private String carnum;
    private String areaname;
    private Date starttime;
    private Date endtime;
    private Integer money;

    public Log() {
    }

    public Log(Integer id, String carnum, String areaname, Date starttime, Date endtime, Integer money) {
        this.id = id;
        this.carnum = carnum;
        this.areaname = areaname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", carnum='" + carnum + '\'' +
                ", areaname='" + areaname + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", money=" + money +
                '}';
    }
}
