package com.lz.pojo.car;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_cars")
public class Car implements Serializable {

    @Id
    private Integer id;
    private String carhost;
    private String carnum;
    private String cartype;
    private Date starttime;
    private Date endtime;
    private Integer money;
    private String areaname;

    public Car(){

    }

    public Car(Integer id, String carhost, String carnum, String cartype, Date starttime, Date endtime, Integer money, String areaname) {
        this.id = id;
        this.carhost = carhost;
        this.carnum = carnum;
        this.cartype = cartype;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
        this.areaname = areaname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarhost() {
        return carhost;
    }

    public void setCarhost(String carhost) {
        this.carhost = carhost;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
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

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carhost='" + carhost + '\'' +
                ", carnum='" + carnum + '\'' +
                ", cartype='" + cartype + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", money=" + money +
                ", areaname='" + areaname + '\'' +
                '}';
    }
}
