<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.back1{
	border:10px solid #AAB8A3;
	height:400px;
	width:900px;
	margin-top:90px;
	position:absolute;
	left:50%;
	margin-left:-450px;
}
.back2{
	margin:0 auto;
	border:10px solid #EBEDF4;
	height:380px;
	width:880px;
}
.back3{
	margin:0 auto;
	border:10px solid #FF5983;
	height:360px;
	width:860px;
}
.back4{
	margin:0 auto;
	border:10px solid #C1194E;
	height:340px;
	width:840px;
}
.back5{
	margin:0 auto;
	border:10px solid #7A023C;
	height:320px;
	width:820px;
	overflow:hidden;
}
.back6{
	margin:0 auto;
	height:320px;
	width:840px;
	overflow-y:scroll;
	overflow-x:hidden;
	background-color:#FFF;
}
.mytable{
	border:2px solid black;
}
.mytable tr{
	
}
.mytable td{
	border:1px solid black;
	padding:10px;
}
.btn{
	position:absolute;
  	margin-top:20px;
 	width:200px;
	height:50px;
	margin-top:520px;
	left:50%;
	margin-left:-100px;
	background-color:#EBEDF4;
	border-color:#FF5983;
	font-size:30px;
	border-radius:6px;
}
.btn:hover{
	background-color:#FF5983;
}
.btn2{
	position:absolute;
	right:10px;
	width:80px;
	height:50px;
	background-color:#555555;
	boder:2px solid #000000;
	color:#FFFFFF;
	font-size:20px;
	font-family:LiSu;
	border-radius:8px;
}
.btn2:hover{
	background-color:#FF5983;
}
.btn3{
	position:absolute;
	right:20px;
	top:20px;
}
.shang{
	position:fixed;
	width:500px;
	height:400px;
	background-color:#FFFFFF;
	top:100px;
	left:50%;
	margin-left:-250px;
	border:5px solid #000000;
	padding:10px;
	display:none;
}
</style>
<script type="text/javascript">

function close1(){
	document.getElementById("shang").style.display="none";
}
function open1(){
	document.getElementById("shang").style.display="block";
}
function check(){
	var t="${c}";
	
	if(t==1){
		Toast("充值成功！");
	}
	else{
		Toast("充值失败！");
	}	
}
function Toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var m = document.createElement('div');
    m.innerHTML = msg;
    m.style.cssText="width: 30%;min-width: 100px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 40%;left: 30%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
}
</script>

</head>
<body style="background-image:url(img/13.jpg);background-size:300px 300px;background-repeat:repeat;">
<a href=""><input type="button" class="btn2" onclick="" value="返回"></a>
<div style=" position:absolute;width:300px;height:100px;left:50%;margin-left:-150px;"><img src="img/perform.png" width="300px" height="100px"></div><br>
<div class="back1">
	<div class="back2">
		<div class="back3">
			<div class="back4">
				<div class="back5">
					<div class="back6" id=back>
					<table id="t" class="mytable">
						<thead><tr><td width=50>节目序号</td><td width=220>节目名称</td><td width=220>表演人员</td><td width=200>表演单位</td><td width=100>收获打赏</td><td width=50>打赏排名</td></tr></thead>
						<tbody id="d"></tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="button" class="btn" value="赏" onclick="open1()">
<div class="shang" id="shang">
	<input type="button" value="关闭" class="btn3" onclick="close1()">
	<form id="form1" name="form1" action="" method="get">
		<div style="margin-top:50px;margin-left:100px;">节目名称:<input type="text" id="text1" name="text1"></div><br>
		<div style="margin-top:10px;margin-left:100px;">打赏金额:<input type="text" id="text2" name="text2"><br>
		<input type="submit" value="确认打赏" style="margin-top:10px;margin-left:100px;">
	</form> 
</div>
</body>
</html>