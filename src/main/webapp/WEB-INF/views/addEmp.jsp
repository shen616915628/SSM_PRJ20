<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/2 0002
  Time: 下午 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加员工</title>
    <script src="<c:url value="/js/jquery3.2.js"/>"></script>
    <script>
        var flag=false;
        function checkNo(){
//            alert($("#empno").val())
            $.ajax({
                url:"${pageContext.request.contextPath}/checkno",
                type:"get",
                data:{empno:$("#empno").val()},
                dataType:"json",
//                contentType:"application/json;charset=utf-8",
                async:false,
                success:function (data) {
                    if (data){
                        flag=true;
                        $("#empnotip").html("该编号可以使用");
                    }else{
                        flag=false;
                        $("#empnotip").html("编号已存在，请重新输入");
                        $("#empNo").focus();
                        $("#empNo").select();
                    }
                }

            });
        }

        function formsubmit() {
            if(flag){
                return true;
            }
            return false;
        }
    </script>
</head>
<body>


<h1 align="center">增加员工</h1>
<sf:form action="addEmp.action" method="post" commandName="EMP" onsubmit="return formsubmit()">
    <sf:label path="empno">
        编号：
    </sf:label>
    <sf:input path="empno"  type="number"  onblur="checkNo()" /><span id="empnotip"></span><br/>

    <sf:label path="ename">
        姓名：
    </sf:label>
    <sf:input path="ename" /><br>
    
    <sf:label path="job">
        职位：
    </sf:label>
    <sf:select path="job">
        <sf:option value="CLERK">前台</sf:option>
        <sf:option value="SALEMAN">销售员</sf:option>
        <sf:option value="ANALYST">分析师</sf:option>
        <sf:option value="MANAGER">经理</sf:option>
    </sf:select><br>
    
    <sf:label path="mgr">
        上级：
    </sf:label>
    <sf:select path="mgr" items="${EMPS}" itemValue="empno" itemLabel="ename"/>
    <br>

    <sf:label path="hiredate" >
        入职时间：
    </sf:label>
    <sf:input path="hiredate" type="date"/><br>

    <sf:label path="sal">
        薪水：
    </sf:label>
    <sf:input path="sal" type="number"/><br>

    <sf:label path="comm">
        津贴：
    </sf:label>
    <sf:input path="comm" type="number"/><br>
    
    <sf:label path="dno">
        部门：
    </sf:label>
    <sf:select path="dno" items="${DEPTS}" itemLabel="dname" itemValue="deptno"/><br>

    <input type="submit" value="增加员工">

    
</sf:form>
</body>
</html>
