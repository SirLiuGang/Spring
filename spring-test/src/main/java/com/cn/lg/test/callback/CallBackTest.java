package com.cn.lg.test.callback;


/**
 * 对回调函数的接口进行测试
 * @Auther: 刘钢
 * @Date: 2019/3/19 23:35
 * @Description:
 */
public class CallBackTest {

    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.testTime(() -> {
            try {
                // 这里可以添加对应的需要计算的方法
                CallBackTest test = new CallBackTest();
                test.sayHello();
                test.sayNo();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void sayHello() {
        System.out.println("say hello");
    }

    public void sayNo() {
        System.out.println("say no");
    }
}
