<%@ page language="java" import = "java.util.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<script type="text/javascript">
function changeimg() {
	document.getElementById("img1").src= "Vali_Code?"+Math.random();
}
function initAjax(){
	var xmlHttp;
	try{
		xmlHttp=new XMLHttpRequest();
	
	}catch(e){
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			alert("1");
		}catch(e){
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e)
			{
				alert("您的浏览器不支持AJAX");
			}
		}
	}
	return xmlHttp;
}
function check_itcode(){
	var http_request = initAjax();
	var itcode = document.getElementById("itcode").value;
	http_request.open("post","check_itcode?itcode="+itcode,true);
	http_request.onreadystatechange = function(){
		if(http_request.readyState==4){
			if(http_request.status==200)
			{
				alert(http_request.responseText);
			
			}
		}
	}
}
</script>
</head>
<body >

<!--
这是jsp版的登录界面
<hr>
昵称：<input name = "nickname" id ="nickname" onblur="check_nick">
<input type="button" value = "check" onclick="check_nick()">
<hr>
<form name="form2" id="form2" action="Upload" method="post" enctype="multipart/form-data">


<input type="file" name="face_icon" id="face_icod">


<input type="submit">
</form>
 <hr>
 -->
<%
/*
	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(Cookie c : cookies) {
			if(c.getName().equals("username")) {
				out.print("欢迎" + c.getValue());
			}
		}
	} else {*/
		out.println("您需要认证后才能访问页面 ！");
		//response.sendRedirect("login.html");
	//}
%>
  
<form name="form1"  action="vali.do" method="get">
<table border = "0">
<tr><td>itcode:</td><td><input id="itcode" type="number" name="itcode" onblur="check_itcode"></td></tr>
<tr><td>密码:</td><td><input id="password" name="password"></td></tr>
<tr><td>验证码:</td><td><input id="vali_code" type="number" name="vali_code"><img  id="img1"  name = "img1" alt="" src="Vali_Code" onclick="changeimg()"></td></tr>
<tr><td><input type="submit" value="登录"></td></tr>
</table>
</form>
</body>
</html>