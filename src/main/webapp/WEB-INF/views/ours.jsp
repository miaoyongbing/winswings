<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>联系方式</title>
<style type="text/css">
.D{
	width:500px;
	height:200px;
	border:1px solid red;
	margin-top:10px;
}

.D img{
	position:absolute;
	width:200px;
	height:200px;
}
.D table{
	float:right;
	color:black;
	background-color:#FFFFFF;
	height:200px;
	width:300px;
}
.img1{
	position:absolute;
}
.btn{
	position:absolute;
	left:1250px;
	top:600px;
	width:80px;
	height:40px;
	border-radius:12px;
	font-family:LiSu;
	font-size:18px;
	border:none;
}
.btn:hover{
	border:1px solid #FFFFFF;
	background-color:#000000;
	color:#FFFFFF;
}
.btn1{
	position:absolute;
	left:910px;
	top:200px;
	background-color:#FFFFFF;
	color:#EB3F2F;
	border:1px solid #DDF0ED;
	width:150px;
	height:60px;
}
.d1{
	position:absolute;
	left:835px;
	top:280px;
	background-color:#FFFFFF;
	color:#000000;
	border:1px solid #DDF0ED;
	width:300px;
	height:30px;
	text-align:center;
	line-height:30px;
	display:none;
}
</style>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$(".btn1").hover(
		function(){
			$(".d1").fadeToggle(1000);
			
		},
		function(){
		}
	);
})
</script>






</head>
<body style="background-image:url(img/contact.jpg);">
<div class="D">
	<img src="img/miao.jpg">
	<table>
		<tr><td>YongBing Miao</td></tr>
		<tr><td>运维工程师</td></tr>
		<tr><td>The smartest boy in the world</td></tr>
		<tr><td>His motto is "始终没有沦为一名优秀的大学生，靠的就是坚强品质！"</td></tr>
	</table>
</div>
<div class="D">
	<img src="img/luo.jpg">
	<table>
		<tr><td>H·L</td></tr>
		<tr><td>前端工程师</td></tr>
		<tr><td>The strongest boy in the world</td></tr>
		<tr><td>His motto is "作为一个怪兽，我的愿望是至少消灭一个奥特曼"</td></tr>
	</table>
</div>
<div class="D">
	<img src="img/wei.jpg">
	<table>
		<tr><td>RiSheng Wei</td></tr>
		<tr><td>后台工程师</td></tr>
		<tr><td>The handsomest boy in the world</td></tr>
		<tr><td>His motto is "妈的，我被人投诉了！客户说我给他的mp3文件没有图像！"</td></tr>
	</table>
</div>
<img class="img1" src="img/remind.png" style="top:20px;left:700px;width:600px;">
<img class="img1" src="img/remind2.png" style="top:80px;left:735px;width:500px;">
<input class="btn1" type="button" value="下载源码">
<div class="d1"><a href="js/project.rar" download="project.rar">点击下载</a></div>
<a href="home"><input class="btn" type="button" value="返回"></a>
</body>
</html>