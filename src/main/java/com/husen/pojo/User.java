package com.husen.pojo;

import java.util.Date;

/**
 * @author 11785
 */
public class User {
    private Integer uid;
    private String name;
    private Date birthday;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
