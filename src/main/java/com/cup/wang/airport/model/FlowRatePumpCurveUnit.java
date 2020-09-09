package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:17
 */
@Entity(name = "flowratepumpcurve_unit")//@Entity注解表示下面的TemperatureUnit是一个实体类,这样才能被就怕识别并创建表(后面的name =
// "temperature_unit"表示jpa创建的表格名为temperature_unit,如果不加,则默认为实体类的名字)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowRatePumpCurveUnit {

    @Id  //jpa中通过@Id注解来指定主键,没有主键表也会创建失败
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //@GeneratedValue表示自增长,
    // strategy = GenerationType.IDENTITY表示自增长的格式
    //可以通过@Column注解,对Book表中的属性进行设置,如字段长度,字段是否可插入,可更新等等,
    private Integer id;//在jpa中每个表必须有id,不然表会创建失败
    private String unit;
}
