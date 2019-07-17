package com.senchen365.ssmempms.service;

import com.senchen365.ssmempms.dao.EmpMapper;
import com.senchen365.ssmempms.entity.EmpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名:EmpService<br>
 * 功能:操作员工的业务类<br>
 * 作者:java战士<br>
 * 日期:2019/3/22<br>
 * 版本:1.0.0<br>
 */
@Service
@Scope("prototype")
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 得到所有的员工信息
     * @return
     */
    public List<EmpEntity> queryAllEmps(){
        return empMapper.queryAllEmps();
    }

    /**
     * 增加
     * @param e
     * @return
     */
    public int addEmp(EmpEntity e){
        return empMapper.addEmp(e);
    }

    /**
     * 判断编号是否存在
     * @param id
     * @return
     */
    public Boolean checkNo(Integer id){
        int r=empMapper.checkNo(id);
        return (r==0?true:false);

    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id){
        int r=empMapper.delete(id);
        return r;
    }

    /**
     *  根据编号查找
     * @param id
     * @return
     */
    public EmpEntity queryByID(Integer id){
        return empMapper.queryByID(id);
    }

    /**
     * 修改
     * @param empEntity
     * @return
     */
    public int modify(EmpEntity empEntity){
        return empMapper.modify(empEntity);

    }

}
