package com.cup.wang.airport.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/22 18:12
 */
@Entity//(name = "temperature_unit")//@Entity注解表示下面的TemperatureUnit是一个实体类,这样才能被就怕识别并创建表(后面的name =
// "temperature_unit"表示jpa创建的表格名为temperature_unit,如果不加,则默认为实体类的名字)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureUnit implements Serializable {


    @Id  //jpa中通过@Id注解来指定主键,没有主键表也会创建失败
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //@GeneratedValue表示自增长,
                                                        // strategy = GenerationType.IDENTITY表示自增长的格式
                                                        //可以通过@Column注解,对Book表中的属性进行设置,如字段长度,字段是否可插入,可更新等等,
    private Integer id;//在jpa中每个表必须有id,不然表会创建失败
    private String unit;

//    @Override
//    public String toString() {
//        return "TemperatureUnit{" +
//                "id=" + id +
//                ", unit='" + unit + '\'' +
//                '}';
//    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUnit() {
//        return unit;
//    }
//
//    public void setUnit(String unit) {
//        this.unit = unit;
//    }
}
