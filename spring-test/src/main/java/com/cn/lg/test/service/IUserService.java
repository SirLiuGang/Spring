package com.cn.lg.test.service;

import com.cn.lg.test.entity.User;

/**
 * @author: 刘钢
 * @Date: 2019/3/3 17:40
 * @Description:
 */
public interface IUserService {

    User getById(Long userId);
}
