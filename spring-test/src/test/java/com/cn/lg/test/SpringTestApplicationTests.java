package com.cn.lg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestApplicationTests {

    @Test
    public void contextLoads() {
        // 本地再一次用来做测试
// 远程再一次用来做测试1
    }

    @Test
    public void test() {
        System.out.println("111");
    }
}
