package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:42
 */
@Entity//(name = "temperature_unit")//@Entity注解表示下面的TemperatureUnit是一个实体类,这样才能被就怕识别并创建表(后面的name =
// "temperature_unit"表示jpa创建的表格名为temperature_unit,如果不加,则默认为实体类的名字)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LengthUnit implements Serializable {

    @Id  //jpa中通过@Id注解来指定主键,没有主键表也会创建失败
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String unit;

    @Override
    public String toString() {
        return "LengthUnit{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
