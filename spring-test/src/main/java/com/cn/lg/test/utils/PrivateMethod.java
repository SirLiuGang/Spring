package com.cn.lg.test.utils;

/**
 * 包含私有方法，提供测试用例编写
 * @Auther: 刘钢
 * @Date: 2019/3/3 23:28
 * @Description:
 */
public class PrivateMethod {

    /**
     * 进行字符串的拼接
     */
    public String getStr(String str) {
        return "123" + appendABC(str);
    }

    /**
     * 私有方法，在字符串后边拼接字母  ABC
     */
    private String appendABC(String str) {
        return str + "ABC";
    }
}
