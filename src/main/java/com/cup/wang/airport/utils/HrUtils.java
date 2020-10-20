package com.cup.wang.airport.utils;

import com.cup.wang.airport.model.utils.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 19:56
 */
public class HrUtils {
    public static Hr getCurrentHr(){
        //获取当前登录的用户对象
        return (Hr) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
