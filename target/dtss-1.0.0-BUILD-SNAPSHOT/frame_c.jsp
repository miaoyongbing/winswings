<%@page import="java.util.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="chatroom.connectSQL" %>
<%@page import="javax.servlet.http.Cookie" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

#bk{
  width:100%;
  border:1px solid red;
  margin:0 auto; 
}
#lyInput{ 
  width:100%; 
} 
#text{
  width:90%; 
  float:left;
  height:100px; a
} 
#btn{
  width:10%
  display:block; 
  float:right;
  height:100px;
}
</style>

<script>
function check(){

	if(document.getElementById("text").value.length==0)
	{
		document.getElementById("text").value="聊天不能为空";
		alert(username);
	}
	else{
		
	    location.reload();
	    
	}
}

</script>


</head>
<body>
<form id="form1" name="form1" action="chatroom" method="post" target="iframe">
<div id="bk">
    <div id="lyInput">
        <textarea id="text" name="text" rows="6" cols="80"></textarea>
        <input type="submit" id="btn" value="发言" onclick="check()">
      <!--   <button id="btn" onclick="chect()">发言</button></p> -->
    </div>
</div>

</form>
<iframe name="iframe" id="iframe" style="display:none"></iframe>



</body>


</html>