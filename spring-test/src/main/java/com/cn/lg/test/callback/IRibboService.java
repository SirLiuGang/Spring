package com.cn.lg.test.callback;

import rx.Observable;

import java.util.concurrent.Future;

/**
 * @author: 刘钢
 * @Date: 2019/6/3 23:31
 * @Description:
 */
public interface IRibboService {

    /**
     * 同步调用hello
     * @return
     */
    String hello();

    /**
     * 异步调用hello
     * @return
     */
    Future<String> syncHello();

    /**
     * 异步回调hello
     * @return
     */
    Observable<String> callHello();

    /**
     * 无法访问服务
     * @return
     */
    String noHello();

}
