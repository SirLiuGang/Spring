package com.cn.lg.test.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;

/**
 * 对私有方法进行测试
 * @Auther: 刘钢
 * @Date: 2019/3/3 23:32
 * @Description:
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateMethod.class)
public class TestPrivateMethod {

    @InjectMocks
    @Spy
    private PrivateMethod privateMethod = new PrivateMethod();

    /**
     * 未mock私有方法
     */
    @Test
    public void getStr_noMock() {
        String exceptStr = "123abcABC";

        String resultStr = privateMethod.getStr("abc");

        Assert.assertEquals(exceptStr, resultStr);
    }

    /**
     * 对私有方法进行mock
     */
    @Test
    public void getStr_mock() {
        String exceptStr = "123abcABC";

        try {
            // 第一个参数：需要mock的类，第二个参数：方法的名称，第三个参数：调用方法入参格式
            PowerMockito.when(privateMethod, "appendABC", any()).thenReturn("___");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultStr = privateMethod.getStr("abc");

        Assert.assertNotEquals(exceptStr, resultStr);
    }
}
