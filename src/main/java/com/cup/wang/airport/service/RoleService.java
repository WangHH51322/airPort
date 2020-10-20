package com.cup.wang.airport.service;


import com.cup.wang.airport.mapper.RoleMapper;
import com.cup.wang.airport.model.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/9 21:41
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;
    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
