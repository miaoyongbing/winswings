<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title>登录注册界面</title>
<script type="text/javascript" src="/dtss/js/jquery.min.js"></script>
<style type="text/css">
.interface {
	position: absolute;
	background-color: transparent;
	border:1px solid #fff;
	opacity:0.9;
	width: 300px;
	height: auto;
	left: 50%;
	top: 150px;
	color: #2ec0f6;
	border-radius: 8px;
}

.interface form {
	padding: 10px;
}

.input1 {
	width: 270px;
	height: 30px;
	margin: 25px 10px 0px 0px;
}

.input2 {
	width: 150px;
	height: 30px;
	margin: 25px 10px 0px 0px;
}

.input3 {
	width: 270px;
	height: 30px;
	margin: 10px 10px 0px 0px;
}

.input4 {
	width: 150px;
	height: 30px;
	margin: 10px 10px 0px 0px;
}

.btn {
	cursor: pointer;
	height: 44px;
	margin-top: 10px;
	padding: 0;
	background: transparent;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #2ec0f6;
	-moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset, 0 2px 7px
		0 rgba(0, 0, 0, .2);
	-webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset, 0 2px
		7px 0 rgba(0, 0, 0, .2);
	box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	font-size: 14px;
	font-weight: 700;
	color: #fff;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
	-o-transition: all .2s;
	-moz-transition: all .2s;
	-webkit-transition: all .2s;
	-ms-transition: all .2s;
}

#d2 {
	position: absolute;
	left: 50%;
	width: 210px;
	margin-left: -103px;
	margin-top: 10px;
}

#img1 {
	width: 100px;
	height: 34px;
	margin: 25px 10px 0px 0px;
}

#img2 {
	width: 100px;
	height: 34px;
	margin-top: 10px;
	margin-right: 10px;
}

span {
	width: 270px;
	height: 15px;
	color: #B3ADE9;
	line-height: 15px;
	font-size: 12px;
}

span img {
	width: 15px;
	height: 15px;
	margin-left: 5px;
	margin-bottom: 0px;
	float: left;
}
</style>

<script type="text/javascript">
	/*视图切换*/
	var c = true;
	
	function change() {
		if (!c) {
			$("#register").animate({
				left : '+=300px',
				opacity : '0'
			});
			$("#login").animate({
				left : '+=300px',
				opacity : '1'
			});
			c = true;
	
		}
		changeimg1();
	}
	
	
	$(document).ready(function() {
		$("#b2").click(function() {
			if (c) {
				$("#login").animate({
					left : '-=300px',
					opacity : '0'
				});
				$("#register").animate({
					left : '-=300px',
					opacity : '1'
				});
				c = false;
			}
			changeimg2();
		});
		$("#b1").click(function() {
			if (!c) {
				$("#register").animate({
					left : '+=300px',
					opacity : '0'
				});
				$("#login").animate({
					left : '+=300px',
					opacity : '1'
				});
				c = true;
		
			}
			changeimg1();
		});
	});
</script>
<script type="text/javascript">
function Toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var mm = document.createElement('div');
    mm.innerHTML = msg;
    mm.style.cssText="width: 30%;min-width: 100px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 40%;left: 35%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
    document.body.appendChild(mm);
    setTimeout(function() {
        var d = 0.5;
        mm.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        mm.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(mm) }, d * 1000);
    }, duration);
}

<!--验证码变换-->
	function changeimg1() {
		document.getElementById("img1").src = "Code?" + Math.random();
	}
	function changeimg2() {
		document.getElementById("img2").src = "Code?" + Math.random();
	}
</script>
<script type="text/javascript">
	var ch11;
	var ch12;
	var ch13;
	var ch21;
	var ch24;

	<!--登录用户名检查-->
	function check11() {
		var s1 = document.getElementById("itcode1").value;
		var s2 = document.getElementById("valiitcode");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>账号不能为空</div>";
			ch11 = false;
			return false;
		} else {
			var reg = /^[0-9]*$/;
			if (!reg.exec(s1)) {
				s2.innerHTML = "<img src='img/error.png'>"
						+ "<div>账号格式错误</div>";
				ch11 = false;
				return false;
			} else {
				ch11 = true;
				return true;
			}

		}
	}

	<!--登录密码检查-->
	function check12() {
		var s1 = document.getElementById("password1").value;
		var s2 = document.getElementById("valipassword");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>密码不能为空</div>";
			ch12 = false;
			return false;
		}
		ch12 = true;
		return true;
	}

	<!--登录验证码检查-->
	function check13() {
		var s1 = document.getElementById("code1").value;
		var s2 = document.getElementById("valicode");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>验证码不能为空</div>";
			ch13 = false;
			return false;
		} else {
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("get", "Valicode?valicode=" + s1);
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
					if (xmlhttp.responseText == "true") {
						ch13 = true;
						return true;
					} else {
						s2.innerHTML = "<img src='img/error.png'>"
								+ "<div>验证码错误</div>";
						ch13 = false;
						return false;

					}

				}
			}
		}
	}

	<!--注册账号检查-->
	function check21() {
		var s1 = document.getElementById("itcode2").value;
		var s2 = document.getElementById("valiitcode2");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>账号不能为空</div>";
			ch21=false;
			return false;
		} else {
			var reg = /^[0-9]*$/;
			if (!reg.exec(s1)) {
				s2.innerHTML = "<img src='img/error.png'>"+ "<div>账号格式错误</div>";
				ch21=false;
				return false;
			} else {
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						if (xmlhttp.responseText == "true") {
							ch21=true;
							return true;
						} else {
							s2.innerHTML = "<img src='img/error.png'>"+ "<div>此账号已存在</div>";
							ch21=false;
							return false;
						}
					}
				}
				xmlhttp.open("GET", "Checkitcode?itcode=" + s1, true);
				xmlhttp.send();
			}
		}

	}

	<!--注册输入用户名 = -->
	function check211() {
		var s1 = document.getElementById("username").value;
		var s2 = document.getElementById("valiname");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>用户名不能为空</div>";
			return false;
		}
		return true;
	}

	<!--注册密码检查-->
	function check22() {
		var s1 = document.getElementById("password2").value;
		var s2 = document.getElementById("valipassword2");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>密码不能为空</div>";
			return false;
		}
		return true;
	}

	<!--确认密码检查-->
	function check23() {
		var s1 = document.getElementById("password22").value;
		var s2 = document.getElementById("valipassword22");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>确认密码不能为空</div>";
			return false;
		} else {
			if (s1 == document.getElementById("password2").value) {
				return true;
			} else {
				s2.innerHTML = "<img src='img/error.png'>" + "<div>密码不一致</div>";
				return false;
			}
		}
		return true;
	}

	<!--注册验证码检查-->
	function check24() {
		var s1 = document.getElementById("code2").value;
		var s2 = document.getElementById("valicode2");
		s2.innerHTML = "";
		if (s1.length == 0) {
			s2.innerHTML = "<img src='img/error.png'>" + "<div>验证码不能为空</div>";
			ch24=false;
			return false;
		} else {
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("get", "Valicode?valicode=" + s1);
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
					if (xmlhttp.responseText == "true") {
						ch24=true;
						return true;
					} else {
						s2.innerHTML = "<img src='img/error.png'>"+ "<div>验证码错误</div>";
						ch24=false;
						return false;
					}
				}
			}
		}
	}

	<!--登录检查-->
	function check1() {

		var s2 = document.getElementById("valiitcode");
		s2.innerHTML = "";
		if (ch11 && ch12 && ch13) {
			var xmlhttp = new XMLHttpRequest();
			var itcode = document.getElementById("itcode1").value;
			var password = document.getElementById("password1").value;
			xmlhttp.open("get", "validuser?itcode=" + itcode + "&password="
					+ password);
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
					if (xmlhttp.responseText == "true") {
						window.location.href = "home";
					} else {
						if (xmlhttp.responseText == "admin") {
							window.location.href = "/dtss/admin";
						} else {
							s2.innerHTML = "<img src='img/error.png'>"
									+ "<div>账号或密码错误</div>";
						}

					}
				}
			}
		}
		return false;
	}
</script>
<script type="text/javascript">
<!--注册检查-->
	function check2() {
		var s2 = document.getElementById("valiitcode");
		s2.innerHTML = "";
		if (ch21 & check22() & check211() & check23() & ch24) {
			var xmlhttp = new XMLHttpRequest();
			var username = document.getElementById("username").value;
			var itcode = document.getElementById("itcode2").value;
			var password = document.getElementById("password2").value;
			xmlhttp.open("get", "AddUser?itcode=" + itcode + "&username="
					+ username + "&password=" + password);
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
					if (xmlhttp.responseText == "1") {	
						Toast("注册成功，请登陆",2000);
						change();
					} else {
						s2.innerHTML = "<img src='img/error.png'>"
								+ "<div>注册失败</div>";
					}
				}
			}
		}
	}
</script>
<body
	style="background-image: url(img/33.png); background-repeat: no-repeat; background-size: cover">

	<div id="d2">
		<button type="button" id="b1" class="btn" style="width: 100px;">登录</button>
		<button type="button" id="b2" class="btn" style="width: 100px;">注册</button>
	</div>
	<br>

	<div class="interface" id="login" style="margin-left: -150px;">
		<form>
			<h2 style="font-family: StLiti; margin: 0px 40px;">登录胜翼年会系统</h2>
			<div style="margin: 5px 0px;" id="div1">
				<input type="text" id="itcode1" class="input1"
					placeholder="请输入账号..." onblur="check11()"> <span
					id="valiitcode"></span>
			</div>
			<div style="margin: 5px 0px;">
				<input type="password" class="input1" id="password1"
					placeholder="请输入密码..." onblur="check12()"> <span
					id="valipassword"></span>
			</div>
			<div style="margin: 5px 0px;">
				<input type="text" class="input2" id="code1" placeholder="请输入验证码..."
					onblur="check13()"> <img id="img1" style="float: right;"
					src="Code" onclick="changeimg1()" alt="点击更换">
			</div>
			<span id="valicode"></span>
			<button type="button" class="btn" style="width: 270px;"
				onclick="check1()">
				登<span style="width: 20px;"></span>录
			</button>
		</form>
	</div>



	<div class="interface" id="register"
		style="opacity: 0; margin-left: 150px;">
		<form>
			<h2 style="font-family: StLiti; margin: 0px 40px;">注册胜翼年会系统</h2>
			<div style="margin: 5px 0px;">
				<input type="text" class="input3" id="itcode2"
					placeholder="请输入账号..." onblur="check21()"> <span
					id="valiitcode2"></span>
			</div>
			<div style="margin: 5px 0px;">
				<input type="text" class="input3" id="username"
					placeholder="请输入名称..." onblur="check211()"> <span
					id="valiname"></span>
			</div>
			<div style="margin: 5px 0px;">
				<input type="password" class="input3" id="password2"
					placeholder="请输入密码..." onblur="check22()"> <span
					id="valipassword2"></span>
			</div>
			<div style="margin: 5px 0px;">
				<input type="password" class="input3" id="password22"
					placeholder="请确认密码..." onblur="check23()"> <span
					id="valipassword22"></span>
			</div>
			<div style="margin: 5px 0px;">
				<input type="text" class="input4" id="code2" style="width: 150px;"
					placeholder="请输入验证码..." onblur="check24()"> <img id="img2"
					style="float: right;" src="Code?" onclick="changeimg2()" alt="点击更换" />
				<span id="valicode2"></span>
			</div>
			<button type="button" class="btn" style="width: 270px;"
				onclick="check2()">
				注<span style="width: 20px;"></span>册
			</button>
		</form>
	</div>
	<script type="text/javascript">
		changeimg1();
	</script>
</body>
</html>