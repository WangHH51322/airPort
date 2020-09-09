package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.HrRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 19:53
 */
public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    void deleteByHrid(Integer hrid);

    Integer addRole(@Param("hrid") Integer hrid, @Param("rids") Integer[] rids);
}
