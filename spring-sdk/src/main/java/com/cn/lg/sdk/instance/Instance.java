package com.cn.lg.sdk.instance;

import java.util.*;

/**
 * 对List和Map进行初始化
 * @Auther: 刘钢
 * @Date: 2019/3/4 23:17
 * @Description:
 */
public class Instance {

    /**
     * 初始化ArrayList
     */
    public <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * 初始化LinkedList
     */
    public <T> LinkedList<T> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * 初始化HashMap
     */
    public <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * 初始化LinkedHashMap
     */
    public <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }
}
