package com.cn.lg.test.controller;

import com.cn.lg.test.entity.User;
import com.cn.lg.test.service.IUserService;
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
import static org.junit.Assert.assertNull;

/**
 * 对Controller层进行测试
 * @Auther: 刘钢
 * @Date: 2019/3/3 18:04
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserController {

    private static final Long USERID = 123L;

    // 引入要测试的Controller
    @InjectMocks    // 这个注解不会把一个类变成mock或是spy，但是会把当前对象下面的Mock/Spy类注入进去,按类型注入。
    @Spy
    private UserController userController = new UserController();

    @Mock
    private IUserService userService;

    @Before
    public void initMoxck() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 没有对Service方法进行mock
     */
    @Test
    public void getUserById_noMock() {
        User user = userController.getUserById(USERID);
        // 返回结果为null
        assertNull(user);
    }

    /**
     * 对Service方法进行mock
     */
    @Test
    public void getUserById_mock() {
        User user = new User(1L, "李四", "男");
        // 模拟service方法的返回值
        PowerMockito.doReturn(user).when(userService).getById(USERID);
        User resutnUser = userController.getUserById(USERID);

        // 看调用Controller层的接口后返回值和定义的是否是同一个对象
        assertEquals(user, resutnUser);
    }
}
