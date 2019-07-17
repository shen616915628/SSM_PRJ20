package com.senchen365.ssmempms.web.controller;

import com.senchen365.ssmempms.entity.EmpEntity;
import com.senchen365.ssmempms.service.DeptService;
import com.senchen365.ssmempms.service.EmpService;
import com.senchen365.ssmempms.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 类名:EmpController<br>
 * 功能:员工的控制器<br>
 * 作者:java战士<br>
 * 日期:2019/3/22<br>
 * 版本:1.0.0<br>
 */
@Controller
@Scope("request")
public class EmpController {
    //支持日志
    private static final Logger LOG= LogManager.getLogger(EmpController.class);
    @Autowired
    private EmpService empService;
    @Autowired
    private DeptService deptService;

    @RequestMapping(method = RequestMethod.GET,value = "/")
    public String goHome(Model model){
        LOG.debug(".......................进入到主页.................................");
        model.addAttribute("DEPTS",deptService.queryAll());
        model.addAttribute("EMPS",empService.queryAllEmps());
        EmpEntity e=new EmpEntity();
        model.addAttribute("EMP",e);
        return "emplist";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/emplist.action")
    @ResponseBody
    public List<EmpEntity> queryEmps(){
        LOG.debug("...........................开始查找所有的员工了.........................................");
        return empService.queryAllEmps();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addEmp.action")
    public String saveEmp(EmpEntity empEntity,Model model ){
        LOG.debug("进入增加方法");
        empService.addEmp(empEntity);
        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.GET,value = "/del.action")
    @ResponseBody
    public int del(Integer id){
        System.out.println("*********"+id+"**********");
        int r=empService.delete(id);
        System.out.println("***************************************"+r);
        return r;
    }



    @RequestMapping(value ="/checkno",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkNo(Integer empno){
        LOG.debug("检查编号是否重复");
        System.out.println("*************************"+empno+"**********************************");
        return empService.checkNo(empno);
    }


    @RequestMapping(value = "/modify",method=RequestMethod.POST)
    @ResponseBody
    public int modify(@RequestBody EmpEntity myemp){
        System.out.println(myemp);
        LOG.debug(myemp);
        int r=empService.modify(myemp);
        return  r;
    }



}
