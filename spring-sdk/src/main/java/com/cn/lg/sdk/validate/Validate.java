package com.cn.lg.sdk.validate;


import org.apache.commons.lang3.StringUtils;

/**
 * 校验类
 * @Auther: 刘钢
 * @Date: 2019/3/4 23:09
 * @Description:
 */
public class Validate {

    /**
     * 字符串是否为空
     * @param str   String字符串
     * @return  boolean
     */
    public boolean isEmptyStr(String str) {
        return StringUtils.isEmpty(str);
    }

}
