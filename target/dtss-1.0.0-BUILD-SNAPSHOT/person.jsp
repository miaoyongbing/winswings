<span style="font-size: 12px;"><span style="font-size: 14px;">
		<%@ page language="java"
			import="java.sql.*,java.io.*,bean.User,java.util.*"%>
		<%@ page contentType="text/html;charset=utf-8"%>
		<html>
<head>
<meta http-equiv="refresh" content="10">

<style type="text/css">
.a-upload {
	padding: 4px 10px;
	height: 20px;
	line-height: 20px;
	position: relative;
	cursor: pointer;
	color: #888;
	background: #fafafa;
	border: 1px solid #ddd;
	border-radius: 4px;
	overflow: hidden;
	display: inline-block;
	*display: inline;
	*zoom: 1
}

.a-upload  input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}

.a-upload:hover {
	color: #444;
	background: #eee;
	border-color: #ccc;
	text-decoration: none
}

.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}

table {
	border: 2px #CCCCCC solid;
	width: 360px;
}

td, th {
	height: 30px;
	border: #CCCCCC 1px solid;
}
</style>
</head>
<body>
	<%
		String itcode = session.getAttribute("itcode").toString();
		out.println("欢迎itcode:" + itcode + "  离开请按 <a href='logout'>注销</a>");

		//System.out.println(phsrc);
		/*
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("username")) {
					out.print("欢迎" + c.getValue());	
				}
			}
		} else {
			out.println("您需要认证后才能访问本页面 ！");
			//response.sendRedirect("login.html");
		
		}*/
	%>


	<hr>
	<!-- 
<form name="form1"  action="vali.do" method="get" >
<table border = "0">
<tr><td>姓名:</td><td><input id="username" name="username" width="10" height="10"></td></tr>
<tr><td><input type="submit" value="登录"></td></tr>
</table>
</form>
-->


	<%
		//驱动程序名   
		String driverName = "com.mysql.jdbc.Driver";
		//数据库用户名   
		String userName = "miao";
		//密码   
		String userPasswd = "zxczxc";
		//数据库名   
		String dbName = "chatroom";
		//表名   
		String tableName = "user";
		//联结字符串   
		String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=true&user=" + userName + "&password="
				+ userPasswd;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(url);

		Statement statement1 = connection.createStatement();
		String sql1 = "SELECT * FROM cd_user where itcode=" + itcode;
		ResultSet rs1 = statement1.executeQuery(sql1);

		String name = "";
		String nickName = "";
		String portrait = "";
		while (rs1.next()) {
			name = rs1.getObject("name").toString();
			nickName = rs1.getObject("nickName").toString();
			portrait = rs1.getObject("portrait").toString();
		}

		String phsrc = portrait + "?" + Math.random();
		System.out.println(phsrc);
	%>
	<img id="face" alt="头像获取失败" src=<%=phsrc%> width="42" height="42">
	<p>
		<%
			out.print("姓名：" + name);
		%>
	</p>
	<%
		out.println("昵称：" + nickName);
		rs1.close();
		statement1.close();

		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM " + tableName;
		ResultSet rs = statement.executeQuery(sql);
	%>
	<hr>
	<script type="text/javascript">
		function changeimg1() {
			document.getElementById("face").src =
	<%=phsrc%>
		;
		}
	</script>
	
	<form name="form2" id="form2" action="Upload" method="post"
		enctype="multipart/form-data">
		<a href="javascript:;" class="a-upload"> <input type="file"
			name="face_icon" id="face_icod">点击选择头像
		</a> <a href="javascript:;" class="file">上传 <input type="submit"
			value="上传" onclick="changeimg1()">
		</a>
	</form>
	
	<br>
	<br>
	<table align="center">
		<tr>
			<th></th>
			<th>
				<%
					out.print("聊天室人员");
				%>
			</th>

		</tr>





		<%
			// HttpSession session1 = request.getSession();
			//ServletContext application1 = request.getServletContext();
			ArrayList<User> users = (ArrayList<User>) application.getAttribute("OnlineUsers");
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getNickName() != null && users.get(i).getNickName() != "") {
					String face = users.get(i).getFace();//+"?"+Math.random()
		%>
		<tr>
			<td width="10%"><img src=<%=face%> width="20px" height="20px"></td>
			<td style="width: 50px" align="center">
				<%
					out.print(users.get(i).getNickName());//
				%>
			</td>

		</tr>
		<%
			}
			}
		%>
	</table>
	<%
		rs.close();
		statement.close();
		connection.close();
	%>
</body>
		</html>
</span><span style="font-size: 24px; color: rgb(255, 0, 0);"> </span></span>