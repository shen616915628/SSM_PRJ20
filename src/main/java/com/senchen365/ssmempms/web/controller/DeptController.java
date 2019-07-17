package com.senchen365.ssmempms.web.controller;

import com.senchen365.ssmempms.entity.DeptEntity;
import com.senchen365.ssmempms.entity.EmpEntity;
import com.senchen365.ssmempms.service.DeptService;
import com.senchen365.ssmempms.service.EmpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类名：DeptController <br>
 * 作者：申殿青 <br>
 * 日期： 2019/6/20 <br>
 * 功能：TODO <br>
 * 版本：1.0.0 <br>
 * 历史纪录：  <br>
 */
@Controller
@Scope("request")
public class DeptController {
    private static final  Logger LOG= LogManager.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;

    @RequestMapping(method = RequestMethod.GET,value = "/gotoadd.action")
    public String goToAddEmp(Model model){
        LOG.debug("进入增加页面");
        model.addAttribute("DEPTS",deptService.queryAll());
        model.addAttribute("EMPS",empService.queryAllEmps());
        EmpEntity e=new EmpEntity();
        model.addAttribute("EMP",e);
        return "addEmp";
    }
    @RequestMapping(method = RequestMethod.GET,value = "/dept.action")
    @ResponseBody
    public List<DeptEntity> queryEmps(){
        LOG.debug("...........................开始查找所有的部门了.........................................");
        return deptService.queryAll();
    }
}
