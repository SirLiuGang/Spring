package com.cn.lg.test.utils;



import com.cn.lg.sdk.LGInstanceUtils;
import com.cn.lg.sdk.LGValidateUtils;

import java.util.List;

/**
 * @Auther: 刘钢
 * @Date: 2019/3/4 23:40
 * @Description:
 */
public class SpringSDKUtils {

    public static void main(String[] args) {
        newArrayList();
        isEmpty();
    }

    public static void newArrayList() {
        List<Integer> list = LGInstanceUtils.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.toString());
    }

    public static void isEmpty() {
        System.out.println(LGValidateUtils.isEmptyStr(null));
    }
}
