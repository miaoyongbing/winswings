<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" ref="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script type="application/javascript">
var itcode='<%=session.getAttribute("itcode")%>';
$(document).ready(function () {
	
	$("#initwallet").click(function() {
   	  $.get("initwallet?itcode="+itcode,function(result){
     if(result=="true"){
    	 $("#initwallet").val("钱包已激活");
    	 checkwallet()
    	 alert("激活成功");
     }else{
    	 alert("激活失败");
    	 }
     })
    	 
     })
})
</script>
<script type="text/javascript">
var itcode='<%=session.getAttribute("itcode")%>';
function checkwallet(){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.open("get", "checkwallet?itcode="+itcode);
	xmlhttp.send(null);
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState===4) {
			if (xmlhttp.status===200) { 
				if(xmlhttp.responseText=="false"){
					document.getElementById("initwallet").value="请激活钱包";
					return true;
				}else{
					document.getElementById("initwallet").value="已经激活钱包";
					document.getElementById("initwallet").disabled="disabled";
					document.getElementById("div2").innerText="账户余额为"+xmlhttp.responseText+"元";
					return false;
				}
			} else {
				alert("发生错误：" + request.status);
				return false;
			}
		} 
	}
}



function check11(){
	if(document.getElementById("itcode").value.length==0){
		//alert("用户名不能为空");
		document.getElementById("afteritcode").innerText="请输入itcode";
		return false;
	}else{
// 		document.getElementById("afteritcode").innerText="";
		return true;
	}
}
function check12(){
	if(document.getElementById("username").value.length==0){
		//alert("用户名不能为空");
		document.getElementById("afterusername").innerText="请输入用户名";
		return false;
	}else{
		document.getElementById("afterusername").innerText="";
		return true;
	}
}
function check13(){
	if(document.getElementById("date").value.length==0){
		//alert("用户名不能为空");
		document.getElementById("afterdate").innerText="请输入日期";
		return false;
	}else{
		document.getElementById("afterdate").innerText="";
		return true;
	}
}

</script>
</head>
<body>
欢迎来到钱的世界,<a href="balance_add">充值</a>
<hr>
您可以查看某天您的交易记录：<hr>
<form name="from1" action="trade_check">

<table>
<tr><td>输入itcode</td><td><input id="itcode" name="itcode" onblur="check11()"></td><td><span id="afteritcode" style="color:red"></span></td></tr>
<tr><td>输入用户姓名</td><td><input id="username" name="username" onblur="check12()"></td><td><span id="afterusername" style="color:red"></span></td></tr>
<tr><td>输入日期</td><td><input id="date" name="date" type="date" onblur="check13()"><td><span id="afterdate" style="color:red"></span></td></td></tr>
<tr><td><input type="submit" value="提交"></td></tr>
</table>
</form>
<hr>
开启红包雨<a href="luckyadmin">进入红包雨界面</a><hr>

<div id="div2">账户余额</div>
<input type="button"  value="激活钱包" id="initwallet">

<a href="/dtss">返回主页</a>
<script type="text/javascript">
checkwallet()
</script>
</body>
</html>