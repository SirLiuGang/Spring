package com.cn.lg.test.callback;

/**
 * 计算方法执行时间（利用自定义的回调函数，都实现了execute方法，然后在testTime中进行调用execute方法，即可自动打印出执行时间）
 * 在这里调用传进来的方法中的函数，即为回调函数
 * @Auther: 刘钢
 * @Date: 2019/3/19 23:32
 * @Description:
 */
public class Tools {

    /**
     * 计算测试时间（callback函数通过接口传递进来）
     */
    public void testTime(MyCallBack myCallBack) {
        long begin = System.currentTimeMillis();    //测试起始时间
        myCallBack.execute(); ///进行回调操作
        long end = System.currentTimeMillis();      //测试结束时间
        System.out.println("[use time]:" + (end - begin)); //打印使用时间
    }


}
