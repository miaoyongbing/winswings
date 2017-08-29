<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
    <meta name="save" content="history"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="blank"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/rain.css"/>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <title>抢红包</title>
    <style type="text/css">
    .redpaper{
	position:fixed;
	width:300px;
	height:400px;
	top:50px;
	left:50%;
	margin-left:-150px;
	display:none;
}
.redpaper_text{
	position:absolute;
	width:200px;
	height:150px;
	top:150px;
	left:50%;
	margin-left:-100px;
	color:#FFFF66;
	text-align:center;
	font-family:LiSu;
	font-size:40px;
}
.btn12{
	position:absolute;
	width:80px;
	height:40px;
	top:100px;
	right:10%;
	margin-left:-400px;
	
	color:#E23352;
	font-size:20px;
	font-family:LiSu;
	background-color:#EACF36;
	border:1px solid #E23352;
	border-radius:8px;
}

.btn12:hover{
	background-color:#E23352;
	color:#FFFFFF;
}
.btn2{
	position:absolute;
	width:350px;
	height:80px;
	top:500px;
	left:50%;
	margin-left:-175px;
	color:#E23352;
	font-size:50px;
	font-family:LiSu;
	background-color:#EACF36;
	border:1px solid #E23352;
	border-radius:8px;
}
.btn2:hover{
	background-color:#E23352;
	color:#FFFFFF;
}
    
        .barrage .screen {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0px;
            right: 0px;
        }

        .barrage .screen .s_close {
            z-index: 2;
            top: 20px;
            right: 20px;
            position: absolute;
            text-decoration: none;
            width: 40px;
            height: 40px;
            border-radius: 20px;
            text-align: center;
            color: #fff;
            background: #000;
            line-height: 40px;
            display: none;
        }

        .barrage .screen .mask {
           
        }

        .barrage {
           
            width: 100%;
            height: 100%;
        }

        .barrage .screen .mask div {
            position: absolute;
            font-size: 20px;
            font-weight: bold;
            white-space: nowrap;
            line-height: 40px;
            z-index: 40;
        }

        .barrage .send {
            z-index: 1;
            width: 100%;
            height: 55px;
            background: #000;
            position: absolute;
            bottom: 0px;
            text-align: center;
        }

        .barrage .send .s_text {
            width: 600px;
            height: 40px;
            line-height: 10px;
            font-size: 20px;
            font-family: "微软雅黑";
        }

        .barrage .send .s_btn {
            width: 105px;
            height: 40px;
            background: #22B14C;
            color: #fff;
        }
        body {background:url(img/redwarbackgrand.jpg) top center no-repeat; background-size:cover;}
    </style>
    <script type="text/javascript">
function Toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var m = document.createElement('div');
    m.innerHTML = msg;
    m.style.cssText="width: 30%;min-width: 100px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 40%;left: 35%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
}

function abc(){
	if("${money}"=="未激活钱包"){
		Toast("请您先去激活钱包");
	}else{
	var r=document.getElementById("redpaper_text");
	var xml=new XMLHttpRequest();
	xml.onreadystatechange=function(){
		if (xml.readyState==4 && xml.status==200)
	    {
	     var number=xml.responseText;
	     if(number==0){
	    	 r.innerHTML="很遗憾<br>你没有抢到红包";
	     }
	     else{
	    	 if(number==-1)
	    		 r.innerHTML="您已经抢过该红包了！";
	    	 else
	    	 r.innerHTML="恭喜你获得<br>"+number+"元"+"<br>"+"红包";
	     }
	    }
	};
	var url="getluck";
	xml.open("GET", url, true);
	xml.send();	
	document.getElementById("redpaper1").style.display="block";
	setTimeout("abc2()",3000); 
	}
}
function abc2(){
	document.getElementById("redpaper1").style.display="none";
}

</script >
</head>
<script>
/*var a =0;
var Timerr = setInterval(aa,200);
var removepackage = setInterval(function(){
			for(var jj=0;jj<$('.div>div').size()/4;jj++){
			$('.div>div').eq($('.div>div').size()-jj).remove();
			}
		},1000)
function aa(){
	for(var i=0;i<2;i++){
	var m=parseInt(Math.random()*700+100);
	var j2=parseInt(Math.random()*300+1200);
	var j=parseInt(Math.random()*1600+000);
	var j1=parseInt(Math.random()*300+300);
	var n=parseInt(Math.random()*10+(-10));
	$('.div').prepend('<div class="dd"></div>');
	$('.div').children('div').eq(0).css({'left':j,'top':n});
	$('.div').children('div').eq(0).animate({'left':j-j1,'top':$(window).height()+20},3000);
	}
	}
$(document).on('touchstart', '.dd', function(){
	$(this).css("background-position","0 -100px");
	a++;
	if(a == 5){
		$(".chuai_box").show();
		clearInterval(Timerr,20);
		$(".div").removeClass("bg_1");
		setTimeout(function(){
			$(".div").addClass("bg_2");
		},3000);
	}
}); */
</script>

<body >
<div class="barrage">
    <div class="screen">
      <div class="mask">
        </div>
    </div>
    <span class="s_close">X</span>
</div>
<div class="page_rain">
	<div class="div bg_1"></div>
	<!--蒙层-->
	<div class="chuai_box" style="display: none;">
		<div class="chuai">
			<p>红包</p>
		</div>
	</div>
</div>
<a href="home"><input type="button" value="返回" class="btn12"></a>
<div id="redpaper1" class="redpaper"><img src="img/red.png" height=400px width=300px ><div id="redpaper_text" class="redpaper_text"></div></div>
<input type="button" value="抢红包" class="btn2" onclick="abc()">


<script>
    $(function () {
        $(".showBarrage,.s_close").click(function () {
            $(".barrage,.s_close").toggle("slow");
        });
        window.location.href='#';
        // init_animated();
        init_barrage();
    }) 
    
    var records=new Array();
    var index=0;
      function abcd () {
    	  if(records[index]!=null){
            var _lable = $("<div style='right:20px;top:0px;opacity:1;color:"
                + getRandomColor()
                + ";'>"
                + records[index]
                + "</div>");
            $(".mask").css("opacity","0");
            $(".mask").append(_lable.show());
            init_barrage()
    	  }
           // init_barrage();
           index=index+2;
           if(index==records.length){
        	   index=1;
           }
           if(index>records.length){
        	   index=0;
           }
          
        }//)
        
function getrecord(){
	
	 $.ajax({
	        type : "GET",
	        url : "luckbroadcast",
	        dataType : "json",
	        success : function (data) {
	        	var json = eval(data);
	            $.each(json, function (i, item) {
	                records[i]=json[i].username+"抢到"+json[i].amount+"元！";
	            });
	        }
	    });
}
setInterval('abcd()',2000);
    //初始化弹幕技术
    function init_barrage() {
        var _top = 0;
        $(".mask div").show().each(function () {
            var _left = $(window).width() - $(this).width();//浏览器最大宽度，作为定位left的值
            var _height = $(window).height()/2;//浏览器最大高度
            _top += 75;
            if (_top >= (_height - 130)) {
                _top = 0;
            }
            $(this).css({
                left: _left,
                top: _top,
                color: getRandomColor()
            });
            //定时弹出文字
            var time = 10000;
            if ($(this).index() % 2 == 0) {
                time = 15000;
            }
            $(".mask").animate({opacity:1},1000);
            $(this).animate({
                left: "-" + _left + "px"
            }, time, function () {
                $(this).remove();
            });
        });
    }

    //获取随机颜色
    function getRandomColor() {
        return '#' + (function (h) {
            return new Array(7 - h.length).join("0") + h
        })((Math.random() * 0x1000000 << 0).toString(16))
    }
    getrecord();
    abcd();
</script>
</body>
</html>