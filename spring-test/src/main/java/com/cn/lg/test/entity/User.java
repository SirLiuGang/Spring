package com.cn.lg.test.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * @Auther: 刘钢
 * @Date: 2019/3/2 23:55
 * @Description:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 779518488091185603L;

    private Long id;
    private String name;
    private String sex;

    public User() {
    }

    public User(Long id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
