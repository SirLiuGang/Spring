package com.cn.lg.annotation;

import org.springframework.transaction.annotation.Transactional;
/**
 * @Auther: 刘钢
 * @Date: 2019/3/2 20:55
 * @Description:
 */
@Transactional
public class TransactionalTest {

    /**
     * 方法上的注解会覆盖类上的注解
     */
    @Transactional(rollbackFor = Exception.class)
    public void save() {

    }
}
