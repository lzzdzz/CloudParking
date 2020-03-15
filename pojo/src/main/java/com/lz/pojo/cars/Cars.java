package com.lz.pojo.cars;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_cars")
public class Cars implements Serializable {
    private Integer id;
    private String carID;
    private String carBrand;
    private String carType;
    private String carHost;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarHost() {
        return carHost;
    }

    public void setCarHost(String carHost) {
        this.carHost = carHost;
    }
}
