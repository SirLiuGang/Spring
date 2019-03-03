package com.cn.lg.test.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 对静态方法进行mock
 * @Auther: 刘钢
 * @Date: 2019/3/3 0:13
 * @Description:
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StaticUtils.class)
public class TestStaticUtils {

    private static Logger LOG = LoggerFactory.getLogger(TestStaticUtils.class);

    private StaticUtils staticUtils = new StaticUtils();

    @Before
    public void initMoxck() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 未mock静态方法
     */
    @Test
    public void isZero_noMock() {
        boolean result = staticUtils.isZero(0);

        assertTrue(result);

        LOG.info("result = {}", result);    // result = true
    }

    /**
     * 使mock静态方法
     */
    @Test
    public void isZero_mock() {
        PowerMockito.mockStatic(StaticUtils.class);
        // 模拟静态方法，当入参为0的时候，输出1
        Mockito.when(StaticUtils.getNum(0)).thenReturn(1);
        boolean result = staticUtils.isZero(0);

        assertFalse(result);

        LOG.info("result = {}", result);    // result = false
    }

}
