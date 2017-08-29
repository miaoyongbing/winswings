<%@ page import="contosql.*,java.util.*"%>
<meta http-equiv="refresh"content="2;url="">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd 

">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello!</title>
</head>

<body>
<script type="text/javascript">
function tenp(){
	var s=cookie.getParameter("time");
	if(s!="1")
		location.reload;
	setTimeout("tenp()",100);
}
</script>
<% ContoSql cs =new ContoSql();
   
   ArrayList<StoreChat> sc=cs.getrecentchats();  //<meta http-equiv="refresh"content="2;url="">
   int i=0;
   if(i>100)
   {
	   i=i-100;
   }else{
	   i=0;
   }
   for(;i<sc.size();i++){
%>
<div >
<div>

<img  src="image/default.png" width="20px" height="20px">
</div><% out.println(sc.get(i).getname()+" "+sc.get(i).getchattime()); %>
</div>
<div>
<% out.print(sc.get(i).getrecords()); %>
<p></p>
</div><% } %>
<script>tenp()</script>
</body>
</html>