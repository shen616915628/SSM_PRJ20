package com.senchen365.ssmempms.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 类名:EmpEntity<br>
 * 功能:员工实体<br>
 * 作者:java战士<br>
 * 日期:2019/3/1<br>
 * 版本:1.0.0<br>
 */
public class EmpEntity  implements Serializable{
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    //格式化日期形式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer dno;


    public Integer getDno() {
        return dno;
    }

    public void setDno(Integer dno) {
        this.dno = dno;
    }

    @Override
    public String toString() {
        return "EmpEntity{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", dno=" + dno +
                ", deptEntity=" + deptEntity +
                '}';
    }

    //多个员工对应一个部门
    private DeptEntity deptEntity;

    public DeptEntity getDeptEntity() {
        return deptEntity;
    }

    public void setDeptEntity(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

}
