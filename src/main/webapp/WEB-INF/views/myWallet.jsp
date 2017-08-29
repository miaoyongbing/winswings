<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page import="com.dcone.dtss.model.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>钱包</title>
<link rel="stylesheet" href="css/common.css" />
<link href="css/bootstrap.min.css" ref="stylesheet">

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<style type="text/css">
.dropdown{
	top:1%;
	right:2%;
	position:absolute;
	width:70px;
	height:120px;
}
.dropbtn{
	width:100%;
	height:40px;
	position:absolute;
	top:0px;
	right:0px;
	border:none;
	background:#99A8A1;
	color:#FFFFFF;
}
.content{
	top:40px;
	left:0px;
	width:100%;
	height:80px;
	position:absolute;
	display:none;
}
.content div{
	display:block;
	width:100%;
	height:40px;
	background:#FFFFFF;
}
.content div img{
	float:left;
	margin-top:8px;
	margin-left:5px;
	max-height:24px;
}
.content div span{
	height:40px;
	font-size:auto;	
	line-height:40px;
	margin-left:5px;
}
.dropdown:hover .content{
	display:block;
	background-color:#BABCAF;
}
.content div:hover{
	background:#FDEDD4;
}
.D{
	position:absolute;
	height:60%;
	width:55%;
	border:1px solid #D9D6CF;
	margin-left:18%;
	margin-top:8%;
	overflow:hidden;
}
.D1{
	position:absolute;
	top:-90%;
	height:90%;
	width:calc(100% + 20px);
	background-color:#87843B;
	color:#FFFFFF;
	overflow-x:hidden;
	overflow-y:scroll;
}
.D2{
	position:absolute;
	top:-100%;
	height:100%;
	width:calc(100% + 20px);
	color:#FFFFFF;
}
.dhead{
	position:absolute;
	top:0;
	width:100%;
	max-head:100%;
	left:0;
}
.menu{
	position:absolute;
	width:18%;
	height:55%;
	margin-top:8%;
	margin-left:78%;
}
.menu_li{
	position:relative;
	width:100%;
	height:20%;
	border-bottom:1px solid #C3BED4;
	color:#FFFFFF;
	font-family:华文行楷;
	font-size:20px;
	line-height:20px;
	text-shadow: 2px 2px 2px #000;
}
.menu_li span{
	position:absolute;
	bottom:8px;
	margin-left:15%;
}
.sign{
	position:absolute;
	float:left;
	width:10px;
	height:10px;
	background-color:#000000;
	margin-left:10px;
	margin-top:20%;
}
.sign2{
	position:absolute;
	width:15px;
	height:15px;
	float:left;
	margin-left:10px;
	margin-top:20%;
	border-radius:50%;
	display:none;
}
.tab{
	width:100%;
	color:#FFFFFF;
}
.tab tr{
	
}
.tab td{
	border:1px solid black;
	height:30px;
}
.btn_broad1{
	width:6%;
	height:auto;
	position:absolute;
	left:18%;
	margin-top:16%;
	z-index:0;
}
.btn{
	width:100%;
	height:50px;
	border:none;
	background-color:#171916;
	color:#515733;
	text-align:center;
	font-size:10px;
	outline:none;
}
.btn_broad2{
	position:absolute;
	width:100%;
	height:10%;
	bottom:0px;
	background-color:#171916;
	text-align:center;
	display:none;
}
.date{
	font-family:LiSu;
	height:80%;
	margin-top:0.5%;
	font-size:15px;
}
.btn2{
	font-family:LiSu;
	height:80%;
	margin-top:0.5%;
	font-size:15px;
	border-radius:8px;
	border:none;
	outline:none;
	background-color:#869B74;
	color:#515733;
}
.btn2:hover{
	background-color:#D9D6CF;
	color:#000000;
}
.D2 input[type=number]{
	position:absolute;
	border-radius: 5px;
	width:40%;
	height:25px;
	border:0;
	left:30%;
	top:40%;
}
.D2 input[type=number]::-ms-input-placeholder{text-align: center;}
.D2 input[type=number]::-webkit-input-placeholder{text-align: center;}
.D2 input::-webkit-outer-spin-button,input::-webkit-inner-spin-button {
    -webkit-appearance: none !important;
    margin: 0;
}

.D2 input[type=text]{
	position:absolute;
	border-radius: 5px;
	width:40%;
	height:25px;
	border:0;
	left:30%;
	top:40%;
}
.D2 input[type=text]::-ms-input-placeholder{text-align: center;}
.D2 input[type=text]::-webkit-input-placeholder{text-align: center;}
.D2 span{
	position:absolute;
	width:30%;
	text-align:center;
	left:35%;
	top:30%;
	font-size:150%;
}
.D2 input[type=button]{
	position:absolute;
    border-radius: 5px;
    width:20%;
    left:40%;
    top:50%;
	height:30px;
	border:1px solid #FFFFB9;
	background-color:#FFFEA0;
	color:#E9AE6A;
}
.D2 input[type=button]:hover{
	background-color:#BA874C;
	color:#FFF8D7;
}

</style>
<script type="text/javascript">

var a=new Array(0,1,2,3,4);
function abc(){
	var b=0;
	for(var i=0;i<5;i++){
		b+=a[i];
	}
	alert(b);
}
</script>
<script type="text/javascript">
var itcode='<%=session.getAttribute("itcode")%>';
$(function() {
	function getSrceenWH() {
        w = $(window).width();
        h = $(window).height();
        $('#dialogBg').width(w).height(h);
    }
    window.onresize = function() {
        getSrceenWH();
    }
    $(window).resize();

    $(function() {
        getSrceenWH();
		if("${money}"=="未激活钱包"){
            	className = $(this).attr('class');
     	       $('#dialogBg').fadeIn(300);
        	    $('#dialog').removeAttr('class').addClass(
            	    'animated ' + className + '').fadeIn();
           	 $('#itcode').val("${itcode}").attr("disabled","disabled");;
            	$('#username').val("尚未激活");
		}
    //关闭弹窗
	    $('.claseDialogBtn').click(function() {
    	    $('#dialogBg').fadeOut(300, function() {
        	    $('#dialog').addClass('bounceOutUp').fadeOut();
       	 });
    	});
    
	    $('#submit').click(function() {
	    	
	    	$.get("initwallet?itcode="+itcode,function(result){
	    	     if(result=="true"){
	    	    	 alert("激活成功");
	    	    	 window.location.reload();//刷新当前页面.
	    	     }else{
	    	    	 alert("激活失败");
	    	    	 }
	    	     });
	    });
	});
});
</script>
<script type="text/javascript">
var D1_btn=0;
$(document).ready(function(){
	//用于按钮状态判断
	$("#b1").click(function(){
		send(1);
		$(".btn").css("background","#171916"),
		$(".btn").css("color","#515733"),
		$("#b1").css("background","#87843B"),
		$("#b1").css("color","#FFFFFF");
		$("#judge1").val("1"); 
		if(D1_btn==0){
			$("#d1").animate({top:'+=90%'});
		}
		if(D1_btn!=1){
			$("#d"+D1_btn).animate({top:'-=90%'},function(){
				$("#d1").animate({top:'+=90%'});
			});
		}
		D1_btn=1;
	});
	$("#b2").click(function(){
		send(2);
		$(".btn").css("background","#171916"),
		$(".btn").css("color","#515733"),
		$(this).css("background","#87843B"),
		$(this).css("color","#FFFFFF");
		$("#judge1").val("2"); 
		if(D1_btn==0){
			$("#d2").animate({top:'+=90%'});
		}
		if(D1_btn!=2){
			$("#d"+D1_btn).animate({top:'-=90%'},function(){
				$("#d2").animate({top:'+=90%'});
			});
		}
		D1_btn=2;
	});
	$("#b3").click(function(){
		send(3);
		$(".btn").css("background","#171916"),
		$(".btn").css("color","#515733"),
		$(this).css("background","#87843B"),
		$(this).css("color","#FFFFFF");
		$("#judge1").val("3"); 
		if(D1_btn==0){
			$("#d3").animate({top:'+=90%'});
		}
		if(D1_btn!=3){
			$("#d"+D1_btn).animate({top:'-=90%'},function(){
				$("#d3").animate({top:'+=90%'});
			});
		}
		D1_btn=3;
	});
	$("#b4").click(function(){
		send(4);
		$(".btn").css("background","#171916"),
		$(".btn").css("color","#515733"),
		$(this).css("background","#87843B"),
		$(this).css("color","#FFFFFF");
		$("#judge1").val("4"); 
		if(D1_btn==0){
			$("#d4").animate({top:'+=90%'});
		}
		if(D1_btn!=4){
			$("#d"+D1_btn).animate({top:'-=90%'},function(){
				$("#d4").animate({top:'+=90%'});
			});
		}
		D1_btn=4;
	});
});
$(document).ready(function(){
	var menu_btn=0;
 	var judge=0;
 	function changeall(){
 		if(D1_btn!=0)
 			$("#d"+D1_btn).animate({top:'-=90%'});
 		$(".btn_broad1").animate({left:'18%'},"normal");
		$(".btn_broad1").css("z-index","-1");
		$(".btn").css("background","#171916");
		$(".btn").css("color","#515733");
		$('#bb1').fadeOut();
 		$(".btn_broad1").animate({left:'18%'},"normal");
 		D1_btn=0;
 	}
 	$("#m1").hover(
 		function(){
 			if(menu_btn!=1){
 				$("#m1 input").css("background","#869B74"),
 				$("#m1 div").css("background","#FFFFFF"),
 				$("#m1 div").css("border","1px solid #F76F60")
 			}	
 		},
 		function(){
 			if(menu_btn!=1){
 				$("#m1 input").css("background","#000000"),
 				$("#m1 div").css("background","none"),
 				$("#m1 div").css("border","none")
 			}
 		}	
 	);	
 	$("#m2").hover(
 		function(){
 			if(menu_btn!=2){
 				$("#m2 input").css("background","#869B74"),
 				$("#m2 div").css("background","#FFFFFF"),
 				$("#m2 div").css("border","1px solid #F76F60")
 			}	
 		},
 		function(){
 			if(menu_btn!=2){
 				$("#m2 input").css("background","#000000"),
 				$("#m2 div").css("background","none"),
 				$("#m2 div").css("border","none")
 			}
 		}
 	);	
 	$("#m3").hover(
 		function(){
 			if(menu_btn!=3){
 				$("#m3 input").css("background","#869B74"),
 				$("#m3 div").css("background","#FFFFFF"),
 				$("#m3 div").css("border","1px solid #F76F60")
 			}
 		},
 		function(){
 			if(menu_btn!=3){
 				$("#m3 input").css("background","#000000"),
 				$("#m3 div").css("background","none"),
 				$("#m3 div").css("border","none")
 			}
 		}	
 	);	
 	$("#m4").hover(
 		function(){
 			if(menu_btn!=4){
 				$("#m4 input").css("background","#869B74"),
 				$("#m4 div").css("background","#FFFFFF"),
 				$("#m4 div").css("border","1px solid #F76F60")
 			}
 		},
 		function(){
 			if(menu_btn!=4){
 				$("#m4 input").css("background","#000000"),
 				$("#m4 div").css("background","none"),
 				$("#m4 div").css("border","none")
 			}
 		}	
 	);	
 	$("#m5").hover(
 		function(){
 			if(menu_btn!=5){
 				$("#m5 input").css("background","#869B74"),
 				$("#m5 div").css("background","#FFFFFF"),
 				$("#m5 div").css("border","1px solid #F76F60")
 			}
 		},
 		function(){
 			if(menu_btn!=5){
				$("#m5 input").css("background","#000000"),
 				$("#m5 div").css("background","none"),
 				$("#m5 div").css("border","none")
 			}
 		}	
 	);	
 	$("#m1").click(function(){
 		changeall();
 		$(".sign").css("background","#000000"),
		$(".menu_li").css("font-size","20px"),
 		$("#m1").css("font-size","30px"),
 		$("#m1 input").css("background","#FFFFFF"),
		$(".sign2").css("background","none"),
		$(".sign2").css("border","none"),
		$("#m1 div").css("background","#F76F60"),
		$("#m1 div").css("border","1px solid #FFFFFF");
 		menu_btn=1;
 	});
 	$("#m2").click(function(){
 		$('#bb1').fadeIn();
 		$("#div1").animate({top:'-100%'});
 		$("#div2").animate({top:'-100%'});
 		$("#div3").animate({top:'-100%'});
 		$(".btn_broad1").animate({left:'12%'});
 		$(".btn_broad1").css("z-index","0");
 		menu_btn=2;
 		$(".sign").css("background","#000000"),
 		$(".menu_li").css("font-size","20px"),
 		$("#m2").css("font-size","30px"),
		$("#m2 input").css("background","#FFFFFF"),
		$(".sign2").css("background","none"),
		$(".sign2").css("border","none"),
		$("#m2 div").css("background","#F76F60"),
		$("#m2 div").css("border","1px solid #FFFFFF");
 	});
 	$("#m3").click(function(){
 		changeall();
 		$("#div2").animate({top:'-100%'},function(){
 			$("#div1").animate({top:'0'});
 		});
 		$("#div3").animate({top:'-100%'},function(){
 			$("#div1").animate({top:'0'});
 		});
 		menu_btn=3;
 		$(".sign").css("background","#000000"),
 		$(".menu_li").css("font-size","20px"),
 		$("#m3").css("font-size","30px"),
 		$("#m3 input").css("background","#FFFFFF"),
		$(".sign2").css("background","none"),
		$(".sign2").css("border","none"),
		$("#m3 div").css("background","#F76F60"),
		$("#m3 div").css("border","1px solid #FFFFFF");
 	});
 	$("#m4").click(function(){
 		changeall();
 		$("#div1").animate({top:'-100%'},function(){
 			$("#div2").animate({top:'0'});
 		});
 		$("#div3").animate({top:'-100%'},function(){
 			$("#div2").animate({top:'0'});
 		});
 		menu_btn=4;
 		$(".sign").css("background","#000000"),
 		$(".menu_li").css("font-size","20px"),
 		$("#m4").css("font-size","30px"),
 		$("#m4 input").css("background","#FFFFFF"),
		$(".sign2").css("background","none"),
		$(".sign2").css("border","none"),
		$("#m4 div").css("background","#F76F60"),
		$("#m4 div").css("border","1px solid #FFFFFF");
 	});
 	$("#m5").click(function(){
 		changeall();
 		$("#div1").animate({top:'-100%'},function(){
 			$("#div3").animate({top:'0'});
 		});
 		$("#div2").animate({top:'-100%'},function(){
 			$("#div3").animate({top:'0'});
 		});
 		menu_btn=5;
 		$(".sign").css("background","#000000"),
 		$(".menu_li").css("font-size","20px"),
 		$("#m5").css("font-size","30px"),
 		$("#m5 input").css("background","#FFFFFF"),
		$(".sign2").css("background","none"),
		$(".sign2").css("border","none"),
		$("#m5 div").css("background","#F76F60"),
		$("#m5 div").css("border","1px solid #FFFFFF");
 	});
 	var change=true;
	$("#changeclothes").click(function(){
		if(change){
			$(".dhead").attr("src","img/wallethead2.jpg");
			$(".menu_li").css("color","#F76F60");
			$(".menu_li").css("border-bottom","2px solid #F76F60");
			$(".sign").css("display","none");
			$(".sign2").css("display","block");
			change=false;
		}else{
			$(".dhead").attr("src","img/wallethead.jpg");
			$(".menu_li").css("color","#FFFFFF");
			$(".menu_li").css("border-bottom","1px solid #C3BED4");
			$(".sign").css("display","block");
			$(".sign2").css("display","none");
			change=true;
		}
	})
});
function Toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var m = document.createElement('div');
    m.innerHTML = msg;
    m.style.cssText="width: 30%;min-width: 100px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 55%;left: 24%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
}
function send(i){
	 set(i);
	 if(document.getElementById('judge1').value!=0){
		var xml =new XMLHttpRequest();
		var judge1=document.getElementById("judge1").value;
		var url="trade_check?judge1="+judge1;
		xml.open("get", url, true);
		xml.send();
		xml.onreadystatechange=function(){
			if (xml.readyState==4 && xml.status==200)
		    {
		     var s=document.getElementById('tb'+i);
		     s.scrollTop=s.scrollHeight;
		     s.innerHTML+=xml.responseText;
		    }
		};
		
	}
	else{
		Toast("不能为空",2000);
	} 
}
function set(i){
	document.getElementById('judge1').value=i;
}
function sendt(){
	var d1=document.getElementById('date1').value;
	var d2=document.getElementById('date2').value;
	if(d1>d2){
		Toast("日期错误",2000);
	
	} else {
			if (document.getElementById('judge1').value != 0) {
				var xml = new XMLHttpRequest();
				var judge1 = document.getElementById("judge1").value;
				var i=judge1;
				var url = "trade_check_time?judge1=" + judge1+"&start="+d1+"&end="+d2;
				xml.open("get", url, true);
				xml.send();
				xml.onreadystatechange = function() {
					if (xml.readyState == 4 && xml.status == 200) {
						var s = document.getElementById('tb' + i);
						s.scrollTop = s.scrollHeight;
						s.innerHTML="";
						s.innerHTML += xml.responseText;
					}
				};

			} else {
				Toast("请选择查询内容", 2000);
			}
		}
	}
function sendm(i){
	if(document.getElementById('mt'+i).value!=0){
		var xml =new XMLHttpRequest();
		var judge1=document.getElementById('mt'+i).value;
		if(i==1)
		    var url="balance_adding?money="+document.getElementById('mt'+i).value;
		else
			var url="balance_m?num="+document.getElementById('mt'+i).value;
		xml.open("get", url, true);
		xml.send();
		xml.onreadystatechange=function(){
			if (xml.readyState==4 && xml.status==200)
		    {
		    Toast(xml.responseText,2000);
		    setTimeout(function(){window.location.reload();},1200);
		    }
		};		
	}
	else{
		Toast("金额不能为空",2000);
	} 
	document.getElementById('mt'+i).value="";
}
</script>
</head>
<body style="background-color:black;">
<img class="dhead" src="img/wallethead.jpg">

<div class="btn_broad1" id="btn">
<form>
    <input type="hidden" name="judge1" id="judge1" value=0>
	<input class="btn" id="b1" type="button" value="所有记录" style="border-radius:12px 0 0 0;">
	<input class="btn" id="b2" type="button" value="红包记录">
	<input class="btn" id="b3" type="button" value="充值提现记录">
	<input class="btn" id="b4" type="button" value="打赏记录" style="border-radius:0 0 0 12px;">
	</form>
</div>
<div class="D">
	<img alt="" src="img/dbackground.jpg" style="height:100%;width:100%;z-index:-1;">
	
	<div class="D2" id="div1" style="background-color:#FF4124;">
		<input type="number" placeholder="请输入itcode" id="mt3" style="top:30%;">
		<input type="number" placeholder="请输入红包金额" id="mt4">
		<input type="button" value="发红包" onclick="sedred()">
	</div>
	<div class="D2" id="div2" style="background-color:#FF7073;">
		<span>充值金额:</span>
		<input type="text" placeholder="请输入充值金额" id="mt1">
		<input type="button" value="确认充值" onclick="sendm(1)">
	</div>
	<div class="D2" id="div3" style="background-color:#4FB3A4;">
		<span>提现金额:</span>
		<input type="text" placeholder="请输入提现金额" id="mt2">
		<input type="button" value="确认提现" onclick="sendm(2)">
	</div>
	
	<div id="d1" class="D1">
		<table class="tab" id="t1" border=1>
			<thead style="text-align:center;"><tr><td width=50px>序号</td><td width=370px>所有记录</td><td width=100px>金额</td><td width=180px>时间</td></tr></thead>
			<tbody id="tb1">
			</tbody>
		</table>
	</div>
	<div id="d2" class="D1">
		<table class="tab" id="t2" border=1>
			<thead style="text-align:center;"><tr><td width=50px>序号</td><td width=370px>红包记录</td><td width=100px>金额</td><td width=180px>时间</td></tr></thead>
			<tbody id="tb2">
			</tbody>
		</table>
	</div>
	<div id="d3" class="D1">
		<table class="tab" id="t3" border=1>
			<thead style="text-align:center;"><tr><td width=50px>序号</td><td width=370px>充值提现记录</td><td width=100px>金额</td><td width=180px>时间</td></tr></thead>
			<tbody id="tb3">
			</tbody>
		</table>
	</div>
	<div id="d4" class="D1">
		<table class="tab" id="t4" border=1>
			<thead style="text-align:center;"><tr><td width=50px>序号</td><td width=370px>打赏记录</td><td width=100px>金额</td><td width=180px>时间</td></tr></thead>
			<tbody id="tb4">
			</tbody>
		</table>
	</div>
	<div id="bb1" class="btn_broad2">
		<input id="date1" class="date" type="date" value="2017-08-01" min="2017-08-01"/>
		<input id="date2" class="date" type="date" min="2017-08-01"/>
		<input type="button" class="btn2" value="查询" onclick="sendt()">
	</div>
</div>
<img src="${face}" width=10% height=10% style="position:absolute;top:15%;left:80%;border:1px solid #FFFFFF;">
<div class="menu">
	<div class="menu_li" id="m1"><div class="sign2"></div><input type="button" class="sign" id="s1"><span>余额:${money}</span></div>
	<div class="menu_li" id="m2"><div class="sign2"></div><input type="button" class="sign" id="s2"><span>查看记录</span></div>
	<div class="menu_li" id="m3"><div class="sign2"></div><input type="button" class="sign" id="s3"><span>发红包</span></div>
	<div class="menu_li" id="m4"><div class="sign2"></div><input type="button" class="sign" id="s4"><span>充值</span></div>
	<div class="menu_li" id="m5"><div class="sign2"></div><input type="button" class="sign" id="s5"><span>提现</span></div>
</div>

<div class="dropdown">
	<button class="dropbtn">菜单</button>
	<div class="content">
		<a href="home"><div><img src="img/homepage.png"><span>返回</span></div></a>
		<a  id="changeclothes" href="javascript:"><div><img src="img/changeclothes.png"><span>换装</span></div></a>
	</div>
</div>
<script type="text/javascript">
function sedred(){
	var seitcode=document.getElementById("mt3").value;
	var money=document.getElementById("mt4").value;
	if(seitcode.length==0||money.length==0){
		Toast("itcode和金额不能为空",2000);
	}else{
		var bmoney=${money};
		if(money>bmoney){
			Toast("用户余额不足，不能充值",2000);
		}else{
		var xml =new XMLHttpRequest();
	    var url="sendpersonluck?itcode="+seitcode+"&money="+money;

		xml.open("get", url, true);
		xml.send();
		xml.onreadystatechange=function(){
			if (xml.readyState==4 && xml.status==200)
		    {
				//-1 没有该用户  -2该用户钱包未激活 -3充值失败   返回充值信息，成功
				var res=xml.responseText;
				if(res=="-1"){
					Toast("该用户不存在",2000);	
				}else if(res=="-2"){
					Toast("该用户钱包未激活",2000);
				}else if(res=="-3"){
					Toast("充值失败",2000);
				}else{
					Toast(res,2000);
					setTimeout(function(){window.location.reload();},2000);
				}
		    }
		};		
	}
	
	}
}
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});
document.getElementById("date2").value=new Date().toDateInputValue();

</script>
<div class="box" >
	<div class="demo" id="perinfo"  style="margin-top:8%;text-align:center;text-shadow: 1px 1px 1px #000; color: #FFFFFF;">
		<a href="javascript:;" class="rollIn"></a>
	</div>
	<div id="dialogBg"></div>
	<div id="dialog" class="animated" style="margin-right: 200px; height:auto;background-image: url(img/33.png);">
		<img class="dialogIco" width="50" height="50" src="img/ico.png"
			alt="" />
		<div class="dialogTop">
			<a href="javascript:;" class="claseDialogBtn"></a>
		</div>
		<form id="editForm">
			<ul class="editInfos">
				<li><label style="color:#fff">账&nbsp;&nbsp;&nbsp;&nbsp;号：<input
						id="itcode" type="text" name="" value="${itcode}" class="ipt" /></label></li>
				<li><label style="font-size:20px;color:#fff"><img src='img/error.png' width='20' height='20'>您的钱包尚未激活!</label></li>
				<li><input type="button" id="submit" value="马上激活" class="submitBtn" />    <a href="home" ><input type="button" id="subbuck" value="返回" class="submitBtn" /></a></li>
			</ul>
		</form>
	</div> 
</div>
</body>
</html>