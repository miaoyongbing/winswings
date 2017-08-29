<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
<link rel="stylesheet" href="css/common.css" />
<link href="css/bootstrap.min.css" ref="stylesheet">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<style type="text/css">

.Body{
	position:absolute;
	width:95%;
	height:95%;
	background: url(img/adminback.jpg);<!--url(img/33.png)-->

	left:20px;
	border-radius:100px; 
	border:3px solid #fff;
	box-shadow: 0px 9px 0px #540000, 0px 9px 25px #540000;
}
.broad{
	position:absolute;
	left:20%;
	width:60%;
	height:100%;
	background-color:#544946;
}
.broadon{
	position:absolute;
	top:5%;
	left:2%;
	width:96%;
	height:90%;
	background-color:#000000;
}
.date{
    position:absolute;
	font-family:LiSu;
	height:30px;
	font-size:15px;
}
.broadon-out{
	position:absolute;
	top:3%;
	left:2%;
	width:96%;
	height:94%;
	overflow:hidden;
}
.broadon-inner{
	position:absolute;
	left:0px;
	width:100%;
	height:100%;
	
	overflow-x:hidden;
	overflow-y:scroll;
}
button{
	position:absolute;
	border-radius:50%;
	outline:none;
	background-color:#fff;
	border:none;
	color:#FFFFFF;
	font-size:16px;
	font-family:华文行魏;
	text-shadow:1px 1px 1px #000;
	box-shadow: 0px 9px 0px #48414A, 0px 9px 25px #48414A;
}
.btn1{
	top:200px;
	left:100px;
	height:70px;
	width:70px;
}
.btn1:active{
	top:206px;
}
.btn2{
	top:260px;
	left:40px;
	height:70px;
	width:70px;
}
.btn2:active{
	top:266px;
}
.btn3{
	top:320px;
	left:100px;
	height:70px;
	width:70px;
}
.btn3:active{
	top:326px;
}
.btn4{
	top:260px;
	left:160px;
	height:70px;
	width:70px;
}
.btn4:active{
	top:266px;
}
.btn5{
	top:200px;
	left:85%;
	height:150px;
	width:150px;
}
.btn5:active{
	top:206px;
}
.btn6{
	top:400px;
	left:90%;
	height:70px;
	width:70px;
}
.btn6:active{
	top:406px;
}
.btn7{
	top:80px;
	left:90%;
	height:70px;
	width:70px;
}
.btn7:active{
	top:86px;
}
button:active{
	box-shadow: 0px 3px 0px rgba(72,65,74,1), 0px 3px 6px rgba(72,65,74,.9);
}
.broadText{
	width:200px;
	height:20px;
	position:absolute;
	left:50%;
	margin-left:-100px;
	text-align:center;
	line-height:20px;
	font-size:15px;
	color:#FFFFFF;
	font-family:华文新魏;
}
.btnbroad-b{
	height:40px;
	width:180px;
	background-color:#376956;
	border:1px solid #FFFFFF;
	float:left;
	outline:none;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
.btnbroad-b2{
	height:40px;
	width:360px;
	background-color:#376956;
	border:1px solid #FFFFFF;
	float:left;
	outline:none;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
.btnbroad-b3{
	height:40px;
	width:360px;
	background-color:#376956;
	border:1px solid #FFFFFF;
	float:left;
	outline:none;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
.btnbroad-b4{
	height:40px;
	width:360px;
	background-color:#376956;
	border:1px solid #FFFFFF;
	float:left;
	outline:none;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
.btnbroad-b5{
	height:40px;
	width:360px;
	background-color:#376956;
	border:1px solid #FFFFFF;
	float:left;
	outline:none;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
select,input[type=text]{
	position:absolute;
	height:30px;
	width:200px;
	border-radius:8px;
	border:1px solid #9c9ea1;
	outline:none;
}
.Text{
	position:absolute;
	color:#FFFFFF;
	text-shadow:1px 1px 1px #000;
}
.innerbtn{
	position:absolute;
	width:100px;
	height:40px;
	border-radius:8px;
	border:1px solid #9c9ea1;
}
table{
	position:absolute;
	width:720px;
	left:0;
	top:0;
	text-align:center;
	border:1px solid #FFFFFF;
	display:none;
}
table td{
	border:1px solid #000000;

}
</style>

<script type="text/javascript">
var s=0;
$(document).ready(function(){
	var colorstyle=new Array("#81FF38","#E4FF39","#FF7438","#FF4238","#AD9876","#7C8489","#4FB3A4","#FF7073","#F5B977","#FDFC7F","#FF9900","#A6325A","#48A7C2","#206FA1","#B1C914");
	var broad=7;
	var d1=0;
	var d2=1;
	var d3=1;
	var d4=1;
	var d5=1;
	$("#b1").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
		if(broad!=1){
			d1=1;
			$("#D1").css("top","-100%");
			$("#D"+broad).animate({"top":"+=100%"});
			$("#D1").animate({"top":"+=100%"});
			broad=1;
		}
	});
	$("#b2").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
		if(broad!=2){
			d1=0;
			$("#D2").css("top","-100%");
			$("#D"+broad).animate({"top":"+=100%"});
			$("#D2").animate({"top":"+=100%"});
			broad=2;
		}
		
	});
	$("#b3").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
		if(broad!=3){
			d1=0;
			$("#D3").css("top","-100%");
			$("#D"+broad).animate({"top":"+=100%"});
			$("#D3").animate({"top":"+=100%"});
			broad=3;
		}
		if(d3==1)
			sendd3(0);
	});
	$("#b4").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
		if(broad!=4){
			d1=0;
			$("#D4").css("top","-100%");
			$("#D"+broad).animate({"top":"+=100%"});
			$("#D4").animate({"top":"+=100%"});
			broad=4;
		}
	});
	$("#b5").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
		if(broad!=5){
			d1=0;
			$("#D5").css("top","-100%");
			$("#D"+broad).animate({"top":"+=100%"});
			$("#D5").animate({"top":"+=100%"});
			broad=5;
		}
	});
	$("#b6").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
	});
	$("#b7").click(function(){
		var r=Math.floor(Math.random()*15);
		$(this).css("background",colorstyle[r]);
		if(broad!=7){
			d1=0;
			$("#D7").css("top","-100%");
			$("#D"+broad).animate({"top":"+=100%"});
			$("#D7").animate({"top":"+=100%"});
			broad=7;
		}
	});
	

	$("#btnbroad-b1").css("background","#C8E9A0"),
	$("#btnbroad-b1").css("border","none");
	$("#btnbroad-b1").click(function(){
		$(".btnbroad-b").css("background","#376956"),
		$(".btnbroad-b").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d1!=1&&d1!=0){
			$("#d1").css("left","720px"),
			$("#d1").animate({left:'-=720px'});
			$("#d"+d1).animate({left:'-=740px'});
			d1=1;
		}
	});
	$("#btnbroad-b2").click(function(){
		$(".btnbroad-b").css("background","#376956"),
		$(".btnbroad-b").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d1!=2&&d1!=0){
			$("#d2").css("left","720px"),
			$("#d2").animate({left:'-=720px'});
			$("#d"+d1).animate({left:'-=740px'});
			d1=2;
		}
	});
	$("#btnbroad-b3").click(function(){
		$(".btnbroad-b").css("background","#376956"),
		$(".btnbroad-b").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d1!=3&&d1!=0){
			$("#d3").css("left","720px"),
			$("#d3").animate({left:'-=720px'});
			$("#d"+d1).animate({left:'-=740px'});
			d1=3;
		}
	});
	$("#btnbroad-b4").click(function(){
		get(7);
		$(".btnbroad-b").css("background","#376956"),
		$(".btnbroad-b").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d1!=4&&d1!=0){
			$("#d4").css("left","720px"),
			$("#d4").animate({left:'-=720px'});
			$("#d"+d1).animate({left:'-=740px'});
			d1=4;
		}
	});
	$("#btnbroad2-b1").css("background","#C8E9A0"),
	$("#btnbroad2-b1").css("border","none");
	$("#btnbroad2-b1").click(function(){
		$(".btnbroad-b2").css("background","#376956"),
		$(".btnbroad-b2").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d2!=1){
			$("#d21").css("left","720px"),
			$("#d21").animate({left:'-=720px'});
			$("#d2"+d2).animate({left:'-=740px'});
			d2=1;
		}
	});
	$("#btnbroad2-b2").click(function(){
		sendd2(2);
		$(".btnbroad-b2").css("background","#376956"),
		$(".btnbroad-b2").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d2!=2){
			$("#d22").css("left","720px"),
			$("#d22").animate({left:'-=720px'});
			$("#d2"+d2).animate({left:'-=740px'});
			d2=2;
		}
	});
	$("#btnbroad3-b1").css("background","#C8E9A0"),
	$("#btnbroad3-b1").css("border","none");
	$("#btnbroad3-b1").click(function(){
		$(".btnbroad-b3").css("background","#376956"),
		$(".btnbroad-b3").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		sendd3(0);
		if(d3!=1){
			$("#d31").css("left","720px"),
			$("#d31").animate({left:'-=720px'});
			$("#d3"+d3).animate({left:'-=740px'});
			d3=1;
		}
	});
	$("#btnbroad3-b2").click(function(){
		$(".btnbroad-b3").css("background","#376956"),
		$(".btnbroad-b3").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d3!=2){
			$("#d32").css("left","720px"),
			$("#d32").animate({left:'-=720px'});
			$("#d3"+d3).animate({left:'-=740px'});
			d3=2;
		}
	});
	$("#btnbroad4-b1").css("background","#C8E9A0"),
	$("#btnbroad4-b1").css("border","none");
	$("#btnbroad4-b1").click(function(){
		$("#d4j").val("1");
		$(".btnbroad-b4").css("background","#376956"),
		$(".btnbroad-b4").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d4!=1){
			$("#d41").css("left","720px"),
			$("#d41").animate({left:'-=720px'});
			$("#d4"+d4).animate({left:'-=740px'});
			d4=1;
		}
	});
	$("#btnbroad4-b2").click(function(){
		$("#d4j").val("2");
		$(".btnbroad-b4").css("background","#376956"),
		$(".btnbroad-b4").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d4!=2){
			$("#d42").css("left","720px"),
			$("#d42").animate({left:'-=720px'});
			$("#d4"+d4).animate({left:'-=740px'});
			d4=2;
		}
	});
	$("#btnbroad4-b3").click(function(){
		$("#d4j").val("3");
		$(".btnbroad-b4").css("background","#376956"),
		$(".btnbroad-b4").css("border","1px solid #FFFFFF"),
		$(this).css("background","#C8E9A0"),
		$(this).css("border","none");
		if(d4!=3){
			$("#d43").css("left","720px"),
			$("#d43").animate({left:'-=720px'});
			$("#d4"+d4).animate({left:'-=740px'});
			d4=3;
		}
	});
	$("#btnbroad5-b1").css("background","#C8E9A0"),
	$("#btnbroad5-b1").css("border","none");
	$('#d1-t1').change(function () {
		var t=this.value;
		switch(t){
		case '所有':
			if(($('#d1-btn').position().top)>100){
				$('#d1-t2').css('display','none');
				$('#d1-t3').css('display','none');
				$('#d1-t4').css('display','none');
				$('#d1-t5').css('display','none');
				$('#d1-text2').css('display','none');
				$('#d1-text3').css('display','none');
				$('#d1-text4').css('display','none');
				$('#d1-text5').css('display','none');
				$('#d1-btn').animate({top:'100px'});
			}else{ 
				$('#d1-btn').animate({top:'100px'},function(){
					$('#d1-t2').css('display','none');
					$('#d1-t3').css('display','none');
					$('#d1-t4').css('display','none');
					$('#d1-t5').css('display','none');
					$('#d1-text2').css('display','none');
					$('#d1-text3').css('display','none');
					$('#d1-text4').css('display','none');
					$('#d1-text5').css('display','none');
				});
			}
			break;
		case 'itcode':
			if(($('#d1-btn').position().top)>150){
				$('#d1-t2').css('display','none');
				$('#d1-t3').css('display','none');
				$('#d1-t4').css('display','block');
				$('#d1-t5').css('display','none');
				$('#d1-text2').css('display','none');
				$('#d1-text3').css('display','none');
				$('#d1-text4').css('display','block');
				$('#d1-text5').css('display','none');
				$('#d1-btn').animate({top:'150px'});
			}else{
				$('#d1-btn').animate({top:'150px'},function(){
					$('#d1-t2').css('display','none');
					$('#d1-t3').css('display','none');
					$('#d1-t4').css('display','block');
					$('#d1-t5').css('display','none');
					$('#d1-text2').css('display','none');
					$('#d1-text3').css('display','none');
					$('#d1-text4').css('display','block');
					$('#d1-text5').css('display','none');
				});
			}
			break;
		case '用户名':
			if(($('#d1-btn').position().top)>150){
				$('#d1-t2').css('display','none');
				$('#d1-t3').css('display','none');
				$('#d1-t4').css('display','none');
				$('#d1-t5').css('display','block');
				$('#d1-text2').css('display','none');
				$('#d1-text3').css('display','none');
				$('#d1-text4').css('display','none');
				$('#d1-text5').css('display','block');
				$('#d1-btn').animate({top:'150px'});
			}else{
				$('#d1-btn').animate({top:'150px'},function(){
					$('#d1-t2').css('display','none');
					$('#d1-t3').css('display','none');
					$('#d1-t4').css('display','none');
					$('#d1-t5').css('display','block');
					$('#d1-text2').css('display','none');
					$('#d1-text3').css('display','none');
					$('#d1-text4').css('display','none');
					$('#d1-text5').css('display','block');
				});
			}
			break;
		case '余额':
			$('#d1-btn').animate({top:'200px'},function(){
				$('#d1-t2').css('display','block');
				$('#d1-t3').css('display','block');
				$('#d1-t4').css('display','none');
				$('#d1-t5').css('display','none');
				$('#d1-text2').css('display','block');
				$('#d1-text3').css('display','block');
				$('#d1-text4').css('display','none');
				$('#d1-text5').css('display','none');
			});
			break;
		}
	});
	d3judge=0;
	$('#d3-t2').change(function () {
		var t=this.value;
		switch(t){
		case "itcode":
			$('#d3-t7').val('1');
			if(d3judge==0){
				$('#d3-t5').animate({top:'+=50px'});
				$('#d3-text5').animate({top:'+=50px'});
			    $('#d3-btn').animate({top:'250px'},function(){
				    $('#d3-t3').css('display','block');
				    $('#d3-text3').css('display','block');
		    	});
			    d3judge=1;
			}else if(d3judge==2){
            	$('#d3-t5').animate({top:'+=50px'});
				$('#d3-text5').animate({top:'+=50px'});
				$('#d3-t6').animate({top:'+=50px'});
				$('#d3-text6').animate({top:'+=50px'});
			    $('#d3-btn').animate({top:'300px'},function(){
				    $('#d3-t3').css('display','block');
				    $('#d3-text3').css('display','block');
		    	});
			    d3judge=3;
			}else{
				$('#d3-t4').css('display','none');
			    $('#d3-text4').css('display','none');
				$('#d3-t3').css('display','block');
			    $('#d3-text3').css('display','block');
			}
			break;
		case "用户名":
			$('#d3-t7').val('2');
			if(d3judge==0){
				$('#d3-t5').animate({top:'+=50px'});
				$('#d3-text5').animate({top:'+=50px'});
			    $('#d3-btn').animate({top:'250px'},function(){
				    $('#d3-t4').css('display','block');
				    $('#d3-text4').css('display','block');
		    	});
			    d3judge=1;
			}else if(d3judge==2){
            	$('#d3-t5').animate({top:'+=50px'});
				$('#d3-text5').animate({top:'+=50px'});
				$('#d3-t6').animate({top:'+=50px'});
				$('#d3-text6').animate({top:'+=50px'});
			    $('#d3-btn').animate({top:'300px'},function(){
				    $('#d3-t4').css('display','block');
				    $('#d3-text4').css('display','block');
		    	});
			    d3judge=3;
			}else{
				$('#d3-t3').css('display','none');
			    $('#d3-text3').css('display','none');
				$('#d3-t4').css('display','block');
			    $('#d3-text4').css('display','block');
			}
			break;
		case "所有":
			$('#d3-t7').val('0');
			if(d3judge==1){
				$('#d3-t3').css('display','none');
			    $('#d3-text3').css('display','none');
			    $('#d3-t4').css('display','none');
			    $('#d3-text4').css('display','none');
			    $('#d3-btn').animate({top:'200px'});
		    	$('#d3-t5').animate({top:'-=50px'});
				$('#d3-text5').animate({top:'-=50px'});
			    d3judge=0;
			}else if(d3judge==3){
		    	$('#d3-t3').css('display','none');
			    $('#d3-text3').css('display','none');
			    $('#d3-t4').css('display','none');
			    $('#d3-text4').css('display','none');
            	$('#d3-t5').animate({top:'-=50px'});
				$('#d3-text5').animate({top:'-=50px'});
				$('#d3-t6').animate({top:'-=50px'});
				$('#d3-text6').animate({top:'-=50px'});
			    $('#d3-btn').animate({top:'250px'});
			    d3judge=2;
			}
			break;
		}
		});
	$('#d3-t5').change(function () {
		var t=this.value;
		switch(t){
		case "大于":
			$('#d3-t8').val('1');
			if(d3judge==0){
			    $('#d3-btn').animate({top:'+=50px'},function(){
				    $('#d3-t6').css('display','block');
				    $('#d3-text6').css('display','block');
		    	});
			    d3judge=2;
			}else if(d3judge==1){
				$('#d3-btn').animate({top:'+=50px'},function(){
					$('#d3-t6').css('top','+=50px');
				    $('#d3-text6').css('top','+=50px');
				    $('#d3-t6').css('display','block');
				    $('#d3-text6').css('display','block');
		    	});
			    d3judge=3;
			}
			break;
		case "等于":
			$('#d3-t8').val('2');
			if(d3judge==0){
			    $('#d3-btn').animate({top:'+=50px'},function(){
				    $('#d3-t6').css('display','block');
				    $('#d3-text6').css('display','block');
		    	});
			    d3judge=2;
			}else if(d3judge==1){
				$('#d3-btn').animate({top:'+=50px'},function(){
					$('#d3-t6').css('top','+=50px');
				    $('#d3-text6').css('top','+=50px');
				    $('#d3-t6').css('display','block');
				    $('#d3-text6').css('display','block');
		    	});
			    d3judge=3;
			}
			break;
		case "小于":
			$('#d3-t8').val('3');
			if(d3judge==0){
			    $('#d3-btn').animate({top:'+=50px'},function(){
				    $('#d3-t6').css('display','block');
				    $('#d3-text6').css('display','block');
		    	});
			    d3judge=2;
			}else if(d3judge==1){
				$('#d3-btn').animate({top:'+=50px'},function(){
					$('#d3-t6').css('top','+=50px');
				    $('#d3-text6').css('top','+=50px');
				    $('#d3-t6').css('display','block');
				    $('#d3-text6').css('display','block');
		    	});
			    d3judge=3;
			}
			break;
		case "所有":
			$('#d3-t8').val('0');
			if(d3judge==2){
			    $('#d3-t6').css('display','none');
			    $('#d3-text6').css('display','none');
			    $('#d3-btn').animate({top:'-=50px'});
			    d3judge=0;
			}else if(d3judge==3){
				$('#d3-t6').css('top','-=50px');
			    $('#d3-text6').css('top','-=50px');
				$('#d3-t6').css('display','none');
			    $('#d3-text6').css('display','none');
				$('#d3-btn').animate({top:'-=50px'});
			    d3judge=1;
			}
			break;
		}
		});
	$('#d4-t11').change(function () {
		var t=this.value;
		switch(t){
		case "查询":
			$('#d4-t16').val("1");		
			if(($('#d4-btn1').position().top)>100){
				$('#d4-t13').css('display','none');
			    $('#d4-text13').css('display','none');
			    $('#d4-t14').css('display','none');
			    $('#d4-text14').css('display','none');
			    $('#d4-t12').css('display','block');
			    $('#d4-text12').css('display','block');
			    $('#d4-btn1').animate({top:'-=50px'});
			    switch($('#d4-t12').val()){
			    case "所有":
					$('#d4-t15').val('0');
					break;
				case "第一轮":
					$('#d4-t15').val('1');
					break;
				case "第二轮":
					$('#d4-t15').val('2');
					break;
				case "第三轮":
					$('#d4-t15').val('3');
					break;
			    }
			}
			break;
		case "充值":
			$('#d4-t16').val("2");
			 switch($('#d4-t13').val()){
				case "第一轮":
					$('#d4-t15').val('1');
					break;
				case "第二轮":
					$('#d4-t15').val('2');
					break;
				case "第三轮":
					$('#d4-t15').val('3');
					break;
			    }
			if(($('#d4-btn1').position().top)<=150){
			    $('#d4-t12').css('display','none');
			    $('#d4-text12').css('display','none');
			    $('#d4-btn1').animate({top:'+=50px'},function(){
			    	$('#d4-t13').css('display','block');
				    $('#d4-text13').css('display','block');
				    $('#d4-t14').css('display','block');
				    $('#d4-text14').css('display','block');
			    });
			}
			else if(($('#d4-btn1').position().top)>150){
			    	$('#d4-t13').css('display','block');
				    $('#d4-text14').css('display','block');
				    $('#d4-t14').css('display','block');
				    $('#d4-text14').css('display','block');
			}
			break;
		case "提现":
			$('#d4-t16').val("3");
			switch($('#d4-t13').val()){
			case "第一轮":
				$('#d4-t15').val('1');
				break;
			case "第二轮":
				$('#d4-t15').val('2');
				break;
			case "第三轮":
				$('#d4-t15').val('3');
				break;
		    }
			if(($('#d4-btn1').position().top)<=150){
			    $('#d4-t12').css('display','none');
			    $('#d4-text12').css('display','none');
			    $('#d4-btn1').animate({top:'+=50px'},function(){
			    	$('#d4-t13').css('display','block');
				    $('#d4-text13').css('display','block');
				    $('#d4-t14').css('display','block');
				    $('#d4-text14').css('display','block');
			    });
			}
			else if(($('#d4-btn1').position().top)>150){
			    	$('#d4-t13').css('display','block');
				    $('#d4-text14').css('display','block');
				    $('#d4-t14').css('display','block');
				    $('#d4-text14').css('display','block');
			}
			break;
		}
		});
	$('#d4-t12').change(function () {
		var t=this.value;
		switch(t){
		case "所有":
			$('#d4-t15').val('0');
			break;
		case "第一轮":
			$('#d4-t15').val('1');
			break;
		case "第二轮":
			$('#d4-t15').val('2');
			break;
		case "第三轮":
			$('#d4-t15').val('3');
			break;
		}
		});
	$('#d4-t13').change(function () {
		var t=this.value;
		switch(t){
		    case "第一轮":
			    $('#d4-t15').val('1');
			    break;
	    	case "第二轮":
		    	$('#d4-t15').val('2');
	    		break;
	    	case "第三轮":
	    		$('#d4-t15').val('3');
	    		break;
		}
		});
	$('#d4-t21').change(function () {
		var t=this.value;
		switch(t){
		case "查询":
			if($('#d4-t22').val()!="红包id"){
				$('#d4-t23').css('display','none');
			    $('#d4-text23').css('display','none');
			    $('#d4-t24').css('display','none');
			    $('#d4-text24').css('display','none');
			    $('#d4-t22').css('display','block');
			    $('#d4-text22').css('display','block');
			    $('#d4-btn2').animate({top:'-=50px'});
			}
			else{
				$('#d4-t23').css('top','+=50px');
			    $('#d4-text23').css('top','+=50px');
			    $('#d4-t24').css('display','none');
			    $('#d4-text24').css('display','none');
			    $('#d4-t22').css('display','block');
			    $('#d4-text22').css('display','block');
			}
			break;
		case "充值":
			if(($('#d4-btn2').position().top)<200){
				$('#d4-t22').css('display','none');
			    $('#d4-text22').css('display','none');
			    $('#d4-btn2').animate({top:'+=50px'},function(){
			    	$('#d4-t23').css('display','block');
				    $('#d4-text23').css('display','block');
				    $('#d4-t24').css('display','block');
				    $('#d4-text24').css('display','block');
			    });
			}
			else{
				$('#d4-t23').css('top','100px');
			    $('#d4-text23').css('top','100px');
				$('#d4-t22').css('display','none');
			    $('#d4-text22').css('display','none');
			    $('#d4-t23').css('display','block');
			    $('#d4-text23').css('display','block');
			    $('#d4-t24').css('display','block');
			    $('#d4-text24').css('display','block');
			}
			break;
		case "提现":
			if(($('#d4-btn2').position().top)<200){
				$('#d4-t22').css('display','none');
			    $('#d4-text22').css('display','none');
			    $('#d4-btn2').animate({top:'+=50px'},function(){
			    	$('#d4-t23').css('display','block');
				    $('#d4-text23').css('display','block');
				    $('#d4-t24').css('display','block');
				    $('#d4-text24').css('display','block');
			    });
			}
			else{
				$('#d4-t23').css('top','100px');
			    $('#d4-text23').css('top','100px');
				$('#d4-t22').css('display','none');
			    $('#d4-text22').css('display','none');
			    $('#d4-t23').css('display','block');
			    $('#d4-text23').css('display','block');
			    $('#d4-t24').css('display','block');
			    $('#d4-text24').css('display','block');
			}
			break;
		}
		});
	$('#d4-t22').change(function () {
		var t=this.value;
		switch(t){
		case "所有":
			$('#d4-t23').css('display','none');
		    $('#d4-text23').css('display','none');
		    $('#d4-t23').css('top','-=50px');
		    $('#d4-text23').css('top','-=50px');
			$('#d4-btn2').animate({top:'-=50px'});
			break;
		case "红包id":
			$('#d4-btn2').animate({top:'+=50px'},function(){
				$('#d4-t23').css('top','+=50px');
			    $('#d4-text23').css('top','+=50px');
				$('#d4-t23').css('display','block');
			    $('#d4-text23').css('display','block');
			});
			break;
			}
	});
	$('#d4-t31').change(function () {
		var t=this.value;
		switch(t){
		case "查询":
			if($('#d4-t32').val()!="节目id"){
				$('#d4-t33').css('display','none');
			    $('#d4-text33').css('display','none');
			    $('#d4-t34').css('display','none');
			    $('#d4-text34').css('display','none');
			    $('#d4-t32').css('display','block');
			    $('#d4-text32').css('display','block');
			    $('#d4-btn3').animate({top:'-=50px'});
			}
			else{
				$('#d4-t33').css('top','+=50px');
			    $('#d4-text33').css('top','+=50px');
			    $('#d4-t34').css('display','none');
			    $('#d4-text34').css('display','none');
			    $('#d4-t32').css('display','block');
			    $('#d4-text32').css('display','block');
			}
			break;
		case "充值":
			if(($('#d4-btn3').position().top)<200){
				$('#d4-t32').css('display','none');
			    $('#d4-text32').css('display','none');
			    $('#d4-btn3').animate({top:'+=50px'},function(){
			    	$('#d4-t33').css('display','block');
				    $('#d4-text33').css('display','block');
				    $('#d4-t34').css('display','block');
				    $('#d4-text34').css('display','block');
			    });
			}
			else{
				$('#d4-t33').css('top','100px');
			    $('#d4-text33').css('top','100px');
				$('#d4-t32').css('display','none');
			    $('#d4-text32').css('display','none');
			    $('#d4-t33').css('display','block');
			    $('#d4-text33').css('display','block');
			    $('#d4-t34').css('display','block');
			    $('#d4-text34').css('display','block');
			}
			break;
		case "提现":
			if(($('#d4-btn3').position().top)<200){
				$('#d4-t32').css('display','none');
			    $('#d4-text32').css('display','none');
			    $('#d4-btn3').animate({top:'+=50px'},function(){
			    	$('#d4-t33').css('display','block');
				    $('#d4-text33').css('display','block');
				    $('#d4-t34').css('display','block');
				    $('#d4-text34').css('display','block');
			    });
			}
			else{
				$('#d4-t33').css('top','100px');
			    $('#d4-text33').css('top','100px');
				$('#d4-t32').css('display','none');
			    $('#d4-text32').css('display','none');
			    $('#d4-t33').css('display','block');
			    $('#d4-text33').css('display','block');
			    $('#d4-t34').css('display','block');
			    $('#d4-text34').css('display','block');
			}
			break;
		}
		});
	$('#d4-t32').change(function () {
		var t=this.value;
		switch(t){
		case "所有":
			$('#d4-t33').css('display','none');
		    $('#d4-text33').css('display','none');
		    $('#d4-t33').css('top','-=50px');
		    $('#d4-text33').css('top','-=50px');
			$('#d4-btn3').animate({top:'-=50px'});
			break;
		case "节目id":
			$('#d4-btn3').animate({top:'+=50px'},function(){
				$('#d4-t33').css('top','+=50px');
			    $('#d4-text33').css('top','+=50px');
				$('#d4-t33').css('display','block');
			    $('#d4-text33').css('display','block');
			});
			break;
			}
	});
	var d5judge1=0;
	$('#d5-t11').change(function () {
		var t=this.value;
		switch(t){
		case "所有":
			$('#d5-t12').css('display','none');
		    $('#d5-text12').css('display','none');
		    $('#d5-t13').css('top','-=40px');
		    $('#d5-text13').css('top','-=40px');
		    $('#d5-t14').css('top','-=40px');
		    $('#d5-text14').css('top','-=40px');
		    $('#d5-t15').css('top','-=40px');
		    $('#d5-text15').css('top','-=40px');
		    $('#d5-t16').css('top','-=40px');
		    $('#d5-text16').css('top','-=40px');
		    $('#d5-t17').css('top','-=40px');
		    $('#d5-text17').css('top','-=40px');
		    $('#d5-t18').css('top','-=40px');
		    $('#d5-text18').css('top','-=40px');
			$('#d5-btn1').animate({top:'-=40px'});
			d5judge1=0;
			break;
		case "根据itcode":
			$('#d5-text12').text("itcode");
			if(d5judge1==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t12').css('display','block');
				    $('#d5-text12').css('display','block');
				    $('#d5-t13').css('top','+=40px');
				    $('#d5-text13').css('top','+=40px');
				    $('#d5-t14').css('top','+=40px');
				    $('#d5-text14').css('top','+=40px');
				    $('#d5-t15').css('top','+=40px');
				    $('#d5-text15').css('top','+=40px');
				    $('#d5-t16').css('top','+=40px');
				    $('#d5-text16').css('top','+=40px');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge1=1;
			break;
		case "根据用户名":
			$('#d5-text12').text("用户名:");
			if(d5judge1==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t12').css('display','block');
				    $('#d5-text12').css('display','block');
				    $('#d5-t13').css('top','+=40px');
				    $('#d5-text13').css('top','+=40px');
				    $('#d5-t14').css('top','+=40px');
				    $('#d5-text14').css('top','+=40px');
				    $('#d5-t15').css('top','+=40px');
				    $('#d5-text15').css('top','+=40px');
				    $('#d5-t16').css('top','+=40px');
				    $('#d5-text16').css('top','+=40px');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge1=1;
			break;
		}
		});
	var d5judge2=0;
	$('#d5-t13').change(function () {
		var t=this.value;
		switch(t){
		case "所有":
			if(d5judge2!=0){
				$('#d5-t14').css('display','none');
			    $('#d5-text14').css('display','none');
			    $('#d5-t15').css('top','-=40px');
			    $('#d5-text15').css('top','-=40px');
			    $('#d5-t16').css('top','-=40px');
			    $('#d5-text16').css('top','-=40px');
			    $('#d5-t17').css('top','-=40px');
			    $('#d5-text17').css('top','-=40px');
			    $('#d5-t18').css('top','-=40px');
			    $('#d5-text18').css('top','-=40px');
				$('#d5-btn1').animate({top:'-=40px'});
			}
			d5judge2=0;
			break;
		case "充值":
			if(d5judge2!=0){
				$('#d5-t14').css('display','none');
			    $('#d5-text14').css('display','none');
			    $('#d5-t15').css('top','-=40px');
			    $('#d5-text15').css('top','-=40px');
			    $('#d5-t16').css('top','-=40px');
			    $('#d5-text16').css('top','-=40px');
			    $('#d5-t17').css('top','-=40px');
			    $('#d5-text17').css('top','-=40px');
			    $('#d5-t18').css('top','-=40px');
			    $('#d5-text18').css('top','-=40px');
				$('#d5-btn1').animate({top:'-=40px'});
			}
			d5judge2=0;
			break;
		case "提现":
			if(d5judge2!=0){
				$('#d5-t14').css('display','none');
			    $('#d5-text14').css('display','none');
			    $('#d5-t15').css('top','-=40px');
			    $('#d5-text15').css('top','-=40px');
			    $('#d5-t16').css('top','-=40px');
			    $('#d5-text16').css('top','-=40px');
			    $('#d5-t17').css('top','-=40px');
			    $('#d5-text17').css('top','-=40px');
			    $('#d5-t18').css('top','-=40px');
			    $('#d5-text18').css('top','-=40px');
				$('#d5-btn1').animate({top:'-=40px'});
			}
			d5judge2=0;
			break;
		case "红包":
			$('#d5-text14').text("红包id");
			if(d5judge2==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t14').css('display','block');
				    $('#d5-text14').css('display','block');
				    $('#d5-t15').css('top','+=40px');
				    $('#d5-text15').css('top','+=40px');
				    $('#d5-t16').css('top','+=40px');
				    $('#d5-text16').css('top','+=40px');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge2=1;
			break;
		case "节目打赏":
			$('#d5-text14').text("节目id");
			if(d5judge2==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t14').css('display','block');
				    $('#d5-text14').css('display','block');
				    $('#d5-t15').css('top','+=40px');
				    $('#d5-text15').css('top','+=40px');
				    $('#d5-t16').css('top','+=40px');
				    $('#d5-text16').css('top','+=40px');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge2=1;
			break;
		case "红包雨":
			$('#d5-text14').text("红包雨轮次");
			if(d5judge2==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t14').css('display','block');
				    $('#d5-text14').css('display','block');
				    $('#d5-t15').css('top','+=40px');
				    $('#d5-text15').css('top','+=40px');
				    $('#d5-t16').css('top','+=40px');
				    $('#d5-text16').css('top','+=40px');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge2=1;
			break;
		}
		});
	var d5judge3=0;
	$('#d5-t15').change(function () {
		var t=this.value;
		switch(t){
		case "所有":
			if(d5judge3!=0){
				$('#d5-t16').css('display','none');
			    $('#d5-text16').css('display','none');
			    $('#d5-t17').css('top','-=40px');
			    $('#d5-text17').css('top','-=40px');
			    $('#d5-t18').css('top','-=40px');
			    $('#d5-text18').css('top','-=40px');
				$('#d5-btn1').animate({top:'-=40px'});
			}
			d5judge3=0;
			break;
		case "大于":
			if(d5judge3==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t16').css('display','block');
				    $('#d5-text16').css('display','block');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge3=1;
			break;
		case "等于":
			if(d5judge3==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t16').css('display','block');
				    $('#d5-text16').css('display','block');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge3=1;
			break;
		case "小于":
			if(d5judge3==0){
				$('#d5-btn1').animate({top:'+=40px'},function(){
					$('#d5-t16').css('display','block');
				    $('#d5-text16').css('display','block');
				    $('#d5-t17').css('top','+=40px');
				    $('#d5-text17').css('top','+=40px');
				    $('#d5-t18').css('top','+=40px');
				    $('#d5-text18').css('top','+=40px');
				});
			}
			d5judge3=1;
			break;
		}
		});
	//查找用户
	$('#d1-btn').click(function(){
		//alert($("#d1-t1").val());
		//alert("hi");
		/*var selecttype =$("#d1-t1").val();
		var itcode=$("#d1-t4").val();
		var username=$("#d1-t5").val();
		var balencetype=$("#d1-t2").val();
		var balence=$("#d1-t3").val();
		/*$.get("adminselcectusers?selecttype="+selecttype+"&itcode="+itcode
				+"&username="+username+"&balencetype="+balence+"&balence",
				function(data){});*/		
	});
	$('#home1').mousedown(function(){
		$(this).css('width','40px');
		$(this).css('margin-top','5px');
	});
	$('#home1').mouseup(function(){
		$(this).css('width','35px');
		$(this).css('margin-top','7px');
		$('#tab1').css('display','none');
		$('#form1').css('display','block');
	});
	$('#home3').mousedown(function(){
		$(this).css('width','40px');
		$(this).css('margin-top','5px');
	});
	$('#home3').mouseup(function(){
		$(this).css('width','35px');
		$(this).css('margin-top','7px');
		$('#d3tab1').css('display','none');
		$('#d3f1').css('display','block');
	});
	$('#home4').mousedown(function(){
		$(this).css('width','40px');
		$(this).css('margin-top','5px');
	});
	$('#home4').mouseup(function(){
		$(this).css('width','35px');
		$(this).css('margin-top','7px');
		switch($('#d4j').val()){
		case '1':
			$('#d4tab1').css('display','none');
			$('#d4f1').css('display','block');
			break;
		case '2':
			$('#d4tab2').css('display','none');
			$('#d4f2').css('display','block');
			break;
		case '3':
			$('#d4tab3').css('display','none');
			$('#d4f3').css('display','block');
			break;
		}
	});
	$('#home5').mousedown(function(){
		$(this).css('width','40px');
		$(this).css('margin-top','5px');
	});
	$('#home5').mouseup(function(){
		$(this).css('width','35px');
		$(this).css('margin-top','7px');
		$('#d5tab1').css('display','none');
		$('#d5f1').css('display','block');
	});
	$('#quit').click(function(){
			window.location.href="logout";
		
	});
});
function get(i){
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
			//r.scrollTop=r.scrollHeight;
	     switch(i){
	    	 case 1:
	    		 document.getElementById("tb1").innerHTML=xml.responseText;
	    		 $('#tab1').css('display','block');
	 			 $('#form1').css('display','none');
	    		 break;
	    	 case 2:
	    		 if(xml.responseText=="itcode错误！")
	    			 Toast(xml.responseText,2000);
	    		 else{
	    			 document.getElementById("tb1").innerHTML=xml.responseText;
	    			 $('#tab1').css('display','block');
	    			 $('#form1').css('display','none');
	    			 }
	    		 break;
	    	 case 3:
	    		 if(xml.responseText=="名字错误！")
	    			 Toast(xml.responseText,2000);
	    		 else{
	    			 document.getElementById("tb1").innerHTML=xml.responseText;
	    			 $('#tab1').css('display','block');
	    			 $('#form1').css('display','none');
	    			 }
	    		 break;
	    	 case 4:
	    		 document.getElementById("tb1").innerHTML=xml.responseText;
	    		 $('#tab1').css('display','block');
    			 $('#form1').css('display','none');
	    		 break;
	    	 case 5:
	    		 Toast(xml.responseText,2000);
	    		 break;
	    	 case 6:
	    		 Toast(xml.responseText,2000);
	    		 break;
	    	 case 7:
	    		 document.getElementById("d14-t1").innerText=xml.responseText;
	    		 break;
	    	 case 8:
	    		 get(7);
	    		 Toast(xml.responseText,2000);
	    		 break;
	     }
	    }
	};
	var url="";
	var p=1;
	switch(i){
	case 1:
		url="adminchecku?judge="+i;
		break;
	case 2:
		url="adminchecku?judge="+i+"&str1="+document.getElementById("d1-t4").value;
		if(document.getElementById("d1-t4").value==""){
			Toast("itcode不能为空",2000);
			p=2;
		}else{
			p=1
		}
			
		break;
	case 3:
		url="adminchecku?judge="+i+"&str1="+document.getElementById("d1-t5").value;
		if(document.getElementById("d1-t5").value==""){
			Toast("用户名不能为空",2000);
			p=2;
		}else{
			p=1;
		}
			
		break;
	case 4:
		url="adminchecku?judge="+i+"&str1="+document.getElementById("d1-t2").value+"&str2="+document.getElementById("d1-t3").value;
		if(document.getElementById("d1-t2").value==""||document.getElementById("d1-t3").value==""){
			Toast("余额不能为空",2000);
			p=2;
		}else{
			p=1;
		}
			
		break;
	case 5:
		if(document.getElementById("d12-t1").value=="")
			Toast("itcode不能为空",2000);
		else
			if(document.getElementById("d12-t2").value=="")
				Toast("金额不能为空",2000);
			else{
				url="adminaddmoney?itcode="+document.getElementById("d12-t1").value+"&money="+document.getElementById("d12-t2").value;
			}
		break;
	case 6:
		if(document.getElementById("d13-t1").value=="")
			Toast("itcode不能为空",2000);
		else
			if(document.getElementById("d13-t2").value=="")
				Toast("新密码不能为空",2000);
			else{
				url="adminccode?itcode="+document.getElementById("d13-t1").value+"&code="+document.getElementById("d13-t2").value;
			}
		break;
	case 7:
		url="adminluck?judge=1";
		break;
	case 8:
		if(document.getElementById("d14-t2").value=="")
			Toast("金额不能为空",2000);
		else
			url="adminluck?judge=2&amount="+document.getElementById("d14-t2").value;
		break;
	}
	if(url!=""&&p==1)
		{
		xml.open("GET", url, true);
		xml.send();	
		}
}	
function senduse(){
	var s=document.getElementById("d1-t1").value;
	document.getElementById("tb1").innerHTML="";
	switch(s){
	case '所有':
		get(1);
		break;
	case 'itcode':
		get(2);
		break;
	case '用户名':
		get(3);
		break;
	case '余额':
		get(4);
		break;
	}
}
function sendd2(i){
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
			if(i==1)
				Toast(xml.responseText,2000);
			else
				document.getElementById("d2tb1").innerHTML=xml.responseText;
			}
	    }
	var url="";
	switch(i){
	case 1:
		var temp1=document.getElementById("d2-t1").value;
		var temp2=document.getElementById("d2-t2").value;
		var temp3=document.getElementById("d2-t3").value;
		if(temp1!="")
			if(temp2!="")
				if(temp3!=""){
					url="programadd?j=1&str1="+temp1+"&str2="+temp2+"&str3="+temp3;
				}
				else
					Toast("演出单位不可为空",2000);
			else
				Toast("表演者不可为空",2000);
		else
			Toast("节目名称不可为空",2000);
		break;
	case 2:
		url="programadd?j=2";
		break;
	}
	if(url!="")
		{
		xml.open("GET", url, true);
		xml.send();
		}
}
function sendd3(i){
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
			if(i==0)
				switch(xml.responseText){
				case "001":
					document.getElementById("d3-btn3").disabled="disabled";
					break;
				case "010":
					document.getElementById("d3-btn2").disabled="disabled";
					break;
				case "011":
					document.getElementById("d3-btn2").disabled="disabled";
					document.getElementById("d3-btn3").disabled="disabled";
					break;
				case "100":
					document.getElementById("d3-btn1").disabled="disabled";
					break;
				case "101":
					document.getElementById("d3-btn1").disabled="disabled";
					document.getElementById("d3-btn3").disabled="disabled";
					break;
				case "110":
					document.getElementById("d3-btn1").disabled="disabled";
					document.getElementById("d3-btn2").disabled="disabled";
					break;
				case "111":
					document.getElementById("d3-btn1").disabled="disabled";
					document.getElementById("d3-btn2").disabled="disabled";
					document.getElementById("d3-btn3").disabled="disabled";
					break;
				}
			else if(i==1||i==2||i==3)
				{
				Toast(xml.responseText,2000);
				sendd3(0);
				}
			else{
				if(xml.responseText=="查询红包雨记录失败！")
					Toast(xml.responseText);
				else{
					document.getElementById("d3tb1").innerHTML=xml.responseText;
					$('#d3tab1').css('display','block');
	   			    $('#d3f1').css('display','none');
				}
				}
			}
	    }
	var url="";
	switch(i){
	case 0:
		url="luckyrain1?j=0";
		break;
	case 1:
		url="luckyrain1?j=1&str1="+i;
		break;
	case 2:
		url="luckyrain1?j=1&str1="+i;
		break;
	case 3:
		url="luckyrain1?j=1&str1="+i;
		break;
	case 4:
		var d3temp=document.getElementById("d3-t1").value;
		if(d3temp=="所有")
			url="luckyrain1?j=2&str1=0";
		else if(d3temp=="第一轮")
			url="luckyrain1?j=2&str1=1";
		else if(d3temp=="第二轮")
			url="luckyrain1?j=2&str1=2";
		else
			url="luckyrain1?j=2&str1=3";
		if(document.getElementById("d3-t7").value=="1")
			url+="&str2="+document.getElementById("d3-t7").value+"&str3="+document.getElementById("d3-t3").value;
		else if(document.getElementById("d3-t7").value=="2")
			url+="&str2="+document.getElementById("d3-t7").value+"&str3="+document.getElementById("d3-t4").value;
		else
			url+="&str2="+document.getElementById("d3-t7").value+"&str3="+document.getElementById("d3-t4").value;
		url+="&str4="+document.getElementById("d3-t8").value+"&str5="+document.getElementById("d3-t6").value;
		break;
	}
	if(url!="")
		{
		xml.open("GET", url, true);
		xml.send();
		}
}
function sendd4(){
	var i=document.getElementById("d4j").value;
	var xml=new XMLHttpRequest();
	var j=0;
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
			if(j=="1")
				if(xml.responseText=="查询失败！")
					Toast(xml.responseText);
				else{
					document.getElementById("d4tb"+i).innerHTML=xml.responseText;
			        $('#d4tab'+i).css('display','block');
			        $('#d4f'+i).css('display','none');
				}
			else 
				Toast(xml.responseText);
			}
	    }
	var url="";
	switch(i){
	case "1":
		j=document.getElementById("d4-t16").value;
		if(j!="1")
			if(document.getElementById("d4-t14").value!="")
				url+="allmoneycheck?j="+i+"&j2="+document.getElementById("d4-t16").value+
				"&j3="+document.getElementById("d4-t15").value+"&j4="+
				document.getElementById("d4-t14").value;
			else
				Toast("金额不可为空",2000);
		else
			url+="allmoneycheck?j="+i+"&j2="+document.getElementById("d4-t16").value+
			"&j3="+document.getElementById("d4-t15").value+"&j4="+
			document.getElementById("d4-t14").value;
		break;
	case "2":
		if(document.getElementById("d4-t21").value=="查询")
		    j=1;
		else if(document.getElementById("d4-t21").value=="充值")
			j=2;
		else 
			j=3;
		var j2=document.getElementById("d4-t22").value;
		if(j==1&&j2=="红包id"&&document.getElementById("d4-t23").value=="")
			Toast("红包id不可为空");
		else if(j!=1&&document.getElementById("d4-t23").value=="")
			Toast("红包id不可为空");
		else if(j!=1&&document.getElementById("d4-t24").value=="")
			Toast("金额不可为空");
		else if(j==1){
			url+="allmoneycheck?j="+i+"&j2="+j;
			if(j2=="红包id")
				url+="&j3="+2+"&j4="+document.getElementById("d4-t23").value;
			else
				url+="&j3="+1;
		}
		else
			url+="allmoneycheck?j="+i+"&j2="+j+"&j3="+document.getElementById("d4-t23").value+
					"&j4="+document.getElementById("d4-t24").value;
		break;
	case "3":
		if(document.getElementById("d4-t31").value=="查询")
		    j=1;
		else if(document.getElementById("d4-t31").value=="充值")
			j=2;
		else 
			j=3;
		var j2=document.getElementById("d4-t32").value;
		if(j==1&&j2=="节目id"&&document.getElementById("d4-t33").value=="")
			Toast("节目id不可为空");
		else if(j!=1&&document.getElementById("d4-t33").value=="")
			Toast("节目id不可为空");
		else if(j!=1&&document.getElementById("d4-t34").value=="")
			Toast("金额不可为空");
		else if(j==1){
			url+="allmoneycheck?j="+i+"&j2="+j;
			if(j2=="节目id")
				url+="&j3="+2+"&j4="+document.getElementById("d4-t33").value;
			else
				url+="&j3="+1;
		}
		else
			url+="allmoneycheck?j="+i+"&j2="+j+"&j3="+document.getElementById("d4-t33").value+
					"&j4="+document.getElementById("d4-t34").value;
		break;
	}
	if(url!="")
	{
	xml.open("GET", url, true);
	xml.send();
	}
}
function sendd5(){
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
			if(xml.responseText=="查询失败！")
				Toast(xml.responseText,2000);
			else
				{
				document.getElementById("d5tb1").innerHTML=xml.responseText;
				$('#d5tab1').css('display','block');
   			    $('#d5f1').css('display','none');
				}
	    }
	}
	var url="";
	if(document.getElementById("d5-t11").value!="所有"&&document.getElementById("d5-t12").value=="")
		Toast("用户名或itcode不可为空！");
	else if(document.getElementById("d5-t15").value!="所有"&&document.getElementById("d5-t16").value=="")
		Toast("金额不可为空！");
	else if(document.getElementById("d5-t17").value>document.getElementById("d5-t18").value)
		Toast("日期错误！");
	else 
		url+="conprehensivecheck?str1="+document.getElementById("d5-t11").value+"&str2="+
		document.getElementById("d5-t12").value+"&str3="+
		document.getElementById("d5-t13").value+"&str4="+
		document.getElementById("d5-t14").value+"&str5="+
		document.getElementById("d5-t15").value+"&str6="+
		document.getElementById("d5-t16").value+"&str7="+
		document.getElementById("d5-t17").value+"&str8="+
		document.getElementById("d5-t18").value;
	if(url!="")
	{
	xml.open("GET", url, true);
	xml.send();
	}
}
function Toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var mm = document.createElement('div');
    mm.innerHTML = msg;
    mm.style.cssText="width: 30%;min-width: 100px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 55%;left: 24%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
    document.body.appendChild(mm);
    setTimeout(function() {
        var d = 0.5;
        mm.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        mm.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(mm) }, d * 1000);
    }, duration);
}
</script>

</head>
<!-- #DDF0ED -->
<body style="background-image: url(img/33.png); background-repeat: no-repeat; background-size: cover">
<div class="Body">
	<div class="broad">
		<div class="broadon">
			<div class="broadon-out">
				<div class="broadon-inner" id="D7" style="background-color:red;height:100%;top:0px;left:0px;">
					<img src="img/logo.png" style="margin-left:180px;margin-top:150px;">
					<img src="img/welcome.png" width=900px height=100px style="margin-left:100px;">
				</div>
				<div id="D2" style="position:absolute;top:-100%;left:0px;width:100%;height:100%;">
				<div id="btnbroad2">
						<input type="button" class="btnbroad-b2" id="btnbroad2-b1" value="节目报送">
						<input type="button" class="btnbroad-b2" id="btnbroad2-b2" value="节目列表">
					</div>
					<div class="broadon-inner" id="d21" style="background-color:#CFD6E6;height:420px;left:0px;">
					<div id="form2" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
							<div class="Text" id="d2-text1" style="top:50px;left:180px;">节目名称:</div>
							<input type="text" id="d2-t1" style="top:45px;left:300px;">					
							<div class="Text" id="d2-text2" style="top:100px;left:180px;">表演者:</div>
							<input type="text" id="d2-t2" style="top:95px;left:300px;">
							<div class="Text" id="d2-text3" style="top:150px;left:180px;">演出单位:</div>
							<input type="text" id="d2-t3" style="top:145px;left:300px;">
							<input type="button"  onclick="sendd2(1)" id="d2-btn" value="添加" class="innerbtn" style="top:200px;left:310px;width:100px;">
						</div>
					</div>
					<div class="broadon-inner" id="d22" style="background-color:#E6CFB5;height:100%;">
					        <table style="display:block;" id="d2tab1">
							    <thead><tr><td width=100px>节目id</td><td width=230px>节目名</td><td width=195px>表演者名</td><td width=195px>演出单位</td></tr></thead>
							    <tbody id="d2tb1">
								    </tbody>
						    </table>
					</div>
					<div style="position:absolute;top:460px;height:40px;width:720px;background-color:#73C09C;text-align:center;">					
						<img src="img/home.png" id="home2" width=35px style="margin-top:7px;">
					</div>
				</div>
				<div id="D3" style="position:absolute;top:-100%;left:0px;width:100%;height:100%;">
				<div id="btnbroad3">
				        <input type="button" class="btnbroad-b3" id="btnbroad3-b1" value="开启红包雨">
						<input type="button" class="btnbroad-b3" id="btnbroad3-b2" value="查看发放记录">
					</div>
					<div class="broadon-inner" id="d31" style="background-color:#CFD6E6;height:420px;left:0px;">
					<input type="button"  onclick="sendd3(1)" id="d3-btn1" value="开启第一轮红包雨" class="innerbtn" style="top:100px;left:260px;width:200px;">
					<input type="button"  onclick="sendd3(2)" id="d3-btn2" value="开启第二轮红包雨" class="innerbtn" style="top:150px;left:260px;width:200px;">
					<input type="button"  onclick="sendd3(3)" id="d3-btn3" value="开启第三轮红包雨" class="innerbtn" style="top:200px;left:260px;width:200px;">
					</div>
					<div class="broadon-inner" id="d32" style="background-color:#E6CFB5;height:420px;;">
					<div id="d3f1" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
					<div class="Text" id="d3-text1" style="top:50px;left:180px;">红包雨轮次:</div>
					<select id="d3-t1" style="top:45px;left:300px;">
						<option >所有</option>
						<option >第一轮</option>
						<option >第二轮</option>
						<option >第三轮</option>
					</select>
					<div class="Text" id="d3-text2" style="top:100px;left:180px;">itcode或用户名:</div>
					<select id="d3-t2" style="top:95px;left:300px;">
						<option >所有</option>
						<option >itcode</option>
						<option >用户名</option>
					</select>
					<div class="Text" id="d3-text3" style="top:150px;left:180px;display:none;">itcode:</div>
					<input type="text" id="d3-t3" style="top:145px;left:300px;display:none;">
					<div class="Text" id="d3-text4" style="top:150px;left:180px;display:none;">用户名:</div>
					<input type="text" id="d3-t4" style="top:145px;left:300px;display:none;">
					<div class="Text" id="d3-text5" style="top:150px;left:180px;">交易金额:</div>
					<select id="d3-t5" style="top:145px;left:300px;">
						<option >所有</option>
						<option >大于</option>
						<option >小于</option>
						<option >等于</option>
					</select>
					<div class="Text" id="d3-text6" style="top:200px;left:180px;display:none;">金额:</div>
					<input type="text" id="d3-t6" style="top:195px;left:300px;display:none;">
					<input type="button"  onclick="sendd3(4)" id="d3-btn" value="查询" class="innerbtn" style="top:200px;left:310px;width:100px;">
					<input type="hidden" id="d3-t7" value='0'><input type="hidden" id="d3-t8" value='0'>
					</div>
					<table  id="d3tab1">
							    <thead><tr><td width=100px>红包雨轮次</td><td width=100px>用户itcode</td><td width=150px>用户名</td><td width=170px>金额</td><td width=200px>时间</td></tr></thead>
							    <tbody id="d3tb1">
								    </tbody>
						    </table>
					</div>
					<div style="position:absolute;top:460px;height:40px;width:720px;background-color:#73C09C;text-align:center;">					
						<img src="img/home.png" id="home3" width=35px style="margin-top:7px;">
					</div>
				</div>
				<div id="D4" style="position:absolute;top:-100%;left:0px;width:100%;height:100%;">
				<div id="btnbroad4">
				        <input type="button" class="btnbroad-b4" style="width:240px;" id="btnbroad4-b1" value="红包雨账户">
						<input type="button" class="btnbroad-b4" style="width:240px;" id="btnbroad4-b2" value="红包账户">
						<input type="button" class="btnbroad-b4" style="width:240px;" id="btnbroad4-b3" value="节目账户">
					</div>
					<input type="hidden" value="1" id="d4j">
					<div class="broadon-inner" id="d41" style="background-color:#CFD6E6;height:420px;left:0px;">
					<div id="d4f1" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
					<div class="Text" id="d4-text11" style="top:50px;left:180px;">选择操作:</div>
					<select id="d4-t11" style="top:45px;left:300px;">
						<option >查询</option>
						<option >充值</option>
						<option >提现</option>
					</select>
					<div class="Text" id="d4-text12" style="top:100px;left:180px;">查询轮次:</div>
					<select id="d4-t12" style="top:95px;left:300px;">
						<option >所有</option>
						<option >第一轮</option>
						<option >第二轮</option>
						<option >第三轮</option>
					</select>
					<div class="Text" id="d4-text13" style="top:100px;left:180px;display:none;">选择轮次:</div>
					<select id="d4-t13" style="top:95px;left:300px;display:none;">
						<option >第一轮</option>
						<option >第二轮</option>
						<option >第三轮</option>
					</select>
					<div class="Text" id="d4-text14" style="top:150px;left:180px;display:none;">金额:</div>
					<input type="text" id="d4-t14" style="top:145px;left:300px;display:none;">
					<input type="button"  onclick="sendd4()" id="d4-btn1" value="执行" class="innerbtn" style="top:150px;left:310px;width:100px;">
					<input type="hidden" id="d4-t15" value="0"><input type="hidden" id="d4-t16" value="1">
					</div>
					<table  id="d4tab1">
						<thead><tr><td width=200px>红包雨轮次</td><td width=220px>金额</td><td width=300px>备注</td></tr></thead>
							<tbody id="d4tb1">
							</tbody>
						</table>
					</div>
					<div class="broadon-inner" id="d42" style="background-color:#E6CFB5;height:420px;">
					<div id="d4f2" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
					<div class="Text" id="d4-text21" style="top:50px;left:180px;">选择操作:</div>
					<select id="d4-t21" style="top:45px;left:300px;">
						<option >查询</option>
						<option >充值</option>
						<option >提现</option>
					</select>
					<div class="Text" id="d4-text22" style="top:100px;left:180px;">查询方式:</div>
					<select id="d4-t22" style="top:95px;left:300px;">
						<option >所有</option>
						<option>红包id</option>
					</select>
					<div class="Text" id="d4-text23" style="top:100px;left:180px;display:none;">红包id:</div>
					<input type="text" id="d4-t23" style="top:95px;left:300px;display:none;">
					<div class="Text" id="d4-text24" style="top:150px;left:180px;display:none;">金额:</div>
					<input type="text" id="d4-t24" style="top:145px;left:300px;display:none;">
					<input type="button"  onclick="sendd4()" id="d4-btn2" value="执行" class="innerbtn" style="top:150px;left:310px;width:100px;">
					</div>
					<table  id="d4tab2">
						<thead><tr><td width=200px>红包id</td><td width=220px>金额</td><td width=300px>备注</td></tr></thead>
							<tbody id="d4tb2">
							</tbody>
						</table>
					</div>
					<div class="broadon-inner" id="d43" style="background-color:#73D99A;height:420px;">
					<div id="d4f3" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
					<div class="Text" id="d4-text31" style="top:50px;left:180px;">选择操作:</div>
					<select id="d4-t31" style="top:45px;left:300px;">
						<option >查询</option>
						<option >充值</option>
						<option >提现</option>
					</select>
					<div class="Text" id="d4-text32" style="top:100px;left:180px;">查询方式:</div>
					<select id="d4-t32" style="top:95px;left:300px;">
						<option >所有</option>
						<option>节目id</option>
					</select>
					<div class="Text" id="d4-text33" style="top:100px;left:180px;display:none;">节目id:</div>
					<input type="text" id="d4-t33" style="top:95px;left:300px;display:none;">
					<div class="Text" id="d4-text34" style="top:150px;left:180px;display:none;">金额:</div>
					<input type="text" id="d4-t34" style="top:145px;left:300px;display:none;">
					<input type="button"  onclick="sendd4()" id="d4-btn3" value="执行" class="innerbtn" style="top:150px;left:310px;width:100px;">
					</div>
					<table  id="d4tab3">
						<thead><tr><td width=60px>节目id</td><td width=200px>节目名</td><td width=200px>演出者</td><td width=200px>演出单位</td><td width=60px>账户金额</td></tr></thead>
							<tbody id="d4tb3">
							</tbody>
						</table>
					</div>
					<div style="position:absolute;top:460px;height:40px;width:720px;background-color:#73C09C;text-align:center;">					
						<img src="img/home.png" id="home4" width=35px style="margin-top:7px;">
					</div>
				</div>
				<div id="D5" style="position:absolute;top:-100%;left:0px;width:100%;height:100%;">
				<div id="btnbroad5">
						<input type="button" class="btnbroad-b5" id="btnbroad5-b1" style="width:720px;" value="综合记录查询">
					</div>
					<div class="broadon-inner" id="d51" style="background-color:#CFD6E6;height:420px;left:0px;">
					<div id="d5f1" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
					<div class="Text" id="d5-text11" style="top:30px;left:180px;">查询用户:</div>
					<select id="d5-t11" style="top:25px;left:300px;">
						<option >所有</option>
						<option>根据itcode</option>
						<option>根据用户名</option>
					</select>
					<div class="Text" id="d5-text12" style="top:70px;left:180px;display:none;">itcode:</div>
					<input type="text" id="d5-t12" style="top:65px;left:300px;display:none;">
					<div class="Text" id="d5-text13" style="top:70px;left:180px;">记录种类:</div>
					<select id="d5-t13" style="top:65px;left:300px;">
					    <option >所有</option>
					    <option>充值</option>
					    <option>提现</option>
						<option >红包</option>
						<option>节目打赏</option>
						<option>红包雨</option>
					</select>
					<div class="Text" id="d5-text14" style="top:110px;left:180px;display:none;">红包id:</div>
					<input type="text" placeholder="不填则默认选择所有该项记录" id="d5-t14" style="top:105px;left:300px;display:none;">
					<div class="Text" id="d5-text15" style="top:110px;left:180px;">金额:</div>
					<select id="d5-t15" style="top:105px;left:300px;">
					    <option >所有</option>
					    <option>大于</option>
					    <option>等于</option>
						<option >小于</option>
					</select>
					<div class="Text" id="d5-text16" style="top:150px;left:180px;display:none;">金额:</div>
					<input type="text" id="d5-t16" style="top:145px;left:300px;display:none;">
					<div class="Text" id="d5-text17" style="top:150px;left:180px;">开始日期:</div>
					<input id="d5-t17" class="date" style="top:145px;left:300px;" type="date" value="2017-08-01" min="2017-08-01"/>
					<div class="Text" id="d5-text18" style="top:190px;left:180px;">结束日期:</div>
		            <input id="d5-t18" class="date" style="top:185px;left:300px;" type="date" min="2017-08-01"/>
		            <input type="button"  onclick="sendd5()" id="d5-btn1" value="查询" class="innerbtn" style="top:230px;left:310px;width:100px;">
					</div>
					<table id="d5tab1">
							<thead><tr><td width=100px>流水号</td><td width=100px>用户itcode</td><td width=130px>用户名</td><td width=330px>交易备注</td><td width=60px>金额</td></tr></thead>
							<tbody id="d5tb1">
								</tbody>
						</table>
					</div>
					<div style="position:absolute;top:460px;height:40px;width:720px;background-color:#73C09C;text-align:center;">					
						<img src="img/home.png" id="home5" width=35px style="margin-top:7px;">
					</div>
				</div>
				<div id="D1" style="position:absolute;top:-100%;left:0px;width:100%;height:100%;">
					<div id="btnbroad">
						<input type="button" class="btnbroad-b" id="btnbroad-b1" value="查找用户">
						<input type="button" class="btnbroad-b" id="btnbroad-b2" value="充值">
						<input type="button" class="btnbroad-b" id="btnbroad-b3" value="修改密码">
						<input type="button" class="btnbroad-b" id="btnbroad-b4" value="发红包">
					</div>
					<div class="broadon-inner" id="d1" style="background-color:#CFD6E6;height:420px;left:0px;">
						<div id="form1" style="background-color:#CFD6E6;height:420px;top:40px;width:720px;">
							<div class="Text" id="d1-text1" style="top:50px;left:180px;">查询方式:</div>
							<select id="d1-t1" style="top:45px;left:300px;">
								<option >所有</option>
								<option >itcode</option>
								<option >用户名</option>
								<option >余额</option>
							</select>
							
							<div class="Text" id="d1-text2" style="top:100px;left:180px;display:none;">查询余额方式:</div>
							<select id="d1-t2" style="top:95px;left:300px;display:none;">
								<option value=1>大于</option>
								<option value=2>等于</option>
								<option value=3>小于</option>
							</select>
							<div class="Text" id="d1-text3" style="top:150px;left:180px;display:none;">余额大小:</div>
							<input type="text" id="d1-t3" style="top:145px;left:300px;display:none;">
							
							<div class="Text" id="d1-text4" style="top:100px;left:180px;display:none;">itcode:</div>
							<input type="text" id="d1-t4" style="top:95px;left:300px;display:none;">
							
							<div class="Text" id="d1-text5" style="top:100px;left:180px;display:none;">用户名:</div>
							<input type="text" id="d1-t5" style="top:95px;left:300px;display:none;">
							
							<input type="button"  onclick="senduse()" id="d1-btn" value="查询" class="innerbtn" style="top:100px;left:310px;width:100px;">
						</div>
						<table id="tab1">
							<thead><tr><td width=130px>用户id</td><td width=130px>itcode</td><td width=230px>用户名</td><td width=230px>余额</td></tr></thead>
							<tbody id="tb1">
								</tbody>
						</table>
					</div>
					<div class="broadon-inner" id="d2" style="background-color:#E6CFB5;height:420px;">
						<div class="Text" style="top:100px;left:180px;">itcode:</div><input type="text" id="d12-t1" style="top:95px;left:300px;">
						<div class="Text" style="top:150px;left:180px;">充值金额：</div><input type="text" id="d12-t2" style="top:145px;left:300px;">
						<input type="button" class="innerbtn" id="d12-btn" value="确认充值" style="top:200px;left:310px;width:100px;" onclick="get(5)">
					</div>
					<div class="broadon-inner" id="d3" style="background-color:#73D99A;height:420px;">
						<div class="Text" style="top:100px;left:180px;">itcode:</div><input type="text" id="d13-t1" style="top:95px;left:300px;">
						<div class="Text" style="top:150px;left:180px;">修改为：</div><input type="text" id="d13-t2" style="top:145px;left:300px;">
						<input type="button" class="innerbtn" onclick="get(6)" id="d13-btn" value="确认修改" style="top:200px;left:310px;width:100px;">
					</div>
					<div class="broadon-inner" id="d4" style="background-color:#F7F288;height:420px;">
					<div class="Text" style="top:100px;left:180px;">当前红包数:</div><div class="Text" id="d14-t1" style="top:100px;left:280px;"></div>
						<div class="Text" style="top:150px;left:180px;">红包金额：</div><input type="text" id="d14-t2" style="top:145px;left:300px;">
						<input type="button" class="innerbtn" id="d14-btn" onclick="get(8)" value="发红包" style="top:200px;left:310px;width:100px;">
					</div>
					<div style="position:absolute;top:460px;height:40px;width:720px;background-color:#73C09C;text-align:center;">					
						<img src="img/home.png" id="home1" width=35px style="margin-top:7px;">
					</div>
				</div>
			</div>
		</div>
	</div>
	<button class="btn1" id="b1">用户<br>管理</button>
	<button class="btn2" id="b2">节目<br>报送</button>
	<button class="btn3" id="b3">红包雨</button>
	<button class="btn4" id="b4">充值<br>提现</button>
	<button class="btn5" id="b5">查看<br>记录</button>
	<button id="quit" class="btn6" id="b6">退出</button>
	<button class="btn7" id="b7">开始</button>
	<div class="broadText" style="top:5px;">超级管理员系统</div>
	<div class="broadText" style="bottom:5px;">胜翼组出品</div>
</div>
<script type="text/javascript">
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});
document.getElementById("d5-t18").value=new Date().toDateInputValue();
</script>
</body>
</html>