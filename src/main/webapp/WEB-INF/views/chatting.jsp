<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>畅所欲言聊天室</title>
<style type="text/css">
/*页头*/
.logo{
	height:100px;
}
/*总框架*/
#D{
	border-radius:8px;
	width:800px;
	height:500px;
	position: absolute;
	top:50%;
	left: 50%;
	margin-top: -250px;
	margin-left: -400px;
}
/*框架头*/
#userhead{
	border-radius:8px 8px 0 0;
	width:100%;
	height:60px;
	background-color:#6699FF;
}
/*logo*/
.logo{
	margin-left:4px;
	width:auto;
	height:auto;
	float:left;
}
/*菜单*/
.menu{
	width:50px;
	height:40px;
	right:0px;
	top:-7px;
	position:absolute;
}
.menu li{
 	width:100px;
 	height:30px;
 	margin-left:-60px;
 	padding:0;
 	display:none;
 	background-color:#FFFFFF;
 	text-align:center;
 	line-height:30px;
}
.menu:hover li{
	display:inline-block;
}
.menu li:hover{
	background-color:#8E8E8E;
}
/*主框架*/
.broad{
	border-radius:0 0 8px 8px;
	width:100%;
	height:440px;
	
}
/*聊天区*/
.chatbroad{
	border-radius:0 0 0 8px;
	float:left;
	width:650px;
	height:440px;
	
}
/*消息区*/
.record{
	background-image:url(img/8.jpg);
	background-repeat:no-repeat;
	background-size:cover;
	width:650px;
	height:350px;
	overflow-y:scroll;
	overflow-x:hidden;
}
/*发言区*/
.chatting{
	border-radius:0 0 0 8px;
	width:650px;
	height:90px;
	
}
/*发言区头*/
.chatting_head{
	width:650px;
	height:25px;
	background-color:#99CCCC;
}
/*发言区体*/
.chatting_body{
	width:650px;
	height:65px;
	background-color:#FFFFFF;
	border-radius:0 0 0 8px;
}
/*用户列表*/
.userlist{
	border-radius:0 0 8px 0;
	float:right;
	width:150px;
	height:440px;
	
}
/*好友列表头*/
.userlist_head{
	width:150px;
	height:30px;
	background-color:#99CCCC;
	color:#FFFFFF;
	line-height:30px;
	text-align:center;
	font-family:LiSu;
	font-size:20px;
}
/*好友列表体*/
.userlist_body_outer{
	border-radius:0 0 8px 0;
	width:150px;
	height:410px;
	overflow:hidden;
}
.userlist_body_inner{
	border-radius:0 0 8px 0;
	width:170px;
	height:100%; 
	margin-left: 0;
	background-color:#D2E9FF;
	overflow-y:scroll;
	overflow-x:hidden;
}
/*好友*/
.user{
	width:150px;
	height:50px;
	margin:0;
}
/*列表头像*/
.user_img{
	width:50px;
	height:50px;
	float:right;
}
/*好友名字*/
.user_name{
	background-color: #D2E9FF;
	text-align: center;
	color: black; 
	width:100px;
	float:left;
	height:50px;
	border-bottom: 1px solid #8E8E8E;
	border-left:none;
	border-right:none;
}
.user_name:hover {
    background-color: #8E8E8E;
    color: white;
}
/*消息*/
.msg{
    overflow: auto;
    _height:1%;
	margin-top:10px;
	width:630px;
	min-height:52px;
	height:auto;
}
/*消息头像*/
.msg_img{
	margin-top:10px;
	margin-left:10px;
	width:50px;
	height:50px;
	/*border:1px solid black;*/
	float:left;
}
/*消息头像2*/
.msg_img2{
	margin-top:10px;
	margin-right:10px;
	width:50px;
	height:50px;
	/*border:1px solid black;*/
	float:right;
}
/*消息体*/
.msg_body{
	float:left;
	_width:100px;
	min-width:100px;
	height:auto;
	margin-top:0;
	margin-left:0;
	display:inline-block; 
	*display:inline; 
	*zoom:1;
	border-radius:8px;
}
/*消息体2*/
.msg_body2{
	float:right;
	_width:100px;
	min-width:100px;
	height:auto;
	margin-top:0;
	margin-right:0;
	display:inline-block; 
	*display:inline; 
	*zoom:1;
	border-radius:8px;
}
/*消息体头*/
.msg_head{
	height:auto;
	width:auto;
	border-bottom:1px solid #FFFFFF;
	border-top:0;
	border-left:0;
	border-right:0;
	border-radius:8px 8px 0 0;
	padding-left:8px;
	padding-top:6px;
	padding-bottom:4px;
	color:#FFFFFF;
	font-size:10px;
	background-color:#FFD306;
}
/*消息信息*/
.message{
	height:auto;
	width:auto;
	padding:10px;
	border-radius:0 0 8px 8px;
	background-color:#FFD306;
	font-family:LiSu;
	font-size:18px;
	color:#BB3D00;
}
/*角*/
.horn { 
	float:left;
	margin-top:30px;
	margin-left:0;
	width:0;  
	height:0;  
	font-size:0;  
	border:solid 8px;  
	border-color:#FFFFFF #FFD306 #FFFFFF #FFFFFF;  
}  
/*角2*/
.horn2 { 
	float:right;
	margin-top:30px;
	margin-right:0;
	width:0;  
	height:0;  
	font-size:0;  
	border:solid 8px;  
	border-color:#FFFFFF #FFFFFF #FFFFFF #FFD306;  
} 
/*表情包*/
.pic{
    overflow-y:scroll;
    float:right;
    bottom:90px;
    right:150px;
    width:617px;
    height:300px;
    border:solid 1px;
    border-color:#000000;
    position:absolute;
    background-color:#A6FFFF;
}
/*表情包头*/
.chatting_head2{
	width:600px;
	height:24px;
	background-color:#99CCCC;
}
/*按钮*/
.btn{
	float:right;
}
/*test3*/
.test3{
	height:auto;
	_width:100px;
	min-width:100px;
	width:auto;
	border:1px solid black;
	display:inline-block; 
	*display:inline; 
	*zoom:1;
}
</style>

<script type="text/javascript">
//检查并提交
function abc(){
	document.getElementById('text').value=document.getElementById('divtext').innerHTML;
	 if(document.getElementById("text").value.length!=0){
		var xml =new XMLHttpRequest();
		var record=document.getElementById("text").value;
		var url="insertrecord?record="+record;
		xml.open("get", url, true);
		document.getElementById('divtext').innerHTML="";
		xml.send();
		xml.onreadystatechange=function(){
			if (xml.readyState==4 && xml.status==200)
		    {
				getrecord();
		     //var s=document.getElementById("userlist").innerHTML+=;
		    }
		};
		
	}
	else{
		Toast("聊天内容不能为空",2000);
	} 
}
//提示弹框
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
function check(){
	if(document.getElementById("text").value.length==0){
		return false;
	}
	return true;
}
//setInterval("getrecord()",20000);


function getuseronline(){
	var xml=new XMLHttpRequest();
	var url="getuseronline";
	xml.open("GET", url, true);
	xml.send();	
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
		 //alert(xml.responseText);
	     //var s=document.getElementById("userlist").innerHTML+=;
	     var r=document.getElementById("userlist");
			r.innerHTML=xml.responseText;
			r.scrollTop=r.scrollHeight;
	    }
	};
	
}
var maxID=0;
function getrecord(){
	++maxID;
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
	     document.getElementById("record").innerHTML=xml.responseText;
	     var r=document.getElementById("record");
			r.scrollTop=r.scrollHeight;
	     
	    }
	};
	var url="getrecords";
	xml.open("GET", url, true);
	xml.send();	
}	
var i=0;
function add(){
	var c="测试";
	var temp=document.getElementById("divtext");
	temp.innerHTML+="<img src='img/photo.gif' height='50' width='50' style='z-index:100;'>";
	for(var j=0;j<5;j++,i++){
		var c1=c+i;
		var s="<div class='msg'>"+
					"<div class='msg_img'>"+"<img src='img/1.jpg' height='50' width='50' style='z-index:100;'>"+"</div>"+
					"<div class='horn'></div>"+
					"<div class='msg_body'>"+
						"<div class='msg_head'>"+"用户+时间"+"</div>"+
						"<div class='message'>"+"这是一条简单的测试"+"<img src='images/10.jpg' height='50' width='50' style='z-index:100;'>"+c1+"</div>"+
					"</div>"+
				"</div>";
		var r=document.getElementById("record");
		r.innerHTML+=s;
		r.scrollTop=r.scrollHeight;
	}
}
function add2(){
	var c="测试";
	for(var j=0;j<5;j++,i++){
		var c1=c+i;
		var s="<div class='user'>"+
					"<div class='user_img'>"+"<img src='img/6.gif' height='50' width='50'>"+"</div>"+
					"<button class='user_name'>"+c1+"</button>"+
				"</div>";
		var r=document.getElementById("userlist");
		r.innerHTML+=s;
		r.scrollTop=r.scrollHeight;
		
	}
}
function getpic(){
	document.getElementById("pic").style.display="";
	}
	function initpic(){
		var r=document.getElementById("pic");
		r.innerHTML+="<table  cellpadding='0' cellspacing='0'>";
		var t=1;
		for (var s = 1; s <= 35; s++) {
				if (t == 1)
					r.innerHTML += "<tr>";
				r.innerHTML += "<td><img onclick='addpic(this)' src='images/"+s+".jpg' width='60px' height='60px'></td>";
				if (t == 6) {
					r.innerHTML += "</tr>";
					t = 1;
				}
			}
			r.innerHTML += "</table>";
	}
	function addpic(img) {
		var t=document.getElementById("divtext");
		t.innerHTML+="<img src='"+img.src+"' width='60px' height='60px'>";
		var s=document.getElementById("pic");
		s.style.display="none";
	}
	function closepic(){
		var s=document.getElementById("pic");
		s.style.display="none";
	}
	function delpic() {

	}
</script>

</head>
<!-- background-image: url(img/33.png); background-repeat: no-repeat; background-size: cover -->
<body style="background-image: url(img/33.png); background-repeat: no-repeat; background-size: cover"><!-- 10.jpg -->
<div class="logo"></div>
<div id="D">
<div class="pic" id="pic" style="display:none">
<div class="chatting_head2"><input type="button" value="关闭" class="btn" onclick="closepic()"></div>
</div>
	<div id="userhead">
		<div class="logo"><img src="img/logo.png" width="400px" height="50px" style="margin-top:3px;"></div>
		<ul class="menu"><img src='img/drop_down.png' height='40px' width='40px'></a>
			<li>弹出菜单1</li>
            <li>弹出菜单2</li>
       	</ul>
	</div>
	<div>
		<div class="chatbroad">
			<div class="record" id="record"></div>
			<div class="chatting">
				<form name="form1">
					<div class="chatting_head"><input type="button" class="btn" value="测试3" onclick="add2()"><input type="button" class="btn" value="测试2" onclick="add()"><input type="button" class="btn" value="表情包" onclick="getpic()"><input type="button" value="发言" class="btn" onclick="abc()"></div>
					<div class="chatting_body"><div contenteditable="true" name="divtext" id="divtext" style="margin:0px 0 0 6px;width:644px; height:70px; border:0 none; outline:none;"></div></div>
				    <input type="hidden" id="text">
				</form>
			</div>
		</div>
		<div class="userlist">
			<div class="userlist_head">在线用户</div>
			<div class="userlist_body_outer">
				<div class="userlist_body_inner" id="userlist"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//getrecord()
getuseronline()
setInterval('getrecord()',4000);
setInterval('getuseronline()',10000);
initpic();
</script>
<a href="home"><font color=#fff>返回主页</font></a>
</body>
</html>