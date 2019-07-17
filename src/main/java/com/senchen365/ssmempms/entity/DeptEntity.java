package com.senchen365.ssmempms.entity;

import java.io.Serializable;

/**
 * 类名:DeptEntity<br>
 * 功能:部门实体信息<br>
 * 作者:java战士<br>
 * 日期:2019/3/1<br>
 * 版本:1.0.0<br>
 */
public class DeptEntity implements Serializable{
    private Integer deptno;
    private String dname;
    private String loc;

    @Override
    public String toString() {
        return "DeptEntity{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    public DeptEntity() {
    }

    public DeptEntity(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
