package com.senchen365.ssmempms.dao;

import com.senchen365.ssmempms.entity.EmpEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类名:EmpMapper<br>
 * 功能:操作员工的映射类(数据访问类)<br>
 * 作者:java战士<br>
 * 日期:2019/3/22<br>
 * 版本:1.0.0<br>
 */
@Repository
public interface EmpMapper {
    /**
     * 得到所有的员工信息(包括每个员工对应的部门)
     * @return
     */
    @Cacheable(value = "menuCache",key="methodName")
    List<EmpEntity> queryAllEmps();

    /**
     * 增加员工
     * @param e
     * @return
     */
    @CacheEvict(value = "menuCache",key = "'queryAllEmps'")
    int addEmp(EmpEntity e);

    /**
     * 检查编号是否存在
     * @param no
     * @return
     */
    int checkNo(Integer no);

    /**
     * 删除
     * @param id
     * @return
     */
    @CacheEvict(value = "menuCache",key = "'queryAllEmps'")
    int delete(Integer id);

    /**
     * 根据编号查找员工
     * @param id
     * @return
     */
    EmpEntity queryByID(Integer id);

    /**
     * 根据编号修改
     * @param empEntity
     * @return
     */
    int modify(EmpEntity empEntity);
}
