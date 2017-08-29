<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.btn{
	top:400px;
	position:absolute;
	width:300px;
	height:80px;
	background-color:#FF3333;
	boder:2px solid #000000;
	color:#FFFFFF;
	font-size:20px;
	font-family:LiSu;
	border-radius:8px;
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

</style>


<script type="text/javascript">
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
function tishi(i){
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
			Toast("红包雨已开启",3000);
			close1(i);
	    }
	};
	var url="luck_check?round="+i;
	xml.open("GET", url, true);
	xml.send();	
}
function close1(i){
	if(i==1)
		document.getElementById("b1").disabled="disabled";
	if(i==2)
		document.getElementById("b2").disabled="disabled";
	if(i==3)
		document.getElementById("b3").disabled="disabled";
}
</script>
</head>
<body style="background-color:#F2753F;">
<a href=""><input type="button" class="btn2" value="返回"></a>
<div class="tv" ></div>
<input type="button" id="b1" class="btn" style="left:100px;" value="第一波红包开启" onclick="tishi(1)">
<input type="button" id="b2" class="btn" style="left:50%;margin-left:-150px;" value="第二波红包开启" onclick="tishi(2)">
<input type="button" id="b3" class="btn" style="right:100px;" value="第三波红包开启" onclick="tishi(3)">
</body>
</html>