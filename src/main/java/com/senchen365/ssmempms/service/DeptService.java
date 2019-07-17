package com.senchen365.ssmempms.service;

import com.senchen365.ssmempms.dao.DeptMapper;
import com.senchen365.ssmempms.entity.DeptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：DeptService<br>
 * 功能：说明功能<br>
 * 作者：从今往后一百年......<br>
 * 日期：2019/6/2 0002<br>
 * 版本：1.0.0
 */
@Service
@Scope("prototype")
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 所有的部门
     * @return
     */
    public List<DeptEntity> queryAll(){
        return deptMapper.queryAll();
    }
}
