package com.cn.lg.sdk;

import com.cn.lg.sdk.instance.Instance;

import java.util.List;
import java.util.Map;

/**
 * 实例化工具类
 * @author: 刘钢
 * @Date: 2019/3/4 23:17
 * @Description:
 */
public class LGInstanceUtils {
    private static LGInstanceUtils lgInstanceUtils = null;

    private Instance instance = new Instance();

    /**
     * 对Instance方法进行实例化，获取单例的LGInstanceUtils 并且供该类中其他方法调用，不允许其他类方法进行调用
     * @return  Instance
     */
    private static Instance getInstance() {
        if(lgInstanceUtils == null) {
             lgInstanceUtils = new LGInstanceUtils();
        }
        return lgInstanceUtils.instance;
    }

    public static <T> List<T> newArrayList() {
        return getInstance().newArrayList();
    }

    public static <T> List<T> newLinkedList() {
        return getInstance().newLinkedList();
    }

    public static <K, V> Map<K, V> newHashMap() {
        return getInstance().newHashMap();
    }

    public static <K, V> Map<K, V> newLinkedHashMap() {
        return getInstance().newLinkedHashMap();
    }


}
