<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.webhead{
	width:101%;
	left:-2px;
	position:fixed;
	top:0;
	height:80px;
	background-color:#E5EEA6;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);
   	-moz-box-shadow: 2px 2px 10px #909090;
 	-webkit-box-shadow: 2px 2px 10px #909090;
  	box-shadow:2px 2px 10px #909090;
}
.webhead_text{
	position:absolute;
	top:55px;
	font-family:LiSu;
	font-size:20px;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
.backbroad{
	margin-top:200px;
	width:100%;
	height:270px;
	background-color:#E5EEA6;
}
.block{
	background-color:#EEFF6B;
	width:300px;
	border:1px solid #FFFFFF;
	height:110px;
	margin-left:20px;
	margin-top:15px;
}
.block_img{
	height:80px;
	width:80px;
	margin-top:15px;
	margin-left:15px;
}
.block_text{
	height:110px;
	width:170px;
	float:right;
	line-height:110px;
	font-family:LiSu;
	font-size:30px;
	color:#7C8489;
	text-align:center;
}
.block:hover{
	background-color:#F5B977;
	color:#FFFFFF;
}
.webtail{
	width:101%;
	left:-2px;
	position:fixed;
	bottom:0;
	height:80px;
	background-color:#E5EEA6;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);
   	-moz-box-shadow: 2px 2px 10px #909090;
 	-webkit-box-shadow: 2px 2px 10px #909090;
  	box-shadow:2px 2px 10px #909090;
}
.webtail_table{
	position:absolute;
	left:50%;
	margin-left:-200px;
	width:400px;
	height:80px;
	text-align:center;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
</style>





</head>
<body>

<!-- 网页头  -->
<div class="webhead">
	<img src="img/logo.png" height=80px width=500px>
	<div class="webhead_text" style="left:240px;">个人中心</div>
	<div id="welcome" class="webhead_text" style="right:150px;">欢迎</div>
	<div class="webhead_text" style="right:50px;">帮助</div>
</div>

<!-- 体  -->
<div class="backbroad">
	<div style="float:left;margin-left:20px;margin-top:10px;">
		<div style="margin-top:20px;">
			<img src="img/face.jpeg" height=160px width=160px >
		</div>
		<div style="margin-top:20px;">
			<div id="user" style="float:left;margin-left:20px;text-shadow:1px 1px 1px #000;color:#FFFFFF;"></div>
			<a href=""><div style="float:right;margin-right:20px;text-shadow:1px 1px 1px #000;color:#FFFFFF;">账户设置</div></a>
		</div>
	</div>
	<div style="float:left;margin-left:100px;">
		<a href=""><div class="block" style="float:left;" onclick="check()"><div style="float:left;"><img class="block_img" src="img/3.png"></div><div class="block_text" style="text-align:center;">聊天</div></div></a>
		<a href="perform"><div class="block" style="float:right;"><div style="float:left;"><img class="block_img"  src="img/4.png"></div><div class="block_text" style="text-align:center;">节目</div></div></a><br>
		<a href="wallet"><div class="block" style="float:left;"><div style="float:left;"><img  class="block_img" src="img/1.png"></div><div class="block_text" style="text-align:center;">钱包</div></div></a>
		<a href="luck"><div class="block" style="float:right;"><div style="float:left;"><img class="block_img"  src="img/2.png"></div><div class="block_text" style="text-align:center;">抢红包</div></div></a>
	</div>
	<div style="float:right;margin-right:20px;margin-top:10px;">
		<img src="img/jugon.gif" style="margin-top:20px;" height=200px width=200px >
	</div>
</div>

<div class="webtail"><table class="webtail_table"><td>版权声明</td><td>鸣谢</td><td>联系方式</td></table></div>

<script type="text/javascript">
var username=<%=session.getAttribute("username")%>;
if(username==null){
	username="游客";
	document.getElementById("welcome").innerHTML="游客，请登录";
}
else{
	document.getElementById("welcome").innerHTML+=username;
}
document.getElementById("user").innerHTML+=username;

</script>
</body>
</html>