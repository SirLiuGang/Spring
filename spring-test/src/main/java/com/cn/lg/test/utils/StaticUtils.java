package com.cn.lg.test.utils;

/**
 * 此方法为工具类，提供静态方法
 * @author: 刘钢
 * @Date: 2019/3/2 23:50
 * @Description:
 */
public class StaticUtils {

    /**
     * 获取传入的数字
     */
    public static Integer getNum(Integer integer) {
        return integer;
    }

    /**
     * 判断传入的参数是否等于0
     * @param num 传入的参数
     * @return  true  入参 == 0
     *          false 入参 != 0
     */
    public boolean isZero(Integer num) {
        return StaticUtils.getNum(num) == 0;
    }
}
