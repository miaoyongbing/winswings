<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" href="css/common.css" />
<link href="css/bootstrap.min.css" ref="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="application/javascript">
        $(document).ready(function () {
            $.get("getuserportrait?",function(result){
                $("#portrait").attr('src',result);
            })
        })
</script>

<script type="text/javascript">

    </script>

<style type="text/css">
body {
	font: normal 100% Helvetica, Arial, sans-serif;
}

.webhead {
	position: fixed;
	left: 0px;
	top: 0px;
	height: 15%;
	width: 100%;
	background-color: #E5EEA6;
	filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090,
		direction=120, strength=4);
	-moz-box-shadow: 2px 2px 10px #909090;
	-webkit-box-shadow: 2px 2px 10px #909090;
	box-shadow: 2px 2px 10px #909090;
	z-index: 1;
}

.webhead img {
	float: left;
	max-height: 80%;
	margin-top: 0.5%;
}

.webhead span {
	position: absolute;
	bottom: 10px;
	font-family: LiSu;
	color: #FFFFFF;
	text-shadow: 1px 1px 1px #000;
}

.backbroad {
	position: absolute;
	top: 30%;
	height: atuo;
	width: 100%;
	background-color: #E5EEA6;
}

.block {
	float: left;
	background-color: #EEFF6B;
	width: 300px;
	margin-top: 2%;
	margin-left: 1%;
	border: 1px solid #FFFFFF;
	height: 110px;
}

.block_img {
	height: 80px;
	width: 80px;
	margin-top: 15px;
	margin-left: 15px;
}

.block_text {
	height: 110px;
	width: 170px;
	float: right;
	line-height: 110px;
	font-family: LiSu;
	font-size: 30px;
	color: #7C8489;
	text-align: center;
}

.block:hover {
	background-color: #F5B977;
	color: #FFFFFF;
}

.downtime {
	float: right;
	text-shadow: 3px #000;
	width: 290px;
	height: 260px;
	margin-top:5%;
}

.downtime span {
	font-size: 20px;
	font-family: LiSu;
}

.downtime table {
	font-size: 30px;
}

.downtime img {
	height: 72px;
	width: 54px;
}

.webtail {
	width: 100%;
	left: 0px;
	position: fixed;
	bottom: 0px;
	height: 12%;
	background-color: #E5EEA6;
	filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090,
		direction=120, strength=4);
	-moz-box-shadow: 2px 2px 10px #909090;
	-webkit-box-shadow: 2px 2px 10px #909090;
	box-shadow: 2px 2px 10px #909090;
}

.webtail_table {
	position: absolute;
	left: 25%;
	width: 50%;
	height: 100%;
	text-align: center;
	color: #FFFFFF;
	text-shadow: 1px 1px 1px #000;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}

#changebox input[type=text]{
	border-radius:4px;
	height:30px;
}
#changebox input[type=button]{
	border-radius:8px;
	height:30px;
	margin-left:30%;
}

#dialog {
	width: 500px;
	height: auto;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -250px;
}

#portraite {
	width: 155px;
	position: relative;
	display: inline-block;
	/*background: #D0EEFF;*/
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 18px;
}

#portraite input {
	position: absolute;
	font-size: 100px;
	left: 0;
	top: 0;
	opacity: 0;
}

#portraite:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}

</style>

<script type="text/javascript">
        var username='<%=session.getAttribute("username")%>';
        var itcode='<%=session.getAttribute("itcode")%>';
        var w, h, className;
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

            //打开弹窗
            $('.box a').click(
                function() {
                    className = $(this).attr('class');
                    $('#dialogBg').fadeIn(300);
                    $('#dialog').removeAttr('class').addClass(
                        'animated ' + className + '').fadeIn();
                    $('#itcode').val(itcode).attr("disabled","disabled");;
                    $('#username').val(username);
                    
                });

            //关闭弹窗
            $('.claseDialogBtn').click(function() {
                $('#dialogBg').fadeOut(300, function() {
                    $('#dialog').addClass('bounceOutUp').fadeOut();
                });
            });
            
            $("#newpassword1").blur(function(){
            	if($("#newpassword1").val()!=""){
            		if($("#oldpassword").val()==null||$("#oldpassword").val()=="")
            			$("#afterold").html("<div><img src='img/error.png' width='10' height='10'>旧密码没有输入<div>")
            	}else{
            		$("#afterold").html("")
            	}
            });
            
            $("#newpassword2").blur(function(){
            	if($("#newpassword2").val()!=""){
            		if($("#newpassword2").val()!=$("#newpassword1").val()){
            			$("#afternew2").html("<div><img src='img/error.png' width='10' height='10'>两次密码不一样<div>")
            		}else{
            			$("#afternew2").html("");
            		}
            		}else{
            		$("#afternew2").html("")
            	}
            });
            
            $('#submit').click(function() {
            	var obj = document.getElementById("port");
               if(username==$("#username").val()&&obj.files.length==0&&$("#newpassword2").val()==""){
            	   alert("信息一样不用修改");
               }else{
            	 
            	   if(obj.files.length!=0){
            		   var files = $('#port').prop('files');  
            		   var data = new FormData();
            		   data.append('img', files[0]); 
            		   var status=0;
            		   $.ajax({
            		    url: 'upload',
            		    type: 'POST',
            		    data: data,
            		    cache: false,
            		    processData: false,
            		    contentType: false,
            		    //error: function(){ //失败 
            		    	//alert('图片上传失败'); 
            		    	//}, 
            		   //	success: function(msg){ //成功
            		   	//	window.location.reload();//刷新当前页面.
            		   		
            		    	//}        		    
            		   });  	   
            	   }
            	   if(username!=$("#username").val()){
        			   $.get("changeusername?text1="+$("#username").val()
        					   //function(data){ 
        				   //window.location.reload();}
        			  )
        		   }
        		   if($("#newpassword2").val()!=""){
        			   $.get("changepassword?text1="+$("#oldpassword").val()+"&text2="+$("#newpassword2").val(),
        					   function(data){ 
        				   if(data==1){
        					   alert( "密码 修改成功" ); 
        					   }else{
        						   alert( "旧密码错误！ " ); 
        					   }
        			   } )
        		   }
        		   setTimeout(function(){window.location.reload();}, 600);
        		   
               }
            });
            
        });
    </script>

<script type="text/javascript">
$(function () {
    var winWide = window.screen.width;
    var wideScreen = false;
    if (winWide <= 750) {
    	$("#blocks").css("width","315px");
    	$(".downtime").css("width","315px");
    	$(".downtime").css("text-align","center");
    	$(".downtime table").css("marginLeft","8px");
    	$("#face").css("width","30%");
    	$("#face span").bind("click",function(){
    		$("#changebox").css("display","block");
    	});
    } 
});
</script>
</head>
<body>

	<!-- 网页头  -->
	<div class="webhead">
		<img src="img/logo.png">
		<a href="logout"><span style="right:1%;">注销登录</span></a>
		<span id="welcome" style="right:20%;">欢迎</span>
	</div>

	<!-- 体  -->
	<div class="backbroad">
		<!--头像框及某些东西 -->
		<div id="face" style="width:18%;height:auto;text-align:center;float:left;">
			<!-- 头像 -->
			<img id="portrait" src="img/face.jpeg" style="width:70%;height:70%;max-width:90%;margin-top:10%;">
		<!--  	<div id="perinfo"  style="margin-top:8%;text-align:center;text-shadow: 1px 1px 1px #000; color: #FFFFFF;">
				<a href="javascript:;" class="rollIn">修改个人信息</a>
			</div>-->
			<div class="box">
				<div class="demo" id="perinfo"  style="margin-top:8%;text-align:center;text-shadow: 1px 1px 1px #000; color: #FFFFFF;">
					<a href="javascript:;" class="rollIn">修改个人信息</a>
				</div>
				<div id="dialogBg"></div>
				<div id="dialog" class="animated" style="margin-right: 200px;">
					<img class="dialogIco" width="50" height="50" src="img/ico.png"
						alt="" />
					<div class="dialogTop">
						<a href="javascript:;" class="claseDialogBtn">关闭</a>
					</div>
					<form id="editForm">
						<ul class="editInfos">
							<li><label>账&nbsp;&nbsp;&nbsp;&nbsp;号：<input
									id="itcode" type="text" name="" value="" class="ipt" /></label></li>
							<li><label>头&nbsp;&nbsp;&nbsp;&nbsp;像：<a
									href="javascript:;" id="portraite" class="ipt">选择头像<input id="port"
										type="file" name='' value="" class="ipt" /></a></label></li>
							<li><label>昵&nbsp;&nbsp;&nbsp;&nbsp;称：<input
									id="username" type="text" name="" required value="" class="ipt" /></label></li>
							<li><label>旧密码：<input placeholder="不修改请跳过"
									id="oldpassword" type="password" name="" value="" class="ipt" /><span
									id="afterold"></span></label></li>
							<li><label>新密码：<input placeholder="不修改请跳过"
									id="newpassword1" type="password" name="" value="" class="ipt" /><span
									id="afternew1"></span></label></li>
							<li><label>新密码：<input placeholder="不修改请跳过"
									id="newpassword2" type="password" name="" value="" class="ipt" /><span
									id="afternew2"></span></label></li>
							<li><input type="button" id="submit" value="确认提交" class="submitBtn" /></li>
						</ul>
					</form>
				</div>
			</div>
		</div>
		
		
		
		
		<div id="changebox" style="width:70%;height:auto;float:left;display:none;">
			<form>
				<table>
					<tr><td>昵称:</td><td><input type="text" id="changename" placeholder="不修改请跳过"></td></tr>
					<tr><td>旧密码:</td><td><input type="text" id="changename" placeholder="不修改请跳过"></td></tr>
					<tr><td>新密码:</td><td><input type="text" id="changename" placeholder="不修改请跳过"></td></tr>
					<tr><td>新密码:</td><td><input type="text" id="changename" placeholder="不修改请跳过"></td></tr>
				</table>
				<input id="sure" type="button" value="确认修改">
			</form>
		</div>
		
		
		
		
		
		<div id="blocks" style="width:650px;float:left;text-align:center;padding-left:30px;padding-top:20px;padding-bottom:20px;">
			<a href="chatting">
				<div class="block">
					<div style="float: left;">
						<img class="block_img" src="img/3.png">
					</div>
					<div class="block_text" style="text-align: center;">聊天</div>
				</div>
			</a> 
			
			<a href="perform">
				<div class="block">
					<div style="float: left;">
						<img class="block_img" src="img/4.png">
					</div>
					<div class="block_text" style="text-align: center;">节目</div>
				</div>
			</a>
			
			<a href="wallet">
				<div class="block">
					<div style="float: left;">
						<img class="block_img" src="img/1.png">
					</div>
					<div class="block_text" style="text-align: center;">钱包</div>
				</div>
			</a> 
			
			<div class="box1">
				<div class="demo" id="perinfo"  style="margin-top:8%;text-align:center;text-shadow: 1px 1px 1px #000; color: #FFFFFF;">
					<a href="javascript:;" class="rollIn"></a>
				</div>
				<div id="dialogBg"></div>
				<div id="dialog" class="animated" style="margin-right: 200px;">
					<img class="dialogIco" width="50" height="50" src="img/ico.png"
						alt="" />
					<div class="dialogTop">
						<a href="javascript:;" class="claseDialogBtn"></a>
					</div>
					<form id="editForm">
						<ul class="editInfos">
							<li><label>账&nbsp;&nbsp;&nbsp;&nbsp;号：<input
									id="itcode" type="text" name="" value="${itcode}" class="ipt" /></label></li>
							<li><label>状&nbsp;&nbsp;&nbsp;&nbsp;态：<input
									id="username" type="text" name="" required value="" class="ipt" /></label></li>
							<li><input type="button" id="submit" value="马上激活" class="submitBtn" /></li>
						</ul>
					</form>
				</div>
			</div>
			
			<a href="luck">
				<div class="block">
					<div style="float: left;">
						<img class="block_img" src="img/home-2.png">
					</div>
					<div class="block_text" style="text-align: center;">抢红包</div>
				</div>
			</a>
		</div>
		<div class="downtime">
			<span>距晚会开始还有：</span>
			<table cellpadding=0 cellspacing=0>
				<tr><td id="td1"><img src="img/num0.jpg"></img></td>
				<td id="td2"><img src="img/num0.jpg"></img></td>
				<td>天</td>
				<td style="margin-left:5px" id="td3"><img src="img/num0.jpg"></img></td>
				<td id="td4"><img src="img/num0.jpg"></img></td>
				<td>时</td></tr>
				<tr><td style="margin-left:5px" id="td5"><img src="img/num0.jpg"></img></td>
				<td id="td6"><img src="img/num0.jpg"></img></td>
				<td>分</td>
				<td id="td7"><img src="img/num0.jpg"></img></td>
				<td id="td8"><img src="img/num0.jpg"></img></td>
				<td>秒</td></tr>
				
			</table>

		</div>
	</div>
	
	<div class="webtail">
		<table class="webtail_table">
			<tr>
				<td>版权声明</td>
				<td><a style="color: #FFFFFF;" href="thanks">鸣谢</a></td>
				<td><a style="color: #FFFFFF;" href="ours">联系方式</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
    var username='<%=session.getAttribute("username")%>';
		if (username == 'null') {
			username = "游客";
			document.getElementById("welcome").innerHTML = "游客，请登录";
		} else {
			document.getElementById("welcome").innerHTML += username;
		}
		
		function change(number,x,y){
			var t=0;
			var i=10;
			while(number-i>=0){
				t++;
				i+=10;
			}
			var j=number+10-i;
			document.getElementById("td"+x).innerHTML="<img src='img/num"+t+".jpg'>";
			document.getElementById("td"+y).innerHTML="<img src='img/num"+j+".jpg'>";
		}
		function countdown() {
			var now = new Date();
			var endDate = new Date(2017, 7, 25, 20);
			var leftTime = endDate.getTime() - now.getTime();
			var leftsecond = parseInt(leftTime / 1000);
			var day1 = Math.floor(leftsecond / (60 * 60 * 24));
			var hour = Math.floor((leftsecond - day1 * 24 * 60 * 60) / 3600);
			var minute = Math.floor((leftsecond - day1 * 24 * 60 * 60 - hour * 3600) / 60);
			var second = Math.floor(leftsecond - day1 * 24 * 60 * 60 - hour * 3600- minute * 60);
			change(day1,1,2);
			change(hour,3,4);
			change(minute,5,6);
			change(second,7,8);
		}
		setInterval(function(){countdown();}, 1000);
	</script>
</body>
</html>