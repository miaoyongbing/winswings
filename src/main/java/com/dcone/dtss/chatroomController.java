package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.ChatroomDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.model.chat;
import com.dcone.dtss.model.dc_user;


/**
 * 聊天室操作
 */
@Controller
public class chatroomController {
	
	private static final Logger logger = LoggerFactory.getLogger(chatroomController.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取当前在线用户
	 * @param request
	 * @param model
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/getuseronline", method = RequestMethod.GET)
	public void getuseronline(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext application = request.getServletContext();
		@SuppressWarnings("unchecked")
		ArrayList<dc_user> users = (ArrayList<dc_user>)application.getAttribute("OnlineUsers");
		ArrayList<String> name=new ArrayList<String>();
		System.out.println(users.size());
        for(int i=0; i<users.size();i++)  {  
    		System.out.println("test:"+name.toString());
        	if(users.get(i).getUsername()!=null&&users.get(i).getUsername()!=""){
               			boolean temp=true;
        			for(String str:name) {
        				System.out.println("tttt:"+str);
        				if(str.equals(users.get(i).getUsername()))
        				{
        					temp=false;
        					break;
        				}
        			}
					if (temp) {
						name.add(users.get(i).getUsername());
						String face=users.get(i).getPortrait();
	                    String username=users.get(i).getUsername();
	                    System.out.println(username+face);
	                    String s= "<div class='user'>"+
	            						"<div class='user_img'>"+"<img src="+face+" height='50' width='50'>"+"</div>"+
	            						"<button class='user_name'>"+username+"</button>"+
	            						"</div>";
	                 try {
						response.getWriter().write(s);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}

        	}
        }          
	}
	
	/**
	 * 获取聊天 记录
	 * @param request
	 * @param response
	 * @param model
	 * @param out
	 */
	@RequestMapping(value = "/getrecords", method = RequestMethod.GET)
	public void getrecords(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		//response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		
		List<chat> records=ChatroomDAO.getrecords(jdbcTemplate);
		HttpSession session=request.getSession();
		String name=(String) session.getAttribute("username");
		 for(int i=records.size()-1;i>-1; i--) {
			String username=records.get(i).getUsername();
			String time=records.get(i).getTime();
			String record=records.get(i).getRecord();
			String portrait=records.get(i).getPortrait();
			String s="";
			if (!name.equals(username))
				s = "<div class='msg'>" + "<div class='msg_img'><img src='" + portrait
						+ "'width='50px' height='50px'></div>" + "<div class='horn'></div>" + "<div class='msg_body'>"
						+ "<div class='msg_head'>" + username + "  " + time + "</div>" + "<div class='message'>"
						+ record + "</div>" + "</div>" + "</div>";
			else
				s = "<div class='msg'>" + "<div class='msg_img2'><img src='" + portrait
						+ "'width='50px' height='50px'></div>" + "<div class='horn2'></div>" + "<div class='msg_body2'>"
						+ "<div class='msg_head'>" + username + "  " + time + "</div>" + "<div class='message'>"
						+ record + "</div>" + "</div>" + "</div>";
			try {
				response.getWriter().write(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 插入聊天记录
	 * @param record
	 * @param req
	 * @param resp
	 * @param model
	 * @param out
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/insertrecord", method = RequestMethod.GET)
	public void insertrecords(String record,HttpServletRequest req, HttpServletResponse resp, Model model, PrintWriter out) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		
		System.out.println("records is:"+record);
		HttpSession session = req.getSession();
		String itcode = (String) session.getAttribute("itcode");
		int i= ChatroomDAO.insert(itcode, record, jdbcTemplate);
		out.write(i);
	}
	
	
}
