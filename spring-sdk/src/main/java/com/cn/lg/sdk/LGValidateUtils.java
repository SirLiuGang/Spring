package com.cn.lg.sdk;

import com.cn.lg.sdk.validate.Validate;

/**
 * 校验工具类
 * @Auther: 刘钢
 * @Date: 2019/3/4 23:18
 * @Description:
 */
public class LGValidateUtils {

    private static LGValidateUtils lgValidateUtils = null;

    private Validate validate = new Validate();

    /**
     * 对Instance方法进行实例化，获取单例的LGInstanceUtils 并且供该类中其他方法调用，不允许其他类方法进行调用
     * @return  Instance
     */
    private static Validate getInstance() {
        if(lgValidateUtils == null) {
            lgValidateUtils = new LGValidateUtils();
        }
        return lgValidateUtils.validate;
    }

    /**
     * 字符串是否为空
     * @param str   String字符串
     * @return  boolean
     */
    public static boolean isEmptyStr(String str) {
        return getInstance().isEmptyStr(str);
    }
}
