<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>员工列表</title>
    <style>
        button[type="button"]{
            margin: 3px;
        }

        table tr td{
            width: 250px;
            text-align: center;
            vertical-align: middle!important;
        }
    </style>

    <%--加入bootstrap.css--%>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-hover text-align table-bordered ">
                <caption style="text-align: center!important;">员工信息表表</caption><a style="align-content: center" href="<c:url value="/gotoadd.action"/>">增加员工</a>
                <thead>
                <tr class="success">
                    <th class="text-center">编号</th>
                    <th class="text-center">名字</th>
                    <th class="text-center">工作职位</th>
                    <th class="text-center">上级编号</th>
                    <th class="text-center">入职时间</th>
                    <th class="text-center">薪资</th>
                    <th class="text-center">津贴</th>
                    <th class="text-center">部门名字</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="emptable">
                    <%--利用json加入数据--%>
                </tbody>
            </table>
        </div>
    </div>



    <div id="modifyDiv" style="display: none" >
        <form id="commonEmpFrm">
            员工编号:<input type="text" name="mempNo" id="mempNo" size="45"/><br>
            员工名字:<input type="text" name="mename" id="mename" size="45"/><br>
            工作职位:
            <%--得到所有的job--%>
            <select name="mjob" id="mjob">
                <option value="CLERK">前台接待员</option>
                <option value="ANALYST">分析师</option>
                <option value="SALESMAN">销售人员</option>
                <option value="MANAGER">经理</option>
                <option value="OTHER">其它</option>
            </select>
            <br>
            上级编号:
            <select name="mmgr" id="mmgr">
                <%--同样的思路得到所有的人员信息，在修改的时候，注意上级编号的反向显示--%>
                <option value="-1">无上级</option>
            </select>
            <br>
            入职时间:<input type="date" name="mhiredate" id="mhiredate"/><br>
            工资:<input type="number" name="msal" size="45" id="msal"/><br>
            津贴:<input type="number" name="mcomm" size="45" id="mcomm"/><br>
            部门编号:
            <select name="mdeptno" id="mdeptno"  >
                <%--利用ajax动态生成部门列表信息--%>
            </select>
            <br>
        <button type="button" id="modifybtn" onclick="modify()">修 改 员 工</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery3.2.js">
</script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js">
</script>


<script>
    //自己编写serializeObject方法
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


    //异步去得到所有的部门信息进行操作
    $.ajax({
        contentType: "application/json;charset=utf-8",
        type: 'get',
        url: '${pageContext.request.contextPath }/dept.action',
        //请求的json，设置
        success: function (depts) {
            $.each(depts, function (i) {
                //下拉列表进行增加
                $("#mdeptno").append("<option value='" + depts[i].deptno + "'>" + depts[i].dname + "</option>");
            });
        }
    });
    $.ajax({
        contentType: "application/json;charset=utf-8",
        type: 'get',
        url: '${pageContext.request.contextPath }/emplist.action',
        success: function (emps) {
            $.each(emps,function (i) {
                $("#mmgr").append("<option value='"+emps[i].empno+"'>"+emps[i].ename+"</option>")
            })
        }
    })
    //刚开始就请求员工列表的内容
    $(document).ready(function () {
        //使用ajax请求得到所有的员工信息
        $.ajax({
            type: 'get',
            url: '${pageContext.request.contextPath }/emplist.action',
            //请求的json，设置
            success: function (emps) {
                $.each(emps, function (i) {
                    var emp = emps[i];
                    //删除的时候得到当前tr的编号就可以删除了
                    //修改的时候不能传递emp,因为是json格式，会跟''号进行配对，所以得传递编号
                    $("#emptable").append($("<tr  id='tr" + emp.empno + "'><td>" + emp.empno
                            + "</td><td>" +
                            emp.ename + "</td><td>" +
                            emp.job + "</td><td>" +
                            (emp.mgr == null ? "无上级" : emp.mgr) + "</td><td>" +
                            emp.hiredate + "</td><td>" +
                            emp.sal + "</td><td>" +
                            (emp.comm == null||emp.comm == 0 ? "无" : emp.comm) + "</td><td>" +
                            emp.deptEntity.dname + "</td><td><button  class='btn btn-primary'  type='button' " +
                            "onclick='modifyEmp(" + emp.empno +")'>修改</button>" +
                            "<button type='button'  class='btn btn-danger' onclick='delEmp(" + emp.empno + ")'>删除" +
                            "</button></td></tr>"));
                });

            }

        });
    });
    function modify() {
        var myemp = $("#commonEmpFrm").serializeObject();
        myemp = JSON.stringify(myemp);
        $.ajax({
            contentType: "application/json;charset=utf-8",
            type: 'post',
            url: '${pageContext.request.contextPath }/modify',
            data:myemp,
            //请求的json，让页面刷新就好
            success: function (r) {
                if (r > 0) {
                    alert("修改成功");
                    location.reload();//页面重新加载
                }
            }

        });
    }

//    $("#modifybtn").click(function () {
//
//    });

    function delEmp(empno) {
//        alert(empno);
        var id=empno;
        $.ajax({
            url: "${pageContext.request.contextPath}/del.action",
            data: {"id": id},
            success:function (r) {
                location.reload();
//                if (r>0){
//                    alert("删除成功！");
//                    $("#tr" + empno).remove();
//                }else{
//                    alert("删除失败！");
//                }

            }
        })
    }
    function modifyEmp(empno) {
        $("#modifyDiv").show();
        var ygno = $("#tr" + empno + " td:nth-child(" + 1 + ")").text();

        var ygname = $("#tr" + empno + " td:nth-child(" + 2 + ")").text();
        var ygjob = $("#tr" + empno + " td:nth-child(" + 3 + ")").text();

        //上级编号
        var ygmgr = $("#tr" + empno + " td:nth-child(" + 4 + ")").text();
        ygmgr=ygmgr=="无上级"?-1:ygmgr;

        var yghiredate = $("#tr" + empno + " td:nth-child(" + 5 + ")").text();
        var ygsal = $("#tr" + empno + " td:nth-child(" + 6 + ")").text();
        var ygcomm = $("#tr" + empno + " td:nth-child(" + 7 + ")").text();
        var ygdno = $("#tr" + empno + " td:nth-child(" + 8 + ")").text();
        $("#mempNo").val(ygno);
        //让员工的编号不能被更改
        document.getElementById("mempNo").readOnly = true;
        $("#mmgr").val(ygmgr);
        $("#mename").val(ygname);
        $("#mjob").val(ygjob);
        $("#mhiredate").val(yghiredate);
        $("#msal").val(ygsal);
        $("#mcomm").val(ygcomm);
        $("#mdeptno").val(ygdno);


    }
</script>
</body>
</html>
