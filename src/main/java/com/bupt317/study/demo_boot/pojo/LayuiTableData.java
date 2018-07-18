package com.bupt317.study.demo_boot.pojo;

import java.util.List;

/**
 * Create by 菲尼莫斯 on 2018/7/16 16:30
 *
 * @Description : LayuiTableData
 * @Program : demo_boot
 */
public class LayuiTableData {
    private Integer code;
    private String msg;
    private Integer count;
    private List data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
