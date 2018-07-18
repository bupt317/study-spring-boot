package com.bupt317.study.demo_boot.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Create by 菲尼莫斯 on 2018/7/11 21:03
 *
 * @Description : Project
 * @Program : demo_boot
 */
public class Project {
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
