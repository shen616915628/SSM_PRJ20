package com.senchen365.ssmempms.dao;

import com.senchen365.ssmempms.entity.DeptEntity;

import java.util.List;

/**
 * 类名：DeptMapper<br>
 * 功能：说明功能<br>
 * 作者：从今往后一百年......<br>
 * 日期：2019/6/2 0002<br>
 * 版本：1.0.0
 */
public interface DeptMapper {
    /**
     * 查询所有部门
     * @return
     */
    List<DeptEntity> queryAll();

    /**
     * 根据编号查询部门
     * @param dno
     * @return
     */
    DeptEntity queryByNo(int dno);
}
