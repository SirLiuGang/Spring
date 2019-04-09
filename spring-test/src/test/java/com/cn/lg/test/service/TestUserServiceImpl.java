package com.cn.lg.test.service;

import com.cn.lg.test.dao.IUserDao;
import com.cn.lg.test.entity.User;
import com.cn.lg.test.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * 对Service层进行测试
 * @Auther: 刘钢
 * @Date: 2019/3/3 21:27
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserServiceImpl {

    private static final Long USERID = 123L;

    @InjectMocks
    @Spy
    private UserServiceImpl userService = new UserServiceImpl();

    @Mock
    private IUserDao userDao;

    @Before
    public void initMoxck() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() {
        User user = new User(1L, "李四", "男");
        // 模拟service方法的返回值
        PowerMockito.doReturn(user).when(userDao).getById(USERID);
        User resutnUser = userService.getById(USERID);

        // 看调用Controller层的接口后返回值和定义的是否是同一个对象
        assertEquals(user, resutnUser);
    }

    @Test
    public void test1() {
        // 本地修改为提交
    }
}
