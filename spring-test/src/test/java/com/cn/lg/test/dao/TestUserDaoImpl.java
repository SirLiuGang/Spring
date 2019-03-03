package com.cn.lg.test.dao;

import com.cn.lg.test.dao.impl.UserDaoImpl;
import com.cn.lg.test.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 对构造方法进行模拟
 * @Auther: 刘钢
 * @Date: 2019/3/3 23:06
 * @Description:
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserDaoImpl.class)
public class TestUserDaoImpl {

    private static final Long USERID = 123L;

    @InjectMocks
    @Spy
    private UserDaoImpl userDao = new UserDaoImpl();

    @Before
    public void initMoxck() {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * 不mock返回结果
     */
    @Test
    public void getById_noMock() {
        // 重写了User的equals方法
        User user = new User(USERID, "张三", "男");
        User resultUser = userDao.getById(USERID);

        // 返回结果和期望的返回结果是相同的
        Assert.assertEquals(user, resultUser);
    }

    /**
     * mock返回结果
     */
    @Test
    public void getById_mock() {
        User user = new User(USERID, "张三", "男");

        User expectUser = new User(USERID, "李四", "男");
        try {
            // 当方法中new User 的时候，都会返回我们自定义的expectUser对象，而不是方法中内容
            PowerMockito.whenNew(User.class).withAnyArguments().thenReturn(expectUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User resultUser = userDao.getById(USERID);

        // 返回结果和修改前的期望结果是不同的
        Assert.assertNotEquals(user, resultUser);
    }
}
